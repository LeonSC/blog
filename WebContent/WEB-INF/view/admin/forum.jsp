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
				<form enctype="multipart/form-data" method="post" action="${config.rootPath}/admin/forum/submit">
					<div class="form-group">
						<label>标题</label> <input type="text" class="form-control" name="name" placeholder="总标题" />
					</div>
					<div class="form-group">
						<label>简介</label> <input type="text" class="form-control" name="intro" placeholder="" />
					</div>
					<div class="form-group">
						<label>图标</label> <input type="file" class="form-control-file" name="file"/>
					</div>
					<div class="form-group">
						<label>排序</label> <input type="number" class="form-control" name="order" placeholder="" />
					</div>
					<fieldset class="form-group">
						<legend>可见设置</legend>
						<div class="form-check">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" name="loginvisible" value="0" checked> 任意可见
							</label>
						</div>
						<div class="form-check">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" name="loginvisible" value="1"> 登录可见
							</label>
						</div>
					</fieldset>
					<button type="submit" class="btn btn-primary">提交</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>