<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tdwidth{width:350px;}
</style>
</head>
<body class="easyui-layout">

<div data-options="region:'west'" style="width:30%;">
<div class="easyui-panel" title="菜单列表" fit="true" border="false">
	<div id="tt"></div>
	<div id="menu1" class="easyui-menu" style="width:120px;">
		<div onclick="menuFn.append()" data-options="iconCls:'icon-add'">新增模板</div>
	</div>
	<div id="menu2" class="easyui-menu" style="width:120px;">
		<div onclick="menuFn.append()" data-options="iconCls:'icon-add'">新增模板</div>
		<div onclick="menuFn.update()" data-options="iconCls:'icon-edit'">修改本级</div>
	</div>
	<div id="menu3" class="easyui-menu" style="width:120px;">
		<div onclick="menuFn.append()" data-options="iconCls:'icon-add'">新增功能</div>
		<div onclick="menuFn.update()" data-options="iconCls:'icon-edit'">修改本级</div>
		<div onclick="menuFn.remove()" data-options="iconCls:'icon-remove'">删除本级</div>
	</div>
</div>  
</div>
<div data-options="region:'east'" style="width:70%;">
<div class="easyui-panel" title="菜单管理" fit="true" border="false">
	<div  id="modelBox" style="display:none;">
		<form id="dataForm"  method="post">
	    	<table cellpadding="3">
	    		<tr>
	    			<td>节点名称:
		    			<input type="hidden" name="parentId"/>
		    			<input type="hidden" name="id"/>
	    			</td>
	    			<td><input   class="easyui-textbox tdwidth" type="text" style="height:35px;"  missingMessage="填写节点名称" name="tname" data-options="required:true" id="tname"></input></td>
	    		</tr>
	    		<tr>
	    			<td>节点链接:</td>
	    			<td><input class="easyui-textbox tdwidth" type="text" style="height:35px;" name="moduleUrl" id="moduleUrl"></input></td>
	    		</tr>
	    		<tr>
	    			<td>节点类型:</td>
	    			<td id="nodeTypeTd">
	    				<select class="easyui-combobox tdwidth" data-options="required:true" style="height:35px;" name="nodeType" id="nodeType">
		    				<option value="1">类别</option>
		    				<option value="2">模块</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>资源内容:</td>
	    			<td><input class="easyui-textbox tdwidth" type="text" style="height:35px;" name="resourceContent" id="resourceContent"></input></td>
	    		</tr>
	    		<tr>
	    			<td>备&nbsp;&nbsp;&nbsp;&nbsp;注:</td>
	    			<td><input class="easyui-textbox tdwidth" type="text" style="height:35px;" name="resourceExplain" id="resourceExplain"></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center"> <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px;" onclick="menuFn.submitData()">提交</a></td>
	    		</tr>
	    	</table>
	    </form>
    </div>
	<div id="menuInfo" style="display:none;">
		<table style="margin:15px 30px;line-height: 30px;color:#555;">
    		<tr>
    			<td>节点名称:</td>
    			<td><span id="nodeName_"></span></td>
    		</tr>
    		<tr>
    			<td>节点链接:</td>
    			<td><span id="moduleUrl_"></span></td>
    		</tr>
    		<tr>
    			<td>节点类型:</td>
    			<td>
    				<span id="nodeType_"></span>
    			</td>
    		</tr>
    		<tr>
    			<td>资源内容:</td>
    			<td><span id="resourceContent_"></span></td>
    		</tr>
    		<tr>
    			<td>备注:</td>
    			<td><span id="resourceExplain_"></span></td>
   			</tr>
    	</table>
	</div>
</div>
</div> 
<script type="text/javascript" src="<c:url value='/resources/js/commonConfig/menuCfg.js'/>"></script>
<script type="text/javascript">
initTree();
function initTree(){
	$('#tt').tree({
		url:"<c:url value='/menu/synMenuList'/>",
		loadFilter:function(data){
			return convertEasyUIParentChildTree(data.jsonData);
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			var id=$('#tt').tree('getData', node.target).id;
			$("input[name='parentId']").val(id);
			$("input[name='id']").val(id);
			
			// 查找节点
			var len=$('#tt').tree('getChildren', node.target).length;
			var menu;
			//顶节点只能新增，当前节点若有子节点不能删除
			if(id==0){
				menu='#menu1';
			}
			else if(len>0){
				menu='#menu2';
			}
			else{
				menu='#menu3';
			}
			$(menu).menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		},
		onClick:function(node){
			menuFn.queryInfo(node.id);
		}
	
	});
}
</script>
</body>
</html>
