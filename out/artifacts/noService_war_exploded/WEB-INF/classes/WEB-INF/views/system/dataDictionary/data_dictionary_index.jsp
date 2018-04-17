<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
     	<script type="text/javascript" src="<c:url value='/resources/js/easyUI1.4.2/datagrid-detailview.js'/>"></script>
  </head>
  <body id="search" class="easyui-layout">   
	<div id="tool" class="datagridsearch">
	  		<a href="#" class="easyui-linkbutton" id="doAddDataDictionary"  data-options="iconCls:'icon-add'">添加</a>
	  		<a href="#" class="easyui-linkbutton" id="doUpdateDataDictionary"  data-options="iconCls:'icon-edit'">修改</a>
	  		<a href="#" class="easyui-linkbutton" id="doAddDataDictionaryDeatil"  data-options="iconCls:'icon-add'">option选项</a>
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

//删除选项方法
function deleteDictionaryOption(dictionary_id,parentId,index){
	//var rows=$('#ddv-'+parentId).datagrid("getSelections");
	$.messager.confirm('确认', '是否确定要删除!', function(r){
		if(!r){
			return;
		}
		$.post(SITE_BASE_PATH+'dataDictionary/deleteDataDictionary', {dictionary_id:dictionary_id},function(data){
			if(data.message > 0){
				$('#ddv-'+parentId).datagrid('deleteRow', index);
				rows = null;
				$.messager.alert("", "数据删除成功！", "warning");
			}
		},"json");	
	}, null);
}

function doUpdateDictionaryOption(dictionary_id){
	
}

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
				$('#mydatagrid').datagrid("load",queryParams); 
			}, 
			menu:'#searchboxSplit', 
			prompt:'请选择需要查询的字段' 
		}); 
	//列表
	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : SITE_BASE_PATH+"dataDictionary/list",
		method : "POST",
		pageSize :20, 
		pageList : [20,50,100],
		columns : [[
					{ field:'dictionary_id',checkbox:true }, 
					{
						field : 'dictionary_name',
						title : '名称',
						width:$(this).width() * 0.2
					}, {
						field : 'dictionary_code',
						title : 'CODE',
						width:$(this).width() * 0.2
					}, {
						field : 'dictionary_type',
						title : '选项类型',
						width:$(this).width() * 0.2,
						formatter:function(value,row,index){
                        	if(1==value){
                        		return "文本框";
                        	}else if(2==value){
                        		return "单选按钮";
                        	}else if(3==value){
                        		return "复选框";
                        	}else if(4==value){
                        		return "字典";
                        	}else if(6==value){
                        		return "下拉列表";
                        	}else if(5==value){
                        		return "选项";
                        	}
                        }

					}, {
						field : 'module_type',
						title : '模块名称',
						width:$(this).width() * 0.2,
						formatter:function(value,row,index){
                        	if(1==value){
                        		return "个人中心";
                        	}
                        }
					},{
						field :'dictionary_explain',
						title:'说明',
						width:$(this).width() * 0.3
					}
				]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true,
		view:detailview,
	    detailFormatter:function(index,data){
				return '<div style="padding:2px"><table id="ddv-' + data.dictionary_id + '"></table></div>';
			},
	        onExpandRow: function(index,row){  
	            $('#ddv-'+row.dictionary_id).treegrid({
	            	url:SITE_BASE_PATH+"dataDictionary/list?rows=1000&page=10&parentId="+row.dictionary_id,
	                fitColumns:true,  
	                singleSelect:true,  
	                rownumbers:true, 
	                loadMsg:'数据正在加载中....', 
	                selectOnCheck: true,
					checkOnSelect: true,
	                height:'auto',
//	         		onClickCell: function (row, field, value) {
//	        			 var row = $('#mydatagrid').datagrid("getSelected");
//	        			 if(null==row) return false;
//	                   if("busname"==field){
//	          			alert(row.memberId);
	                 
//	                   }
//	              },
	                columns:[[  
							{ field:'dictionary_id',checkbox:false }, 
							/* {
								field : 'dictionary_name',
								title : '选项名称',
								width:$(this).width() * 0.2
							}, */{
								field : 'dictionary_code',
								title : 'KEY',
								width:$(this).width() * 0.2
							}, {
								field : 'dictionary_value',
								title : 'VALUE',
								width:$(this).width() * 0.2
							}/* , {
								field : 'dictionary_explain',
								title : '描述',
								width:$(this).width() * 0.2
							} */, 
							{"field":'dictionary',"title":'操作',width:$(this).width() * 0.3,formatter:function(value,row,index){
	                        		return "<a href='javascript:void(0)' class='easyui-linkbutton' onclick='shopEdit("+index+")'>修改</a>&nbsp;<a href='javascript:void(0);' onclick='deleteDictionaryOption("+row.dictionary_id+","+row.parentId+","+index+")'>删除</a>"; 
							}}
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
	
	//添加选项方法
	$("#doAddDataDictionaryDeatil").click(function(){ 
		var rows=$("#mydatagrid").datagrid("getSelections");
		if(rows.length!=1){
			$.messager.alert("", "请选择一条数据!", "warning");
			return;
		}
		if("2"==rows[0].dictionary_type || "3"==rows[0].dictionary_type){
			window.location.href="<c:url value='/dataDictionary/doAddDataDictionaryDeatil'/>?parentId="+rows[0].dictionary_id;
		}else{
			$.messager.alert("", "请选择(单选按钮/文本框)!", "warning");
			return;
		}
	});
	
	//添加方法
	$("#doAddDataDictionary").click(function(){ 
		window.location.href="<c:url value='/dataDictionary/doAddDataDictionary'/>";
	});
	
	
	$("#doUpdateDataDictionary").click(function(){ 
			doUpdateDataDictionary();
	});
	
	//修改方法
	function doUpdateDataDictionary(){
		var rows=$("#mydatagrid").datagrid("getSelections");
		if(rows.length!=1){
			$.messager.alert("", "请选择一条数据！", "warning");
			return;
		}
		window.location.href="<c:url value='/member/jumpEdit?id="+rows[0].memberId+"'/>";
	}
	
	
	
	//删除方法
	$("#deleteDataDictionary").click(function(){ 
			var rows=$("#mydatagrid").datagrid("getSelections");
			if(rows.length!=1){
				$.messager.alert("", "请选择一条数据！", "warning");
				return;
			}
			var ids = [];
			for(var i=0; i<rows.length; i++){
				ids.push(rows[i].dictionary_id);
			}
			$.messager.confirm('确认', '是否确定要删除!', function(r){
				if(!r){
					return;
				}
				$.post(SITE_BASE_PATH+'dataDictionary/deleteDataDictionary', {dictionary_id: ids.join(',')},function(data){
					if(data.message > 0){
						for ( var i = rows.length-1; i >=0; i--) {
						var index = $('#mydatagrid').datagrid('getRowIndex', rows[i]);
							$('#mydatagrid').datagrid('deleteRow', index);
						}
						rows = null;
						$.messager.alert("", "数据删除成功！", "warning");
					}
				},"json");	
			}, null);	
	});
});
</script>  
</html>