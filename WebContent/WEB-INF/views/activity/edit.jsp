<%@page import="com.suyin.activity.model.*" %>
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
	    	module :"activity"
      }
	});

	editor2=K.create("#content2",{
		uploadJson :urlLoad,
		fileManagerJson :urlManager,
		filePostName:"imgFile",
		allowFileManager : false,
	    extraFileUploadParams : {
	    	module :"activity"
      }
	});
});
	

</script>
  </head>
  <body >   
	<div class="easyui-panel" title="编辑"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>活动标题:	 <input type="hidden" value="${activity.id }" name="id"/></td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写活动标题" name="title" data-options="required:true" value="${activity.title }"></input></td>
	    		</tr>
	    		<tr>
	    		<td>活动时间：</td>
	    		<td>
	    		<input type="text" name="beginTime" placeholder="输入活动开始时间" id="beginTime" value="${activity.beginTime }" class="input" style="width:145px;"/>
	    		<input type="text" name="endTime"   placeholder="输入活动结束时间 "id="endTime" value="${activity.endTime }" class="input" style="width:145px;"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动规则:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content2">${activity.votingRules}</textarea>
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>活动内容:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content1">${activity.content }</textarea>	    		
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
	
			url="<c:url value='/adminactivity/update'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			$("#content").val(editor1.html());
			$("#votingRules").val(editor2.html());
			$.ajax({
				type : 'POST',
				url : url,
				data : $("#dataForm").serialize(),
				dataType : "json",
				success: function(data) {
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/adminactivity/index'/>";
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
			
			 $("#beginTime").bind("click",function(){
				 
				 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'});
			 });
		 	$("#endTime").bind("click",function(){
				 
				 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'});
			 });
		})
	</script>
</body>  
</html>
