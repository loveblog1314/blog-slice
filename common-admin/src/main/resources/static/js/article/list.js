layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    // 获取url
    var router = location.search.split("=")[1];

    //列表
    var tableIns = table.render({
        elem: '#articleList',
        url : '/article/lists',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "articleListTable",
        where:{menuId:router},
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
			{field: 'title', title: '文章标题', minWidth:100, align:"center"},
		    {field: 'thumb', title: '缩略图', minWidth:100, align:"center", templet: function(d){
                                                                                        return '<div onclick="show_img(this)" ><img src="'+d.thumb+'" alt="" width="50px" height="50px"></a></div>';
                                                                                    }
                                                                        },
		    {field: 'originType', title: '来源', minWidth:100, align:"center"},
		    {field: 'author', title: '作者', minWidth:100, align:"center"},
		    {field: 'hits', title: '浏览量', minWidth:100, align:"center"},
		    {field: 'updateTime', title: '更新时间', minWidth:100, align:"center"}
		 ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
        table.reload("articleListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                searchKey: $(".searchKey").val()
            }
        })
    });

    //添加
    function addArticle(edit){
        var title = "添加";
        if (edit){
            title = "编辑";
        }
        var index = layui.layer.open({
            title : title,
            type : 2,
            area : ["1280px","600px"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
					                    body.find("#id").val(edit.id);
					                    body.find("#menuId").val(edit.menuId);
					                    body.find("#title").val(edit.title);
					                    body.find("#content").val(edit.content);
					                    body.find("#edit").val(edit.content);
					                    body.find("#createTime").val(edit.createTime);
					                    body.find("#thumb").val(edit.thumb);
					                    body.find("#originType").val(edit.originType);
					                    body.find("#author").val(edit.author);
					                    body.find("#hits").val(edit.hits);
					                    form.render();
                } else {
                    body.find("#menuId").val(router);
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
    }

    $(".add_btn").click(function(){
        addArticle();
    });

    $(".edit_btn").click(function(){
        var checkStatus = table.checkStatus('articleListTable'),
            data = checkStatus.data;
        if(data.length > 0){
            addArticle(data[0]);
        }else{
            layer.msg("请选择需要修改的记录");
        }
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('articleListTable'),
            data = checkStatus.data,
            idArr = [];
        if(data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/article/delBatch",{
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

    //显示大图片
    function show_img(t) {
        var t = $(t).find("img");
        //页面层
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
             area: ['80%', '80%'], //宽高
            shadeClose: true, //开启遮罩关闭
            end: function (index, layero) {
                return false;
            },
            content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
        });
    }

})