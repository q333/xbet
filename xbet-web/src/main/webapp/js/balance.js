function checkAmountInput() {
    var amount = $("#balanceInput").val();
    if (amount === "") {
        alert("<c:out value="${betAmount}"/>");
    } else if (amount <= 0) {
        alert("<c:out value="${amountNegative}"/>");
    } else {
        var form = document.getElementById('balanceForm');
        form.action = "<c:url value="/main/topup"/>";
        form.submit();
    }
}