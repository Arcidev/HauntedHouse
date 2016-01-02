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
<fmt:message var="his" key="history.his"/>
<fmt:message var="spookAbilities" key="spook.spookAbilities"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="makeVisible" key="misc.makeVisible"/>
<fmt:message var="makeInvisible" key="misc.makeInvisible"/>
<fmt:message var="removeAbility" key="spook.removeAbility"/>
<fmt:message var="addAbility" key="ability.addAbility"/>
<fmt:message var="remove" key="spook.remove"/>
<fmt:message var="removeSpook" key="misc.remove"/>

<spook:header>
    <jsp:attribute name="body">
         <div>
            <div class="manage-buttons-container">
                <a href="${pageContext.request.contextPath}/history/all/${spook.id}" class="m-btn black">
                    <span class="glyphicon glyphicon-new-window"></span>
                    ${his}
                </a>
                <c:if test="${userRole == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/spook/edit/${spook.id}" class="m-btn black">
                        <span class="glyphicon glyphicon-edit"></span>
                        ${editSpook}
                    </a>  
                    <c:choose>
                        <c:when test="${spook.visible}">
                            <a href="${pageContext.request.contextPath}/spook/visible/${spook.id}/false" class="m-btn black">
                                <span class="glyphicon glyphicon-cog"></span>
                                ${makeInvisible}
                            </a>
                        </c:when>    
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/spook/visible/${spook.id}/true" class="m-btn black">
                                <span class="glyphicon glyphicon-cog"></span>
                                ${makeVisible}
                            </a>
                        </c:otherwise>
                    </c:choose>
                    <button onclick="showPopup('${pageContext.request.contextPath}/spook/remove/${spook.id}', '${removeSpook}')" class="m-btn black">
                        <span class="glyphicon glyphicon-minus"></span>
                        ${remove}
                    </button>
                </c:if>
            </div>
            <img class="img-ability" src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}">
            <h2>${spook.name}</h2>
            <p><b>${history}: </b><span>${spook.history}</span></p>
            <p><b>${hauntsSince}: </b><span>${spook.hauntsSince}</span></p>
            <p><b>${hauntsUntil}: </b><span>${spook.hauntsUntil}</span></p>  
            <c:if test="${not empty abilities}">
                <h3>${spookAbilities}</h3>
                <c:forEach items="${abilities}" var="ability" varStatus="ic">
                    <div>
                        <c:if test="${userRole == 'ADMIN'}">
                            <button onclick="showPopup('${pageContext.request.contextPath}/spook/removeAbility/${spook.id}/${ability.id}', '${removeAbility}')" class="m-btn">
                                <span class="glyphicon glyphicon-minus"></span>
                            </button>
                        </c:if>
                        <a class="anchor-no-decor" href="${pageContext.request.contextPath}/ability/${ability.id}">
                            <div class="ability-spook">
                                <img src="${pageContext.request.contextPath}/webApi/ability/${ability.id}" alt="${noImage}"/>
                            </div>
                            <strong>${ability.name}</strong>
                        </a>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${userRole == 'ADMIN' && not empty notAssignedAbilities}">
                <h3>${addAbility}</h3>
                <form:form action="${pageContext.request.contextPath}/spook/addAbility">
                    <input type="hidden" name="spookId" value="${spook.id}" />
                    <select class="form-control" name="abilityId">
                        <c:forEach items="${notAssignedAbilities}" var="ability" varStatus="ic">
                            <option value="${ability.id}">
                                ${ability.name}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="submit" class="m-btn black" />
                </form:form>
            </c:if>
        </div>
    </jsp:attribute>   
</spook:header>
