<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  		<script type="text/javascript">
  		$(function() {
  			$('#mydatagrid').treegrid({
  			    url:SITE_BASE_PATH+"city/doTreeCity?parentId=0",
  			    idField:'id',
  			    treeField:'name',
  			 	pageSize :10, 
  				pageList : [10,20,50],
  				pagination : true,
  				rownumbers : true,
	  			onBeforeLoad : function(node, param) {
	  				if (node != null) {
	  					$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"city/findCityTreeByUpid?parentId=" + node.id;
	  				}else{
						$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"city/doTreeCity?parentId=0";
					}
	  				return true;
	  			},
  			    columns : [[
  							{ field:'id',checkbox:true }, 
  							{
  								field : 'name',
  								title : '省市名称'
  							}
  						]],
  				toolbar :'#tool',
  				pagination : true,
  				rownumbers : true
  			});

  		});
  		</script>
  </head>
  <body>
  		<div id="mydatagrid" fit="true"></div>   
</body>
</html>
