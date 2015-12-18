<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="spook" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addSpook" key="spook.addSpook"/>
<fmt:message var="editSpook" key="spook.editSpook"/>
<fmt:message var="hauntsSince" key="spook.hauntsSince"/>
<fmt:message var="hauntsUntil" key="spook.hauntsUntil"/>
<fmt:message var="history" key="spook.history"/>
<fmt:message var="histories" key="history.histories"/>
<fmt:message var="spookAbilities" key="spook.spookAbilities"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="makeVisible" key="misc.makeVisible"/>
<fmt:message var="makeInvisible" key="misc.makeInvisible"/>

<spook:header>
    <jsp:attribute name="body">
         <div>
            <c:if test="${userRole == 'ADMIN'}">
                <div class="manage-buttons-container">
                <a href="${pageContext.request.contextPath}/spook/edit/${spook.id}" class="m-btn black">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    ${editSpook}
                </a>  
                <c:choose>
                    <c:when test="${spook.visible}">
                        <a href="${pageContext.request.contextPath}/spook/visible/${spook.id}/false" class="m-btn black">
                            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                            ${makeInvisible}
                        </a>
                    </c:when>    
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/spook/visible/${spook.id}/true" class="m-btn black">
                            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                            ${makeVisible}
                        </a>
                    </c:otherwise>
                </c:choose>
                </div>
            </c:if>
        <img class="img-ability" src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}">
        <h2>${spook.name}</h2>
        <p><b>History: </b><span>${spook.history}</span></p>
        <p><b>HauntsSince: </b><span>${spook.hauntsSince}</span></p>
        <p><b>HauntsUntil: </b><span>${spook.hauntsUntil}</span></p>  
         <c:if test="${not empty abilities}">
            <h3>${spookAbilities}</h3>
            <c:forEach items="${abilities}" var="ability" varStatus="ic">
                <div>
                    <a class="anchor-no-decor" href="${pageContext.request.contextPath}/ability/${ability.id}">
                        <div class="ability-spook">
                            <img src="${pageContext.request.contextPath}/webApi/ability/${ability.id}" alt="${noImage}"/>
                        </div>
                        <strong>${ability.name}</strong>
                    </a>
                </div>
            </c:forEach>
         </c:if>
        <div>
            <div class="manage-buttons-container">
                <a href="${pageContext.request.contextPath}/history/all/${spook.id}" class="m-btn black">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    ${histories}
                </a>        
            </div>
        </div>       
        
    </jsp:attribute>   
    
</spook:header>
