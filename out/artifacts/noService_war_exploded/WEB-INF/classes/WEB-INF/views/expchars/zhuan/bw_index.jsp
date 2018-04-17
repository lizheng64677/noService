<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
         <script type="text/javascript" src=" <c:url value="/resources/js/My97DatePicker/WdatePicker.js"></c:url>"> </script>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
			客户端:
			<select id="clientType">
			<option value="-1">---请选择---</option>
			<option value="0">---微信端---</option>
			<option value="1">---苹果端---</option>
			<option value="2">---安卓端---</option>
			</select>
			开始时间:<input  type="text" id="beginTime" class="Wdate"/>
			结束时间:<input type="text" id="endTime" class="Wdate"/>

			<a href="javascript:void(0);" class="easyui-linkbutton" id="searchbox" data-options="iconCls:'icon-search'">查询</a>
			<div style="float:right;padding-right:18px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" id="exper" data-options="iconCls:'icon-remove'">导出</a>
			</div> 
	</div>
	</div>
	<div id="mydatagrid" fit="true" ></div>

</body>
<script type="text/javascript">
$(function() {
	 $("#beginTime").bind("click",function(){
		 
		 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'});
	 });
 	$("#endTime").bind("click",function(){
		 
		 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'});
	 });
	//搜索框
	$('#searchbox').bind("click",function(){

		var queryParams={"clicentType":$("#clientType").val(),"beginTime":$("#beginTime").val(),"endTime":$("#endTime").val()};

		$('#mydatagrid').datagrid("load",queryParams); 
		
	});
	$("#exper").bind("click",function(){
		var queryParams={"clicentType":$("#clientType").val(),"beginTime":$("#beginTime").val(),"endTime":$("#endTime").val()};
		$.messager.confirm('确认', '是否确定导出数据!', function(data){
			if(!data){
				return;
			}
			location.href="<c:url value='/expExcel/expTaskDataExport'/>?expType=0&clicentType="+$("#clientType").val()+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();
			
		});	
		
	});
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/expChars/findQZhuanList'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[			    	
						{ "field":'expId',checkbox:true },
					    { "field": 'expType',"title" : '活动类型',width:$(this).width() * 0.2,formatter:function(value,row){
					    	if("0"==value){
					    		return "帮我赚";
					    	}else{
					    		return "---";
					    	}
					    }},
					    { "field": 'clicentType',"title" : '客户端',width:$(this).width() * 0.2,formatter:function(value,row){
					    	if("1"==value){
					    		return "苹果";
					    	}else if("2"==value){
					    		return "安卓";
					    	}else if("0"==value){
					    		
					    		return "微信";
					    	}
					    }},
					    
					    { "field": 'title',"title" : '活动标题',width:$(this).width() * 0.2},
					    { "field": 'bengTime',"title" : '开始时间',width:$(this).width() * 0.2},
					    { "field": 'endTime',"title" : '结束时间',width:$(this).width() * 0.2},
					    { "field": 'pv',"title" : '页面点击量',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"次";
					    }},
					    { "field": 'uv',"title" : '用户访问量',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"人";
					    }},
					    { "field": 'qj',"title" : '金币区间',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"金币";
					    }},
					    { "field": 'orderNum',"title" : '领取人数',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"人";
					    }},
					    
					    { "field": 'zs',"title" : '发放总收益',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"个金币";
					    }}
					    
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});


</script>

</html>