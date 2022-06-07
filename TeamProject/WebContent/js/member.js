function loginCheck(){
	
	if(document.frm.userid.value.length == 0){
		alert("아이디를 입력하세요.");
		document.frm.userid.focus();
		
		return false;
	}
	
	if(document.frm.pwd.value == ""){
		alert("패스워드는 반드시 입력해야 합니다.")
		document.frm.pwd.focus();
		
		return false;
	}
	
	return true;
}

function idCheck(){
	
	if(document.frm.userid.value == ""){
		alert("아이디를 입력해 주세요.")
		document.frm.userid.focus();
		
		return;
	}
	
	var url="idCheck.do?userid="+document.frm.userid.value;
	window.open(url,"_blank_1","toolbar=no,menubar=no,"+"scrollbars=yes,resizable=no,width=450,height=200");
}

function idok(userid){
	opener.frm.userid.value=document.frm.userid.value;
	opener.frm.reid.value=document.frm.userid.value;
	self.close();
}

function joinCheck(){
	if(document.frm.name.value.length == 0){
		alert("이름을 써 주세요.");
		document.frm.name.focus();
	
	return false;
	}
	
	if(document.frm.userid.value.length == 0){
		alert("아이디 써 주세요.");
		document.frm.userid.focus();
	
	return false;
	}
	
	if(document.frm.userid.value.length < 4){
		alert("아이디 4글자 이상이어야 합니다.");
		document.frm.userid.focus();
	
	return false;
	}
	
	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		document.frm.pwd.focus();
	
		return false;
	}
	
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("암호가 일치하지 않습니다.");
		document.frm.pwd.focus();
	
		return false;
	}
	
	if(document.frm.reid.value.length == 0){
		alert("중복 체크를 하지 않았습니다.");
	    document.frm.userid.focus();
	
	    return false;
	}
	
	return true;
}

// 주소
function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();

}

// 내가 선택한 이메일 select option값 넣기
function email_check() {
    if (document.frm.eMailForm.options[document.frm.eMailForm.selectedIndex].value == "선택하세요") {
        document.frm.eMailSite.readOnly = true;
        document.frm.eMailSite.value = "";
    }
    if (document.frm.eMailForm.options[document.frm.eMailForm.selectedIndex].id == "직접입력") {
        document.frm.eMailSite.readOnly = false;
        document.frm.eMailSite.value = "";
        document.frm.eMailSite.focus();
    } else {
        document.frm.eMailSite.readOnly = true;
        document.frm.eMailSite.value= 
            document.frm.eMailForm.options[document.frm.eMailForm.selectedIndex].value;
    }
}

// 수정 체크
function check() {
	if(document.frm.name.value==""){
		alert("이름을 입력해주세요.");
		document.frm.pwd.focus();
		return false;
	}if (document.frm.pwd.value == "") {
        alert("비밀번호를 입력해주세요.");
        document.frm.pwd.focus();
        return false;
    }if (document.frm.pwd_check.value == "") {
        alert("비밀번호 확인란을 입력해주세요.");
        document.frm.pwd_check.focus();
        return false;
    }if (document.frm.pwd.value != document.frm.pwd_check.value) {
        alert("입력된 비밀번호가 비밀번호 확인란에 입력된 비밀번호와 다릅니다.");
        document.frm.pwd_check.focus();
        return false;
    }if (document.frm.email.value == "") {
        alert("이메일을 입력해주세요.");
        document.frm.email.focus();
        return false;
    } if(document.frm.eMailForm.options[document.frm.eMailForm.selectedIndex].getElementById("선택하세요").value=="" || document.frm.eMailForm.option.value=="") {	
       alert("이메일사이트를 입력해주세요.");
       document.frm.eMailForm.focus();
       	return false;
    }if(document.frm.eMailSite.value=""){
    	alert("이메일 사이트를 선택해주세요.")
    	document.frm.eMailSite.focus();
    	return false;
	}if (document.frm.phone.value == "") {
        alert("핸드폰번호를 입력해주세요.");
        document.frm.phone.focus();
        return false;
    }if (document.frm.addr.value == "") {
        alert("우편번호를 입력해주세요.");
        document.frm.addr.focus();
        return false;
    }if (document.frm.addr1.value == "") {
        alert("주소를 입력해주세요.");
        document.frm.addr1.focus();
        return false;
    }if (document.frm.addr2.value == "") {
        alert("상세주소를 입력해주세요.");
        document.frm.addr2.focus();
        return false;
    } else {
        return true;
    }
 
}

/*
function em_submit() {
      var text = document.getElementByEmail('text').value;
      var Email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z]);
      if (frm.Email.test(text) === true) {

    	  	return true;
	      }
      }
*/ 
    
// 핸드폰 - 자동기능
function mobile_keyup(obj){
    let mobile_len=obj.value.length;
    console.log(mobile_len)
    if(event.keyCode==8){
        obj.value=obj.value.slice(0,mobile_len); 
        return 0; 
    }else if (mobile_len==3 || mobile_len==8){
        obj.value += '-';
    }
}

/*function deleteCheck(){
	var deleteCheck; 
	 //페이지 이동 전 confirm() 사용해 다시 한 번 확인하기. // confirm() : yes, no 확인 창
	deleteCheck=confirm("정말 탈퇴하시겠습니까?");
	// 확인 선택 시, deleteCheck에 true, 취소 선택 시 false
	if(deleteCheck==true){
		location="delete.jsp";
	}
	
}*/







