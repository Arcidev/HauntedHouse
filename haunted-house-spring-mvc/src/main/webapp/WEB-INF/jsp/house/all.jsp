<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="house" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addHouse" key="house.addHouse"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="hidHouses" key="house.hidHouses"/>
<fmt:message var="search" key="misc.search"/>

<house:header>
<jsp:attribute name="body">

    <div class="jumbotron">
        
        <div class="manage-buttons-container">
            <c:if test="${isAuthenticated}">
                <a href="${pageContext.request.contextPath}/house/new" class="m-btn black">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    ${addHouse}
                </a>
            </c:if>
            <form class="search-box" action="${pageContext.request.contextPath}/house" method="GET">
                <input type="text" class="search-square" name="searchFilter" value="${searchFilter}" />
                <input class="m-btn black" type="submit" value="${search}" />
            </form>
        </div>
        <c:forEach items="${houses}" var="house" varStatus="ic">
            <div class="inline-block-content-house">
                <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/house/${house.id}">
                    <div class="ability-container">
                        <img class = "img-house" src="${pageContext.request.contextPath}/webApi/house/${house.id}" alt="${noImage}">
                        <span>${house.name}</span>
                    </div>
                </a>
            </div>
        </c:forEach>
        <c:if test="${not empty hiddenHouses}">
            <h3>${hidHouses}</h3>
            <c:forEach items="${hiddenHouses}" var="house" varStatus="ic">
                <div class="inline-block-content-house">
                    <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/house/${house.id}">
                        <div class="ability-container">
                            <img class="img-house" src="${pageContext.request.contextPath}/webApi/house/${house.id}" alt="${noImage}">
                            <span>${house.name}</span>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </div>
</jsp:attribute>
</house:header>
