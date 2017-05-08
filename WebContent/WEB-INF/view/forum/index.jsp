<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<br/>
		<div class="row">
			<div class="col col-9">
				<c:forEach var="item" items="${forum.block}">
					<div class="row">
						<div class="col col-6">
							<div class="media">
								<!-- <img class="d-flex align-self-center mr-3" src="${item.value.icon}" style="height: 5rem; width: 5rem" /> -->
								<div class="media-body">
									<h4 style="padding: 0; margin: 0">${item.value.name}</h4>
									<c:if test="${not empty item.value.intro}">
										<small>${item.value.intro}</small>
									</c:if>
									<hr style="margin: .2rem 0 0" />
									<small style="line-height: 0; padding: 0; margin: 0"><i class="fa fa-user" aria-hidden="true"></i> </small>
								</div>
							</div>
						</div>
					</div>
					<br />
					<div class="row">
						<c:forEach var="sub" items="${item.value.block}">
							<div class="col col-6">
								<div class="media">
									<img class="d-flex align-self-center mr-3" src="${sub.value.icon}" style="height: 5rem; width: 5rem" />
									<div class="media-body">
										<a class="btn btn-link" href="${config.rootPath}/forum/${sub.value.BM_ID}" style="line-height: 0; padding: 0">${sub.value.name}</a>
										<c:if test="${not empty sub.value.intro}">
											<br style="margin: 0" />
											<small>${sub.value.intro}</small>
										</c:if>
										<hr style="margin: .2rem 0 0" />
										<small style="line-height: 0; padding: 0; margin: 0"><i class="fa fa-user" aria-hidden="true"></i> </small>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<br/>
					<hr />
					<br/>
				</c:forEach>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>