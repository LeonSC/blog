<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=yes" />
<link rel="shortcut icon" type="image/ico" href=""/>
<title>BLOG</title>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<script src="//cdn.bootcss.com/tether/1.4.0/js/tether.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/holder/2.9.4/holder.min.js"></script>
<link rel="stylesheet" href="${config.rootPath}/public/medium-editor-insert-plugin-2.4.0/medium-editor-insert-plugin-frontend.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
p {overflow:hidden}
.custom-max { width:100%;}
.custom-max-70-70 { width:70px;height:70px;}
.custom-height-22 { height:1.5rem;overflow:hidden}
.custom-height-66 { height:9.1rem;overflow:hidden}
.custom-height-180 { height:11.25rem;}
.custom-max-75-75 { width:75px;height:75px;}
.custom-table {}
.custom-td{vertical-align:top;border:1px solid rgba(0,0,0,.125);}
</style>
<script>
$(document).ready(function() {
	$("*[data-href]").css("cursor","pointer");
	$("*[data-href]").click(function(){
		location.href = $(this).attr("data-href");
	});
});
</script>
</head>