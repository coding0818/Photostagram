
  /*** 프로필 팔로잉 클릭 시 뜨는 모달 창 ***/

    $(function(){
      var modal = document.getElementById("fCancel");
      var btn = document.getElementById("following");
      var span = document.getElementsByClassName("fClose")[0];

      btn.onclick = function() {
        modal.style.display = "block";
      }

      span.onclick = function() {
        modal.style.display = "none";
      }

      window.onclick = function(event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      }
    });


    /*** 팔로워 모달창 ***/

    $(function(){
      var modal = document.getElementById("followerModalWindow");
      var btn = document.getElementById("followerModal");
      var span = document.getElementsByClassName("closeFollow")[0];

      btn.onclick = function() {
        modal.style.display = "block";
      }

      span.onclick = function() {
        modal.style.display = "none";
      }

      window.onclick = function(event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      }

     $('#followerModal').click(function(e){
        e.preventDefault();
     });

    });

    /*** 팔로잉 모달창 ***/

    $(function(){
      var modal = document.getElementById("followingModalWindow");
      var btn = document.getElementById("followingModal");
      var span = document.getElementsByClassName("closeFollowing")[0];

      btn.onclick = function() {
        modal.style.display = "block";
      }

      span.onclick = function() {
        modal.style.display = "none";
      }

      window.onclick = function(event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      }

     $('#followingModal').click(function(e){
        e.preventDefault();
     });

    });


    /*** 프로필 사진 클릭 시 뜨는 모달 창 ***/

    $(function(){
      var modal = document.getElementById("photoModal");
      const btn = document.querySelector(".addNewProf");
      const close = document.querySelector(".photoClose");
      const del = document.querySelector(".deleteProf");

      btn.onclick = function() {
        modal.style.display = "block";
      }

      close.onclick = function() {
        modal.style.display = "none";
      }

      window.onclick = function(event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      }

      del.onclick = function() {
        modal.style.display = "none";
        let spinner = $('.profileSpinner');

        spinner.show();
        setTimeout(function(){
            $.ajax({
                url: '/Photostagram/profile/upload',
                type: 'get',
                dataType: 'json',
                data: {"type":"delete"},
                success: function(data){
                    if(data.result > 0){
                        spinner.hide();
                        alert('프로필 삭제가 완료되었습니다.');
                        location.reload();
                    }
                }
            });
        });


      }

    });


    /*** 내 팔로워 중 삭제를 클릭하면 뜨는 모달 창 ***/

