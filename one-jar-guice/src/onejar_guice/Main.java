package onejar_guice;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.simontuffs.onejar.test.Testable;

public class Main extends Testable {

    protected static Throwable thrown;
    
    static {
        Logger root = Logger.getLogger("");
        root.addHandler(new Handler() {
            @Override
            public void publish(LogRecord record) {
                thrown = record.getThrown();
                System.out.println("thrown=" + thrown);
            }
            @Override
            public void close() throws SecurityException {
            }
            @Override
            public void flush() {
            }
        });
        
    }
    
    public Main(){
	}
	
	public void sayHello(){
		System.out.println("Hello from Guice classloader=" + this.getClass().getClassLoader());
	}

    public static void main(String[] args) throws Throwable {
        Injector injector = Guice.createInjector();
        injector.getInstance(Main.class).sayHello();
        System.out.println("thrown=" + thrown);
        if (thrown != null) {
            throw thrown;
        }
    }

}
