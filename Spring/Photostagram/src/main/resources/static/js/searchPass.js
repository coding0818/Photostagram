/*
    이름 : 김진우
    날짜 : 2023/03/15
    내용 : 비밀번호 찾기 js
*/

$(function(){
    let regUserName = /^(?=.*[a-z0-9])[a-z0-9]{5,19}$/; // 영어 소문자 또는 숫자 하나 이상 포함.
    let regName  = /^[가-힣]{2,15}$/; // 한글
    let regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; // 이메일

    $('#checkPass-next').click(function(e){
        e.preventDefault();

        let userName = $('input[name=userName]').val();
        let name = $('input[name=name]').val();
        let email = $('input[name=email]').val();
        console.log('userName : ' + userName);
        console.log('name : ' + name);
        console.log('email : ' + email);

        if(userName == "") { // 아이디가 공백일 때
            $('.check-txt').removeClass('on');
            $('.check-txt.nId').addClass('on');
            return false;
        }else if(name == "") {
            $('.check-txt').removeClass('on');
            $('.check-txt.nName').addClass('on');
            return false;
        } else if (email == "") {
            $('.check-txt').removeClass('on');
            $('.check-txt.nEmail').addClass('on');
            return false;
        } else if (!userName.match(regUserName)) {
            $('.check-txt').removeClass('on');
            $('.check-txt.id').addClass('on');
            return false;
        } else if (!name.match(regName)) {
            $('.check-txt').removeClass('on');
            $('.check-txt.name').addClass('on');
            return false;
        } else if (!email.match(regEmail)) {
            $('.check-txt').removeClass('on');
            $('.check-txt.email').addClass('on');
            return false;
        }

        let jsonData = {
                "userName" : userName,
                "name" : name,
                "email" : email
                };

        $.ajax({
            url: '/Photostagram/member/searchPass',
            method: 'POST',
            data: jsonData,
            dataType: 'json',
            success: function(data) {
                if(data.result == 1) {
                    alert('회원 찾음!');
                } else {
                    alert('회원 없음!');
                    return false;
                }
            }
        });


    });

});