<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2026-06-06
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>


<!-- ===== NAVBAR ===== -->

<nav class="main-nav">
  <div class="container-fluid px-4">
    <div class="d-flex align-items-center justify-content-between">

      <!-- Liens de navigation -->
      <ul class="nav-links d-flex list-unstyled mb-0 gap-5">
        <li><a href="${pageContext.request.contextPath}/home" class="nav-link-item ${pageContext.request.requestURI.contains('home')? 'active' : ''}">
          <fmt:bundle basename="app">
            <fmt:message key="Acceuil"/>
          </fmt:bundle>
        </a></li>
        <!-- page produit -->
        <li><a href="${pageContext.request.contextPath}/videogames" class="nav-link-item ${pageContext.request.requestURI.contains('videogames')? 'active' : ''}">
          <fmt:bundle basename="app">
            <fmt:message key="Produits"/>
          </fmt:bundle>
        </a></li>
        <!-- wiki -->
        <li><a href="${pageContext.request.contextPath}/videogameWiki" class="nav-link-item ${pageContext.request.requestURI.contains('wiki')? 'active' : ''}">
          <fmt:bundle basename="app">
            <fmt:message key="Wiki"/>
          </fmt:bundle>
        </a></li>
      </ul>

      <!-- Barre de recherche + panier -->
      <div class="d-flex align-items-center gap-3">
        <div class="search-group d-flex align-items-center gap-2">
          <label for="searchInput" class="search-label">
            <fmt:bundle basename="app">
              <fmt:message key="Recherche"/>
            </fmt:bundle>
          </label>
          <form style="display: inline-flex" action="${pageContext.request.contextPath}/videogames" method="get">
            <input type="text" id="searchInput" name="searchInput" required class="search-input" placeholder="search games..." />
            <button class="search-btn" type="submit" id="searchButton"><img src="${pageContext.request.contextPath}/images/loupelogo.png" width="20" height="auto" alt="loupelogo"/></button>
          </form>
          <i class="bi bi-search"></i>
        </div>
        <!-- page cart -->
        <button class="cart-btn">
          <a href="${pageContext.request.contextPath}/cart?action=view" class="nav-link-item position-relative ${pageContext.request.requestURI.contains('cart')? 'active' : ''}">
            <div class="cart-icon-wrapper">
              <img src="${pageContext.request.contextPath}/images/cart.png" width="30" height="auto" alt="cart"/>
              <c:if test="${cartCount > 0}">
                <span class="cart-badge">${cartCount}</span>
              </c:if>
            </div>
          </a>
        </button>
        <div class="dropdown">
          <a class="nav-link-item dropdown-toggle" href="#" id="LangDropdown" role="button" data-bs-toggle="dropdown">
            lang
          </a>
          <ul class="dropdown-menu dropdown-menu-end">
            <li><a class="dropdown-item" href=${pageContext.request.contextPath}/TraitementLangueDefaut?lang=fr_CA>Français</a></li>
            <li><a class="dropdown-item" href=${pageContext.request.contextPath}/TraitementLangueDefaut?lang=en_CA>Anglais</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</nav>

