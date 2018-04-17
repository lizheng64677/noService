<%@page import="com.suyin.falsedata.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" onclick="doAdd();" data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" onclick="doUpdate();" data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" onclick="doDelete();" data-options="iconCls:'icon-remove'">删除</a>
			<a href="#" class="easyui-linkbutton" onclick="doAddBatch();" data-options="iconCls:'icon-remove'">批量插入</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/falsenouser/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ "field":'userId',checkbox:true },
		    { "field": 'userPhone',"title" : '手机号',width:$(this).width() * 0.2},
// 		    { "field": 'userState',"title" : '用户状态',width:$(this).width() * 0.2},
			{ "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
			{ "field": 'updateTime',"title" : '修改时间',width:$(this).width() * 0.2},
		    { "field": 'headUrl',"title" : '头像',width:$(this).width() * 0.2,formatter:function(value,j){
		    	
		    	return "<img style='width:60px;height:60px;' src="+value+"/>";
		    }},
		    { "field": 'texpShareNum',"title" : '用户所分享的数量',width:$(this).width() * 0.2,formatter:function(value,j){
		    	return value+"次";
		    }},
// 					    { "field": 'ttaskEasyNum',"title" : '轻松赚预留字段(暂不实现 )',width:$(this).width() * 0.2},
// 					    { "field": 'ttaskHelpNum',"title" : '帮我赚预留字段(暂不实现)',width:$(this).width() * 0.2},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

function serchGrid(){
	var queryParams = {directoryName:$("#directoryName").val()};
	$('#mydatagrid').datagrid("load",queryParams); 
}
function doAdd(){
	window.location.href="<c:url value='/falsenouser/jumpAdd'/>";
}

function doAddBatch()
{
	window.location.href="<c:url value='/falsenouser/jumpAddBatch'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/falsenouser/jumpEdit?id="+rows[0].userId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].userId);

	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'falsenouser/delete?', {id: ids.join(',')},function(data){
			if(data.result > 0){
				for ( var i = rows.length-1; i >=0; i--) {
				var index = $('#mydatagrid').datagrid('getRowIndex', rows[i]);
					$('#mydatagrid').datagrid('deleteRow', index);
				}
				rows = null;
				systemShow("系统提示","数据删除成功!");
			}
		},"json");	
	}, null);	
}
</script>  
</html>