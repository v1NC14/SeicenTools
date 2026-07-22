<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class = "product_img">
<img src="${pageContext.request.contextPath}/${prodotto.imgPath}" alt="foto prodotto" id="prodImg"> <!--qui devo capire come poter caricare le immagini, servirà tempo, è una delle parti più difficili del progetto-->
<c:if test="${sessionScope.utente.ruolo == 'ADMIN'}">
    <a href="mod-prod">
        <button type="button" class="btn btn-danger mx-1">Cambia immagine prodotto</button>
    </a>
</c:if>
</div>

<div class = "product_data">
    <div class="mx-2 my-2">
        <c:if test="${error != null}">
            <div class="error-banner">
                <p>${error}</p>
            </div>
        </c:if>
        <div class="my-1">
            <h1 class="grassetto">${sessionScope.prodotto.nome}</h1>
            <h3>Disponibilità: ${sessionScope.prodotto.disponibilita}</h3>
        </div>

        <div>
            <h2><strong>€ ${sessionScope.prodotto.prezzo}</strong></h2>
            <h2>Descrizione:</h2>
            <div>${sessionScope.prodotto.descrizione}</div>
        </div>

        <div class="my-2">

            <a href="${pageContext.request.contextPath}/add-carrello?id=${sessionScope.prodotto.id}&qta=1">
                <button type="button" class="btn btn-warning mx-1">Aggiungi al carrello<i class="bi bi-patch-plus-fill"></i></button>
            </a>
            <c:if test="${sessionScope.utente.ruolo == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/mod-prod?id=${sessionScope.prodotto.id}"> <!--da fare-->
                    <button type="button" class="btn btn-info mx-1">Modifica prodotto</button>
                </a>
                <a href="${pageContext.request.contextPath}/del-prod?id=${sessionScope.prodotto.id}"> <!--da fare-->
                    <button type="button" class="btn btn-danger mx-1">Elimina prodotto</button>
                </a>
            </c:if>
        </div>
    </div>
</div>