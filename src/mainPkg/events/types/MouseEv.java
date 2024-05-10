package mainPkg.events.types;

import mainPkg.events.Event;

public enum MouseEv implements Event<int[]>
{
	move(new int[2]),
	
	scroll(new int[1]),
	
	click(new int[2]),
	
	drag(new int[2]);
	
	private int[] values;
	
	private MouseEv(int[] value)
	{
		this.values = value;
	}

	@Override
	public void setValues(int[] ev)
	{
		this.values = (int[])ev;
	}

	@Override
	public int[] getValues()
	{
		return this.values;
	}
}

