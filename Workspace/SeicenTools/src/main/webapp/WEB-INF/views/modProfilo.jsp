<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:choose>
    <c:when test = "${sessionScope.utente.ruolo == 'ADMIN'}">
        <div class = "profile_data">
            <div class="mx-2 my-2">
                <c:if test="${error != null}">
                    <div class="error-banner">
                        <p>${error}</p>
                    </div>
                </c:if>
                <table>
                    <tr>
                        <td>
                            <h1>Dati Utente</h1>
                            <h2>Nome: ${utenteTrovato.nome}</h2>
                            <h3><strong>E-mail: </strong>${utenteTrovato.email}</h3>
                        </td>
                        <td>
                            <h1>Modifica i dati</h1>
                            <form name="form1" action = "${pageContext.request.contextPath}/mod-utente?idUser=${utenteTrovato.id}" method="post">
                                <div class="form-group">
                                    <div align="center" class="my-sm-1">Nome</div>
                                    <input type="text" id="nome" name="nome" placeholder="es. ${utenteTrovato.nome}" value="">
                                </div>

                                <div class="form-group">
                                    <div align="center" class="my-sm-1">Email</div>
                                    <input type="email" id="email" name="email" placeholder="Cambia mail - es. ${utenteTrovato.email}" value="">
                                </div>

                                <button type="submit" class="btn-warning">Modifica dati utente</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h1 align="center">Errore 401 - accesso non autorizzato</h1>
        <h2 align="center">Niente da vedere qui</h2>
    </c:otherwise>
</c:choose>