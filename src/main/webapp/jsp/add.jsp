<%@page contentType="text/html; charset=UTF-8"  language="java" %>
<%--引入jstl--%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增秒杀</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
<%--页面显示部分--%>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center" >
            <h2>新增秒杀</h2>
        </div>
        <div class="panel-body ">
            <form class="form-horizontal"  action="/seckill/add" method="POST">
                <div class="form-group">
                    <label for="name" class="col-sm-2  control-label">秒杀名称：</label>
                    <div class="col-sm-8">
                        <input type="text" name="name" class="form-control" id="name" placeholder="秒杀名称">
                     </div>
                </div>
                <div class="form-group">
                    <label for="number" class="col-sm-2  control-label">库存数量：</label>
                    <div class="col-sm-8">
                        <input type="text"  name="number" class="form-control" id="number" placeholder="请写入库存数量">
                    </div>
                </div>
                <div class="form-group">
                    <label for="startTime" class="col-sm-2  control-label">秒杀开始时间：</label>
                    <div class="col-sm-8">
                        <input type="text"  class="form-control" name="startTime" id="startTime">
                        <p class="help-block">开始时间需要大于当前时间并小于结束时间</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="endTime" class="col-sm-2  control-label">秒杀结束时间：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="endTime" id="endTime">
                        <p class="help-block">结束时间需要大于开始时间</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2  control-label"></label>
                    <div class="col-sm-8">
                        <button type="submit" class="btn btn-default" id="submit-btn">Submit</button>
                        <a class="btn btn-default" href="/seckill/list">返回秒杀列表</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
<%--<script type="text/javascript">--%>
    <%--$(function() {--%>
        <%--var $sbumit = $('#submit-btn');--%>
        <%--$submit.click(function() {--%>
            <%----%>
        <%--});--%>
    <%--});--%>
<%--//</script>--%>
</body>
</html>