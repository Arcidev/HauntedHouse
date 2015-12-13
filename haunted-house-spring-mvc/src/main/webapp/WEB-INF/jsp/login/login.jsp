<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="login" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="email" key="login.email"/>
<fmt:message var="password" key="login.password"/>
<fmt:message var="register" key="login.register"/>
<fmt:message var="login" key="login.login"/>

<login:header>
<jsp:attribute name="body">
    <div class="login-form center-block">
        <form name='loginForm' action="<c:url value='authenticate' />" method='POST'>
            <div class="form-group">
                <span>${email}:</span>
                <input class="login-input" type='text' name='email' value=''>
            </div>
            <div class="form-group">
                <span>${password}:</span>
                <input class="login-input" type='password' name='password' />
            </div>
            <div class="form-group">
                <input class="m-btn black login-button-left button-100" name="submit" type="submit" value="${login}" />
                <input class="m-btn login-button-right button-100" name="submit" type="submit" value="${register}" />
            </div>
        </form>
    </div>
</jsp:attribute>
</login:header>