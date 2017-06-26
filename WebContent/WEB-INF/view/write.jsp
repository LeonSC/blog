<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="static/header.jsp"%>
<script src="//cdn.bootcss.com/medium-editor/5.23.0/js/medium-editor.min.js"></script>
<link rel="stylesheet" href="//cdn.bootcss.com/medium-editor/5.23.0/css/medium-editor.min.css" type="text/css" media="screen">
<link rel="stylesheet" href="//cdn.bootcss.com/medium-editor/5.23.0/css/themes/bootstrap.min.css" type="text/css" media="screen">
<link rel="stylesheet" href="${config.rootPath}/public/medium-editor-insert-plugin-2.4.0/medium-editor-insert-plugin.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css" media="screen">
<!-- JS -->
<script src="//cdn.bootcss.com/handlebars.js/4.0.6/handlebars.runtime.min.js"></script>
<script src="//cdn.bootcss.com/jquery-sortable/0.9.13/jquery-sortable-min.js"></script>
<script src="//cdn.bootcss.com/blueimp-file-upload/9.18.0/js/vendor/jquery.ui.widget.min.js"></script>
<script src="//cdn.bootcss.com/blueimp-file-upload/9.18.0/js/jquery.iframe-transport.min.js"></script>
<script src="//cdn.bootcss.com/blueimp-file-upload/9.18.0/js/jquery.fileupload.min.js"></script>
<script src="${config.rootPath}/public/medium-editor-insert-plugin-2.4.0/medium-editor-insert-plugin.min.js"></script>
<body>
	<%@ include file="static/nav.jsp"%>
	<div class="container">
		<br />
		<div class="row">
			<div class="col col-12">
				<button type="button" class="btn">草稿</button>
				<a href="#" class="btn btn-primary" id="writeSave">保存并预览</a>
				<a href="${config.rootPath}/write/preview" class="btn btn-primary">预览</a>
			</div>
			<div class="col col-12">
				<br/>
				<h4 contenteditable="true" id="editorTitle" class="text-center">
					<c:if test="${empty draft.title}">输入标题</c:if>
					<c:if test="${not empty draft.title}">${draft.title}</c:if>
				</h4>
				<hr />
				<div class="card-text" id="editor">
					<c:if test="${empty draft.content}"></c:if>
					<c:if test="${not empty draft.content}">${draft.content}</c:if>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			var editor = new MediumEditor('#editor', {
				toolbar : {
					buttons : [ 'bold', 'italic', 'underline', 'anchor', 'h2', 'justifyLeft', 'justifyCenter' ],
				},
				placeholder : {
					text : '请在这里输入您的文章'
				},
				customClassOption : "card-text",
				imageDragging : false
			});
			$('#editor').css("min-height","40rem");
			var op = {};
			$('#editor').mediumInsert({
				editor : editor,
				addons : { // (object) Addons configuration
					images : { // (object) Image addon configuration
						deleteScript : '${config.rootPath}/write/imgDelete', // (string) A relative path to a delete script
						deleteMethod : 'POST',
						fileDeleteOptions : {}, // (object) extra parameters send on the delete ajax request, see http://api.jquery.com/jquery.ajax/
						fileUploadOptions : { // (object) File upload configuration. See https://github.com/blueimp/jQuery-File-Upload/wiki/Options
							url : '${config.rootPath}/write/imgUpload', // (string) A relative path to an upload script
							acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i
						},
						messages : {
							acceptFileTypesError : 'This file is not in a supported format: ',
							maxFileSizeError : 'This file is too big: '
						},
						uploadCompleted : function($el, data) {console.log("上传完成")},
						uploadFailed : function(uploadErrors, data) {} // (function) Callback function called when upload failed
					}
				}
			});
			$("#writeSave").click(function(e) {
				e.preventDefault();
				$('#postModal').modal('show');
				var allContents = editor.serialize();
				var elContent = allContents["editor"].value;
				$.post("${config.rootPath}/write/savedraft", {
					title : $("#editorTitle").html(),
					content : elContent
				},
					function(data) {
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
	<%@ include file="static/footer.jsp"%>
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