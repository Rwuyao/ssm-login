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


<style type="text/css">
body {
	margin: 0;
}

#canvas {
	height: 100%;
	width: 100%;
	background-size: 100%;
	display: block;
	background-image: url('image/aa.jpg')
}

#dd {
	width: 500px;
	height: 350px;
	position: absolute;
	right: 450px;
	bottom: 500px;
	background: rgba(216, 216, 216, 0.4);
}
</style>




</head>

<canvas id="canvas"></canvas>
<script>
	//初始化画布属性
	var cxt = document.getElementById("canvas").getContext('2d');
	var canvas = document.getElementById("canvas");
	canvas.width = window.innerWidth;
	canvas.height = window.innerHeight;
	var num = 200;
	var data = [];//储存粒子的属性数据
	function init() {
		for (var i = 0; i < num; i++) {
			data[i] = {
				x1 : Math.random() * window.innerWidth,
				y1 : Math.random() * window.innerHeight,
				sX : Math.random() * 0.6 - 0.3,//取值范围0.3-0.3
				sY : Math.random() * 0.6 - 0.3
			};
			createArc(data[i].x1, data[i].y1);
		}
	}
	init();

	//创建粒子
	function createArc(x, y) {
		cxt.save();
		cxt.beginPath();
		cxt.fillStyle = 'white';
		cxt.arc(x, y, 2, 0, Math.PI * 2, false);
		cxt.closePath();
		cxt.fill();
		cxt.restore();
	}

	//创建线条
	function createLine(x1, y1, x2, y2) {
		cxt.save();
		var lin = cxt.createLinearGradient(x1, y1, x2, y2);
		lin.addColorStop(0, '#99b3ff');
		lin.addColorStop(1, '#e5ffff');
		cxt.lineWidth = 1.5;
		cxt.strokeStyle = lin;
		cxt.beginPath();
		//连线
		cxt.moveTo(x1, y1);
		cxt.closePath();
		cxt.lineTo(x2, y2);
		cxt.stroke();
		cxt.restore();
	}

	//粒子运动
	function drawPath() {
		cxt.clearRect(0, 0, window.innerWidth, window.innerHeight);//先清除之前的圆
		for (var i = 0; i < num; i++) {
			data[i].x1 += data[i].sX;
			data[i].y1 += data[i].sY;
			//边界值检测
			if (data[i].x1<0||data[i].x1>window.innerWidth)
				data[i].sX = -data[i].sX;
			if (data[i].y1<0||data[i].y1>window.innerHeight)
				data[i].sY = -data[i].sY;
			createArc(data[i].x1, data[i].y1);
			//利用勾股定理判断是否连线
			for (var j = i + 1; j < num; j++) {//下一个点/下下一个点...
				if (Math.pow(data[i].x1 - data[j].x1, 2)
						+ Math.pow(data[i].y1 - data[j].y1, 2) < 100 * 80)
					createLine(data[i].x1, data[i].y1, data[j].x1, data[j].y1);
			}
		}
	}
	setInterval(function() {
		drawPath()
	}, 1);
</script>


<body>

	<div id="dd" style="height: 80px">
		<div style="height: 80px">
			<div align="center">
				<span style="font-size: 40px"> XX管理系统</span>
			</div>

		</div>

		<div style="background: rgba(216, 216, 216, 0.4)">
			<font color="red">${info}</font>
			<form action="login" method="post">
				<table align="center" style="height: 220px; width: 350px">
					<tr>
						<td><span class="glyphicon glyphicon-user" aria-hidden="true"
							style="font-size: 20px"> 用户名：</span></td>
						<td>
							<div class="input-group col-md-9 " style="display: inline-block">

								<input type="text" name="username" class="form-control"
									value="${message}" placeholder="请输入用户名"
									aria-describedby="sizing-addon1">
							</div>
						</td>
					</tr>
					<tr>
						<td><span class="glyphicon glyphicon-asterisk"
							aria-hidden="true" style="font-size: 20px"> 密码：</span></td>
						<td>
							<div class="input-group col-md-9 ">

								<input type="passqord" name="password" placeholder="请输入密码"
									class="form-control" aria-describedby="sizing-addon1">
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<button class="btn btn-primary" type="submit">登 陆</button>
						</td>
						<td>
							<button class="btn btn-danger" type="button">忘记密码</button>
						</td>
					</tr>
				</table>




			</form>
		</div>
		<!-- <div class="panel-footer">面板footer</div>
	</div> -->
	</div>
</body>
</html>