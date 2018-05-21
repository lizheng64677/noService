<%@page import="com.suyin.uservoucher.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
		<div id="tool">
		<!-- 0:未使用，1:已使用，2已过期作废   -->
			 券状态:
			<select id="type" class="input" style="width:120px">
			<option value="-1">---请选择---</option>
			<option value="0">---未使用---</option>
			<option value="1">---已使用---</option>
			<option value="2">---已作废---</option>
			</select>
			券号:<input type="text" class="input " name="voucherCode" id="voucherCode" style="width:180px"/>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="serchGrid();" data-options="iconCls:'icon-search'">查询</a>
			<div style="float:right;padding-right:18px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="doUpdate();" data-options="iconCls:'icon-remove'">消券处理</a> 
			</div> 
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/expdecorateuservoucher/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ "field":'id',checkbox:true },
					    { "field": 'name',"title" : '券名称',width:$(this).width() * 0.2},
					    { "field": 'vourcheCode',"title" : '券号',width:$(this).width() * 0.2},
					    { "field": 'price',"title" : '金额',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	return value+"(元)";
					    }},
					    { "field": 'usePrice',"title" : '返佣金额',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	return value+"(元)";
					    }},
					    { "field": 'headImg',"hidden" : '',width:$(this).width() * 0.2},
					    { "field": 'nickName',"title" : '购买者',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	return "<img src="+row.headImg+" style='width:40px;height:40px'>"+""+value;
					    }},		
					    { "field": 'uheadImg',"hidden" : '',width:$(this).width() * 0.2},						
					    { "field": 'unickName',"title" : '推荐人',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	return "<img src="+row.uheadImg+" style='width:40px;height:40px'>"+""+value;
					    }},
					    { "field": 'type',"title" : '券类型',width:$(this).width() * 0.2,formatter:function(value,row,index){
  						 	if(0==value){					    		
					    		return "福利券";
					    	}else if(1==value){
					    		return "体验券";
					    	}else if(2==value){
					    		return "优惠券";
					    	}
					    }},

					    { "field": 'state',"title" : '状态',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if(0==value){
					    		
					    		return "未使用";
					    	}else if(1==value){
					    		return "已使用";
					    	}else if(2==value){
					    		return "已作废";
					    	}
					    }},
						{ "field": 'createTime',"title" : '购买时间',width:$(this).width() * 0.2},
						{ "field": 'useTime',"title" : '使用时间',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if(0==row.state){					    		
					    		return "--";
					    	}else if(1==row.state){
					    		return value;
					    	}else if(2==row.state){
					    		return "--";
					    	}
					    }},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

function serchGrid(){
	var queryParams = {type:$("#type").val(),vourcheCode:$("#voucherCode").val()};
	$('#mydatagrid').datagrid("load",queryParams); 
}

function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/expdecorateuservoucher/jumpEdit?id="+rows[0].id+"'/>";
}

</script>  
</html>