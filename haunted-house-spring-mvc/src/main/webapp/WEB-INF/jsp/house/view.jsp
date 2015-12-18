<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="house" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addHouse" key="house.addHouse"/>
<fmt:message var="editHouse" key="house.editHouse"/>
<fmt:message var="address" key="house.address"/>
<fmt:message var="history" key="house.history"/>
<fmt:message var="date" key="house.date"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="makeVisible" key="misc.makeVisible"/>
<fmt:message var="makeInvisible" key="misc.makeInvisible"/>
<fmt:message var="remove" key="misc.remove"/>

<house:header>
<jsp:attribute name="body">

    <div>
        <c:if test="${userRole == 'ADMIN'}">
        <div class="manage-buttons-container">
            <a href="/HauntedHouse/house/edit/${house.id}" class="m-btn black">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                ${editHouse}
            </a>
            <c:choose>
                    <c:when test="${house.visible}">
                        <a href="/HauntedHouse/house/visible/${house.id}/false" class="m-btn black">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            ${makeInvisible}
                        </a>
                    </c:when>    
                    <c:otherwise>
                        <a href="/HauntedHouse/house/visible/${house.id}/true" class="m-btn black">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            ${makeVisible}
                        </a>
                    </c:otherwise>
                </c:choose>
        </div>
        </c:if>
        <img class = "img-house" src="${pageContext.request.contextPath}/webApi/house/${house.id}" alt="${noImage}">
        <h2>${house.name}</h2>
        <p><b>${address} : </b><span>${house.address}</span></p>
        <p><b>${history} : </b><span>${house.history}</span></p>
        <p><b>${date} : </b><span>${house.hauntedSince}</span></p>
        <c:if test="${not empty spooks}">
            <h3>Spooks which haunt in this house:</h3>
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
</house:header>
