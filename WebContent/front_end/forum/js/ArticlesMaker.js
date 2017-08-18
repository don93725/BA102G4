var editor;
var originPic = [];
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		allowFileManager : true
	});
	var path = window.location.pathname;
	webCtx = path.substring(0, path.indexOf('/', 1));
	Preview.file_change();
	K('input[name=getHtml]').click(function(e) {
		
		e.preventDefault();
		$('#ctx').html(editor.html());
		var text = $('#ctx').html();
		var form = $('#articleForm');
		var art_ctx = document.getElementsByName("art_ctx")[0]; 
		var art_name = $('#art_name').val();
		art_ctx.innerHTML = text;
		check(art_name,text,form);
		
	});
	
});

var webCtx;
function check(art_name,art_ctx,form){
	$.ajax({
        url: webCtx+"/forum/ArticlesActionCtrl",
        data: {
        	"action":"check",
        	"art_name":art_name,
        	"art_ctx":art_ctx        	
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
Preview = new function() {
	var fileInput = $('#file');
	this.file_change = function() {
		$('#file').on('change', function() {
			show(this);
		});
	}
	var show = function(input) {
		if (input.files && input.files[0]) {
			each_img(input.files);
		}
	}			
	var each_img = function(files) {
		$.each(files,function(index, file) {
				if (file.type.match('image')) {
					var reader = new FileReader();
					var img = new Image();
					img.onload = function() {
						
						var pic ="<img  height=100  src='"
								+ img.src
								+ "'>";
						editor.insertHtml(pic);

					};
					reader.onload = function() {
						img.src = reader.result;
					}
					if (file) {
						reader.readAsDataURL(file);
					}
				}
				if (file.type.match('video')) {
					film = null;
					var reader = new FileReader();
					reader.onload = function() {
						var video = "<video style='height:100px;'  controls='conrtols'><source src="+
								reader.result+" type='video/mp4'></video>";
						editor.insertHtml(video);							
						

					}
					if (file) {
						reader.readAsDataURL(file);
					}
					return;
				}
			});
	}

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