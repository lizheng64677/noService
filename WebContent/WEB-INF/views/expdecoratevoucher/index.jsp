<%@page import="com.suyin.decoratevoucher.model.*" %>
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
		url : "<c:url value='/expdecoratevoucher/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ "field":'id',checkbox:true },
					    { "field": 'name',"title" : '券的名称',width:$(this).width() * 0.2},
					    { "field": 'voucheUrl',"title" : '券封面图',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	return "<img src="+value+" style='width:40px;height:40px'>";
					    }},
					    { "field": 'title',"title" : '简述',width:$(this).width() * 0.2},
					    { "field": 'price',"title" : '优惠券金额',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	return value+"(元)";
					    	}},
					    { "field": 'usePrice',"title" : '返现佣金',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	return value+"(元)"
					    }},
					    { "field": 'type',"title" : '券类型',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if(value==0){
					    		return "福利券";
					    	}else if(value==1){
					    		return "体验券";
					    	}else if(value==2){
					    		return "优惠券";
					    	}else{
					    		return "未知情况";
					    	}
					    }},
					    { "field": 'state',"title" : '状态',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if(value==0){
					    		return "长期";
					    	}else if(value==1){
					    		return "定期";
					    	}else{
					    		return "未知情况";
					    	}
					    }},
					    { "field": 'validityDay',"title" : '有效期',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if(row.state==0){
					    		return "长期";
					    	}else if(row.state==1){
					    		return value+"天";
					    	}else{
					    		return "未知情况";
					    	}
					    }},
						{ "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
						{ "field": 'updateTime',"title" : '修改时间',width:$(this).width() * 0.2},
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
	window.location.href="<c:url value='/expdecoratevoucher/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/expdecoratevoucher/jumpEdit?id="+rows[0].id+"'/>";
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
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'expdecoratevoucher/delete?', {id: ids.join(',')},function(data){
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