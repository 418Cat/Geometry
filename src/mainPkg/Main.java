package mainPkg;

import mainPkg.Examples.*;

public class Main {
	
	public static int[] frameSize = new int[] {700, 700};
	
	public static Example ex = new Example2();
	
	public static void main(String[] args) {
		
		Frame.initFrame(frameSize[0], frameSize[1], 0, 0);
		
	}
}
