
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
  </head>
  <body >   
	<div class="easyui-panel" title="<c:if test='${entity!=null }'>编辑</c:if><c:if test='${entity==null }'>添加</c:if>"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    	<input type="hidden" name="companyId" value="${entity.companyId }"/>
	    	<input type="hidden" name="allImages" id="allImages" value=""/>	  	
	    		<tr>
	    			<td>图片地址:
	    			</td>
    				<td style="position: relative; height:64px;">
    				<img id="img" style="position: absolute; top:2px; left:2px; z-index: 100;" width="120" height="60" src="${entity.image }"/>
    				<input style="position: absolute; top:0px; left:0px; width:120px;
    				 height:60px; z-index: -1;" id="image" name="image" value="${entity.image}" 
    				 class="easyui-validatebox" 
    				 data-options="required:true" 
    				 missingMessage="请选择一个图片"
    				 ></input>
    				<input type="button" id="imgButton" class="easyui-linkbutton"   style="width:70px;height:25px;float:right;"  value="上传"/>
	    			    			    
    			  	</td>
	    		</tr>
	    		<tr>
	    			<td>商家标题:
	    			</td>
	    				<td><input class="easyui-validatebox" 
	    				type="text" style="width:300px;height:30px;"  
	    			    name="title"   
	    			    value="${entity.title}"
	    			    maxlength="200"
	    			    ></input></td>
	    		</tr>
	    		<tr>
	    			<td>链接地址:
	    			</td>
	    				<td><input class="easyui-validatebox" 
	    				type="text" style="width:300px;height:30px;"  
	    			    name="url"   
	    			    value="${entity.url}"
	    			    maxlength="500"
	    			    ></input></td>
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
  <script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
  <script src="<c:url value='/resources/js/kindeditor/lang/zh_CN.js'/>"></script>
  <script>
  	  var editor;
	  KindEditor.ready(function(K) {
			editor = K.editor({
				uploadJson : '<c:url value="/file/upload"/>',
				fileManagerJson : '<c:url value="/file/fileManager"/>',
				filePostName:"imgFile",
				allowFileManager : false,
			    extraFileUploadParams : {
			    	module :"company"
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
			var url="<c:url value='/company/add?'/>";
			if('${entity.companyId}'){
				url="<c:url value='/company/update?'/>";
			}
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
	                   if(data.result>0){
	                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
	                		   window.location.href="<c:url value='/company/index/'/>";
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
