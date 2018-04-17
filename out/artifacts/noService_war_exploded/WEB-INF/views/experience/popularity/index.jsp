<%@page import="com.suyin.experience.model.*" %>
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
		 	<a href="#" class="easyui-linkbutton" onclick="doVoucher();" data-options="iconCls:'icon-add'">发券</a>
		 	<a href="#" class="easyui-linkbutton" onclick="doVoucher1();" data-options="iconCls:'icon-add'">新增用户参与</a>
			
			<a href="#" class="easyui-linkbutton" onclick="doSetting();" data-options="iconCls:'icon-remove'">配置动态属性</a>
			&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" onclick="doStart();" data-options="iconCls:'icon-remove'">开始活动</a> 
			<a href="#" class="easyui-linkbutton" onclick="doStop();" data-options="iconCls:'icon-remove'">结束活动</a>			
			</div>
	</div>
	<div id="mydatagrid" fit="true"></div>
	<div id="dlga" style="text-align: center;padding-top: 30px;">
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
	<div id="dlg" style="text-align: center;padding-top: 30px;">
	 <div>
	 <table>
	 	<tr>
	 	<td>选择时间段:</td>
	 	<td>
	 	<select id="expTime" style="width:450px;">

	 	</select>
	 	</td>
	 	</tr>
	 </table>
	 </div>
	 <div>
	 	  	<a href="#" class="easyui-linkbutton" onclick="doSearch();" data-options="iconCls:'icon-search'">查询人气用户</a> 
	 </div>
	</div>
	
	<div id="dlg1" style="text-align: center;padding-top: 30px;">
	 <div>
	 <table>
	 	<tr>
	 	<td>选择时间段:</td>
	 	<td>
	 	<select id="expTime1" style="width:450px;">

	 	</select>
	 	</td>
	 	</tr>
	 </table>
	 </div>
	 <div>
	 	  	<a href="#" class="easyui-linkbutton" onclick="doSearch2();" data-options="iconCls:'icon-search'">查询已参与用户信息</a> 
	 	  	<a href="#" class="easyui-linkbutton" onclick="doSearch1();" data-options="iconCls:'icon-search'">查询用户信息</a> 
	 </div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/exprenqi/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[			    	
						{ "field":'expId',checkbox:true },
					    { "field": 'title',"title" : '标题',width:$(this).width() * 0.2},
					    { "field": 'expType',"title" : '活动类型',width:$(this).width() * 0.2,hidden:true},
					    { "field": 'showType',"title" : '活动方式',width:$(this).width() * 0.2,formatter:function(value,row){  
							return "人气式";  
						}},
					    { "field": 'isBegin',"title" : '是否开始',width:$(this).width() * 0.2,formatter:function(value,row){ 
					    	if(value==0){
					    		return "<span style='color:red;'>否</span>";
					    	}else{
					    		return "<span style='color:#00FF00;'>是</span>"; 
					    	}
						}},
						{ "field": 'beginTime',"title" : '开始时间',width:$(this).width() * 0.2},
						{ "field": 'endTime',"title" : '结束时间',width:$(this).width() * 0.2},
// 					    { "field": 'addOrderNum',"title" : '订单数',width:$(this).width() * 0.2},
						{ "field": 'seqNum',"title":'置顶序号',width:$(this).width() * 0.1,formatter:function(value,row,index){
// 							 
							return "<input type='text' value="+value+" style='width:85px;border:1px solid #ccc' readonly='readonly' ></input>";
						}},
					    { "field": 'proSet',"title" : '操作',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	
					    	return "<a href='javascript:void(0)' id='addProId' onclick='doDetailAdd()'>增加商品</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='doDetailEdit()'>修改商品</a> ";
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
    $('#dlga').dialog({
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
	$.post(SITE_BASE_PATH+'exprenqi/updataSeqNum?', {expId: rows[0].expId,seq:seq},function(data){
		if(data.message=="1"){
			$('#mydatagrid').datagrid('reload');
			$.messager.show({
				title:'系统消息',
				msg:'活动置顶成功！！！',
				timeout:2000,
				showType:'slide'
			});
			$("#dlga").dialog("close");
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
	window.location.href="<c:url value='/exprenqi/jumpAdd'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	$.post(SITE_BASE_PATH+'exprenqi/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){
			
			window.location.href="<c:url value='/exprenqi/jumpEdit?id="+rows[0].expId+"'/>";
	
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	
}
function doDetailEdit(){
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
	
	$.post(SITE_BASE_PATH+'exprenqi/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){
	
// 			window.location.href="<c:url value='/expdetail/jumpEdit?expId="+rows[0].expId+"'/>";//1对1
			window.location.href="<c:url value='/expdetail/index?expId="+rows[0].expId+"'/>"; 
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	
	
}
//添加活动商品 
function doDetailAdd(){
	
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("提示", "请选择一条数据！", true, "warning");
		return;
	}

	$.post(SITE_BASE_PATH+'exprenqi/isBeg?', {expId: rows[0].expId},function(data){
// 	$.post(SITE_BASE_PATH+'exprenqi/isBegProduct?', {expId: rows[0].expId},function(data){//一对一
		if(data.message=="0"){
	
			window.location.href="<c:url value='/expdetail/jumpAdd?expId="+rows[0].expId+"'/>";
		}else if(data.message=="1"){
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}else if(data.message=="2"){
			
			$.messager.alert("提示", "产品已经存在无需再次添加！", true, "warning");  
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
		$.post(SITE_BASE_PATH+'exprenqi/startExp?', {expId: ids.join(','),isBegin:"1"},function(data){
			if(data.message=="success"){
				$('#mydatagrid').datagrid('reload');
				$.messager.show({
					title:'系统消息',
					msg:'活动开始成功!', 
					timeout:2000,
					showType:'slide'
				});
			}else if(data.message=="invalidProNum"){
				
				$.messager.alert("提示", "该活动暂未添加产品！", true, "warning");
				return false;
			}else if(data.message=="error"){
				
				$.messager.alert("提示", "活动状态修改失败", true, "warning");
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
		$.post(SITE_BASE_PATH+'exprenqi/stopExp?', {expId: ids.join(','),isBegin:"0"},function(data){
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
	$.post(SITE_BASE_PATH+'exprenqi/isBeg?', {expId: rows[0].expId},function(data){
		if(data.message=="0"){
			
			window.location.href="<c:url value='/exprenqi/Setting?expId="+rows[0].expId+"&type=2'/>";
	
		}else{
			
			$.messager.alert("提示", "活动正在进行中无法进行修改！", true, "warning"); 
			return false;
		}
	},"json");	

}
//发券 
function doVoucher(){
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
	
	//查询时间段
	$.post(SITE_BASE_PATH+'exprenqi/findForExpTime?', {expId: rows[0].expId},function(data){	
		$("#expTime").empty();
		var html="";
		$(data.result).each(function(i,val){
			
			html+="<option value="+val.exp_time_id+">"+val.begin_time+"----至----"+val.end_time+"</option>"; 
			
		});
		$("#expTime").append(html);
	})
	


    $('#dlg').dialog({
        title: '选择活动时间',
        iconCls: "icon-edit",
        collapsible: true,
        minimizable: false,
        maximizable: false,
        resizable: true,
        width: 600,
        height: 150,
        modal: true
    });
}


//新增用户参与活动
function doVoucher1(){
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
	
	//查询时间段
	$.post(SITE_BASE_PATH+'exprenqi/findForExpTime?', {expId: rows[0].expId},function(data){	
		$("#expTime1").empty();
		var html="";
		$(data.result).each(function(i,val){
			
			html+="<option value="+val.exp_time_id+">"+val.begin_time+"----至----"+val.end_time+"</option>"; 
			
		});
		$("#expTime1").append(html);
	})
	


    $('#dlg1').dialog({
        title: '选择活动时间',
        iconCls: "icon-edit",
        collapsible: true,
        minimizable: false,
        maximizable: false,
        resizable: true,
        width: 600,
        height: 150,
        modal: true
    });
}

//查询时间段是否合法
function doSearch1(){
	var timeId=$("#expTime1").val();
	var rows=$("#mydatagrid").datagrid("getSelections");
	window.location.href="<c:url value='/exprenqi/queryUserList?expId="+
					rows[0].expId+"&timeId="+timeId+"&expType="+rows[0].expType+"'/>";
}

//查询时间段是否合法
function doSearch2(){
	var timeId=$("#expTime1").val();
	var rows=$("#mydatagrid").datagrid("getSelections");
	window.location.href="<c:url value='/exprenqi/userPartList?expId="+
					rows[0].expId+"&timeId="+timeId+"&expType="+rows[0].expType+"'/>";
}

//查询时间段是否合法
function doSearch(){
	var timeId=$("#expTime").val();
	var rows=$("#mydatagrid").datagrid("getSelections");
	$.post(SITE_BASE_PATH+'exprenqi/findForIsExpTimeStatus?', {timeId: timeId},function(data){	
		if("1"==data.result.status){
			console.log("params search success");

			window.location.href="<c:url value='/exprenqi/findForUserPopListIndex?expId="+rows[0].expId+"&timeId="+timeId+"'/>";
		}else{
			
			$.messager.show({
				title:'系统消息',
				msg:'当前时间段未开始或未结束，无法进行发券！', 
				timeout:2000,
				showType:'slide'
			});
		}
	})
	
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
		$.post(SITE_BASE_PATH+'exprenqi/delete?', {id: ids.join(',')},function(data){
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
</script>
<script type="text/javascript">
$(function(){


});
</script>  
</html>