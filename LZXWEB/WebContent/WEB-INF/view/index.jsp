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
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="https://v3.bootcss.com/favicon.ico">

		<title>图书查找</title>

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
							<h3 class="masthead-brand">图书查找</h3>
							<nav>
								<ul class="nav masthead-nav">
									<li class="active">
										<a href="javascript:void(0)">Home</a>
									</li>
								</ul>
							</nav>
						</div>
					</div>
					<div class="inner cover">
						<div class="row">
							<div class="col-lg-12">
								<div class="input-group">

									<input type="text" class="form-control" placeholder="Search for...">
									<span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
								</div>
								<!-- /input-group -->
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
				<p>版权所有：
					<a href="http://getbootstrap.com">李忠雪</a>, by
					<a href="https://twitter.com/mdo">ynu.edu.cn</a>.</p>
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