<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" onclick="doVoucher();" data-options="iconCls:'icon-edit'">修改</a>
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
		url : "<c:url value='/exprenqi/queryUserPartList?expId=${expId}&timeId=${timeId}&expType=${expType}'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[			    	
						{ "field":'orderId',checkbox:true },
					    { "field": 'userPhone',"title" : '用户电话',width:$(this).width() * 0.2},
					    { "field": 'title',"title" : '活动标题',width:$(this).width() * 0.2},
					    { "field": 'expType',"title" : '活动类型',width:$(this).width() * 0.2,formatter:function(value,row){  
							if(value==0){
								return "抽奖式";	
							}else if(value==1){
								return "人气式";
							}else if(value==2){
								return "兑换式";
							}else if(value==3){
								return "试用式";
							}
					    	  
						}},
					    { "field": 'popStatus',"title" : '订单状态',width:$(this).width() * 0.2,formatter:function(value,row){  
					    	if(value==0){
								return "申请中";	
							}else if(value==1){
								return "申请成功";
							}else if(value==2){
								return "申请失败";
							}
						}},
					    { "field": 'prizeStatus',"title" : '是否中奖',width:$(this).width() * 0.2,
							formatter:function(value,row){  
								if(value==0){
									return "未中奖";	
								}else if(value==1){
									return "已中奖";
								}
							}},
					    { "field": 'beginTime',"title" : '活动期数开始时间',width:$(this).width() * 0.2},
					    { "field": 'endTime',"title" : '活动期数结束时间',width:$(this).width() * 0.2},
					    { "field": 'shareNum',"title" : '分享数',width:$(this).width() * 0.2},
					    { "field": 'createTime',"title" : '参与时间',width:$(this).width() * 0.2},
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
	
	if(rows.length>1){
		$.messager.alert("", "只能选择一条哦！", true, "warning");
		return;
	}
	
	
	var timeId=$("#timeId").val();
	var expId=$("#expId").val();
	var expType=$("#expType").val();
	window.location.href="<c:url value='/exprenqi/oneOrder?expId="+expId
			+"&timeId="+timeId+"&orderId="+rows[0].orderId+"'/>";

}

</script>

</html>