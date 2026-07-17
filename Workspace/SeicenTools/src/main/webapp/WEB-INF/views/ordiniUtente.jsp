<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:choose>
    <c:when test = "${numOrders} == 0">
        <div id = "noOrders"><h3>Nessun Ordine da mostrare</h3></div>
    </c:when>
    <c:otherwise>
        <div id = "ordini-page">
            <span><h2>I tuoi ordini</h2></span>
            <c:forEach items="${orders}" var = "ordine" begin = "${offset}" end = "${offset + limit - 1}">
                <div id = "ord-utente my-3">
                    <span class="badge bg-info">Ordine #${ordine.id}</span>
                    <span class="badge bg-secondary">#${ordine.dataCreazione}</span>
                    <span class="fw-bold ms-2">${ordine.indirizzoConsegna}</span>
                    <span class="badge bg-success ms-4">#${ordine.tot}</span>
                </div>
            </c:forEach>
        </div>
        <div id="nav-pagination">
            <nav aria-label="Page navigation example">
              <ul class="pagination">
                <li class="page-item">
                  <a class="page-link" href="${pageContext.request.contextPath}/ordini-utente?page=${page - 1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                </li>

                <c:forEach begin = "1" end = "${totalPages}" var = "i">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ordini-utente?page=${i}"><c:out value = "${i}"></c:out></a></li>
                </c:forEach>

                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/ordini-utente?page=${page + 1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                </li>
              </ul>
            </nav>
        </div>
    </c:otherwise>
</c:choose>