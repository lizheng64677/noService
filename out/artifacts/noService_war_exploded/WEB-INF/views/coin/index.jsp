<%@page import="com.suyin.coin.model.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
			<label>状态：</label>
			<select id="status" style="width:100px;">
				<option value="-1" selected="selected">全部状态</option>
				<option value="0" >新申请</option>
				<option value="1" >同意</option>
				<option value="2" >拒绝</option>
			</select>
			<a href="#" class="easyui-linkbutton" onclick="searchGrid();" data-options="iconCls:'icon-search'">查找</a>
	  		<a href="#" class="easyui-linkbutton" onclick="doAgree();" data-options="iconCls:'icon-edit'">同意</a>
			<a href="#" class="easyui-linkbutton" onclick="doReject();" data-options="iconCls:'icon-remove'">拒绝</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
	<div id="goldenList" title="用户金币明细" style="width:500px;height:550px;">
		<div id="goldengrid" fit="true"></div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	
	$("#status").combobox();
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/nousercointeller/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ field:'logId',checkbox:true }, 
 			{ "field": 'userPhone',"title" : '用户手机号',width:$(this).width() * 0.2},
		    { "field": 'coin',"title" : '提取的金币数',width:$(this).width() * 0.2},
		    { "field": 'money',"title" : '换多少钱',width:$(this).width() * 0.2},
		    { "field": 'status',"title" : '状态',width:$(this).width() * 0.2,formatter:function(value){
		    	if(value==0) return "新建";
		    	else if(value==1) return "同意";
		    	else if(value==2) return "拒绝";
		    }},
			{ "field": 'createTime',"title" : '申请时间',width:$(this).width() * 0.2},
			{ "field": 'updateTime',"title" : '状态修改时间',width:$(this).width() * 0.2},
			{ "field": 'proSet',"title" : '操作',width:$(this).width() * 0.2,formatter:function(value,row,index){
			    	
			    	return "<a href='javascript:void(0)' id='queryGoldenRecord' onclick='queryGoldenRecord("+row.userId+")'>查看金币记录</a>";
			    }},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

//查看金币历史纪录 
function queryGoldenRecord(userId){

	$('#goldengrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/nousercointeller/queryGoldenList'/>?userId="+userId,
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
 			{ "field": 'desc',"title" : '描述', align:'center', width:$(this).width() * 0.8},
		    { "field": 'num',"title" : '金币数', align:'center', width:$(this).width() * 0.2},
		]],
		pagination : true,
		rownumbers : true
	});
	$("#goldenList").dialog({modal:true,resizable:true});
}

function searchGrid(){
	var sc={};
	var status=$("#status").combobox("getValue");
	if(status!='-1') sc["status"]=status;
	$('#mydatagrid').datagrid("reload",sc); 
}

function doAgree(){
	doUpdate("1");
}
function doReject(){
	doUpdate("2");
}

function doUpdate(status){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		if(status==1&&rows[i].status!=0){
			$.messager.alert("", "只能同意新建状态的！", true, "warning");
			return;
		}
		if(status==2&&rows[i].status!=0){
			$.messager.alert("", "只能拒绝新建状态的！", true, "warning");
			return;
		}
		ids.push(rows[i].logId);
	}
	$.messager.confirm('确认', '是否确定要同意或者拒绝?', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'nousercointeller/update', {ids: ids.join(','),status:status},function(data){
			if(data.result==1){
				for(var i=0;i<rows.length;i++){
					var index = $('#mydatagrid').datagrid('getRowIndex', rows[i]);
					$('#mydatagrid').datagrid("updateRow",{
						index:index,
						row:{status:status}
					});
				}
				searchGrid();
			}
		},"json");	
	}, null);	
}
</script>  
</html>