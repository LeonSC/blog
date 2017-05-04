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
						<label class="col-2 col-form-label">发布到</label>
						<div class="col-6">
						<select class="form-control" name="topic">
							<c:forEach var="item" items="${cache.titleCache}">
								<option value="${item.value.BM_ID}" <c:if test="${item.value.BM_ID eq onviewtopic}">selected="selected"</c:if>>${item.value.name}</option>
							</c:forEach>
						</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">价格</label>
						<div class="col-6">
							<input class="form-control" type="number" name="price">
						</div>
					</div>
				</form>
				<h3>草稿</h3>
				<hr />
				<h4 class="card-title" contenteditable="true" id="editorTitle">
					<c:if test="${empty draft.title}">输入标题</c:if>
					<c:if test="${not empty draft.title}">${draft.title}</c:if>
				</h4>
				<textarea class="layui-textarea" id="layuieditor">
					<c:if test="${empty draft.content}">
						<p>输入内容.</p>
					</c:if>
					<c:if test="${not empty draft.content}">${draft.content}</c:if>
				</textarea>
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
					content : elContent
				}, function(data) {
					if (data.status == "0") {
						location.href = '${config.rootPath}/write/preview';
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