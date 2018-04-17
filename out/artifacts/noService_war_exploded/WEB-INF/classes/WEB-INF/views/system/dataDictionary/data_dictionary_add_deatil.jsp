<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-panel" title="数据字典选项添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<!-- 隐藏表单域存放位置 -->
	    	<input type="hidden" name="parentId" value="${parentId }" />
	    	<input type="hidden" name="dictionary_type" value="0" />
	    	<input type="hidden" name="module_type" value="0" />
	    	<table cellpadding="3">
	    		<!-- <tr>
	    			<td>选项名称:</td>
	    			<td><input   class="easyui-textbox" data-options="required:true" type="text" style="width:300px;height:30px;"  name="dictionary_name" data-options="required:true" value=""></input></td>
	    		</tr> -->
	    		<tr>
	    			<td>KEY:</td>
	    			<td><input class="easyui-textbox" type="text" data-options="required:true" style="width:300px;height:30px;" name="dictionary_code" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>VALUE:</td>
	    			<td><input class="easyui-textbox" data-options="required:true" type="text"  style="width:300px;height:30px;" name="dictionary_value" ></input></td>
	    		</tr>
	    		<!-- <tr>
	    			<td>描述说明:</td>
	    			<td>
	    				<input class="easyui-textbox" name="dictionary_explain"
							data-options="multiline:true" style="height: 60px; width: 400px;"></input>
	    			</td>
	    		</tr> -->
	    		<tr>
	    			<td colspan="2" align="center"> 
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="subDataForm" >提交</a>
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="jumpBack" >返回</a>								
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>   
</body>
<script type="text/javascript">
	//保存方法
	$("#subDataForm").click(function(){ 
		var isValid = $("#dataForm").form('validate');
		if(!isValid){
			return false;
		}
		var url="<c:url value='/dataDictionary/addDataDictionary'/>";
		$.ajax({
			type : 'POST',
			url : url,
			data : $("#dataForm").serialize(),
			dataType : "json",
			success: function(data) {
                   if(1==data.message){
                	   $.messager.alert("提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/dataDictionary'/>";
                       });
       
                   }else{
                	   $.messager.alert("提示","提交失败!", "warning");
                   }
            }
		});
	});
	//返回列表
	$("#jumpBack").click(function(){ 
		window.location.href="<c:url value='/dataDictionary'/>";
	});
</script>
</html>