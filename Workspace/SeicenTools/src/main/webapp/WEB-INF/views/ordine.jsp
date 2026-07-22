<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<h3>Conferma ordine</h3>

<form method="post" action="${pageContext.request.contextPath}/conferma-ordine">

    <table class="table">
        <thead>
            <tr>
                <th>Prodotto</th>
                <th>Quantità</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty prodotti}">
                <tr>
                    <c:forEach var="item" items="${prodotti}">
                            <td>${item.nome}</td>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${carrello}">
                         <tr>
                             <td>${item.id_prodotto}</td>
                             <td>${item.qta}</td>
                         </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <div class="mb-3">
        <label>Indirizzo consegna</label>
        <input class="form-control" name="indirizzo" placeholder="Inserisci l'indirizzo di consegna" required>
    </div>

    <div class="mb-3">
        <label>Numero carta</label>

        <input class="form-control" name="numCarta" placeholder="Inserisci il numero della tua carta" required>
    </div>

    <input type="submit" class="btn btn-success">Conferma ordine</button>
</form>