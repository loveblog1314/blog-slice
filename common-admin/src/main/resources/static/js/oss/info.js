layui.use(['form','layer'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
    $ = layui.jquery;

    form.on("submit(addOss)",function(data){
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#id").val()==="") {
            $.post("/oss/add",data.field,function(res){
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
            $.post("/oss/edit",data.field,function(res){
            $("#company").attr("disabled","disabled");
            $("#endPoint").attr("disabled","disabled");
            $("#accessId").attr("disabled","disabled");
            $("#secret").attr("disabled","disabled");
            $("#ossPreffix").attr("disabled","disabled");
            $("#ossBucketName").attr("disabled","disabled");
            form.render();
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

})