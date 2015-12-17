<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="house" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="browse" key="misc.browse"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="houseImage" key="house.image"/>
<fmt:message var="address" key="house.address"/>
<fmt:message var="history" key="house.history"/>
<fmt:message var="date" key="house.date"/>
<fmt:message var="name" key="misc.name"/>
<fmt:message var="create" key="misc.create"/>
<fmt:message var="edit" key="misc.edit"/>
<fmt:message var="fileExceededSize" key="misc.fileExceededSize"/>

<house:header>
<jsp:attribute name="body">

    <div>
        <form:form name='loginForm' enctype="multipart/form-data" action="${pageContext.request.contextPath}/house/edit?${_csrf.parameterName}=${_csrf.token}" modelAttribute="houseEdit" method='POST'>
            <input type="hidden" name="id" value ="${houseEdit.id}" />
            <h3>${houseImage}</h3>
            <div class="form-group house-img-block">
                <img id="houseImageId" src="${pageContext.request.contextPath}/webApi/house/${houseEdit.id}" alt="${noImage}" style="">
                <p id="abilityImageErrorId" style="display: none" class="input_error">*${fileExceededSize}</p>
                <p class="m-btn black btn-file button-100">
                    ${browse} <input name="file" id="imageFileId" type="file" accept="image/*" onchange="onImageFileInputChange(this, 'abilityImageId', 'abilityImageErrorId')">
                </p>
            </div>
            <h3>${houseInfo}</h3>
            <div class="form-group house-edit-block">
                <p>${name}:</p>
                <input type="text" name="name" value="${houseEdit.name}" />
                <c:if test="${not empty name_error}">
                    <p class="input_error">*${name_error}</p>
                </c:if>
            </div>
            <div class="form-group house-edit-block">
                <p>${address}:</p>
                <input type="text" name="address" value="${houseEdit.address}"/>
                <c:if test="${not empty info_error}">
                    <p class="input_error">*${info_error}</p>
                </c:if>
            </div>
                <div class="form-group house-edit-block">
                <p>${history}:</p>
                <textarea name="history">${houseEdit.history}</textarea>
                <c:if test="${not empty info_error}">
                    <p class="input_error">*${info_error}</p>
                </c:if>
            </div>
            <div class = "form-group house-edit-block">
                <p>${date}:</p>
                <input type="date" value="${houseEdit.hauntedSince}" placeholder="YYYY-MM-DD"/>
            </div>
            <div class="house-img-block">
                <c:choose>
                    <c:when test="${houseEdit.id == 0}">
                        <input class="m-btn black button-100" type="submit" value="${create}" />
                    </c:when>    
                    <c:otherwise>
                        <input class="m-btn black button-100" type="submit" value="${edit}" />
                    </c:otherwise>
                </c:choose>
            </div>
        </form:form>
    </div>
    
</jsp:attribute>
</house:header>