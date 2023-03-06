$(function () {
  let modalPost = $(".modal-post");
  let PostMenu = $(".post-menu");
  /**
   * 댓글 더보기 누를 경우 Post-Modal 뜸.
   */
  $(".commentAll").on("click", function () {
    modalPost.css("display", "flex");
    $("body").css("overflow-y", "hidden");
  });

  $(".post-close").on("click", function () {
    modalPost.css("display", "none");
    $("body").css("overflow-y", "visible");
  });

  $('.sprite_more_icon').on("click", function(){
    PostMenu.css("display", " flex");
    $("body").css("overflow-y", "hidden");
  })

  $('.postmenu-cancel').on("click", function(){
    PostMenu.css("display", "none");
    $("body").css("overflow-y", "visible");
  })
  
});
