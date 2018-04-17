<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../../../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style>
  .roleBox{width:300px;margin:20px auto;}
  .roleBox ul{list-style-type: none;}
  .roleBox ul li{margin:5px;float:left;}
  </style>
  </head>
  <body >   
	<div class="easyui-panel" title="用户密码修改"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>您的旧密码:</td>
	    			<td><input   class="easyui-textbox" type="password" style="width:300px;height:30px;" id="pwd"   missingMessage="输入您的旧密码"  data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>输入新密码:</td>
	    			<td><input   class="easyui-validatebox" type="password" style="width:300px;height:30px;" id="npwd"   missingMessage="输入新密码" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>新密码确认:</td>
	    			<td><input   class="easyui-validatebox" type="password" style="width:300px;height:30px;"required="required" validType="equals['#npwd']" ></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">提交</a></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			var url="<c:url value='/sysUser/editPwd?'/>";
			$.ajax({
				type : 'POST',
				url : url,
				data : {pwd:$("#pwd").val(),npwd:$("#npwd").val()},
				dataType : "json",
				success: function(data) {
	                   if(data.result>0){
	                	   $.messager.alert("操作提示", "提交成功，退出重新登录！", "info", function () {		
	                		   window.parent.location.href="<c:url value='/login/doLogin'/>";
	                       });
	       
	                   }else if(data.result==-1){
	                	   $.messager.alert("操作提示","您的旧密码输入有误!");
	                   }
	                   else{
	                	   $.messager.alert("操作提示","提交失败!");
	                   }
	            }
			});
		}
		function clearForm(){
			$('#dataForm').form('reset');
		}
		$.extend($.fn.validatebox.defaults.rules, {    
		    equals: {    
		        validator: function(value,param){    
		            return value == $(param[0]).val();    
		        },    
		        message: '两次密码输入不一致'   
		    }    
		});  


	</script>
</body>  
</html>
