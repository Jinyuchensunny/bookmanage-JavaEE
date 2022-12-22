<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>图书列表</title>
    <%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
    <script src="${APP_PATH}/jslib/js/jquery-1.12.4.min.js"></script>
    <script src="./jslib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link href="./jslib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<!-- 搭建显示页面 -->
<div class="container">
    <!--标题-->
    <div class="row">
        <div class="col-md-12">
            <h1>图书列表</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="Book_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="Book_delete_all_btn">删除</button>
        </div>
    </div>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="Books_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all"/>
                    </th>
                    <th>图书分类名称</th>
                    <th>图书名称</th>
                    <th>图书单价</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- 员工添加的模态框 -->
<div class="modal fade" id="BookAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">图书添加</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图书分类</label>
                        <div class="col-sm-10">
                            <input type="text" name="BookClass" class="form-control" id="BookClass_add_input" placeholder="BookClass">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图书名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="BookName" class="form-control" id="BookName_add_input" placeholder="BookName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图书价格</label>
                        <div class="col-sm-10">
                            <input type="text" name="BookPrice" class="form-control" id="BookPrice_add_input" placeholder="BookPrice">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="Book_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //1、页面加载完成以后，直接去发送 ajax 请求,要到分页数据
    $(function () {
        //去首页
        findAllBook();
    })

    function findAllBook(){
        $.ajax({
            url:"${APP_PATH}/findAllBook",
            type:"GET",
            success:function (result) {
                console.log(result);
                //1、解析并显示图书数据
                var jsjson=eval("("+result+")");
                console.log(jsjson);
                build_book_table(jsjson);
            }
        })
    }

    function build_book_table(jsjson){
        //清空 table 表格
        $("#Books_table tbody").empty();
        var book = jsjson.result;
        $.each(book, function(index,item){
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
            var BookClassIDTd= $("<td></td>").append(item.BookClass);
            var BookNameTd = $("<td></td>").append(item.BookName);
            var BookPriceTd = $("<td></td>").append(item.BookPrice);
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                .append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前图书 id
            editBtn.attr("edit-id", item.BookId);
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                .append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的图书 id
            delBtn.attr("del-id", item.BookId);

            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            //append 方法执行完后还是返回原对象，可以连续 append，最后添加到 tbody 中
            $("<tr></tr>").append(checkBoxTd)
                .append(BookClassIDTd)
                .append(BookNameTd)
                .append(BookPriceTd)
                .append(btnTd)
                .appendTo("#Books_table tbody");
        });
    }

    function reset_form(ele){
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //点击新增按钮弹出模态框。
    $("#Book_add_modal_btn").click(function(){
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#BookAddModal form");
        //s$("")[0].reset();

        //弹出模态框
        $("#BookAddModal").modal({
            backdrop:"static"
        });
    });

    //点击保存，保存员工。
    $("#Book_save_btn").click(function(){
        //1、模态框中填写的表单数据提交给服务器进行保存
        //1、先对要提交给服务器的数据进行校验

        //2、发送ajax请求保存员工
        $.ajax({
            url:"${APP_PATH}/addBook",
            type:"POST",
            data:$("#BookAddModal form").serialize(),
            success:function(result){
                //alert(result.msg);
                if(result.code == 100){
                    //员工保存成功；
                    //1、关闭模态框
                    $("#BookAddModal").modal('hide');

                    //2、来到最后一页，显示刚才保存的数据
                    //发送ajax请求显示最后一页数据即可
                    //to_page(totalRecord);
                }
            }
        });
    });

    $(document).on("click",".delete_btn",function(){
        var BookName = $(this).parents("tr").find("td:eq(2)").text();
        var flag = confirm("确定删除["+BookName+"]吗？");
        if (flag){
            var id = $(this).attr("del-id");
            // alert(id);
            $.ajax({
                url:"${APP_PATH}/deleteBook?bookClassId="+id,
                type:"post",
                async:false,
                data:{delid:id},
                dataType: 'json',
                success:function(data){
                    window.location.reload();
                }
            })
        }
    });

</script>
</body>