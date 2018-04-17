<%@page import="com.suyin.expzhuan.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src=" <c:url value="/resources/js/experience/experience.js"></c:url>"> </script>
<script type="text/javascript" src=" <c:url value="/resources/js/My97DatePicker/WdatePicker.js"></c:url>"> </script>
<link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
<script type="text/javascript">
var editor1="";
var editor2="";
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
		    	module :"exptask"
          }
		});
		
		K('#imgButton1').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#downImg').val(url);
						K("#dowImgUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});
	
		K('#imgButton2').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#upImg').val(url);
						K("#upImgUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});
});
	

</script>
</head>
<body>
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	      	<!-- 图片相关信息 -->
	        <input type="hidden" name="allImages" id="allImages" value=""/>	
	    	<input type="hidden" name="expId" value="${exper.expId }"/>
	    	<table cellpadding="3">
	    		<tr>
	    		<td>下载流程图:</td>
	    		<td>
	    			<img alt="" src="${exper.dowImgUrl}" id="dowImgUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="dowImgUrl" id="downImg" value="${exper.dowImgUrl}"> 
	    			<input type="button" id="imgButton1" class="easyui-linkbutton" data-type="exp"  style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>上传流程图:</td>
	       		<td>
	    			<img alt="" src="${exper.upImgUrl }" id="upImgUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="upImgUrl" id="upImg" value="${exper.upImgUrl }"> 
	    			<input type="button" id="imgButton2" class="easyui-linkbutton" data-type="exp"  style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>应用介绍:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content1">${exper.appInfo }</textarea>
	    		<input type="hidden" id="info" name="appInfo" value='${exper.appInfo }'/>
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
		
			url="<c:url value='/expquanminzhuan/updateExpAppImagesInfo'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			
			$("#info").val(editor1.html());

			$.ajax({
				type : 'POST',
				url : url,
				data : $("#dataForm").serialize(),
				dataType : "json",
				success: function(data) {
                   if("success"==data.message){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/expquanminzhuan/index'/>";
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
			
			//提交
			$("#submitForm").bind("click",function(){
				
				submitForm();
			});
		})
	</script>
</body>
</html>