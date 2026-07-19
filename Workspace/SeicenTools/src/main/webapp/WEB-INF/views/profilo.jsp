<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class = "profile_data">
    <div class="mx-2 my-2">
        <c:if test="${error != null}">
            <div class="error-banner">
                <p>${error}</p>
            </div>
        </c:if>
        <div class="my-1">
            <h1>Nome: ${sessionScope.utente.nome}</h1>
            <h3><strong>E-mail: </strong>${sessionScope.utente.email}</h3>
        </div>

        <div class="my-2">
            <a href="${pageContext.request.contextPath}/logout">
                        <button type="button" class="btn btn-danger mx-1">Logout<i class="bi bi-box-arrow-right"></i></button>
                    </a>
            <a href="${pageContext.request.contextPath}/ordini-utente?page=1">
                <button type="button" class="btn btn-danger mx-1">Elenco Ordini<i class="bi bi-file-earmark-text"></i></button>
            </a>
        </div>
    </div>
</div>