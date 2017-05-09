<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<br />
		<div class="row">
			<div class="col col-9">
				<table>
					<tr>
						<td style="width: 25%">
							<div class="card">
								<img class="card-img-top" src="${memAuth.headerIcon}" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes">
								<div class="card-block">
									<h4 class="card-title">${point.user.nickname}</h4>
									<h6 class="card-title"><i class="fa fa-id-card-o" aria-hidden="true"></i> ${point.user.username}</h6>
									<h6 class="card-title"><i class="fa fa-trophy" aria-hidden="true"></i> ${point.user.lv}</h6>
								</div>
							</div>
						</td>
						<td style="width: 1%"></td>
						<td valign ="top">
							<div class="border">
							<h4>${point.title}</h4>
							<p>${point.content}</p>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>