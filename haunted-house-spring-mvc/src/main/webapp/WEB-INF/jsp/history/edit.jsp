<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="history" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="historyInfo" key="history.info"/>
<fmt:message var="date" key="misc.date"/>
<fmt:message var="description" key="misc.description"/>
<fmt:message var="create" key="misc.create"/>
<fmt:message var="edit" key="misc.edit"/>

<history:header>
<jsp:attribute name="body">

    <div>
        <form:form action="${pageContext.request.contextPath}/history/edit" modelAttribute="historyEdit" method='POST'>
            <input type="hidden" name="id" value ="${historyEdit.id}" />
            <input type="hidden" name="spookId" value ="${historyEdit.spookId}" />
            <h3>${historyInfo}</h3>
            <div class="form-group">
                <p>${description}:</p>
                <textarea name="info">${historyEdit.info}</textarea>
                <c:if test="${not empty historyInfo_error}">
                    <p class="input_error">*${historyInfo_error}</p>
                </c:if>
            </div>
            <div class="form-group">
                <p>${date}:</p>
                <input type="text" name="historyDate" placeholder="2000-01-01" value="${historyEdit.historyDate}" />
                <c:if test="${not empty historyDate_error}">
                    <p class="input_error">*${historyDate_error}</p>
                </c:if>
            </div>
            <c:choose>
                <c:when test="${historyEdit.id == 0}">
                    <input class="m-btn black button-100" type="submit" value="${create}" />
                </c:when>    
                <c:otherwise>
                    <input class="m-btn black button-100" type="submit" value="${edit}" />
                </c:otherwise>
            </c:choose>
        </form:form>
    </div>
    
</jsp:attribute>
</history:header>
