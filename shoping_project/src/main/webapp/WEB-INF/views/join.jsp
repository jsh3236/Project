<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
<style>
 table.type03 {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-left: 3px solid #369;
  border-right:hidden;
   margin : 20px 10px;
 }
 table.type03 th {
  width: 147px;
  padding: 10px;
  font-weight: bold;
  vertical-align: middle;
  color: #153d73;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
 
 }
 table.type03 td {
  width: 300px;
  padding: 10px;
  vertical-align: middle;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
 }
 
input.ng-not-empty
{ /* 에러 출력시 필드 외곽선(적색) 표시  */
   border: 2px solid red;
}

input.ng-invalid 
{ /* 에러 출력시 필드 외곽선(적색) 표시  */
   border: 1px solid #ccc;
}

/* 에러 메시지 출력 팝업 */
.msg-popup 
{
	background-color:yellow;
	color:red;
	padding:10px;
	border-radius:7px;
	border:2px solid cyan;
	z-index:2;
}
</style>


<!-- jQuery UI CSS : 캘린더(datePicker) -->	  	  
<link rel="stylesheet" href="<c:url value='/jQuery/ui/1.12.1/jquery-ui.min.css' />">

<!-- 주소검색 : daum -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- jQuery -->
<script src="<c:url value='/jQuery/3.3.1/jquery-3.3.1.min.js' />"></script>

<!-- jQuery UI -->
<script src="<c:url value='/jQuery/ui/1.12.1/jquery-ui.min.js' />"></script>

<!-- jQuery UI : 캘린더(datePicker) 설정(한글 지원) -->	
<script src="<c:url value='/custom/datepicker.ko.js' />"></script>

<!-- 폼 점검(form validation) -->
<script src="<c:url value='/custom/memberJoinAngularValidator.js' />"></script>

<!-- AngularJS lib -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.10/angular.min.js"></script> -->
<script src="<c:url value='/angularjs/1.6.10/angular.min.js' />"></script>


<!-- AngularJS -->
<script>
var app = angular.module('joinBody', []);

	app.controller('joinAngularController', 
			       ['$scope', function ($scope) {
				
	    // TODO
	    // 아이디 중복 점검
	    /* $scope.idBtnHandler = function() {
	        
	    	// 아이디 필드 점검 
	        if ($scope.memberJoin.id_fld.$valid == true) {
	         //  sendId(); // 비동기적 폼 점검 
	        }   
	    };  */
	
	}]);

// jQuery : 아이디 폼 점검과 중복 점검 메시지 충돌 방지을 위한 초기화
$(function() {
	
	$("#id_fld").focus(function(){
		$("#id_msg2").text(""); // 메시지 초기화
	}); //
});
</script>

</head>

<body ng-app="joinBody" 
	  ng-controller="joinAngularController">
	  
		<form id="join" 
			  name="join"
			  action="joinAction" 
			  method="post">
	<table class="type03" align="center">
	     <tr>
	         <th scope="row" align="center">이름</th>
	            <td colspan="2"><input type="text" id="name" name="name" size=15 maxlength="10" style="height:20px" />
	             <font size="2">
	                 한글로 10글자 이내로 입력해 주세요.
	             </font>
	            </td>
	        </tr>
	        
	        <tr>
	         <th scope="row" align="center">아이디</th>
	            <td><input type="text" id="id" name="id" size=15 maxlength="20" style="height:20px" 
	          			  	ng-model="id"
                      	 	ng-pattern="/^[a-zA-Z]{1}\w{7,19}$/"
                      	 	ng-required="true">&nbsp;
                      	 	
	             <input type="button" value="중복 확인"><br>
	             
	             <font size="2"> 
	                 [아이디 중복 확인 결과 표기]    
	             </font>
	             
	            </td>
	            
	            <td style="border-left:hidden">
	             <div id="id_msg1" 
                      ng-model="id_msg1"
                 	  ng-show="join.id.$error.pattern">
                 	  <font size="2">
		              	 	영문 숫자를 조합하여 8~20자 이내로 입력 <br>
		                   (대소문자 구별. 한글/특수문자 사용 불가)
		           	  </font>
	             </div>
	            </td>
	        </tr>
	        
	        <tr>
	         <th scope="row" align="center">비밀번호</th>
	            <td><input type="text" id="pw" name="pw" size=15 maxlength="20" style="height:20px"
	             value="비밀번호 입력" onfocus="this.value=''" onblur="if(this.value =='') this.value='비밀번호 입력';">
	             <input type="text" id="rpw" name="rpw" size=15 maxlength="20" style="height:20px"
	                value="비밀번호 확인" onfocus="this.value=''" onblur="if(this.value =='') this.value='비밀번호 확인';">
	            </td>
	            <td valign="top" style="border-left:hidden">
	             <font size="2">
	                 영문, 숫자, 특수문자를 조합하여<br>
	                    (8~20자 이내로 입력
	             </font>
	            </td>
	        </tr>
	        
	        <tr>
		       	<th scope="row" align="center">성별</th>
	 			<td colspan="2"> 남 <input type="radio"
	                        id="gender"
	                        name="gender"
	                        value="m">
	                        여 <input type="radio"
	                        id="gender"
	                        name="gender"
	                        value="f">
				</td>
	        </tr>
	        
	        <tr>
	         <th scope="row" align="center">전화번호</th>
	            <td colspan="2"><input type="text" id="phone" name="phone" size=3 maxlength="3" style="height:20px"> - <input type="text" id="phone2" name="phone2" size=4 maxlength="4" style="height:20px">
	            - <input type="text" id="phone3" name="phone3" size=4 maxlength="4" style="height:20px; ">
	            </td>
	        </tr>
	        
	        <tr>
	         <th scope="row" align="center">주소</th>
	            <td colspan="2"><input type="text" id="address" name="address" size=50 maxlength="300" style="height:20px">&nbsp;<input type="button" value="주소 찾기"><br>
	             <input type="text" id="address" name="address" size=50 maxlength="300" style="height:20px; margin-top: 5px;">
	        </tr>
	        
	        <tr>
	         <th scope="row" align="center">이메일</th>
	            <td colspan="2"><input type="text" id="email" name="email" size=15 maxlength="20" style="height:20px">
	             <input type="text" id="email2" name="email2" size=15 maxlength="20" style="height:20px">
	               	<select name="mem_job" style="height:25px">
	                      <option value="0">직접 입력</option>
	                            <option value="naver">naver</option>
	                            <option value="daum">daum</option>
	                            <option value="hanmail">hanmail</option>
	                            <option value="google">google</option>
	     			</select>      
	            </td>
	        </tr>
	    </table>
	    
	    <br><br>
	        
		<input type="submit" value="회원가입" style="margin-left: 350px" > &nbsp;&nbsp; <input type="button" value="취소" onclick="location.href='http://localhost:8181/secureApp1/'"> 
	    
    </form>
</body>
</html> 
