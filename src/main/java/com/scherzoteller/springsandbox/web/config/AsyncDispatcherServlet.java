package com.scherzoteller.springsandbox.web.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.DispatcherServlet;

public class AsyncDispatcherServlet extends DispatcherServlet {
    private static final long serialVersionUID = -132214508907727907L;
    private ExecutorService exececutor;
    private static final int NUM_ASYNC_TASKS = 15;
    private static final long TIME_OUT = 10l * 1000;
    private static final Log LOG = LogFactory.getLog(AsyncDispatcherServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        exececutor = Executors.newFixedThreadPool(NUM_ASYNC_TASKS);
    }

    @Override
    public void destroy() {
        exececutor.shutdownNow();
        super.destroy();
    }

    @Override
    protected void doDispatch(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final AsyncContext ac = request.startAsync(request, response);
        ac.setTimeout(TIME_OUT);
        FutureTask<?> task = new FutureTask<>(() ->{
                try {
                    LOG.debug("Dispatching request " + request);
                    AsyncDispatcherServlet.super.doDispatch(request, response);
                    LOG.debug("doDispatch returned from processing request " + request);
                    ac.complete();
                } catch (Exception ex) {
                    LOG.error("Error in async request", ex);
                }
        }, null);

        ac.addListener(new AsyncDispatcherServletListener(task));
        exececutor.execute(task);
    }

    private class AsyncDispatcherServletListener implements AsyncListener {

        private FutureTask<?> futureTask;

        public AsyncDispatcherServletListener(FutureTask<?> futureTask) {
            this.futureTask = futureTask;
        }

        @Override
        public void onTimeout(AsyncEvent event) throws IOException {
            LOG.warn("Async request did not complete timeout occured");
            handleTimeoutOrError(event, "Request timed out");
        }

        @Override
        public void onComplete(AsyncEvent event) throws IOException {
            LOG.debug("Completed async request");
        }

        @Override
        public void onError(AsyncEvent event) throws IOException {
            LOG.error("Error in async request", event.getThrowable());
            handleTimeoutOrError(event, "Error processing " + event.getThrowable().getMessage());
        }

        @Override
        public void onStartAsync(AsyncEvent event) throws IOException {
            LOG.debug("Async Event started..");
        }

        private void handleTimeoutOrError(AsyncEvent event, String message) {
            PrintWriter writer = null;
            try {
                futureTask.cancel(true);
                HttpServletResponse response = (HttpServletResponse) event.getAsyncContext().getResponse();
                writer = response.getWriter();
                writer.print(message);
                writer.flush();
            } catch (IOException ex) {
                LOG.error(ex);
            } finally {
                event.getAsyncContext().complete();
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

}