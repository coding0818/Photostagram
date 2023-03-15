$(function(){

        /*** 팔로워 ***/
        $('.userFollow').click(function(e){
            e.preventDefault();
            let tag = $(this);

            tag.children('.spinner').show();

            let userName = $(this).attr('data-value');

            setTimeout(function(){
                $.ajax({
                    url: '/Photostagram/profile/follow',
                    type: 'post',
                    dataType: 'json',
                    data: {'userName':userName, 'type':'insert'},
                    success: function(data){
                        console.log('here1');
                        if(data.result > 0){
                            console.log('here2');
                            tag.parent().children('.userFollow').hide();
                            tag.children('.spinner').hide();
                            tag.parent().children('.userFollowing').show();
                        }
                    }
                });
            }, 1000);

        });
        

        /*** 팔로잉 ***/
        /*
        $('.userFollowing').click(function(e){
            e.preventDefault();
            let tag = $(this);

            tag.children('.spinner').show();

            let userName = $(this).attr('data-value');

            setTimeout(function(){
                $.ajax({
                    url: '/Photostagram/profile/follow',
                    type: 'post',
                    dataType: 'json',
                    data: {'userName':userName, 'type':'delete'},
                    success: function(data){
                        console.log('here1');
                        if(data.result > 0){
                            console.log('here2');
                            tag.parent().children('.userFollowing').hide();
                            tag.children('.spinner').hide();
                            tag.parent().children('.userFollow').show();
                        }
                    }
                });
            }, 1000);
        });
        */

    });



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