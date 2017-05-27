<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<img src="http://7xnuan.com1.z0.glb.clouddn.com/9115-11042410010213.jpg" data-src="holder.js/100px130?theme=simple" class="img-fluid custom-max-12rem">
<div class="container">
<div class="row">
	<div class="col col-12">
		<ul class="nav">
			<li class="nav-item"><a class="nav-link" href="${config.rootPath}">首页</a></li>
			<c:forEach var="item" items="${cache.titleCache}">
				<c:choose>
					<c:when test="${item.value.auth.loginVisible==1}">
						<c:if test="${not empty memAuth}">
							<li class="nav-item"><a class="nav-link" href="${config.rootPath}/topic/${item.key}">${item.value.name}</a></li>
						</c:if>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="${config.rootPath}/topic/${item.key}">${item.value.name}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- <li class="nav-item"><a class="nav-link" href="${config.rootPath}/forum">论坛</a></li> -->
		</ul>
	</div>
</div>
</div>