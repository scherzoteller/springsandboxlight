package com.scherzoteller.springsandbox.web.beans;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scherzoteller.springsandbox.bo.impl.TestFuture;
import com.scherzoteller.springsandbox.web.controller.testfuture.TestFutureController;

public class TestFutureGUIObject {
    private static final Log LOG = LogFactory.getLog(TestFutureController.class);
    private String strResult;
    private Integer intResult;
    private BigDecimal bdResult;
    private DeferredResult<TestFutureGUIObject> deferedResult;
    
    public TestFutureGUIObject(TestFuture bo){
        LOG.debug("TestFutureGUIObject constructor beginning");
        this.deferedResult = new DeferredResult<>();
        CompletableFuture.allOf(
            bo.getBdResult(),
            bo.getStrResult(),
            bo.getIntResult())
        .thenRun(() -> { 
            this.bdResult = bo.getBdResult().join();
            this.strResult = bo.getStrResult().join();
            this.intResult = bo.getIntResult().join();
            LOG.debug("Setting DefferedResult's value"); 
            this.deferedResult.setResult(this);
        } );
//        CompletableFuture.allOf(
//            bo.getBdResult().thenAccept(bdResult -> this.bdResult = bdResult),
//            bo.getStrResult().thenAccept(strResult -> this.strResult = strResult),
//            bo.getIntResult().thenAccept(intResult -> this.intResult = intResult))
//        .thenRun(() -> { 
//            LOG.debug("Setting DefferedResult's value"); 
//            this.deferedResult.setResult(this);
//        } );
    }
    
    
    public TestFutureGUIObject(String strResult, Integer intResult, BigDecimal bdResult) {
        super();
        this.strResult = strResult;
        this.intResult = intResult;
        this.bdResult = bdResult;
    }



    public String getStrResult() {
        return strResult;
    }
    public Integer getIntResult() {
        return intResult;
    }
    public BigDecimal getBdResult() {
        return bdResult;
    }

    @JsonIgnore
    public DeferredResult<TestFutureGUIObject> getDeferedResult() {
        return deferedResult;
    }
}
