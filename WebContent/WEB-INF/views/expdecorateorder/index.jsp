<%@page import="com.suyin.decorate.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
			<a href="#" class="easyui-linkbutton" onclick="doDelete();" data-options="iconCls:'icon-remove'">删除</a>
			<a href="#" class="easyui-linkbutton" onclick="reviewOrder();" data-options="iconCls:'icon-remove'">提现审批</a>
			<a href="#" class="easyui-linkbutton" onclick="doDelete();" data-options="iconCls:'icon-remove'">导出</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/expdecorateorder/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ "field":'orderId',checkbox:true },
					    { "field": 'openid',"title" : '微信id',width:$(this).width() * 0.2},
					    { "field": 'userId',"title" : '用户id',width:$(this).width() * 0.2},
					    { "field": 'withdrawPrice',"title" : '提现金额（元）',width:$(this).width() * 0.2},
					    { "field": 'state',"title" : '提现状态',width:$(this).width() * 0.2,
					    	formatter:function(value,row,index){
						    	if(value==0){
						    		return "<span style='color:red'>审核中</span>";
						    	}else if(value==1){
						    		return "<span style='color:#228B22'>审核通过</span>";
						    	}else{
						    		return "审核失败";
						    	}
						    }},
						
					    { "field": 'reviewUser',"title" : '审核人编号',width:$(this).width() * 0.2},
					    { "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
						{ "field": 'reviewTime',"title" : '审核时间',width:$(this).width() * 0.2},
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
	window.location.href="<c:url value='/expdecorateorder/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/expdecorateorder/jumpEdit?id="+rows[0].orderId+"'/>";
}
function reviewOrder(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/expdecorateorder/jumpReview?id="+rows[0].orderId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].orderId);

	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'expdecorateorder/delete?', {id: ids.join(',')},function(data){
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