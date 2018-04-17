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
	var day =$("#installments").val(i);
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
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<!-- 图片相关信息 -->
	    	<input type="hidden" name="allImages" id="allImages" value=""/>	
	    	<table cellpadding="3">
	    		<input type="hidden" name="expType" value="1"/> <!--人气式 -->
	    		<tr>
	    			<td>活动标题:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写活动标题" name="title" data-options="required:true" value=""></input></td>
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
	    		<td>活动类型:</td>
	    		<td>
	    		
	    		<select name="type" class="input">
  					<option value="5" selected="selected">人气式</option>
	    		</select>
	    		</td>
	    		</tr>

	    		<tr>
	    		<td>活动时间：</td>
	    		<td>
	    		<input type="text" name="beginTime" onblur="count()" placeholder="输入活动开始时间" id="beginTime"  class="input" style="width:145px;"/>
	    		<input type="text" name="endTime"  onblur="count()"  placeholder="输入活动结束时间 "id="endTime"  class="input" style="width:145px;"/>
	    		</td>
	    		</tr>
	    		<tr>
		    		<td>单位发券(天数):</td>
		    		<td>
		    		<input type="text"  onblur="count()"  name="unitDay" id="unitDay" class="easyui-validatebox input"  missingMessage="请填写单位天数" data-options="required:true" /><span style="color:red;">*整数</span>
		    		</td>
	    		</tr>
	    		<tr>
		    		<td>期数:</td>
		    		<td>
		    		<input type="text"   name="installments" id="installments" readonly="readonly" class="easyui-validatebox input"  missingMessage="活动期数" data-options="required:true" /><span style="color:red;"></span>
		    		</td>
	    		</tr>
	    		
	    		<tr>
	    		<td>是否有标签:</td>
	    		<td>	
	    			<input type="radio" value="1"  name="isLabel" id="isLabel" class="easyui-validatebox radio" onclick="isTrue()" />是
	    			<input type="radio" value="0"  name="isLabel" id="isLabel" class="easyui-validatebox radio" onclick="isFalse()" checked="checked"/>否
	    		</td>
	    		</tr>
	    		<tr  id="labelText">
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
	    		<input type="hidden" id="info" name="info"/>
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
		
			url="<c:url value='/exprenqi/add'/>";
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
                		   window.location.href="<c:url value='/exprenqi/index'/>";
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
		})
		
		/* function isTrue(){
			//展示input
			$("#labelText").show();			
		}
		function isFalse(){
			//隐藏input
			$("#labelText").hide();
		}
		 */
		
	</script>
</body>
</html>