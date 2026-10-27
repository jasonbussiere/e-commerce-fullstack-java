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
      <fmt:message key="SPPageTitle"/>
    </fmt:bundle>
  </title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LoginStyle.css" />
</head>

<body id="#login">

<div class="ac-card">

  <p class="ac-title">
    <fmt:bundle basename="app">
      <fmt:message key="SPTitle"/>
    </fmt:bundle>
  </p>
  <p class="ac-subtitle">
    <fmt:bundle basename="app">
      <fmt:message key="SPSubtitle"/>
    </fmt:bundle>
  </p>

  <div class="ac-divider">
    <div class="ac-divider-line"></div>
    <img src="${pageContext.request.contextPath}/images/aclogo.png" alt="aclogo"
      height="60 px" width="auto" />
    <div class="ac-divider-line"></div>
  </div>

  <%--Message erreur--%>
  <c:if test="${errorMessage != null}">
    <div class="ac-error">
      <fmt:bundle basename="app">
        <fmt:message key="SPErrorMessage"/>
      </fmt:bundle>
    </div>
  </c:if>

  <!--Form-->
  <form action="${pageContext.request.contextPath}/signup" method="post">
    <div class="ac-field">
      <label class="ac-label" for="firstname">
        <fmt:bundle basename="app">
          <fmt:message key="SPFirstName"/>
        </fmt:bundle>
      </label>
      <input class="ac-input" type="text" id="firstname" name="firstname"
             placeholder="first name" required />
    </div>


      <div class="ac-field">
        <label class="ac-label" for="lastname">
          <fmt:bundle basename="app">
            <fmt:message key="SPLastName"/>
          </fmt:bundle>
        </label>
        <input class="ac-input" type="text" id="lastname" name="lastname"
               placeholder="last name" required />
      </div>

    <div class="ac-field">
      <label class="ac-label" for="email">
        <fmt:bundle basename="app">
          <fmt:message key="SPEmail"/>
        </fmt:bundle>
      </label>
      <input class="ac-input" type="email" id="email" name="email"
             placeholder="assassin@hidden-order.org" required />
    </div>

    <div class="ac-field">
      <label class="ac-label" for="password">
        <fmt:bundle basename="app">
          <fmt:message key="SPPassword"/>
        </fmt:bundle>
      </label>
      <input class="ac-input" type="password" id="password" name="password"
             placeholder="••••••••••••" required />
    </div>

    <div style="text-align: center">
      <button class="ac-btn" type="submit">
        <fmt:bundle basename="app">
          <fmt:message key="SPButton"/>
        </fmt:bundle>
      </button>
    </div>
  </form>

  <p class="ac-nav">
    <fmt:bundle basename="app">
      <fmt:message key="SPaam"/>
    </fmt:bundle>
    <a href="${pageContext.request.contextPath}/login">
      <fmt:bundle basename="app">
        <fmt:message key="SPlhLink"/>
      </fmt:bundle>
    </a>
  </p>

  <p class="ac-motto">
    <fmt:bundle basename="app">
      <fmt:message key="SPMotto"/>
    </fmt:bundle>
  </p>
</div>


</body>
</html>
