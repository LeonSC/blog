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
				<form method="post" action="${config.rootPath}/admin/topic/remove/moveart">
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">当前主题</label>
						<div class="col-sm-10">${topic.name}</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">剩余帖子数量</label>
						<div class="col-sm-10">${restArt}</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">转移至</label>
						<div class="col-sm-10">
							<c:forEach var="item" items="${cache.titleCache}" varStatus="i">
							<div class="form-check form-check-inline">
								<label class="form-check-label"> <input class="form-check-input" type="radio" name="to" value="${item.value.BM_ID}"> ${item.value.name}
								</label>
							</div>
							</c:forEach>
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-sm">提交</button>
				</form>
				<hr/>
				<c:if test="${restArt eq 0}"><a class="btn btn-danger btn-sm" role="button" href="${config.rootPath}/admin/topic/remove/action">删除</a></c:if>
			</div>
		</div>
	</div>
</body>
</html>