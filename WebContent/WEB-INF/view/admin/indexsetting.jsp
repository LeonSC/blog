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
				<h4>首页轮播图设置</h4>
				<small class="form-text text-muted">图片会被强制修改到 <mark>825</mark> X <mark>160</mark>.</small>
			</div>
			<div class="col col-4">
				<form>
					<div class="form-group">
						<img data-src="holder.js/825x160?theme=simple">
						<input type="file" class="form-control-file">
						<small class="form-text text-muted">第一张轮播图.</small>
					</div>
					<button type="submit" class="btn btn-primary">修改</button>
				</form>
			</div>
			<div class="col col-4">
				<form>
					<div class="form-group">
						<img data-src="holder.js/825x160?theme=simple">
						<input type="file" class="form-control-file">
						<small class="form-text text-muted">第一张轮播图.</small>
					</div>
					<button type="submit" class="btn btn-primary">修改</button>
				</form>
			</div>
			<div class="col col-4">
				<form>
					<div class="form-group">
						<img data-src="holder.js/825x160?theme=simple">
						<input type="file" class="form-control-file">
						<small class="form-text text-muted">第一张轮播图.</small>
					</div>
					<button type="submit" class="btn btn-primary">修改</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>