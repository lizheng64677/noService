<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
     	<%-- <script type="text/javascript" src="<c:url value='/resources/js/easyUI1.4.2/datagrid-detailview.js'/>"></script> --%>
  </head>
  <body >   
	<div id="tool" class="datagridsearch">
	  		<a href="#" class="easyui-linkbutton" id="doAddDataDictionary"  data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" id="doUpdateDataDictionary"  data-options="iconCls:'icon-edit'">修改</a>
			<a href="#" class="easyui-linkbutton" id="deleteDataDictionary" data-options="iconCls:'icon-remove'">删除</a>
			<input id="searchbox" style="width:300px"></input> 
			<div id="searchboxSplit" style="width:100px"> 
			<div data-options="name:'dictionary_name'">选项名称</div> 
			<div data-options="name:'dictionary_code'">CODE</div> 
			</div>
	</div>
	<div id="mydatagrid" fit="true"></div>
	
</body>
<script type="text/javascript">
$(function() {
	//搜索框
	$('#searchbox').searchbox({ 
			searcher:function(value,name){ 
				var queryParams;
				if('dictionary_name'==name){
					queryParams={dictionary_name:value};
				}if('dictionary_code'==name){
					queryParams={dictionary_code:value};
				}
				$('#mydatagrid').treegrid("load",queryParams); 
			}, 
			menu:'#searchboxSplit', 
			prompt:'请选择需要查询的字段' 
		}); 
	//列表
	$('#mydatagrid').treegrid({
		 url:SITE_BASE_PATH+"dataDictionary/list",
		    idField:'id',
		    treeField:'name',
		 	pageSize :10, 
			pageList : [10,20,50],
			pagination : true,
			rownumbers : true,
			onBeforeLoad : function(node, param) {
				if (node != null) {
					$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"dataDictionary/synTreeList?parentId=" + node.id;
				}else{
					$('#mydatagrid').treegrid('options').url = SITE_BASE_PATH+"dataDictionary/list";
				}
				return true;
			},
	   	 columns : [[
					{ field:'dictionary_id',checkbox:true }, 
					{
						field : 'name',
						title : '名称'
					}, {
						field : 'dictionary_code',
						title : 'CODE'
						
					}, {
						field : 'dictionary_value',
						title : 'VALUE'
						
					}, {
						field : 'dictionary_type',
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
						field : 'module_type',
						title : '模块名称',
						formatter:function(value,row,index){
                        	if(1==value){
                        		return "个人资料动态属性";
                        	}if(2==value){
                        		return "个人中心完善资料";
                        	}if(3==value){
                        		return "问卷调查";
                        	}
                        }
					},{
						field :'dictionary_explain',
						title:'说明'
					}
				]],
		toolbar :'#tool'
	});
	
	//添加方法
	$("#doAddDataDictionary").click(function(){ 
		var row=$("#mydatagrid").treegrid("getSelected");
		if(null!=row){
			window.location.href="<c:url value='/dataDictionary/doAddDataDictionary'/>?parentId="+row.id;
		}else{
			window.location.href="<c:url value='/dataDictionary/doAddDataDictionary'/>";
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
			/* alert(ids);
			return false; */
			$.messager.confirm('确认', '是否确定要删除!', function(r){
				if(!r){
					return;
				}
				$.post(SITE_BASE_PATH+'dataDictionary/deleteDataDictionary', {dictionary_id: ids.join(',')},function(data){
					if(data.message > 0){
						for ( var i = rows.length-1; i >=0; i--) {
						 	$('#mydatagrid').treegrid('remove', rows[i].id);
						}
						rows = null;
						$.messager.alert("", "数据删除成功！", "warning");
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
		window.location.href="<c:url value='/dataDictionary/doUpdateDataDictionary'/>?id="+row.id;
	}
}
</script>  
</html>