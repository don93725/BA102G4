var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		allowFileManager : true
	});
	K('input[name=getHtml]').click(function(e) {
		$('#ctx').html(editor.html());
		var ctx = document.getElementById('ctx').childNodes;
		var text = "";	
		var path = window.location.pathname;
		webCtx = path.substring(0, path.indexOf('/', 1));
		for(var i = 0 ; i<ctx.length ; i++){
 			var temp = ctx[i];	 			
 			if(temp.nodeType==1){
 				var tag = temp.tagName;
 				if(tag=='IMG'){			 					
 					if(temp.hasAttribute('alt')){
 						text = text + "<img src='"+temp.src+"' border='0'>";
 					}else{
 						text = text + "<img width=100 src='$ProjectRealPath$/"+
		 				"util/OutputPic?art_cmt_no=$ArtCmtPrimaryKey$'>";
 					}
 					
	 			}else{
 					var tagInner = temp.innerHTML;
 					text = text +"<"+tag+">"+tagInner+"</"+tag+">";	 						
	 			}					 	
 			
 			}
 			if(temp.nodeType==3){
 				text = text +temp.nodeValue;
 			}	 

		} 	
		var form = $('#cmmtForm');
		var art_cmt_ctx = document.getElementsByName("art_cmt_ctx")[0]; 
		art_cmt_ctx.innerHTML = text;
		check(text,form);
	});
	
});
var webCtx;
function check(art_cmt_ctx,form){
	$.ajax({
        url: webCtx+"/forum/ArtCmtActionCtrl",
        data: {
        	"action":"check",
        	"art_cmt_ctx":art_cmt_ctx,   	
        },                	  
        type:"POST",
        dataType:'text',

        success: function(msg){	
        	if(msg.length==0){                    	
					swal({
					  title: "發文成功",
					  text: "水喔，已成功發文，希望不是廢文。",
					  timer: 1000,
					  type: "success",
					  showConfirmButton: false
					},function(){
						form.submit();
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
var editor;
KindEditor.ready(function(K) {
	editor = K.create('div[name="content"]', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
});

(function($) {
     $(".inline").colorbox({inline:true, width:"50%"});
})(jQuery);
	

function report(event,pj,art_no){
	event.preventDefault();
	var rpt_type = $('#rpt_type').val()
	var rpt_ctx= $('#rpt_ctx').val();
	var art_no = art_no;
	
	$.ajax({
		type : "POST",
		url : pj + "/forum/ArticlesReportActionCtrl",
		dataType : 'text',
		data: {
			"action" : "insert",
			"rpt_type" : rpt_type,
			"rpt_ctx" : rpt_ctx,
			"art_no" : art_no
			
		},
		success : function(msg) {
			if(msg.trim().length==0){	
				  $(".inline").colorbox.close();
				  swal({
					  title: "成功",
					  text: "已成功送出檢舉",
					  timer: 1000,
					  type: "success",
					  showConfirmButton: false
				  });
			  } else{
				  $.each(JSON.parse(msg),function(v,i){
						swal({
						  title: "輸入錯誤",
						  text: i,
						  timer: 1000,
						  type: "error",
						  showConfirmButton: false
						});
					});
			  }
		},
		error : function(xhr, ajaxOptions, thrownError) {
			 swal({
				  title: "檢舉失敗",
				  text: "請在嘗試看看",
				  timer: 1000,
				  type: "error",
				  showConfirmButton: false
			  });
		}

		});	
	  	
			
	
}

