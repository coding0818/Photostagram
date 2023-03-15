/*
    이름 : 김진우
    날짜 : 2023/03/14
    내용 : 아이디, 비밀번호 찾기 js
*/

$(function(){
    let regName  = /^[가-힣]{2,15}$/; // 한글
    let regPassword  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/; // 8자 이상, 영문, 숫자, 특수문자

    $('#checkIdCode').click(function(e){
        e.preventDefault();

//        alert('here!!!');
        let name = $('.checkId.name').val();
        let email = $('.checkId.email').val();
//        console.log('name = ' + name);
//        console.log('email = ' + email);

        // 유효성 검사
        if(name == "") {
            $('.check-txt').removeClass('on');
            $('.check-txt.nName').addClass('on');
            return false;
        } else if (email == "") {
            $('.check-txt').removeClass('on');
            $('.check-txt.nEmail').addClass('on');
            return false;
        } else if (!name.match(regName)) {
            $('.check-txt').removeClass('on');
            $('.check-txt.name').addClass('on');
            return false;
        }else if (!name.match(regName)) {
            $('.check-txt').removeClass('on');
            $('.check-txt.email').addClass('on');
            return false;
        }

        $.ajax({
            url: '/Photostagram/member/',
        });


    });
});