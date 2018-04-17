<%@page import="com.suyin.member.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>

  </head>
  <body >   
	<div class="easyui-panel" title="<%=Category.TABLE_ALIAS %>编辑"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td><%=Category.ALIAS_CG_NAME %>:<input type="hidden" name="cgId" value="${category.cgId }"/></td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="填写品类名称" name="CgName" data-options="required:true" value="${category.cgName }"></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="submitForm" >提交修改</a></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
	
			url="<c:url value='/category/update'/>";

			$.ajax({
				type : 'POST',
				url : url,
				data : $("#dataForm").serialize(),
				dataType : "json",
				success: function(data) {
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/category/index'/>";
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
		
		$(function(){
			 //提交表单
			$("#submitForm").bind("click",function(){
				
				submitForm();
			});
		});
	</script>
</body>  
</html>
