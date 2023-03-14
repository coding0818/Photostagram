

/***  프로필 사진 미리보기  ***/

$(function(){

    function getProfile(input){
         if(input.files && input.files[0]) {
                 const reader = new FileReader()

                 reader.onload = e => {
                     const preview = document.querySelector('.addProf');
                     preview.src = e.target.result
                 }

                 reader.readAsDataURL(input.files[0])
             }
         }

         const real = document.querySelector('.real-addProf');
         const fake = document.querySelector('.addProf');

         if ($('#profilePhoto').hasClass('changeProf') == true){
            const chan = document.querySelector('.changeProf');
            chan.addEventListener('click', () => real.click());
         }

         fake.addEventListener('click', () => real.click());
         real.addEventListener('change', e => {
             getProfile(e.target);
     });

});


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
                    alert ('프로필 업로드가 완료되었습니다.');
                    location.reload();
                }
            }
        });

    });

});
