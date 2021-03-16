<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1); 
%>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<body style="overflow: hidden;">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>

	<div class="container">
		<h2 style="text-align: center; margin-top: 30px">ĐĂNG NHẬP</h2>
		<hr style="width: 100px; height: 5px;">
		<form action="UserLogin" method="post"
			style="width: 400px; margin: auto; margin-top: 15px">
			<div class="form-group">
				<label for="id" style="font-weight: 700; font-size: 16px">Mã
					nhân viên:</label> <input type="text" value="${userNamepass}" class="form-control" id="usn"
					placeholder="Nhập vào mã nhân viên" name="usn">
			</div>
			<div class="form-group">
				<label for="pwd" style="font-weight: 700; font-size: 16px">Mật
					khẩu: </label> <input type="password" value="${passUserName}" class="form-control" id="pwd"
					placeholder="Nhập vào mật khẩu" name="pswd">
			</div>
			<jsp:include page="viewcommon/message.jsp"></jsp:include>
			<button type="submit" class="btn "
				style="color: white; background-color: #0090DD">Đăng nhập</button>
		</form>
	</div>
	
	<div class="only-view-pc" id="footer-hide">
		<jsp:include page="viewcommon/footer.jsp"></jsp:include>
	</div>
	
</body>
<script type="text/javascript">

function getBrowserSize(){
    var w, h;

      if(typeof window.innerWidth != 'undefined')
      {
       w = window.innerWidth; //other browsers
       h = window.innerHeight;
      } 
      else if(typeof document.documentElement != 'undefined' && typeof      document.documentElement.clientWidth != 'undefined' && document.documentElement.clientWidth != 0) 
      {
       w =  document.documentElement.clientWidth; //IE
       h = document.documentElement.clientHeight;
      }
      else{
       w = document.body.clientWidth; //IE
       h = document.body.clientHeight;
      }
    return {'width':w, 'height': h};
}

if(parseInt(getBrowserSize().width) < 900){
	document.getElementById("footer-content").style.display = "none";
	document.getElementById("footer-div").style.height = "50px";
	document.getElementById("name_screen").style.textAlign  = "center";
	document.getElementById("name-div").style.paddingLeft  = "0px";
	document.getElementById("footer-div").style.paddingLeft = "0px";
	document.getElementById("pdaversion").style.display = "block";
}
	

</script>
</html>