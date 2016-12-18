<%--
  Created by IntelliJ IDEA.
  User: zhumin
  Date: 2016/12/18
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="/upload/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file" />
        <input type="submit" value="上传" />
    </form>
</body>
</html>
