$(function () {
  // 댓글 모두보기
  $(".commentMore").on("click", function () {
    let article = $(this).closest("article");
    let modalPost = article.find(".modal-post");
    let imgsection = article.find(".img_section");
    let mainPostBx = imgsection.find(".bx-controls-direction");
    let modalPostBx = modalPost.find(".bx-controls-direction");

    modalPost.css("display", "flex");
    mainPostBx.css("display", "none");
    modalPostBx.css("display", "flex");
    $("body").css("overflow-y", "hidden");
  });

  // 버블 아이콘
  $(".sprite_bubble_icon").on("click", function () {
    let article = $(this).closest("article");
    let modalPost = article.find(".modal-post");
    let imgsection = article.find(".img_section");
    let mainPostBx = imgsection.find(".bx-controls-direction");
    let modalPostBx = modalPost.find(".bx-controls-direction");

    modalPost.css("display", "flex");
    mainPostBx.css("display", "none");
    modalPostBx.css("display", "flex");
    $("body").css("overflow-y", "hidden");
  });

  // 모달 post 닫기
  $(".post-close").on("click", function () {
    let article = $(this).closest("article");
    let modalPost = article.find(".modal-post");
    let bxControll = article.find(".bx-controls-direction");

    modalPost.css("display", "none");
    bxControll.css("display", "flex");
    $("body").css("overflow-y", "visible");
  });

  // 게시글 토글버튼
  $(".sprite_more_icon").on("click", function () {
    let article = $(this).closest("article");
    let PostMenu = article.find(".post-menu");
    let bxControll = article.find(".bx-controls-direction");

    PostMenu.css("display", " flex");
    bxControll.css("display", "none");
    $("body").css("overflow-y", "hidden");
  });

  // 게시글 토글버튼 취소클릭
  $(".postmenu-cancel").on("click", function () {
    let article = $(this).closest("article");
    let PostMenu = article.find(".post-menu");
    let bxControll = article.find(".bx-controls-direction");

    PostMenu.css("display", "none");
    bxControll.css("display", "flex");
    $("body").css("overflow-y", "visible");
  });

  // (1)댓글 Modal Open
  /*
  $(".comment_option").on("click", function () {
    let divtop = $(this).closest("div.top");
    let optionOpen = divtop.find("#modal_postSelect");
    optionOpen.show();

    let confirm_delete = divtop.find(".modal_postDelete");
    if (confirm_delete.click()) {
      alert("ㅋ르릭");
    }
  });

  // (1)에서 삭제버튼 클릭 시
  // $(".modal_postDelete").click(function () {
  //   let divtop = $(this).closest("div.top");
  //   divtop.find("#modal_postSelect").hide();

  //   // (2)Confirm Modal Show
  //   divtop.find("#modal_postSelect_delete").show();
  // });

  // modal 취소 관련
  $(".modal_postCancel").click(() => {
    let divtop = $(this).closest("div.top");
    divtop.find("#modal_postSelect").hide();
  });

  $(".post_delete_cancel").click(() => {
    let divtop = $(this).closest("div.top");
    divtop.find("#modal_postSelect_delete").hide();
  });
  */
});
