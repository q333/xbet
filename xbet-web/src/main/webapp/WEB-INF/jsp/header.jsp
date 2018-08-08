<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="currentURI" value="${pageContext.request.requestURI}" scope="session"/>

<fmt:bundle basename="i18n">
    <fmt:message key="title" var="title"/>
    <fmt:message key="language" var="language"/>
    <fmt:message key="welcome" var="welcome"/>
    <fmt:message key="home" var="home"/>
    <fmt:message key="my.account" var="account"/>
    <fmt:message key="login" var="login"/>
    <fmt:message key="join" var="join"/>
    <fmt:message key="logout" var="logout"/>
    <fmt:message key="matches" var="matches"/>
    <fmt:message key="balance.top.up" var="balanceTopUp"/>
    <fmt:message key="bet.amount" var="betAmount"/>
    <fmt:message key="amount.negative" var="amountNegative"/>
    <fmt:message key="my.bets" var="myBets"/>

</fmt:bundle>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="referrer" content="unsafe-url">

    <title><c:out value="${title}"/></title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Rubik" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"/>

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/slick.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/slick-theme.css"/>"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/nouislider.min.css"/>"/>

    <!-- Font Awesome Icon -->
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/> ">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>

</head>

<body>

<script>
    <%@include file="../../js/balance.js"%>
</script>

<!-- HEADER -->
<header>
    <!-- top Header -->
    <div id="top-header">
        <div class="container">
            <div class="pull-left">
                <span><c:out value="${welcome}"/><c:if test="${not empty user}">, <strong><c:out
                        value="${user.firstName}"/></strong></c:if>!</span>
            </div>
            <div class="pull-right">
                <ul class="header-top-links">
                    <li class="dropdown default-dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown"
                           aria-expanded="true"><c:out value="${language} "/><i class="fa fa-caret-down"></i></a>
                        <ul class="custom-menu">
                            <li><a href="<c:url value="/main/locale?lang=en"/>">English</a></li>
                            <li><a href="<c:url value="/main/locale?lang=ru"/>">Русский</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- /top Header -->

    <!-- header -->
    <div id="header">
        <div class="container">
            <div class="pull-left">
                <!-- Logo -->
                <div class="header-logo">
                    <a class="logo" href="<c:url value="/main/home"/> ">
                        <img src="<c:url value="/img/logo.png"/>" alt="X-BET">
                    </a>
                </div>
                <!-- /Logo -->
            </div>

            <!-- Balance -->
            <div class="pull-right">
                <ul class="header-btns">
                    <c:if test="${not empty user}">
                        <li class="header-cart dropdown default-dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                <div class="header-btns-icon">
                                    <i class="fa fa-usd"></i>
                                </div>
                                <strong class="text-uppercase hover"><c:out value="${balanceTopUp}"/></strong>
                                <br>
                                <span class="small text-orange">
                                    <fmt:formatNumber value="${user.balance}" type="currency"/></span>
                            </a>
                            <div class="custom-menu">
                                <div id="shopping-cart">
                                    <form id="balanceForm" class="clearfix" method="post">
                                        <div class="form-group">
                                            <input id="balanceInput" type="number" class="form-control" name="amount"
                                                   placeholder="${betAmount}" style="margin-top: 10px"/>
                                            <button id="balanceBtn" class="main-btn" type="button"
                                                    onclick="checkAmountInput()" style="margin: 15px 30px">
                                                <c:out value="${balanceTopUp}"/></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:if>
                    <!-- /Balance -->

                    <!-- Account -->
                    <li class="header-account dropdown default-dropdown">
                        <c:choose>
                            <c:when test="${empty user}">
                                <div>
                                    <div class="header-btns-icon">
                                        <i class="fa fa-user-o"></i>
                                    </div>
                                    <strong class="text-uppercase"><c:out value="${account} "/><i
                                            class="fa fa-caret-down"></i></strong>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
                                    <div class="header-btns-icon">
                                        <i class="fa fa-user-o"></i>
                                    </div>
                                    <strong class="text-uppercase hover"><c:out value="${account} "/><i
                                            class="fa fa-caret-down"></i></strong>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${empty user}">
                            <a href="<c:url value="/main/login"/>" class="small"><c:out value="${login}"/></a> /
                            <a href="<c:url value="/main/registration"/>" class="small"><c:out value="${join}"/></a>
                        </c:if>
                        <c:if test="${not empty user}">
                            <a href="<c:url value="/main/logout"/>" class="small"><c:out value="${logout}"/></a>
                        </c:if>
                        <ul class="custom-menu">
                            <li><a href="<c:url value="/main/bets"/>"><i class="fa fa-handshake-o"></i> <c:out value="${myBets}"/></a></li>
                        </ul>
                    </li>
                    <!-- /Account -->

                    <!-- Mobile nav toggle-->
                    <li class="nav-toggle">
                        <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                    </li>
                    <!-- / Mobile nav toggle -->
                </ul>
            </div>
        </div>
        <!-- header -->
    </div>
    <!-- container -->
</header>
<!-- /HEADER -->

<!-- NAVIGATION -->
<div id="navigation">
    <!-- container -->
    <div class="container">
        <div id="responsive-nav">
            <!-- menu nav -->
            <div class="menu-nav">
                <span class="menu-header">Menu <i class="fa fa-bars"></i></span>
                <ul class="menu-list">
                    <li><a href="<c:url value="/main/home"/>"><c:out value="${home}"/></a></li>
                    <li><a href="<c:url value="/main/matches"/>"><c:out value="${matches}"/></a></li>
                </ul>
            </div>
            <!-- menu nav -->
        </div>
    </div>
    <!-- /container -->
</div>
<!-- /NAVIGATION -->

