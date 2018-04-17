
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../../common_resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
  <link href="<c:url value='/resources/js/My97DatePicker/skin/WdatePicker.css'/>" rel="stylesheet" type="text/css" />
  <script src="<c:url value='/resources/js/My97DatePicker/WdatePicker.js'/>"></script>
  </head>
  <body >   
	<div class="easyui-panel" title="<c:if test='${entity!=null }'>编辑</c:if><c:if test='${entity==null }'>添加</c:if>"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    	<input type="hidden" name="id" value="${entity.id }"/>
	    		<tr>
	    			<td>标题:
	    			</td>
	    				<td><input class="easyui-validatebox" 
	    				type="text" style="width:300px;height:30px;"  
	    			    name="title"   
	    			    value="${entity.title}"
	    			    maxlength="30"
	    			    ></input></td>
	    		</tr>
	    		<tr>
	    			<td>链接地址:
	    			</td>
	    				<td><input class="easyui-validatebox" 
	    				type="text" style="width:300px;height:30px;"  
	    			    name="url"   
	    			    value="${entity.url}"
	    			    maxlength="300"
	    			    ></input></td>
	    		</tr>	    	
	    		<tr>
	    			<td>图片地址:
	    			</td>
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
	    			<td>类型:
	    			</td>
	    				<td>
	    				<select name="type" style="width:300px;height:30px;"  >
	    					<option value="4">主题</option>
	    				</select>
	    				</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>开始时间:
	    			</td>
	    			<td><input class="easyui-validatebox" type="text" style="width:300px;height:30px;"  
	    			 name="begin" id="begin" 
	    			 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   
	    			 value="${entity.beginString}">
	    			 </input>
	    			 </td>
	    		</tr>
	    		
	    		<tr>
	    			<td>结束时间:
	    			</td>
	    			<td><input class="easyui-validatebox" type="text" style="width:300px;height:30px;"  
	    			 name="end" id="end" 
	    			 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})"
	    			 value="${entity.endString}">
	    			 </input>
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
    		<input type="hidden" name="allImages" id="allImages" value=""/>	   	
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
				height:500,
				items:['source', '|', 'undo', 'redo','|', 'cut', 'copy', 'paste','|','formatblock', 'fontname', 'fontsize','|','image'],
			    extraFileUploadParams : {
			    	module :"theme"
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
			
			var begin = $.trim($("#begin").val());
			var end = $.trim($("#end").val());
			
			begin = begin.replace(/-/g,"/");
			var startdate = new Date(begin);
			
			end = end.replace(/-/g,"/");
			var enddate = new Date(end);
			
			if(enddate < startdate)
			{
				alert('结束时间不能小于开始时间');
				return;
			}
			var url="<c:url value='/theme/add?'/>";
			if('${entity.id}'){
				url="<c:url value='/theme/update?'/>";
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
	                		   window.location.href="<c:url value='/theme/index/'/>";
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
