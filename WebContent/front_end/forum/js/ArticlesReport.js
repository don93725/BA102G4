	$(function(){
		$('.art_rpt').click(function(){
			if($(this).next('tr' ).css("display")=='none'){
				$(this).css("background-color","lightblue");
				$(this).next('tr' ).css("display","");
			}else{
				$(this).next('tr' ).css("display","none");
				$(this).css("background-color","");
			}
			

			

		});
		$('.fininshRpt').click(function(event){
			 event.stopPropagation();			
			 var self = $(this);
			 swal({
				  title: "確定要忽略此檢舉?",
			 	  text: "忽略後完成處理，你審慎思考你的行為。",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  cancelButtonText: "算了",
				  confirmButtonText: "是的",
				  closeOnConfirm: false
			 },function(){
				 $.ajax({
		                url: "/BA102G4/forum/ArticlesReportActionCtrl",
		                data: "action=update&art_rpt_no="+self.val(),
		                type:"POST",
		                dataType:'text',

		                success: function(msg){		                  
		                	if(msg.legth!=0){
		                		swal({
									  title: "成功",
									  text: "已處理剛剛的行為。",
									  timer: 1000,
									  type: "success",
									  showConfirmButton: false
		                    	},function(){
		                    		 location.reload();
		                    	});
		  				
		                    }else{
		                    	swal({
			      					  title: "刪除失敗",
			      					  text: "請稍後再次嘗試",
			      					  timer: 1000,
			      					  type: "error",
			      					  showConfirmButton: false
			      					});
		                    }  
		                
		                },

		                 error:function(xhr, ajaxOptions, thrownError){ 
		                	 swal({
		      					  title: "刪除失敗",
		      					  text: "請稍後再次嘗試",
		      					  timer: 1000,
		      					  type: "error",
		      					  showConfirmButton: false
		      					});
		                 }
		            });
			 });
			
				
				
			 		
			 
		});
		$('.delRpt').click(function(event){
			 event.stopPropagation();
			 var self = $(this);
			 swal({
				  title: "確定要刪除此文章?",
			 	  text: "刪除後將會無法回復，你審慎思考你的行為。",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  cancelButtonText: "算了",
				  confirmButtonText: "是的",
				  closeOnConfirm: false
			},function(){
				 $.ajax({
		                url: "/BA102G4/forum/ArticlesReportActionCtrl",
		                data: "action=delete&art_no="+self.val(),
		                type:"POST",
		                dataType:'text',

		                success: function(msg){
		                    if(msg.legth!=0){
		                    	swal({
									  title: "成功",
									  text: "已成功發布動態",
									  timer: 1000,
									  type: "success",
									  showConfirmButton: false
		                    	},function(){
		                    		location.reload();		                    		
		                    	});
		                    }else{
		                    	swal({
		      					  title: "刪除失敗",
		      					  text: "請稍後再次嘗試",
		      					  timer: 1000,
		      					  type: "error",
		      					  showConfirmButton: false
		      					});
		                    }
		                },

		                 error:function(xhr, ajaxOptions, thrownError){ 
		                	 swal({
		      					  title: "刪除失敗",
		      					  text: "請稍後再次嘗試",
		      					  timer: 1000,
		      					  type: "error",
		      					  showConfirmButton: false
		      				});
		                 }
		            });
			 	});
			 
		});
	})
	
