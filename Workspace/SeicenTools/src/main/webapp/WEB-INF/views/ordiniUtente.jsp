
    <div class="content">
        <div class="head-content">
            <h3 class="grassetto">I TUOI ORDINI</h3>
            <div class="filtro">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" >Filtra per</button>
                <ul class="dropdown-menu">
                    <c:forEach var="opzione" items="">
                        <li><a class="filtro-item" href=""> </a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="body-content">
            <nav aria-label="pagination-nav">
              <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#"> < </a></li>
                <c:forEach var="pagina" items="${totalPages}">
                    <li class="page-item"><a class="page-link" href="#">${currentPage}+1</a></li>
                </c:forEach>
              </ul>
            </nav>
        </div>
    </div>




    <div id="footerL">
        <p>&copy; 2026 SeicenTools</p>
    </div>