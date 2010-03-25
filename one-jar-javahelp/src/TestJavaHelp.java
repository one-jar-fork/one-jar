import java.net.URL;

import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;

// One-Jar maven plugin issue: http://code.google.com/p/onejar-maven-plugin/issues/detail?id=16
public class TestJavaHelp {
	public static void main(String args[]) throws Exception {
		new TestJavaHelp().run();
	}
	
	public void run() throws Exception {

		ClassLoader cl = getClass().getClassLoader();
		
		URL hsURL = HelpSet.findHelpSet(cl, "IdeHelp");
		HelpSet hs = new HelpSet(null, hsURL);
		HelpBroker hb = hs.createHelpBroker();
		
		System.out.println("Help");
		new CSH.DisplayHelpFromSource( hb );
		hb.setDisplayed(true);

	}
}
