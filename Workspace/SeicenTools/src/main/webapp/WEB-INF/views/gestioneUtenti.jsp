<c:choose>
    <c:when test = "${sessionScope.utente.ruolo == 'ADMIN'}">
        <div class="content">
            <h3>ELENCO UTENTI </h3>
            <c:choose>
                <c:when test="${not empty listaUtenti}">
                    <c:forEach var="utente" items="${listaUtenti}">
                        <div>
                            <p>${utente.id}&nbsp&bhsp${utente.nome}&nbsp&nbsp${utente.email} </p>
                            <a href"${pageContext.request.contextPath}/mod-utente?id=${utente.id}">
                                <button type="button">Modifica utente</button>
                            </a>

                            <a href="${pageContext.request.contextPath}/rmv-utente?id=${utente.id}">
                                <button type="button">Rimuovi utente</a>
                            </a>
                        </div>
                    </c:forEach>
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