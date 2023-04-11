$(function(){
    // modal 팝업 띄우기
    let modal = $('#modal_postSelect');
    let modal_delete = $('#modal_postSelect_delete');
    let modal_modify = $('#modal_modify_feed_content');

    $('.postSelect').on('click', function(){
        modal.css('top', $(window).scrollTop()+'px');
        modal.css('display', 'flex');
        $('body').css('overflow-y', 'hidden');
    });

    $('.modal_postCancel').on('click', function(){
        modal.css('display', 'none');
        $('body').css('overflow-y', 'visible');
    });

    $(document).on('click', '.modal_postDelete', function(){
        modal.css('display', 'none');
        modal_delete.css('display', 'flex');
    });

    $(document).on('click', '.post_delete_cancel', function(){
        modal_delete.css('display', 'none');
        $('body').css('overflow-y', 'visible');
    });

    $(document).on('click', '.modal_title_side_post_cancel', function(){
        modal_modify.css('display', 'none');
    });
});