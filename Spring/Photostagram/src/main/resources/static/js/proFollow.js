
 /*** 팔로우, 언팔로우 시 스타일 변화 ***/

  $(function(){
    $('#follow').click(function(){
      $('#follow').hide();
      $('#following').show();
    });
  });
  $(function(){
    $('#following').click(function(){
      $('#following').hide();
      $('#follow').show();
    });
  });


  /*** 팔로잉 클릭 시 뜨는 모달 창 ***/

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