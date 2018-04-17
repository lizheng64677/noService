<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout" >   
	<div id="tool">
		<tr>
			操作名称:<input id="name"  type="text" class="easyui-validatebox"  />  
			<a href="#" onclick="serchGrid();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		</tr>
		<tr>
			<a href="#" class="easyui-linkbutton" onclick="hotCity();" data-options="iconCls:'icon-remove'">设置为热门城市</a>
		</tr>
		<tr>
			<a href="#" class="easyui-linkbutton" onclick="noHotCity();" data-options="iconCls:'icon-remove'">取消设置为热门城市</a>
		</tr>
	</div>
	<div id="mydatagrid" fit="true"></div>
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/city/findHotCityByPage'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ field:'id',checkbox:true }, 
			{
				field : 'name',
				title : '城市名称',
				width:$(this).width() * 0.2
			},{
				field :'level',
				title:'级别',
				"width":$(this).width() * 0.2
			},{
				field : 'upid',
				title : '从属于',
				width:$(this).width() * 0.2,
			},{
				field : 'hotCity',
				title : '是否是热门城市',
				width:$(this).width() * 0.2,
				formatter:function(value,row,index){
                	if(0==value){
                		return "是";
                	}else if(1==value){
                		return "否";
                	}else if(null==value){
                		return "否";
                	}
                }
			}
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

function serchGrid(){
	var queryParams = {name:$("#name").val()};
	$('#mydatagrid').datagrid("load",queryParams); 
}

function hotCity(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(null==rows || 0==rows.length){
		$.messager.alert('提示','请选择数据！','info');
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('确认', '是否确定要设为热门城市!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'city/hotCity', {removeIds:ids.join(','),hotCity:0},function(data){
			if(data.message > 0){
				$('#mydatagrid').datagrid("load"); 
				rows = null;
				$.messager.show({
					title:'系统消息',
					msg:'设置成功!',
					timeout:2000,
					showType:'slide'
				});
			}
		},"json");
	},null);
}

function noHotCity(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(null==rows || 0==rows.length){
		$.messager.alert('提示','请选择数据！','info');
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('确认', '是否确定要取消设为热门城市!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'city/hotCity?', {removeIds:ids.join(','), hotCity:1},function(data){
			if(data.message > 0){
				$('#mydatagrid').datagrid("load"); 
				rows = null;
				$.messager.show({
					title:'系统消息',
					msg:'设置成功!',
					timeout:2000,
					showType:'slide'
				});
			}
		},"json");
	},null);
}
</script>  
</html>