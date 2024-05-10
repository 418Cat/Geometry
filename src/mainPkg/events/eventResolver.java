package mainPkg.events;

public interface eventResolver
{
	public void resolveQueue();
	
	public void addToQueue(Event<?> ev);
}