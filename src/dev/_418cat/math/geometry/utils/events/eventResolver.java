package dev._418cat.math.geometry.utils.events;

public interface eventResolver
{
	public void resolveQueue();

	public void addToQueue(Event<?> ev);
}