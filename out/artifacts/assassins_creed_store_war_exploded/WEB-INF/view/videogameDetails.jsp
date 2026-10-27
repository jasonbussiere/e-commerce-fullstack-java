<%--

    Document   : gamePage

    Created on : 3 juin 2026, 12 h 07 min 45 s

    Author     : Bruno

--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MonStyle.css">
    <title>
        ${videogame.title}
    </title>
</head>
<body>

<header class="site-header d-flex align-items-center px-4">
    <div class="logo d-flex align-items-center gap-2 me-auto">
        <img src="${pageContext.request.contextPath}/images/aclogo.png" id="logo" alt="aclogo" width="60px" height="auto" />
        <div class="logo-text" >
            <span class="logo-top" style="color:black">Assassin's</span>
            <span class="logo-bottom" style="color:#C00000">creed</span>
        </div>
    </div>
</header>

<!-- ===== NAVBAR ===== -->

<nav class="main-nav">
    <div class="container-fluid px-4">
        <div class="d-flex align-items-center justify-content-between">

            <!-- Liens de navigation -->
            <ul class="nav-links d-flex list-unstyled mb-0 gap-5">
                <li><a href="${pageContext.request.contextPath}/home" class="nav-link-item">
                    <fmt:bundle basename="app">
                        <fmt:message key="Acceuil"/>
                    </fmt:bundle>
                </a></li>
                <!-- page produit -->
                <li><a href="${pageContext.request.contextPath}/videogames" class="nav-link-item">
                    <fmt:bundle basename="app">
                        <fmt:message key="Produits"/>
                    </fmt:bundle>
                </a></li>
                <!-- wiki -->
                <li><a href="${pageContext.request.contextPath}/videogameWiki" class="nav-link-item">
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
                <button class="cart-btn ">
                    <a href="${pageContext.request.contextPath}/cart?action=view" class="nav-link-item position-relative">
                        <img src="${pageContext.request.contextPath}/images/cart.png" width="30" height="auto" alt="cart"/>
                        <c:if test="${cartCount > 0}">
                            <span class="cart-badge">${cartCount}</span>
                        </c:if>
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

<!-- ===== CONTENU PRINCIPAL ===== -->

    <br>
    <div class="container">
        <div class="row">
            <div class="col-4">
                <img src="${pageContext.request.contextPath}${videogame.imageUrl}" alt="${videogame.title}">
            </div>

            <div class="col-8 rounded bg-light">
                <h1 style="color:black">assassin's creed</h1>

                <p style="color:black">
                    <fmt:bundle basename="app">
                        <fmt:message key="Prix"/>
                    </fmt:bundle>
                    <span>${videogame.unitPrice}</span></p>

                <div>
                    <span class="review-icon"><img src="${pageContext.request.contextPath}/images/reviews5.png" width="auto" height="60px"></span>

                    <form action="${pageContext.request.contextPath}/cart" method="get">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="gameId" value="${videogame.gameId}">
                    <button class="btn-add-cart btn btn-block btn-lg">
                        <fmt:bundle basename="app">
                            <fmt:message key="BoutonAjouter"/>
                        </fmt:bundle>
                    </button>
                </div>

                <div>
                    <br>
                    <p style="color:black; ">

                        ${videogame.description}
                    </p>
                </div>
            </div>
        </div>

        <!-- ===== reviews ===== -->

    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
