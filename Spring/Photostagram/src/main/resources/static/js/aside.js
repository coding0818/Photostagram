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
        sessionStorage.clear();
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

    $('.afterBtn').on('click', function(){
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

    $('.beforeBtn').on('click', function(){
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
  });