<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col col-12">
		<img src="http://otakula.com/imgBlogFolder/1110x130.png" data-src="holder.js/100px130?theme=simple" class="img-fluid custom-max">
	</div>
</div>
<div class="row">
	<div class="col col-12">
		<ul class="nav">
			<li class="nav-item"><a class="nav-link" href="${config.rootPath}">首页</a></li>
			<c:forEach var="item" items="${cache.titleCache}">
			<li class="nav-item"><a class="nav-link" href="${config.rootPath}/topic/${item.BM_ID}">${item.name}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>