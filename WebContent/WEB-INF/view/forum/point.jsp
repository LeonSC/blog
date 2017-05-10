<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col col-12">
				<a class="btn btn-link btn-sm" href="${config.rootPath}/forum">论坛首页</a> / <a class="btn btn-link btn-sm" href="${config.rootPath}/forum/node/${point.topic}">${forum.blockmap[point.topic].name}</a>
			</div>
		</div>
		<div class="row">
			<div class="col col-9">
				<table style="width: 100%">
					<tr>
						<td class="custom-td" style="width: 25%;"><img class="" src="${point.user.headerIcon}" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes"> <br />
							<div class="col-12">
								<h4>${point.user.nickname}</h4>
								<h6>
									<i class="fa fa-id-card-o" aria-hidden="true"></i> ${point.user.username}
								</h6>
								<h6>
									<i class="fa fa-trophy" aria-hidden="true"></i> ${point.user.lv}
								</h6>
							</div></td>
						<td style="width: 1%"></td>
						<td class="custom-td" style="padding: 1.25rem;">
							<h4>${point.title}</h4>
							<hr />
							<p>${point.content}</p>
						</td>
					</tr>
					<c:if test="${not empty list}">
						<c:forEach var="item" items="${list}">
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td class="custom-td" style="width: 25%;"><img class="" src="${item.user.headerIcon}" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes"> <br />
									<div class="col-12">
										<h4>${item.user.nickname}</h4>
										<h6>
											<i class="fa fa-id-card-o" aria-hidden="true"></i> ${item.user.username}
										</h6>
										<h6>
											<i class="fa fa-trophy" aria-hidden="true"></i> ${item.user.lv}
										</h6>
									</div></td>
								<td style="width: 1%"></td>
								<td class="custom-td" style="padding: 1.25rem;">
									<p>${item.content}</p>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<c:if test="${not empty memAuth}">
					<div class="row">
						<div class="col col-12">
							<form method="post" action="${config.rootPath}/write/forumreply">
								<div class="form-group">
									<small class="form-text text-muted">快速回复</small> <input type="hidden" name="contentid" value="${point.BM_ID}">
									<textarea class="form-control" name="reply" rows="3"></textarea>
								</div>
								<button type="submit" class="btn btn-outline-primary btn-sm">提交</button>
							</form>
						</div>
					</div>
				</c:if>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>