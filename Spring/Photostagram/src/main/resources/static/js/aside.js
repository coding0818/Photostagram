function dragOver(e){
    e.stopPropagation();
    e.preventDefault();
    
    if (e.type == "dragover") {
        $(e.target).css({
            "background-color": "black",
            "outline-offset": "-20px"
        });
    } else {
        $(e.target).css({
            "background-color": "white",
            "outline-offset": "-10px"
        });
    }
}

function uploadFiles(e){
    e.stopPropagation();
    e.preventDefault();
    console.log(e.dataTransfer)
    console.log(e.originalEvent.dataTransfer)

    e.dataTransfer = e.originalEvent.dataTransfer;

    files = e.dataTransfer.files;

    if (files[0].type.match(/image.*/)) {
        $('#modal_add_feed_content_drop').css({
            display : 'flex'
        });
        $('#input_image_drop').css({
            "background-image": "url(" + window.URL.createObjectURL(files[0]) + ")",
            "outline": "none",
            "background-size": "contain",
            "background-repeat" : "no-repeat",
            "background-position" : "center"
        });
        $('#modal_add_feed').css({
            display: 'none'
        })
    }else{
        alert('이미지가 아닙니다.');
        return;
    }
}

  $(function(){

      $('#search').on('click',function(){
          $('.sidesearch').toggleClass('on');
          $('.side').toggleClass('on');              
      });

      $('#search_close').on('click', function(){
        $('.sidesearch').toggleClass('on');
        $('.side').toggleClass('on');
      });

      $('#alarm').on('click',function(){
          $('.sidealarm').toggleClass('on');
          $('.side').toggleClass('on');              
      });

      $('#alarm_close').on('click',function(){
        $('.sidealarm').toggleClass('on');
        $('.side').toggleClass('on');              
      });

      let modal = $('#modal_add_feed');
      let modal_feed = $('#modal_add_feed_content');
      let modal_feed_drop = $('#modal_add_feed_content_drop');

      $('#add_feed').on('click', function(){
          modal.css('top', $(window).scrollTop()+'px');
          modal.css('display', 'flex');
          $('body').css('overflow-y', 'hidden');
      });

      $('#close_modal').on('click', function(){
        modal.css('display', 'none');
        $('body').css('overflow-y', 'visible');
      });

      $('#close_modal_feed').on('click', function(){
        modal_feed.css('display', 'none');
        $('body').css('overflow-y', 'visible');
      });

      $('#close_modal_feed_drop').on('click', function(){
        modal_feed_drop.css('display', 'none');
        $('body').css('overflow-y', 'visible');
      });
      
      $('.modal_image_upload')
        .on("dragover", dragOver)
        .on("dragleave", dragOver)
        .on("drop", uploadFiles);

      $('#file_upload').on('change', function(){
          //alert('file_upload!');

          let file = this.files;
          console.log('file : ' +file);
          let dataTransfer = new DataTransfer();

          let fileArray = Array.from(file);
          console.log('fileArray : ' +fileArray);
          
          fileArray.forEach(file => {
              dataTransfer.items.add(file);
          });

          let images= dataTransfer.files;
          console.log('images : '+images);

          if (images[0].type.match(/image.*/)) {
              $('#modal_add_feed_content').css({
                  display : 'flex'
              });
              $('.modal_image_upload_content').css({
                "background-image": "url(" + window.URL.createObjectURL(images[0]) + ")",
                "outline": "none",
                "background-size": "contain",
                "background-repeat" : "no-repeat",
                "background-position" : "center"
              });                  
              $('#modal_add_feed').css({
                  display: 'none'
              });

          }else{
              alert('이미지가 아닙니다.');
              return;
          }
      });
    
      

    let j = 0;

    $('#afterBtn').on('click', function(){
        let inputFile = $('#file_upload');
        let file = inputFile[0].files;

        console.log(file);

        let dataTransfer = new DataTransfer();

        let fileArray = Array.from(file);
        console.log('fileArray : ' +fileArray);

        fileArray.forEach(f => {
            dataTransfer.items.add(f);
        });

        let images= dataTransfer.files;
        console.log('images : '+images);

        if(j+1 == images.length) return;
        j++;

        console.log("j1 :"+j);

        $('.modal_image_upload_content').css({
            "background-image": "url(" + window.URL.createObjectURL(images[j]) + ")",
            "outline": "none",
            "background-size": "contain",
            "background-repeat" : "no-repeat",
            "background-position" : "center"
        }); 
        console.log("j2 :"+j);
    });

    $('#beforeBtn').on('click', function(){
        let inputFile = $('#file_upload');
        let file = inputFile[0].files;

        console.log(file);

        let dataTransfer = new DataTransfer();

        let fileArray = Array.from(file);
        console.log('fileArray : ' +fileArray);

        fileArray.forEach(f => {
            dataTransfer.items.add(f);
        });

        let images= dataTransfer.files;
        console.log('images : '+images);

        if(j == 0)return;
        j--;

        $('.modal_image_upload_content').css({
            "background-image": "url(" + window.URL.createObjectURL(images[j]) + ")",
            "outline": "none",
            "background-size": "contain",
            "background-repeat" : "no-repeat",
            "background-position" : "center"
        }); 
        console.log("j"+j); 
        
    });

    $('#afterBtnDrop').on('click', function(){
        let file = files;

        console.log(file);

        let dataTransfer = new DataTransfer();

        let fileArray = Array.from(file);
        console.log('fileArray : ' +fileArray);

        fileArray.forEach(f => {
            dataTransfer.items.add(f);
        });

        let images= dataTransfer.files;
        console.log('images : '+images);

        if(j+1 == images.length) return;
        j++;

        console.log("j1 :"+j);

        $('.modal_image_upload_content').css({
            "background-image": "url(" + window.URL.createObjectURL(images[j]) + ")",
            "outline": "none",
            "background-size": "contain",
            "background-repeat" : "no-repeat",
            "background-position" : "center"
        });
        console.log("j2 :"+j);
    });

    $('#beforeBtnDrop').on('click', function(){
        let file = files;

        console.log(file);

        let dataTransfer = new DataTransfer();

        let fileArray = Array.from(file);
        console.log('fileArray : ' +fileArray);

        fileArray.forEach(f => {
            dataTransfer.items.add(f);
        });

        let images= dataTransfer.files;
        console.log('images : '+images);

        if(j == 0)return;
        j--;

        $('.modal_image_upload_content').css({
            "background-image": "url(" + window.URL.createObjectURL(images[j]) + ")",
            "outline": "none",
            "background-size": "contain",
            "background-repeat" : "no-repeat",
            "background-position" : "center"
        });
        console.log("j"+j);
    });

    $('#more_view').on('click', function(){
        $('.more_logout').toggleClass('on');
    });

    //게시글 업로드 버튼 클릭시
    $('#button_write_feed').on('click', ()=>{
      let inputFile = $('#file_upload');
      let files = inputFile[0].files;

      console.log(files);

      let dataTransfer = new DataTransfer();
      let fileArray = Array.from(files);
      console.log('fileArray : ' +fileArray);

      fileArray.forEach(f => {
          dataTransfer.items.add(f);
      });

      let images= dataTransfer.files;
      let urls = [];
      for(i=0; i<images.length; i++){
        let url = window.URL.createObjectURL(images[i]);
        urls.push(url);
      }
      console.log('urls : ' +urls);

      let content = $('#input_content').val();
      let user_no = $('#input_user_id').data('no');
      console.log('content : '+content);
      console.log('user_no : '+user_no);

      let formData = new FormData();
      for(j=0; j<images.length; j++){
        formData.append('files', files[j]);
      }
      formData.append('content', content);
      formData.append('user_no', user_no);

      $.ajax({
        url:'/Photostagram/postUpload',
        processData: false,
        contentType: false,
        method:'POST',
        data:formData,
        dataType:'json',
        success:function(data){
          if(data.result > 0){
            alert('업로드되었습니다.');
            $('#modal_add_feed_content').css({display : 'none'});
          }
        }
      });
    });

    // 게시물 업로드
    $('#button_write_feed_drop').on('click', function(){
        let content = $('#input_content_drop').val();
        let user_id = $('#input_user_id_drop').data('no');
        let file = files;

        console.log('file_formData : '+file[0]);
        console.log('file_formData : '+file[1]);

        let fd = new FormData();

        for(j=0; j<file.length; j++){
          fd.append('files', file[j]);
        }
        fd.append('content', content);
        fd.append('user_no', user_id);

        $.ajax({
            url:'/Photostagram/postUpload',
            processData: false,
            contentType: false,
            method:'POST',
            data:fd,
            dataType:'json',
            success:function(data){
              if(data.result > 0){
                alert('업로드되었습니다.');
                $('#modal_add_feed_content').css({display : 'none'});
              }
            }
        });
    });

    // 검색 버튼 클릭시
    $('#searchBtn').on('click', function(){
        let searchItem = $('#searchAll').val();
        let user_no = $('input[name=user_no]').val();
        console.log('user_no : '+user_no);

        let isHashTag = /(#[^\s#]+)/g;
        if(isHashTag.test(searchItem)){
            // 검색 아이템이 해시태그일 경우
            console.log('true');
            let cate = 2;
            let content = searchItem.substring(1);
            console.log(content);

            let jsonData = {"user_no":user_no, "searchItem":content, "cate":cate};

            $.ajax({
                url:'/Photostagram/searchHashtag',
                method:'POST',
                data:jsonData,
                dataType:'json',
                success:function(data){
                    if(data.result.length > 0){
                        let r = data.result;
                        console.log(r);
                        $('#searchListRecent').hide();
                        $('.searchListAll').empty();
                        r.forEach(function(i){
                            let searchResult = "<div class='searchlist' data-no='"+i.no+"'>";
                                searchResult += "<a>";
                                searchResult += "<div><img src=./img/hashtag.PNG></div>";
                                searchResult += "<div>";
                                searchResult += "<div><h3>#"+i.hashtag+"</h3></div>";
                                searchResult += "<div><h8>게시물"+i.countPost+"개</h8></div>";
                                searchResult += "</div>";
                                searchResult += "</a>";
                                searchResult += "</div>";

                            $('.searchListAll').append(searchResult);
                            $('.searchListAll').trigger("create");
                        });

                        $('.searchlist > a').on('click', function(e){
                            e.preventDefault();
                            let list = $(this).closest('div');
                            let dataNo = list.attr('data-no');
                            console.log('dataNo : '+dataNo);

                            let jsonData = {
                                "user_no":user_no,
                                "searchItem":content,
                                "cate":cate,
                                "searchResult":dataNo
                            };

                            console.log(jsonData);

                            $.ajax({
                                url:'/Photostagram/insertSearchResult',
                                method:'POST',
                                data:jsonData,
                                dataType:'json',
                                success:function(data){
                                    alert('입력성공!');
                                }
                            });
                        });
                    }else{
                        alert("검색 결과가 없습니다.");
                        return;
                    }
                }
            });
        }else{
            // 검색 아이템이 계정일 경우
            console.log('false');
            let cate = 1;
            let jsonData = {"user_no":user_no, "searchItem":searchItem, "cate":cate};

            $.ajax({
                url:'/Photostagram/searchUser',
                method:'POST',
                data:jsonData,
                dataType:'json',
                success:function(data){
                    console.log('성공!');
                    if(data.result.length > 0){
                        let r = data.result;
                        console.log(r);
                        $('#searchListRecent').hide();
                        $('.searchListAll').empty();
                        r.forEach(function(i){
                            let searchResult = "<div class='searchlist' data-username='"+i.username+"' data-no='"+i.no+"'>";
                                searchResult += "<a href='/Photostagram/profile?username="+i.username+"'>";
                                if(i.profileImg != null){
                                    searchResult += "<div><img src=/Photostagram/thumb/"+i.profileImg+"></div>";
                                }else{
                                    searchResult += "<div><img src=./img/44884218_345707102882519_2446069589734326272_n.jpg></div>";
                                }
                                searchResult += "<div>";
                                searchResult += "<div><h3 class='username'>"+i.username+"</h3></div>";
                                if(i.profileText != null){
                                    searchResult += "<div><h8>"+i.profileText+"</h8></div>";
                                }else{
                                    searchResult += "<div><h8></h8></div>";
                                }
                                searchResult += "</div>";
                                searchResult += "</a>";
                                searchResult += "</div>";

                            $('.searchListAll').append(searchResult);
                            $('.searchListAll').trigger("create");
                        });

                        $('.searchlist > a').on('click', function(e){
                            e.preventDefault();
                            let list = $(this).closest('div');
                            let dataNo = list.attr('data-no');
                            let username = list.attr('data-username');
                            console.log('dataNo : '+dataNo);

                            let jsonData = {
                                "user_no":user_no,
                                "searchItem":searchItem,
                                "cate":cate,
                                "searchResult":dataNo
                            };

                            console.log(jsonData);

                            $.ajax({
                                url:'/Photostagram/insertSearchResult',
                                method:'POST',
                                data:jsonData,
                                dataType:'json',
                                success:function(data){
                                    //alert('입력성공!');
                                    location.href='/Photostagram/profile?username='+username;
                                }
                            });
                        });
                    }else{
                        alert("검색 결과가 없습니다.");
                        return;
                    }
                }
            });
        }
    });

    // 검색 삭제 버튼 클릭시
    $('.searchDelete').on('click', function(){
        let searchNoDiv = $(this).closest('div');
        let searchNo = searchNoDiv.attr('data-searchNo');
        let searchList = $(this).parent().parent();
        console.log('searchNo : '+searchNo);

        let jsonData = {"searchNo":searchNo};

        $.ajax({
            url:'/Photostagram/deleteSearch',
            method:'POST',
            data:jsonData,
            dataType:'json',
            success:function(data){
                if(data.result > 0){
                    console.log(searchList);
                    searchList.remove();
                }
            }
        });
    });

    $('#deleteSearchAll').on('click', function(){
        let user_no = $('input[name=user_no]').val();

        let jsonData = {"user_no":user_no};

        $.ajax({
            url:'/Photostagram/deleteSearchAll',
            method:'POST',
            data:jsonData,
            dataType:'json',
            success:function(data){
                $('.searchListAll').hide();
            }
        });
    });

    $('.followBtn').on('click', function(){
        let div = $(this).closest('div');
        let user_no = div.attr('data-userNO');
        let my_no = div.attr('data-myNo');
        let status = div.attr('data-status');

        let jsonData = {"user_no":user_no, "my_no":my_no};
        console.log(jsonData);

        if(status == '0'){
            $.ajax({
                url:'/Photostagram/insertFollow',
                method:'POST',
                data:jsonData,
                dataType:'json',
                success:function(data){
                    $(this).hide();
                    $('.followingBtn').show();
                }
            });
        }
    });

    $('.followingBtn').on('click', function(){
        let div = $(this).closest('div');
        let user_no = div.attr('data-userNO');
        let my_no = div.attr('data-myNo');
        let status = div.attr('data-status');

        let jsonData = {"user_no":user_no, "my_no":my_no};
        console.log(jsonData);

        if(status == '1'){
            $.ajax({
                url:'/Photostagram/deleteFollow',
                method:'POST',
                data:jsonData,
                dataType:'json',
                success:function(data){
                    $(this).hide();
                    $('.followBtn').show();
                }
            });
        }
    });
  });