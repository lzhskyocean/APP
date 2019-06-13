// 一级分类联动二级分类.
function findLevelTwo(id,$,form){
    // 声明第一个请选择选项
    var content = "<option value='0'>--请选择--</option>";
    // 判断
    if(id == 0){
        // 选择的是请选择.
        $("#categoryLevelTwo").html(content);
        $("#categoryLevelThree").html(content);
        // 重新渲染select
        form.render('select');
        return ;
    }
    // 发送ajax请求数据.
    $.getJSON(
        "/dev/app/find-category",
        "parentId=" + id,
        function(result){


            // 遍历json数组
            for(var i = 0;i < result.length;i++){
                // 封装每一个选项
                content += "<option value='"+result[i].id+"'>"+result[i].categoryName+"</option>";
            }

            // 插入到二级分类的select标签体内.
            $("#categoryLevelTwo").html(content);

            // 重新渲染select
            form.render('select');
        }
    );
}


// 二级分类联动三级分类.
function findLevelThree(id,$,form){
    // 声明第一个请选择选项
    var content = "<option value='0'>--请选择--</option>";
    // 判断
    if(id == 0){
        // 选择的是请选择.
        $("#categoryLevelThree").html(content);
        // 重新渲染select
        form.render('select');
        return ;
    }
    // 发送ajax请求数据.
    $.getJSON(
        "/dev/app/find-category",
        "parentId=" + id,
        function(result){
            // 遍历json数组
            for(var i = 0;i < result.length;i++){
                // 封装每一个选项
                content += "<option value='"+result[i].id+"'>"+result[i].categoryName+"</option>";
            }

            // 插入到二级分类的select标签体内.
            $("#categoryLevelThree").html(content);

            // 重新渲染select
            form.render('select');
        }
    );
}