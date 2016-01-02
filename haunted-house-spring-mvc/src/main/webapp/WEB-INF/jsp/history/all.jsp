<%-- 
    Document   : all
    Created on : 17.12.2015, 23:40:13
    Author     : Luka
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="history" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<fmt:message var="addHistory" key="history.addHistory"/>
<fmt:message var="editHistory" key="history.editHistory"/>
<fmt:message var="info" key="history.info"/>
<fmt:message var="date" key="misc.date"/>
<fmt:message var="removeHistory" key="misc.remove"/>

<history:header>
<jsp:attribute name="body">
    
    <c:if test="${isAuthenticated}">
        <div class="manage-buttons-container">
            <a href="${pageContext.request.contextPath}/history/new/${spookId}" class="m-btn black">
                <span class="glyphicon glyphicon-plus"></span>
                ${addHistory}
            </a>
        </div>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <c:if test="${userRole == 'ADMIN'}">
                <th class="removeRow"></th>
            </c:if>
            <th>Id</th>
            <th>${info}</th>
            <th>${date}</th>
            <c:if test="${userRole == 'ADMIN'}">
                <th></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${histories}" var="history" varStatus="ic">
            <tr>
                <c:if test="${userRole == 'ADMIN'}">
                    <td>
                        <button onclick="showPopup('${pageContext.request.contextPath}/history/remove/${spookId}/${history.id}', '${removeHistory}')" class="m-btn">
                            <span class="glyphicon glyphicon-minus"></span>
                        </button>
                    </td>
                </c:if>
                <td>${history.id}</td>
                <td><c:out value="${history.info}"/></td>
                <td><c:out value="${history.historyDate}"/></td>
                <c:if test="${userRole == 'ADMIN'}">
                    <td>
                        <a href="${pageContext.request.contextPath}/history/edit/${history.id}" class="m-btn black">
                            <span class="glyphicon glyphicon-edit"></span>
                            ${editHistory}
                        </a>
                    </td>
                </c:if>
            </tr>
            
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</history:header>