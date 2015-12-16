<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ability" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addAbility" key="ability.addAbility"/>
<fmt:message var="editAbility" key="ability.editAbility"/>
<fmt:message var="abilitySpooks" key="ability.abilitySpooks"/>
<fmt:message var="noImage" key="misc.noImage"/>

<ability:header>
<jsp:attribute name="body">

    <div>
        <div class="manage-buttons-container">
            <a href="/HauntedHouse/ability/edit/${ability.id}" class="m-btn black">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                ${editAbility}
            </a>
        </div>
        <img class="img-ability" src="${pageContext.request.contextPath}/webApi/ability/${ability.id}" alt="${noImage}">
        <h2>${ability.name}</h2>
        <span>${ability.info}</span>
        <c:if test="${not empty spooks}">
            <h3>${abilitySpooks}:</h3>
            <c:forEach items="${spooks}" var="spook" varStatus="ic">
                <div>
                    <a class="anchor-no-decor" href="${pageContext.request.contextPath}/spook/${spook.id}">
                        <div class="ability-spook">
                            <img src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}"/>
                        </div>
                        <strong>${spook.name}</strong>
                    </a>
                </div>
            </c:forEach>
        </c:if>
        
    </div>
    
</jsp:attribute>
</ability:header>
