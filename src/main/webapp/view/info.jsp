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
<script type="text/javascript" src="./lib/jquery-3.4.1.js"></script>
<script type="text/javascript">
function chek(){
	var obj = document.getElementsByName("rad");
	for (var i = 0; i < obj.length; i++) {
		if(obj[i].checked==true){
			/* getElementsByName获得的是几个集合。需要遍历 */
			var el = document.getElementsByName("rad2");
			for (var i = 0; i < el.length; i++) {
				el[i].checked=true
			}
			
		   
			
		}else {
			var el = document.getElementsByName("rad2");
			for (var i = 0; i < el.length; i++) {
				el[i].checked=false
			}
		}
	}
	
}


function delete2(){
	var number="";
	var obj3 = document.getElementsByName("rad2");
	for (var i = 0; i < obj3.length; i++) {
		if(obj3[i].checked==true){
			number=number+","+obj3[i].value
			
			
		}
		
	};
	
	$.ajax({
		type:"post",
		url:"pdeleteindo",
		data:{
			number:number
		},
	dataType:"text",
	scriptCharset:"utf-8",
	success:function(ret){
		if(ret=="success"){
			window.location.reload();
			alert("删除成功")
			
			
		}
		else {
			alert("删除失败，稍后在试")
		}
	
		},
		error:function(ret){
			alert("服务器异常")
		}
	
		
	})
	
	
	
}



</script>

<style type="text/css">




</style>
</head>

<body>
<div align="center">

<!-- 条件查询的代码 -->
<form action="query" method="post">
<font>请输入关键字</font>
<input type="text" name=aa value="" placeholder="${message}">
<button type="submit">查询</button>
</form>
<button type="button" onclick= "delete2()">批量删除</button>

<table border="1" style="width:500px">
<tr>
<td><input id="ind" name="rad"   type="checkbox" onclick="chek()"> 全选(当前页) </td>
<td>id</td>
<td>编号</td>
<td>名字</td>
</tr>
<c:forEach items="${list }" var="list">
<tr>
<td><input type="checkbox"  name="rad2" id=rad2 value="${list.id }"></td>
<td>${list.id }</td>
<td>${list.num }</td>
<td>${list.name}</td>
</tr>
</c:forEach>
</table>


<!--下面是分页按钮的代码，aa=${message}第一次进入该页面前是将message设置为空的。做了条件查询。那么message就是条件值。利用c:if标签显示页数按钮。  -->
<c:if test="${pa.num-1 gt 0}">
<button type="button" onclick="location.href='query?num=${pa.num-1}&aa=${message}'">上一页</button>
</c:if>
<c:if test="${pa.num-2 gt 0}">
<button type="button" onclick="location.href='query?num=${pa.num-2}&aa=${message}'">${pa.num-2}</button>
</c:if>
<c:if test="${pa.num-1 gt 0}">
<button type="button" onclick="location.href='query?num=${pa.num-1}&aa=${message}'">${pa.num-1}</button>
</c:if>
<font>${pa.num}</font>
<c:if test="${pa.num+1 le pa.sum }">
<button type="button" onclick="location.href='query?num=${pa.num+1}&aa=${message }'">${pa.num+1}</button>
</c:if>
<c:if test="${pa.num+2 le pa.sum}">
<button type="button" onclick="location.href='query?num=${pa.num+2}&aa=${message }'">${pa.num+2}</button>
</c:if>
<c:if test="${pa.num lt pa.sum}">
 <button type="button" onclick="location.href='query?num=${pa.num+1}&aa=${message}'">下一页</button>
 </c:if>
 <form action="query" method="post">
 <input type="hidden" name=aa value="${message }">
 <font>跳转到 :<input style="size:20px" type="text" name=num> 页 </font><button type="submit">跳转</button> 
 </form>
 <font>共：${pa.sum}页</font>
 
 <br><hr>
 <font>上传文件</font>
 <form action="upload" method="post" enctype="multipart/form-data">
 <input type="file" name="mufile" >
 <button type="submit">提交</button>
 </form>

<button type="button" onclick="location.href='load?aa=${message}'">导出数据</button>

<form action="insert" method="post" enctype="multipart/form-data">
<input type="file" name="mufile2" >

<button type="submit" >批量插入</button>
 </form>
 
 
 
</div>



</body>
</html>