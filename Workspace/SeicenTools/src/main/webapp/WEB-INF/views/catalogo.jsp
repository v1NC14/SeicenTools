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

    <div class="content">
        <div align="left" class="categorie" >
            <h2>CATEGORIE</h2><br>
            <c:forEach var="categ" items="${categorie}">
                <p>-${categ.categoria}</p><br>
            </c:forEach>
        </div>
        <div align="right" class="main-content">
            <div class="searchBar">

                <div class="filtro">
                  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" >
                    Filtra per
                  </button>
                  <ul class="dropdown-menu">
                        <c:forEach var="opzione" items="">
                            <li><a class="filtro-item" href="">Action</a></li>
                        </c:forEach>
                  </ul>
                </div>
            </div><!-- da completare, capire per cosa filtrare-->

            <div class="catalogo">
                <c:forEach var="list" items="${listaProdotti">
                    <div class="card" style="width: 18rem;">
                        <img src="${pageContext.request.contextPath}/imgs/${list.imgPath}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${list.nome}</h5>
                                <p class="grassetto">${list.prezzo}.</p>
                                <a href="dettagli_prod? id=${prod.id}" class="btn btn-primary">visualizza</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>


    <div id="footerL">
        <p>&copy; 2026 SeicenTools</p>
    </div>
    </body>
</html>