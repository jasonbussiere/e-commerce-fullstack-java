<%--author: zacch, Bruno--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

<fmt:setLocale value="${sessionScope.locale}"/>


    <title>
        <fmt:bundle basename="app">
            <fmt:message key="LPPageTitle"/>
        </fmt:bundle>
    </title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LoginStyle.css" />
</head>
<body id="#login">

<div class="ac-card">

    <p class="ac-title">
        <fmt:bundle basename="app">
            <fmt:message key="LPTitle"/>
        </fmt:bundle>
    </p>
    <p class="ac-subtitle">
        <fmt:bundle basename="app">
            <fmt:message key="LPSubtitle"/>
        </fmt:bundle>
    </p>

    <div class="ac-divider">
        <div class="ac-divider-line"></div>
        <img src="${pageContext.request.contextPath}/images/aclogo.png" alt="aclogo"
        height="60 px" width="auto">
        <div class="ac-divider-line"></div>
    </div>

    <%--Message erreur--%>
    <c:if test="${requestScope.errorMessage != null}">
        <div class="ac-error">
            <fmt:bundle basename="app">
                <fmt:message key="LPErrorMessage"/>
            </fmt:bundle>
            <input type="hidden" name="${requestScope.name}" value="${requestScope.errorMessage}" />
        </div>
    </c:if>

    <c:if test="${sessionScope.successMessage != null}">
        <div class="ac-success">
            ${sessionScope.successMessage}
        </div>
        <!-- deletes the message on the next refresh or redirection
        // This way, it doesn't stack over the error message if there is an error
        // and it doesn't stay on the page if we leave it and return to it later -->
        <c:remove var="successMessage" scope="session"/>
    </c:if>

    <!--Form-->
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="ac-field">
            <label class="ac-label" for="email">
                <fmt:bundle basename="app">
                    <fmt:message key="LPEmail"/>
                </fmt:bundle>
            </label>
            <input class="ac-input" type="email" id="email" name="email"
                   placeholder="assassin@hidden-order.org" required />
        </div>

        <div class="ac-field">
            <label class="ac-label" for="password">
                <fmt:bundle basename="app">
                    <fmt:message key="LPPassword"/>
                </fmt:bundle>
            </label>
            <input class="ac-input" type="password" id="password" name="password"
                   placeholder="••••••••••••" required />
        </div>

        <div style="text-align: center">
            <button class="ac-btn" type="submit">
                <fmt:bundle basename="app">
                    <fmt:message key="LPButton"/>
                </fmt:bundle>
            </button>
        </div>
    </form>

    <p class="ac-nav">
        <fmt:bundle basename="app">
            <fmt:message key="LPnry"/>
        </fmt:bundle>
        <a href="${pageContext.request.contextPath}/signup">
            <fmt:bundle basename="app">
                <fmt:message key="LPspLink"/>
            </fmt:bundle>
        </a>
    </p>

    <p class="ac-motto">
        <fmt:bundle basename="app">
            <fmt:message key="LPMotto"/>
        </fmt:bundle>
    </p>

        <!-- language selection -->
    <div class="dropdown">
        <a class="nav-link-item dropdown-toggle" href="#" id="LangDropdown" role="button" data-bs-toggle="dropdown" style="text-decoration: none; color: #C00000">
            lang
        </a>
        <ul class="dropdown-menu dropdown-menu-end">
            <li><a class="dropdown-item" href=${pageContext.request.contextPath}/TraitementLangueDefaut?lang=fr_CA>Français</a></li>
            <li><a class="dropdown-item" href=${pageContext.request.contextPath}/TraitementLangueDefaut?lang=en_CA>Anglais</a></li>
        </ul>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
