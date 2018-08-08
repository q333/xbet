<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp" %>

<fmt:bundle basename="i18n">
    <fmt:message key="login.data" var="data"/>
    <fmt:message key="enter.login" var="enterLogin"/>
    <fmt:message key="enter.password" var="enterPassword"/>
    <fmt:message key="login" var="login"/>
    <fmt:message key="login.correct" var="loginCorrect"/>
    <fmt:message key="login.incorrect" var="loginIncorrect"/>
    <fmt:message key="login.error" var="loginError"/>
    <fmt:message key="login.required" var="loginRequired"/>
    <fmt:message key="password.required" var="passwordRequired"/>
    <fmt:message key="password.valid" var="passwordValid"/>
    <fmt:message key="password.incorrect" var="passwordIncorrect"/>
    <fmt:message key="password.whitespace" var="passwordWhitespace"/>
    <fmt:message key="password.regexp" var="passwordRegexp"/>
    <fmt:message key="user.logged" var="userLogged"/>
</fmt:bundle>

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <c:choose>
            <c:when test="${empty user}">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="billing-details">
                            <div class="section-title">
                                <c:choose>
                                    <c:when test="${empty message}">
                                        <h3 class="title"><c:out value="${data}"/></h3>
                                    </c:when>
                                    <c:otherwise>
                                        <h3 class="title" style="color:crimson"><c:out
                                                value="${passwordIncorrect}"/>!</h3>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <form id="checkout-form" class="clearfix" method="post">
                                <div class="form-group">
                                    <input id="login" class="input" type="text" name="login" onchange="checkLogin()"
                                           placeholder="<c:out value="${enterLogin}"/>">
                                    <div id="loginDiv" style="display:none;"></div>
                                </div>
                                <div class="form-group">
                                    <input id="password" class="input" type="password" name="password"
                                           onchange="checkPassword()"
                                           placeholder="<c:out value="${enterPassword}"/>">
                                    <div id="passwordDiv" style="display:none;"></div>
                                </div>
                            </form>
                            <button id="btn" class="primary-btn pull-left" onclick="changeFormAction()"><c:out
                                    value="${login}"/></button>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="text-center">
                    <h2><c:out value="${userLogged}"/></h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <!-- /container -->
</div>
<!-- /section -->

<script>
    <%@include file="../../js/login.js" %>
</script>

<%@include file="footer.jsp" %>