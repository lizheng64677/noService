<%@page import="com.suyin.expzhuan.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src=" <c:url value="/resources/js/experience/experience.js"></c:url>"> </script>
<script type="text/javascript" src=" <c:url value="/resources/js/My97DatePicker/WdatePicker.js"></c:url>"> </script>
<link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
<script type="text/javascript">
var editor1="";
var editor2="";
var urlLoad="<c:url value='/file/upload'/>";
var urlManager="<c:url value='/file/fileManager'/>";
var type="";
KindEditor.ready(function(K) {
		editor1=K.create("#content",{
			uploadJson :urlLoad,
			fileManagerJson :urlManager,
			filePostName:"imgFile",
			allowFileManager : false,
		    extraFileUploadParams : {
		    	module :"exptask"
          }
		});
		
		K('#imgButton1').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#expImg').val(url);
						K("#expImgUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});
		
		editor2=K.create("#content1");
});
	

</script>
<style type="text/css">
._serarchDiv{
	position: absolute;
	z-index: 110003;
	display: none; 
	left: 30.5%;
	width: 150px;
	top: 91px;
	border:1px solid #95b8e7;
}
.seleDiv{
	font-size:14px;
	color:#000000;
    height: 25px;
    width: 148;

}
</style>
</head>
<body>
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	      	<!-- 图片相关信息 -->
	        <input type="hidden" name="allImages" id="allImages" value=""/>	
	    
	    	<table cellpadding="3">
	    	<tr>
	    			<td>商家名称:</td>
	    			<td>	    
	    			<input type="text"  name="memberName" class="easyui-validatebox input"  placeholder="输入商家名称查询"   missingMessage="请填写活动标题" data-options="required:true"  />	 
	    			</td>
	    		</tr>
	      		<tr>
	    		<td>选择城市:</td>
	    		<td>	    		
	    		<select name="provinId" class="input"  id="selectCity"  style="width:145px;">
		    		<option value="">---请选择---</option>
	    			<c:forEach items="${cityList }" var="ct">
	    				 <option value="${ct.id}">${ct.name }</option>
	    			</c:forEach>
	    		</select>
	    		<select name="cityId" class="input" style="width:145px;" id="cityId">
		    		<option value="">---请选择---</option>
	    		</select>
	    		</td>
	    		</tr>
	    		<tr>
	    			<td>活动标题:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写活动标题" name="title" data-options="required:true" value=""></input></td>
	    		</tr>

	    		<tr>
	    		<td>活动类型:</td>
	    		<td>
	    		
	    		<select name="expType" class="input">
  					<option value="1" selected="selected">全民赚</option>
	    		</select>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动方式:</td>
	    		<td>
	    		
	    		<select name="showType" class="input" id="showType">
	    			<option value="">---请选择---</option>
  					<option value="0">应用下载</option>
  					<option value="1">问卷调查</option>
	    		</select>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动时间：</td>
	    		<td>
	    		<input type="text" name="beginTime" placeholder="输入活动开始时间" id="beginTime"  class="input" style="width:145px;"/>
	    		<input type="text" name="endTime"   placeholder="输入活动结束时间 "id="endTime"  class="input" style="width:145px;"/>
	    		</td>
	    		</tr>

   				<tr>
		    		<td>参与次数:</td>
		    		<td>
		    		<input type="text"  name="userTotal" class="easyui-validatebox input"   placeholder="输入用户参与次数" missingMessage="输入用户参与次数" data-options="required:true" />
		    		</td>
	    		</tr>
		    	<tr>
	    		<td>固定收益:</td>
	    		<td>	
	    			<input type="text"  name="expUserGold" id="expUserGold" class="easyui-validatebox input"   placeholder="输入活动固定收益" missingMessage="输入活动固定收益" data-options="required:true" />
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>是否有标签:</td>
	    		<td>	
	    			<input type="radio" value="1"  name="isLabel" id="isLabel" class="easyui-validatebox radio" onclick="isTrue()" />是
	    			<input type="radio" value="0"  name="isLabel" id="isLabel" class="easyui-validatebox radio" onclick="isFalse()" checked="checked"/>否
	    		</td>
	    		</tr>
	    		<tr id="labelText">
	    		<td>标签内容:</td>
	    		<td>
	    			<input  class="easyui-validatebox input" type="text" name="label"></input>	
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>客户端类型:</td>
	    		
	    		<td>	
	    			<input type="hidden" name="clientType"/>
	    			<input type="checkbox"  name="clientT"  value="0" class="easyui-validatebox checkbox"  missingMessage="客户端类型" data-options="required:true" />微信
	    			<input type="checkbox"  name="clientT"  value="1" class="easyui-validatebox checkbox"  missingMessage="客户端类型" data-options="required:true" />ios
	    			<input type="checkbox"  name="clientT"  value="2" class="easyui-validatebox checkbox"  missingMessage="客户端类型" data-options="required:true" />android
	    		</td>
	    		</tr>
	    		
	    		
		    	<tr>
	    		<td>上传的字段说明:</td>
	    		<td>	
	    			<input type="text"  name="tips" id="tips" maxlength="50" class="easyui-validatebox input"   placeholder="上传的字段说明（APP下载需要）"  />
	    		</td>
	    		</tr>
	 		   	<tr>
	    		<td>是否有下载:</td>
	    		<td>	
	    			<input type="radio" value="1"  name="isDown" id="isDown" class="easyui-validatebox radio" checked="checked" />
	    			是
	    			<input type="radio" value="0"  name="isDown" id="isDown" class="easyui-validatebox radio" />
	    			否
	    		</td>
	    		</tr>
	    		<tr>
	 		   	<tr style="display:none;" id="appurl">
	    		<td>应用地址:</td>
	    		<td>	
	    			<input type="text"  name="expAppUrl" id="expAppUrl" class="input"   placeholder="输入应用地址"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动图片:</td>
	    		<td>
	    			<img alt="" src="" id="expImgUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="expImgUrl" id="expImg"> 
	    			<input type="button" id="imgButton1" class="easyui-linkbutton" data-type="exp"  style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动简介:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content"></textarea>
	    		<input type="hidden" id="info" name="expInfo"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>商家简介:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content1"></textarea>
	    		<input type="hidden" id="info1" name="memberInfo"/>
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
		
			url="<c:url value='/expquanminzhuan/add'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			
			$("#info").val(editor1.html());
			$("#info1").val(editor2.html());
			
			var clientType="";
			$("input[name='clientT']:checked").each(function(){
				clientType+=$(this).val()+",";
			});
			
			$("input[name='clientType']").val(clientType);
			if(clientType==''){
				 $.messager.alert("操作提示","请至少选择一种客户端类型");
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
                		   window.location.href="<c:url value='/expquanminzhuan/index'/>";
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
			//级联操作 
			$("#selectCity").bind("change",function(){
				$("#cityId").empty();
				var html="";
				$.ajax({
					type:"post",
					url:"<c:url value='/city/findForCityByUpid'/>",
					data:{"upid":this.value},
					dataType:"json",
					success:function(data){
						
						$(data.result).each(function(i,val){
							
							html+="<option value="+val.id+">"+val.name+"</option>";
							
						});
						$("#cityId").append(html);
					}
				});
			});
			 $("#beginTime").bind("click",function(){
				 
				 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'});
			 });
		 	$("#endTime").bind("click",function(){
				 
				 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'});
			 });
		 	
		 	$("#showType").bind("change",function(){
		 		
		 	 	if(this.value==0){
		 	 		
		 	 		$("#appurl").show();
		 	 		
		 	 	}else{
		 	 		$("#appurl").hide();
		 	 	}
		 	});
		})
		
		/* function isTrue(){
			//展示input
			$("#labelText").show();			
		}
		function isFalse(){
			//隐藏input
			$("#labelText").hide();
		} */
		
	</script>
</body>
</html>