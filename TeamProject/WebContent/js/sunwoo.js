/**
 * 
 */
function method(a){
	let form = document.createElement("form");
	
	let str = document.createElement("input");
	str.setAttribute("type","hidden");
	str.setAttribute("name","bbsid");
	str.setAttribute("value",a);
	
	form.appendChild(str);
	form.setAttribute("method" , "post");
	form.setAttribute("action" , "/TeamProject/bbsView.do");
	document.body.appendChild(form);
	
	form.submit();
}