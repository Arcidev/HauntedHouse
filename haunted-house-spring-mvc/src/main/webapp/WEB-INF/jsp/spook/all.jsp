<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="spook" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addSpook" key="spook.addSpook"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="hidSpooks" key="spook.hidSpooks"/>

<spook:header>
    <jsp:attribute name="body">
        <div class="jumbotron">
         <c:if test="${isAuthenticated}">
        <div class="manage-buttons-container">
            <a href="${pageContext.request.contextPath}/spook/new" class="m-btn black"> 
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                ${addSpook}
            </a>
        </div>
         </c:if>
        <c:forEach items="${spooks}" var="spook" varStatus="ic">
            <div class="inline-block-content-spook"><!-- bootstrap responsive grid -->
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
                <div class="inline-block-content-house"><!-- bootstrap responsive grid -->
                    <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/spook/${spook.id}">
                        <div class="ability-container">
                            <img class="img-house" src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}">
                            <span>${spook.name}</span>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </c:if>
       </div> 
    </jsp:attribute>    
    
</spook:header>