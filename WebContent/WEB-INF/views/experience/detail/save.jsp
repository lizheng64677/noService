<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src=" <c:url value="/resources/js/My97DatePicker/WdatePicker.js"></c:url>"> </script>
<style type="text/css">
._serarchDiv{
	position: absolute;
	z-index: 110003;
	display: none; 
	left: 29%;
	width: 180px;
	top: 99px;
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
	<div class="easyui-panel" title="活动产品添加"   style="padding:10px;">
		<div style="padding:10px 10px 10px 10px">
	    <form id="dataForm"  method="post">
	    	<input type="hidden" value="${expId }" name="expId"/>
	    	<table cellpadding="3">
	    		<tr>
	    			<td>商家名称:</td>
	    			<td>
	    		
	    			<select class="input" name="memberId" id="memberId">
	    				<option value="">---请选择---</option>
	    				<c:forEach items="${memberList }" var="m">
	    				<option value="${m.id }">${m.text }</option>
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
	    			<td>产品名称:</td>
	    			<td>
	    			<select class="input" name="proId" id="proId">
	    				<option value="">---请选择---</option>
	    			</select>
	    			</td>
	    		</tr>
	    		<tr>
		    		<td>产品数量:</td>
		    		<td>
		    		<input type="text"  class="easyui-validatebox input"   missingMessage="请填写产品数量"  data-options="required:true"  name="proNum"/>
		    		</td>
	    		</tr>
	    		<tr>
	    			<td>券有效期:</td>
	    			<td>
	    			<select class="input" id="timeout">
	    				<option value="">---请选择---</option>
	    				<option value="0">几天后劵失效</option>
						<option value="1">券的有效期</option>
	    			</select>
	
	    			<input type="text"  class="easyui-validatebox input" style="width:150px;" id="time" placeholder="输入天数或选择时间" />
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
	
			var url="<c:url value='/expdetail/add'/>";
			var isValid = $("#dataForm").form('validate');
			if(!isValid){
				return false;
			}
			var _memberId=$("#memberId").val();			
			var _pvalue=$("#proId").val();
			var _timeout=$("#timeout").val();
			var _time=$("#time").val();
			if(_memberId==""){
				
				$.messager.alert("提示", "请选择商家！", true, "warning");  
				return false;
			}
			if(_pvalue==""){
				
				$.messager.alert("提示", "请选择产品！", true, "warning");
				return false;
			}
			if(_timeout==""){
				
				$.messager.alert("提示", "请选择券有效期类型！", true, "warning");
				return false;
			}
			if(_time==""){
				
				$.messager.alert("提示", "请输入天数或选择时间！", true, "warning");
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
                		   window.location.href="<c:url value='/expdetail/index?expId=${expId }'/>"; 
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
		
		//根据商家id查询所包含的产品 
		function setProId(memberId){
		var url="<c:url value='/expdetail/findProductByMember'/>";
		$.post(url,{memberId:memberId},function(data){
				$("#proId").empty();
				var html='';
				$(data.result).each(function(i,val){
					html+="<option value="+val.proId+">"+val.proName+"</option>";
				});
				$("#proId").html(html);

			},"json");

		}
		
		$("#submitForm").bind("click",function(){
			
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
				    		setProId($(this).val());
				    		
				    	}
				    	 $("._serarchDiv").hide();
					 }); 
				});
			 }); 
			$("#timeout").bind("change",function(){
			   if(this.value == 0){
				   
				   	$("#time").unbind("click");
					$("#time").attr("name","addDay");
			   }else{
				   
				   $("#time").attr("name","validity");
				   $("#time").bind("click",function(){
					   
					   WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
					   
				   })
				   
			   }
			
			});
			$("#memberId").bind("change",function(){
				
				setProId($(this).val());
			});
			$("body").click(function(){
				$("._serarchDiv").hide();
			});
	</script>
</body>
</html>