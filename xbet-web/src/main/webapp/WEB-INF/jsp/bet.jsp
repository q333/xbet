<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp" %>

<fmt:bundle basename="i18n">
    <fmt:message key="match" var="matchTitle"/>
    <fmt:message key="match.date" var="matchDate"/>
    <fmt:message key="match.team1" var="matchTeam1"/>
    <fmt:message key="match.team2" var="matchTeam2"/>
    <fmt:message key="match.1" var="match1"/>
    <fmt:message key="match.X" var="matchX"/>
    <fmt:message key="match.2" var="match2"/>
    <fmt:message key="match.1X" var="match1X"/>
    <fmt:message key="match.12" var="match12"/>
    <fmt:message key="match.2X" var="match2X"/>
    <fmt:message key="matches.log.in" var="matchesLogIn"/>
    <fmt:message key="bet" var="bet"/>
    <fmt:message key="bet.choose" var="betChoose"/>
    <fmt:message key="bet.amount" var="betAmount"/>
    <fmt:message key="bet.place" var="betPlace"/>
    <fmt:message key="balance.error" var="balanceError"/>
    <fmt:message key="balance.not.enough" var="balanceNotEnough"/>
    <fmt:message key="amount.negative" var="amountNegative"/>
    <fmt:message key="bet.placed" var="betPlaced"/>

</fmt:bundle>

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <c:choose>
            <c:when test="${not empty confirmMessage}">
                <h2 class="text-center"><c:out value="${betPlaced}"/>!</h2>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${not empty user}">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                                <div class="billing-details">
                                    <div class="section-title">
                                        <h3 class="title"><c:out value="${betPlace}"/></h3>
                                    </div>
                                    <div>
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th><c:out value="${matchDate}"/></th>
                                                <th><c:out value="${matchTeam1}"/></th>
                                                <th><c:out value="${matchTeam2}"/></th>
                                                <th title="<c:out value="${match1}"/>">1</th>
                                                <th title="<c:out value="${matchX}"/>">X</th>
                                                <th title="<c:out value="${match2}"/>">2</th>
                                                <th title="<c:out value="${match1X}"/>">1X</th>
                                                <th title="<c:out value="${match12}"/>">12</th>
                                                <th title="<c:out value="${match2X}"/>">2X</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td><c:out value="${match.date}"/></td>
                                                <td><c:out value="${match.team1.name}"/></td>
                                                <td><c:out value="${match.team2.name}"/></td>
                                                <td><c:out value="${match.victory1}"/></td>
                                                <td><c:out value="${match.draw}"/></td>
                                                <td><c:out value="${match.victory2}"/></td>
                                                <td><c:out value="${match.victory1OrDraw}"/></td>
                                                <td><c:out value="${match.victory1Or2}"/></td>
                                                <td><c:out value="${match.victory2OrDraw}"/></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form id="checkoutForm" class="clearfix" method="post">
                            <div class="row">
                                <div class="col-md-4 col-md-offset-2">
                                    <div class="form-group">
                                        <label for="select"><c:out value="${betChoose}"/>: </label>
                                        <select id="select" name="bet" class="form-control" form="checkoutForm">
                                            <option value="1/${match.victory1}"><c:out value="${match1}"/></option>
                                            <option value="X/${match.draw}"><c:out value="${matchX}"/></option>
                                            <option value="2/${match.victory2}"><c:out value="${match2}"/></option>
                                            <option value="1X/${match.victory1OrDraw}"><c:out value="${match1X}"/></option>
                                            <option value="12/${match.victory1Or2}"><c:out value="${match12}"/></option>
                                            <option value="2X/${match.victory2OrDraw}"><c:out value="${match2X}"/></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="input"><c:out value="${betAmount}"/>: </label>
                                        <input id="input" type="number" class="form-control" name="amount"/>
                                        <input type="hidden" name="matchId" value="${match.id}"/>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <div class="row" style="color: transparent">1</div>
                                        <button id="btn" class="btn primary-btn pull-right" type="button"
                                                onclick="checkAmount()">
                                            <c:out value="${bet}"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="text-center">
                            <h2><c:out value="${matchesLogIn}"/></h2>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
    <!-- /container -->
</div>
<!-- /section -->

<script>
    <%@include file="../../js/bet.js"%>
</script>

<%@include file="footer.jsp" %>