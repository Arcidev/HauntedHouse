<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-fixed-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Project name</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="javascript:void(0)">Spooks</a></li>
                    <li><a href="javascript:void(0)">Abilities</a></li>
                    <li><a href="javascript:void(0)">Houses</a></li>
                </ul>
            </div>
          </div>
        </nav>

        <div class="container">

            <c:if test="${not empty title}">
                <div class="page-header">
                    <h1><c:out value="${title}"/></h1>
                </div>
            </c:if>

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

            <div>
                <jsp:invoke fragment="body"/>
            </div>

            <!-- footer -->
            <footer class="footer">
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Masaryk University</p>
            </footer>
        </div>
    </body>
</html>