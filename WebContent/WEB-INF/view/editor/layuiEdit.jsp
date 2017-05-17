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
				<form id="layuiform">
					<div class="form-group row">
						<label class="col-2 col-form-label">所在</label>
						<label class="col-6 col-form-label h4">${cache.titleCache[c.topic].name}</label>
					</div>
				</form>
				<h3>草稿</h3>
				<hr />
				<h4 class="card-title" contenteditable="true" id="editorTitle">
					<c:if test="${empty c.title}">输入标题</c:if>
					<c:if test="${not empty c.title}">${c.title}</c:if>
				</h4>
				<textarea class="layui-textarea" id="layuieditor">
					<c:if test="${empty c.content}">
						<p>输入内容.</p>
					</c:if>
					<c:if test="${not empty c.content}">${c.content}</c:if>
				</textarea>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col col-12">
				<a href="#" class="btn btn-primary" id="editSave">修改</a>
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
				},
				height:700
			});
			//注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
			var index = layedit.build("layuieditor"); //建立编辑器
			
			$("#editSave").click(function(e) {
				e.preventDefault();
				//var price = $("#layuiformprice").val();
				//校验区
				//if(price<0||price>10000)
				//{
					//return 0;
				//}
				$('#postModal').modal('show');
				var elContent = layedit.getContent(index);
				$.post("${config.rootPath}/write/editlayui", {
					content : elContent,
					bmid : "${c.BM_ID}"
				}, function(data) {
					if (data.status == "0") {
						location.href = '${config.rootPath}/topic/art/${c.BM_ID}';
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