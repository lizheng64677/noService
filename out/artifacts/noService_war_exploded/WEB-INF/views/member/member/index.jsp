<%@page import="com.suyin.member.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
   	<script type="text/javascript" src="<c:url value='/resources/js/easyUI1.4.2/datagrid-detailview.js'/>"></script>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" id="doAdd" data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" id="doUpdate" data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" id="doDelete" data-options="iconCls:'icon-remove'">删除</a>
			
			
			<a href="#" class="easyui-linkbutton" id="doAddShop" data-options="iconCls:'icon-add'">添加分店</a>

	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/member/list'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[
						{ "field":'memberId',checkbox:true }, 
					    { "field": 'busname',"title" : '商家名',width:$(this).width() * 0.2},
					    { "field": 'username',"title" : '用户名',width:$(this).width() * 0.2},
					    { "field": 'cityId',"title" : '城市',width:$(this).width() * 0.2},
					    { "field": 'regionId',"title" : '商圈',width:$(this).width() * 0.2},
					    { "field": 'cgId',"title" : '品类',width:$(this).width() * 0.2},	    
					    { "field": 'isSelf',"title" : '是否自营',width:$(this).width() * 0.2,formatter:function(value,row){
					    	if(value==0){
					    		return "否";
					    	}else{
					    		return "是"; 
					    	}
						}},
					    { "field": 'isChain',"title" : '是否连锁',width:$(this).width() * 0.2,formatter:function(value,row){
					    	if(value==1){
					    		return "否";
					    	}else{
					    		return "是"; 
					    	}
						}},
// 					    { "field": 'seqNo',"title" : '排名序列',width:$(this).width() * 0.2},
						{ "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.2},					   
						{ "field": 'updateTime',"title" : '更新时间',width:$(this).width() * 0.2},
					    { "field": 'logoPicUrl',"title" : '商家logo图片',width:$(this).width() * 0.2,formatter:function(value,row){
					    	var str = "";
					    	if(value!="" || value!=null){
					    	str = "<img style=\"height: 60px;width: 60px;\" src=\""+value+"\"/>";
					         return str;
					    	}
						}},
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true,
		view:detailview,
      detailFormatter:function(index,data){

			return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
		},
        onExpandRow: function(index,row){  
            $('#ddv-'+index).datagrid({
            	url:"<c:url value='/member/list?memberId='/>"+row.memberId+"",
                fitColumns:true,  
                singleSelect:true,  
                rownumbers:true, 
                loadMsg:'', 
                selectOnCheck: true,
				checkOnSelect: true,
                height:'auto',
//         		onClickCell: function (row, field, value) {
//        			 var row = $('#mydatagrid').datagrid("getSelected");
//        			 if(null==row) return false;
//                   if("busname"==field){
//          			alert(row.memberId);
                 
//                   }
//              },
                columns:[[  
	    
						{ "field": 'busname',"title" : '商家名',width:$(this).width() * 0.3},
					    { "field": 'username',"title" : '用户名',width:$(this).width() * 0.3},
					    { "field": 'cityId',"title" : '城市',width:$(this).width() * 0.3},
					    { "field": 'regionId',"title" : '商圈',width:$(this).width() * 0.3},
					    { "field": 'cgId',"title" : '品类',width:$(this).width() * 0.3},	    
					    { "field": 'isSelf',"title" : '是否自营',width:$(this).width() * 0.3,formatter:function(value,row){
					    	if(value==0){
					    		return "否";
					    	}else{
					    		return "是"; 
					    	}
						}},
					    { "field": 'isChain',"title" : '是否连锁',width:$(this).width() * 0.3,formatter:function(value,row){
					    	if(value==1){
					    		return "否";
					    	}else{
					    		return "是"; 
					    	}
						}},
// 					    { "field": 'seqNo',"title" : '排名序列',width:$(this).width() * 0.2},
						{ "field": 'createTime',"title" : '创建时间',width:$(this).width() * 0.3},					   
						{ "field": 'updateTime',"title" : '更新时间',width:$(this).width() * 0.3},
					    { "field": 'logoPicUrl',"title" : '商家logo图片',width:$(this).width() * 0.3,formatter:function(value,row){
					    	var str = "";
					    	if(value!="" || value!=null){
					    	str = "<img style=\"height: 60px;width: 60px;\" src=\""+value+"\"/>";
					         return str;
					    	}
						}}, 
						{"field":'memberId',"title":'操作',width:$(this).width() * 0.3,formatter:function(value,row){
                        		return "<a href='javascript:void(0)' onclick='shopEdit("+value+")'>修改</a> &nbsp;&nbsp;<a href='javascript:void(0);' onclick='shopDel("+value+")'>删除</a>"; 
						}},	
                ]],  
                onResize:function(){  
                    $('#mydatagrid').datagrid('fixDetailRowHeight',index);  
                },  
                onLoadSuccess:function(){  
                    setTimeout(function(){  
                        $('#mydatagrid').datagrid('fixDetailRowHeight',index);  
                    },0);  
                }  
            });  
            $('#mydatagrid').datagrid('fixDetailRowHeight',index);  
        } 
	});
});

function serchGrid(){
	var queryParams = {directoryName:$("#directoryName").val()};
	$('#mydatagrid').datagrid("load",queryParams); 
}
function doAddShop(){
	
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	if(rows[0].isChain==1){
		
		$.messager.alert("", "您选择的商家不是连锁店无法添加分店！", true, "warning");
	}else{
		
		window.location.href="<c:url value='/member/jumpAdd?parentId='/>"+rows[0].memberId;
	}
}
function doAdd(){
	window.location.href="<c:url value='/member/jumpAdd?parentId=0'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert("", "请选择一条数据！", true, "warning");
		return;
	}
	window.location.href="<c:url value='/member/jumpEdit?id="+rows[0].memberId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].memberId);
	}
	if(ids.length>1){
		
		$.messager.alert("提示", "本功能不允许批量操作！", true, "warning");
		return false;
	}
	$.messager.confirm('确认', '是否确定要删除!', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'member/delete?', {id: ids.join(',')},function(data){
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

function shopEdit(val){
	
	window.location.href="<c:url value='/member/jumpEdit?id="+val+"'/>";
}

function shopDel(val){
	
	$.messager.confirm('确认', '是否确定要删除!', function(data){
		
	});
}
$(function(){
	    
	//添加分店 
	$("#doAddShop").bind("click",function(){
		doAddShop();
	});
	
	//删除 
	$("#doDelete").bind("click",function(){
		doDelete();
	});
	//跳转修改 
	$("#doUpdate").bind("click",function(){
		doUpdate();
	});
	//跳转添加 
	$("#doAdd").bind("click",function(){
		
		doAdd();
	});
});
</script>  
</html>