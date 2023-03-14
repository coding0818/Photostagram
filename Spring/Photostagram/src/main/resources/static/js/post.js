  $(function() {

    /*** 게시글 좋아요 ***/
    $('#like').click(function(){
      if($('#like').hasClass('unlike')){
        $('#like').removeClass('unlike').addClass('like');
      } else {
        $('#like').removeClass('like').addClass('unlike');
      }
    });

    /*** 게시글 북마크 ***/
    $('#bookMark').click(function(){
      if($('#bookMark').hasClass('remove')){
        $('#bookMark').removeClass('remove').addClass('mark');
      } else {
        $('#bookMark').removeClass('mark').addClass('remove');
      }
    });

    /*** 댓글 좋아요 ***/
    $('#reHeart').click(function(){
      if($('#reHeart').hasClass('reWhite')){
        $('#reHeart').removeClass('reWhite').addClass('reRed');
      } else {
        $('#reHeart').removeClass('reRed').addClass('reWhite');
      }
    });

    /*** 댓글 접기, 펴기 ***/
    $('.reOpen').click(function(e){
      e.preventDefault();

      let items = $(this).parent().find('.reHide');

      if (items.is(":visible")){
        items.hide();
      } else {
        items.show();
      }

    });

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
