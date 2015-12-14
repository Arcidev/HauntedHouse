<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ability" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addAbility" key="ability.addAbility"/>
<fmt:message var="noImage" key="ability.noImage"/>

<ability:header>
<jsp:attribute name="body">

    <div class="jumbotron">
        <div class="manage-buttons-container">
            <a href="/ability/new" class="m-btn black">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                ${addAbility}
            </a>
        </div>
        <c:forEach items="${abilities}" var="ability" varStatus="ic">
            <div class="inline-block-content"><!-- bootstrap responsive grid -->
                <a class="anchor-image" href="${pageContext.request.contextPath}/ability/${ability.id}">
                    <div class="ability-container">
                        <img src="${pageContext.request.contextPath}/ability/image/${ability.id}" alt="${noImage}">
                        <span>${ability.name}</span>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    
</jsp:attribute>
</ability:header>
