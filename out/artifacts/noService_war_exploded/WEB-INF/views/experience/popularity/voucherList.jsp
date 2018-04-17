<%@page import="com.suyin.experience.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp" %>
<html>
  <head>
  </head>
  <body id="search" class="easyui-layout"  >   
	<div id="tool">
	  		<a href="#" class="easyui-linkbutton" onclick="doVoucher();" data-options="iconCls:'icon-edit'">发券</a>
	</div>
	<div id="mydatagrid" fit="true"></div>
	<input type="hidden" id="expId" value="${expId }"/>
	<input type="hidden" id="timeId" value="${timeId }"/>
</body>
<script type="text/javascript">
$(function() {

	$('#mydatagrid').datagrid({
		fitColumns : true,
		url : "<c:url value='/exprenqi/voucherUserPoplist?expId=${expId}&timeId=${timeId}'/>",
		method : "POST",
		pageSize :10, 
		pageList : [10,20,50],
		columns : [[			    	
						{ "field":'userId',checkbox:true },
					    { "field": 'userName',"title" : '用户姓名',width:$(this).width() * 0.2},
					    { "field": 'iphone',"title" : '手机号码',width:$(this).width() * 0.2},
					    { "field": 'shareNum',"title" : '分享总数',width:$(this).width() * 0.2},
						{ "field": 'voucherStatus',"title" : '发券状态',width:$(this).width() * 0.2,formatter:function(value,row,index){
					    	if("0"==value){
					    		return "<span style='color:#00FF00'>未发券</span>";
					    	}else if("1"==value){
					    		return "<span style='color:red'>已发券</span>";
					    	}else{
					    		
					    		return "<span style='color:red'>参与失败</span>"; 
					    	}
					    }},
						{ "field": 'createTime',"title" : '参与时间',width:$(this).width() * 0.2}
		]],
		toolbar :'#tool',
		pagination : true,
		rownumbers : true
	});
});




//发券 
function doVoucher(){
	var rows=$("#mydatagrid").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("", "请选择数据！", true, "warning");
		return;
	}
	var ids = [];
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].userId);

	}
	$.messager.confirm('确认', '是否确定发放'+rows.length+'张券!', function(data){ 
		if(!data){
			return;
		}
		var timeId=$("#timeId").val();
		var expId=$("#expId").val();
		$.post(SITE_BASE_PATH+'exprenqi/sendUserPopInfo?', {"userId": ids.join(','),"expId":expId,"timeId":timeId},function(data){
			if(data.result=="success"){

				$('#mydatagrid').datagrid('reload');
				$.messager.alert("提示", "发券完毕！", true, "warning");
			}
		},"json");
	});	


}

</script>

</html>