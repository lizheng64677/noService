<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" id="doAdd" data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" id="doUpdate" data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" id="doDelete" data-options="iconCls:'icon-remove'">删除</a>
			<a href="#" class="easyui-linkbutton" id="doReply" data-options="iconCls:'icon-filter'">关注回复</a>
			<a href="#" class="easyui-linkbutton" id="deReply" data-options="iconCls:'icon-filter'">取消设置</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/weChatReply/synChatReplyList'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,50,1000],
		columns : [[
				{"field":'messageId',checkbox:true }, 
				{"field": 'title',"title" : '标题',width:$(this).width() * 0.2},
				{"field": 'description',"title" : '说明',width:$(this).width() * 0.2},
				{"field": 'keywords',"title" : '关键字',width:$(this).width() * 0.2},
				{
					field : 'messageType',
					title : '消息类型',
					width:$(this).width() * 0.2,
					formatter:function(value,row,index){
                    	if(0==value){
                    		return "文本回复";
                    	}else if(1==value){
                    		return "图文回复";
                    	}
                    }

				},
				{
					field : 'reply_is_use',
					title : '关注回复',
					width:$(this).width() * 0.2,
					formatter:function(value,row,index){
                    	if(0==value){
                    		return "是";
                    	}else if(1==value){
                    		return "否";
                    	}
                    }
				}
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
	$.messager.progress(); 
	window.location.href="<c:url value='/weChatReply/doAddWeChatReply'/>";
}
function doUpdate(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length!=1){
		$.messager.alert('我的消息','请选择一条数据！','info');
		return;
	}
	window.location.href="<c:url value='/weChatReply/doUpdateWeChatReply?messageId="+rows[0].messageId+"'/>";
}
function doDelete(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert('我的消息','请选择一条数据！','info');
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].messageId);
	}
	$.messager.confirm('确认', '是否确定要删除!', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'weChatReply/deleteWeChatReply', {removeIds: ids.join(',')},function(data){
			if(data.message > 0){
				for ( var i = rows.length-1; i >=0; i--) {
				var index = $('#mydatagrid').datagrid('getRowIndex', rows[i]);
					$('#mydatagrid').datagrid('deleteRow', index);
				}
				rows = null;
				$.messager.show({
					title:'系统消息',
					msg:'删除成功!',
					timeout:2000,
					showType:'slide'
				});
			}
		},"json");	
	}, null);	
}


function doReply(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(null==rows || 0==rows.length){
		$.messager.alert('提示','请选择数据！','info');
		return;
	}
	var ids = [];
	var keywords=[];
	var messageTypes=[];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].messageId);
		keywords.push(rows[i].keywords);
		messageTypes.push(rows[i].messageType);
	}
	

	var num=unique(keywords).length+unique(messageTypes).length;
	if(1<rows.length && num!=2){
		$.messager.alert('提示','请选择相同关键字和图文回复类型！','info');
		return;
	}
	var isText=0;
	for(var i=0; i<messageTypes.length; i++){
		
		if(messageTypes[i]==0){
			isText=isText+1;
		}
	}
	if(isText>1){
		
		$.messager.alert('提示','文本回复不允许多个设置！','info');
		return false;
	}

	$.messager.confirm('确认', '是否确定设置关注回复!', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'weChatReply/reply?', {removeIds:ids.join(','),messageType:"0"},function(data){
			if(data.message > 0){
				$('#mydatagrid').datagrid("load"); 
				rows = null;
				$.messager.show({
					title:'系统消息',
					msg:'自动回复设置成功!',
					timeout:2000,
					showType:'slide'
				});
			}
		},"json");	
	}, null);	
}

//数组去重复
function unique(arr) {
	  var ret = []
	  var hash = {}

	  for (var i = 0; i < arr.length; i++) {
	    var item = arr[i]
	    var key = typeof(item) + item
	    if (hash[key] !== 1) {
	      ret.push(item)
	      hash[key] = 1
	    }
	  }
	  return ret
}
function deReply(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(null==rows || 0==rows.length){
		$.messager.alert('提示','请选择数据！','info');
		return;
	}
	var ids = [];
	var keywords=[];
	var messageTypes=[];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].messageId);
		keywords.push(rows[i].keywords);
		messageTypes.push(rows[i].messageType);
	}
	
	var num=unique(keywords).length+unique(messageTypes).length;
	if(1<rows.length && num!=2){
		$.messager.alert('提示','请选择相同关键字和图文回复类型！','info');
		return;
	}
	$.messager.confirm('确认', '是否确定取消关注回复!', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'weChatReply/reply?', {removeIds:ids.join(','),messageType:"1"},function(data){
			if(data.message > 0){
				$('#mydatagrid').datagrid("load"); 
				rows = null;
				$.messager.show({
					title:'系统消息',
					msg:'自动回复设置成功!',
					timeout:2000,
					showType:'slide'
				});
			}
		},"json");	
	}, null);	
}

$(function(){
	
	//删除
	$("#doDelete").bind("click",function(){
		
		doDelete();
	})
	//跳转添加 
	$("#doAdd").bind("click",function(){
		
		 doAdd(); 
		
	});
	//跳转修改 
	$("#doUpdate").bind("click",function(){
		
		doUpdate();
	});
	//跳转修改 
	$("#doReply").bind("click",function(){
		doReply();
	});
	$("#deReply").bind("click",function(){
		
		deReply();
	});
});
</script>  
</html>