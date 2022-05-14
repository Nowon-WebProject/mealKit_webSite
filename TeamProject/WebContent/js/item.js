function itemCheck() {
	if(document.frm.item_name.value.length == 0) {
		alert("상품명을 써 주세요.");
		frm.item_name.focus();
		
		return false;
	}
	
	if (document.frm.item_price.value.length == 0) {
		alert("가격을 써 주세요.");
		frm.item_price.focus();
		
		return false;
	}
	
	if (isNaN(document.frm.item_price.value)) {
		alert("숫자를 입력해야 합니다.");
		frm.item_price.focus();
		
		return false;
	}
	
	if (document.frm.item_quantity.value.length == 0) {
		alert("재고를 써 주세요.");
		frm.item_quantity.focus();
		
		return false;
	}
	
	if (document.frm.item_total.value.length == 0) {
		alert("인분을 써 주세요.");
		frm.item_total.focus();
		
		return false;
	}
	
	if (document.frm.item_time.value.length == 0) {
		alert("조리 시간을 써 주세요.");
		frm.item_time.focus();
		
		return false;
	}
	
	return true;
}