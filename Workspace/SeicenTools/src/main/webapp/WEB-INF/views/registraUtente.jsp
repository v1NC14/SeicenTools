<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>
    <h1>Registrati a SeicenTools</h1>
    <div class="addUser_form">
        <c:if test="${error != null}">
            <div class="error-banner mx-2">
                <p>${error}</p>
            </div>
        </c:if>
        <form name="form1" action = "add-utente" method="post">
            <div class="form-group">
                <div align="center" class="my-sm-1">Nome</div>
                <input type="text" id="nome" name="nome"  placeholder="Inserisci il tuo nome">
                <small id="error-username" class="error-msg" style="color: red"></small>
            </div>
            <div class="form-group">
                <div align="center" class="my-sm-1">Password</div>
                <input type="password" id="password" name="password"  placeholder="Inserisci la tua password">
            </div>
            <div class="form-group">
                <div align="center" class="my-sm-1">Email</div>
                <input type="email" id="email" name="email"  placeholder="Inserisci la tua email">
            </div>
            <div class="form-group">
                <div align="center" class="my-sm-1">Ruolo</div>
                <select id="ruolo" name="ruolo" placeholder="Seleziona un ruolo">
                    <option value="USER" selected>Utente</option>
                    <option value="ADMIN" >Admin</option>

                </select>
            </div>

            <button type="submit" class="btn-login">Crea utente</button>
        </form>
    </div>
</div>