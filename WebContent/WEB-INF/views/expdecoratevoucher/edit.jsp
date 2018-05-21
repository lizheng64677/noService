<%@page import="com.suyin.decoratevoucher.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
<script type="text/javascript" src=" <c:url value="/resources/js/My97DatePicker/WdatePicker.js"></c:url>"> </script>
<link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
<script type="text/javascript">
var editor1="";
var urlLoad="<c:url value='/file/upload'/>";
var urlManager="<c:url value='/file/fileManager'/>";
var type="";
KindEditor.ready(function(K) {
	
	editor1=K.create("#content1",{
		uploadJson :urlLoad,
		fileManagerJson :urlManager,
		filePostName:"imgFile",
		allowFileManager : false,
	    extraFileUploadParams : {
	    	module :"decorate"
      }
	});
	
	K('#imgButton').click(function() {
		
		type=$(this).data("type"); //当前上传图片的类型 
		editor1.loadPlugin('image',function() {
			editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
				
					K('#voucheUrl').val(url);
					K("#voucheImg").attr("src",url);
					editor1.hideDialog();
				}
			});
		});
	});	
});
	
</script>
  </head>
  <body >   
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>券名称:</td>
	    			<input type="hidden" id="${expdecoratevoucher.id }" name="id" value="${expdecoratevoucher.id }"/>
	    			<td><input    class="easyui-validatebox input" type="text" style="width:300px;height:30px;"  missingMessage="请输入券名称" name="name" data-options="required:true" value="${expdecoratevoucher.name }"></input></td>
	    		</tr>
	    		 <tr>
	    			<td>券简述:</td>
	    			<td><input    class="easyui-validatebox input" type="text" style="width:300px;height:30px;"  missingMessage="请输入券简述" name="title" data-options="required:true" value="${expdecoratevoucher.title}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>券金额:</td>
	    			<td><input    class="easyui-validatebox input" type="text" style="width:300px;height:30px;"  missingMessage="请输入券金额" name="price" data-options="required:true" value="${expdecoratevoucher.price}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>券返现金额:</td>
	    			<td><input    class="easyui-validatebox input" type="text" style="width:300px;height:30px;"  missingMessage="请输入返现金" name="usePrice" data-options="required:true" value="${expdecoratevoucher.usePrice}"></input></td>
	    		</tr>	    	
	    		<tr>
	    			<td>券总数:</td>
	    			<td><input    class="easyui-validatebox input" type="text" style="width:300px;height:30px;"  missingMessage="请输入券的总数量" name="num" data-options="required:true" value="${expdecoratevoucher.num}"></input></td>
	    		</tr>	
	    		<tr>
			    	<td>福券示图:</td>
			    	<td>	
			    		<input type="hidden" name="voucheUrl" id="voucheUrl" value="${expdecoratevoucher.voucheUrl}"> 
			    	 	<img alt="" src="${expdecoratevoucher.voucheUrl}" id="voucheImg" style="width:70px;height:70px">    			
			    		<input type="button" id="imgButton" class="easyui-linkbutton"  data-type="pro_default" style="width:70px;height:25px;"  value="上传"/>
			    	</td>
			    </tr>
			    <tr>
	    			<td>福券状态:</td>
	    			<td>
	    				<select class="" id="state" name="state" style="width:300px;height:30px;">
		    				<option value="0" <c:if test="${expdecoratevoucher.state==0 }">selected="selected"</c:if>>长期</option>
		    				<option value="1"<c:if test="${expdecoratevoucher.state==1 }">selected="selected"</c:if>>定期</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr id="validityDaytr">
	    			<td>有效期:</td>
	    			<td><input type="text" style="width:300px;height:30px;"  missingMessage="请输入有效期" id="validityDay" name="validityDay" data-options="required:true" value="${expdecoratevoucher.validityDay}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>福券类型:</td>
	    			<td>
	    				<select name="type" style="width:300px;height:30px;">
		    				<option value="0" <c:if test="${expdecoratevoucher.type==0 }">selected="selected"</c:if>>福利券</option>
		    				<option value="1"<c:if test="${expdecoratevoucher.type==1 }">selected="selected"</c:if>>体验券</option>
	    					<option value="2"<c:if test="${expdecoratevoucher.type==2 }">selected="selected"</c:if>>优惠券</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>福券说明:</td>
	    			<td>
	    		<textarea rows="14" cols="100" id="content1">${expdecoratevoucher.content}</textarea>
	    		<input type="hidden" id=content name="content"/>
	    		</tr>
  				<tr>
	    			<td colspan="2" align="center">
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">提交保存</a>
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="jumpBack" >返回</a>	
	    			 </td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		$(function(){
			if(0==$("#state").val()){
				$("#validityDaytr").hide();
			}
			//返回列表
			$("#jumpBack").click(function(){ 
				window.location.href="<c:url value='/expdecoratevoucher/index'/>";
			});
		})
		function submitForm(){
	
			url="<c:url value='/expdecoratevoucher/update'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			$("#content").val(editor1.html());
			var n=$('#state').val();
			var vday=$("#validityDay").val();
			if(1==n && vday.length<1){
				$.messager.alert("提示", "请填写有效期天数", true, "warning");
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
                		   window.location.href="<c:url value='/expdecoratevoucher/index'/>";
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
