<%@page import="com.suyin.member.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

 <link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
  <link href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/kindeditor/lang/zh_CN.js?ver=4.1.10 (2013-11-23)'/>"></script>
  <script type="text/javascript">
	var urlLoad="<c:url value='/file/upload'/>";
	var urlManager="<c:url value='/file/fileManager'/>";

  KindEditor.ready(function(K) {
	  
		var editor=K.editor({
			uploadJson :urlLoad,
			fileManagerJson :urlManager,
			filePostName:"imgFile",
			allowFileManager : true,
			extraFileUploadParams : {
		    	module :"store"
          }
		});
		
		K('#imgButton').click(function() {
			editor.loadPlugin('image', function() {
				editor.plugin.imageDialog({
					clickFn : function(url, title, width, height, border, align) {
						K('#storePicUrl').val(url);
						K("#imgurl").attr("src",url);
						editor.hideDialog();
					}
				});
			});
		});
		

	});
  

//更新地图
function updateMap(){
	var a="http://map.baidu.com/mobile/webapp/place/list/qt=s&wd="+	$("#address").val()+
	"&c=315&searchFlag=bigBox&nb_x=13222089.80322&nb_y=3750948.9116394&center_rank=1/vt=map&center_name=%E6%88%91%E7%9A%84%E4%BD%8D%E7%BD%AE&i=0/?itj=45&ssid=0&from=1086k&bd_page_type=1&uid=E658869BE502AB0E0954CDB3F717EC82&pu=sz%401320_1002%2Cta%40iphone_2_4.0_2_4.4";
	document.getElementById("baidumap_iframe").src=a;

	$("#storeAddressUrl").val(a);
}
</script>
</head>
<body>
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
<!-- 		<input type="hidden" id="content"> -->
	    <form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>门店名称:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="填写门店名称" name="storeName" data-options="required:true" value=""></input></td>
	    		</tr>
	    		<tr>
	    			<td>门店电话:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"   missingMessage="填写门店电话" name="storeTel" data-options="required:true" value=""></input></td>
	    		</tr>
	    		<tr>
	    			<td>所属城市:</td>
	    			<td>
	    			<select class="input" style="width:145px;" id="selectCity" name="provinId">
	    				<option value="">---请选择---</option>
	    				<c:forEach items="${cityList }" var="ct">
	    				 <option value="${ct.id}">${ct.name }</option>
	    				</c:forEach>
	    			</select>
	    			
	    			<select name="cityId" class="input" style="width:145px;" id="cityId" >
						<option value="">---请选择--- </option>
	    			</select>
					<td>
	    		</tr>
	    		<!--  <tr>
	    			<td>所属品类:</td>
	    			<td>
	    			<select class="input" name="cgId">
	    				<option value="">---请选择--- </option>
	    				<c:forEach items="${cateList }" var="cat">
	    					<option value="${cat.cgId }">${cat.cgName }</option>	
	    				</c:forEach>
	    			</select>
	    			
					</td>
	    		</tr>-->
	    		<tr>
	    		<td>门店图片:</td>
	    		<td>
    			<img alt="" src="" id="imgurl" style="width:70px;height:70px">    
	    		<input type="hidden" id="storePicUrl"  name="storePicUrl"/>
	    		<input type="button"  value="上传"  id="imgButton"/>
	    		</td>
	    		</tr>
	    		<tr>
	    			<td>详细地址:</td>
	    			<td>
	    			<textarea rows="8" cols="60" id="address" name="storeAddress"></textarea>
	    			<input value="点击获取" id="toMap" type="button" >	    			
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>地址预览:</td>
				<td height="60"  valign="middle">
				<input type="hidden" name="storeAddressUrl"  id="storeAddressUrl"/>
              	<iframe id="baidumap_iframe" src="http://map.baidu.com/mobile/webapp/index/index/qt=s&wd=&c=315&searchFlag=bigBox&nb_x=13222089.80322&nb_y=3750948.9116394&center_rank=1/vt=map&center_name=%E6%88%91%E7%9A%84%E4%BD%8D%E7%BD%AE&i=0" frameborder="0" style="width:560px;height:362px;"> </iframe>
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
	
			url="<c:url value='/store/add'/>";
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
                		   window.location.href="<c:url value='/store/index'/>";
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
		
		//事件绑定 
		$(function(){
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
			 //更新地图 
			$("#toMap").bind("click",function(){
				
				updateMap();
				
			});
			 //提交表单
			$("#submitForm").bind("click",function(){
					
					submitForm();
				});
			
		})
	</script>
</body>
</html>