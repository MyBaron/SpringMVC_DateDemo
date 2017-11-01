

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<body>
<h2>Hello World!</h2>

<form action="<%=basePath%>Test/form" method="post">
    <label >姓名
    <input type="text" name="name"/>
    </label>
    <label >Date
    <input type="text" name="Data"/>
    </label>
    <label >date
    <input type="text" name="date"/>
    </label>
    <label >Timestamp
    <input type="text" name="timestamp"/>
    </label>






    <button type="submit">提交</button>
</form>

</body>
</html>
