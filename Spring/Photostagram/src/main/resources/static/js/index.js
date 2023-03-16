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
  $(".commentMore").on("click", function () {
    modalPost.css("display", "flex");
    $("body").css("overflow-y", "hidden");
  });
  $(".sprite_bubble_icon").on("click", function () {
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

  //메인콘텐츠 더보기 누를 경우
  $(".contents").each(function () {
    let article = $(this).closest('article');
    let wordArticle = article.find('.commentWord');
    let word = wordArticle.text().trim(); // 게시하면서 작성자가 남긴 문구 불러오기
    let word_short = word.substring(0, 10) + "...";
    let btnMore = $(
      '<a href="javascript:void(0)" class="ContentsMore">더보기</a>'
    );
    
    article.find('.comment').append(btnMore);
    // $(this).children(".comment_container").children(".comment").append(btnMore);

    // 남긴 글이 10글자 이상인 경우
    if (word.length >= 10) {
      // 10글자만 남긴다
      wordArticle.html(word_short);
    } else {
      btnMore.hide();
    }

    btnMore.click(toggle_content);

    function toggle_content() {
      if (article.find('.ContentsMore').hasClass("short")) {
        // 접기 상태
        article.find('.ContentsMore').html("더보기");
        wordArticle.html(word_short);
        article.find('.ContentsMore').removeClass("short");
      } else {
        // 더보기 상태
        article.find('.ContentsMore').html("접기");
        wordArticle.html(word);
        article.find('.ContentsMore').addClass("short");
      }
    }

    article.find('.img_section').bxSlider({});
  });


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

    
    if(comment == ""){
        alert('댓글을 입력하세요.')
        return false;
    }

    $.ajax({
      url: url,
      method: "post",
      data: JSON.stringify(jsonData),
      contentType: "application/json",
      dataType: "json",
      success: (data) => {

        if (data.result > 0) {



          let str = "<div class='reply_user' data-no='"+data.no+"'>";
          str += "<input type='hidden' class='reply_no' value='"+data.no+"'>";
          str += "<span class='reply_nick'>" + uid + "</span>";
          str += "<span class='reply_content' style='margin-left:4px;'>" + comment + "</span>";
          str += "<div class='comLike sprite_small_heart_icon_outline' data-no='"+data.user_no+"'></div>";
          str += "</div>";

          article.find('.comment_container').append(str);
          article.find('.commentText').val('');

          let count = article.find('#comment-count').text(); // 현재 태그사이 텍스트받고
          let commentCount = parseInt(count); // 문자열이라 더하기가 안되기때문에 parseInt

          article.find('#comment-count').text(commentCount+1); // 해당 텍스트에 +1

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
      
            let textCount = article.find('#like-count').text();
            let count = parseInt(textCount);

            article.find('#like-count').text(count+1);
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

              let textCount = article.find('#like-count').text();
              let count = parseInt(textCount);

              article.find('#like-count').text(count-1);
            }
          }
        }
      })
    })

  $(document).on("click", ".sprite_small_heart_icon_outline", function(e){
    e.preventDefault();

    let article    = $(this).closest('article');
    let user_no    = $(this).attr('data-no'); // 유저 번호
    let comment_no = article.find($(this).parent()).attr('data-no'); // 댓글번호
    let url        = "/Photostagram/CommentLikeAdd";

    console.log("user_no : " + user_no);
    console.log("comment_no : " + comment_no);

    let jsonData = {
      "user_no":user_no,
      "comment_no":comment_no
    };

    $.ajax({
      url:url,
      method:'POST',
      data:JSON.stringify(jsonData),
      contentType: "application/json",
      dataType:'json',
      success: (data)=>{
        if(data.result > 0){
          if(article.find($(this)).hasClass('sprite_small_heart_icon_outline')){
      
            article.find($(this))
            .removeClass('sprite_small_heart_icon_outline')
            .addClass('sprite_full_small_heart_icon_outline');


          }
        }
      }
    })  

  })
  
  $(document).on("click", ".sprite_full_small_heart_icon_outline", function(e){
    e.preventDefault();

    let article    = $(this).closest('article'); 
    let user_no    = $(this).attr('data-no'); // 유저 번호
    let comment_no = article.find('.reply_no').val(); // 댓글번호
    let url        = "/Photostagram/CommentLikeDel";

    console.log("user_no : " + user_no);
    console.log("comment_no : " + comment_no);

    let jsonData = {
      "user_no":user_no,
      "comment_no":comment_no
    };

    $.ajax({
      url:url,
      method:'POST',
      data:JSON.stringify(jsonData),
      contentType: "application/json",
      dataType:'json',
      success: (data)=>{
        if(data.result > 0){
          if(article.find('.comLike').hasClass('sprite_full_small_heart_icon_outline')){
      
            article.find('.comLike')
            .removeClass('sprite_full_small_heart_icon_outline')
            .addClass('sprite_small_heart_icon_outline');
      
          }
        }
      }
    })  

  })

});
