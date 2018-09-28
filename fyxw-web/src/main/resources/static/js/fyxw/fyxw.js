$(function() {

	// Full Page menu
	$(".sidemenu").fullpageMenu({
		animationSpeed : 200,
		openButton:$(".nickNameStr").html()
	});
	fixedBottom();
	$("#mainDiv").bind('DOMNodeInserted', function() {
		fixedBottom();
	});
});
window.onresize = function() {
	fixedBottom();
}
function fixedBottom() {
	var mainHeight = $("#mainDiv").height();
	var footerHeight = $(".section-footer").height();
	var headerHeight = $(".custom-navbar").height();
	var windowHeight = $(window).height();
	if ((mainHeight + footerHeight + headerHeight) < windowHeight) {
		// 固定页脚
		$(".section-footer").addClass("fixedBottom");
	} else {
		$(".section-footer").removeClass("fixedBottom");
	}
}