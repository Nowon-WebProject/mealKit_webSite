function itemCheck() {
	if (document.frm.item_name.value.length == 0) {
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

function display(id) {
	var openClose = document.getElementById(id);
	if (openClose.style.display == 'none') {
		openClose.style.display = '';
	} else {
		openClose.style.display = 'none';
	}
}

// 이 화면 그대로 조회수만 1씩 늘어나게 하기 나중에 할 것
function displayUpdate(id, post_num) {
	var openClose = document.getElementById(id, post_num);
	if (openClose.style.display == 'none') {
		openClose.style.display = '';
		
		var url = "updateHits.jsp?post_num=" + post_num;
		window.open(url,"_blank_1", "width=400, height=300, menubar=no, resizable=no, scrollbars=yes, toolbar=no");
	} else {
		openClose.style.display = 'none';
	}
}