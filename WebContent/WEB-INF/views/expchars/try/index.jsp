<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
		<input id="searchbox" style="width:300px"></input> 
			<div id="searchboxSplit" style="width:100px">
			<div data-options="name:'-1'">选项名称</div>  
			<div data-options="name:'0'">微信</div> 
			<div data-options="name:'1'">苹果</div>  
			<div data-options="name:'2'">安卓</div>
			</div>
	</div>
	</div>
	<div id="mydatagrid" fit="true" ></div>

</body>
<script type="text/javascript">
$(function() {
	$('#searchbox').searchbox({ 
		searcher:function(value,name){ 			
			var queryParams;	
			if('-1'==name){
				
				queryParams={clicentType:"-1","modelName":value};
			}else{
				
				queryParams={"clicentType":name,"modelName":value};
			}
			
			$('#mydatagrid').datagrid("load",queryParams); 
		}, 
		menu:'#searchboxSplit', 
		prompt:'请输入活动名称' 
	}); 
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/expChars/findPrizeList'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[			    	
						{ "field":'expId',checkbox:true },
					    { "field": 'expType',"title" : '活动类型',width:$(this).width() * 0.2,formatter:function(value,row){
					    	if("1"==value){
					    		return "抽奖乐";
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
					    { "field": 'proName',"title" : '产品名称',width:$(this).width() * 0.2},
					    { "field": 'bengTime',"title" : '开始时间',width:$(this).width() * 0.2},
					    { "field": 'endTime',"title" : '结束时间',width:$(this).width() * 0.2},
					    { "field": 'pv',"title" : '页面点击量',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"次";
					    }},
					    { "field": 'uv',"title" : '用户访问量',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"人";
					    }},
					    { "field": 'rnum',"title" : '参与人数',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"人";
					    }},
					    { "field": 'probability',"title" : '概率',width:$(this).width() * 0.2,formatter:function(value,row){ 
					    	return value+"%";
						}},
					    { "field": 'proNum',"title" : '提供数量',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"份";
					    }},
					    { "field": 'expNum',"title" : '剩余数量',width:$(this).width() * 0.2,formatter:function(value,row){
					    	return value+"份";
					    }},
					    { "field": 'price',"title" : '市场价',width:$(this).width() * 0.2},
					    { "field": 'validity',"title" : '券的有效期',width:$(this).width() * 0.2,formatter:function(value,row){
					    	if(value=="-1"){
					    		
					    		return "--";
					    	}else{
					    		
					    		return value;
					    	}
					    }},
					    { "field": 'addDay',"title" : '券的有效天数',width:$(this).width() * 0.2,formatter:function(value,row){
					    
    						if(value=="-1"){
    							return "--";
					    		
					    	}else{
					    		
					    		return "中奖后"+value+"天后到期";
					    	}
					    }},
					    { "field": 'xqs',"title" : '消券数',width:$(this).width() * 0.2}
					    
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});


</script>

</html>