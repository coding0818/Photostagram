
let isEmailAuthOk = false;
let emailCode = null;

// 페이지 로드 완료시 이메일 보내기
window.addEventListener('DOMContentLoaded', function() {

    let userData = sessionStorage.getItem("user");
    let user = JSON.parse(userData);
    console.log(user);

    let email = user.email;
    let jsonData = {"email" : email};
    console.log(jsonData);

    $.ajax({
        url: '/Photostagram/member/sendEmail',
        method: 'POST',
        data: jsonData,
        dataType: 'json',
        success: function(data) {
            if (data.result == 1) {
                alert('이메일 코드 전송 성공!')
                emailCode = data.confirm;
                console.log("emailCodeAjax = " + emailCode);
            } else {
                alert('이메일 코드 전송 실패!')
            }
        }
    });

    // 이메일 인증 코드 입력

})
console.log("emailCode = " + emailCode);





