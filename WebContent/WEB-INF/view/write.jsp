<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="static/header.jsp"%>
<script src="//cdn.bootcss.com/medium-editor/5.23.0/js/medium-editor.min.js"></script>
<link rel="stylesheet" href="//cdn.bootcss.com/medium-editor/5.23.0/css/medium-editor.min.css" type="text/css" media="screen">
<link rel="stylesheet" href="//cdn.bootcss.com/medium-editor/5.23.0/css/themes/bootstrap.min.css" type="text/css" media="screen">
<body>
	<%@ include file="static/nav.jsp"%>
	<div class="container">
		<br/>
		<div class="row">
			<div class="col col-xl-12">
				<div class="card">
					<div class="card-header">音像</div>
					<div class="card-block">
						<h4 class="card-title" contenteditable="true">输入标题</h4>
						<div class="card-text" id="editor">输入内容.</div>
					</div>
					<div class="card-footer"><a href="#" class="btn btn-primary">保存</a></div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("form").submit(function() {
				return true;
			});
			var editor = new MediumEditor('#editor', {
				customClassOption : "card-text",
				imageDragging : false
			});
		});
	</script>
	<%@ include file="static/footer.jsp"%>
</body>
</html>