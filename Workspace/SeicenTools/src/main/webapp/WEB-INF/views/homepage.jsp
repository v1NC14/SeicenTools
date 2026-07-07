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

        <div id="Banner" class="Banner" data-bs-ride="carousel">
            <div class="banner-inner">
                <div class="banner-item">
                    <img src="..." class="d-block w-100" alt="...">
                </div>
                <div class="banner-item">
                    <img src="..." class="d-block w-100" alt="...">
                </div>
                <div class="banner-item">
                    <img src="..." class="d-block w-100" alt="...">
                </div>
            </div>
        </div><!-- si deve vedere per le immagini-->

        <div class="content">

            <h4>CATEGORIE</h4><br>
            <div  align="left" class="list-categories">
               <c:forEach var="categories" items="${prodotto}">
                    <a href="dettagli-prod? categoria=${categories.categoria}" class="list-categories-item list-categories-item-action ">${categories.categoria}</a>
               </c:forEach>
            </div>

            <div align="right" class="productCard">
                <c:forEach var="prod" items="${cardProducts}"
                    <div class="card" style="width: 18rem;">
                      <img src="${pageContext.request.contextPath}/imgs/${prod.imgPath}" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">${prod.nome}</h5>
                        <p class="card-text">${prod.descrizione}.</p>
                        <a href="dettagli_prod? id=${prod.id}" class="btn btn-primary">visualizza</a>
                      </div>
                    </div>
                </c:forEach>
            </div>
        </div>



        <div id="footerL">
            <p>&copy; 2026 SeicenTools</p>
        </div>



    </body>
</html>