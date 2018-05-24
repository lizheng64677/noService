<%@page import="com.suyin.decoraterecord.model.*" %>
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
		url : "<c:url value='/decoraterecord/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ "field":'recordId',checkbox:true },
					    { "field": 'type',"title" : '收益类型:0分享，1:购买福券返佣金，2签单奖励',width:$(this).width() * 0.2},
					    { "field": 'publishOpenid',"title" : '微信分享发起者openid',width:$(this).width() * 0.2},
					    { "field": 'publishUserid',"title" : '发布者userid',width:$(this).width() * 0.2},
					    { "field": 'acceptOpenid',"title" : '被邀请人微信openid',width:$(this).width() * 0.2},
					    { "field": 'accptUserid',"title" : '接受人userid',width:$(this).width() * 0.2},
					    { "field": 'priceState',"title" : '券状态 0:已收益 1:待收益',width:$(this).width() * 0.2},
					    { "field": 'state',"title" : '标识：0发起者，1:接收者',width:$(this).width() * 0.2},
						{ "field": 'createTimeString',"title" : 'createTime',width:$(this).width() * 0.2},
					    { "field": 'commissionPrice',"title" : '本次变动金额',width:$(this).width() * 0.2},
					    { "field": 'message',"title" : '收益说明，说明为何变更',width:$(this).width() * 0.2},
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
	window.location.href="<c:url value='/decoraterecord/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/decoraterecord/jumpEdit?id="+rows[0].recordId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].recordId);

	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'decoraterecord/delete?', {id: ids.join(',')},function(data){
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