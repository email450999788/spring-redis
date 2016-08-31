;$(function(){
	var baseNavNs = {
		show : function(modular,$this){
//			console.log(modular+".show");
		}  
	};
	var navHandler = {
		"set" : $.extend({},baseNavNs,{
//			show : function(modular,$this){
//				
//			} 
		}) ,
		"get" : $.extend({},baseNavNs,{
//			show : function(modular,$this){
//				console.log(modular+".show");
//			} 
		}) 
	};
	
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		var modular = $(this).data("modular") ;
		navHandler[modular].show(modular,$(this));
	});
	
	$('#set_btn_set','#set-form').click(function(){
		$.ajax({
		  url: "set",
		  cache: false,
		  data:{
			  key : $("#set-key","#set-form").val(),
			  val : $("#set-val","#set-form").val()
		  },
		  success: function(respMsg){
			  showConfirm("提示",respMsg);
		  }
		});
	});
	
	$('#get_btn_get','#get-form').click(function(){
		$.ajax({
		  url: "get",
		  cache: false,
		  data:{
			  key : $("#get-key","#get-form").val()
		  },
		  success: function(respMsg){
//			  showConfirm("提示",respMsg);
			  $("#get-val","#get-form").val(respMsg)
		  }
		});
	});
});