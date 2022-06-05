function setDisplay(){
    if($('input:radio[id=deli2]').is(':checked')){
        $('#divId').hide();
		$("input:text[name=deli_pwd]").val("");
    }else{
        $('#divId').show();
    }
}
function modalDisplay1(){
        $('#recent').hide();
        $('#manage').show();
}
function modalDisplay2(){
        $('#manage').hide();
        $('#recent').show();
}




$(document).ready(function() {
	$("#cbx_chkAll").click(function() {
		if($("#cbx_chkAll").is(":checked")) $("input[name=orderInfo]").prop("checked", true);
		else $("input[name=orderInfo]").prop("checked", false);
	});
	
	$("input[name=orderInfo]").click(function() {
		var total = $("input[name=orderInfo]").length;
		var checked = $("input[name=orderInfo]:checked").length;
		
		if(total != checked) $("#cbx_chkAll").prop("checked", false);
		else $("#cbx_chkAll").prop("checked", true); 
	});
});