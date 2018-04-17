<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" charset="utf-8" href="<c:url value='/resources/js/kindeditor/themes/default/default.css'/>" />
<script src="<c:url value='/resources/js/kindeditor/kindeditor-min.js'/>"></script>
<script type="text/javascript">
var editor1="";
var editor2="";
var editor3="";
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
		    	module :"product"
          }
		});
		K('#imgButton').click(function() {
		
			type=$(this).data("type"); //当前上传图片的类型 

			editor1.loadPlugin('image',function() {
				editor1.plugin.imageDialog({imageType:type,clickFn : function(url, title, width, height, border, align) {
					
						K('#proImg').val(url);
						K("#proImgUrl").attr("src",url);
						editor1.hideDialog();
					}
				});
			});
		});
		


		editor2=K.create("#content2",{
			uploadJson :urlLoad,
			fileManagerJson :urlManager,
			filePostName:"imgFile",
			allowFileManager : false,
		    extraFileUploadParams : {
		    	module :"product"
          }
		});
		editor3=K.create("#content3",{
			uploadJson :urlLoad,
			fileManagerJson :urlManager,
			filePostName:"imgFile",
			allowFileManager : false,
		    extraFileUploadParams : {
		    	module :"product"
          }
		});
});
	
var num=0
function annexImages(){
	 var d=$("#imageslength").val();	 
	  num++;
	 var m=Number(d)+Number(num);
	 if(m<5){
		var html="<tr id='html"+m+"'>";
			html+="<td>产品图片:</td>";
			html+="<td>";
			html+="<img alt='' src='' id='pro"+m+"' style='width:70px;height:70px'>";
			html+="<input type='button' id='annex"+m+"'  class='easyui-linkbutton'  data-type='pro' style='width:70px;height:25px;'  value='上传'/>";
			html+="<input type='hidden' name='allImages' id='imginfo"+m+"'  />";
			html+="<input type='button' id=''  class='easyui-linkbutton' onclick='deleImagesCopy("+m+")'  data-type='pro' style='width:70px;height:25px;'  value='删除'/>"
			html+="</td>";
		    html+="</tr>";
		$(html).insertBefore("#addimages");
		loadimages(m,2);
	 }else{
		 
		$.messager.alert("提示", "最多允许上传4张图片！", true, "warning");
	 }
}
function loadimages(num,id){
	
	$('#annex'+num).click(function() {
		
		type=$(this).data("type"); //当前上传图片的类型 

		editor1.loadPlugin('image',function() {
			editor1.plugin.imageDialog({imageType:type,clickFn : function(urla,url, title, width, height, border, align) {
					if(id=="1"){
					$("#imginfof"+num).val(urla);
					$('#prof'+num).attr("src",url);
					}else{
						
					$("#imginfo"+num).val(urla);
					$('#pro'+num).attr("src",url);
					}
					editor1.hideDialog();
				}
			});
		});
	});
}
function deleImagesCopy(id){
	$("#html"+id).empty();
	var d=$("#imageslength").val();	
	alert(d);
}
function deleImages(id,idf){
	$("#htmlf"+idf).empty();

	//alert(id);
}
</script>
<script type="text/javascript">

function submitForm(){
	url="<c:url value='/product/update'/>";
	var isValid = $("#dataForm").form('validate');
	if(!isValid){
		return false;
	}
	var _memberId=$("#memberId").val();	
	if(_memberId==""){
		
		$.messager.alert("提示", "请选择商家！", true, "warning");  
		return false;
	}
	$("#proInfo").val(editor1.html());
	$("#activityInfo").val(editor2.html());
	$("#useInfo").val(editor3.html());

	$.ajax({
		type : 'POST',
		url : url,
		data : $("#dataForm").serialize(),
		dataType : "json",
		success: function(data) {
           if(1==data.result){
        	   $.messager.alert("操作提示", "提交成功！", "info", function () {
        		   window.location.href="<c:url value='/product/index'/>";
               });

           }else{
        	   $.messager.alert("操作提示","提交失败!");
           }
        } 
	});
}
</script>
<style type="text/css">
._serarchDiv{
	position: absolute;
	z-index: 110003;
	display: none; 
	left: 40.4%;
	width: 180px;
	top: 44px;
	border:1px solid #95b8e7;
}
.seleDiv{
	font-size:14px;
	color:#000000;
    height: 25px;
    width: 178;

}
</style>
</head>
<body>
	<div class="easyui-panel" title="添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
  	    	 <!-- 图片相关信息 -->
<!-- 	        <input type="hidden" name="allImages" id="allImages" value=""/>	 -->
	    	<div style="width:60%;float:left;" >
	    	<table cellpadding="3">
	    		<tr>
	    			<td>产品标题:</td>
	    			<input type="hidden" name="proId" value="${product.proId }"/>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写产品标题" name="title" data-options="required:true" value="${product.title }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>产品名称:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写产品名称" name="proName" data-options="required:true" value="${product.proName }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>产品价格:</td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写产品价格" name="price" data-options="required:true" value="${product.price}"></input></td>
	    		</tr>
	      		<tr>
	    			<td>产品单位</td>
	    			<td><input  class="easyui-validatebox input"  type="text"  missingMessage="请填写产品单位"  placeholder="请输入产品单位如：份,个" name="unit" data-options="required:true" value="${product.unit }"></input></td>
	    		</tr>
	    		<tr>
	    		<td>所属商家:</td>
	    		<td>
	    		
	    		<select name="memberId" class="input" id="memberId">
  					<option value="">--请选择---</option>
  					<c:forEach items="${memberList }" var="m">
  					<option value="${m.id }" <c:if test="${product.memberId == m.id}">selected="selected"</c:if>>${m.text }</option>
  					
  					</c:forEach>
  					
	    		</select>
	    		<input type="text"  class="input" id="_serarch"   style="width:150px;" placeholder="输入商家名称查询" />
	    	  	<div class="_serarchDiv">
                          <div id="divPanel" class="cdiv" title="" style="width: 198px; height: 198px;">
                          </div>
                </div>
	    		</td>
	    		</tr>

	    		<tr>
	    		<td>套餐详情:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content1">${product.proInfo }</textarea>
	    		<input type="hidden" id="proInfo" name="proInfo" value=''/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>产品简介:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content2">${product.activityInfo }</textarea>
	    		<input type="hidden" id="activityInfo" name="activityInfo" value=''/>
	    		</td>
	    		</tr>
	    		<tr>
	    		<td>使用须知:</td>
	    		<td>
	    		<textarea rows="14" cols="100" id="content3">${product.useInfo }</textarea>
	    		<input type="hidden" id="useInfo" name="useInfo" value=''/>
	    		</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="submitForm();" >提交保存</a></td>
	    		</tr>
	    	</table>
	    	</div>
	    	<div style="width:40%;float:right;">
			    <table cellpadding="3">
			    		<tr>
			    			<td>默认显示图:</td>
			    			<td>	
			    			<input type="hidden" name="proImg" id="proImg" value="${product.proImg }"> 
			    			<img alt="" src="${product.proImg }" id="proImgUrl" style="width:70px;height:70px">    			
			    			<input type="button" id="imgButton" class="easyui-linkbutton"  data-type="pro_default" style="width:70px;height:25px;"  value="上传"/>
			    			</td>
			    		</tr>
			    		<c:forEach items="${mentList }" var="m" varStatus="index">
			    		<tr id="htmlf${index.index+1}">
						<td>产品图片</td>
						<td>
						<img alt='' src='${m.display_path }' id='prof${index.index+1}' style='width:70px;height:70px'>
						<input type='button' id='annex${index.index+1}'  class='easyui-linkbutton' onclick="loadimages(${index.index+1},1);"  data-type='pro' style='width:70px;height:25px;'  value='上传'/>
						<input type='hidden' name='allImages' value="${m.display_path };${m.file_path};${m.file_name};${m.file_size};pro@" id="imginfof${index.index+1}" />
						<input type='button' id='del${index.index+1}'  class='easyui-linkbutton' onclick="deleImages(${m.attachment_id},${index.index+1});"  data-type='pro' style='width:70px;height:25px;'  value='删除'/>
						</td>
					    </tr>
					    </c:forEach>
						<tr  id="addimages">
						<td></td>
						<td>
							<input type="hidden" value="${fn:length(mentList)}" id="imageslength" />
							<input type="button"  class="easyui-linkbutton"  style="width:70px;height:25px;" onclick="annexImages()" value="添加图片" />
						</td>
						</tr>		    			    		
		
			    	</table>
		    </div>
	    </form>
	    </div>
	</div>

	<script type="text/javascript">
	  

	

		function clearForm(){
			$('#dataForm').form('reset');
		}
		
		$(function(){
			
			//提交
			$("#submitForm").bind("click",function(){
				alert("d");
				submitForm();
			});

			//事件检索集合内数据 
			$("#_serarch").keyup(function(){
					 var  arrays=new Array();
					 //将option转移至数组  
				     $("#memberId option").each(function(){
				    	 if($(this).val()!=""){
				    		 arrays.push($(this).text());
				    	 }
					 }); 
				     var html='';
					 var textval=$("#_serarch").val();
					 for ( var i = 0; i < arrays.length; i++) {
						if(arrays[i].indexOf(textval)>-1){
							html+='<div id="'+i+'" class="seleDiv">'+arrays[i]+'</div>';
						}
					}
					 $("._serarchDiv").show();
					$("#divPanel").html(html);
					
					$(".seleDiv").click(function(){
						var seleDiv=$(this).html();
						$("#_serarch").val(seleDiv);
						$("#memberId option").each(function(){
					    	if($(this).text()==seleDiv){
					    		
					    		$(this).attr("selected", "selected");
					    		
					    	}
					    	 $("._serarchDiv").hide();
						 }); 
					});
				 }); 
			$("body").click(function(){
				$("._serarchDiv").hide();
			});
		});
	</script>
</body>
</html>