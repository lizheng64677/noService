<%@page import="com.suyin.experience.model.*" %>
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
		    	module :"experience"
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
});
	
function count(){
	var beginTime =$("#beginTime").val().split(" ")[0];
	var endTime =$("#endTime").val().split(" ")[0];
	var day =$("#unitDay").val();
	var date=DateDiff(beginTime,endTime);
	var i=(date+1)/day;
    var reg = /.*\..*/;
    if(reg.test(i)){
    	 $.messager.alert("操作提示","期数不能为小数哦,修改单位发卷(天数)吧!");
    	 return;
    };
	if(date=="Infinity"||date==null||beginTime==null||beginTime==""||day==null|| day=="" ||endTime==null||endTime==""){
		return;
	}
	$("#installments").val(i);
}

 function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式 
       var  aDate,  oDate1,  oDate2,  iDays 
       aDate  =  sDate1.split("-") 
       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]) //转换为12-18-2006格式 
       aDate  =  sDate2.split("-") 
       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]) 
       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24) //把相差的毫秒数转换为天数 
       return  iDays 
   }   
</script>

</head>
<body>
	<div class="easyui-panel" title="编辑"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	 <!-- 图片相关信息 -->
	    	<input type="hidden" name="allImages" id="allImages" value=""/>	
	    	<input type="hidden" name="expType" value="${experience.expType }"/> <!--抽奖式 -->
	    	<table cellpadding="3">
	    		<tr>
	    			<td>活动标题:</td>
	    			<td><input  class="easyui-validatebox input"   type="text"  missingMessage="请填写活动标题" name="title" data-options="required:true" value="${experience.title}"></input></td>
	    		</tr>
	    		<tr>
	    		<td>选择城市:</td>
	    		<td>	    		
	    		<select  class="input" style="width:145px;" id="selectCity" name="provinId">>
		    		<option value="">---请选择---</option>
	    			<c:forEach items="${cityList }" var="ct">
	    				 <option value="${ct.id}" <c:if test="${ct.id == experience.provinId}">selected="selected"</c:if>>${ct.name }</option> 
	    			</c:forEach>
	    		</select>
	    		<select name="cityId" class="input" style="width:145px;" id="cityId">
		    		<option value="">---请选择---</option>
	    		</select>
	    		 <input type="hidden" value="${experience.cityId }" id="cid"/>
	    		 <input type="hidden" value="${experience.memberId }" name="memberId"/>
	    		 <input type="hidden" value="${experience.expId }" name="expId"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动类型:</td>
	    		<td>
	    		
	    		<select name="type" class="input">
  					<option value="7" selected="selected">抽奖式</option>
	    		</select>
	    		</td>
	    		</tr>
	    		<tr>
		    		<td>抽奖方式:</td>
		    		<td>
		    		<select name="showType" class="input">
			    		<option value="">---请选择---</option>
			    		<option value="0" <c:if test="${experience.showType == 0}">selected="selected"</c:if>>红包方式</option>
			    		<option value="1" <c:if test="${experience.showType == 1}">selected="selected"</c:if>>拼图方式</option>
			    		<option value="2" <c:if test="${experience.showType == 2}">selected="selected"</c:if>>刮刮卡方式</option>
			    		<option value="3" <c:if test="${experience.showType == 3}">selected="selected"</c:if>>抽签方式</option>
		    		</select>
		    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动时间：</td>
	    		<td>
	    		<input type="text" name="beginTime" onblur="count()" placeholder="输入活动开始时间" id="beginTime" value="${experience.beginTime }"  class="input" style="width:145px;"/>
	    		<input type="text" name="endTime"   onblur="count()" placeholder="输入活动结束时间" id="endTime" value="${experience.endTime }"   class="input" style="width:145px;"/>
	    		</td>
	    		</tr>
	    		<tr>
		    		<td>单位发券(天数):</td>
		    		<td>
		    		<input type="text"  name="unitDay"  onblur="count()" id="unitDay" value="${experience.unitDay }" class="easyui-validatebox input"  missingMessage="请填写单位天数" data-options="required:true" /><span style="color:red;">*整数</span>
		    		</td>
	    		</tr>
	    		<tr>
		    		<td>期数:</td>
		    		<td>
		    		<input type="text"   name="installments" id="installments" value="${experience.installments }" readonly="readonly" class="easyui-validatebox input"  missingMessage="活动期数" data-options="required:true" /><span style="color:red;"></span>
		    		</td>
	    		</tr>
	    		<tr>
		    		<td>中奖概率:</td>
		    		<td>
		    		<input type="text"  name="probability" value="${experience.probability }" class="easyui-validatebox input" missingMessage="请填写中奖概率" data-options="required:true" />
		    		</td>
	    		</tr>
	    		
	    		<tr>	    		
	    		<td>是否有标签:</td>
	    		<td>
	    		<input id="isLabel" type="hidden" value="${experience.isLabel}"/>
	    			<c:if test="${experience.isLabel == 1}">
	    				<input type="radio" value="1"  name="isLabel" class="easyui-validatebox radio" onclick="isTrue()" checked="checked" />
	    			</c:if>
	    			<c:if test="${experience.isLabel != 1}">
	    				<input type="radio" value="1"  name="isLabel"  class="easyui-validatebox radio" onclick="isTrue()"/>
	    			</c:if>		
	    			是
	    			<c:if test="${experience.isLabel == 0}">
	    				<input type="radio" value="0"  name="isLabel"  class="easyui-validatebox radio" onclick="isFalse()" checked="checked"/>
	    			</c:if>
	    			<c:if test="${experience.isLabel != 0}">
	    				<input type="radio" value="0"  name="isLabel"  class="easyui-validatebox radio" onclick="isFalse()"/>
	    			</c:if>
	    			否
	    		</td>
	    		</tr>
	    		<tr  id="labelText">
	    		<td>标签内容:</td>
	    		<td>
	    			<input  class="easyui-validatebox input" type="text" name="label" value="${experience.label }"></input>	
	    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>客户端类型:</td>
	    		
	    		<td>	
	    			<input type="hidden" name="clientType"/>
	    			<c:if test="${fn:contains(experience.clientType, 0)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" checked="checked" value="0">
        			</c:if>
					<c:if test="${!fn:contains(experience.clientType, 0)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" value="0">
        			</c:if>
        			微信
        			<c:if test="${fn:contains(experience.clientType, 1)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" checked="checked" value="1">
        			</c:if>
					<c:if test="${!fn:contains(experience.clientType, 1)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" value="1">
        			</c:if>
        			ios
        			<c:if test="${fn:contains(experience.clientType, 2)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" checked="checked" value="2">
        			</c:if>
					<c:if test="${!fn:contains(experience.clientType, 2)}">
        				<input name="clientT" type="checkbox" class="easyui-validatebox checkbox" value="2">
        			</c:if>
        			android
        			
	    		</td>
	    		</tr>
	    		
	    		
	    		
	    		<tr>
	    		<td>活动图片:</td>
	    		<td>
	    			<img alt="" src="${experience.expImgUrl }" id="expImgUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="expImgUrl" id="expImg" value="${experience.expImgUrl }"> 
	    			<input type="button" id="imgButton1" class="easyui-linkbutton" data-type="exp"  style="width:70px;height:25px;"  value="上传"/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>活动简介:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content">${experience.info }</textarea>
	    		<input type="hidden" id="info" name="info" value='${experience.info }'/>
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
	
			url="<c:url value='/experience/update'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			
			if(!validTime()){
	         	   $.messager.alert("操作提示","单位时间与抢购时间不匹配！");
	         	   return false;
				}
			$("#info").val(editor1.html());
			
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
                		   window.location.href="<c:url value='/experience/index'/>";
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
			
			 //级联操作
			 seleList($("#selectCity").val());
			 $("#selectCity").bind("change",function(){
				
				$("#cityId").empty();
				seleList(this.value);
			 });
			 //数据提交 
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