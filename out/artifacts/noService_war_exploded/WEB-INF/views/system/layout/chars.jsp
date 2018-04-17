<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  	<script type="text/javascript" src="<c:url value='/resources/js/FusionCharts.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/MainCharts.js'/>"></script>
  </head>
<body>

		<div style="widht:100%;">				
				<div class="list" id="initWxUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initWxUv();  
					</script>
				</div>
				<div class="list" id="initAndriodUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initAndriodUv(); 
					</script>
				</div>
			</div>
			<div style="widht:100%;">				
				<div class="list" id="initIosUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initIosUv(); 
					</script>
				</div>
				<div class="list" id="initAllUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initAllUv(); 
					</script>
				</div>
			</div>
		
</body>
</html>
