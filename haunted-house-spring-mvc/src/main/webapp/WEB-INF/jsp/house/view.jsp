<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="house" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addHouse" key="house.addHouse"/>
<fmt:message var="editHouse" key="house.editHouse"/>
<fmt:message var="deleteHouse" key="house.deleteHouse"/>
<fmt:message var="houseSpooks" key="house.houseSpooks"/>
<fmt:message var="address" key="house.address"/>
<fmt:message var="history" key="house.history"/>
<fmt:message var="date" key="house.date"/>
<fmt:message var="removeSpook" key="house.removeSpook"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="makeVisible" key="misc.makeVisible"/>
<fmt:message var="makeInvisible" key="misc.makeInvisible"/>
<fmt:message var="addSpook" key="spook.addSpook"/>
<fmt:message var="remove" key="house.remove"/>
<fmt:message var="removeHouse" key="misc.remove"/>

<house:header>
<jsp:attribute name="body">

    <div>
        <c:if test="${userRole == 'ADMIN'}">
        <div class="manage-buttons-container">
            <a href="${pageContext.request.contextPath}/house/edit/${house.id}" class="m-btn black">
                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                ${editHouse}
            </a>
            <c:choose>
                <c:when test="${house.visible}">
                    <a href="${pageContext.request.contextPath}/house/visible/${house.id}/false" class="m-btn black">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                        ${makeInvisible}
                    </a>
                </c:when>    
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/house/visible/${house.id}/true" class="m-btn black">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                        ${makeVisible}
                    </a>
                </c:otherwise>
            </c:choose>
            <button onclick="showPopup('${pageContext.request.contextPath}/house/remove/${house.id}', '${removeHouse}')" class="m-btn black">
                <span class="glyphicon glyphicon-minus"></span>
                ${remove}
            </button>
        </div>
        </c:if>
        <img class = "img-house" src="${pageContext.request.contextPath}/webApi/house/${house.id}" alt="${noImage}">
        <h2>${house.name}</h2>
        <p><b>${address}: </b><span>${house.address}</span></p>
        <p><b>${history}: </b><span>${house.history}</span></p>
        <p><b>${date}: </b><span>${house.hauntedSince}</span></p>
        <c:if test="${not empty spooks}">
            <h3>${houseSpooks}</h3>
            <c:forEach items="${spooks}" var="spook" varStatus="ic">
                <div>
                    <c:if test="${userRole == 'ADMIN'}">
                        <button onclick="showPopup('${pageContext.request.contextPath}/house/removeSpook/${house.id}/${spook.id}', '${removeSpook}')" class="m-btn">
                           <span class="glyphicon glyphicon-minus"></span>
                       </button>
                    </c:if>
                    <a class="anchor-no-decor" href="${pageContext.request.contextPath}/spook/${spook.id}">
                        <div class="house-spook">
                            <img src="${pageContext.request.contextPath}/webApi/spook/${spook.id}" alt="${noImage}"/>
                        </div>
                        <strong>${spook.name}</strong>
                    </a>
                </div>
            </c:forEach>
        </c:if>
            <c:if test="${userRole == 'ADMIN' && not empty notAssignedSpooks}">
            <h3>${addSpook}</h3>
            <form:form action="${pageContext.request.contextPath}/house/addSpook">
                <input type="hidden" name="houseId" value="${house.id}" />
                <select class="form-control" name="spookId">
                    <c:forEach items="${notAssignedSpooks}" var="spook" varStatus="ic">
                        <option value="${spook.id}">
                            ${spook.name}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" class="m-btn black" />
            </form:form>
        </c:if>
    </div>
</jsp:attribute>
</house:header>
