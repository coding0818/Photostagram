$(function(){
    // modal 팝업 띄우기
    let modal = $('#modal_postSelect');

    $('.postSelect').on('click', function(){
        modal.css('top', $(window).scrollTop()+'px');
        modal.css('display', 'flex');
    });
});