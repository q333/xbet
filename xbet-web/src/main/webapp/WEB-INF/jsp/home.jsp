<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp"%>

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="text-center">
            <h2><c:out value="${title}"/></h2>
            <br>
        </div>
        <img src="<c:url value="/img/fooltball.jpg"/>" alt="image" class="img-responsive center-block" />
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->


<%@include file="footer.jsp"%>
