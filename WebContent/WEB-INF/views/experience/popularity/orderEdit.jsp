<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />

</head>
<body>
	<div class="easyui-panel" title="编辑"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<input type="hidden" id="orderId" name="orderId"  value="${expTime.orderId }"/>
	    	<input type="hidden" id="timeId"  value="${expTime.timeId }"/>
			<input type="hidden" id="expId"   value="${expTime.expId }"/>
			<input type="hidden" id="expType" value="${expTime.expType }"/>
	    	<table cellpadding="3">
	    		<tr>
	    			<td>活动标题:</td>
	    			<td>${expTime.title }</td>
	    		</tr>
	    		<tr>
	    			<td>活动类型:</td>
	    			<td>
	    				<c:if test="${expTime.expType eq 0 }">抽奖式</c:if>
	    				<c:if test="${expTime.expType eq 1 }">人气式</c:if>
	    				<c:if test="${expTime.expType eq 3 }">兑换式</c:if>
	    				<c:if test="${expTime.expType eq 2 }">试用式</c:if>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>活动时间：</td>
		    		<td>
		    			${expTime.beginTime }-${expTime.endTime }
		    		</td>
	    		</tr>
	    		<tr>
		    		<td>订单状态:</td>
		    		<td>
		    			<c:if test="${expTime.popStatus eq 0}">
		    				<input type="radio" value="0"  class="easyui-validatebox radio" name="popStatus" checked="checked" />申请中
		    				<input type="radio" value="1" class="easyui-validatebox radio" name="popStatus" />申请成功
		    				<input type="radio" value="2" class="easyui-validatebox radio" name="popStatus" />申请失败
		    			</c:if>
		    			<c:if test="${expTime.popStatus eq 1}">
		    				<input type="radio" value="0" class="easyui-validatebox radio" name="popStatus" />申请中
		    				<input type="radio" value="1" class="easyui-validatebox radio" name="popStatus"  checked="checked"/>申请成功
		    				<input type="radio" value="2" class="easyui-validatebox radio" name="popStatus" />申请失败
		    			</c:if>
		    			<c:if test="${expTime.popStatus eq 2}">
		    				<input type="radio" value="0" class="easyui-validatebox radio" name="popStatus" />申请中
		    				<input type="radio" value="1"class="easyui-validatebox radio" name="popStatus" />申请成功
		    				<input type="radio" value="2" class="easyui-validatebox radio" name="popStatus" checked="checked" />申请失败
		    			</c:if>
		    		</td>
	    		</tr>
	    		<tr>	    		
		    		<td>是否中奖:</td>
		    		<td>
		    			<c:if test="${expTime.prizeStatus eq 0}">
		    				<input type="radio" value="0" class="easyui-validatebox radio" name="prizeStatus" checked="checked" />未中奖
		    				<input type="radio" value="1" class="easyui-validatebox radio" name="prizeStatus" />已中奖
		    			</c:if>
		    			<c:if test="${expTime.prizeStatus eq 1}">
		    				<input type="radio" value="0" class="easyui-validatebox radio" name="prizeStatus" />未中奖
		    				<input type="radio" value="1" class="easyui-validatebox radio" name="prizeStatus"  checked="checked"/>已中奖
		    			</c:if>
		    		</td>
	    		</tr>
	    		<tr id="labelText">
		    		<td>分享数:</td>
		    		<td>
		    			<input id="shareNum"  class="easyui-validatebox input" type="text" name="shareNum" value="${expTime.shareNum }"/>	
		    		</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">提交保存</a></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
			url="<c:url value='/exprenqi/updateOrder'/>";
			var shareNum=$("#shareNum").val();
			if(shareNum.trim()==""|| shareNum.trim()==null){
				 $.messager.alert("操作提示","分享数不能为空!");
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
                		   
                		   window.location.href="<c:url value='/exprenqi/userPartList?expId="+$("#expId").val()+"&timeId="+$("#timeId").val()+"'/>";
                       });
       
                   }else{
                	   $.messager.alert("操作提示","提交失败!");
                   }
	            } 
			});
		}
	</script>
</body>
</html>