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
+            <div >
+                 <a href="ShowCarrelloServlet">
+                    <img src="${pageContext.request.contextPath}/imgs/carrello.jpg" alt="carrello" id="carrello" class="mx-3 my-1">
+                </a>
+            </div>
+
+            <div>
+                <a herf=""><!--fare la servlet profilo-->
+                    <img src="${pageContext.request.contextPath}/imgs/profilo.jpg" alt="profilo" id="profilo" class="mx-3 my-1">
+                </a>
+            </div>
+
+        </nav>
+
+        <div class="banner">
+            <h1>BANNER</h1>
+        <!-- da completare-->
+        </div>
+
+        <div class="content">
+
+            <aside class="categorie" >
+
+                <a href="#">CATEGORIA 1</a>
+                <a href="#">CATEGORIA 2</a>
+                <a href="#">CATEGORIA 3</a>
+                <a href="#">CATEGORIA 5</a>
+            </aside>
+
+            <section class="prodotti">
+                    <div class="product-card">
+                        <a href="DettagliProdottoServlet" >
+                            <img src="" alt="prodotto1" width="200" height="200"><br>Prodotto3
+                        </a>
+                    </div>
+                    <div class="product-card">
+                        <a href="DettagliProdottoServlet" >
+                            <img src="" alt="prodotto2" width="200" height="200"><br>Prodotto3
+                        </a>
+                    </div>
+                    <div class="product-card">
+                        <a href="DettagliProdottoServlet" >
+                            <img src="" alt="prodotto3" width="200" height="200"><br>Prodotto3
+                        </a>
+                    </div>
+            </section>
+
+        </div>
+
+
+
+        <div id="footerL">
+            <p>&copy; 2026 SeicenTools</p>
+        </div>
+
+
+
+    </body>
+</html>