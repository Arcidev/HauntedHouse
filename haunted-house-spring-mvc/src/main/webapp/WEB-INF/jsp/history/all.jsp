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
<fmt:message var="addHistory" key="history.addHistory"/>
<fmt:message var="hist" key="history.histories"/>

<history:header>
<jsp:attribute name="body">

            
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>info</th>
            <th>datum</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${histories}" var="history" varStatus="ic">
            <tr>
                <td>${history.id}</td>
                <td><c:out value="${history.info}"/></td>
                <td><c:out value="${history.historyDate}"/></td>
                <td>
                <div class="manage-buttons-container">
                    <a href="${pageContext.request.contextPath}/history/edit/${history.id}" class="m-btn black">
                        <span class="glyphicon" aria-hidden="true"></span>
                        ${editHistory}
                    </a>
                </div>
                </td>
            </tr>
            
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</history:header>