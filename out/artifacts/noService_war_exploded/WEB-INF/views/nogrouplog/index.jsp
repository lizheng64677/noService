<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
       	<script type="text/javascript" src="<c:url value='/resources/js/easyUI1.4.2/datagrid-detailview.js'/>"></script>
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
	<div id="mydatagrid" fit="true"></div>
	
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
			location.href="<c:url value='/expExcel/systemModelDataExport'/>?clicentType="+$("#clientType").val()+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();
			
		});	
		
	});
	
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/sysLog/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
						{ "field":'id',checkbox:true },
					    { "field": 'modelName',"title" : '模块名称',width:$(this).width() * 0.2},
					    { "field": 'pv',"title" : '页面点击量',width:$(this).width() * 0.2},
					    { "field": 'uv',"title" : '用户点击量',width:$(this).width() * 0.2},
					    { "field": 'clicentType',"title" : '客户端类型 ',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if(value=="0"){
							return "微信";
					    	}else if(value=="1"){
					    		
					    		return "ios";
					    	}else{
					    		
					    		return "Android";
					    	}
						}},
						{ "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},
						{ "field": 'updateTime',"title" : '更新时间',width:$(this).width() * 0.2}
		
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
	window.location.href="<c:url value='/sysLog/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/sysLog/jumpEdit?id="+rows[0].tid+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].tid);

	}
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'nologs/delete?', {id: ids.join(',')},function(data){
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