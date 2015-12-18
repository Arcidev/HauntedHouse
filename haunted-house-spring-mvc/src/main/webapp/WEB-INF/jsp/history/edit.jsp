<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="history" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="browse" key="misc.browse"/>
<fmt:message var="noImage" key="misc.noImage"/>
<fmt:message var="historyInfo" key="history.info"/>
<fmt:message var="name" key="misc.name"/>
<fmt:message var="description" key="misc.description"/>
<fmt:message var="create" key="misc.create"/>
<fmt:message var="edit" key="misc.edit"/>
<fmt:message var="fileExceededSize" key="misc.fileExceededSize"/>

<history:header>
<jsp:attribute name="body">

    <div>
        <form:form enctype="multipart/form-data" action="${pageContext.request.contextPath}/history/edit?${_csrf.parameterName}=${_csrf.token}" modelAttribute="historyEdit" method='POST'>
            <input type="hidden" name="id" value ="${historyEdit.id}" />
            <h3>${historyInfo}</h3>
            <div class="form-group history-edit-block">
                <p>${description}:</p>
                <textarea name="info">${historyEdit.info}</textarea>
                <c:if test="${not empty info_error}">
                    <p class="input_error">*${info_error}</p>
                </c:if>
            </div>
        </form:form>
    </div>
    
</jsp:attribute>
</history:header>
