package dwr;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.extend.ScriptSessionManager;

public class SessionCreateListen implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent ev) {
		new Thread(new PushMessageRunnable()).start();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent ev) {
	}

} 
class PushMessageRunnable implements Runnable {
	@Override
	public void run() {
		while (true) {			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DwrPushMessage.pushMessageToClient("来自服务端的数据:" + System.currentTimeMillis());
		}
	}
}
