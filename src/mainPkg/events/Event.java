package mainPkg.events;

public interface Event<T>
{
	public void setValues(T ev);
	public T getValues();
}
