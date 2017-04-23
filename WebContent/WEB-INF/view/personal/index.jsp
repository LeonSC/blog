<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<%@ include file="../static/navFunction.jsp"%>
		<div class="row">
			<div class="col col-9">
				<div class="row">
					<div class="col col-12">
						<h3>${memAuth.username}</h3>
					</div>
					<div class="col col-12">
						<form enctype="multipart/form-data" method="post" action="${config.rootPath}/personal/headericon/update">
							<div class="form-group">
								<small class="form-text text-muted">头像设置.</small>
								<input type="file" class="form-control-file" name="img">
								<input type="hidden" name="bmid" value="${cache.indexPageCarouselList[0].BM_ID}">
							</div>
							<button type="submit" class="btn btn-primary">提交</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>