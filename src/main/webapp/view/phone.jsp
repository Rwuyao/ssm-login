<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page isELIgnored ="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">

 #as{
 background-image: url(image/aa.jpg);
 background-size: 100%;
 
 }
 
 #aa{
 position: absolute;
 bottom: 350px;
 left: 65px;
 }
 #cc{
 position: absolute;
 bottom: 20px;
 left: 120px;
 }
</style>
</head>
<body>
<div id ="as" style="height:2280px;width: 1080px  ">
<div id ="aa" align="center" >
<span>用户名</span><input ><br><br>
&nbsp &nbsp<span>密     码</span><input >
</div>
<div id="cc">
<button>登陆</button>
&nbsp &nbsp &nbsp
<button>取消</button>
</div>


</div>

</body>
</html>