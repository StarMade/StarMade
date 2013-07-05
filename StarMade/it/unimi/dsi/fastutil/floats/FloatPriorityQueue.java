package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.PriorityQueue;

public abstract interface FloatPriorityQueue extends PriorityQueue<Float>
{
  public abstract void enqueue(float paramFloat);

  public abstract float dequeueFloat();

  public abstract float firstFloat();

  public abstract float lastFloat();

  public abstract FloatComparator comparator();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatPriorityQueue
 * JD-Core Version:    0.6.2
 */