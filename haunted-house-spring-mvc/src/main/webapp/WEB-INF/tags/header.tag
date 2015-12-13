<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <link href="<c:url value="/resources/style/m-buttons.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/style/default.css" />" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <jsp:invoke fragment="head"/>
    </head>
    
    <fmt:message var="home" key="navigation.home"/>
    <fmt:message var="spooks" key="navigation.spooks"/>
    <fmt:message var="abilities" key="navigation.abilities"/>
    <fmt:message var="houses" key="navigation.houses"/>
    <fmt:message var="signUp" key="navigation.signUp"/>
    <fmt:message var="login" key="navigation.login"/>
    
    <body>
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="${activePage == "Home" ? "active": ""}"><a href="home">${home}</a></li>
                    <li class="${activePage == "Spooks" ? "active": ""}"><a href="javascript:void(0)">${spooks}</a></li>
                    <li class="${activePage == "Abilities" ? "active": ""}"><a href="ability">${abilities}</a></li>
                    <li class="${activePage == "Houses" ? "active": ""}"><a href="javascript:void(0)">${houses}</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="${activePage == "SignUp" ? "active": ""}"><a href="register"><span class="glyphicon glyphicon-user"></span> ${signUp}</a></li>
                    <li class="${activePage == "Login" ? "active": ""}"><a href="login"><span class="glyphicon glyphicon-log-in"></span> ${login}</a></li>
                </ul>
            </div>
          </div>
        </nav>

        <div class="container">
            <!-- authenticated user info -->
            <c:if test="${not empty authenticatedUser}">
            <div class="row">
                <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
                <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <c:out value="${authenticatedUser.givenName} ${authenticatedUser.surname}"/>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>

            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <c:out value="${alert_danger}"/></div>
            </c:if>
            <c:if test="${not empty alert_info}">
                <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
            </c:if>

            <jsp:invoke fragment="body"/>
        </div>
        <div class="container">
            <!-- footer -->
            <footer class="footer">
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Masaryk University</p>
            </footer>
        </div>
    </body>
</html>