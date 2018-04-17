<%@page import="com.suyin.participator.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
  <head>
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
	    	module :"participator"
      }
	});

	editor2=K.create("#content2",{
		uploadJson :urlLoad,
		fileManagerJson :urlManager,
		filePostName:"imgFile",
		allowFileManager : false,
	    extraFileUploadParams : {
	    	module :"participator"
      }
	});
	
	K('#imgButton').click(function() {
		
		type=$(this).data("type"); //当前上传图片的类型 

		editor1.loadPlugin('image',function() {
			editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
				
					K('#headImgUrl').val(url);
					K("#headImg").attr("src",url);
					editor1.hideDialog();
				}
			});
		});
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
	    			<td>姓名:
		    			<input type="hidden" name="id" value="${participator.id }"/>
	    			</td>
	    			<td><input   class="easyui-validatebox" type="text" style="width:300px;height:30px;"   missingMessage="填写姓名" name="name"  required="required" validType="isExist" value="${participator.name }"></input></td>
	    		</tr>
		    	<tr>
		    		<td>年龄:</td>
		   			<td><input   class="easyui-validatebox" type="text" style="width:300px;height:30px;"  missingMessage="必须为整数" name="age" data-options="validType:'number'" value="${participator.age }"> </input></td>
		   		</tr>
	    		<tr>
	    			<td>编号:</td>
	    			<td><input   class="easyui-validatebox" type="text" style="width:300px;height:30px;"   missingMessage="填写编号" name="number"  required="required" validType="isExist" value="${participator.number }"></input></td>
	    		</tr>		   		
		   	
	    		<tr>
	    			<td>类型:</td>
	    			<td>
	    				<select class="easyui-combobox" data-options="required:true" name="type" style="width:300px;height:30px;">
		    				<option value="0" <c:if test="${participator.type==0 }">selected="selected"</c:if>>强警标兵</option>
		    				<option value="1" <c:if test="${participator.type==1 }">selected="selected"</c:if>>最美警嫂</option>
	    				</select>
	    			</td>
	    		</tr>
	    		
	    		<tr>
			    	<td>头像:</td>
			    	<td>	
			    		<input type="hidden" name="headImgUrl" id="headImgUrl" value="${participator.headImgUrl }"> 
			    	 	<img alt="" src="${participator.headImgUrl}" id="headImg" style="width:70px;height:70px">    			
			    		<input type="button" id="imgButton" class="easyui-linkbutton"  data-type="pro_default" style="width:70px;height:25px;"  value="上传"/>
			    	</td>
			    </tr>
			    
	    		<tr>
	    		<td>个人简介 :</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content1">${participator.introduce}</textarea>
	    		<input type="hidden" id="introduce" name="introduce"></input>
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>活动宣言:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content2">${participator.activityDeclaration }</textarea>
	    		<input type="hidden" id="activityDeclaration"  name="activityDeclaration"/>
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
	
			url="<c:url value='/adminparticipator/update'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			$("#introduce").val(editor1.html());
			$("#activityDeclaration").val(editor2.html());
			$.ajax({
				type : 'POST',
				url : url,
				data : $("#dataForm").serialize(),
				dataType : "json",
				success: function(data) {
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/adminparticipator/index'/>";
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
