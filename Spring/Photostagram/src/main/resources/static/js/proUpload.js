
/***  프로필 이미지 저장 ***/

$(function(){

    $('.real-addProf').change(function(){
        var file = $(this)[0].files[0];
        var formData = new FormData();
        formData.append("file", file);

        $.ajax({
            type: 'post',
            url: '/Photostagram/profile/upload',
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'json',
            success: function(data){
                if (data.result > 0) {
                    alert ('업로드 완료');
                }
            }
        });

    });

});
