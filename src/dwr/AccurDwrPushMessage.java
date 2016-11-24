package dwr;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContextFactory;

/**
 * 精确推送代理类
 *
 */
public class AccurDwrPushMessage {
		//前端js函数名称
		private static final String FORNT_METHOD_NAME = "accurReceiveMessages";
		private static final String SESSION_ATTRIBUTE_NAME ="userId";
		private static AccurDwrPushMessage AccurDwrPushMessage;
		public static void pushMessageToClient(String id,String message) {
			if(AccurDwrPushMessage == null){
				AccurDwrPushMessage = new AccurDwrPushMessage();
			}
			AccurDwrPushMessage.pushMessage(id,message);
		}

		public void pushMessage(String userid, String message) {
	        final String userId = userid;
	        final String autoMessage = message;
	        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
	            public boolean match(ScriptSession session){
	                if (session.getAttribute(SESSION_ATTRIBUTE_NAME) == null){
	                	return false;                	
	                }
	                else{                	
	                    return (session.getAttribute(SESSION_ATTRIBUTE_NAME)).equals(userId);
	                }                	
	            }
	        }, new Runnable(){            
	            private ScriptBuffer script = new ScriptBuffer();            
	            public void run(){               
	                script.appendCall(FORNT_METHOD_NAME, autoMessage);                
	                Collection<ScriptSession> sessions = Browser.getTargetSessions();                
	                for (ScriptSession scriptSession : sessions){
	                   /* scriptSession.addScript(script);*/
	                    if (scriptSession.getAttribute(SESSION_ATTRIBUTE_NAME).equals(userId)) {
	                        scriptSession.addScript(script);
	                        System.out.println("pushMessage:"+scriptSession.getAttribute(SESSION_ATTRIBUTE_NAME));
	                    }
	                }
	            }
	        });
	    }}


