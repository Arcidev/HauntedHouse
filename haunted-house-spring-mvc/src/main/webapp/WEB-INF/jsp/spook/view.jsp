<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="spook" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addSpook" key="spook.addSpook"/>
<fmt:message var="editSpook" key="spook.editSpook"/>
<fmt:message var="spookAbilities" key="spook.spookAbilities"/>
<fmt:message var="noImage" key="misc.noImage"/>

<spook:header>
    <jsp:attribute name="body">
         <div>
            <div class="manage-buttons-container">
            <a href="/HauntedHouse/spook/edit/${spook.id}" class="m-btn black">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                ${editSpook}
            </a>        
        </div>
        <img class="img-ability" src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}">
        <h2>${spook.name}</h2>
        <p><b>History: </b><span>${spook.history}</span></p>
        <p><b>HauntedSince: </b><span>${spook.hauntsSince.toString()}</span></p>
        <p><b>HauntedUntil: </b><span>${spook.hauntsUntil.toString()}</span></p>  
         <c:if test="${not empty abilities}">
            <h3>those abilities are used:</h3>
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
    </div>       
        
    </jsp:attribute>   
    
</spook:header>


