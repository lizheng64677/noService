var permissionFn={
	/**
	 * 查询角色权限
	 */
	queryPerms:function(roleId){
		this.roleId=roleId;
		this.initMenuTree();
		$("#confimButton").show();
	},
	/**
	 * 提交角色权限修改
	 */
	submitPerms:function(){
		var nodes = $('#tt').tree('getChecked');
		var ids = [];
		for(var i=0; i<nodes.length; i++){
			ids.push(nodes[i].id);
		}
		this.ids=ids;
		this.addPermissions();
	},
	/**
	 * 加载菜单树
	 * @param type
	 */
	initMenuTree:function(){
		$("#tt").tree({
			url:SITE_BASE_PATH+"/permission/synMenuList?roleId="+this.roleId,
			checkbox:true,
			cascadeCheck:false,
			loadFilter:function(data){
				return convertEasyUIParentChildTree(data.jsonData);
			},
			onCheck:function(node,checked){
				var el=$('#tt').tree('getParent', node.target);
				if(el&&checked){
					$('#tt').tree('check',el.target);
				}
				el=$('#tt').tree('getChildren', node.target);
				if(el&&!checked){
					for(var i=0;i<el.length;i++){
						$('#tt').tree('uncheck',el[i].target);
					}
				}
			}
		});
	},
	/**
	 * 确认修改角色权限
	 */
	addPermissions:function(){
		var idsArr=this.ids;
		var roleId=this.roleId;
		$.messager.confirm('确认', '确认提交!', function(data){
			if(!data){
				return;
			}
			$.post(SITE_BASE_PATH+'permission/addPermission?', {ids: idsArr.join(','),roleId:roleId},function(data){
				if(data.result){
					$.messager.alert("系统提示","权限设置成功!");
				}
			},"json");	
		}, null);	
	}
};

function convertEasyUIParentChildTree(rows,type){
    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
        	if(type){
        		 nodes.push({
                     id:row.id,
                     text:row.roleName,
                     iconCls:"icon-role"
                 });
        	}else{
        		nodes.push({
                    id:row.id,
                    text:row.nodeName,
                    checked:row.state==1?true:false
                });
        	}
        }
    }
    
    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while(toDo.length){
        var node = toDo.shift();    // the parent node
        // get the children nodes
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (row.parentId == node.id){
            	var child = {id:row.id,text:row.nodeName,checked:row.state==1?true:false};
            	if(type){
            		child = {id:row.id,text:row.roleName};
            	}
                
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }  
    return nodes;
}

function exists(rows, parentId){
    for(var i=0; i<rows.length; i++){
        if (rows[i].id == parentId) return true;
    }
    return false;
}