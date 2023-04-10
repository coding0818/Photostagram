
/*** 선택 버튼으로 체크박스 열기 ***/
$(function(){
    const labels = $("label");
    $('.mySortSelect').on('click', function(){
        if (labels.hasClass("myCheckOff")){
            labels.removeClass("myCheckOff");
            labels.addClass("myCheckOn");
            $(".activityPop").show();
        } else {
            labels.removeClass("myCheckOn");
            labels.addClass("myCheckOff");
            $("input[name=photo]").prop("checked", false);
            $("#deleteCount").text("0");
            $(".activityPop").hide();
        }
    });
});

/*** 선택 갯수 카운트 ***/
function getCheckedCnt(){
  // 선택된 목록 가져오기
      const query = 'input[name="photo"]:checked';
      const selectedElements =
          document.querySelectorAll(query);

  // 선택된 목록의 갯수 세기
  const selectedElementsCnt =
        selectedElements.length;

  // 출력
  document.getElementById('deleteCount').innerText
    = selectedElementsCnt;
}

/*** ajax로 데이터 삭제 처리 ***/
$(function(){
    $('.mySortDelete').click(function(){
        let tags = $(this);
        let tagType = $(this).attr('data-type');
        let checkArray = [];
        let length = $('input:checked').length;
        //console.log ($('input:checked').length);

        $("input[type=checkbox]:checked").each(function(){
            let no = $(this).attr("data-value");
            checkArray.push(no);
        });

        console.log(checkArray);
        console.log(tagType);

        $.ajax({
            url: "/Photostagram/my/delete",
            type: "POST",
            traditional: true,
            data: { checkArray:checkArray, type:tagType },
            success: function(data){
                console.log("success");
                console.log(data.result);
                $("input[type=checkbox]:checked").parent().remove();
                $("input[type=checkbox]:checked").remove();
                $("label").removeClass("myCheckOn");
                $("label").addClass("myCheckOff");
                $(".activityPop").hide();
                alert('정상적으로 삭제되었습니다.');
            }
        });
    });
});