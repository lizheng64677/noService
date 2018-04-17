<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../../../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body >   
	<div class="easyui-panel" title="<c:if test='${role!=null }'>角色编辑</c:if><c:if test='${role==null }'>角色添加</c:if>"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>角色名称:<input type="hidden" name="id" value="${role.id }"/></td>
	    			<td><input   class="easyui-textbox" type="text" style="width:300px;height:30px;"  missingMessage="填写角色名称" name="roleName" data-options="required:true" value="${role.roleName }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色类型:</td>
	    			<td>
	    				<select class="easyui-combobox" data-options="required:true" name="roleType" style="width:300px;height:30px;">
		    				<option value="1" <c:if test="${role.roleType==1 }"></c:if>>系统管理员</option>
		    				<option value="2" <c:if test="${role.roleType==2 }"></c:if>>普通管理员</option>
	    				</select>
	    			</td>
	    		</tr>
<!-- 	    		<tr> -->
<!-- 	    			<td>所属应用:</td> -->
<!-- 	    			<td> -->
<!-- 	    				<select class="easyui-combobox" data-options="required:true" name="applicationId" style="width:300px;height:30px;"> -->
<%-- 	    				<c:forEach items="${wxAppList }" var="wxApp"> --%>
<%-- 		    				<option value="${wxApp.applicationId}" <c:if test="${role.applicationId==wxApp.applicationId }">selected="selected"</c:if>>${wxApp.applicationName}</option> --%>
<%-- 	    				 </c:forEach> --%>
<!-- 	    				</select> -->
<!-- 	    			</td> -->
<!-- 	    		</tr> -->
	    		<tr>
	    			<td>角色英文名称:</td>
	    			<td><input class="easyui-textbox" type="text" style="width:300px;height:30px;" name="code" value="${role.code }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色说明:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" style="width:300px;height:30px;" name="roleDescription" value="${role.roleDescription }"></input>
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
			var url="<c:url value='/sysRole/addRole?'/>";
			if('${role}'){
				url="<c:url value='/sysRole/updateRole?'/>";
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
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/sysRole/'/>";
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
