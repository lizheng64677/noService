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
	<div class="easyui-panel" title="<c:if test='${user!=null }'>用户编辑</c:if><c:if test='${user==null }'>用户添加</c:if>"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>登录账号:
		    			<input type="hidden" name="id" value="${user.id }"/>
	    			</td>
	    			<td><input   class="easyui-validatebox" type="text" style="width:300px;height:30px;"   missingMessage="填写登录账号" name="loginName"  required="required" validType="isExist" value="${user.loginName }"></input></td>
	    		</tr>
	    		<c:if test="${user==null }">
		    		<tr>
		    			<td>登录密码:</td>
		    			<td><input   class="easyui-textbox" type="text" style="width:300px;height:30px;"   missingMessage="填写登录密码" name="loginPwd" data-options="required:true"></input></td>
		    		</tr>
	    		</c:if>
	    		<tr>
	    			<td>用户名称:</td>
	    			<td><input   class="easyui-textbox" type="text"  style="width:300px;height:30px;"  missingMessage="填写用户名称" name="nickName" data-options="required:true" value="${user.nickName }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色选择:</td>
	    			<td>
	    				<div class="roleBox">
		    				<ul>
			    				<c:forEach items="${roleList }" var="role">
			    					<li>
			    						<input type="checkbox" name="role" value="${role.id }" <c:if test="${user!=null&&role.state!=0 }">checked="checked"</c:if>/>${role.roleName }
			    					</li>
			    				</c:forEach>
		    				</ul>
	    				</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>可用状态:</td>
	    			<td>
	    				<select class="easyui-combobox" data-options="required:true" name="state" style="width:300px;height:30px;">
		    				<option value="0" <c:if test="${user.state==0 }">selected="selected"</c:if>>可用</option>
		    				<option value="1" <c:if test="${user.state==1 }">selected="selected"</c:if>>禁用</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" style="width:300px;height:30px;" name="remark" value="${user.remark }"></input>
	    			</td>
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
			var url="<c:url value='/sysUser/addUser?'/>";
			if('${user}'){
				url="<c:url value='/sysUser/updateUser?'/>";
			}
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
	                   if(data.result>0){
	                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
	                		   window.location.href="<c:url value='/sysUser/index'/>";
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
		$.extend($.fn.validatebox.defaults.rules, {    
		    isExist: {    
		        validator: function(value){ 
		        	var flag;
		        	var loginName='${user.loginName}';
		        	$.ajax({
						type : 'POST',
						url : "<c:url value='/sysUser/isExistLoginName?'/>",
						data : {loginName:value},
						dataType : "json",
						async:false,
						success: function(data) {
							if(data){
								flag=data.isExist;
							}
						}
		        	});
		        	return flag==0||value == loginName;
		        },    
		        message: '此登录账号已存在'   
		    }    
		});  
	</script>
</body>  
</html>
