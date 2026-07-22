<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="cart-container my-4">
    <h3 class="grassetto">Il tuo Carrello</h3>
    <table class="table-cart table table-striped table-hover">
        <thead>
            <tr>
                <th>Prodotto</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Subtotale</th>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody id="cart-items-body">
            <!-- I prodotti del carrello verranno caricati qui da JavaScript -->
            <tr><td colspan="5" class="text-center">Caricamento carrello...</td></tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3" class="text-end"><strong>Totale:</strong></td>
                <td id="cart-total" class="fw-bold">0.00 €</td>
                <td ID="cart-numProds"><h5>Prodotti presenti nel carrello: ${numProdotti}</h5></td>
            </tr>
        </tfoot>
    </table>
    <div id="cart-empty-message" class="alert alert-info text-center" style="display: none;">
        <p class="mb-0">Il carrello è vuoto.</p>
    </div>
</div>

<div class="text-end mt-3">
    <a href="${pageContext.request.contextPath}/ordine" class="btn btn-primary btn-lg">Ordina</a>
</div>

<script>
    function loadAndRenderCart() {
        fetch('${pageContext.request.contextPath}/api/carrello')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                const cartTableBody = document.getElementById('cart-items-body');
                const cartTotalElement = document.getElementById('cart-total');
                const cartEmptyMessage = document.getElementById('cart-empty-message');
                cartTableBody.innerHTML = ''; // Pulisci gli elementi esistenti

                if (data.carrelloJSON && data.carrelloJSON.length > 0) {
                    cartEmptyMessage.style.display = 'none'; // Nascondi messaggio carrello vuoto
                    data.carrelloJSON.forEach(item => {
                        const row = `
                            <tr>
                                <td>
                                    <img src="${pageContext.request.contextPath}/\${item.imgPath}" alt="\${item.nome}" class="thumbnail-cart">
                                    \${item.nome}
                                </td>
                                <td>\${Number(item.prezzo).toFixed(2)} €</td>
                                <td>
                                    <button class="btn btn-sm btn-outline-secondary" onclick="updateCartItem(\${item.id}, 'meno')">-</button>
                                    <span class="mx-2">\${item.qta}</span>
                                    <button class="btn btn-sm btn-outline-secondary" onclick="updateCartItem(\${item.id}, 'piu')">+</button>
                                </td>
                                <td>\${Number(item.subtotal).toFixed(2)} €</td>
                                <td>
                                    <button class="btn btn-sm btn-danger" onclick="updateCartItem(\${item.id}, 'rmv')">Rimuovi</button>
                                </td>
                            </tr>
                        `;
                        cartTableBody.innerHTML += row;
                    });
                    cartTotalElement.textContent = parseFloat(data.tot).toFixed(2) + ' €';
                } else {
                    cartEmptyMessage.style.display = 'block'; // Mostra messaggio carrello vuoto
                    cartTableBody.innerHTML = '';
                    cartTotalElement.textContent = '0.00 €';
                }
            })
            .catch(error => {
                console.error('Errore nel caricamento del carrello:', error);
                const cartTableBody = document.getElementById('cart-items-body');
                cartTableBody.innerHTML = '<tr><td colspan="5" class="text-danger text-center">Errore nel caricamento del carrello. Riprova più tardi.</td></tr>';
                document.getElementById('cart-total').textContent = 'N/A';
            });
    }

    function updateCartItem(idPrd, action) {
        fetch('${pageContext.request.contextPath}/api/carrello', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `idPrd=\${idPrd}&action=\${action}`
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            loadAndRenderCart();
        })
        .catch(error => {
            console.error('Errore:', error);
        });
    }

    document.addEventListener('DOMContentLoaded', loadAndRenderCart);
</script>