<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>图书分类列表</title>
    <%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
    <script src="${APP_PATH}/jslib/js/jquery-1.12.4.min.js"></script>
    <script src="./jslib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link href="./jslib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- 搭建显示页面 -->
<div class="container">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <h1>图书分类列表</h1>
        </div>
    </div>
    <!-- 按钮 -->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="BookKind_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="BookKind_delete_all_btn">删除</button>
        </div>
    </div>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="BookKinds_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all"/>
                    </th>
                    <th>图书分类编号</th>
                    <th>图书分类名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- 员工添加的模态框 -->
<div class="modal fade" id="BookKindAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">图书分类添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图书类别名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="BookKindName" class="form-control" id="BookKindName_add_input" placeholder="BookKindName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="BookKind_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function(){
        //去首页
        findAllBookClass();
    });

    function findAllBookClass(){
        $.ajax({
            url:"${APP_PATH}/findAllBookKind",
            type:"GET",
            success:function(result){
                var ggg=eval("("+result+")");
                console.log(result);
                //1、解析并显示设备分类数据
                build_bookclass_table(ggg);
            }
        });
    }

    //清空表单样式及内容
    function reset_form(ele){
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //点击新增按钮弹出模态框。
    $("#BookKind_add_modal_btn").click(function(){
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#BookKindAddModal form");
        //s$("")[0].reset();

        //弹出模态框
        $("#BookKindAddModal").modal({
            backdrop:"static"
        });
    });

    //点击保存，保存员工。
    $("#BookKind_save_btn").click(function(){
        //1、模态框中填写的表单数据提交给服务器进行保存
        //1、先对要提交给服务器的数据进行校验

        //2、发送ajax请求保存员工
        $.ajax({
            url:"${APP_PATH}/addBookKind",
            type:"POST",
            data:$("#BookKindAddModal form").serialize(),
            success:function(result){
                //alert(result.msg);
                if(result.code == 100){
                    //员工保存成功；
                    //1、关闭模态框
                    $("#BookKindAddModal").modal('hide');

                    //2、来到最后一页，显示刚才保存的数据
                    //发送ajax请求显示最后一页数据即可
                    //to_page(totalRecord);
                }
            }
        });
    });

    function build_bookclass_table(result){
        //清空table表格
        $("#BookKinds_table tbody").empty();
        console.log(result);
        $.each(result.result,function(index,item){
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
            var BookKindID = $("<td></td>").append(item.BookkindId);
            var BookKindName = $("<td></td>").append(item.BookkindName);

            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前员工id
            editBtn.attr("edit-id",item.BookkindId);
            var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的员工id
            delBtn.attr("del-id",item.BookkindId);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            //var delBtn =
            //append方法执行完成以后还是返回原来的元素

            $("<tr></tr>").append(checkBoxTd)
                .append(BookKindID)
                .append(BookKindName)
                .append(editBtn)
                .append(delBtn)
                .appendTo("#BookKinds_table tbody");
        });
    }

    //单个删除
    $(document).on("click",".delete_btn",function(){
        //1、弹出是否确认删除对话框
        var BookKindName = $(this).parents("tr").find("td:eq(2)").text();
        var BookKindId = $(this).attr("del-id");
        //alert($(this).parents("tr").find("td:eq(1)").text());
        if(confirm("确认删除["+BookKindName+"]图书类吗？")){
            //确认，发送ajax请求删除即可
            $.ajax({
                url:"${APP_PATH}/deleteBookKind?bookClassId="+BookKindId,
                type:"DELETE",
                success:function(result){
                    // alert(result.msg);
                    //回到本页
                    to_page(currentPage);
                }
            });
        }
    });
</script>


</body>
</html>
