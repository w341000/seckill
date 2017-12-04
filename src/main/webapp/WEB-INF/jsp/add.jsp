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

<form action="${pageContext.request.contextPath}/seckill/add" method="post">
秒杀商品名称:<input type="text" name="name"><br>
库存:<input type="text" name="number" ><br>
秒杀开始时间:<input type="text" name="startTime"><br>
秒杀结束时间:<input type="text" name="endTime"><br>

<button type="submit" >保存商品</button>

</form>

</body>

</html>
