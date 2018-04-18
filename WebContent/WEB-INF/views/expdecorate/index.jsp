<%@page import="com.suyin.decorate.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" onclick="doAdd();" data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" onclick="doUpdate();" data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" onclick="doDelete();" data-options="iconCls:'icon-cancel'">删除</a>
			<div style="float:right;margin-right:18px;">

			&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" onclick="doStart();" data-options="iconCls:'icon-remove'">启动活动</a> 
			<a href="#" class="easyui-linkbutton" onclick="doStop();" data-options="iconCls:'icon-remove'">停止活动</a>			
			</div>
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/adminexpdecorate/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
						{ "field":'id',checkbox:true },
					    { "field": 'name',"title" : '活动名称',width:$(this).width() * 0.2},
					    { "field": 'title',"title" : '活动标题',width:$(this).width() * 0.2},						
					    { "field": 'shareTitle',"title" : '分享标题',width:$(this).width() * 0.2},
					    { "field": 'shareImg',"title" : '分享图片',width:$(this).width() * 0.2,formatter:function(value,row,index){
							return "<img src="+value+" style='width:40px;height:40px'>";
						}},
					    { "field": 'beginMoney',"title" : '随机金额起',width:$(this).width() * 0.2},
					    { "field": 'endMoney',"title" : '随机金额止',width:$(this).width() * 0.2},
					    { "field": 'status',"title" : '活动状态',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if(value==0){
					    		return "<span style='color:red'>未启动</span>";
					    	}else if(value==1){
					    		return "<span style='color:#228B22'>已启动</span>";
					    	}else{
					    		return "未知情况";
					    	}
					    }},
						{ "field": 'beginTime',"title" : '开始时间',width:$(this).width() * 0.2},
						{ "field": 'endTime',"title" : '结束时间',width:$(this).width() * 0.2},	
					 
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

//开始活动 
function doStart(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);

	}
	if(ids.length > 1){
		$.messager.alert("提示", "该功能暂不支持批量操作！", true, "warning");
		return;
	}
	$.messager.confirm('确认', '是否确定要开始活动吗？', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'adminexpdecorate/startExp?', {id: ids.join(','),status:"1"},function(data){
			if(data.message=="success"){
				$('#mydatagrid').datagrid('reload');
				$.messager.show({
					title:'系统消息',
					msg:'活动启动成功!',
					timeout:2000,
					showType:'slide'
				});
			}else {
				$.messager.alert("提示", "活动状态更新失败！", true, "warning");
				return false;
			}
		},"json");	
	},null);
}

//结束活动 
function doStop(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);

	}
	if(ids.length > 1){
		$.messager.alert("提示", "该功能暂不支持批量操作！", true, "warning");
		return;
	}
	$.messager.confirm('确认', '是否确定要结束活动吗？', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'adminexpdecorate/stopExp?', {id: ids.join(','),status:"0"},function(data){
			if(data.message=="success"){
				$('#mydatagrid').datagrid('reload');
				$.messager.show({
					title:'系统消息',
					msg:'活动停止成功!',
					timeout:2000,
					showType:'slide'
				});
			}else if(data.message=="error"){
				
				$.messager.alert("提示", "活动状态修改失败", true, "warning");
			}else if(data.message=="invalidParam"){
				
				$.messager.alert("提示", "参数值传递有误！", true, "warning");
			}
		},"json");	
	}, null);	
}

function serchGrid(){
	var queryParams = {directoryName:$("#directoryName").val()};
	$('#mydatagrid').datagrid("load",queryParams); 
}
function doAdd(){
	window.location.href="<c:url value='/adminexpdecorate/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);

	}
	$.post(SITE_BASE_PATH+'adminexpdecorate/findExpInfoById?', {id: ids.join(',')},function(data){	
		if(data.value.status== 0){
			window.location.href="<c:url value='/adminexpdecorate/jumpEdit?id="+rows[0].id+"'/>";
		}else{
			$.messager.alert("提示", "活动已经启动无法修改，请停止后再次操作", true, "warning");
		}
	},"json");	

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
	if(ids.length > 1){
		$.messager.alert("提示", "该功能暂不支持批量操作！", true, "warning");
		return;
	}
	$.post(SITE_BASE_PATH+'adminexpdecorate/findExpInfoById?', {id: ids.join(',')},function(data){
		if(data.value.status== 0){
			$.messager.confirm('确认', '是否确定要删除!', function(data){
				if(!data){
					return;
				}
				$.post(SITE_BASE_PATH+'adminexpdecorate/delete?', {id: ids.join(',')},function(data){
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
		}else{
			$.messager.alert("提示", "活动已经启动无法删除，请停止后再次操作", true, "warning");
		}
	},"json");	

}
</script>  
</html>