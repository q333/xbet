<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<fmt:bundle basename="i18n">
    <fmt:message key="error" var="error"/>
    <fmt:message key="no.command" var="noCommand"/>
    <fmt:message key="login.failure" var="loginFailure"/>
    <fmt:message key="login.exception" var="loginException"/>
    <fmt:message key="registration.error" var="registrationError"/>
    <fmt:message key="database.exception" var="databaseEcxeption"/>
    <fmt:message key="matches.list.empty" var="matchesListEmpty"/>
    <fmt:message key="match.id.error" var="matchIdError"/>
    <fmt:message key="bet.param.error" var="betParamError"/>
    <fmt:message key="bet.error" var="betError"/>
    <fmt:message key="balance.error" var="balanceError"/>
    <fmt:message key="amount.error" var="amountError"/>
    <fmt:message key="top.up.error" var="topupError"/>
    <fmt:message key="bets.error" var="betsError"/>
    <fmt:message key="finish.error" var="finishError"/>

</fmt:bundle>

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <div class="centered text-center">
            <h2><c:out value="${error}"/></h2>
            <br>
            <h3>
                <c:if test="${not empty errorMessage}">
                    <c:if test="${errorMessage eq 'noCommand'}"><c:out value="${noCommand}"/></c:if>
                    <c:if test="${errorMessage eq 'loginFailure'}"><c:out value="${loginFailure}"/></c:if>
                    <c:if test="${errorMessage eq 'loginException'}"><c:out value="${loginException}"/></c:if>
                    <c:if test="${errorMessage eq 'registrationError'}"><c:out value="${registrationError}"/></c:if>
                    <c:if test="${errorMessage eq 'matchException'}"><c:out value="${databaseEcxeption}"/></c:if>
                    <c:if test="${errorMessage eq 'matchesListEmpty'}"><c:out value="${matchesListEmpty}"/></c:if>
                    <c:if test="${errorMessage eq 'matchIdError'}"><c:out value="${matchIdError}"/></c:if>
                    <c:if test="${errorMessage eq 'betParamError'}"><c:out value="${betParamError}"/></c:if>
                    <c:if test="${errorMessage eq 'betError'}"><c:out value="${betError}"/></c:if>
                    <c:if test="${errorMessage eq 'balanceError'}"><c:out value="${balanceError}"/></c:if>
                    <c:if test="${errorMessage eq 'amountError'}"><c:out value="${amountError}"/></c:if>
                    <c:if test="${errorMessage eq 'topupError'}"><c:out value="${topupError}"/></c:if>
                    <c:if test="${errorMessage eq 'betsError'}"><c:out value="${betsError}"/></c:if>
                    <c:if test="${errorMessage eq 'finishError'}"><c:out value="${finishError}"/></c:if>

                </c:if>
            </h3>
        </div>
    </div>
    <!-- /container -->
</div>
<!-- /section -->

<jsp:include page="footer.jsp"/>