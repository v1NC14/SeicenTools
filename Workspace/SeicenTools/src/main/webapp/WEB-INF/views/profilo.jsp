<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class = "profile_img">
<img > <!--qui devo capire come poter caricare le immagini, servirà tempo, è una delle parti più difficili del progetto-->
<a href="ProfiloServlet?action=chngImg"> <!--da creare-->
    <button type="button" class="btn btn-danger mx-1">Cambia immagine profilo<i class="bi bi-box-arrow-right"></i></button>
</a>
</div>
<div class = "profile_data">
    <div class="mx-2 my-2">
        <c:if test="${error != null}">
            <div class="error-banner">
                <p>${error}</p>
            </div>
        </c:if>
        <div class="my-1">
            <h1>${sessionScope.utente.nome}</h1>
            <h3><strong>E-mail: </strong>${sessionScope.utente.email}</h3>
        </div>

        <div class="my-2">
            <a href="LogoutServlet">
                        <button type="button" class="btn btn-danger mx-1">Logout<i class="bi bi-box-arrow-right"></i></button>
                    </a>
            <a href="OrdineServlet">
                <button type="button" class="btn btn-danger mx-1">Elenco Ordini<i class="bi bi-box-arrow-right"></i></button>
            </a>
        </div>
    </div>
</div>