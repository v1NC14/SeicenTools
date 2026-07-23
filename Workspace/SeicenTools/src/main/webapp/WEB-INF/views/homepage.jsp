<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="content">

    <div class="list-categories my-1 mx-2">
        <h4>CATEGORIE</h4><br>
        <c:forEach var="cat" items="${categorie}">
            <a href="${pageContext.request.contextPath}/catalogo?filtro=${cat}" class="list-categories-item list-categories-item-action ">${cat}</a>
        </c:forEach>
    </div>

    <c:if test="${not empty bannerProducts}">
        <div id="Banner" class="Banner my-3 mx-1" data-bs-ride="carousel">
            <div class="banner-inner">
                <c:forEach var="banner" items="${bannerProducts}">
                    <div class="banner-item m-2">
                        <img src="${pageContext.request.contextPath}/${banner.imgPath}" class="d-block w-100" alt="..."><!--${prod.imgPath}-->
                    </div>
                </c:forEach>
            </div>
        </div><!-- si deve vedere per le immagini-->
    </c:if>

    <div align="center" class="productCard mx-2 my-1">
        <c:forEach var="prod" items = "${cardProducts}">
            <div class="card mx-1 my-1" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}/products/${prod.imgPath}" class="card-img-top img-thumbnail-lg" alt="${prod.nome}">
                <div class="card-body">
                    <h5 class="grassetto">${prod.nome}</h5>
                    <p class="card-text">${prod.descrizione}.</p>
                    <a href = "${pageContext.request.contextPath}/dettagli-prod?idPrd=${prod.id}" class="btn btn-primary">visualizza</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>