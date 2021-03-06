<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="spook" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addSpook" key="spook.addSpook"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="hidSpooks" key="spook.hidSpooks"/>
<fmt:message var="search" key="misc.search"/>

<spook:header>
    <jsp:attribute name="body">
        <div class="jumbotron">
            <div class="manage-buttons-container">
         <c:if test="${isAuthenticated}">        
            <a href="${pageContext.request.contextPath}/spook/new" class="m-btn black"> 
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                ${addSpook}
            </a>
        
         </c:if>
            <form class="search-box" action="${pageContext.request.contextPath}/spook" method="GET">
                <input type="text" class="search-square" name="searchFilter" value="${searchFilter}" />
                <input class="m-btn black" type="submit" value="${search}" />
            </form>
        </div>
        <c:forEach items="${spooks}" var="spook" varStatus="ic">
            <div class="inline-block-content-spook">
                <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/spook/${spook.id}">
                    <div class="ability-container">
                        <img class="img-ability" src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}">
                        <span>${spook.name}</span>
                    </div>
                </a>
            </div>
        </c:forEach>
        <c:if test="${not empty hiddenSpooks}">
            <h3>${hidSpooks}</h3>
            <c:forEach items="${hiddenSpooks}" var="spook" varStatus="ic">
                <div class="inline-block-content-spook">
                    <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/spook/${spook.id}">
                        <div class="ability-container">
                            <img class="img-ability" src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}">
                            <span>${spook.name}</span>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </c:if>
       </div> 
    </jsp:attribute>    
    
</spook:header>