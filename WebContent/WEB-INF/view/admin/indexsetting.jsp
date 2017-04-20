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
				<form enctype="multipart/form-data" method="post" action="${config.rootPath}/admin/index/carousel/submit">
					<div class="form-group">
						<img class="img-fluid" src="${cache.indexPageCarouselList[0].img}" data-src="holder.js/825x160?theme=simple">
						<input type="file" class="form-control-file" name="img">
						<input type="hidden" name="bmid" value="${cache.indexPageCarouselList[0].BM_ID}">
						<small class="form-text text-muted">第一张轮播图.</small>
					</div>
					<div class="form-group">
						<label>链接</label>
						<input type="text" class="form-control" name="link" value="${cache.indexPageCarouselList[0].link}"/>
					</div>
					<button type="submit" class="btn btn-primary">修改</button>
				</form>
			</div>
			<div class="col col-4">
				<form enctype="multipart/form-data" method="post" action="${config.rootPath}/admin/index/carousel/submit">
					<div class="form-group">
						<img class="img-fluid" src="${cache.indexPageCarouselList[1].img}" data-src="holder.js/825x160?theme=simple">
						<input type="file" class="form-control-file" name="img">
						<input type="hidden" name="bmid" value="${cache.indexPageCarouselList[1].BM_ID}">
						<small class="form-text text-muted">第二张轮播图.</small>
					</div>
					<div class="form-group">
						<label>链接</label>
						<input type="text" class="form-control" name="link" value="${cache.indexPageCarouselList[1].link}"/>
					</div>
					<button type="submit" class="btn btn-primary">修改</button>
				</form>
			</div>
			<div class="col col-4">
				<form enctype="multipart/form-data" method="post" action="${config.rootPath}/admin/index/carousel/submit">
					<div class="form-group">
						<img class="img-fluid" src="${cache.indexPageCarouselList[2].img}" data-src="holder.js/825x160?theme=simple">
						<input type="file" class="form-control-file" name="img">
						<input type="hidden" name="bmid" value="${cache.indexPageCarouselList[2].BM_ID}">
						<small class="form-text text-muted">第三张轮播图.</small>
					</div>
					<div class="form-group">
						<label>链接</label>
						<input type="text" class="form-control" name="link" value="${cache.indexPageCarouselList[2].link}"/>
					</div>
					<button type="submit" class="btn btn-primary">修改</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>