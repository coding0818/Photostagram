/**
 *  날짜 : 2023 / 02 / 28
 *  이름 : 조광호
 */

$(function () {
  let modalPost = $(".modal-post");
  let PostMenu = $(".post-menu");
  /**
   * 댓글 모두보기 누를 경우 Post-Modal 뜸.
   */
  $(".commentAll").on("click", function () {
    modalPost.css("display", "flex");
    $("body").css("overflow-y", "hidden");
  });

  $(".post-close").on("click", function () {
    modalPost.css("display", "none");
    $("body").css("overflow-y", "visible");
  });

  $(".sprite_more_icon").on("click", function () {
    PostMenu.css("display", " flex");
    $("body").css("overflow-y", "hidden");
  });

  $(".postmenu-cancel").on("click", function () {
    PostMenu.css("display", "none");
    $("body").css("overflow-y", "visible");
  });

  // 메인콘텐츠 더보기 누를 경우
  //  $(".contents").each(function () {
  //    let wordArticle = $(".commentWord");
  //    let word = wordArticle.text().trim(); // 게시하면서 작성자가 남긴 문구 불러오기
  //    let word_short = word.substring(0, 10) + "...";
  //    let btnMore = $(
  //      '<a href="javascript:void(0)" class="ContentsMore">더보기</a>'
  //    );
  //
  //    $(this).children(".comment_container").children(".comment").append(btnMore);
  //
  //    // 남긴 글이 10글자 이상인 경우
  //    if (word.length >= 10) {
  //      // 10글자만 남긴다
  //      wordArticle.html(word_short);
  //    } else {
  //      btnMore.hide();
  //    }
  //
  //    btnMore.click(toggle_content);
  //
  //    function toggle_content() {
  //      if ($(".ContentsMore").hasClass("short")) {
  //        // 접기 상태
  //        $(".ContentsMore").html("더보기");
  //        wordArticle.html(word_short);
  //        $(".ContentsMore").removeClass("short");
  //      } else {
  //        // 더보기 상태
  //        $(".ContentsMore").html("접기");
  //        wordArticle.html(word);
  //        $(".ContentsMore").addClass("short");
  //      }
  //    }
  //
  //  });

  // 댓글 작성
  $(document).on("click",".upload_btn", function(e) {
    e.preventDefault();

    let article    = $(this).closest('article')
    let div        = $(this).parent();
    let input      = div.children();

    let uid     = input.eq(0).val();
    let post_no = input.eq(1).val();
    let user_no = input.eq(2).val();
    let comment = input.eq(3).val();
    let url = "/Photostagram/CmtRegister";

    console.log("uid : " + uid)
    console.log("post_no : " + post_no)
    console.log("user_no : " + user_no)
    console.log("comment : " + comment)

    let jsonData = {
      uid: uid,
      post_no: post_no,
      user_no: user_no,
      comment: comment
    };

    $.ajax({
      url: url,
      method: "post",
      data: JSON.stringify(jsonData),
      contentType: "application/json",
      dataType: "json",
      success: (data) => {
        if (data.result > 0) {
          
          let str = "<div class='reply_user'>";
          str += "<span class='reply_nick'>" + uid + "</span>";
          str += "<span class='reply_content' style='margin-left:2px;'>" + comment + "</span>";
          str += "<span class='small_heart'>";
          str += "<div class=sprite_small_heart_icon_outline></div>";
          str += "</span>";
          str += "</div>";

          article.find('.comment_container').append(str);
          article.find('.commentText').val('');
          //$(".comment_container").append(str);
        } else {
          alert("작성 실패");
        }
      }
    });
  });

  /*
    게시글 좋아요 (빈하트 -52px -258px 꽉찬하트 -26px -258px;)
    댓글 좋아요 (빈하트 -323px -274px 꽉찬하트  -323px -287px)
  */
  $(document).on("click", ".sprite_heart_icon_outline", function(e){
    e.preventDefault();
    let article    = $(this).closest('article'); 
    let post_no    = article.attr('data-no'); // 게시글 번호
    let user_no    = $(this).attr('data-no'); // 유저 번호
    let url        = "/Photostagram/ArticleLikeAdd";

    let jsonData = {
      "no":post_no,
      "user_no":user_no
    };

    $.ajax({
      url:url,
      method:'POST',
      data:JSON.stringify(jsonData),
      contentType: "application/json",
      dataType:'json',
      success: (data)=>{
        if(data.result > 0){
          if(article.find('.artLike').hasClass('sprite_heart_icon_outline')){
      
            article.find('.artLike')
            .removeClass('sprite_heart_icon_outline')
            .addClass('sprite_full_heart_icon_outline');
      
          }
        }
      }
    })
  })

  $(document).on("click", ".sprite_full_heart_icon_outline", function(e){
      e.preventDefault();

      let article    = $(this).closest('article');
      let post_no    = article.attr('data-no'); // 게시글 번호
      let user_no    = $(this).attr('data-no'); // 유저 번호
      let url        = "/Photostagram/DeleteArticleLike";

      let jsonData = {
        "no":post_no,
        "user_no":user_no
      };

      $.ajax({
        url:url,
        method:'POST',
        data:JSON.stringify(jsonData),
        contentType: "application/json",
        dataType:'json',
        success: (data)=>{
          if(data.result > 0){
            if(article.find('.artLike').hasClass('sprite_full_heart_icon_outline')){
      
              article.find('.artLike')
              .removeClass('sprite_full_heart_icon_outline')
              .addClass('sprite_heart_icon_outline');
        
            }
          }
        }
      })
    })
});
