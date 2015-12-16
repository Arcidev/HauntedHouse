<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="home" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/style/background.css" rel="stylesheet">

<home:header>
<jsp:attribute name="body">
    <body background="${pageContext.request.contextPath}/resources/images/home.jpg">"/>
    <div class="animated fadeInUp">
        <p>Haunted House<p>
        <p class = "info">Tu bude nejaky popis</p>
        <p class = "info">A este par teplych slov dame tu</p>
    </div>
    
</jsp:attribute>
</home:header>