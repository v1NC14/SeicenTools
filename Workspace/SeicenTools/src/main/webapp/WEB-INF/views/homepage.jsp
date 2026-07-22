<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div id="Banner" class="Banner" data-bs-ride="carousel">
    <div class="banner-inner">
        <div class="banner-item">
            <img src="${pageContext.request.contextPath}/imgs/products/..." class="d-block w-100" alt="..."><!--${prod.imgPath}-->
        </div>
        <div class="banner-item">
            <img src="${pageContext.request.contextPath}/imgs/products/..."" class="d-block w-100" alt="...">
        </div>
        <div class="banner-item">
            <img src="${pageContext.request.contextPath}/imgs/products/..."" class="d-block w-100" alt="...">
        </div>
    </div>
</div><!-- si deve vedere per le immagini-->

<div class="content">
    <h4>CATEGORIE</h4><br>
    <div  align="left" class="list-categories">
        <c:forEach var="cat" items="${categorie}">
            <a href="${pageContext.request.contextPath}/catalogo?filtro=${cat}" class="list-categories-item list-categories-item-action ">${cat}</a>
        </c:forEach>
    </div>

    <div align="center" class="productCard">
        <c:forEach var="prod" items = "${cardProducts}">
            <div class="card" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}/products/${prod.imgPath}" class="card-img-top img-thumbnail-lg" alt="${prod.nome}">
                <div class="card-body">
                    <h5 class="card-title">${prod.nome}</h5>
                    <p class="card-text">${prod.descrizione}.</p>
                    <a href = "${pageContext.request.contextPath}/dettagli-prod?idPrd=${prod.id}" class="btn btn-primary">visualizza</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>