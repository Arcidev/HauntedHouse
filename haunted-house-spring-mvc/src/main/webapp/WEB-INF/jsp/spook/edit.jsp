<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="spook" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="browse" key="misc.browse"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="spookImage" key="spook.image"/>
<fmt:message var="history" key="spook.history"/>
<fmt:message var="hauntsSince" key="spook.hauntsSince"/>
<fmt:message var="hauntsUntil" key="spook.hauntsUntil"/>
<fmt:message var="name" key="misc.name"/>
<fmt:message var="description" key="misc.description"/>
<fmt:message var="create" key="misc.create"/>
<fmt:message var="edit" key="misc.edit"/>
<fmt:message var="fileExceededSize" key="misc.fileExceededSize"/>

<spook:header>
<jsp:attribute name="body">

    <div>
        <form:form enctype="multipart/form-data" action="${pageContext.request.contextPath}/spook/edit?${_csrf.parameterName}=${_csrf.token}" modelAttribute="spookEdit" method='POST'>
            <input type="hidden" name="id" value ="${spookEdit.id}" />
            <h3>${spookImage}</h3>
            <div class="form-group ability-img-block">
                <img id="spookImageId" src="${pageContext.request.contextPath}/webApi/spook/${spookEdit.id}" alt="${noImage}" style="">
                <p id="spookImageErrorId" style="display: none" class="input_error">*${fileExceededSize}</p>
                <p class="m-btn black btn-file button-100">
                    ${browse} <input name="file" id="imageFileId" type="file" accept="image/*" onchange="onImageFileInputChange(this, 'spookImageId', 'spookImageErrorId')">
                </p>
            </div>
            <h3>${spookInfo}</h3>
            <div class="form-group ability-edit-block">
                <span>${name}:</span>
                <input type="text" name="name" value="${spookEdit.name}" />
                <c:if test="${not empty name_error}">
                    <p class="input_error">*${name_error}</p>
                </c:if>
            </div>
             <div class="form-group ability-edit-block">
                <p>${history}:</p>                
                <input type="text" name="history" value="${spookEdit.history}"/>
                <c:if test="${not empty history_error}">
                    <p class="input_error">*${history_error}</p>
                </c:if>
            </div>
            <div class="form-group ability-edit-block">
                <p>${hauntsSince}:</p>                
                <input type="text" name="hauntsSince" value="${spookEdit.hauntsSince}" " placeholder="HH:MM:SS"/>
                <c:if test="${not empty hauntsSince_error}">
                    <p class="input_error">*${hauntsSince_error}</p>
                </c:if>
            </div>
            <div class="form-group ability-edit-block">
                <p>${hauntsUntil}:</p>                
                <input type="text" name="hauntsUntil" value="${spookEdit.hauntsUntil}" placeholder="HH:MM:SS"/>
                <c:if test="${not empty hauntsUntil_error}">
                    <p class="input_error">*${hauntsUntil_error}</p>
                </c:if>
            </div>
            <div class="ability-img-block">
                <c:choose>
                    <c:when test="${spookEdit.id == 0}">
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
</spook:header>
