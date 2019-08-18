layui.use(['form','layer'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
    $ = layui.jquery;

    form.on("submit(addArticle)",function(data){
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#id").val()==="") {
            $.post("/article/add",data.field,function(res){
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
            $.post("/article/edit",data.field,function(res){
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

    $.post("/menu/menuSelectData",function(data){
                var parentSelectDataList = data.data;
                parentSelectDataList.forEach(function(e){
                    $("#menuSelect").append("<option value='"+e.id+"'>"+e.name+"</option>");
                });
                //编辑
                if($("#menuId").val()!==""){
                    $("#menuSelect").val($("#menuId").val());//默认选中
//                    $("#menuSelect").attr("disabled","disabled");
                }
                $("#originTypeSelect").empty();//清空下拉列表
                $("#originTypeSelect").append("<option value='0'>原创</option>");
                $("#originTypeSelect").append("<option value='1'>网络</option>");
                if($("#originType").val()!=="") {
                    $("#originTypeSelect").val($("#originType").val());
                }
                $("#statusSelect").empty();//清空下拉列表
                $("#statusSelect").append("<option value='0'>已完毕</option>");
                $("#statusSelect").append("<option value='1'>连载中</option>");
                if($("#status").val()!=="") {
                    $("#statusSelect").val($("#status").val());
                }
                form.render('select');//重新渲染
            });

})