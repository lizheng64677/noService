<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<link href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
		<script>
			KindEditor.ready(function(K) {
				var uploadbutton = K.uploadbutton({
					button : K('#uploadButton')[0],
					fieldName : 'imgFile',
					url : '<c:url value="/file/uploadFiles?module=tempFiles"/>',
					afterUpload : function(data) {
						if (data.error === 0) {
							var url = K.formatUrl(data.url, 'absolute');
							K('#url').val(url);
						} else {
							if(data.msgId==1){
								alert("没有文件！");
							}else if(data.msgId==2){
								alert("系统文件路径错误");
							}else if(data.msgId==3){
								
								alert("文件格式错误！");
							}
						}
					},
					afterError : function(str) {
						alert('自定义错误信息: ' + str);
					}
				});
				uploadbutton.fileBox.change(function(e) {
					uploadbutton.submit();
				});
				
				K("#save").bind("click",function(){
					if($("#url").val().length>0){
						$.ajax({
							type : 'POST',
							url : "<c:url value='/falsenouser/addByBatch'/>",
							data : $("#dataForm").serialize(),
							dataType : "json",
							success: function(data) {
			                   if("success"==data.message){
			                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
			                		   window.location.href="<c:url value='/falsenouser/index'/>";
			                       });
		       
			                   }else if("nofiles"==data.message){
			                	   $.messager.alert("操作提示","系统出现故障，上传文件不存在!");
			                   }else if("nodata"==data.message){
			                	   
			                	   $.messager.alert("操作提示","上传文件数据读取异常!");
			                   }else{
			                	   
			                	   $.messager.alert("操作提示","系统故障！请联系相关管理员");
			                	   
			                   }
				            } 
						});
					}else{
						
						alert("请选择文件后再次保存");
					}
					});
			});
			
		</script>
</head>
<body>
 <div class="easyui-panel" title="批量插入"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    <a href="<c:url value='/resources/template/falsenouser.rar'/>">模板下载</a>
		<div class="upload">
			<input class="ke-input-text" type="text" id="url" name="url" value="" readonly="readonly" /> 
			<input type="button" id="uploadButton" value="选择文件" />
			<input type="button" id="save" value="保存" />
		</div>
	    </form>
	    </div>
	</div>
</body>
</html>