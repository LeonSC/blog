<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="header.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="nav.jsp"%>
		<div class="row">
			<div class="col col-12">
				<h3>七牛设置</h3>
				<form method="post" action="${config.rootPath}/admin/setting/qiniusubmit">
					<div class="form-group row">
						<div class="form-group col-3">
							<label class="col-form-label">AccessKey</label>
							<input type="text" class="form-control" name="accesskey" value="${setting.qiniuAccessKey}">
						</div>
						<div class="form-group col-3">
							<label class="col-form-label">SecretKey</label>
							<input type="text" class="form-control" name="secretkey" value="${setting.qiniuSecretKey}">
						</div>
						<div class="form-group col-3">
							<label class="col-form-label">bucket</label>
							<input type="text" class="form-control" name="bucket" value="${setting.qiniuBucket}">
						</div>
						<div class="form-group col-1">
							<label class="col-form-label">启用</label> 
							<select name="onoff" class="form-control">
								<option value="0">不启用</option>
								<option value="1" <c:if test="${setting.qiniuOnOff eq 1}">selected</c:if>>启用</option>
							</select>
						</div>
						<div class="form-group col-1">
							<label class="col-form-label">状态</label> 
							<button type="button" class="form-control">测试 <i class="fa fa-cog fa-spin"></i></button>
						</div>
					</div>
					<div class="form-group row">
						<div class="form-group col-4">
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>