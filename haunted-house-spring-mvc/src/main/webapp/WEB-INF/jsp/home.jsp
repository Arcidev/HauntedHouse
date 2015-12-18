<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="home" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="project" key="navigation.project"/>
<fmt:message var="welcome" key="home.welcome"/>
<fmt:message var="joinUs" key="home.joinUs"/>
<fmt:message var="info" key="home.info"/>

<home:header>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>${project}</h1>
        <p class="home-paragraph">
            ${welcome}
        </p>
        <p class="home-paragraph">
            <a href="${pageContext.request.contextPath}/register">${joinUs}</a> ${info}
       </p>
    </div>
    
</jsp:attribute>
</home:header>