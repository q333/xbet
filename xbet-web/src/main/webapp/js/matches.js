function isUserLoggedIn(formId) {
    $.ajax({
        url: "<c:url value="/main/check"/>",
        type: "POST",
        success: function (data, textStatus, request) {
            var result = request.getResponseHeader('result');
            if (result !== null && result !== "") {
                if (result === "SUCCESS") {
                    makeBet(formId);
                } else {
                    alert("<c:out value="${matchesLogIn}"/>");
                }
            } else {
                alert("<c:out value="${loginError}"/>");
            }
        }
    });
}

function makeBet(formId) {
    var form = document.getElementById(formId);
    form.action = "<c:url value="/main/place"/>";
    form.submit();
}