<%@page import="com.suyin.decorateuser.model.*" %>
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
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="doUpdate();" data-options="iconCls:'icon-remove'">签单处理</a> 
			</div> 
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/expdecorateuser/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ "field":'userId',checkbox:true },
		    { "field": 'userName',"title" : '实名',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	if(""==value || null==value){
		    		return "--";
		    	}else{
		    		return value;
		    	}
		    }},
   			{ "field": 'nickName',"title" : '微信昵称',width:$(this).width() * 0.2},
		    { "field": 'headImg',"title" : '微信头像',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	return "<img src="+value+" style='width:40px;height:40px'>";
		    }},
		    { "field": 'userPhone',"title" : '手机号',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	if(""==value || null==value){
		    		return "--";
		    	}else{
		    		return value;
		    	}
		    }},
		
	
		    { "field": 'countPrice',"title" : '总收益金额',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	return value+"(元)";
		    }},
		    { "field": 'balancePrice',"title" : '可提现余额',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	return value+"(元)";
		    }},
		    { "field": 'alipayNumber',"title" : '支付宝账号',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	if(""==value || null==value){
		    		return "--";
		    	}else{
		    		return value;
		    	}
		    }},
		    { "field": 'useNum',"title" : '已邀请用户',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	
	    		return value+"(个)";
	    	
	 	   }},
	 
		    { "field": 'isExpOrg',"title" : '是否外销编制',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	if("0"==value){
		    		return "否";
		    	}else{
		    		return "是";
		    	}
		    }},
		    { "field": 'isExpUser',"title" : '是否体验用户',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	if("0"==value){
		    		return "否";
		    	}else{
		    		return "是";
		    	}
		    }},
		    { "field": 'isSign',"title" : '是否签约',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	if("0"==value){
		    		return "否";
		    	}else{
		    		return "已签约";
		    	}
		    }},
		    { "field": 'userState',"title" : '用户状态',width:$(this).width() * 0.2,formatter:function(value,row,index){
		    	if("0"==value){
		    		return "正常";
		    	}else if("1"==value){
		    		return "锁定";
		    	}else if("2"==value){
		    		return "冻结";
		    	}else{
		    		return "未实名";
		    	}
		    }},

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
	window.location.href="<c:url value='/expdecorateuser/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}
	var isAuth=rows[0].alipayNumber;
	if(""==isAuth || null==isAuth){
		$.messager.alert("提示", "暂不支持未补充资料用户的签单(未实名)！", true, "warning");
		return;
	}
	var isSign=rows[0].isSign;
	if(1==isSign){
		$.messager.alert("提示", "该用户已签约完成，无需再次签约！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/expdecorateuser/jumpEdit?id="+rows[0].userId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].userId);

	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'expdecorateuser/delete?', {id: ids.join(',')},function(data){
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