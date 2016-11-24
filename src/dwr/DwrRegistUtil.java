package dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;




/**
 *Ìí¼Ósession
 */
public class DwrRegistUtil {
	public void registerAccout(String userId) {
		ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
		scriptSession.setAttribute("userId", userId);
		Container container = ServerContextFactory.get().getContainer();
        ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
        ScriptSessionListener listener = new ScriptSessionListener() {
               public void sessionCreated(ScriptSessionEvent ev) {
                      HttpSession session = WebContextFactory.get().getSession();
                      String userId =session.getAttribute("userId")+"";
                      System.out.println("a ScriptSession is created!  userId:"+userId);
                      ev.getSession().setAttribute("userId", userId);
               }
               public void sessionDestroyed(ScriptSessionEvent ev) {
                      System.out.println("a ScriptSession is distroyed");
               }
        };
        manager.addScriptSessionListener(listener);
	}
}
