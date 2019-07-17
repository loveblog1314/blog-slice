layui.use(['form','layer'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
    $ = layui.jquery;
    form.on("submit(addMenu)",function(data){
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#id").val()==="") {
            $.post("/menu/add",data.field,function(res){
                if (res.data){
                    layer.close(index);
                    layer.msg("添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg(data.msg);
                }
            })
        } else {
            $.post("/menu/edit",data.field,function(res){
                if (res.data){
                    layer.close(index);
                    layer.msg("修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg(data.msg);
                }
            })
        }

        return false;
    })

    $.post("/menu/parentSelectData",function(data){
            var parentSelectDataList = data.data;
            $("#parentIdSelect").empty();//清空下拉列表
            $("#parentIdSelect").append("<option value=''>请选择上级菜单</option>");
            parentSelectDataList.forEach(function(e){
                $("#parentIdSelect").append("<option value='"+e.id+"'>"+e.name+"</option>");
            });
            //编辑
            if($("#parentId").val()!==""){
                $("#parentIdSelect").val($("#parentId").val());//默认选中
                $("#parentIdSelect").attr("disabled","disabled");
            }
            form.render('select');//重新渲染
        });

})