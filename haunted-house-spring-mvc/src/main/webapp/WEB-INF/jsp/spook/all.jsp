<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="spook" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addSpook" key="spook.addSpook"/>
<fmt:message var="noImage" key="misc.noImage"/>

<spook:header>
    <jsp:attribute name="body">
        <div class="jumbotron">
        <div class="manage-buttons-container">
            <a href="/spook/new" class="m-btn black">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                ${addSpook}
            </a>
        </div>
        <c:forEach items="${spooks}" var="spook" varStatus="ic">
            <div class="inline-block-content-spook"><!-- bootstrap responsive grid -->
                <a class="anchor-no-decor ability-image" href="${pageContext.request.contextPath}/spook/${spook.id}">
                    <div class="ability-container">
                        <img src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}">
                        <span>${spook.name}</span>
                    </div>
                </a>
            </div>
        </c:forEach>
       </div> 
    </jsp:attribute>
    
    
</spook:header>