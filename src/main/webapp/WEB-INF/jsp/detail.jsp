<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀商品详情页面</title>
    <%@include file="common/head.jsp" %>
</head>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery.cookie.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery.countdown.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/script/seckill.js"></script>

<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1>${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <span class="glyphicon glyphicon-time"></span>
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>

<div id="killPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>秒杀电话:
                </h3>
            </div>
        </div>

        <div class="modal-body">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2">
                    <input type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机号码" class="form-control">
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <span id="killPhoneMessage" class="glyphicon"></span>
            <button type="button" id="killPhoneBtn" class="btn btn-success">
                <span class="glyphicon glyphicon-phone"></span>
                提交
            </button>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function () {
    // 传入参数
    seckill.detail.init({
        seckillId: ${seckill.seckillId},
        startTime: ${seckill.startTime.time},
        endTime: ${seckill.endTime.time}
    })
})
</script>

</html>
