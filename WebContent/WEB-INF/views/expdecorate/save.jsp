<%@page import="com.suyin.decorate.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	    	module :"decorate"
      }
	});
	
	editor2=K.create("#content2",{
		uploadJson :urlLoad,
		fileManagerJson :urlManager,
		filePostName:"imgFile",
		allowFileManager : false,
	    extraFileUploadParams : {
	    	module :"decorate"
      }
	});
	
	K('#imgButton').click(function() {
		
		type=$(this).data("type"); //当前上传图片的类型 

		editor1.loadPlugin('image',function() {
			editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
				
					K('#shareImg').val(url);
					K("#headImg").attr("src",url);
					editor1.hideDialog();
				}
			});
		});
	});	
	K('#imgButton2').click(function() {
		
		type=$(this).data("type"); //当前上传图片的类型 
		
		editor2.loadPlugin('image',function() {
			editor2.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
				
					K('#activeImg').val(url);
					K("#headImg2").attr("src",url);
					editor2.hideDialog();
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
	    	<table cellpadding="3">
	    		<tr>
	    			<td>活动名称:</td>
	    			<td><input   class="easyui-validatebox input" type="text" style="width:300px;height:30px;"   missingMessage="请填写活动名称" name="name"  required="required" validType="isExist" ></input></td>
	    		</tr>
		    	<tr>
	    			<td>活动标题:</td>
	    			<td><input   class="easyui-validatebox input" type="text" style="width:300px;height:30px;"   missingMessage="请填写活动标题" name="title"  required="required" validType="isExist"></input></td>
	    		</tr>
	    		<tr>
	    		<td>活动时间：</td>
	    		<td>
	    		<input type="text" name="beginTime" placeholder="输入活动开始时间" id="beginTime"  class="input" style="width:145px;"/>
	    		<input type="text" name="endTime"   placeholder="输入活动结束时间 "id="endTime"  class="input" style="width:145px;"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>金额范围：</td>
	    		<td>
	    		<input   class="easyui-validatebox input" type="text" style="width:140px;height:30px;"   missingMessage="请填写金额" name="beginMoney"  required="required" validType="integer"></input>--
	    		<input   class="easyui-validatebox input" type="text" style="width:140px;height:30px;"   missingMessage="请填写金额" name="endMoney"  required="required" validType="integer" ></input>
	    		</td>
	    		</tr>	
	    		<tr>
	    		<td>封顶金额：</td>
	    		<td>
	    		<input   class="easyui-validatebox input" type="text" style="width:140px;height:30px;"   missingMessage="请填写金额" name="maxMoney"  required="required" validType="integer" ></input>
	    		(*)每人得到佣金的上限
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>签单返现金额：</td>
	    		<td>
	    		<input   class="easyui-validatebox input" type="text" style="width:140px;height:30px;"   missingMessage="请填写金额" name="sginPrice"  required="required" validType="integer" ></input>
	    		(*)签单成功后，返给推荐人的
	    		</td>
	    		</tr>		   		
		   		<tr>
	    		<td>起提金额：</td>
	    		<td>
	    		<input   class="easyui-validatebox input" type="text" style="width:140px;height:30px;"   missingMessage="请填写金额" name="tqMoney"  required="required" validType="integer" ></input>
	    		(*)满足或大于该金额时才可以提现
	    		</td>
	    		</tr>	
	    		
	    		<tr>
	    			<td>分享标题:</td>
	    			<td>
	    		<input   class="easyui-validatebox input" type="text" style="width:300px;height:30px;"   missingMessage="请填写分享标题" name="shareTitle"  required="required" validType="isExist" ></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
			    	<td>分享图片:</td>
			    	<td>	
			    		<input type="hidden" name="shareImg" id="shareImg"> 
			    	 	<img alt="" src="" id="headImg" style="width:70px;height:70px">    			
			    		<input type="button" id="imgButton" class="easyui-linkbutton"  data-type="pro_default" style="width:70px;height:25px;"  value="上传"/>
			    	</td>
			    </tr>
			    <tr>
			    	<td>活动图片:</td>
			    	<td>	
			    		<input type="hidden" name="activeImg" id="activeImg"> 
			    	 	<img alt="" src="" id="headImg" style="width:70px;height:70px">    			
			    		<input type="button" id="imgButton2" class="easyui-linkbutton"  data-type="pro_default" style="width:70px;height:25px;"  value="上传"/>
			    	</td>
			    </tr>	
			    <tr>
	    		<td>商家介绍:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content2"></textarea>
	    		<input type="hidden" id=sellerDescription name="sellerDescription"/>
	    		</td>
	    		</tr>    		
	    		<tr>
	    		<td>活动规则:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content1"></textarea>
	    		<input type="hidden" id=description name="description"/>
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    			<td colspan="2" align="center">
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm()">提交保存</a>
	    			 <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="jumpBack" >返回</a>	
	    			 </td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
	<script>
		function submitForm(){
	
			url="<c:url value='/adminexpdecorate/add'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			$("#description").val(editor1.html());
			$("#sellerDescription").val(editor2.html());
			$.ajax({
				type : 'POST',
				url : url,
				data : $("#dataForm").serialize(),
				dataType : "json",
				success: function(data) {
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   window.location.href="<c:url value='/adminexpdecorate/index'/>";
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
			
			//返回列表
			$("#jumpBack").click(function(){ 
				window.location.href="<c:url value='/adminexpdecorate/index'/>";
			});
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