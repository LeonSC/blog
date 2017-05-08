<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="stylesheet" href="${config.rootPath}/public/layui/css/layui.css">
<script type="text/javascript" src="${config.rootPath}/public/layui/layui.js"></script>
<%@ include file="../static/header.jsp"%>
</head>
<body>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<br />
		<div class="row">
			<div class="col col-12">
				<h3>${node.name}</h3>
				<hr />
				<h4 class="card-title" contenteditable="true" id="editorTitle">输入标题</h4>
				<textarea class="layui-textarea" id="layuieditor"></textarea>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col col-12">
				<a href="#" class="btn btn-primary" id="writeSave">保存并发布</a>
			</div>
		</div>
	</div>
	<script>
		layui.use("layedit", function() {
			var layedit = layui.layedit;
			layedit.set({
				uploadImage : {
					url : "${config.rootPath}/write/imguploadforlayui", //接口url
					type : "post",
				}
			});
			//注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
			var index = layedit.build("layuieditor"); //建立编辑器
			
			$("#writeSave").click(function(e) {
				e.preventDefault();
				$('#postModal').modal('show');
				var elContent = layedit.getContent(index);
				$.post("${config.rootPath}/write/publishlayui", {
					title : $("#editorTitle").html(),
					content : elContent,
					topic : "${topic.BM_ID}",
					price : price
				}, function(data) {
					if (data.status == "0") {
						location.href = '${config.rootPath}/forum/${node.BM_ID}';
					} else {
						alert(data.info);
					}
				}, "json");
				return;
			});
		});
	</script>
	<%@ include file="../static/footer.jsp"%>
	<!-- Modal -->
	<div class="modal fade" id="postModal" tabindex="-1" role="dialog" aria-labelledby="postModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="postModalLabel">提交中</h5>
				</div>
				<div class="modal-body text-center">
					<i class="fa fa-spinner fa-pulse fa-3x fa-fw" aria-hidden="true"></i>
				</div>
			</div>
		</div>
	</div>
</body>
</html>