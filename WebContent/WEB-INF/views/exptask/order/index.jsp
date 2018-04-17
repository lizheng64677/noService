<%@page import="com.suyin.expzhuan.model.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" onclick="doAgree();" data-options="iconCls:'icon-edit'">批量同意</a>
	  		<a href="#" class="easyui-linkbutton" onclick="doReject();" data-options="iconCls:'icon-edit'">批量拒绝</a>
	  		<select id="expId" style="width:200px;">
	  		</select>
	  		<select id="status" style="width:100px;">
	  			<option value="-1" selected="selected">所有状态</option>
	  			<option value="0">新参与的</option>
	  			<option value="1">已提交的</option>
	  			<option value="2">已同意的</option>
	  			<option value="3">已拒绝的</option>
	  		</select>	
	  		<select id="showType"  style="width:100px;">
	  			<option value="-1" selected="selected">所有类型</option>
	  			<option value="0">APP下载</option>
	  			<option value="1">表单问答</option>
	  		</select>		  		  		
	  		<a href="#" class="easyui-linkbutton" onclick="searchGrid();" data-options="iconCls:'icon-search'">查找</a>
	  		<div style="float:right;padding-right:15px;">
	  		<a href="#" class="easyui-linkbutton" onclick="doDownLoadAllPages();" data-options="iconCls:'icon-remove'">导出所有</a>
			<a href="#" class="easyui-linkbutton" onclick="doDownLoadCurrentPage();" data-options="iconCls:'icon-remove'">导出本页</a>
			</div>
	</div>
	<div id="mydatagrid" fit="true"></div>
	
	<div id="dd" title="图片" style="width:600px;height:400px;">  
   	 	<!-- <img id="img" src="" style="width:100%;height:100%;"/> -->
	</div> 
	
</body>
<script type="text/javascript" src="<c:url value='/resources/js/easyUI1.4.2/datagrid-detailview.js'/>"></script>
<script type="text/javascript">

//导出所有 
function doDownLoadAllPages(){
	//alert("导出所有 ");
	var status=$("#status").combobox("getValue");
	var expId=$("#expId").combobox("getValue");
	var showType=$("#showType").combobox("getValue");
	window.location.href="<c:url value='/exptaskorder/dExcel?statu='/>" + status + "&expId=" + expId +"&showType="+ showType;
}

//导出本页面 
function doDownLoadCurrentPage(){
	//alert("导出本页面 ");
	var status=$("#status").combobox("getValue");
	var expId=$("#expId").combobox("getValue");
	var showType=$("#showType").combobox("getValue");
	window.location.href="<c:url value='/exptaskorder/dExcel?statu='/>" + status + "&expId=" + expId +"&showType="+ showType + "&isAll=y/>";
}

$(function() {
	
	$("#expId").combobox({
		url:"<c:url value='/exptaskorder/findAllExpA'/>",
		valueField:'expId',
		textField:'title',
		onLoadSuccess:function(){
			$("#expId").combobox('select',"-1");
		}
	}); 
	
	$("#status").combobox();
	$("#showType").combobox();
	
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/exptaskorder/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
			{ field:'orderId',checkbox:true }, 
		    { "field": 'userPhone',"title" : '手机号码',width:$(this).width() * 0.1},
		    { "field": 'task',"title" : '活动标题',width:$(this).width() * 0.4,formatter:function(value){
		    	if(value)
		    		return value.title;
		    }},
		     { "field": 'showType',"title" : '类型',width:$(this).width() * 0.05,formatter:function(value){
		    	if("0"==value)
		    		return "APP下载";
		    	else if("1"==value)
		    		return "表单问答";
		    }}, 
		    
			{ "field": 'status',"title" : '状态',width:$(this).width() * 0.05,formatter:function(value){
				if("0"==value) return "新参与";
				else if("1"==value) return "已提交";
				else if("2"==value) return "已同意";
				else if("3"==value) return "已拒绝";
				else return "未知状态";
			}},
			{ "field": 'createTimeString',"title" : '参与时间',width:$(this).width() * 0.1},
			{ "field": 'updateTimeString',"title" : '提交时间',width:$(this).width() * 0.1},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true,
		view:detailview,
		detailFormatter:function(index,data){
			return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
		},
		onExpandRow: function(index,row){  
	            if("0"==row.showType)
	            	onAppExpand(index,row);
	            else if("1"==row.showType)
	            	onFormExpand(index,row);
	    } 
	});
});


//下载APP的展开，就是找到图片，显示出来，并且显示电话号码
function onAppExpand(index,row){
	 var columns= [[  
					{ "field": 'userPhone',"title" : '提交的手机号码',width:$(this).width() * 0.3},
				    { "field": 'imageUrl',"title" : '图片',width:$(this).width() * 0.3,formatter:function(value){
				    	console.log(value);
				    	if(!value) return "";
				    	var html="";
				    	var kk=value.split(";");
				    	for(var i=0;i<kk.length-1;i++){
				    		html+="<img height='50' width='50' src='"+kk[i]+"'/>";
				    	} 
				    	return html;
				    }}
	        ]];  
	var clickCell=function(row, field, value){
		/* <img id="img" src="" style="width:100%;height:100%;"/> */
		var html="";
    	var kk=value.split(";");
    	for(var i=0;i<kk.length-1;i++){
    		html+="<img style='width:30%;height:100%;' src='"+kk[i]+"'/>";
    	} 
    	$("#dd").empty();
    	$("#dd").append($(html));
		$("#dd").dialog({modal:true,resizable:true});
	};
	onExpand(index,row,clickCell,columns);
}

//这个就多了，显示每个问题和问题的答案
function onFormExpand(index,row){
	var columns=[[  
					{ "field": 'question',"title" : '问题',width:$(this).width() * 0.3},
				    { "field": 'answer',"title" : '答案',width:$(this).width() * 0.3}
	        ]];
	var clickCell=$.noop;
	onExpand(index,row,clickCell,columns);
}

function onExpand(index,row,clickCell,columns){
	 $('#ddv-'+index).datagrid({
	    	url:"<c:url value='/exptaskorder/getById?orderId='/>"+row.orderId+"&showType="+row.showType,
	        fitColumns:true,  
	        singleSelect:true,  
	        rownumbers:true, 
	        loadMsg:'加载中', 
	        height:'auto',
	 		onClickCell: clickCell,
	        columns:columns,  
	        onResize:function(){  
	            $('#mydatagrid').datagrid('fixDetailRowHeight',index);  
	        },  
	        onLoadSuccess:function(){  
	        	 $('#mydatagrid').datagrid('fixDetailRowHeight',index);  
	        }  
	    }); 
}

function searchGrid(){
	var sc={};
	var status=$("#status").combobox("getValue");
	var expId=$("#expId").combobox("getValue");
	var showType=$("#showType").combobox("getValue");
	if(status!='-1') sc["status"]=status;
	if(expId!='-1') sc["expId"]=expId;
	if(showType!='-1') sc["showType"]=showType;
	$('#mydatagrid').datagrid("reload",sc); 
}
//批量同意
function doAgree(){
	doUpdate("2");
}
//批量拒绝
function doReject(){
	doUpdate("3");
}

function doUpdate(status){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		
		if("2"==status&&"1"!=rows[i].status||"3"==status&&"1"!=rows[i].status){
			$.messager.alert("提示", "只能同意/拒绝处于\"已提交\"状态的订单！", true, "warning"); 
			return;			
		}
		ids.push(rows[i].orderId);
	}
	$.messager.confirm('确认', '是否确定操作？', function(data){
		if(!data){
			return;
		}
		$.post(SITE_BASE_PATH+'exptaskorder/update?', {ids: ids.join(','),status:status},function(data){
			if(data.error==0){
				for ( var i = rows.length-1; i >=0; i--) {
					var index = $('#mydatagrid').datagrid('getRowIndex', rows[i]);
					$('#mydatagrid').datagrid("updateRow",{
						index:index,
						row:{status:status}
					});
				}
				rows = null;
				$.messager.confirm('确认','修改成功，是否重新加载？（如果取消，你也可以通过刷新按钮重新加载）',function(data){
					if(!data){
						return;
					}
					searchGrid();
				},null);
			}
		},"json");	
	}, null);	
}
</script>  
</html>