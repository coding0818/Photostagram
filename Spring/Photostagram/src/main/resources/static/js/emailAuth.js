let isEmailAuthOk = false;
let emailCode = null;

// 페이지 로드 완료시 이메일 보내기
window.addEventListener('DOMContentLoaded', function() {
    let userData = sessionStorage.getItem("user");
    let user = JSON.parse(userData);
    console.log(user);

    let email = user.email;

    // 화면에 이메일 띄우기
    document.getElementById('userEmail').innerHTML = email;

    let jsonData = {"email" : email};
    console.log(jsonData);

    $.ajax({
        url: '/Photostagram/member/sendEmail',
        method: 'POST',
        async: false, // 비동기방식을 끈다. (ajax를 수행 후 다음 함수가 실행)
        data: jsonData,
        dataType: 'json',
        success: function(data) {
            if (data.result == 1) {
                emailCode = data.confirm;
                console.log("emailCodeAjax = " + emailCode);
            } else {
                alert('이메일 코드 전송 실패!')
            }
        }
    });
})

// email code 입력
$(function(){
    $('#email-next').click(function(e){
        e.preventDefault();
        let inputCode = $('input[name="input-code"]').val();
//        console.log("inputCode = " + inputCode);
        if (inputCode == emailCode) {
            alert('이메일 인증이 완료되었습니다.');
            location.href = '/Photostagram/member/terms';
        } else if (inputCode == "") {
            alert('인증번호를 입력하여주세요.');
        } else {
            alert('인증번호가 일치하지 않습니다. 다시 확인해주세요.');
        }
    });
});






