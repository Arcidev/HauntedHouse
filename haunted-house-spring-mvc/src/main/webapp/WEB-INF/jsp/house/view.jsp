<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="house" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addHouse" key="house.addHouse"/>
<fmt:message var="noImage" key="misc.noImage"/>

<house:header>
<jsp:attribute name="body">
    <div>
        <img src="${pageContext.request.contextPath}/webApi/house/${house.id}" alt="${noImage}">
        <h2>${house.name}</h2>
        <p><b>Adress: </b><span>${house.address}</span></p>
        <p><b>History: </b><span>${house.history}</span></p>
        <p><b>HauntedSince: </b><span>${house.hauntedSince.toString()}</span></p>
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