<%@page import="com.suyin.decorate.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>

  </head>
  <body >   
	<div class="easyui-panel" title="编辑"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>:<input type="hidden" name="" value=""/></td> 
	    			<td><input    type="text" style="width:300px;height:30px;"  missingMessage="" name="" data-options="required:true" value=""></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">提交修改</a></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
	
			url="<c:url value='/expdecorate/update'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			$.ajax({
				type : 'POST',
				url : url,
				data : $("#dataForm").serialize(),
				dataType : "json",
				success: function(data) {
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/expdecorate/index'/>";
                       });
       
                   }else{
                	   $.messager.alert("操作提示","提交失败!");
                   }
	            } 
			});
		}
		
		function clearForm(){
			$('#dataForm').form('reset');
		}
		
	</script>
</body>  
</html>
