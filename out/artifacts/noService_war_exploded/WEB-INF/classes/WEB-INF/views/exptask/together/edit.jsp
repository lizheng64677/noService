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
		K('#imgButton').click(function() {
		
			type=$(this).data("type"); //当前上传图片的类型 

			editor1.loadPlugin('image',function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#shareImg').val(url);
						K("#shareImgUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
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
	    	<input type="hidden" name="expId" value="${expzhuan.expId }"/>
	    	<table cellpadding="3">
	    	<tr>
	    			<td>商家名称:</td>
	    			<td>
	    			    <input type="text"  value="${expzhuan.memberName }" name="memberName" class="easyui-validatebox input"  placeholder="输入商家名称查询"   missingMessage="请填写活动标题" data-options="required:true"  />
	    			</td>
	    		</tr>
	      		<tr>
	    		<td>选择城市:</td>
	    		<td>	    		
	    		<select name="provinId" class="input"  id="selectCity"  style="width:145px;">
		    		<option value="">---请选择---</option>
	    			<c:forEach items="${cityList }" var="ct">
	    				 <option value="${ct.id}" <c:if test="${ct.id==expzhuan.provinId}">selected="selected"</c:if>>${ct.name }</option>
	    			</c:forEach>
	    		</select>
	    		<select name="cityId" class="input" style="width:145px;" id="cityId">
		    		<option value="">---请选择---</option>
	    		</select>
	    		<input type="hidden" id="cid" value="${expzhuan.cityId }"/>
	    		</td>
	    		</tr>
	    		<tr>
	    			<td>活动标题:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写活动标题" name="title" data-options="required:true" value="${expzhuan.title }"></input></td>
	    		</tr>

	    		<tr>
	    		<td>活动类型:</td>
	    		<td>
	    		
	    		<select name="expType" class="input">
  					<option value="0" selected="selected">齐心赚</option>
	    		</select>
	    		</td>
	    		</tr>

	    		<tr>
	    		<td>活动时间：</td>
	    		<td>
	    		<input type="text" name="beginTime" placeholder="输入活动开始时间" id="beginTime"  class="input" style="width:145px;" value="${expzhuan.beginTime }"/>
	    		<input type="text" name="endTime"   placeholder="输入活动结束时间 "id="endTime"  class="input" style="width:145px;" value="${expzhuan.endTime }"/>
	    		</td>
	    		</tr>
	    		 <tr>
		    		<td>活动总资产:</td>
		    		<td>
		    		<input type="text"  name="expCountGold" value="${expzhuan.expCountGold }" id="expCountGold" class="easyui-validatebox input"   placeholder="输入总金币（整数）" missingMessage="请填写总资产金币" data-options="required:true" />
		    		</td>
	    		</tr>
			 <tr>
		    		<td>活动剩余资产:</td>
		    		<td>
		    		<input type="text"  name="expRemainGold" id="expRemainGold" value="${expzhuan.expRemainGold }" class="easyui-validatebox input"   placeholder="输入剩余金币（整数）" missingMessage="请填写总资产金币" data-options="required:true" />
		    		</td>
	    		</tr>
	    		<tr>
		    		<td>浏览/次:</td>
		    		<td>
		    		<input type="text"  name="expGoldMin" value="${expzhuan.expGoldMin }" class="easyui-validatebox input"   placeholder="输入整数" missingMessage="填写浏览一次所得金币数" data-options="required:true" style="width:145px;" />
		    		<input type="text"  name="expGoldMax" value="${expzhuan.expGoldMax }" class="easyui-validatebox input"   placeholder="输入整数" missingMessage="填写浏览一次所得金币数" data-options="required:true" style="width:145px;" />
		    		<span style="font-size:12px;color:red;">0-100之间整数随机</span>
		    		</td>
	    		</tr>
	    		<tr>
		    		<td>个人金币上限:</td>
		    		<td>
		    		<input type="text"  name="userMaxGold" value="${expzhuan.userMaxGold }" class="easyui-validatebox input"   placeholder="个人所得金币上限" missingMessage="填写个人所得金币上限" data-options="required:true" />
		    		</td>
	    		</tr>
	    		
	    		
	    		<tr>	    		
	    		<td>是否有标签:</td>
	    		<td>
	    		<input id="isLabel" type="hidden" value="${expzhuan.isLabel}"/>
	    			<c:if test="${expzhuan.isLabel == 1}">
	    				<input type="radio" value="1"  name="isLabel" class="easyui-validatebox radio" onclick="isTrue()" checked="checked" />
	    			</c:if>
	    			<c:if test="${expzhuan.isLabel != 1}">
	    				<input type="radio" value="1"  name="isLabel"  class="easyui-validatebox radio" onclick="isTrue()"/>
	    			</c:if>		
	    			是
	    			<c:if test="${expzhuan.isLabel == 0}">
	    				<input type="radio" value="0"  name="isLabel"  class="easyui-validatebox radio" onclick="isFalse()" checked="checked"/>
	    			</c:if>
	    			<c:if test="${expzhuan.isLabel != 0}">
	    				<input type="radio" value="0"  name="isLabel"  class="easyui-validatebox radio" onclick="isFalse()"/>
	    			</c:if>
	    			否
	    		</td>
	    		</tr>
	    		<tr id="labelText">
	    		<td>标签内容:</td>
	    		<td>
	    			<input  class="easyui-validatebox input" type="text" name="label" value="${expzhuan.label }"></input>	
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>客户端类型:</td>
	    		
	    		<td>	
	    			<input type="hidden" name="clientType"/>
	    			<c:if test="${fn:contains(expzhuan.clientType, 0)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" checked="checked" value="0">
        			</c:if>
					<c:if test="${!fn:contains(expzhuan.clientType, 0)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" value="0">
        			</c:if>
        			微信
        			<c:if test="${fn:contains(expzhuan.clientType, 1)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" checked="checked" value="1">
        			</c:if>
					<c:if test="${!fn:contains(expzhuan.clientType, 1)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" value="1">
        			</c:if>
        			ios
        			<c:if test="${fn:contains(expzhuan.clientType, 2)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" checked="checked" value="2">
        			</c:if>
					<c:if test="${!fn:contains(expzhuan.clientType, 2)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" value="2">
        			</c:if>
        			android
        			
	    		</td>
	    		</tr>
	    		
	    		
	    		
	    		
	    		<tr>
	    		<td>分享标题:</td>
	    		<td>
	    		<input type="text"  name="shareTitle" id="shareTitle" class="easyui-validatebox input"   placeholder="输入分享标题" missingMessage="输入分享标题" data-options="required:true"  value="${expzhuan.shareTitle }"/>
	    		</td>
	    		</tr>
		    	<tr>
	    		<td>分享所得金币:</td>
	    		<td>	
	    			<input type="text"  name="userShareGold" id="userShareGold" class="easyui-validatebox input"   placeholder="输入分享所得金币" missingMessage="请填分享所得金币" data-options="required:true" value="${expzhuan.userShareGold }" />
	    		</td>
	    		</tr>
	    		
	 		   	<tr>
	    		<td>分享地址:</td>
	    		<td>	
	    			<input type="text"  name="shareUrl" id="shareUrl" class="easyui-validatebox input"   placeholder="输入分享地址" missingMessage="请填分享地址" data-options="required:true"  value="${expzhuan.shareUrl }"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>分享图片:</td>
	    		<td>
	    			<img alt="" src="${expzhuan.shareImgUrl }" id="shareImgUrl" style="width:70px;height:70px">	    			
					<input type="hidden" name="shareImgUrl" id="shareImg" value="${expzhuan.shareImgUrl }"> 
	    			<input type="button" id="imgButton" class="easyui-linkbutton"  data-type="share"   style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动图片:</td>
	    		<td>
	    			
	    			<img alt="" src="${expzhuan.expImgUrl }" id="expImgUrl" style="width:70px;height:70px">		  			
					<input type="hidden" name="expImgUrl" id="expImg" value="${expzhuan.expImgUrl }"> 
	    			<input type="button" id="imgButton1" class="easyui-linkbutton" data-type="exp"   style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动简介:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content">${expzhuan.expInfo }</textarea>
	    		<input type="hidden" id="info" name="expInfo" value='${expzhuan.expInfo }'/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>商家简介:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content1">${expzhuan.memberInfo }</textarea>
	    		<input type="hidden" id="info1" name="memberInfo" value='${expzhuan.memberInfo }'/>
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
		
			url="<c:url value='/expqixinzhuan/update'/>";
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
                		   window.location.href="<c:url value='/expqixinzhuan/index'/>";
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
		
		function seleList(id){

			var html="";
			$.ajax({
				type:"post",
				url:"<c:url value='/city/findForCityByUpid'/>",
				data:{"upid":id},
				dataType:"json",
				success:function(data){

					var id=$("#cid").val();
					$(data.result).each(function(i,val){
						if(id==val.id){
							
							html+="<option value="+val.id+" selected='selected'>"+val.name+"</option>";
						}else{
							
							html+="<option value="+val.id+">"+val.name+"</option>";
						}
				
						
					});
					$("#cityId").append(html);
				}
			});
		}
		$(function(){
			/* var islabel=$("#isLabel").val();
			if(islabel==1){
				$("#labelText").show();	
			} */
			
			
			//提交
			$("#submitForm").bind("click",function(){
				
				submitForm();
			});
			 //级联操作
			 seleList($("#selectCity").val());
			$("#selectCity").bind("change",function(){
				$("#cityId").empty();
				seleList(this.value);
			});
			 $("#beginTime").bind("click",function(){
				 
				 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});
			 });
		 	$("#endTime").bind("click",function(){
				 
				 WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});
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