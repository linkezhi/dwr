<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>starting page</title>
<script type="text/javascript" src="./dwr/engine.js"></script>
<script type="text/javascript" src="./dwr/util.js"></script>
<script type="text/javascript" src="./dwr/interface/DwrPushMessage.js"></script>
<script type="text/javascript" src="./dwr/interface/DwrRegistUtil.js"></script>
<script type="text/javascript" src="./dwr/interface/AccurDwrPushMessage.js"></script>
<!-- dwr根据dwr.xml的配置自动生成DwrPushMessage.js,是dwr生成的DwrPushMessage.java的前台代理类 -->
<!-- 需要注意的是，util.js中也运用$来操作dom，和jquery同时使用时存在多库共存问题，需要注意“$”符的控制权问题 -->
<script type="text/javascript">
var LocalUserId ;
//读取name值作为推送的唯一标示
function registed(){
    // 获取URL中的name属性为唯一标识符
    var account = document.getElementById("account").value;
    // 通过代理，传入区别本页面的唯一标识符
    DwrRegistUtil.registerAccout(account);
    document.getElementById("userID").innerHTML=account;
    LocalUserId = account;
 }
 
 function sendToReceive(){
	// 获取URL中的name属性为唯一标识符
	    var account = document.getElementById("recevierId").value;
	    // 通过代理，传入区别本页面的唯一标识符
	    var message = '${sessionScope.userId}';
	    AccurDwrPushMessage.pushMessage(account,"来自"+LocalUserId+"的信息");
 }
    /* 接受消息方法的前台代理 函数*/
    function receiveMessages(messages) {
    	var lastMessage = messages;
    	console.log(messages);
    	var ul = document.getElementById("list");
    	var li = document.createElement("li");
    	li.innerHTML = lastMessage;
    	ul.appendChild(li);
    } 
    
    /* 精确传送 接受消息方法的前台代理 */
    function accurReceiveMessages(messages) {
    	console.log(messages);
    	var lastMessage = messages;
    	var ul = document.getElementById("list2");
    	var li = document.createElement("li");
    	li.innerHTML = lastMessage;
    	ul.appendChild(li);
    	/* li.innerHTML = lastMessage;
    	ul.appendChild(li);
        for ( var data in messages) {
            messageList = "<div>" + dwr.util.escapeHtml(messages[data]) + "</div>" + messageList;
        }
        dwr.util.setValue("list2", messageList, {
            escapeHtml : false
        }); */
    }
</script>
</head>
<!-- onload方法开启逆向Ajax -->
<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);">
    <div>我的id为<b id="userID"></b></div>
    <input id="account" type="text" />
    <input type="button" onclick="registed()" value="注册"/>
    <br>
    接收者id:<input id="recevierId" type="text" />
    <input type="button" onclick="sendToReceive()" value="发送"/>
    </br>
    <div style="clear:both"></div>
   <div style="float:left; width:500px ;">
    <h2>接收服务器广播信息</h2>
    <ul id="list"></ul>
   </div style="float:left; width:500px ;">
    <div>
    <h2>接收客户端的单点信息</h2>
    <ul id="list2"></ul>
    </div>
</body>
</html>