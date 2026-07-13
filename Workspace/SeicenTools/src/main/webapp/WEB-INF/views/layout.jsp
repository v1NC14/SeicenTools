<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!Doctype html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>SeicenTools</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
        <nav class="navbar nav-color">
            <div class="navbar-brand" id="brand">
                <img src="${pageContext.request.contextPath}/imgs/SeicenToolsLogo2.png" alt="logo" id="logo" class="mx-3 my-1">
                SeicenTools
            </div>

            <c:if test="${viewPath != 'login.jsp'}">
                <div class="menu-links">

                    <c:if test="${viewPath != 'homepage.jsp' && viewPath != 'registraUtente.jsp'}">
                        <a href="${pageContext.request.contextPath}/homepage?action=homepage.jsp" class="btn-create"><!--nell'href ho scritto roba a caso per quando farai il backend-->
                            <i class="bi bi-house-door mx-2" style="color: #04042a;"></i>
                        </a>
                    </c:if>

                    <c:if test="${viewPath != 'profilo.jsp' && viewPath != 'registraUtente.jsp'}">
                        <a href="${pageContext.request.contextPath}/profilo?action=profilo.jsp">
                            <i class="bi bi-person-square mx-2" style="color: #04042a;"></i>
                        </a>
                    </c:if>
                    <c:if test="${viewPath != 'aggiungiProdottoAdmin.jsp' && viewPath != 'registraUtente.jsp'}">
                        <a href="${pageContext.request.contextPath}/crea-prod?action=aggiungiProdottoAdmin.jsp">
                            <i class="bi bi-plus-square-fill mx-2" style="color: #04042a;"></i>
                        </a>
                    </c:if>
                </div>
            </c:if>
        </nav>

        <c:choose>
            <c:when test="${not empty viewPath}">
                <jsp:include page="${viewPath}" />
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning">Nessun contenuto da visualizzare.</div> <!--Error 404-->
            </c:otherwise>
        </c:choose>
        <div id="footerL">
            <p>&copy; 2026 SeicenTools</p>
        </div>
    </body>
</html>