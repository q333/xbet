var isLoginExists = false;
var isPasswordCorrect = false;

function checkLogin() {
    var login = $("#login").val();
    if (login === "") {
        $('#loginDiv').css("display", "none");
        alert("<c:out value="${loginRequired}"/>");
        isLoginExists = false;
        return;
    }
    $.ajax({
        url: "<c:url value="/main/authenticate"/>",
        type: "POST",
        data: {
            key: login
        },
        success: function (data, textStatus, request) {
            var result = request.getResponseHeader('result');
            if (result !== null && result !== "") {
                loginMessage(result);
                $('#loginDiv').css("display", "block");
            } else {
                $('#loginDiv').css("display", "none");
                $('#loginDiv').html("");
                alert("<c:out value="${loginError}"/>");
                isLoginExists = false;
            }
        }
    });
}

function loginMessage(result) {
    if (result === 'SUCCESS') {
        $('#loginDiv').html("<p style='color: #4cae4c'><c:out value="${loginCorrect}"/></p>");
        isLoginExists = true;
    } else if (result === 'FAILURE') {
        $('#loginDiv').html("<p style='color: red'><c:out value="${loginIncorrect}"/></p>");
        isLoginExists = false;
    }
}

function checkPassword() {
    var login = $("#login").val();
    var password = $("#password").val();
    var regExp = new RegExp("^[A-Z](?=.*[a-z])(?=.*[0-9])(?=.{7,})");
    if (login === "") {
        $('#passwordDiv').css("display", "block");
        passwordMessage('EMPTY_USER');
    } else if (password === "") {
        $('#passwordDiv').css("display", "none");
        alert("<c:out value="${passwordRequired}"/>");
        isPasswordCorrect = false;
        return;
    } else if (password.trim() === "") {
        passwordMessage("INCORRECT");
    } else if (!regExp.test(password)) {
        passwordMessage("REGEXP");
    } else {
        passwordMessage("SUCCESS");
    }
}

function passwordMessage(result) {
    if (result === 'SUCCESS') {
        $('#passwordDiv').html("<p style='color: #4cae4c'><c:out value="${passwordValid}"/></p>");
        $('#passwordDiv').css("display", "block");
        isPasswordCorrect = true;
    } else if (result === 'INCORRECT') {
        $('#passwordDiv').html("<p style='color: red'><c:out value="${passwordWhitespace}"/></p>");
        $('#passwordDiv').css("display", "block");
        isPasswordCorrect = false;
    } else if (result === 'REGEXP') {
        $('#passwordDiv').html("<p style='color: red'><c:out value="${passwordRegexp}"/></p>");
        $('#passwordDiv').css("display", "block");
        isPasswordCorrect = false;
    } else if (result === 'EMPTY_USER') {
        $('#passwordDiv').html("<p style='color: red'><c:out value="${loginRequired}"/></p>");
        isPasswordCorrect = false;
    }
}

function changeFormAction() {
    var result = isLoginExists && isPasswordCorrect;
    if (result) {
        var form = document.getElementById('checkout-form');
        form.action = "<c:url value="/main/login"/>";
        form.submit();
    } else {
        alert("<c:out value="${data}"/>");
    }
}
