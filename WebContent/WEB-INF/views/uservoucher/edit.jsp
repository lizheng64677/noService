<%@page import="com.suyin.uservoucher.model.*" %>
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
	    			<td>券名:<input type="hidden" name="id" value="${voucher.id }"/></td> 
	    			<td><input    type="text" style="width:300px;height:30px;"disabled="disabled"data-options="required:true" value="${voucher.name }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>券金额:</td> 
	    			<td><input    type="text" style="width:300px;height:30px;"  disabled="disabled"data-options="required:true" value="${voucher.price }"></input></td>
	    		</tr> 
	    			<tr>
	    			<td>返佣金额:</td> 
	    			<td><input    type="text" style="width:300px;height:30px;"  disabled="disabled" data-options="required:true" value="${voucher.usePrice }"></input></td>
	    		</tr>
	    			<tr>
	    			<td>购买用户-推荐人:</td> 
	    			<td>
	    			<img alt="购买用户" width="40px;" height="40px;" src="${voucher.headImg }">--<img alt="推荐人" width="40px;" height="40px;" src="${voucher.uheadImg }"></br>
	    			${voucher.nickName }--${voucher.unickName }
	    			 </td>
	    		</tr>
	    			<tr>
	    			<td>购买时间:</td> 
	    			<td><input    type="text" style="width:300px;height:30px;"  disabled="disabled"  data-options="required:true" value="${voucher.createTime }"></input></td>
	    		</tr>
	    			<tr>
	    			<td>券类型:</td> 
	    			<td>
	    				<select  style="width:300px;height:30px;" disabled="disabled">
		    				<option value="0" <c:if test="${voucher.type==0 }">selected="selected"</c:if>>福利券</option>
		    				<option value="1"<c:if test="${voucher.type==1 }">selected="selected"</c:if>>体验券</option>
	    					<option value="2"<c:if test="${voucher.type==2 }">selected="selected"</c:if>>优惠券</option>
	    				</select>
	    			</td>
	    		</tr>	   
	    		<tr>
	    			<td>处理:</td> 
	    			<td>		
		    		<select id="state" class="input" name="state" >
						<option value="-1">---请选择---</option>					
						<option value="1">---消券---</option>
						<option value="2">---作废---</option>
					</select>
				</td>
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
	
			url="<c:url value='/expdecorateuservoucher/update'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			var state=$("#state").val();
			if("-1"==state){
				$.messager.alert("提示", "请选择处理事项", true, "warning");
				return;
			}
			$.messager.confirm('确认', '是否确定修改券状态!', function(data){
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
	                	   $.messager.alert("操作提示", "处理成功！", "info", function () {
	                		   window.location.href="<c:url value='/expdecorateuservoucher/index'/>";
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
