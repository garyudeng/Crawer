// author:LvBin QQ:286504720

$(function(){
	//导航下拉
	$(".nav li.class").hover(function(){
		$(this).find(".sc_dlist").show();
	},function(){
		$(this).find(".sc_dlist").hide();
	});
	$("#top .top_info .mail").hover(function(){
		$(this).find(".mail_drop").show();
	},function(){
		$(this).find(".mail_drop").hide();
	});
	$("#top .mail_drop ul li").hover(function(){
		$(this).addClass("bg");
	},function(){
		$(this).removeClass("bg");
	});
	//状态
	$(".info_con").mouseleave(function(){
		$(this).hide();
	});
	$(".info_cont p").hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
	});
	$(".info_cont p").click(function(){
		$(this).next(".info_con").show();	
	});
	//我在做什么
	$("#layout .info .doing .con .text").focus(function(){
		$(this).next(".fb").show();
		$("#layout .speak").show();
		$("#layout .speak").css({left:"800px",top:"60px"});
	});
	$("#layout .speak_fb .fs,#layout .speak h2 .close").click(function(){
		$("#layout .speak").hide();
	});
	//点击div之外关闭div
	$(document).blur(function(){
		  if(!$("div.fb").hide()){
			  $("div.fb").hide();
		  }
	});
	$("#layout .info .doing .con .fb .fb_btn .btn,.fb_list a").click(function(){
		$(this).parents(".fb").hide();
	});
	//图书弹出
	$(".book_info").hide();
   $(".sidebar .now_read .read_list ul li,.tab_con dl dd,.tab .tab_con .book_img,.con_list .col ul li,.interest_list .bk_img").hover(
	function(){
		var tc = $(this).find(".book_info");
		var pe = $(this).position();
		tc.show();
		tc.css("position","absolute")
		tc.css({top:pe.top+20,left:pe.left+40});
	},function(){
		$(this).find(".book_info").hide();	
  });
  //书信息弹出喜欢
  $(".book_info_btn .text").click(
	  function(){
		  $(this).toggleClass("text2");
  });
  //用户弹出
  $(".interest_person .list ul li").hover(function(){
	  	var reader = $(this).find(".read_info");
	    var po = $(this).position();
		reader.show();
		reader.css("position","absolute");
		reader.css({top:po.top-140,left:po.left-230}); 
  },function(){
		  $(this).find(".read_info").hide();
  });
   $(".tab .tab_con .tab_list li .img,.person_tab .tab_con ul li .img,.circle_right .interest_list .hd .img,.dr_list .list dd .img,.cs_list ul li .img,.zr_list dd .img").hover(function(){
	  	var reader = $(this).find(".read_info");
	    var po = $(this).position();
		reader.show();
		reader.css("position","absolute");
		reader.css({top:po.top-110,left:po.left+10}); 
  },function(){
		  $(this).find(".read_info").hide();
  });
  //私信展开、收起
  $(".tab_list li p.toggle a").click(function(){
	  $(this).parents(".tab_list li").find(".ly").toggleClass("W540");
	  $(this).parents(".tab_list li").find(".ly_me").toggle();
	  $(this).toggleClass("sq");
	  if($(this).hasClass("sq")){
		  $(this).text("展开");
	  }else{
		   $(this).text("收起");
	  }
	  return false;
  });
  //加入书架
  //弹出层高度
  $(".box_wap").height($(document.body).height());
  $(".box_wap .shadow").height($(document.body).height());
  $(".book_info_btn .join").click(function(){
	  //弹出层显示
	  $(".box_wap").show();
	  //弹出层加入书架显示
	  $(".box_wap .bookshelf").show();
	  //弹出层下拉按钮
	  $(".bookshelf .read .s").click(
		  function(){
			  $(this).next(".more_list").toggle();
	  });
	  //弹出层标签删除
	  $(".read_span a .x").click(
		  function(){
			  $(this).parents("a").hide();
			  return false;
	  });
  });
  //关闭书架
  $(".box_wap .close").click(function(){
	  $(".box_wap").hide();
	  $(".bookshelf").hide();
  });
  $(".tab .tab_con .bott .p_function a").val("收藏").click(
  	function(){
		$(this).text("已收藏");
		$(this).css("color","#999");
		return false;
	});
});
$(function(){
	//关注按钮
	$(".gz_btn_cont .gz_btn").click(
		function(){
			$(this).removeClass("gz_btn");
			$(this).addClass("gz_btn_off");
			$(".gz_btn_cont .gz_btn_off").hover(function(){
				$(".gz_btn_cont .gz_btn_off").addClass("gz_btn_off2");
			},function(){
				$(".gz_btn_cont .gz_btn,.gz_btn_cont .gz_btn_off").removeClass("gz_btn_off2");
			});
			$(".gz_btn_cont .gz_btn_off").click(
				function(){
					$(this).addClass("gz_btn");
					$(this).removeClass("gz_btn_off");	
			});
	});	
	$(".person_t_con .gzed_off").click(
		function(){
		$(this).toggleClass("gzed_on");
	});
	$(".interest_list .bd .guanz_btn").click(
		function(){
		$(this).toggleClass("guanz_btn_on");
	});
});
//搜索框字体
$(function(){
	$(".tab_search_text").focus(function(){
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
$(function(){
		$(".info_btn_cont .sp").click(function(){
			$(this).next(".speak").show();
		});
		$(".info_btn_cont .speak_fb .fs,.person_info .info_btn_cont .speak h2 .close").click(function(){
			$(this).parents(".speak").hide();
		});
});
$(function(){
		$(".info_btn_cont .sx").click(function(){
			$(this).next(".sixin").show();
		});
		$(".info_btn_cont .fs,.person_info .info_btn_cont .sixin h2 .close").click(function(){
			$(this).parents(".sixin").hide();
		});
});
$(function(){
	$(".side_detail h5 a").click(
		function(){$(this).parents(".side_detail").find("p").toggleClass("height");
		$(this).next("h5").find("a").addClass("more");
		if($(this).hasClass("more")){
			$(this).text("更多...");
		}else{
			$(this).text("收起");
		}
		return false;
	});
	$(".read_select").click(
		function(){
			$(this).find(".yc").toggleClass("fn-hide");
	});
	$(".book_list li a").click(
		function(){
			var span = $(this).parents("li").clone();
			 span.appendTo(".read_choose ul");
			$(".read_choose ul li em").click(
				function(){
					$(this).parents("li").remove();
			}); 
			return false;
	});
	$(".read_choose ul li em").click(
		function(){
			$(this).parents("li").remove();
	});
	//发私信
	$(".sx_click").click(function(){
		var sixin = $(this).next(".sixin").show();
	    var po = $(this).position();
		sixin.css("position","absolute");
		sixin.css({top:po.top-50,left:po.left+20}); 
		sixin.find(".close,.fs").click(
			function(){sixin.hide();
		});
		return false;
	});
});
//圈子邀请、关注
$(function(){
	$(".dr_list .list dd .yq").click(
		function(){
			$(this).toggleClass("ed");
			if($(this).hasClass("ed")){
				$(this).text("已邀请");
			}else{
				$(this).text("邀请");
			}
	});
	$(".dr_list .list dd .gz,.zr_dd_btn").click(
		function(){
			$(this).toggleClass("dr_list_btn_ed");
	});
	$(".zr_dt ul li").click(function(){
			$(this).siblings().find("em").removeClass("on");
			$(this).find("em").addClass("on");
	});
});