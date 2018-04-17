<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" onclick="doVoucher();" data-options="iconCls:'icon-edit'">参与活动</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
	<input type="hidden" id="expId" value="${expId }"/>
	<input type="hidden" id="timeId" value="${timeId }"/>
	<input type="hidden" id="expType" value="${expType }"/>
</body>
<script type="text/javascript">
$(function() {

	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/exprenqi/queryForUserList?expId=${expId}&timeId=${timeId}&expType=${expType}'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[			    	
						{ "field":'user_id',checkbox:true },
					    { "field": 'user_phone',"title" : '手机号码',width:$(this).width() * 0.2},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});




//参与活动
function doVoucher(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].user_id);
	}
	var timeId=$("#timeId").val();
	var expId=$("#expId").val();
	var expType=$("#expType").val();
	$.post(SITE_BASE_PATH+'exprenqi/userPart?', {"userId": ids.join(','),"expId":expId,"timeId":timeId,"expType":expType},function(data){
		if(data.result=="success"){
			$('#mydatagrid').datagrid('reload');
			$.messager.alert("提示", "参与成功", true, "warning");
		}
	},"json");

}

</script>

</html>