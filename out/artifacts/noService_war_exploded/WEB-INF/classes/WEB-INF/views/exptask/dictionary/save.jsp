<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-panel" title="问卷调查添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<input type="hidden" name="parentId" value="${null==parentId?0:parentId }" />
	    	<input type="hidden" name="expId" value="${expId }" id="expId"/>
	    	<table cellpadding="3">
	    		<tr>
	    			<td>选项名称:</td>
	    			<td><input   class="easyui-textbox" type="text" style="width:300px;height:30px;"  name="dictionaryName" data-options="required:true" value=""></input></td>
	    		</tr>
	    		<tr>
	    			<td>选项类型:</td>
	    			<td>
	    				<select class="easyui-combobox" data-options="required:true" name="dictionaryType" style="width:300px;height:30px;">
		    				<option value="2" >单选按钮</option>
		    				<option value="3" >复选框</option>
		    				<option value="6" >下拉列表</option>
		    				<option value="1" >文本框</option>
		    				<option value="4" >字典</option>
		    				<option value="5" >选项</option>
	    				</select>
	    			</td>
	    		</tr>
<%-- 	    		<input type="hidden" name="moduleType"  value="${moduleType}"/> --%>
	    		<tr>
	    			<td>模块:</td>
	    			<td>
	    				<select class="easyui-combobox" data-options="required:true" name="moduleType" style="width:300px;height:30px;">
		    				<option value="1">问卷调查</option>
		    				<option value="2">试用问卷</option>
	    				</select>
	    				<span style="color:red;">请注意模块类型</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>VALUE:</td>
	    			<td><input class="easyui-textbox" type="text"  style="width:300px;height:30px;" name="dictionaryValue" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>CODE:</td>
	    			<td><input class="easyui-textbox" type="text"  style="width:300px;height:30px;" name="dictionaryCode" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>描述说明:</td>
	    			<td>
	    				<input class="easyui-textbox" name="dictionary_Explain"	data-options="multiline:true" style="height: 60px; width: 400px;"></input>
	    			</td>
	    		</tr>
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
		var url="<c:url value='/expdictionary/add'/>";
		$.ajax({
			type : 'POST',
			url : url,
			data : $("#dataForm").serialize(),
			dataType : "json",
			success: function(data) {
                   if(data.message=="success"){
                	   $.messager.alert("提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/expdictionary/index?id='/>"+$("#expId").val(); 
                       });
       
                   }else{
                	   $.messager.alert("提示","提交失败!", "warning");
                   }
            }
		});
	});
	//返回列表
	$("#jumpBack").click(function(){ 
		window.location.href="<c:url value='/expdictionary/index?id='/>"+$("#expId").val();
	});
</script>
</html>