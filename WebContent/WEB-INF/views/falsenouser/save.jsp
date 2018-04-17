<%@page import="com.suyin.falsedata.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<link href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/kindeditor/lang/zh_CN.js?ver=4.1.10 (2013-11-23)'/>"></script>
</head>
<body>
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>手机号:</td>
	    			<td><input  type="text" class="easyui-validatebox input" style="width:300px;height:30px;"  missingMessage="填写手机号" name="userPhone" data-options="required:true" value=""></input></td>
	    		</tr>	  
	    		<tr>
	    			<td>头像:</td>
	    			<td><img alt="" src="" id="imgurl" style="width:70px;height:70px">    
	    		      <input type="hidden" id="headUrl"  name="headUrl"/>
	    		      <input type="button"  value="上传"  id="imgButton"/>
	    		      </td>
	    		</tr>
	    		<tr>
	    			<td>用户所分享的数量:</td>
	    			<td>
	    			<input  type="text" style="width:300px;height:30px;"  name="texpShareNum"  value=""></input>
	    		   </td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> 
	    			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">提交保存</a>
	    			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="clearForm()">重置</a>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
<script type="text/javascript">
	var urlLoad="<c:url value='/file/upload'/>";
	var urlManager="<c:url value='/file/fileManager'/>";
   KindEditor.ready(function(K) {
		var editor=K.editor({
			uploadJson :urlLoad,
			fileManagerJson :urlManager,
			filePostName:"imgFile",
			allowFileManager : true,
			extraFileUploadParams : {
		    	module :"falsenouser"
          }
		});
		
		K('#imgButton').click(function() {
			editor.loadPlugin('image', function() {
				editor.plugin.imageDialog({
					clickFn : function(url, title, width, height, border, align) {
						K('#headUrl').val(url);
						K("#imgurl").attr("src",url);
						editor.hideDialog();
					}
				});
			});
		});
	});
  </script>
	<script>
		function submitForm(){
	
			url="<c:url value='/falsenouser/add'/>";
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
                		   window.location.href="<c:url value='/falsenouser/index'/>";
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