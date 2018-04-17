<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/js/kindeditor/plugins/code/prettify.css'/>" />
<script src="/noService/resources/js/kindeditor/kindeditor-min.js"></script>
<script>
  	  var editor;
	  KindEditor.ready(function(K) {
			editor = K.create('textarea[name="description"]',{
				uploadJson : SITE_BASE_PATH+'file/upload',
				fileManagerJson :SITE_BASE_PATH+'file/fileManager',
				filePostName:"imgFile",
				allowFileManager : false,
				extraFileUploadParams : {
		    	module :"reply"
		    	},
				height:100,
				width:400,
				items:['source', '|', 'undo', 'redo','|', 'cut', 'copy', 'paste','|','formatblock', 'fontname', 'fontsize'],
				afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
				});
			},
			afterBlur:function(){ 
	            this.sync(); 
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
</head>
<body>
	<div class="easyui-panel" title="图文回复消息设置"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    <input type="hidden" name="reply_is_use" id="reply_is_use"  value="1"/>
	    	<table cellpadding="3">
	    		<tr>
	    			<td>类型:</td>
	    			<td>
	    				<select id="messageType"  name="messageType" style="width:300px;height:30px;">
	    					<option <c:if test="${reply.messageType==1 }">selected='selected'</c:if> value="1" >图文回复</option>
		    				<option <c:if test="${reply.messageType==0 }">selected='selected'</c:if> value="0" >文本回复</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td  title="多图文回复请设置相同关键字" class="easyui-tooltip">关键字:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text"  style="width:300px;height:30px;" name="keywords" value="${reply.keywords}"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>标题:</td>
	    			<td>
	    				<input type="hidden" name="messageId" value="${reply.messageId }"/>
	    				<input class="easyui-textbox" type="text"  style="width:300px;height:70px;" name="title" value="${reply.title}"></input>
	    			</td>
	    		</tr>
	    		<tr class="messageImage">
	    			<td>点击图文跳转链接:</td>
	    			<td><input id="url" style="width:300px;height:30px;" name="url" value="${reply.url}"></input></td>
	    		</tr>
	    		<tr class="messageImage">
	    			<td>图片地址:</td>
	    			<td>
	    				<img id="img"  width="120" height="60" src="${reply.pic_url }"/>
	    				<input type="hidden" id="pic_url" name="pic_url" value="${reply.pic_url}">
	    				<input type="button" id="imgButton" class="easyui-linkbutton"   style="width:70px;height:25px;float:right;"  value="上传"/>
	    			</td>
	    		</tr>
	    		<tr class="messageImage">
	    			<td>描述:</td>
	    			<td>
	    				<textarea id="descrip" name="description">${reply.description}</textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> 
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="subDataForm" >提交</a>
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="jumpBack" >返回</a>								
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>   
</body>
<script type="text/javascript">
window.onload=function(){
	if(0==$("#messageType").val()){
		$(".messageImage").hide();
	}else if(1==$("#messageType").val()){
		$(".messageImage").show();
	}
}
$(function(){
	$("#messageType").change(function(){ 
			if(0==$("#messageType").val()){
				$(".messageImage").hide();
			}else if(1==$("#messageType").val()){
				$(".messageImage").show();
			}
	});
});
	//修改方法
	$("#subDataForm").click(function(){ 
		var isValid = $("#dataForm").form('validate');
		if(!isValid){
			return false;
		}
		var messageType = $("#messageType").val();
		var description = editor.html();
		if(messageType==0){
			$("#url").val('');
			$("#pic_url").val('');
			$("#img").attr("src","");
			$("#descrip").val('');
		}
		$("#pic_url").val($("#img").attr("src"));
		var url="<c:url value='/weChatReply/updateWeChatReply'/>";
		$.ajax({
			type : 'POST',
			url : url,
			data : $("#dataForm").serialize(),
			dataType : "json",
			success: function(data) {
                   if(1==data.message){
                	   $.messager.alert("提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/weChatReply'/>";
                       });
                   }else{
                	   $.messager.alert("提示","提交失败!", "warning");
                   }
            }
		});
	});
	//返回列表
	$("#jumpBack").click(function(){ 
		window.location.href="<c:url value='/weChatReply'/>";
	});
</script>
</html>