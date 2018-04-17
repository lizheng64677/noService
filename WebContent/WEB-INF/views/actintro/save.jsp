<%@page import="com.suyin.actinfo.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
</head>
<body>
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>类型:
	    			</td>
	    				<td>
	    			    <select style="width:300px;height:30px;"  name="type" value="${entity.type }">
	    			    	<!-- 0:轻松赚，1：帮我赚，2：抽奖式，3：人气式，4：0元式，5：兑换式 -->
	    			    	<option value="0" <c:if test="${entity.type==0 }"> selected="selected"</c:if>>轻松赚</option>
	    			    	<option value="1" <c:if test="${entity.type==1 }"> selected="selected"</c:if>>帮我赚</option>
	    			    	<option value="2" <c:if test="${entity.type==2 }"> selected="selected"</c:if>>抽奖式</option>
	    			    	<option value="3" <c:if test="${entity.type==3 }"> selected="selected"</c:if>>人气式</option>
	    			    	<option value="4" <c:if test="${entity.type==4 }"> selected="selected"</c:if>>0元式</option>
	    			    	<option value="5" <c:if test="${entity.type==5 }"> selected="selected"</c:if>>兑换式</option>
	    			    </select>
	    			    </td>
	    		</tr>
              <tr>
	    			<td>内容:
	    			</td>
	    				<td><textarea rows="25" cols="120" id="content1" >${entity.content}</textarea>
	    				<input type="hidden" id="content" name="content" value='${entity.content}'/>
	    				</td>
	    				
	    		</tr>
    		<tr>
    			<td colspan="2" align="center"> 
    			<a href="javascript:void(0)" class="easyui-linkbutton" 
    			 style="width:100px;height:30px;" onclick="submitForm()">提交</a>
    			</td>
    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	
	  <script>
  	  var editor="";
  	var urlLoad="<c:url value='/file/upload'/>";
  	var urlManager="<c:url value='/file/fileManager'/>";
	  KindEditor.ready(function(K) {
			editor = K.create('#content1',{
				uploadJson : '<c:url value="/file/upload"/>',
				fileManagerJson : '<c:url value="/file/fileManager"/>',
				filePostName:"imgFile",
				allowFileManager : false,
				height:500,
			    extraFileUploadParams : {
			    	module :"about"
	          }
			});
			K('#imgButton').click(function() {
				editor.loadPlugin('image', function() {
					editor.plugin.imageDialog({
						clickFn : function(url, title, width, height, border, align) {
							K('#image').val(url);
							K("#img").attr("src",url);
							editor.hideDialog();
						}
					});
				});
			});
		});
	  
  </script>
	<script>
		function submitForm(){
	
			url="<c:url value='/actintro/add'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			$("#content").val(editor.html());
			$.ajax({
				type : 'POST',
				url : url,
				data : $("#dataForm").serialize(),
				dataType : "json",
				success: function(data) {
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/actintro/index'/>";
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