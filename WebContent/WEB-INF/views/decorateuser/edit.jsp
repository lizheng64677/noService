<%@page import="com.suyin.decorateuser.model.*" %>
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
	    			<td>姓名:<input type="hidden" name="id" value="${expdecorateuser.userId }"/></td> 
	    			<td><input    type="text" style="width:300px;height:30px;"disabled="disabled"data-options="required:true" value="${expdecorateuser.userName }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>微信昵称:</td> 
	    			<td><input    type="text" style="width:300px;height:30px;"  disabled="disabled"data-options="required:true" value="${expdecorateuser.nickName }"></input></td>
	    		</tr> 	    		
	    			<tr>
	    			<td>用户<c:if test="${null!=expdecorateuser.unickName }">-推荐人</c:if>:</td> 
	    			<td>
	    			<img alt="购买用户" width="40px;" height="40px;" src="${expdecorateuser.headImg }"><c:if test="${null!=expdecorateuser.unickName }">--<img alt="推荐人" width="40px;" height="40px;" src="${expdecorateuser.uheadImg }"></c:if></br>
	    			${expdecorateuser.nickName }<c:if test="${null!=expdecorateuser.unickName }">--${expdecorateuser.unickName }</c:if>
	    			 </td>
	    		</tr>
	    		<tr>
	    			<td>手机号:</td> 
	    			<td><input    type="text" style="width:300px;height:30px;"  disabled="disabled"data-options="required:true" value="${expdecorateuser.userPhone }"></input></td>
	    		</tr> 
	    		<tr>
	    			<td>支付宝:</td> 
	    			<td><input    type="text" style="width:300px;height:30px;"  disabled="disabled"data-options="required:true" value="${expdecorateuser.alipayNumber }"></input></td>
	    		</tr> 
	    		<tr>
	    			<td>用户状态:</td> 
	    			<td>
	    				<select  style="width:300px;height:30px;" disabled="disabled">
		    				<option value="0" <c:if test="${expdecorateuser.userState==0 }">selected="selected"</c:if>>正常</option>
		    				<option value="1"<c:if test="${expdecorateuser.userState==1 }">selected="selected"</c:if>>锁定</option>
	    					<option value="2"<c:if test="${expdecorateuser.userState==2 }">selected="selected"</c:if>>删除</option>
	    				</select>
	    			</td>
	    		</tr>	 
				<c:if test="${null!=expdecorateuser.unickName }"> 
	    		<tr id="sigPrice">
	    			<td>返佣金额:</td> 
	    			<td><input  class="easyui-validatebox input"  type="text" style="width:250px;height:30px;" data-options="required:true" name="backPrice" ></input>
	    			(*)签单成功后返给推荐人的金额
	    			</td>
	    		</tr>
	    		</c:if>
	    		<tr>
	    			<td>处理:</td> 
	    			<td>		
		    		<select id="isSign" class="input" name="isSign" >
						<option value="-1">---请选择---</option>					
						<option value="1">---签约---</option>
						<option value="2">---解除签约---</option>
					</select>
				</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">提交操作</a></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
	
			url="<c:url value='/expdecorateuser/update'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			var isSign=$("#isSign").val();
			if("-1"==isSign){
				$.messager.alert("提示", "请选择处理事项", true, "warning");
				return;
			}
			
			$.messager.confirm('确认', '是否确定签单!', function(data){
				if(!data){
					return;
				}
				$.ajax({
					type : 'POST',
					url : url,
					data : $("#dataForm").serialize(),
					dataType : "json",
					success: function(data) {
	                   if(1==data.result){
	                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
	                		   window.location.href="<c:url value='/expdecorateuser/index'/>";
	                       });
	       
	                   }else{
	                	   $.messager.alert("操作提示","提交失败!");
	                   }
		            } 
				});

			}, null);	

		}
		
		function clearForm(){
			$('#dataForm').form('reset');
		}
		
	</script>
</body>  
</html>
