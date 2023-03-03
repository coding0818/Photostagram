$(function () {
  let modalPost = $(".modal-post");

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
});
