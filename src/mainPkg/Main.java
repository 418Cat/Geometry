package mainPkg;

import mainPkg.Examples.*;

public class Main {
	
	public static int[] frameSize = new int[] {1000, 1000};
	
	public static int MAX_BOUNCES = 10;	
	
	public static Example ex = new Example1();
	
	public static void main(String[] args) {
		
		Frame.initFrame(frameSize[0], frameSize[1], 1440, 1280);
		
	}
}
