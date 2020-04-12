<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap.css"></link>
<script type="text/javascript" src="./lib/jquery-3.4.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.js"></script>

<script type="text/javascript" src="./js/Vidage.min.js"></script>  
<script>
    new Vidage('#cc');
</script>
<style type="text/css">

.Vidage--allow .vv {
    display: block
}

.aa {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: -1
}

.aa, .vv {
    min-width: 100%;
    min-height: 100%
}

.vv {
    position: absolute;
    top: 50%;
    left: 50%;
    width: auto;
    height: auto;
    -webkit-transform: translateX(-50%) translateY(-50%);
    -ms-transform: translateX(-50%) translateY(-50%);
    -o-transform: translateX(-50%) translateY(-50%);
    transform: translateX(-50%) translateY(-50%);
    display: block
}

#dd {
	
	width: 500px;
	height: 350px;
	position: absolute;
	right: 450px;
	bottom: 500px;
	background: rgba(216,216,216,0.4);
	
} 

</style>
<script type="text/javascript">

function login() {
	var username=$("#aa").val();
	var password=$("#bb").val();
	
	$.ajax({
		type:"post",
		url:"login2",
		data:{
			username:username,
			password:password
		},
	dataType:"text",
	scriptCharset:"utf-8",
	success:function(ret){
		/*对返回回来的值进行判断  */
		if(ret=='no'){
			alert("用户不存在")
		}
		if(ret=='erro'){
			alert("密码错误")
		}
		if(ret=='success'){
			window.location.href='view/success.jsp'
		}
	}
		
	})
	
}

</script>




</head>

<canvas id="canvas" ></canvas>
       

<body >

<!-- 创建一个视频容器 -->
<div class="aa">


    <video id="cc" class="vv" preload="metadata" loop autoplay muted>
        <source src="http://bpic.588ku.com/video_listen/588ku_video/19/08/13/15/41/16/video5d52699c24301.mp4" type="video/mp4">
    </video>
    </div>




	<div id="dd"  style="height: 80px ">
		<div  style="height: 80px ">
		<div align="center"><span  style="font-size: 40px"> XX管理系统</span>
		</div>
			 
		</div>
		
		<div style="background: rgba(216,216,216,0.4)" >
				<table align="center" style="height: 220px; width: 350px">
					<tr>
						<td><span class="glyphicon glyphicon-user" aria-hidden="true" style="font-size: 20px">
								用户名：</span></td>
						<td>
							<div class="input-group col-md-9 " style="display: inline-block">

								<input type="text" id="aa" name="username" class="form-control"
									value="" placeholder="请输入用户名"
									aria-describedby="sizing-addon1">
							</div>
						</td>
					</tr>
					<tr>
						<td><span class="glyphicon glyphicon-asterisk"
							aria-hidden="true" style="font-size: 20px" > 密码：</span></td>
						<td>
							<div class="input-group col-md-9 ">

								<input type="passqord" id="bb" name="password" placeholder="请输入密码"
									class="form-control" aria-describedby="sizing-addon1">
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<button class="btn btn-primary" type="button" onclick="login()">登    陆</button>
						</td>
						<td>
							<button class="btn btn-danger" type="button">忘记密码</button>
						</td>
					</tr>
				</table>




		</div>
		<!-- <div class="panel-footer">面板footer</div>
	</div> -->
	</div>
</body>
</html>