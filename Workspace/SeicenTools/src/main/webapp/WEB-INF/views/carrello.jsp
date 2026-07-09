<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
+<%@ taglib prefix="c" uri="jakarta.tags.core" %>
+
+<!Doctype html>
+<html lang="it">
+    <head>
+        <meta charset="UTF-8">
+        <title>SeicenTools</title>
+        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
+        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
+        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
+    </head>
+    <body>
+    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
+        <nav class="navbar nav-color">
+            <div class="navbar-brand" id="brand">
+                <img src="${pageContext.request.contextPath}/imgs/logo.jpg" alt="logo" id="logo" class="mx-3 my-1"> <!-- qui poi verrÃ  inserita l img del logo (croppato) di EWMS -->
+                SeicenTools
+            </div>
+
+            <div >
+                <a href="HomepageServlet">
+                    <img src="${pageContext.request.contextPath}/imgs/homepage.jpg" alt="homepage" id="homepage" class="mx-3 my-1">
+                </a>
+            </div>
+            <div >
+                <a href=""><!--profiloServlet-->
+                    <img src="${pageContext.request.contextPath}/imgs/profilo.jpg" alt="profilo" id="profilo" class="mx-3 my-1">
+                </a>
+            </div>
+        </nav>
+
+       <h3>Il tuo carrello</h3>
+       <div calss="cart" id="cart">
+            <c:forEach var="item" items="${prodOrdine}">
+                <div class="cartItem">
+                    <c:out value="${item}">
+                    <h3>${sessionScope.prodOrdine.nome}</h3>&nbsp&nbsp
+                    <p>quantita:${sessionScope.prodOrdine.qta}&nbsp&nbsp  prezzo:   </p>
+                    <a href="RmvFromCarrelloServlet">
+                        <button type="button">Rimuovi</button>
+                    </a>
+                </div>
+            </c:forEach>
+       </div>
+
+    </body>
+</html>