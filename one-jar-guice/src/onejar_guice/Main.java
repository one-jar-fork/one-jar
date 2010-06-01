package onejar_guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public Main(){
	}
	
	public void sayHello(){
		System.out.println("Hello from Guice classloader=" + this.getClass().getClassLoader());
	}

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        injector.getInstance(Main.class).sayHello();
    }

}
