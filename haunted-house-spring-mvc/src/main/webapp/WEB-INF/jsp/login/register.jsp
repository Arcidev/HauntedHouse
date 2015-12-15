<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="login" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="firstName" key="login.firstName"/>
<fmt:message var="lastName" key="login.lastName"/>
<fmt:message var="email" key="login.email"/>
<fmt:message var="password" key="login.password"/>
<fmt:message var="passwordAgain" key="login.passwordAgain"/>
<fmt:message var="register" key="login.register"/>

<login:header>
<jsp:attribute name="body">
    <div class="login-form center-block">
        <form:form name='loginForm' action="${pageContext.request.contextPath}/register" modelAttribute="userCreate" method='POST'>
            <div class="form-group">
                <p>
                    <span>${firstName}:</span>
                    <input class="login-input" type='text' name='firstName' value="${userCreate.firstName}">
                </p>
                <c:if test="${not empty firstName_error}">
                    <p class="input_error">*${firstName_error}</p>
                </c:if>
            </div>
            <div class="form-group">
                <p>
                    <span>${lastName}:</span>
                    <input class="login-input" type='text' name='lastName' value="${userCreate.lastName}">
                </p>
                <c:if test="${not empty lastName_error}">
                    <p class="input_error">*${lastName_error}</p>
                </c:if>
            </div>
            <div class="form-group">
                <p>
                    <span>${email}:</span>
                    <input class="login-input" type='text' name='email' value="${userCreate.email}">
                </p>
                <c:if test="${not empty email_error}">
                    <p class="input_error">*${email_error}</p>
                </c:if>
            </div>
            <div class="form-group">
                <p>
                    <span>${password}:</span>
                    <input class="login-input" type='password' name='password' value="${userCreate.password}" />
                </p>
                <c:if test="${not empty password_error}">
                    <p class="input_error">*${password_error}</p>
                </c:if>
            </div>
            <div class="form-group">
                <p>
                    <span>${passwordAgain}:</span>
                    <input class="login-input" type='password' name='passwordAgain' value="${userCreate.passwordAgain}" />
                </p>
                <c:if test="${not empty passwordAgain_error}">
                    <p class="input_error">*${passwordAgain_error}</p>
                </c:if>
            </div>
            <div class="form-group">
                <input class="m-btn black register-button button-100" name="submit" type="submit" value="${register}" />
            </div>
        </form:form>
    </div>
</jsp:attribute>
</login:header>
