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
<fmt:message var="makeVisible" key="misc.makeVisible"/>
<fmt:message var="makeInvisible" key="misc.makeInvisible"/>
<fmt:message var="removeAbility" key="ability.removeSpook"/>

<ability:header>
<jsp:attribute name="body">

    <div>
        <c:if test="${userRole == 'ADMIN'}">
            <div class="manage-buttons-container">
                <a href="/HauntedHouse/ability/edit/${ability.id}" class="m-btn black">
                    <span class="glyphicon glyphicon-plus"></span>
                    ${editAbility}
                </a>
                <c:choose>
                    <c:when test="${ability.visible}">
                        <a href="/HauntedHouse/ability/visible/${ability.id}/false" class="m-btn black">
                            <span class="glyphicon glyphicon-cog"></span>
                            ${makeInvisible}
                        </a>
                    </c:when>    
                    <c:otherwise>
                        <a href="/HauntedHouse/ability/visible/${ability.id}/true" class="m-btn black">
                            <span class="glyphicon glyphicon-cog"></span>
                            ${makeVisible}
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
        <img class="img-ability" src="${pageContext.request.contextPath}/webApi/ability/${ability.id}" alt="${noImage}">
        <h2>${ability.name}</h2>
        <span>${ability.info}</span>
        <c:if test="${not empty spooks}">
            <h3>${abilitySpooks}:</h3>
            <c:forEach items="${spooks}" var="spook" varStatus="ic">
                <div>
                    <c:if test="${userRole == 'ADMIN'}">
                        <button onclick="showPopup('${pageContext.request.contextPath}/ability/removeSpook/${ability.id}/${spook.id}', '${removeAbility}')" class="m-btn">
                            <span class="glyphicon glyphicon-minus"></span>
                        </button>
                    </c:if>
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
