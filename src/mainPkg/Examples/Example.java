package mainPkg.Examples;

public interface Example {
	
	public enum eventType
	{
		mouse(new int[2]), scroll(new int[1]), click(new int[2]);
		
		private int[] value;
		
		private eventType(int[] val)
		{
			this.value = val;
		}
		
		public void setValues(int[] val)
		{
			this.value = val;
		}
		
		public int[] getValues()
		{
			return value;
		}
	}
	
	public void resolveQueue();
	public void addToQueue(eventType ev);
	
	void draw();
	
}
