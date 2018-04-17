<%@page import="com.suyin.member.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
  <script type="text/javascript">
	var urlLoad="<c:url value='/file/upload'/>";
	var urlManager="<c:url value='/file/fileManager'/>";
	var editor1="";
	var type="";
  KindEditor.ready(function(K) {
	  updateMap();
	  
		editor1=K.create("#content",{
			uploadJson :urlLoad,
			fileManagerJson :urlManager,
			filePostName:"imgFile",
			allowFileManager : true,
		    extraFileUploadParams : {
		    	module :"member"
          }
		});
	
	
		K('#imgButton').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,	clickFn : function(url, title, width, height, border, align) {
						K('#logoPicUrl').val(url);
						K("#logoUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});
		
		K('#imgButton1').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#picUrl').val(url);
						K("#purl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});
		
		K('#imgButton2').click(function() {
			type=$(this).data("type"); //当前上传图片的类型 
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
						K('#widePic').val(url);
						K("#wurl").attr("src",url);
						editor1.hideDialog();
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
	
  	$("#addressUrl").val(a);
  }
  </script>
</head>
<body>
	<div class="easyui-panel" title="商家添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px;width:100%;">
	    <form id="dataForm"  method="post">
	   	<input type="hidden" name="allImages" id="allImages" value=""/>	
	    <div style="width:48%;float:left;" >
	    	<table cellpadding="3">
	    		<tr>
	    			<input type="hidden" value="${member.memberId }" name="memberId"/>
	    			<input type="hidden" value="${member.parentId }" name="parentId"/>
	    			<td>商家名称:</td>
	    			<td><input   class="easyui-validatebox input"  type="text" style="width:300px;height:30px;"  missingMessage="填写登录用户名" name="busname" data-options="required:true" value="${member.busname }"></input></td>
	    		</tr>
	    		<tr>
	    			
	    			<td>登录用户名:</td>
	    			<td><input   class="easyui-validatebox input"  type="text" style="width:300px;height:30px;"  value="${member.username }" disabled="disabled"></input></td>
	    			<input type="hidden" value="${member.username }" name="username"/>
	    		</tr>
	    			    		<tr>
	    			<td>商家登录密码:</td>
	    			<td><input   class="easyui-validatebox input"  type="text" style="width:300px;height:30px;"  missingMessage="填写登录密码" name="pwd" data-options="required:true" value="${member.pwd }"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>消券验证码:</td>
	    			<td><input   class="easyui-validatebox input"  type="text" style="width:300px;height:30px;"  missingMessage="填写消券验证码" name="code" data-options="required:true" value="${member.code }"></input></td>
	    		</tr>
	    		
				<tr>
	    			<td>是否自营:</td>
	    			<td>
	    				<input type="hidden" name="isSelf"  value="${member.isSelf }" id="isself"/>
	    				<input type="radio" name="_isself" value="1" <c:if test="${member.isSelf == 1 }"> checked="checked"</c:if>>是
	    				<input type="radio" name="_isself" value="0" <c:if test="${member.isSelf == 0 }"> checked="checked"</c:if>>否
	    				<c:if test=""></c:if>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>是否连锁:</td>
	    			<td>
	    				<input type="hidden" name="isChain" value="${member.isChain }" id="ischain" />
	    				
	    				<input type="radio" name="_ischain" value="0" <c:if test="${member.isChain == 0 }"> checked="checked"</c:if>>是
	    				<input type="radio" name="_ischain" value="1" <c:if test="${member.isChain == 1 }"> checked="checked"</c:if>>否
	    			</td>
	    		</tr> 	
	    		
	  	    	<tr>
	    		<td>选择城市:</td>
	    		<td>
	    		<select  class="input" style="width:145px;" id="selectCity" name="provinId">
	    				<option value="">---请选择---</option>
	    				<c:forEach items="${cityList }" var="ct">
	    				 <option value="${ct.id}" <c:if test="${ct.id == member.provinId}">selected="selected"</c:if>>${ct.name }</option>
	    				</c:forEach>
	    		</select>
	    			
	    		<select name="cityId" class="input" style="width:145px;" id="cityId" >
						<option value="">---请选择--- </option>
	    		</select>
	    				<input type="hidden" value="${member.cityId }" id="cid"/>
	    		</td>
	    		</tr>
	    		<tr>
	    			<td>选择商圈:</td>
	    			<td>
	    			<select class="input" name="regionId">
	    				<option value="">---请选择--- </option>
	    				<c:forEach items="${regList }" var="reg">
						<option value="${reg.regionId }"<c:if test="${reg.regionId == member.regionId}">selected="selected"</c:if>>${reg.regionName }</option>
						</c:forEach>
	    			</select>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>选择品类:</td>
	    			<td>
	    			<select class="input" name="cgId">
	    				<option value="">---请选择--- </option>
	    				<c:forEach items="${cateList }" var="cat">
	    					<option value="${cat.cgId }"<c:if test="${cat.cgId == member.cgId}">selected="selected"</c:if>>${cat.cgName }</option>	
	    				</c:forEach>
	    			</select>
	    			
					</td>
	    		</tr>
	    		

	    		<tr>
	    			<td>联系电话:</td>
	    			<td><input   class="easyui-validatebox input"  type="text"   missingMessage="填写联系电话" name="telephone" data-options="required:true" value="${member.telephone }"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>腾讯QQ:</td>
	    			<td><input   class="easyui-validatebox input"  type="text"   missingMessage="填写腾讯QQ" name="qq"  value="${member.qq }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td><input   class="easyui-validatebox input"  type="text"  missingMessage="填写邮箱" name="email" value="${member.email }"></input></td>
	    		</tr>

	    		<tr>
	    			<td>商家品牌语:</td>
	    			<td>
					<textarea rows="8" cols="40" name="title">${member.title }</textarea>
					</td>
	    		</tr>
	    		<tr>
	    			<td> 详细地址:</td>
	    			<td>
	    			<textarea rows="8" cols="40" name="address" id="address">${member.address }</textarea>
	    			<input value="点击获取地图" type="button" style="width:100px;" onclick="updateMap();">
	    			</td>
	    		 <input name="addressUrl" id="addressUrl" type="hidden" value="${member.addressUrl }">
	    		</tr>
	    		<tr>
	    		<td>地址预览:</td>
				<td height="60"  valign="middle">
              	<iframe id="baidumap_iframe" src="http://map.baidu.com/mobile/webapp/index/index/qt=s&wd=&c=315&searchFlag=bigBox&nb_x=13222089.80322&nb_y=3750948.9116394&center_rank=1/vt=map&center_name=%E6%88%91%E7%9A%84%E4%BD%8D%E7%BD%AE&i=0" frameborder="0" style="width:560px;height:362px;"> </iframe>
              	</td>
	    		</tr>
	    	</table>
	    </div>
	    <div style="width:48%;float:right;">
	    <table cellpadding="3">
	    		<tr>
	    			<td>商家LOGO:</td>
	    			<td>	
	    			<img alt="" src="${member.logoPicUrl }" id="logoUrl" style="width:70px;height:70px">    			
					<input type="hidden" name="logoPicUrl" id="logoPicUrl" value="${member.logoPicUrl }"> 
	    			<input type="button" id="imgButton" class="easyui-linkbutton"  data-type="logourl" style="width:70px;height:25px;float:right;"  value="上传"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>商家长图片:</td>
	    			<td>
	    			<img alt="" src="${member.picUrl }" id="purl" style="width:70px;height:70px">
	    			<input type="hidden" value="${member.picUrl }" name="picUrl" id="picUrl"/>
	    			<input type="button" id="imgButton1" class="easyui-linkbutton" data-type="purl"   style="width:70px;height:25px;float:right;"  value="上传"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>商家宽图片:</td>
	    			<td>
	    			<img alt="" src="${member.widePic}" id="wurl" style="width:70px;height:70px">
	    			<input type="hidden" name="widePic" id="widePic" value="${member.widePic}"/>
	    			<input type="button" id="imgButton2" class="easyui-linkbutton"   data-type="wpic" style="width:70px;height:25px;float:right;"  value="上传"/>
	    			</br>
<!-- 	    			<input type="button" id="fj" class="easyui-linkbutton"   style="width:70px;height:25px;float:right;"  value="添加附件"/> -->
	    			</td>
	    		</tr>	    			    		

	    	</table>
	    </div>
	    </br>
	    <div style="float:left;width:97%;padding:10px;">
	    	   <table cellpadding="3">
	    	   	    				    		
	    		<tr>
	    			<td>商家简介:</td>
	    			<td>
	    			<textarea rows="10" cols="80" id="content">${member.description }</textarea>
					</td>
	    		</tr>
	    		
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" id="submitForm">提交保存</a></td>
	    		</tr>
	    		</table>
	    </div>
	    	    <input type="hidden"  name="description" id="desp" />
	    </form>

	    </div>
	</div>
	<script>
		function submitForm(){
	
			url="<c:url value='/member/update'/>";
			$("#desp").val("");
			$("#desp").val(editor1.html());
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
                		   window.location.href="<c:url value='/member/index'/>";
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
			 seleList($("#selectCity").val());
			 $("#selectCity").bind("change",function(){
				
				$("#cityId").empty();
				seleList(this.value);
			 });
			  //是否连锁 
			  $("input[name='_ischain']").bind('click',function(){

					 $("#ischain").val(this.value);

			  });
			  
			  //是否自营 
			  $("input[name='_isself']").bind('click',function(){
			 		
					 $("#isself").val(this.value);

			  });
			 //提交表单
			 $("#submitForm").bind("click",function(){
					
					submitForm();
				});
		})
	</script>
</body>
</html>