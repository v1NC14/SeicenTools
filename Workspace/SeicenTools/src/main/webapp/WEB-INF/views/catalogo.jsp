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
        <div class="catalogo">
            <c:forEach var="prod" items="${listaProdotti}">
                <div class="card" style="width: 18rem;">
                    <img src="${pageContext.request.contextPath}/products/${prod.imgPath}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${prod.nome}</h5>
                        <p class="grassetto">${prod.prezzo}.</p>
                            <a href="${pageContext.request.contextPath}/dettagli-prod?idPrd=${prod.id}" class="btn btn-primary">visualizza</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>