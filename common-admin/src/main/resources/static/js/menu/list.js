layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#menuList',
        url : '/menu/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "menuListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', minWidth:100, align:"center"},
			{field: 'name', title: '菜单名称', minWidth:100, align:"center"},
			{field: 'desc', title: '菜单描述', minWidth:100, align:"center"},
			{field: 'updateTime', title: '更新时间', minWidth:100, align:"center"},
			{field: 'createTime', title: '创建时间', minWidth:100, align:"center"},
			{field: 'parentId', title: '上级菜单', minWidth:100, align:"center"}            			        ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
        table.reload("menuListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                searchKey: $(".searchKey").val()
            }
        })
    });

    //添加
    function addMenu(edit){

        var title = "添加";
        if (edit){
            title = "编辑";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area : ["500px","300px"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
					                    body.find("#id").val(edit.id);
					                    body.find("#name").val(edit.name);
					                    body.find("#desc").val(edit.desc);
					                    body.find("#updateTime").val(edit.updateTime);
					                    body.find("#createTime").val(edit.createTime);
					                    body.find("#parentId").val(edit.parentId);
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    }

    $(".add_btn").click(function(){
        addMenu();
    });

    $(".edit_btn").click(function(){
        var checkStatus = table.checkStatus('menuListTable'),
            data = checkStatus.data;
        if(data.length > 0){
            addMenu(data[0]);
        }else{
            layer.msg("请选择需要修改的记录");
        }
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('menuListTable'),
            data = checkStatus.data,
            idArr = [];
        if(data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/menu/delBatch",{
                    idArr : idArr.toString()
                },function(data){
                    layer.close(index);
                    tableIns.reload();
                    if (data.data){
                        layer.msg("删除成功！");
                    } else {
                        layer.msg(data.msg);
                    }
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })


})