/* Daniel Caruso
 * MainClass.java
 * 2/3/2015
 * */
public class MainClass {
	public static EventHandler eventController = new EventHandler();
	public static Environment worldController = new Environment();
	public static void main(String[] args){ //Everything is called right here
		 worldController.createMap();
		 System.out.println("You wake up in a dark room. Your head is pounding and you are very confused.");
		 eventController.printMainOptions();
	}
}
