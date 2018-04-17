<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<script type="text/javascript" src=" <c:url value="/resources/js/My97DatePicker/WdatePicker.js"></c:url>"> </script>
<html>
  <head>
  	<script type="text/javascript">
  		function FindData(){
  			var queryParams = {startTime:$("#beginTime").val(),endTime:$("#endTime").val()};
  			$('#mydatagrid').datagrid("load",queryParams); 
  		}
  		
  		function doDownLoadCurrentPage(){
  			var startTime=$("#beginTime").val();
  			var endTime=$("#endTime").val();
  			window.location.href="<c:url value='/sysLog/dExcel?startTime="+startTime+"&endTime="+endTime+"'/>"
  		}
  	</script>
  </head>
  <body id="search" class="easyui-layout"  >   
	
	 <div id="searchtool" style="padding:5px">
	 	<span>
	 		开始时间:
	 		<input type="text" name="beginTime"   id="beginTime"  onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true})"  class="Wdate" style="width:145px;"/>
	 	</span>
	    <span>
	    	结束时间:
	    	<input type="text" name="endTime"   id="endTime" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true})"   class="Wdate" style="width:145px;"/>
	    </span>  
        <a href="javascript:FindData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
        
        
        <a href="#" class="easyui-linkbutton" onclick="doDownLoadCurrentPage();" data-options="iconCls:'icon-remove'">导出</a>  
    </div>
	<div id="mydatagrid" fit="true" ></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/sysLog/capitalCount'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[			    	
					    { "field": 'userCount',"title" : '会员人数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'goldCount',"title" : '平台金币总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'goldSignCount',"title" : '签到金币总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'registerGoldCount',"title" : '注册金币总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'dataGoldCount',"title" : '完善资料金币总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'qisongTaskGoldCount',"title" : '轻松赚金币总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'qmTaskGoldCount',"title" : '帮我赚金币总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'walletGoldCount',"title" : '金币兑换钱包金币总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'walletUserCount',"title" : '金币兑换钱包总人数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'waletMoneyCount',"title" : '申请兑换金额总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'applyMoneyCount',"title" : '申请提现金额总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'applyUserCount',"title" : '申请提现总人数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }},
					    { "field": 'moneyCount',"title" : '钱包提现支付宝总数',width:$(this).width() * 0.1,formatter:function(value,row){
					    	if(value==""||value==null){
					    		return "0"; 
					    	}else{
					    		return value;
					    	}
					    }}
					   
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});

</script>

</html>