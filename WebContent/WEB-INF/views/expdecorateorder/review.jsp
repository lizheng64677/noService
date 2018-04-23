<%@page import="com.suyin.decorate.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>

  </head>
  <body >   
	<div class="easyui-panel" title="提现审批"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
				<input type="hidden" id = "orderId" name="orderId" value="${expdecorateorder.orderId}"/>	
	    		<tr>
	    			<td>审批结果:</td>
	    			<td>
	    				<select class="easyui-combobox" data-options="required:true" name="state" style="width:300px;height:30px;">
		    				<option value="1" selected="selected">同意</option>
		    				<option value="2" >拒绝</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>审批意见:<input type="hidden" name="" value=""/></td> 
	    			<td>
	    				<input class="easyui-textbox" type="text" style="width:300px;height:100px;" name="description" ></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">确认</a></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
	
			url="<c:url value='/expdecorateorder/review'/>";
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
                		   window.location.href="<c:url value='/expdecorateorder/index'/>";
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
