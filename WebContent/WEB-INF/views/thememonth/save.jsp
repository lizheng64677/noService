<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<c:url value='/resources/js/color/jscolor.js'/>"></script>
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
		    	module :"thememonth"
          }
		});
		K('#imgButton').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 

			editor1.loadPlugin('image',function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#themeLogo').val(url);
						K("#themeLogoUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});
		K('#imgButton1').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#themePic').val(url);
						K("#themePicUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});

		K('#imgButton2').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#bottomPic').val(url);
						K("#bottomPicUrl").attr("src",url);
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
	    	<table cellpadding="3">
	    		<tr>
	    			<td>主题标题:</td>
	    			<td><input    class="easyui-validatebox input" type="text" style="width:300px;height:30px;"  missingMessage="请输入主题标题" name="themeTitle" data-options="required:true" value=""></input></td>
	    		</tr>
	    		 <tr>
	    			<td>主题颜色:</td>
	    			<td><input    class="easyui-validatebox input color" type="text" style="width:300px;height:30px;"  missingMessage="请选择主题颜色" name="color" data-options="required:true" value=""></input></td>
	    		</tr>
	    		<tr>
	    			<td>主题月份:</td>
	    			<td><input    class="easyui-validatebox input" type="text" style="width:300px;height:30px;"  missingMessage="请输入主题月份" name="months" id="month" data-options="required:true" value=""></input></td>
	    		</tr>
	    		<tr>
	    		<td>主题LOGO:</td>
	    		<td>
	    			<img alt="" src="" id="themeLogoUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="themeLogo" id="themeLogo"> 
	    			<input type="button" id="imgButton" class="easyui-linkbutton"  data-type="logo" style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>主题图片:</td>
	    		<td>
	    			<img alt="" src="" id="themePicUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="themePic" id="themePic"> 
	    			<input type="button" id="imgButton1" class="easyui-linkbutton"  data-type="themepic" style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>底部图片:</td>
	    		<td>
	    			<img alt="" src="" id="bottomPicUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="bottomPic" id="bottomPic"> 
	    			<input type="button" id="imgButton2" class="easyui-linkbutton"  data-type="bottom" style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr style="display:none;">
	    		<td></td>
	    		<td> <!-- 预留 -->
	    			<textarea rows="1" cols="1" id="content1"></textarea>
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
	
			url="<c:url value='/thememonth/add'/>";
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
                		   window.location.href="<c:url value='/thememonth/index'/>";
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
			
			
			$("#submitForm").bind("click",function(){
				
				submitForm();
			});
			
			$("#month").bind("click",function(){
				
			 	WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
			});
		})
		
	</script>
</body>
</html>