package com.scherzoteller.springsandbox.service.testfuture.impl;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scherzoteller.springsandbox.bo.impl.TestFuture;
import com.scherzoteller.springsandbox.service.testfuture.ITestFutureService;

public class TestFutureService implements ITestFutureService {
    private static final Log LOG = LogFactory.getLog(TestFutureService.class);

    /* (non-Javadoc)
     * @see com.bureauveritas.springsandbox.service.testfuture.ITestFutureService#getTestFuture()
     */
    @Override
    public TestFuture getTestFuture(){
        TestFuture tf = new TestFuture();
        LOG.debug("service begin");
        
        tf.setIntResult(CompletableFuture.supplyAsync(() -> {
            LOG.debug("int result supplier begin");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.debug("int result supplier end");
            return 123;
        }));
        
        tf.setStrResult(CompletableFuture.supplyAsync(() -> {
            LOG.debug("str result supplier begin");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.debug("str result supplier end");
            return "123";
        }));
        tf.setBdResult(CompletableFuture.supplyAsync(() -> {
            LOG.debug("bd result supplier begin");
            try {
                Thread.sleep(13000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.debug("bd result supplier end");
            return BigDecimal.valueOf(123l);
        }));
        LOG.debug("service return ");
        return tf;
    }
}
