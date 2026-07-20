<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="content">
    <div align="left" class="categorie" >
        <h2>CATEGORIE</h2><br>
        <c:forEach var="categ" items="${categorie}">
            <p>-${categ}</p><br>
        </c:forEach>
    </div>
    <div align="right" class="main-content">
        <div class="searchBar">
            <div class="filtro">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" >Filtra per</button>
                <ul class="dropdown-menu">
                    <c:forEach var="opzione" items="categorie">
                        <li><a class="filtro-item" href="${pageContext.request.contextPath}/catalogo?categoria=${opzione}"></a></li>
                    </c:forEach>
                </ul>
            </div>
        </div><!-- da completare, capire per cosa filtrare-->

        <div class="catalogo">
            <c:forEach var="list" items="${listaProdotti}">
                <div class="card" style="width: 18rem;">
                    <img src="${pageContext.request.contextPath}/imgs/${list.imgPath}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${list.nome}</h5>
                        <p class="grassetto">${list.prezzo}.</p>
                            <a href="${pageContext.request.contextPath}/dettagli_prod?id=${prod.id}" class="btn btn-primary">visualizza</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>