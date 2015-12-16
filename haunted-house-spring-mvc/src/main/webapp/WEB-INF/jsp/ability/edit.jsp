<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ability" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="browse" key="misc.browse"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="abilityImage" key="ability.image"/>
<fmt:message var="abilityInfo" key="ability.info"/>
<fmt:message var="name" key="misc.name"/>
<fmt:message var="description" key="misc.description"/>
<fmt:message var="create" key="misc.create"/>
<fmt:message var="edit" key="misc.edit"/>

<script type="text/javascript">
    function onFileInputChange(input) {
        if (input.files && input.files[0]) {
            document.getElementById('abilityImageId').src = URL.createObjectURL(input.files[0]);
        }
    }
</script>

<ability:header>
<jsp:attribute name="body">

    <div>
        <form:form name='loginForm' enctype="multipart/form-data" action="${pageContext.request.contextPath}/ability/editAbility" modelAttribute="abilityEdit" method='POST'>
            <input type="hidden" name="id" value ="${abilityEdit.id}" />
            <h3>${abilityImage}</h3>
            <div class="form-group ability-img-block">
                <img id="abilityImageId" src="${pageContext.request.contextPath}/webApi/ability/${abilityEdit.id}" alt="${noImage}" style="">
                <p class="m-btn black btn-file button-100">
                    ${browse} <input name="file" id="imageFileId" type="file" accept="image/*" onchange="onFileInputChange(this)">
                </p>
            </div>
            <h3>${abilityInfo}</h3>
            <div class="form-group ability-edit-block">
                <span>${name}:</span>
                <input type="text" name="name" value="${abilityEdit.name}" />
            </div>
            <div class="form-group ability-edit-block">
                <p>${description}:</p>
                <textarea name="info">${abilityEdit.info}</textarea>
            </div>
            <div class="ability-img-block">
                <c:choose>
                    <c:when test="${abilityEdit.id == 0}">
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
</ability:header>
