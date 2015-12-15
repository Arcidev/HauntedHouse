<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="house" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="addHouse" key="house.addHouse"/>
<fmt:message var="noImage" key="house.noImage"/>

<house:header>
<jsp:attribute name="body">

    <div>
        <img src="${pageContext.request.contextPath}/webApi/house/${house.id}" alt="${noImage}">
        <h2>${house.name}</h2>
        <span>${house.info}</span>        
    </div>
    
</jsp:attribute>
</house:header>
