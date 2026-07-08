<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!Doctype html>
html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>SeicenTools</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>


       <h3>Il tuo carrello</h3>
       <div calss="cart" id="cart">
            <c:forEach var="item" items="${show-carrello}">
                <div class="cartItem">
                    <h3>${item.nome}</h3>&nbsp&nbsp
                    <p>qta:${item.qta}&nbsp&nbsp  totale:${item.prezzoLotto </p><!--"prezzoLotto è una variabile di ShowCarrelloServlet che fa il prodotto tra prezzo e qta di ogno prodotto nel carrello"-->
                    <a href="RmvFromCarrelloServlet">
                        <button type="button">Rimuovi</button>
                    </a>
                </div>
            </c:forEach>
       </div>
        <div class="effettuaOrdine">
            <p class="grassetto" id="prezzo"> Totale ordine: ${item.prezzoTotaleCarrello}</p><br>    <!-- anche questa è una variabile di ShowCarrelloServlet-->
            <a href="OrdineServlet">
                <button type="button">ORDINA</button>
            </a>
        </div>


        <div id="footerL">
            <p>&copy; 2026 SeicenTools</p>
        </div>
    </body>
</html>