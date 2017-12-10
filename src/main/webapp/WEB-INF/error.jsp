<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/bv-spring" prefix="spring" %>

<c:set var="errorMessage" value="${requestScope.EXCEPTION_MESSAGE}" />


<div id="errorTitle">
    <h2>${errorMessage}</h2>
</div>