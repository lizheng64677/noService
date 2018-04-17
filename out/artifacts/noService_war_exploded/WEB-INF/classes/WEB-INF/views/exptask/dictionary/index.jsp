<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body >   
	<div id="tool" class="datagridsearch">
	  		<a href="#" class="easyui-linkbutton" id="doAddDataDictionary"  data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" id="doUpdateDataDictionary"  data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" id="deleteDataDictionary" data-options="iconCls:'icon-remove'">删除</a>
			<input id="searchbox" style="width:300px"></input> 
			<div id="searchboxSplit" style="width:100px"> 
			<div data-options="name:'dictionaryName'">选项名称</div> 
			<div data-options="name:'dictionaryCode'">CODE</div> 
			</div>
	</div>
	<div id="mydatagrid" fit="true"></div>
	<input type="hidden" id="expId" value="${expId }" />
</body>
<script type="text/javascript">
$(function() {
	//搜索框
	$('#searchbox').searchbox({ 
			searcher:function(value,name){ 
				var queryParams;
				if('dictionaryName'==name){
					queryParams={dictionaryName:value};
				}if('dictionaryCode'==name){
					queryParams={dictionaryCode:value};
				}
				$('#mydatagrid').treegrid("load",queryParams); 
			}, 
			menu:'#searchboxSplit', 
			prompt:'请选择需要查询的字段' 
		}); 
	//列表
	$('#mydatagrid').treegrid({
		 url:SITE_BASE_PATH+"expdictionary/list?expId="+$("#expId").val(),
		    idField:'id',
		    treeField:'name',
		 	pageSize :10, 
			pageList : [10,20,50],
			pagination : true,
			rownumbers : true,
			onBeforeLoad : function(node, param) {
				if (node != null) {
					$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"expdictionary/synTreeList?parentId=" + node.id;  
				}else{
					$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"expdictionary/list?expId="+$("#expId").val();
				}
				return true;
			},
	   	 columns : [[
					{ field:'dictionaryId',checkbox:true }, 
					{
						field : 'name',
						title : '名称'
					}, {
						field : 'dictionaryCode',
						title : 'CODE'
						
					}, {
						field : 'dictionaryValue',
						title : 'VALUE'
						
					}, {
						field : 'dictionaryType',
						title : '选项类型',
						formatter:function(value,row,index){
                        	if(1==value){
                        		return "文本框";
                        	}else if(2==value){
                        		return "单选按钮";
                        	}else if(3==value){
                        		return "复选框";
                        	}else if(4==value){
                        		return "字典";
                        	}else if(5==value){
                        		return "选项";
                        	}else if(6==value){
                        		return "下拉列表";
                        	}
                        }

					}, {
						field : 'moduleType',
						title : '模块名称',
						formatter:function(value,row,index){
                        	if(1==value){
                          		return "问卷调查";
                        	}else if(2==value){
                        		
                        		return "试用问卷";
                        	}else{
                        		
                        		return "未知模块";
                        	}
                        }
					}
				]],
		toolbar :'#tool'
	});
	
	//添加方法
	$("#doAddDataDictionary").click(function(){ 
		var row=$("#mydatagrid").treegrid("getSelected");
		if(null!=row){
			window.location.href="<c:url value='/expdictionary/jumpAdd'/>?parentId="+row.id+"&expId="+$("#expId").val();
		}else{
			window.location.href="<c:url value='/expdictionary/jumpAdd'/>?expId="+$("#expId").val();
		}
		
	});
	
	//删除方法
	$("#deleteDataDictionary").click(function(){ 
			var rows=$("#mydatagrid").treegrid("getSelections");
			if(rows.length!=1){
				$.messager.alert("", "请选择一条数据！", "warning");
				return;
			}
			var ids = [];
			for(var i=0; i<rows.length; i++){
				ids.push(rows[i].id);
			}
			if(ids.length>1){
				$.messager.alert("提示", "本功能不支持批量操作！", "warning");
				return false;
			}
			$.messager.confirm('确认', '是否确定要删除!', function(r){
				if(!r){
					return;
				}
				$.post(SITE_BASE_PATH+'expdictionary/delete', {id: ids.join(','),expId:$("#expId").val()},function(data){
					if(data.message > 0){
						for ( var i = rows.length-1; i >=0; i--) {
						 	$('#mydatagrid').treegrid('remove', rows[i].id);
						}
						rows = null;
					}
				},"json");	
			}, null);	
	});
	$("#doUpdateDataDictionary").click(function(){ 
		doUpdateDataDictionary();
	});
});
//修改方法
function doUpdateDataDictionary(){
	var row=$("#mydatagrid").treegrid("getSelected");
	if(null==row){
		$.messager.alert("", "请选择需要修改的数据！", "warning");
	}else{
		window.location.href="<c:url value='/expdictionary/jumpEdit'/>?id="+row.id+"&expId="+$("#expId").val();
	}
}
</script>  
</html>