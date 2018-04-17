<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	$(function() {
		$("#submit").click(function(){ 
			assembleUserPrototype();
		});
		var expId=$("#expid").val();
		var moduleType=$("#moduleType").val()
		var url="<c:url value='/expzhuanprototype/findExpZhuanPrototype'/>";
		$.ajax({
			url : url,
			type : 'post',
			data : {expId:expId,moduleType:moduleType},
			dataType:"json",
			success : function(data) {
				if(data.message !="success"){
	
					return false;
				}else{
					
				var html="<table cellpadding='3'>";
					$(data.result).each(function(i,result){
					
						if(result.dictionary_type == 1){
							
	
						}else if(result.dictionary_type == 2){				 	
				    		
							html+='<tr>';
							html+='<td>'+result.dictionary_name+'</td>';
							html+='<td>';
							
							$(result.options).each(function(i,val){
			
							 		if("1"==val.selected){
							 			html+='	<input tid='+val.dictionary_id+' type="radio" onclick="checkRadio(this)" name='+result.dictionary_id+' value='+val.dictionary_value+' class="radioStyle chooseRadio"/></span>';
							 		}else{
							 			html+='	<input tid='+val.dictionary_id+' type="radio" onclick="checkRadio(this)" name='+result.dictionary_id+' value='+val.dictionary_value+' class="radioStyle"/></span>';
							 		}
							 		
									html+='	<span>'+val.dictionary_name+'</span>';
							});
							html+='</td>';
							html+='</tr>';
						
						}else if(result.dictionary_type == 3){							
							
							html+='<tr>';
							html+='<td>'+result.dictionary_name+'</td>';
							html+='<td>';
					 		html+='<select	class="input" name='+data.result[i].dictionary_id+'>';
					 		
							$(result.options).each(function(i,val){									
									if("1"==val.selected){
										html+='<option selected=selected tid='+val.dictionary_id+' value='+val.dictionary_value+'>'+val.dictionary_name+'</option>';
									}else{
										html+='<option tid='+val.dictionary_id+' value='+val.dictionary_value+'>'+val.dictionary_name+'</option>';
									}
							 		
							});
					 		html+='</select>';
							html+='</td>';
							html+='</tr>';
	
							
						}
					});
					html+="</table>"
					$("#dataForm").append(html); 
				}
			}
		});
	});
</script>
<script type="text/javascript">
	function checkRadio(obj){
		var name=$(obj).attr("name");
		$("input[name='"+name+"']").removeClass("chooseRadio");
		$(obj).addClass("chooseRadio");
	}
</script>
<script type="text/javascript">
	function assembleUserPrototype(){
		var jsonArray=new Array();
		var jsonObject;
		var expid=$("#expid").val();
		var moduleType=$("#moduleType").val();
		//获取select元素选中值
		var selects=$("#dataForm").find("option:selected");
		for(var i=0;i<selects.length;i++){
			jsonObject=new Object();
			jsonObject.did=$(selects[i]).attr("tid");
			jsonObject.expid=expid;
			jsonObject.moduletype=moduleType;
			jsonArray.push(jsonObject);
		}
		//获取radios的单选按钮选中值
		var radios=$("#dataForm").find(".chooseRadio");
		for(var i=0;i<radios.length;i++){
			jsonObject=new Object();
			jsonObject.did=$(radios[i]).attr("tid");
			jsonObject.expid=expid;
			jsonObject.moduletype=moduleType;
			jsonArray.push(jsonObject);
		}
		var suburl="<c:url value='/expzhuanprototype/addExpZhuanPrototype'/>";
		//执行保存 
		$.ajax({
			url : suburl,
			type : 'post',
			dataType :'json',
			data:{paramdata:JSON.stringify(jsonArray),expId:expid},
			success : function(data) {
				if(data.message=="success"){
					var settingUrl="";
					var show=$("#showjsp").val();
					
					if(show==1){
						
						settingUrl="<c:url value='/expquanminzhuan/index'/>";
					}else if(show==2){
						
					
						settingUrl="<c:url value='/expshiyong/index'/>"; 
					}
					
             	   $.messager.alert("操作提示", "提交成功！", "info", function () {
             		   
            		   window.location.href=settingUrl;
                   });
             	   
				}else if(data.message=="invalidAddInfo"){
					
			    	   $.messager.alert("操作提示","信息更新失败!");
			    	   return false;
				}else if(data.message=="invalidDelInfo"){
					
			    	   $.messager.alert("操作提示","信息清除更新失败!");
			    	   return false;
				}
			}
		});
	}
</script>
<style type="text/css">

.radioStyle {
	width: 20px;
	height: 20px;
	background: #fff;
	border: none;
	-webkit-appearance: button;
	border-radius: 100%;
	border: solid 1px #e5e5e5;
	overflow: hidden;
	outline: none;
	outline: navajowhite;
}
.chooseRadio {
	background:#34c3ff;
	border:1px solid #fd8a1a;
}
</style>
</head>
<body>
	<div class="easyui-panel" title="问卷调查答案配置"   style="padding:10px;">
	    <input type="hidden" name="expId" value="${expId}" id="expid"/>
	    <input type="hidden" name="moduleType" value="${moduleType }" id="moduleType"/>
 	    <input type="hidden" id="showjsp" value="${showJsp }"/>
		<div style="padding:10px 10px 10px 10px" id="d">
	    <form id="dataForm"  method="post" >

	    </form>	   
	    </div>
	    <div style="padding:10px 10px 10px 10px;padding-left:220px;">

	    	<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;"  id="submit">提交保存</a>
	
	    </div>
	</div>
</body>

</html>