<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
</head>
<body>
	<div class="easyui-panel" title="调查问卷修改"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">

	    	<input type="hidden" name="parentId" value="${dic.parentId}" />
	    	<input type="hidden" name="dictionaryId" value="${dic.id }" />
	    	<input type="hidden" name="moduleType" value="${dic.moduleType }" />
	    	<input type="hidden" name="dictionaryType" value="${dic.dictionaryType }" />
	    	<input type="hidden" name="expId" value="${expId }" id="expId"/>
	    	<table cellpadding="3">
	    		<tr>
	    			<td>选项名称:</td>
	    			<td><input   class="easyui-textbox" type="text" style="width:300px;height:30px;"  name="dictionaryName" data-options="required:true" value="${dic.dictionaryName}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>选项类型:</td>
	    			<td>
	    				<select disabled="disabled" class="easyui-combobox" data-options="required:true"  style="width:300px;height:30px;">
		    				<option <c:if test="${'2'==dic.dictionaryType}"> selected="selected"</c:if> value="2" >单选按钮</option>
		    				<option <c:if test="${'3'==dic.dictionaryType}"> selected="selected"</c:if> value="3" >复选框</option>
		    				<option <c:if test="${'1'==dic.dictionaryType}"> selected="selected"</c:if> value="1" >文本框</option>
		    				<option <c:if test="${'4'==dic.dictionaryType}"> selected="selected"</c:if> value="4" >字典</option>
		    				<option <c:if test="${'5'==dic.dictionaryType}"> selected="selected"</c:if> value="5" >选项</option>
		    				<option <c:if test="${'6'==dic.dictionaryType}"> selected="selected"</c:if> value="6" >下拉列表</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>模块:</td>
	    			<td>
	    				<select disabled="disabled" class="easyui-combobox" data-options="required:true"  style="width:300px;height:30px;">		   
		    				<option value="1"<c:if test="${1==dic.moduleType}"> selected="selected"</c:if>>问卷调查</option>
		    				<option value="2"<c:if test="${2==dic.moduleType}"> selected="selected"</c:if>>试用问卷</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>VALUE:</td>
	    			<td><input class="easyui-textbox" type="text"  style="width:300px;height:30px;" name="dictionaryValue" value="${dic.dictionaryValue }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>CODE:</td>
	    			<td><input class="easyui-textbox" type="text"  style="width:300px;height:30px;" name="dictionaryCode" value="${dic.dictionaryCode }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>描述说明:</td>
	    			<td>
	    				<input class="easyui-textbox" name="dictionaryExplain"
							data-options="multiline:true" style="height: 60px; width: 400px;" value="${dic.dictionaryExplain }"></input>
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
		var url="<c:url value='/expdictionary/update'/>";
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