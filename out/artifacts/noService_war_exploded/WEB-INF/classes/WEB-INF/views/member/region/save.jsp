<%@page import="com.suyin.member.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>商圈名称:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"   missingMessage="填写商圈名称" name="regionName" data-options="required:true" value=""></input></td>
	    		</tr>
	    		<tr>
	    			<td>城市:</td>
	    			<td>
	    			
	    			<select style="width:145px;height:30px;" id="selectCity" name="provinId">
	    				<option value="">---请选择---</option>
	    				<c:forEach items="${cityList }" var="ct">
	    				 <option value="${ct.id}">${ct.name }</option>
	    				</c:forEach>
	    			</select>
	    			
	    			<select name="cityId" style="width:145px;height:30px;" id="cityId" >
						<option value="">---请选择--- </option>
	    			</select>
	    			
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="submitForm">提交保存</a></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
	
			url="<c:url value='/region/add'/>";
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
                		   window.location.href="<c:url value='/region/index'/>";
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
			
			$("#selectCity").bind("change",function(){
				$("#cityId").empty();
				var html="";
				$.ajax({
					type:"post",
					url:"<c:url value='/city/findForCityByUpid'/>",
					data:{"upid":this.value},
					dataType:"json",
					success:function(data){
						
						$(data.result).each(function(i,val){
							
							html+="<option value="+val.id+">"+val.name+"</option>";
							
						});
						$("#cityId").append(html);
					}
				});
			});
			 //提交表单
			 $("#submitForm").bind("click",function(){
					
					submitForm();
				});
		});
	</script>
</body>
</html>