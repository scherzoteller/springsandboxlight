package com.scherzoteller.springsandbox.bo;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface ITestFuture {

    CompletableFuture<String> getStrResult();

    void setStrResult(CompletableFuture<String> strResult);

    CompletableFuture<Integer> getIntResult();

    void setIntResult(CompletableFuture<Integer> intResult);

    CompletableFuture<BigDecimal> getBdResult();

    void setBdResult(CompletableFuture<BigDecimal> bdResult);

}