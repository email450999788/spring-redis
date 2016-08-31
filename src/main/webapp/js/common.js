function showConfirm(title,msg){
	var modalId = "#myModal" ;
	$(modalId).modal();
	$('.modal-title',modalId).html(title);
	$('.modal-body',modalId).html(msg);
	$('.modal-footer',modalId).css('display','none');
}
function showAlert(title,msg){
	var modalId = "#myModal" ;
	$(modalId).modal();
	$('.modal-title',modalId).html(title);
	$('.modal-body',modalId).html(msg);
	$('.modal-footer',modalId).css('display','none');
}