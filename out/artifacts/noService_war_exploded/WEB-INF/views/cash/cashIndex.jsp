<%@page import="com.suyin.coin.model.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
			<label>手机号码：</label>
			<input type="text" id="number"  class="easyui-validatebox" />
			<a href="#" class="easyui-linkbutton" onclick="searchGrid();" data-options="iconCls:'icon-search'">查找</a>

	</div>
	<div id="mydatagrid" fit="true"></div>
</body>
<script type="text/javascript">
$(function() {
	
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/nousercashlog/cashTellerRecordList'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ field:'logId',checkbox:true }, 
 			{ "field": 'userName',"title" : '用户',width:$(this).width() * 0.2},
 			{ "field": 'userPhone',"title" : '用户手机号',width:$(this).width() * 0.2},
			{ "field": 'money',"title" : '累积提现金额',width:$(this).width() * 0.2},
		    { "field": 'count',"title" : '提取次数',width:$(this).width() * 0.2},
			{ "field": 'updateTime',"title" : '最后修改时间',width:$(this).width() * 0.2},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

function searchGrid(){
	var sc={};
	var number=$("#number").val()
	if(number!='-1') sc["userPhone"]=number;
	$('#mydatagrid').datagrid("reload",sc); 
}


</script>  
</html>