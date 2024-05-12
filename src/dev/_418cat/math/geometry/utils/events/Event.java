package dev._418cat.math.geometry.utils.events;

public interface Event<T>
{
	public void setValues(T ev);

	public T getValues();
}
