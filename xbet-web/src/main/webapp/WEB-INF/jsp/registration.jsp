<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp" %>

<fmt:bundle basename="i18n">
    <fmt:message key="registration.data" var="regData"/>
    <fmt:message key="enter.login" var="enterLogin"/>
    <fmt:message key="login.required" var="loginRequired"/>
    <fmt:message key="login.accepted" var="loginAccepted"/>
    <fmt:message key="login.exists" var="loginExists"/>
    <fmt:message key="login.whitespace" var="loginWhitespace"/>
    <fmt:message key="login.error" var="loginError"/>
    <fmt:message key="login.regexp" var="loginRegexp"/>
    <fmt:message key="enter.password" var="enterPassword"/>
    <fmt:message key="password.required" var="passwordRequired"/>
    <fmt:message key="password.accepted" var="passwordAccepted"/>
    <fmt:message key="password.correct" var="passwordCorrect"/>
    <fmt:message key="password.incorrect" var="passwordIncorrect"/>
    <fmt:message key="password.whitespace" var="passwordWhitespace"/>
    <fmt:message key="password.regexp" var="passwordRegexp"/>
    <fmt:message key="password.match" var="passwordMatch"/>
    <fmt:message key="password.second.required" var="passwordSecondRequired"/>
    <fmt:message key="enter.password.check" var="enterPasswordCheck"/>
    <fmt:message key="enter.firstname" var="enterFirstname"/>
    <fmt:message key="enter.lastname" var="enterLastname"/>
    <fmt:message key="enter.email" var="email"/>
    <fmt:message key="firstname.required" var="firstNameRequired"/>
    <fmt:message key="firstname.whitespace" var="firstNameWhitespace"/>
    <fmt:message key="firstname.regexp" var="firstNameRegexp"/>
    <fmt:message key="firstname.accepted" var="firstNameAccepted"/>
    <fmt:message key="lastname.required" var="lastNameRequired"/>
    <fmt:message key="lastname.accepted" var="lastNameAccepted"/>
    <fmt:message key="lastname.regexp" var="lastNameRegexp"/>
    <fmt:message key="lastname.whitespace" var="lastNameWhitespace"/>
    <fmt:message key="regexp" var="regexp"/>
    <fmt:message key="regexp.email" var="regexpEmail"/>
    <fmt:message key="email.accepted" var="emailAccepted"/>
    <fmt:message key="email.required" var="emailRequired"/>
    <fmt:message key="email.whitespace" var="emailWhitespace"/>
    <fmt:message key="email.regexp" var="emailRegexp"/>
    <fmt:message key="user.logged" var="userLogged"/>
    <fmt:message key="send" var="send"/>
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
                                <h3 class="title"><c:out value="${regData}"/></h3>
                            </div>
                            <form id="checkout-form" class="clearfix" method="post" action="#">
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
                                <div class="form-group">
                                    <input id="passwordCheck" class="input" type="password" name="passwordCheck"
                                           onchange="secondPasswordCheck()"
                                           placeholder="<c:out value="${enterPasswordCheck}"/>">
                                    <div id="passwordCheckDiv" style="display:none;"></div>
                                </div>
                                <div class="form-group">
                                    <input id="firstName" class="input" type="text" name="firstName"
                                           onchange="firstNameCheck()"
                                           placeholder="<c:out value="${enterFirstname}"/>">
                                    <div id="firstNameDiv" style="display:none;"></div>
                                </div>
                                <div class="form-group">
                                    <input id="lastName" class="input" type="text" name="lastName"
                                           onchange="lastNameCheck()"
                                           placeholder="<c:out value="${enterLastname}"/>">
                                    <div id="lastNameDiv" style="display:none;"></div>
                                </div>
                                <div class="form-group">
                                    <input id="email" class="input" type="text" name="email" onchange="emailCheck()"
                                           placeholder="<c:out value="${email}"/>">
                                    <div id="emailDiv" style="display:none;"></div>
                                </div>
                            </form>
                            <button id="btn" class="primary-btn pull-left"
                                    onclick="changeFormAction()"><c:out value="${send}"/></button>
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


<script type="text/javascript" charset="UTF-8">
    <%@include file="../../js/registration.js" %>

</script>

<%@include file="footer.jsp" %>