<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common_resource.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	function convertTreeGridData(rows) {
		function exists(rows, parentId) {
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].id == parentId)
					return true;
			}
			return false;
		}

		var nodes = [];
		// get the top level nodes
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (!exists(rows, row.parentId)) {
				nodes.push(row);
			}
		}
		var toDo = [];
		for (var i = 0; i < nodes.length; i++) {
			toDo.push(nodes[i]);
		}
		while (toDo.length) {
			var node = toDo.shift(); // the parent node
			// get the children nodes
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				if (row.parentId == node.id) {
					var child = row;
					if (node.children) {
						node.children.push(child);
					} else {
						node.children = [ child ];
					}
					toDo.push(child);
				}
			}
		}
		return nodes;
	}

	$(function() {
		$('#menus').combotree(
				{
					url : "<c:url value="/weChatMenu/synTreeList"/>",
					loadFilter : function(rows) {
						return convertTreeGridData(rows);
					},
					multiple : true,
					cascadeCheck : false,
					onLoadSuccess : function(node, data) {
						/*  var t = Travel('menu');
						for (var i = 0; i < t.length; i++) {
							var node = $('#menu').combotree('tree').tree(
									'find', t[i].id);
							if ('${id}' == t[i].id) {
								$('#menu').combotree('tree').tree('check',
										node.target);
							}
						}  */
						$('#menus').combotree('setValues', ['${id}']);
					},
					onCheck : function(node, checked) {
						var nodes = $('#menus').combotree('tree').tree('getChecked');
						if (1 < nodes.length) {
							$.messager.alert('','只能选择一个父节点','info');
							$('#menus').combotree('setValues', ['${id}']);
							return ;
						}
						if(checked){
							$("#parentId").val(nodes[0].id);
						}else{
							$("#parentId").val("0");
						}
						
					}
				});
	});

	/*获取全部节点 以及 节点的选中情况 组装成字符串 返回*/
	function Travel(treeID) {//参数为树的ID，注意不要添加#
		var roots = $('#' + treeID).combotree('tree').tree('getRoots'), children, i, j;
		for (i = 0; i < roots.length; i++) {
			children = $('#' + treeID).combotree('tree').tree('getChildren',
					roots[i].target);
			for (j = 0; j < children.length; j++) {
				roots.push(children[j]);
			}
		}
		return roots;
	}
</script>
</head>
<body>
	<div class="easyui-panel" title="微信菜单添加" style="padding: 10px;">
		<div style="padding: 5px 5px 5px 5px;width:50%;" >
			<form id="menu" method="post">
				<table cellpadding="3">
					<tr>
						<td>菜单名称:</td>
						<td><input class="easyui-textbox" type="text"
							missingMessage="菜单名称" name="name" data-options="required:true"></input>
							<input type="hidden" id="parentId" name="parentId" value="${id }"  />
							<input type="hidden" id="level" name="level" value="<c:if test="${null==id}">1</c:if><c:if test="${null!=id}">2</c:if>"  />
							</td>
						<td>所属父级:</td>
						<td><select class="easyui-combotree" style="width: 200px"
							id="menus"></select></td>
					</tr>
					<tr>
						<td title="view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节" class="easyui-tooltip" id="tool">
						填写关键字或URL
						:</td>
						<td colspan="3">
						<input class="input" name="moduleUrl" style="height: 60px; width: 400px;" id="keyValue"/></td>
					</tr>
					<tr>
						<td title="对该功能的描述" class="easyui-tooltip">描述:</td>
						<td colspan="3"><input class="easyui-textbox" name="description"
							data-options="multiline:true" style="height: 60px; width: 400px;"></input></td>
					</tr>
					<tr>
						<td  title="菜单的响应动作类型，目前有click、view两种类型" class="easyui-tooltip">事件类型:</td>
						<td>
						<select  class=" input" style="width: 200px" name="nodeType" id="nType">
								<option selected="selected" value="click">点击事件</option>
								<option value="view">链接跳转</option>
								<option value="scancode_waitmsg">扫码带提示</option>
								<option value="scancode_push">扫码推事件</option>
								<option value="pic_sysphoto">系统拍照发图</option>
								<option value="pic_photo_or_album">拍照或者相册发图</option>
								<option value="pic_weixin">微信相册发图</option>
								<option value="location_select">发送位置</option>
						</select>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a data-options="iconCls:'icon-save'" href="javascript:void(0)"
					class="easyui-linkbutton" onclick="submitForm()">提交</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-back'" onclick="clearForm()">返回</a>
			</div>
		</div>
		<div style="padding: 5px 5px 5px 5px;width:45%;height:200px;" >
		 <span style="color:red;">注意（*）</span>
		 	</br>1.在选择类型，点击事件，或，地址链接两项时，URL或关键字不允许为空。</br>
		 		2.一级菜单最多允许创建三个，子菜单最多创建五个，菜单名称过长时，将以....号展示结束</br>
		 		3.每次修改添加或删除，都必须在列表页面刷新自定义菜单，否则将不会刷新公众号菜单
		 		
		</div>
	</div>
	<script>
	$(function(){
		$("#nType").bind("change",function(){
			if(this.value=="click"){
				$("#tool").text("关键字:");		
			}else if(this.value=="view"){
				$("#tool").text("URL:");		
			}else{
				
				$("#tool").text("可为空:");				
			}
			
		});
		
	});
		function submitForm() {
			$.ajax({   
	            type: 'POST',   
	            url: "<c:url value='/weChatMenu/addWeChatMenu'/>" ,   
	            data: $("#menu").serialize() ,
	            success: function(data) {
	                   if(data.message>0){
	                	   $.messager.alert("操作提示", "添加成功！", "info", function () {
	                		   window.location.href="<c:url value='/weChatMenu/'/>";
	                       });
	       
	                   }else{
	                	   systemShow("操作提示","添加失败!");
	                   }
	            },   
	           error: function(XmlHttpRequest, textStatus, errorThrown){  
	        	   systemAlert("系统错误","error");
	            }   
	       }); 
		}
		function clearForm() {
			 window.location.href="<c:url value='/weChatMenu/'/>";
		}
	</script>
</body>
</html>
