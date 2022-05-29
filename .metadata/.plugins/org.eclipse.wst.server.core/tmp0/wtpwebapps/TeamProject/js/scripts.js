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




