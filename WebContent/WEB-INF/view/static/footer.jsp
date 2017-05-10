<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br/>
<div class="container">
	<!-- FOOTER -->
	<footer>
	  <p><a href="javascript:scroll(0,0);">Back to top</a></p>
	  <p>&copy; POWERED BY SoulMax Team <!-- 灵溢科技股份有限公司. --> &middot; Last Update Time : ${config.updateTime} &middot; <a href="${config.rootPath}/building">Privacy</a> &middot; <a href="${config.rootPath}/building">Terms</a> &middot; V0.9</p>
	</footer>
</div>
<script>
$(document).ready(function() {
	
	$('a[href="#"]').click(function(e){
		e.preventDefault();
	});
});
</script>