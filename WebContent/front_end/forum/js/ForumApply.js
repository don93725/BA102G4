var count = 0;
function create(pj,mem_no) {
	
	var result = false;
	var art_type_name = "";
		 for(var i = 0 ; i< $('input[name="art_type_name"]').length ; i++){
   		  art_type_name= art_type_name+"art_type_name="+$('input[name="art_type_name"]:eq('+i+')').val()+"&"
   	  }
		 $.ajax({
                url: pj+"/forum/ForumActionCtrl",
                data: "action=insert&mem_no="+mem_no+
                	  "&forum_name="+$('#forum_name').val()+"&forum_desc="+$('#forum_desc').val()+
                	  "&forum_note="+$('#forum_note').val()+"&"+art_type_name.substring(0,art_type_name.length-1),	                	  
                type:"POST",
                dataType:'text',

                success: function(msg){		
                	if(msg.length==0){                    	
  					  $(".inline").colorbox.close();
	  					swal({
							  title: "申請成功",
							  text: "已成功送出創建板塊申請，請等待審核通知。",
							  timer: 1000,
							  type: "success",
							  showConfirmButton: false
	  					});
                    }else{
                    	$.each(JSON.parse(msg),function(v,i){
        					swal({
        					  title: "輸入錯誤",
        					  text: v+i,
        					  timer: 1000,
        					  type: "error",
        					  showConfirmButton: false
        					});
        				});
                    }
                
                },

                 error:function(xhr, ajaxOptions, thrownError){ 
                	 swal({
   					  title: "申請失敗",
   					  text: "請再嘗試看看",
   					  timer: 1000,
   					  type: "error",
   					  showConfirmButton: false
   					});
                 }
            });
		 
		
	 		
	 }




$(function() {

	$(".inline").colorbox({
		inline : true,
		width : "50%"
	});

	count = $('input[name="art_type_name"][value]').length;

	if (count > 3) {
		$('#btn').css("display", "none");
	}
	for (var i = 0; i < count; i++) {
		$('input[name="art_type_name"]:eq(' + i + ")").css("display", "");
		$('input[name="art_type_name"]:eq(' + i + ")").attr("readonly", "");
		$('.xbtn:eq(' + i + ')').css("display", "none");
		$('.xbtn:eq(' + i + ')').after('<br>');
		$('.xbtn:eq(' + (i) + ')').click(
				function() {
					if (count == 4) {
						$('#btn').css("display", "");
					}
					$(this).next().remove();
					$(this).unbind('click');
					$('input[name="art_type_name"]:eq(' + (count) + ")").css(
							"display", "none");
					$('input[name="art_type_name"]:eq(' + (count) + ")").val("");
					$(this).css("display", "none");
					$('input[name="art_type_name"]:eq(' + (--count) + ")")
							.removeAttr("readonly");
					$('.xbtn:eq(' + (count - 1) + ')').css("display", "");
				});
	}
	$('input[name="art_type_name"]:eq(' + count + ")").css("display", "");
	$('.xbtn:eq(' + (count - 1) + ')').css("display", "");
	$('#btn').click(
			function() {
				$('input[name="art_type_name"]:eq(' + (count) + ")").attr(
						"readonly", "");
				$('.xbtn:eq(' + (count - 1) + ')').css("display", "none");
				$('.xbtn:eq(' + (count) + ')').css("display", "");
				$('.xbtn:eq(' + (count++) + ')').after('<br>');
				$('input[name="art_type_name"]:eq(' + (count) + ")").css(
						"display", "");
				if (count > 3) {
					$(this).css("display", "none");
				}
				$('.xbtn:eq(' + (count - 1) + ')')
						.click(
								function() {
									if (count == 4) {
										$('#btn').css("display", "");
									}
									$(this).next().remove();
									$(this).unbind('click');
									$(
											'input[name="art_type_name"]:eq('
													+ (count) + ")").css(
											"display", "none");
									$(
											'input[name="art_type_name"]:eq('
													+ (count) + ")").val("");
									$(this).css("display", "none");
									$(
											'input[name="art_type_name"]:eq('
													+ (--count) + ")")
											.removeAttr("readonly");
									$('.xbtn:eq(' + (count - 1) + ')').css(
											"display", "");
								});

			});
})
