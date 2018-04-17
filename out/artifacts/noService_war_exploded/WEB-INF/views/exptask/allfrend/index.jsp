<%@page import="com.suyin.expzhuan.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" onclick="doAdd();" data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" onclick="doUpdate();" data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" onclick="doDelete();" data-options="iconCls:'icon-remove'">删除</a>
			<a href="#" class="easyui-linkbutton" onclick="zhidiv();" data-options="iconCls:'icon-edit'">置顶</a>  
			<div style="float:right;margin-right:18px;">
<!-- 			<a href="#" class="easyui-linkbutton" onclick="doSetting();" data-options="iconCls:'icon-remove'">配置动态属性</a> -->

			&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" onclick="doStart();" data-options="iconCls:'icon-remove'">开始活动</a> 
			<a href="#" class="easyui-linkbutton" onclick="doStop();" data-options="iconCls:'icon-remove'">结束活动</a>			
			</div>
	</div>
	<div id="mydatagrid" fit="true"></div>
		<div id="dlg" style="text-align: center;padding-top: 30px;">
	 <div>
	 <table>
	 	<tr>
	 	<td>数字序号:</td>
	 	<td>
	 	<input type="text" class="input" id="seq">
	 	</td>
	 	</tr>
	 </table>
	 </div>
	 <div>
	 	  	<a href="#" class="easyui-linkbutton" onclick="updataSeqNum();" data-options="iconCls:'icon-search'">确定置顶</a> 
	 </div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/expquanminzhuan/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
						{ "field":'expId',checkbox:true },						
					    { "field": 'expId1',"title" : '活动Id',width:$(this).width() * 0.1,formatter:function(value,row){  
					    	return row.expId;
						}},
						
					    { "field": 'memberName',"title" : '商家名称',width:$(this).width() * 0.2},
					    { "field": 'title',"title" : '活动标题',width:$(this).width() * 0.2},
					    { "field": 'isBegin',"title" : '活动状态',width:$(this).width() * 0.2,formatter:function(value,row){  
					    	if(value==0){
					    		return "<span style='color:red;'>否</span>";
					    	}else{
					    		return "<span style='color:#00FF00;'>是</span>"; 
					    	}
						}},
					    { "field": 'expUserGold',"title" : '固定收益金币',width:$(this).width() * 0.2},
					    { "field": 'userTotal',"title" : '用户可参与次数',width:$(this).width() * 0.2},
					    { "field": 'seqNum',"title" : '置顶序号',width:$(this).width() * 0.1,formatter:function(value,row,index){							 
							return "<input type='text' value="+value+" style='width:85px;border:1px solid #ccc' readonly='readonly' ></input>";
						}},	
						{ "field": 'beginTime',"title" : '开始时间',width:$(this).width() * 0.2},
						{ "field": 'endTime',"title" : '结束时间',width:$(this).width() * 0.2},
						{ "field": 'showType',"title" : '操作',width:$(this).width() * 0.2,formatter:function(value,row){  
					    	if(value==0){
					    		return "<a href='javascript:void(0)' onclick='doSetImages()'>配置图片</a>";    
					    	}else{
					    		return "<a  href='javascript:void(0)' onclick='doSetwjdc()'>配置问卷</a>";//&nbsp;<a  href='javascript:void(0)' onclick='doSetResult()'>配置答案</a> 
					    	}
						}},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});
function zhidiv(){	
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
    $('#dlg').dialog({
        title: '序号',
        iconCls: "icon-edit",
        collapsible: true,
        minimizable: false,
        maximizable: false,
        resizable: true,
        width: 400,
        height: 150,
        modal: true
    });
}
function updataSeqNum(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	var seq=$("#seq").val();
	if(seq.length<1){
		$.messager.show({
			title:'系统消息',
			msg:'请填写数字序号!',
			timeout:2000,
			showType:'slide'
		});
		return;
	}
	$.post(SITE_BASE_PATH+'expquanminzhuan/updataSeqNum?', {expId: rows[0].expId,seq:seq},function(data){
		if(data.message=="1"){
			$('#mydatagrid').datagrid('reload');
			$.messager.show({
				title:'系统消息',
				msg:'活动置顶成功！！！',
				timeout:2000,
				showType:'slide'
			});
			$("#dlg").dialog("close");
			$("#seq").val("");
		
		}else{
			
			$.messager.show({
				title:'系统消息',
				msg:'置顶过程出现异常!',
				timeout:2000,
				showType:'slide'
			});
		}

	},"json");	
}

function serchGrid(){
	var queryParams = {directoryName:$("#directoryName").val()};
	$('#mydatagrid').datagrid("load",queryParams); 
}

function doAdd(){
	window.location.href="<c:url value='/expquanminzhuan/jumpAdd'/>";
}
function doSetResult(){
	
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	$.post(SITE_BASE_PATH+'expquanminzhuan/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){
			
			window.location.href="<c:url value='/expzhuanprototype/setting?id="+rows[0].expId+"&moduleType=1&showJsp=1'/>";//全民赚问卷调查    
	
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	
	
}
//配置app操作范例图片 
function doSetImages(){
	
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	$.post(SITE_BASE_PATH+'expquanminzhuan/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){

			window.location.href="<c:url value='/expquanminzhuan/jumpAddImages?expId="+rows[0].expId+"'/>";   
	
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	
}
//问卷调查 
function doSetwjdc(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	$.post(SITE_BASE_PATH+'expquanminzhuan/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){
			
			window.location.href="<c:url value='/expdictionary/index?id="+rows[0].expId+"'/>"; 
	
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	

}

function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	$.post(SITE_BASE_PATH+'expquanminzhuan/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){
			
			window.location.href="<c:url value='/expquanminzhuan/jumpEdit?id="+rows[0].expId+"'/>";
	
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	

}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].expId);

	}
	if(ids.length > 1){
		$.messager.alert("提示", "该功能暂不支持批量操作！", true, "warning");
		return;
	}
	$.messager.confirm('确认', '是否确定要删除!', function(r){
		if(!r){
			return;
		}
	$.post(SITE_BASE_PATH+'expquanminzhuan/delete?', {id: ids.join(',')},function(data){
			if(data.result > 0){
				for ( var i = rows.length-1; i >=0; i--) {
				var index = $('#mydatagrid').datagrid('getRowIndex', rows[i]);
					$('#mydatagrid').datagrid('deleteRow', index);
				}
				rows = null;
				systemShow("系统提示","数据删除成功!");
			}else{
				
				$.messager.alert("提示", "活动正在进行中无法删除！", true, "warning"); 
				return;
				
			}
		},"json");	
	}, null);	
}

//配置动态属性 
function doSetting(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].expId);

	}
	if(ids.length > 1){
		$.messager.alert("提示", "该功能暂不支持批量操作！", true, "warning");
		return;
	}
	$.post(SITE_BASE_PATH+'expquanminzhuan/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){
			
			window.location.href="<c:url value='/expquanminzhuan/Setting?expId="+rows[0].expId+"&showJsp=1'/>";
	
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	

}
//开始活动 
function doStart(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].expId);

	}
	if(ids.length > 1){
		$.messager.alert("提示", "该功能暂不支持批量操作！", true, "warning");
		return;
	}
	$.messager.confirm('确认', '是否确定要开始活动吗？', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'expquanminzhuan/startExp?', {expId: ids.join(','),isBegin:"1",showType:rows[0].showType},function(data){
			if(data.message=="success"){
				$('#mydatagrid').datagrid('reload');
				$.messager.show({
					title:'系统消息',
					msg:'活动开始成功!',
					timeout:2000,
					showType:'slide'
				});
			}else if(data.message=="error"){
				
				$.messager.alert("提示", "活动状态修改失败", true, "warning");
				return false;
				
			}else if(data.message=="invalidCjdc"){
				$.messager.alert("提示", "该活动需要配置问卷调查题目！", true, "warning");
				return false;
			}else if(data.message=="invalidResult"){
				$.messager.alert("提示", "您还没有设置问题答案呢！", true, "warning");
				return false;
				
			}else if(data.message=="invalidSetting"){
				
				$.messager.alert("提示", "你还没有配置动态属性值，请点击上部设置动态属性按钮！", true, "warning");
				return false;
			}
		},"json");	
	},null);
}
//结束活动 
function doStop(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].expId);

	}
	if(ids.length > 1){
		$.messager.alert("提示", "该功能暂不支持批量操作！", true, "warning");
		return;
	}
	$.messager.confirm('确认', '是否确定要结束活动吗？', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'expquanminzhuan/stopExp?', {expId: ids.join(','),isBegin:"0"},function(data){
			if(data.message=="success"){
				$('#mydatagrid').datagrid('reload');
				$.messager.show({
					title:'系统消息',
					msg:'活动结束成功!',
					timeout:2000,
					showType:'slide'
				});
			}else if(data.message=="error"){
				
				$.messager.alert("提示", "活动状态修改失败", true, "warning");
			}else if(data.message=="invalidParam"){
				
				$.messager.alert("提示", "参数值传递有误！", true, "warning");
			}
		},"json");	
	}, null);	
}
</script>  
</html>