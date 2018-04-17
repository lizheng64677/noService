<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tdwidth{width:350px;}
</style>
</head>
<body class="easyui-layout">

<div data-options="region:'west'" style="width:30%;">
	<div class="easyui-panel" title="角色组" fit="true" border="false">
		<div id="tt_role"></div>
	</div>
</div>  

<div data-options="region:'east'" style="width:70%;">
	<div class="easyui-panel" title="菜单组" fit="true" border="false">
		<div id="tt"></div>
	    <div style="padding:30px 50px;display:none;" id="confimButton">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="permissionFn.submitPerms()">确认提交</a>
	    </div>
	</div> 
</div>
<script type="text/javascript" src="<c:url value='/resources/js/commonConfig/permission.js'/>"></script>
<script type="text/javascript">
initRoleTree();
function initRoleTree(type){
	$("#tt_role").tree({
		url:"<c:url value='/permission/synRoleList'/>",
		loadFilter:function(data){
			return convertEasyUIParentChildTree(data.jsonData,true);
		},
		onClick:function(node){
			permissionFn.queryPerms(node.id);
		}
	});
}

</script>
</body>
</html>
