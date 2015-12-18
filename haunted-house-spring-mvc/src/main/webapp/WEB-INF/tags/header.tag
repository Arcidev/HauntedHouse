<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <script type="text/javascript">
            function onImageFileInputChange(inputFile, imgId, errorId) {
                if (inputFile.files && inputFile.files[0]) {
                    if (inputFile.files[0].size > 500 * 1024) {
                        document.getElementById(imgId).src = '${pageContext.request.contextPath}/resources/images/error.png';
                        document.getElementById(errorId).style.display = 'block';
                        inputFile.value = '';
                    } else {
                        document.getElementById(errorId).style.display = 'none';
                        document.getElementById(imgId).src = URL.createObjectURL(inputFile.files[0]);
                    }                  
                }
            }
            
            function showPopup(href, message) {
                document.getElementById('popupAnchorId').href = href;
                document.getElementById('popupParagraphId').innerHTML = message;
                document.getElementById('popupDivId').style.display = 'block';
            }
            
            function hidePopup() {
                document.getElementById('popupDivId').style.display = 'none';
            }
        </script>
        <jsp:invoke fragment="head"/>
    </head>
    
    <fmt:message var="home" key="navigation.home"/>
    <fmt:message var="spooks" key="navigation.spooks"/>
    <fmt:message var="abilities" key="navigation.abilities"/>
    <fmt:message var="houses" key="navigation.houses"/>
    <fmt:message var="signUp" key="navigation.signUp"/>
    <fmt:message var="login" key="navigation.login"/>
    <fmt:message var="logout" key="navigation.logout"/>
    <fmt:message var="yes" key="popup.yes"/>
    <fmt:message var="no" key="popup.no"/>
    
    <body>
        <div id="popupDivId" style="display: none">
            <div class="white-content">
                <div class="form-group">
                    <p id="popupParagraphId"></p>
                    <a id="popupAnchorId" class="m-btn black button-100 popup-button-left">
                        ${yes}
                    </a>
                    <button onclick="hidePopup()" class="m-btn button-100 popup-button-right">
                        ${no}
                    </button>
                </div>
            </div>
            <div id="popupOverlayId" class="black-overlay"></div>
        </div>
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
                    <li class="${activePage == "Home" ? "active": ""}"><a href="${pageContext.request.contextPath}/home">${home}</a></li>
                    <li class="${activePage == "Spooks" ? "active": ""}"><a href="${pageContext.request.contextPath}/spook">${spooks}</a></li>
                    <li class="${activePage == "Abilities" ? "active": ""}"><a href="${pageContext.request.contextPath}/ability">${abilities}</a></li>
                    <li class="${activePage == "Houses" ? "active": ""}"><a href="${pageContext.request.contextPath}/house">${houses}</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${isAuthenticated}">
                            <li>
                                <form:form action="${pageContext.request.contextPath}/logout" method="post" id="logoutForm">
                                    <button type="submit" class="navigation-button">
                                        <span class="glyphicon glyphicon-log-out"></span> ${logout}
                                    </button>
                                </form:form>
                            </li>
                        </c:when>    
                        <c:otherwise>
                            <li class="${activePage == "SignUp" ? "active": ""}"><a href="${pageContext.request.contextPath}/register"><span class="glyphicon glyphicon-user"></span> ${signUp}</a></li>
                            <li class="${activePage == "Login" ? "active": ""}"><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> ${login}</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
          </div>
        </nav>

        <div class="container">
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