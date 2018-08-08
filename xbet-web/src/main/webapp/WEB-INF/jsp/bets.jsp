<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp" %>

<fmt:bundle basename="i18n">
    <fmt:message key="matches.log.in" var="matchesLogIn"/>
    <fmt:message key="my.bets" var="myBets"/>
    <fmt:message key="match.date" var="matchDate"/>
    <fmt:message key="match.team1" var="matchTeam1"/>
    <fmt:message key="match.team2" var="matchTeam2"/>
    <fmt:message key="coefficient" var="coefficient"/>
    <fmt:message key="bet" var="betTitle"/>
    <fmt:message key="no.bets" var="noBets"/>
    <fmt:message key="status" var="status"/>
    <fmt:message key="bet.result" var="betResult"/>


</fmt:bundle>

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <c:choose>
            <c:when test="${not empty user}">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="billing-details">
                            <c:choose>
                                <c:when test="${not empty bets}">
                                    <div class="section-title">
                                        <h3 class="title"><c:out value="${myBets}"/></h3>
                                    </div>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th><c:out value="${matchDate}"/></th>
                                            <th><c:out value="${matchTeam1}"/></th>
                                            <th><c:out value="${matchTeam2}"/></th>
                                            <th><c:out value="${betResult}"/></th>
                                            <th><c:out value="${coefficient}"/></th>
                                            <th><c:out value="${betTitle}"/></th>
                                            <th><c:out value="${status}"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${bets}" var="bet">
                                            <tr>
                                                <td><c:out value="${bet.match.date}"/></td>
                                                <td><c:out value="${bet.match.team1.name}"/></td>
                                                <td><c:out value="${bet.match.team2.name}"/></td>
                                                <td><c:out value="${bet.betResult}"/></td>
                                                <td><c:out value="${bet.bet}"/></td>
                                                <td><c:out value="${bet.money}"/></td>
                                                <td><c:out value="${bet.status}"/></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <div class="text-center">
                                        <h2><c:out value="${noBets}"/></h2>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="text-center">
                    <h2><c:out value="${matchesLogIn}"/></h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <!-- /container -->
</div>
<!-- /section -->


<%@include file="footer.jsp" %>