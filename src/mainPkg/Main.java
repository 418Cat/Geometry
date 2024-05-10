package mainPkg;
import mainPkg.Examples.*;

public class Main {
	
	public static int[] frameSize = new int[] {500, 500};
	
	public static Example ex = new Example5();
	
	public static void main(String[] args)
	{
		Frame.initFrame(frameSize[0], frameSize[1], 0, 0);
		
		while(true)
		{
			Frame.start();
			ex.draw();
			Frame.end();
			
			ex.resolveQueue();
		}
		
	}
}
