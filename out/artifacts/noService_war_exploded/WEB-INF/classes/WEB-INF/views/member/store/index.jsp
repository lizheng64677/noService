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
		url : "<c:url value='/store/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
						{ "field":'storeId',checkbox:true },
					    { "field": 'storeName',"title" : '门店名称',width:$(this).width() * 0.2},
					    { "field": 'storeTel',"title" : '门店电话',width:$(this).width() * 0.2},
					    { "field": 'cityId',"title" : '城市id',width:$(this).width() * 0.2},
						{ "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
						{ "field": 'updateTime',"title" : '修改时间',width:$(this).width() * 0.2},
						{"field": 'storePicUrl',"title" : '门店图片',width:$(this).width() * 0.2,formatter:function(value,row){ 
					    	var str = "";
					    	if(value!="" || value!=null){
					    	str = "<img style=\"height: 60px;width: 60px;\" src=\""+value+"\"/>";
					         return str;
					    	}
						}}
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
	window.location.href="<c:url value='/store/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/store/jumpEdit?id="+rows[0].storeId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].storeId);

	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'store/delete?', {id: ids.join(',')},function(data){
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
	});
	//跳转修改 
	$("#doUpdate").bind("click",function(){
		doUpdate();
	});
	//跳转添加 
	$("#doAdd").bind("click",function(){
		
		doAdd();
	});
});
</script>  
</html>