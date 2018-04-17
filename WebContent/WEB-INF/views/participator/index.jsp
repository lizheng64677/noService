<%@page import="com.suyin.participator.model.*" %>
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
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/adminparticipator/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ "field":'id',checkbox:true },
					    { "field": 'name',"title" : '姓名',width:$(this).width() * 0.2},
					    { "field": 'number',"title" : '编号',width:$(this).width() * 0.2},
					    { "field": 'age',"title" : '年龄',width:$(this).width() * 0.2},
// 					    { "field": 'activityDeclaration',"title" : '活动宣言',width:$(this).width() * 0.2},				
					    { "field": 'type',"title" : '类型',width:$(this).width() * 0.2,formatter:function(value,row,index){
							return value==0?"强警标兵":"最美警嫂";
						}},
					    { "field": 'votesNumber',"title" : '得票数',width:$(this).width() * 0.2},
					    { "field": 'headImgUrl',"title" : '头像地址',width:$(this).width() * 0.2,formatter:function(value,row,index){
							return "<img src="+value+" style='width:40px;height:40px'>";
						}},
						{ "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
						{ "field": 'updateTime',"title" : '更新时间',width:$(this).width() * 0.2},
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
	window.location.href="<c:url value='/adminparticipator/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/adminparticipator/jumpEdit?id="+rows[0].id+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);

	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'adminparticipator/delete?', {id: ids.join(',')},function(data){
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