<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  	<script type="text/javascript" src="<c:url value='/resources/js/FusionCharts.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/ExpCharts.js'/>"></script>
    <script type="text/javascript">
    
    	function findChars(){
    		
    		location.href="<c:url value='/sysLog/toTry'/>";
    	}
    	function findDetailInfo(){
    		
			alert("暂未实现");
    	}
    </script>
  </head>
<body>

		<input type="button" onclick="findChars();" value="查看PV数据">
		<input type="button" onclick="findDetailInfo();" value="查看详情数据" />
		<div style="widht:100%;">				
				<div class="list" id="initWxUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initWxUv(3);  
					</script>
				</div>
				<div class="list" id="initAndriodUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initAndriodUv(3); 
					</script>
				</div>
			</div>
			<div style="widht:100%;">				
				<div class="list" id="initIosUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initIosUv(3); 
					</script>
				</div>
				<div class="list" id="initAllUv" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initAllUv(3); 
					</script>
				</div>
			</div>
		
</body>
</html>
