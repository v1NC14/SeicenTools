
        <div class="content">
            <h3>ELENCO UTENTI </h3>
            <c:forEach var="utente" items="listaUtenti">
                <div>
                    <p>${utente.id}&nbsp&bhsp${utente.nome}&nbsp&nbsp${utente.email} </p>
                    <a href"${pageContext.request.contextPath}/mod-utente?utente=${utente}">
                        <button type="button">Modifica utente</button>
                    </a>
                    <a href="${pageContext.request.contextPath}/rmv-utente?utente=${utente}">
                        <button type="button">Rimuovi utente</a>
                    </a>
                </div>
            </c:forEach>
        </div>
        <div id="footerL">
            <p>&copy; 2026 SeicenTools</p>
        </div>
