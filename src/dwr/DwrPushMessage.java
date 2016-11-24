package dwr;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContextFactory;

/**
 * 在dwr.xml中注册该类后，dwr将会自动生成js文件。前端页面引入js文件后javascript与后台java的函数能互相调用
 *
 */
public class DwrPushMessage {
	//前端js函数名称
	private static final String FORNT_METHOD_NAME = "receiveMessages";
	private static DwrPushMessage dwrPushMessage;
	

	public static void pushMessageToClient(String message) {
		if(dwrPushMessage == null){
			dwrPushMessage = new DwrPushMessage();
		}
		dwrPushMessage.pushMessage(message);
	}

	private void pushMessage(String message) {
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				return true;
			}
		}, new Runnable() {
			public void run() {
//				System.out.println("推送信息：" + message);
				// 调用前端页面receiveMessages函数
				ScriptSessions.addFunctionCall(FORNT_METHOD_NAME, message);
			}
		});
	}

	
}
