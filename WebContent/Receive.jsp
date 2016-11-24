<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>starting page</title>
<script type="text/javascript" src="./dwr/engine.js"></script>
<script type="text/javascript" src="./dwr/util.js"></script>
<script type="text/javascript" src="./dwr/interface/DwrPushMessage.js"></script>
<!-- 这三个js都是由dwr的servlet生成 -->
<!-- dwr根据dwr.xml的配置自动生成DwrPushMessage.js,是dwr生成的DwrPushMessage.java的前台代理类 -->
<!-- 需要注意的是，util.js中也运用$来操作dom，和jquery同时使用时存在多库共存问题，需要注意“$”符的控制权问题 -->
<script type="text/javascript">
    /* 接受消息方法的前台代理 函数*/
    function receiveMessages(messages) {
    	var lastMessage = messages;
    	console.log(messages);
    	var ul = document.getElementById("list");
    	var li = document.createElement("li");
    	li.innerHTML = lastMessage;
    	ul.appendChild(li);
    }
</script>
</head>
<!-- onload方法开启逆向Ajax -->
<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);">
    <h2>接收服务器广播信息</h2>
    <ul id="list"></ul>
</body>
</html>