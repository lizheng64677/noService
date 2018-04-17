<%@page import="com.suyin.member.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" id="doAdd" data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" id="doUpdate" data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" id="doDelete" data-options="iconCls:'icon-remove'">删除</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/category/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
				{"field":'cgId',checkbox:true }, 
				{"field": 'cgName',"title" : '品类名称',width:$(this).width() * 0.2},
				{"field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
				{"field": 'updateTime',"title" : '修改时间',width:$(this).width() * 0.2},
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
	window.location.href="<c:url value='/category/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/category/jumpEdit?id="+rows[0].cgId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].cgId);
	}
	$.messager.confirm('确认', '是否确定要删除!', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'category/delete?', {id: ids.join(',')},function(data){
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
$(function(){
	
	//删除
	$("#doDelete").bind("click",function(){
		
		doDelete();
	})
	//跳转添加 
	$("#doAdd").bind("click",function(){
		
		 doAdd(); 
		
	});
	//跳转修改 
	$("#doUpdate").bind("click",function(){
		
		doUpdate();
	});
	
});
</script>  
</html>