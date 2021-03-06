<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
	pageContext.setAttribute("basePath", basePath);
%>

<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<base target="_blank" />     
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="https://v3.bootcss.com/favicon.ico">

		<title>图书查找</title>
		<style> 
			a:link{text-decoration: underline;color: blue}
			a:active{text-decoration:underline;blink}
			a:hover{text-decoration:underline;color: red}
			a:visited{text-decoration: underline;color: red}
		</style> 
		<base href="<%=basePath%>" />
		<!-- Bootstrap core CSS -->
		<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">

		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="<%=basePath%>css/ie10-viewport-bug-workaround.css" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="<%=basePath%>css/cover.css" rel="stylesheet">

		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]><script src="https://v3.bootcss.com/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
		<script src="https://v3.bootcss.com/assets/js/ie-emulation-modes-warning.js"></script>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	</head>

	<body>

		<div class="site-wrapper">

			<div class="site-wrapper-inner">

				<div class="cover-container">
					<div class="masthead clearfix ">
						<div class="inner">
							<h1 class="masthead-brand">图书信息概览</h3>
						</div>
					</div>
					<div class="inner cover">
						<div class="row">
							<div class="col-lg-12">
							<!-- Book_Shelf [primary_key=305, storey_number=3, area_number=中庭一区, 
							shelf_number=1, search_numbe r_type=TP, search_number_start=-61/1, search_number_end=273/49, zhuzi=0]
							-->
								<%-- ${Bb} --%>
								<ul class="list-group">
								    <li class="list-group-item"><font color=black> ${Bb.storey_number}楼</font></li>
								    <li class="list-group-item"><font color=black>${Bb.area_number}</font></li>
								    <li class="list-group-item"><font color=black>第${Bb.shelf_number}书架</font></li>
								    <li class="list-group-item"><a href="<%=basePath%>/LZX/3d?shelf=${Bb.search_information}">在3D虚拟场景中显示书架具体位置</a></li>
								</ul>
							</div>

							<!-- /.col-lg-6 -->
						</div>
						<!-- /.row -->
						
						
					</div>
					
					


				</div>

			</div>

		</div>

		<div class="navbar navbar-inverse navbar-fixed-bottom">
			<div class="inner">
				<p>版权所有：李忠雪 by ynu.edu.cn
					<!-- <a href="http://getbootstrap.com">李忠雪</a>, by
					<a href="https://twitter.com/mdo">ynu.edu.cn</a>.</p> -->
			</div>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<script>
			window.jQuery || document.write('<script src="https://v3.bootcss.com/assets/js/vendor/jquery.min.js"><\/script>')
		</script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<%=basePath%>js/ie10-viewport-bug-workaround.js"></script>
	</body>

</html>