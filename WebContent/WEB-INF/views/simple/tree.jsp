<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  		<script type="text/javascript">
  		$(function() {
  			//列表
  			$('#mydatagrid').treegrid({
  			    url:SITE_BASE_PATH+"tree/synTreeListByPage?parentId=0",
  			    idField:'id',
  			    treeField:'name',
  			 	pageSize :5, 
  				pageList : [5,20,50],
  				pagination : true,
  				rownumbers : true,
	  			onBeforeLoad : function(node, param) {
	  				//异步加载树形菜单分页必须用如下写法不然分页会出现连接错乱(切记)
	  				if (node != null) {
	  					$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"tree/synTreeList?parentId=" + node.id;
	  				}else{
						$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"tree/synTreeListByPage?parentId=0";
					}
	  				return true;
	  			},
  			    columns : [[
  							{ field:'id',checkbox:true }, 
  							{
  								field : 'name',
  								title : '名称'
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
