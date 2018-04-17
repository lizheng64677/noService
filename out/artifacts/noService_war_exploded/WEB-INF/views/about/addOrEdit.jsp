<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
  </head>
  <body >   
	<div class="easyui-panel" title="<c:if test='${entity!=null }'>编辑</c:if><c:if test='${entity==null }'>添加</c:if>"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    	<c:if test='${entity!=null }'><input type="hidden" name="aboutId" value="${entity.aboutId }" /></c:if>
	    	<c:if test='${entity==null }'><input type="hidden" name="aboutId" value="-1" /></c:if>
	    
	    		<tr>
	    			<td>类型:
	    			</td>
	    				<td>
	    			    <select style="width:300px;height:30px;"  name="type" value="${entity.type }">
	    			    	<option value="1"
	    			    	<c:if test="${entity.type==1 }"> selected="selected"</c:if>
							>用户协议</option>
	    			    	<option value="0"
	    			    	<c:if test="${entity.type==0 }"> selected="selected"</c:if>
	    			    	>平台简介</option>
	    			    		<option value="2"
	    			    	<c:if test="${entity.type==2 }"> selected="selected"</c:if>
	    			    	>新手指南</option>
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
    			<a href="javascript:void(0)" class="easyui-linkbutton" 
    			 style="width:100px;height:30px;" onclick="clearForm()">清空</a>
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
			var url="<c:url value='/about/addAbout?'/>";
			if('${entity.aboutId}'){
				url="<c:url value='/about/updateAbout?'/>";
			}
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
	                   if(data.result>0){
	                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
	                		   window.location.href="<c:url value='/about/toindex/'/>";
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
