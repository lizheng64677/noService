<%@page import="com.suyin.decorate.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >  
	<div id="tool">
			 类别:
			<select id="type" class="input" style="width:120px">
			<option value="-1">---请选择---</option>
			<option value="0">---姓名---</option>
			<option value="1">---手机号---</option>
			<option value="2">---微信昵称---</option>
			</select>
			内容:<input type="text" class="input " name="text" id="text" style="width:180px"/>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="serchGrid();" data-options="iconCls:'icon-search'">查询</a>
			<div style="float:right;padding-right:18px;">
			<a href="#" class="easyui-linkbutton" onclick="reviewOrder();" data-options="iconCls:'icon-remove'">提现审批</a>
			</div> 
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
					    { "field": 'userName',"title" : '用户姓名',width:$(this).width() * 0.2},
					    { "field": 'nickName',"title" : '微信昵称',width:$(this).width() * 0.2},
					    { "field": 'userPhone',"title" : '手机号',width:$(this).width() * 0.2},
					    { "field": 'alipayNumber',"title" : '支付宝账号',width:$(this).width() * 0.2},	   
					    { "field": 'withdrawPrice',"title" : '提现金额（元）',width:$(this).width() * 0.2},
					    { "field": 'state',"title" : '提现状态',width:$(this).width() * 0.2,
					    	formatter:function(value,row,index){
						    	if(value==0){
						    		return "<span style='color:red'>待审核</span>";
						    	}else if(value==1){
						    		return "<span style='color:#228B22'>审核通过</span>";
						    	}else{
						    		return "审核失败";
						    	}
						    }},
						
// 					    { "field": 'reviewUser',"title" : '审核人编号',width:$(this).width() * 0.2},
					    { "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
						{ "field": 'reviewTime',"title" : '审核时间',width:$(this).width() * 0.2},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

function serchGrid(){
	var queryParams = {type:$("#type").val(),text:$("#text").val()};
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
	for(var i=0;i<rows.length;i++){
		var state = rows[i].state
		if(state != 0){
			$.messager.alert("", "只能审核待审核状态的订单！", true, "warning");
			return;
		}
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