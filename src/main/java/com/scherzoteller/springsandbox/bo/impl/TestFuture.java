package com.scherzoteller.springsandbox.bo.impl;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import com.scherzoteller.springsandbox.bo.AbstractFutureBO;
import com.scherzoteller.springsandbox.bo.ITestFuture;


public class TestFuture extends AbstractFutureBO implements ITestFuture {
    private CompletableFuture<String> strResult = nullCompletableFuture();
    private CompletableFuture<Integer> intResult = nullCompletableFuture();
    private CompletableFuture<BigDecimal> bdResult = nullCompletableFuture();
    
    /* (non-Javadoc)
     * @see com.bureauveritas.springsandbox.bo.testfuture.impl.ITestFuture#getStrResult()
     */
    @Override
    public CompletableFuture<String> getStrResult() {
        return strResult;
    }
    /* (non-Javadoc)
     * @see com.bureauveritas.springsandbox.bo.testfuture.impl.ITestFuture#setStrResult(java.util.concurrent.CompletableFuture)
     */
    @Override
    public void setStrResult(CompletableFuture<String> strResult) {
        this.strResult = defaultValue(strResult);
    }
    /* (non-Javadoc)
     * @see com.bureauveritas.springsandbox.bo.testfuture.impl.ITestFuture#getIntResult()
     */
    @Override
    public CompletableFuture<Integer> getIntResult() {
        return intResult;
    }
    /* (non-Javadoc)
     * @see com.bureauveritas.springsandbox.bo.testfuture.impl.ITestFuture#setIntResult(java.util.concurrent.CompletableFuture)
     */
    @Override
    public void setIntResult(CompletableFuture<Integer> intResult) {
        this.intResult = defaultValue(intResult);
    }
    /* (non-Javadoc)
     * @see com.bureauveritas.springsandbox.bo.testfuture.impl.ITestFuture#getBdResult()
     */
    @Override
    public CompletableFuture<BigDecimal> getBdResult() {
        return bdResult;
    }
    /* (non-Javadoc)
     * @see com.bureauveritas.springsandbox.bo.testfuture.impl.ITestFuture#setBdResult(java.util.concurrent.CompletableFuture)
     */
    @Override
    public void setBdResult(CompletableFuture<BigDecimal> bdResult) {
        this.bdResult = defaultValue(bdResult);
    }
    
}
