package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Stack;

public abstract interface DoubleStack
  extends Stack<Double>
{
  public abstract void push(double paramDouble);
  
  public abstract double popDouble();
  
  public abstract double topDouble();
  
  public abstract double peekDouble(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */