<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ability" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addAbility" key="ability.addAbility"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="hAbilities" key="ability.hiddenAbilities"/>
<fmt:message var="search" key="misc.search"/>

<ability:header>
<jsp:attribute name="body">

    <div class="jumbotron">
        <div class="manage-buttons-container">
            <c:if test="${isAuthenticated}">
                <a href="${pageContext.request.contextPath}/ability/new" class="m-btn black">
                    <span class="glyphicon glyphicon-plus"></span>
                    ${addAbility}
                </a>
            </c:if>
            <form class="search-box" action="${pageContext.request.contextPath}/ability" method="GET">
                <input type="text" class="search-square" name="searchFilter" value="${searchFilter}" />
                <input class="m-btn black" type="submit" value="${search}" />
            </form>
        </div>
        <c:forEach items="${abilities}" var="ability" varStatus="ic">
            <div class="inline-block-content">
                <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/ability/${ability.id}">
                    <div class="ability-container">
                        <img class="img-ability" src="${pageContext.request.contextPath}/webApi/ability/${ability.id}" alt="${noImage}">
                        <span>${ability.name}</span>
                    </div>
                </a>
            </div>
        </c:forEach>
        <c:if test="${not empty hiddenAbilities}">
            <h3>${hAbilities}</h3>
            <c:forEach items="${hiddenAbilities}" var="ability" varStatus="ic">
                <div class="inline-block-content">
                    <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/ability/${ability.id}">
                        <div class="ability-container">
                            <img class="img-ability" src="${pageContext.request.contextPath}/webApi/ability/${ability.id}" alt="${noImage}">
                            <span>${ability.name}</span>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </div>
    
</jsp:attribute>
</ability:header>
