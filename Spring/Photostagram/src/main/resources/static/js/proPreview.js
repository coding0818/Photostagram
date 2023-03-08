
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
         const chan = document.querySelector('.changeProf');

         fake.addEventListener('click', () => real.click());
         chan.addEventListener('click', () => real.click());
         real.addEventListener('change', e => {
             getProfile(e.target);
     });

});


