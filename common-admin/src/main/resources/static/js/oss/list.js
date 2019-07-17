layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#ossList',
        url : '/oss/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "ossListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
			{field: 'company', title: '服务商', minWidth:100, align:"center"},
			{field: 'endPoint', title: '端点', minWidth:100, align:"center"},
			{field: 'accessId', title: '访问ID', minWidth:100, align:"center"},
			{field: 'secret', title: '密钥', minWidth:100, align:"center"},
			{field: 'ossPreffix', title: '访问URL', minWidth:100, align:"center"},
			{field: 'ossBucketName', title: 'BUCKET', minWidth:100, align:"center"},
			{field: 'status', title: '状态', minWidth:100, align:"center",templet : function(d){
                                                                                		    if (d.status < 0) {
                                                                                		        return '已失效';
                                                                                		    } else{
                                                                                                return '使用中';
                                                                                		    }
                                                                                		}}
		]]

    });

    //搜索
    $(".search_btn").on("click",function(){
        table.reload("ossListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                searchKey: $(".searchKey").val()
            }
        })
    });

    //添加
    function addOss(edit){
        var title = "添加";
        var info = "info.html";
        if (edit){
            title = "查看";
            info = "show.html";
        }

        layui.layer.open({
            title : title,
            type : 2,
            area : ["500px","430px"],
            content : info,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
					                    body.find("#id").val(edit.id);
					                    body.find("#company").val(edit.company);
					                    body.find("#endPoint").val(edit.endPoint);
					                    body.find("#accessId").val(edit.accessId);
					                    body.find("#secret").val(edit.secret);
					                    body.find("#ossPreffix").val(edit.ossPreffix);
					                    body.find("#ossBucketName").val(edit.ossBucketName);
					                    form.render();
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
        addOss();
    });

    $(".edit_btn").click(function(){
        var checkStatus = table.checkStatus('ossListTable'),
            data = checkStatus.data;
        if(data.length > 0){
            addOss(data[0]);
        }else{
            layer.msg("请选择需要查看的记录");
        }
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('ossListTable'),
            data = checkStatus.data,
            idArr = [];
        if(data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/oss/delBatch",{
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