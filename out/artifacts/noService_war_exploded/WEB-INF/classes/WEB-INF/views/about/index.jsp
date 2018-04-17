<%@page import="com.suyin.about.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool" class="datagridsearch">
		    <select id="type" style="width:100px;">
			   <option value="-1" selected="selected">全部</option>
			   <option value="0">平台简介</option>
			   <option value="1">用户协议</option>
			   <option value="2">新手指南</option>
			</select>
		    <a href="#" class="easyui-linkbutton" onclick="searchGrid();" data-options="iconCls:'icon-search'">查找</a>
	  		<a href="#" class="easyui-linkbutton" onclick="doAdd();" data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" onclick="doUpdate();" data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" onclick="doDelete();" data-options="iconCls:'icon-remove'">删除</a>	
<!-- 			<input id="searchbox" style="width:300px"></input>  -->
<!-- 	        <div id="searchboxSplit" style="width:100px">  -->
<!-- 			    <div data-options="name:'dictionary_code'">类型</div>  -->
<!-- 	        </div> -->
	</div>
	<div id="mydatagrid" fit="true"></div>
</body>
<style>
img{
height:80px;
}
</style>
<script type="text/javascript">

	$(function() {		
	$("#type").combobox();	

		$('#mydatagrid').datagrid({
			fitColumns : true,
			url : "<c:url value='/about/findAboutBypage'/>",
			nowrap : false,
			method : "POST",
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
	
	
			columns : [ [ {
				field : 'aboutId',
				checkbox : true
			}, 

			{
				"field" : 'type',
				"title" : '类型',
				width : $(this).width() * 0.2,formatter:function(value){
			    	if(value==0) {
			    		return "平台简介";
			    		}
			    	else if(value==1) {
			    		return "用户协议";
			    	}
			    	else if(value==2){
			    		return "新手指南";
			    	};
			    }},
	
// 			{
// 				"field" : 'content',
// 				"title" : '内容',
// 				width : $(this).width() * 0.2,formatter:function(value){
// 					return value;
// 				}
// 				},
			{
				"field" : 'createTimeString',
				"title" : '创建时间',
				width : $(this).width() * 0.2
			}, {
				"field" : 'updateTimeString',
				"title" : '更新时间',
				width : $(this).width() * 0.2
			} ] ],
			toolbar : '#tool',
			pagination : true,
			rownumbers : true
		});
	});

	function searchGrid() {
        var sc={};
		var type=$("#type").combobox("getValue");
		if(type!='-1') {
			sc["type"]=type;
			}
		$('#mydatagrid').datagrid("reload",sc); 
	}
	function doAdd() {
		window.location.href = "<c:url value='/about/findAboutById'/>";
	}
	function doUpdate() {
		var rows = $("#mydatagrid").datagrid("getSelections");
		if (rows.length != 1) {
			$.messager.alert("", "请选择一条数据！", true, "warning");
			return;
		}
		window.location.href="<c:url value='/about/findAboutById?aboutId="+rows[0].aboutId+"'/>";
	}
	function doDelete(){
		var rows=$("#mydatagrid").datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert("", "请选择数据！", true, "warning");
			return;
		}
		var ids = [];
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].aboutId);
		}
		$.messager.confirm('确认', '是否确定要删除!', function(data){
			if(!data){
				return;
			}
			$.post(SITE_BASE_PATH+'about/deleteAbout?', {id:ids.join(',')},function(data){
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