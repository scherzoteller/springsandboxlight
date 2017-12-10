package com.scherzoteller.springsandbox.service.testfuture;

import org.springframework.security.access.prepost.PreAuthorize;

import com.scherzoteller.springsandbox.bo.impl.TestFuture;
import com.scherzoteller.springsandbox.service.IBaseService;

public interface ITestFutureService extends IBaseService {
    @PreAuthorize("isAuthenticated()")
    TestFuture getTestFuture();

}