<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
.A{text-decoration:none} 无下划线 
.A:link{color:red} 
.A:visited{color:blue} 
.A:active{color:yellow} 
.A:hover{color:red;font-size:20} 
</style>
<script type="text/javascript">
function convertTreeGridData(rows){
	function exists(rows, parentId){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == parentId) return true;
		}
		return false;
	}
	
	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row.parentId)){
			nodes.push(row);
		}
	}
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift();	// the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row.parentId == node.id){
				var child = row;
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}
$(function() {
	$('#mydatagrid').treegrid({
	    url:"<c:url value="/weChatMenu/synTreeList"/>",
		loadFilter: function(rows){
			return convertTreeGridData(rows);
		},
		onLoadSuccess:function() 
		{  
			$('#mydatagrid').treegrid('collapseAll');
		},
	    idField:'id',
	    treeField:'name',
	    columns:[[
	    	{title:'编号',field:'id',width:200,align:'center'},
	        {title:'菜单名称',field:'name',width:200,align:'left'},
	        {field:'nodeType',title:'节点类型',width:160,align:'center',formatter:function(value,row){ 
		    	if(value=='click'){
		    		return "点击事件";
		    	}else if(value=='view'){
		    		return "地址链接"; 
		    	}else if(value=='scancode_push'){
		    		return "扫码推事件"; 
		    	}else if(value=='scancode_waitmsg'){
		    		return "扫码带提示"; 
		    	}else if(value=='pic_sysphoto'){
		    		return "系统拍照发图"; 
		    	}else if(value=='pic_photo_or_album'){
		    		return "拍照或者相册发图"; 
		    	}else if(value=='pic_weixin'){
		    		return "微信相册发图"; 
		    	}
			}},
	        {field:'description',title:'描述',width:200,align:'center'},
	        {field:'level',title:'节点等级',width:200,align:'center'}
	        ,{
				field :'createUser',
				title:'操作',
				width:$(this).width() * 0.2,
				formatter:function(value,row,index){ 
					return '<a class="A" href="<c:url value='/weChatMenu/doUpdateWeChatMenu?id='/>'+row.id+'">编辑<a/>&nbsp;'+'<a class="A" onclick="deteteweChatMenu();">删除</a>';
				}
			}
	    ]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});
 	function doAdd(){
 		//获取选中的节点
 		var node=$('#mydatagrid').treegrid('getSelected');
 		if(node){
 			window.location.href="<c:url value="/weChatMenu/doAdd"/>?id="+node.id;
 		}else{
 			window.location.href="<c:url value="/weChatMenu/doAdd"/>";
 		}
 	}
 	function deteteweChatMenu(){
 		var node=$('#mydatagrid').treegrid('getSelected');
 		$.messager.confirm('确认', '是否删除当前节点以及子节点!', function(r){
 			if(r){
 				$.ajax({   
 		            type: 'POST',   
 		            url: "<c:url value='/weChatMenu/deteteWeChatMenu'/>?id="+node.id ,   
 		            data: $("#menu").serialize() ,
 		            success: function(data) {
 		                   if(data.message>=1){
 		                	  $('#mydatagrid').treegrid('remove', node.id);
 							  $('#mydatagrid').treegrid('reloadFooter');
	 							 $.messager.show({
	 								title:'系统消息',
	 								msg:'删除成功!',
	 								timeout:2000,
	 								showType:'slide'
	 							});
 		                   }
 		            },   
 		           error: function(XmlHttpRequest, textStatus, errorThrown){  
 		        	  $.messager.show({
							title:'系统消息',
							msg:'系统错误',
							timeout:2000,
							showType:'slide'
						});
 		            }   
 		       }); 
 			}
		}, null);
 	}
 	
 	
 	function assembleMenu(){
 		$.ajax({   
	            type: 'POST',   
	            url: "<c:url value="/weChatMenu/assembleMenu"/>?wxkey=${loginUser.applicationId }",  
	            success: function(data) {
	                   alert(data);
	            },   
	           error: function(XmlHttpRequest, textStatus, errorThrown){  
	        	   $.messager.show({
						title:'系统消息',
						msg:'系统错误',
						timeout:2000,
						showType:'slide'
					});
	            }   
	       });
 	}
</script>
</head>
<body>
	<div id="tool">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="doAdd();" >添加</a>
	  	<a href="#"  title="刷新公众号的自定义菜单" class="easyui-linkbutton easyui-tooltip" data-options="iconCls:'icon-reload'"  onclick="assembleMenu();" >刷新自定义菜单</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
</body>
</html>
