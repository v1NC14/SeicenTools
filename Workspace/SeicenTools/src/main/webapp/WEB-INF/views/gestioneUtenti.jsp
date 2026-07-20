<c:choose>
    <c:when test = "${sessionScope.utente.ruolo == 'ADMIN'}">
        <div class="elenco-utenti mx-3 my-2">
            <h3>ELENCO UTENTI </h3>
            <c:choose>
                <c:when test="${not empty listaUtenti}">
                    <div class="li-utente">
                        <table>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th><i class="bi bi-gear"></i></th>
                            <c:forEach var="utente" items="${listaUtenti}">
                                <tr>
                                    <td>{utente.nome}</td>
                                    <td>{utente.email}</td>
                                    <td>
                                        <a href"${pageContext.request.contextPath}/mod-utente?id=${utente.id}">
                                            <button type="button">Modifica utente</button>
                                        </a>

                                        <a href="${pageContext.request.contextPath}/rmv-utente?id=${utente.id}">
                                            <button type="button">Rimuovi utente</a>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <h2 align = "center">Nessun utente registrato...</h2>
                </C:otherwise>
            </c:choose>
        </div>
    </c:when>
    <c:otherwise>
        <h1 align="center">Errore 401 - accesso non autorizzato</h1>
        <h2 align="center">Niente da vedere qui</h2>
    </c:otherwise>
</c:choose>

<div id="footerL">
    <p>&copy; 2026 SeicenTools</p>
</div>