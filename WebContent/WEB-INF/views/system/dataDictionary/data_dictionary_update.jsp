<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-panel" title="数据字典"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<!-- 隐藏表单域存放位置 -->
	    	<input type="hidden" name="parentId" value="${dic.parentId }" />
	    	<input type="hidden" name="dictionary_id" value="${dic.id }" />
	    	<input type="hidden" name="module_type" value="${dic.module_type }" />
	    	<input type="hidden" name="dictionary_type" value="${dic.dictionary_type }" />
	    	<table cellpadding="3">
	    		<tr>
	    			<td>选项名称:</td>
	    			<td><input   class="easyui-textbox" type="text" style="width:300px;height:30px;"  name="dictionary_name" data-options="required:true" value="${dic.name}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>选项类型:</td>
	    			<td>
	    				<select disabled="disabled" class="easyui-combobox" data-options="required:true"  style="width:300px;height:30px;">
		    				<option <c:if test="${'2'==dic.dictionary_type}"> selected="selected"</c:if> value="2" >单选按钮</option>
		    				<option <c:if test="${'3'==dic.dictionary_type}"> selected="selected"</c:if> value="3" >复选框</option>
		    				<option <c:if test="${'1'==dic.dictionary_type}"> selected="selected"</c:if> value="1" >文本框</option>
		    				<option <c:if test="${'4'==dic.dictionary_type}"> selected="selected"</c:if> value="4" >字典</option>
		    				<option <c:if test="${'5'==dic.dictionary_type}"> selected="selected"</c:if> value="5" >选项</option>
		    				<option <c:if test="${'6'==dic.dictionary_type}"> selected="selected"</c:if> value="6" >下拉列表</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>模块:</td>
	    			<td>
	    				<select disabled="disabled" class="easyui-combobox" data-options="required:true"  style="width:300px;height:30px;">
		    				<option <c:if test="${1==dic.module_type}"> selected="selected"</c:if> value="1" >个人资料动态属性</option>
		    				<option <c:if test="${2==dic.module_type}"> selected="selected"</c:if> value="2" >个人中心完善资料</option>
		    				<option <c:if test="${3==dic.module_type}"> selected="selected"</c:if> value="3" >问卷调查</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>VALUE:</td>
	    			<td><input class="easyui-textbox" type="text"  style="width:300px;height:30px;" name="dictionary_value" value="${dic.dictionary_value }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>CODE:</td>
	    			<td><input class="easyui-textbox" type="text"  style="width:300px;height:30px;" name="dictionary_code" value="${dic.dictionary_code }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>描述说明:</td>
	    			<td>
	    				<input class="easyui-textbox" name="dictionary_explain"
							data-options="multiline:true" style="height: 60px; width: 400px;" value="${dic.dictionary_explain }"></input>
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
		var url="<c:url value='/dataDictionary/updateDataDictionary'/>";
		$.ajax({
			type : 'POST',
			url : url,
			data : $("#dataForm").serialize(),
			dataType : "json",
			success: function(data) {
                   if(1==data.message){
                	   $.messager.alert("提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/dataDictionary/treeIndex'/>";
                       });
       
                   }else{
                	   $.messager.alert("提示","提交失败!", "warning");
                   }
            }
		});
	});
	//返回列表
	$("#jumpBack").click(function(){ 
		window.location.href="<c:url value='/dataDictionary/treeIndex'/>";
	});
</script>
</html>