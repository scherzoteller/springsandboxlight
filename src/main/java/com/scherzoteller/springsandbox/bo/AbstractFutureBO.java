package com.scherzoteller.springsandbox.bo;

import java.util.concurrent.CompletableFuture;

public class AbstractFutureBO {
    protected final <T> CompletableFuture<T> nullCompletableFuture(){
        return CompletableFuture.supplyAsync(() -> (T)null);
    }
    protected final <T> CompletableFuture<T> defaultValue(CompletableFuture<T> val){
        return val != null ? val : nullCompletableFuture();
    }
}
