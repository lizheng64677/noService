<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  	<script type="text/javascript" src="<c:url value='/resources/js/FusionCharts.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/ExpZhuanCharts.js'/>"></script>
    <script type="text/javascript">
    
    	function findChars(){
    		
    		location.href="<c:url value='/sysLog/toQsU'/>";
    	}
    	function findDetailInfo(){
    		
      		location.href="<c:url value='/expChars/toQs'/>";
    	}
    </script>
  </head>
<body>
		<input type="button" onclick="findChars();" value="查看UV数据">
		<input type="button" onclick="findDetailInfo();" value="查看详情数据" />
		<div style="widht:100%;">				
				<div class="list" id="initWx" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initWx(9);  
					</script>
				</div>
				<div class="list" id="initAndriod" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initAndriod(9); 
					</script>
				</div>
			</div>
			<div style="widht:100%;">				
				<div class="list" id="initIos" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initIos(9); 
					</script>
				</div>
				<div class="list" id="initAll" style="float:left;width:48%;"> 
					<script type="text/javascript">
					initAll(9); 
					</script>
				</div>
			</div>
		
</body>
</html>
