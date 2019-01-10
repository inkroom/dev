function scroll() {
	//                console.log("打印log日志");实时看下效果
	//				console.log("开始滚动！");
}
var i = 0;
var scrollFunc = function(e) {
	e = e || window.event;

	if(e.wheelDelta) { //第一步：先判断浏览器IE，谷歌滑轮事件     

		if(e.wheelDelta > 0 && i > 0) { //当滑轮向上滚动时  
			i = i - 1;
			console.log("滑轮向上滚动" + i);
		}

		if(e.wheelDelta < 0 && i < 3) { //当滑轮向下滚动时
			i = i + 1;
			console.log("滑轮向下滚动" + i);

			if(i > 1) {
				$("#top-img").stop().animate({
					height: '200px'
				}, 350);
			}
		}
	} else if(e.detail) { //Firefox滑轮事件  
		if(e.detail > 0) { //当滑轮向下滚动时  
			console.log("滑轮向下滚动");

			$("#top-img").stop().animate({
				height: '200px'
			}, 350);

		}
		if(e.detail < 0) { //当滑轮向上滚动时  
			console.log("滑轮向上滚动");

		}
	}
}
//给页面绑定滑轮滚动事件  
if(document.addEventListener) { //firefox  
	document.addEventListener('DOMMouseScroll', scrollFunc, false);
}
//滚动滑轮触发scrollFunc方法  //ie 谷歌  
window.onmousewheel = document.onmousewheel = scrollFunc;

$(this).scroll(function() {

	var viewHeight = $(this).height(); //可见高度  

	var contentHeight = $("body").get(0).scrollHeight; //内容高度  

	var scrollHeight = $(this).scrollTop(); //滚动高度  
	var min_height = 200;
	if(scrollHeight < 200) {

		$("#top-img").stop().animate({
			height: '100%'
		}, 350);

		//					alert("到达顶部了");

	}

});