<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>
    <div class="modProdForm">
        <c:if test="${error != null}">
            <div class="error-banner mx-2"><p>${error}</p></div>
        </c:if>

        <form name = "form1" action = "${pageContext.request.contextPath}/mod-prod" method = "post" enctype="multipart/form-data">
        <div id = "dataUpload">
            <div class="form-group">
                <div align="center" class="my-sm-1">Nome</div>
                    <input type="text" id="nome" name="nome"  placeholder="${prod.nome}">
                    <small id="error-username" class="error-msg" style="color: red"></small>
            </div>
            <div class="form-group">
                <div align="center" class="my-sm-1">Prezzo</div>
                    <input type="number" id="prezzo" name="prezzo" min="0.01" max="10000" step = "0.01">
                    <small id="error-username" class="error-msg" style="color: red"></small>
            </div>
            <div class="form-group">
                <div align="center" class="my-sm-1">Disponibilità</div>
                    <input type="number" id="disponibilita" name="disponibilita" min="1" max="999">
                    <small id="error-username" class="error-msg" style="color: red"></small>
            </div>
            <div class="form-group">
                <div align="center" class="my-sm-1">Categoria</div>
                    <select id="categoria" name="categoria" placeholder = "modifica categoria">
                        <c:forEach var = "c" items = "${categorie}">
                            <option value = "${c}">${c}</option>
                        </c:forEach>
                    </select>
                    <small id="error-username" class="error-msg" style="color: red"></small>
            </div>
            <div class="form-group">
                <div align="center" class="my-sm-1">Descrizione</div>
                    <textarea name = "descrizione" id = "descrizione" placeholder = "${prod.descrizione}" maxlength = 250></textarea>
                    <small id="error-username" class="error-msg" style="color: red"></small>
            </div>
        </div>
        <div id="imgUpload">
            <div class="form-group">
                <div align="center" class="my-sm-1">Immagine</div>
                   <img src="  ${pageContext.request.contextPath}/images/products/${prod.ImgPath}">
                    <input type = "file" name = "img" id = "img" accept="image/*">
                    <small id="error-username" class="error-msg" style="color: red"></small>
                </div>

            <button type="submit" class="btn-login">Aggiorna prodotto</button>
        </div>
        </form>
    </div>
</div>