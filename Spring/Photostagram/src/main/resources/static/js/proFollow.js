
 /*** 팔로우, 언팔로우 시 스타일 변화 ***/

      $(function(){
        $('#follow').click(function(){
          $('#follow').hide();
          $('#following').show();
        });
      });
      $(function(){
        $('#unFollow').click(function(){
          $('#following').hide();
          $('#follow').show();
        });
       });
      $(function(){
        $('.fClose').click(function(){
          $('#fCancel').hide();
        });
      });


      $(function(){
        $('#postFollow').click(function(){
            $('#postFollow').hide();
            $('#postFollowing').show();
        });
      });
      $(function(){
          $('#postUnFollow').click(function(){
            $('#postFollowing').hide();
            $('#postFollow').show();
          });
       });
      $(function(){
        $('.pClose').click(function(){
          $('#pCancel').hide();
        });
      });


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

    /*** 게시물 팔로잉 클릭 시 뜨는 모달 창 ***/

    $(function(){
      var modal = document.getElementById("pCancel");
      var btn = document.getElementById("postFollowing");

      btn.onclick = function() {
        modal.style.display = "block";
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