package com.scherzoteller.springsandbox.web.controller.testfuture;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.scherzoteller.springsandbox.service.testfuture.ITestFutureService;
import com.scherzoteller.springsandbox.web.beans.TestFutureGUIObject;

@Controller
@RequestMapping("/testFuture")
public class TestFutureController /* extends AbstractBaseController */ {
    private static final Log LOG = LogFactory.getLog(TestFutureController.class);
    @Autowired
    private ITestFutureService testFutureService;

    @PostConstruct
    public void test() {
        LOG.debug("TestFutureController was created");
    }

    @RequestMapping("/list")
    @ResponseBody
    public DeferredResult<TestFutureGUIObject> lst() {
        LOG.debug("TestFutureController.lst() begin");
        try {
            TestFutureGUIObject guiObject = new TestFutureGUIObject(testFutureService.getTestFuture());
            guiObject.getDeferedResult().onCompletion(() -> LOG.debug("is completed !!!!"));
            return guiObject.getDeferedResult();
        } finally {
            LOG.debug("TestFutureController end");
        }
    }

    @RequestMapping("/listSync")
    @ResponseBody
    public TestFutureGUIObject lstSync() {
        LOG.debug("TestFutureController.lstSync() begin");

        return new TestFutureGUIObject("str", 1, BigDecimal.valueOf(123));
    }

    @RequestMapping("/listCall")
    @ResponseBody
    public Callable<TestFutureGUIObject> lstCallable() {
        try {
            LOG.debug("TestFutureController.lstCallable() begin");
            return () -> {
                Thread.sleep(5000);
                LOG.debug("result filled");
                return new TestFutureGUIObject("str", 1, BigDecimal.valueOf(123));
            };
        } finally {
            LOG.debug("out of TestFutureController.lstCallable()");
        }

    }

    @RequestMapping("/listAsynSimple")
    @ResponseBody
    public DeferredResult<TestFutureGUIObject> listAsynSimple() {
        try {
            LOG.debug("TestFutureController begin");
            DeferredResult<TestFutureGUIObject> dr = new DeferredResult<>();
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    LOG.error("Interrupted!");
                    Thread.currentThread().interrupt();
                }
                LOG.debug("result filled");
                dr.setResult(new TestFutureGUIObject("str", 1, BigDecimal.valueOf(123)));
            }).start();
            return dr;
        } finally {
            LOG.debug("out of listAsynSimple()");
        }

    }
}
