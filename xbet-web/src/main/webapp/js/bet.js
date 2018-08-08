function checkAmount() {
    var amount = $("#input").val();
    if (amount === "") {
        alert("<c:out value="${betAmount}"/>");
    } else if (amount <= 0) {
        alert("<c:out value="${amountNegative}"/>");
    } else {
        $.ajax({
            url: "<c:url value="/main/balance"/>",
            type: "POST",
            data: {
                key: amount
            },
            success: function (data, textStatus, request) {
                var result = request.getResponseHeader('result');
                if (result !== null && result !== "") {
                    if (result === "SUCCESS") {
                        var form = document.getElementById('checkoutForm');
                        form.action = "<c:url value="/main/bet"/>";
                        form.submit();
                    } else if (result === "NOT_ENOUGH") {
                        alert("<c:out value="${balanceNotEnough}"/>");
                    }
                } else {
                    alert("<c:out value="${balanceError}"/>");
                }
            }
        });
    }
}