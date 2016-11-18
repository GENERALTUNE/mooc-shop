<%@page contentType="text/html; charset=UTF-8"  language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel-heading">
                <h1>${seckill.name}</h1>
            </div>
        </div>
        <div class="panel-body">
            <h2 class="text-danger text-center">
                <%--显示time图标--%>
                <div class="glyphicon glyphicon-time"></div>
                <%--展示倒计时--%>
                <span class="glyphicon"  id="seckill-box"></span>
            </h2>
        </div>
        <div class="panel-footer text-center">
            <a class="btn btn-default"  href="/seckill/list">返回列表页</a>
        </div>
    </div>

    <%--登录弹出层, 输入电话--%>
    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>秒杀电话
                    </h3>
                </div>
                <div class="modal-body ">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text"  name="killPhone" id="killPhoneKey" placeholder="填手机号 ^o^" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <%--验证信息--%>
                    <span id="killPhoneMessage" class="glyphicon"></span>
                    <button type="button"  id="killPhoneBtn"  class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>Submit
                    </button>
                </div>
            </div>
        </div>
    </div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.cookie.js"></script>
<script src="/js/jquery.countdown.min.js"></script>
<%--开始编写交互逻辑--%>
<script src="/js/scripts/seckill.js"></script>
<script type="text/javascript">
    seckill.detail.init({
        seckillId : ${seckill.seckillId},
        startTime : ${seckill.startTime.time},//毫秒
        endTime : ${seckill.endTime.time}
    });
</script>
</body>
</html>