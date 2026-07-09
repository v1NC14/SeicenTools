
        <div class="content">
            <h3>ELENCO UTENTI </h3>
            <c:forEach var="elencoUtenti" items="listaUtenti">
                <div>
                    <p>${elencoUtenti.id}&nbsp&bhsp${elencoUtenti.nome}&nbsp&nbsp${elencoUtenti.email} </p>
                    <a href"ModificaUtenteSerlet">
                        <button type="button">Modifica utente</button>
                    </a>
                    <a href="RimuoviUtenteServlet">
                        <button type="button">Rimuovi utente</a>
                    </a>
                </div>
            </c:forEach>
        </div>
        <div id="footerL">
            <p>&copy; 2026 SeicenTools</p>
        </div>
