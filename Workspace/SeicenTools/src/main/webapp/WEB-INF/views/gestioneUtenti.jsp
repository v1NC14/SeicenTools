<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:choose>
    <c:when test = "${sessionScope.utente.ruolo == 'ADMIN'}">
        <div class="elenco-utenti mx-3 my-2">
            <h3>ELENCO UTENTI </h3>
            <c:choose>
                <c:when test="${not empty listaUtenti}">
                    <div class="li-utente">
                        <table>
                            <th class="mx-1">Nome</th>
                            <th class="mx-1">E-mail</th>
                            <th align="center"><i class="bi bi-gear"></i></th>
                            <c:forEach var="user" items="${listaUtenti}">
                                <tr>
                                    <td class="mx-1">${user.nome}</td>
                                    <td class="mx-1">${user.email}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/mod-utente?idUser=${user.id}">
                                            <button type="button" class="btn btn-warning">Modifica utente</button>
                                        </a>

                                        <a href="${pageContext.request.contextPath}/rmv-utente?id=${user.id}">
                                            <button type="button" class="btn btn-danger">Rimuovi utente</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <h2>Nessun utente registrato...</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </c:when>
    <c:otherwise>
        <h1 align="center">Errore 401 - accesso non autorizzato</h1>
        <h2 align="center">Niente da vedere qui</h2>
    </c:otherwise>
</c:choose>