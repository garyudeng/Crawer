// author:LvBin QQ:286504720
$(function(){
	//搜索框字体消失和显示
	$(".foucs_text").focus(function(){
		  $(this).addClass("focus");
		  if($(this).val() ==this.defaultValue){  
			  $(this).val("");           
		  } 
	}).blur(function(){
		 $(this).removeClass("focus");
		 if ($(this).val() == '') {
			$(this).val(this.defaultValue);
		 }
	});
});