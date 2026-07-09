<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div calss="cart" id="cart">
    <c:forEach var="item" items="${prodottiUtente}">
        <div class="cartItem">
            <h3>${item.nome}</h3>&nbsp&nbsp
            <p>qta:${item.qta}&nbsp&nbsp  totale:${item.prezzoLotto </p><!--"prezzoLotto è una variabile di ShowCarrelloServlet che fa il prodotto tra prezzo e qta di ogno prodotto nel carrello"-->
            <a href="RmvFromCarrelloServlet"><button type="button">Rimuovi</button></a>
        </div>
    </c:forEach>
</div>

<div class="effettuaOrdine">
    <p class="grassetto"> Totale ordine: ${item.prezzoTotaleCarrello}</p><br>    <!-- anche questa è una variabile di ShowCarrelloServlet-->
    <a href="OrdineServlet"><button type="button">ORDINA</button></a>
</div>