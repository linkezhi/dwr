package dwr;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContextFactory;

/**
 * ��dwr.xml��ע������dwr�����Զ�����js�ļ���ǰ��ҳ������js�ļ���javascript���̨java�ĺ����ܻ������
 *
 */
public class DwrPushMessage {
	//ǰ��js��������
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
//				System.out.println("������Ϣ��" + message);
				// ����ǰ��ҳ��receiveMessages����
				ScriptSessions.addFunctionCall(FORNT_METHOD_NAME, message);
			}
		});
	}

	
}
