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
	//二级导航里的更多
	$("#more_drop").click(
			function(){$(".more_div").toggle();	
			$(".more_div ul li a").click(
				function(){
					$(".more_div ul li a").parents(".more_div").hide()
			});
	});
	//顶部的下拉框
	$(".drop_icon").click(function(){
			$(".drop_icon_con").toggle();
			$(".drop_icon_con a").click(
				function(){
					$(".drop_icon_con a").parents(".drop_icon_con").hide()
			});
	});
	//排序js
	$(function(){
		$(".sort dd a").click(
			function(){
				$(this).parents("dd").find("em").removeClass("down").toggleClass("up");
			});
	});
	//弹出层高度
	$(".box_wap").height($(document.body).height());
	$(".box_wap .shadow").height($(document.body).height());	
});
$(function(){
		//喜欢
	$(function(){
		$(".like em").click(
			function(){
				$(this).toggleClass("liked");
		});
	});
	//书城比价按钮
	$(".s_btn_1").click(
		function(){
			//弹出层显示
			$(".box_wap").show();
			//弹出层比价层显示
			$(".box_wap .money").show();			
	});
	//书城加入书架按钮
	$(".s_btn_2,.book_info_btn .join,.gb_img .gb_img_btn_2").click(
		function(){
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
	//弹出层关闭按钮
	$(".box_wap .close").click(
		function(){
			$(".box_wap").hide();
			$(".box_wap .bookshelf").hide();
			$(".box_wap .money").hide();
	});
});
$(function(){
  //书信息弹出层隐藏
  $(".book_info").hide();
  //书信息弹出
  $(".bk .rk_con .img").hover(
	  function(){
		  var tc = $(this).find(".book_info");
		  var pe = $(this).position();
		  tc.show();
		  tc.css("position","absolute")
		  tc.css({top:pe.top,left:pe.left+90});
	  },function(){
		  $(this).find(".book_info").hide();	
  });
  //书信息弹出喜欢
  $(".book_info_btn .text").click(
	  function(){
		  $(this).toggleClass("text2");
  });
  //用户弹出
  $(".reader_list ul li").hover(function(){
	  	var reader = $(this).find(".read_info");
	    var po = $(this).position();
		reader.show();
		reader.css("position","absolute");
		reader.css({top:po.top-165,left:po.left-200}); 
  },function(){
		  $(this).find(".read_info").hide();
  });
   $(".search_menu_list ul li .img").hover(function(){
	  	var reader = $(this).find(".read_info");
	    var po = $(this).position();
		reader.show();
		reader.css("position","absolute");
		reader.css({top:po.top-60,left:po.left+80}); 
  },function(){
		  $(this).find(".read_info").hide();
  });
  $(".menu_text dt .menu_btn").click(function(){
	  	$(this).toggleClass("menu_btn_ed");
  });
  //时间分类
  $(function(){
	  $(".rk_ul li a").click(
	  function(){
		  $(this).addClass("click");
	  });
  });
  //标签分类
  $(function(){
	   $(".rk_bh a em").click(
			function(){
				$(this).parents("a").remove();
	  });
	  $(".rk_col .rk_list ul li a").click(
	  	function(){
			var em = $(this).clone().appendTo(".rk_bh");
			em.find("em").empty();
			$(".rk_bh a em").click(
				function(){
					$(this).parents("a").remove();
			  });
	  });
  });
});
$(function(){
	//目录下拉
	$(".ul_menu .mulu").click(function(){
			$(this).next(".ml_con").toggle();
			return false;
	});
	//在线试读下拉
	$(".ul_menu .online").click(function(){
		  $(this).next(".online_con").toggle();
		  return false;
			
	});
	 $(".online_con ul li").hover(function(){
			  $(this).addClass("hover");
			  },function(){
			  $(this).removeClass("hover");
	});
	//多少人想读下拉
	$("#pages_book .reader_list").hide();
	$(".reader em").click(function(){
			$(this).next(".reader_list").toggle();
			$(".reader_list ul li").hover(function(){
				$(this).addClass("hover");
			},function(){
				$(this).removeClass("hover");
			});
	});
	//分享下拉
	$(".share a.sha").click(function(){
		$(this).parents(".share").find(".share_p").show();
		return false;
	});
	$(".share_p a").click(function(){
		$(this).parents(".share_p").hide();
		return false;
	});
	//@下拉
	$(".share a.spa").click(function(){
		$(this).parents(".share").find(".speak_s").show();
		return false;
	});
	$(".speak_s .s_con .sure_btn").click(function(){
		$(this).parents(".speak_s").hide();
		return false;
	});
	//写书评下拉
	$(".share a.write").click(function(){
		$(this).parents(".share").find(".speak_s").show();
		$(this).parents(".share").find(".speak_s").css("right","62px");
		$(this).parents(".share").find(".speak_s").find("h2").text("写书评").css("padding","0 5px");
		return false;
	});
	$(".speak_s .s_con .sure_btn").click(function(){
		$(this).parents(".speak_s").hide();
		$(this).parents(".speak_s").css("right","0");
		$(this).parents(".share").find(".speak_s").find("h2").text("@").css("padding","0");
		return false;
	});
	$("#pages_book").click(function(){
		$(".share_p").hide();
		$(".speak_s").hide();
		$(".speak_s").css("right","0");
		$(".share").find(".speak_s").find("h2").text("@").css("padding","0");
	});
	//点击div之外关闭div
	$(document).click(function(){
			if(!$("div.ml_con").hide()){
				$("div.ml_con").hide();
			}
			if(!$("div.online_con").hide()){
				$("div.online_con").hide();
			}
	});
});
//图书概论和书评书信息弹出喜欢
$(function(){
	$(".col_list ul li").hover(function(){
	  var read = $(this).find(".book_info");
	  var por = $(this).position();
	  read.show();
	  read.css("position","absolute");
	  read.css("text-align","left");
	  read.css({top:por.top+60,left:por.left-280}); 
	},function(){
	  $(this).find(".book_info").hide();
   });
   //喜欢
   $(".gb_img_btn .gb_img_btn_1").click(function(){
	   $(this).toggleClass("gb_img_btn_3");
   });
   //加入书架
   $(".gb_img_btn .gb_img_btn_2").click(function(){
	   $(this).toggleClass("gb_img_btn_4");
   });
   //展开、收起
   $(".gb_con .sq").click(
   		function(){
			var text = $(this).prev(".text");
			text.toggleClass("he");
			if(text.hasClass("he")){
				$(this).text("展开");
			}else{
				$(this).text("收起");
			}
	});
	//展开、收起
	$(".con_info ul li").last().find("a").click(
		function(){
			$(".con_info").toggleClass("he");
			if($(".con_info").hasClass("he")){
				$(this).text("收起");
			}else{
				$(this).text(">更多");
			}
			return false;
		}
	);
	//图书概述下拉18条
	$(".gb_list dd:gt(5)").hide();
	$(".gb_list dt .more_show").click(function(){
		$(".gb_list dd:gt(5)").toggle();
		$(this).find("a").toggleClass("sq");
		if(!$(this).find("a").hasClass("sq")){
			$(this).find("a").text("18条相似结果 »");
		}else{
			$(this).find("a").text("收起");
		}
		return false;
	});
	//书签下拉更多
	$(".span_list ul li:gt(5)").hide();
	$(".span_list p a").click(
		function(){
			$(this).parents(".span_list").find("ul li:gt(5)").toggle();
			$(this).toggleClass("sq");
			if(!$(this).hasClass("sq")){
				$(this).text(">更多");
			}else{
				$(this).text("收起");
			}
			return false;
	});
	//收藏变已收藏
	$("#pages_book .sp_list dd h5 .sf a").val("收藏").click(
		function(){
			$(this).text("已收藏");
			$(this).css("color","#999");
			return false;
		}
	);
});
$(function(){
	//登录
	$("#index_top .index_member .l_btn").click(
		function(){
			$(".box_login").show();
			$(".box_login .shadow").height($(document.body).height());
			$(".box_login .shadow,#login .close,#login .lg .l_btn").click(function(){
				$(".box_login").hide();	
			});
		}
	);
	//注册
	$("#index_top .index_member .r_btn").click(
		function(){
			$(".box_reg").show();
			$(".box_reg .shadow").height($(document.body).height());
			$(".box_reg .shadow").click(function(){
				$(".box_reg").hide();	
			});
		}
	);
	$("#reg .dq em").click(function(){
			var  list = $(this).parents(".dq").find(".dq_list");
			list.show();
			var sp = $(this).parents(".dq").find("span");
			list.find("ul").find("li").click(
				function(){
					sp.empty();
					$(this).find("a").clone().appendTo(sp);
					$(".dq_list").hide();
			});
	});
});
