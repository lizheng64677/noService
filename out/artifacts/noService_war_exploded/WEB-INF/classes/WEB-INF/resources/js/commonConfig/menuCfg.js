var menuFn={
	append:function(){
		this.type="add";
		$("#menuInfo").hide();
		$("#modelBox").show();
		$("#modelBox td:not('#nodeTypeTd')").find("input[type='text']:first").each(function(){
			$(this).textbox('setValue',"");
		});
		$("#modelBox").find("select").combobox('setValue',"1");
	},
	update:function(){
		this.type="edit";
		$("#menuInfo").hide();
		$("#modelBox").show();
		this.queryData($("input[name='id']").val());
		var data=this.data;
		$("#tname").textbox('setValue',data.menuInfo.nodeName);
		$("#moduleUrl").textbox('setValue',data.menuInfo.moduleUrl);
		$("#resourceContent").textbox('setValue',data.menuInfo.resourceContent);
		$("#resourceExplain").textbox('setValue',data.menuInfo.resourceExplain);
		$("#nodeType").combobox('setValue',data.menuInfo.nodeType);
				
	},
	remove:function(){
		$.messager.confirm('确认', '是否确定要删除!', function(data){
			if(!data){
				return;
			}
			$.post(SITE_BASE_PATH+'menu/deleteMenu?', {id: $("input[name='id']").val()},function(data){
				if(data.result > 0){
					initTree();
				}
			},"json");	
		});	
	},
	/**
	 * 点击菜单信息展示
	 */
	queryInfo:function(id){
		$("#modelBox").hide();
		$("#menuInfo").show();
		this.queryData(id);
		var data=this.data;
		$("#nodeName_").html(data.menuInfo.nodeName);
		$("#moduleUrl_").html(data.menuInfo.moduleUrl);
		$("#resourceContent_").html(data.menuInfo.resourceContent);
		$("#resourceExplain_").html(data.menuInfo.resourceExplain);
		$("#nodeType_").html(data.menuInfo.nodeType==1?"类别":"模块");
	},
	/**
	 * 根据菜单id查询菜单信息
	 */
	queryData:function(id){
		$.ajax({
			type : 'POST',
			url : SITE_BASE_PATH+'/menu/queryMenuInfo?',
			data : {"id":id},
			dataType : "json",
			async:false,
			success: function(data) {
				if(data){
					menuFn.data=data;
				}
			}
		});
	},
	/**
	 * 数据提交
	 */
	submitData:function(url){
		/*var isValid = $("#dataForm").form('validate');
		if(!isValid){
			return false;
		}*/
		if($.trim($("#tname").val())==""){
			$.messager.alert("操作提示","节点名称不能为空");
			return false;
		}
		var url=SITE_BASE_PATH+'/menu/addMenu?';
		if(this.type=='edit'){
			url=SITE_BASE_PATH+'/menu/updateMenu?';
		}
		$.ajax({
			type : 'POST',
			url : url,
			data : $("#dataForm").serialize(),
			dataType : "json",
			success: function(data) {
                   if(1==data.result){
                	   $.messager.alert("操作提示", "提交成功！", "info", function () {
                		   initTree();
                       });
       
                   }else{
                	   $.messager.alert("操作提示","提交失败!");
                   }
            } 
		});
	}
};

function exists(rows, parentId){
    for(var i=0; i<rows.length; i++){
        if (rows[i].id == parentId) return true;
    }
    return false;
}

function convertEasyUIParentChildTree(rows){
    
    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
            nodes.push({
                id:row.id,
                text:row.nodeName
            });
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
                var child = {id:row.id,text:row.nodeName};
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    
    var nodes_=[];
    nodes_.push({
    	id:0,
    	text:"菜单",
    	children:nodes
    });
    return nodes_;
}

