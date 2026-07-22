<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>
    <div class="modProdForm">
        <c:if test="${error != null}">
            <div class="error-banner mx-2"><p>${error}</p></div>
        </c:if>
    <table>
        <tr>
             <td>
                <h1 class="grassetto">Dati Prodotto</h1>
                <h2>Nome: ${prodotto.nome}</h2>
                <h3>Prezzo: ${prodotto.prezzo} €</h3>
                <h3>Disponibilità: ${prodotto.disponibilita}</h3>
                <h3>Categoria: ${prodotto.categoria}</h3>
             </td>
             <td>
                <h1 class="grassetto">Modifica i dati</h1>
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
                            <textarea name = "descrizione" id = "descrizione" placeholder = "${prodotto.descrizione}" maxlength = 250></textarea>
                            <small id="error-username" class="error-msg" style="color: red"></small>
                            </div>
                        </div>
                        <div id="imgUpload">
                            <div class="form-group">
                                 <div align="center" class="my-sm-1">Immagine</div>
                                     <input type = "file" name = "img" id = "img" accept="image/*">
                                     <small id="error-username" class="error-msg" style="color: red"></small>
                            </div>
                            <input type="text" name="idPrd" value="${prodotto.id}" hidden>
                            <button type="submit" class="btn-login">Aggiorna prodotto</button>
                        </div>
                    </div>
                </form>
             </td>
        </tr>
    </table>
    </div>
</div>