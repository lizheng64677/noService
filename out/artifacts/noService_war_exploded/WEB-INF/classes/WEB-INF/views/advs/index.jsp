<%@page import="com.suyin.advs.model.*" %>

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
		url : "<c:url value='/advs/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ field:'advId',checkbox:true }, 
					    { "field": 'advName',"title" : '名称',width:$(this).width() * 0.2},
					    { "field": 'picUrl',"title" : '图片',width:$(this).width() * 0.2,formatter:function(value){
					    	return "<img src='"+value+"' height='50' width='120'/>";
					    }},
					    { "field": 'linkUrl',"title" : '链接地址',width:$(this).width() * 0.2},
					    { "field": 'picIndex',"title" : '显示顺序',width:$(this).width() * 0.2},
					    { "field": 'type',"title" : '类型',width:$(this).width() * 0.2,formatter:function(value){
					    	if(value==0) return "首页广告";
					    	else if(value==1) return "全民赚广告";
					    	else if(value==2) return "齐心赚广告";
					    	else if(value==3) return "抽奖式广告";
					    	else if(value==4) return "人气式广告";
					    	else if(value==5) return "试用式广告";
					    	else if(value==6) return "兑换式广告";
					    }},
						{ "field": 'createTimeString',"title" : '创建时间',width:$(this).width() * 0.2}
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
	window.location.href="<c:url value='/advs/addOrEdit'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/advs/addOrEdit?advId="+rows[0].advId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].advId);
	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'advs/delete?', {id: ids.join(',')},function(data){
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