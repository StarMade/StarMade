package org.jaxen.saxpath;

public abstract interface Operator
{
  public static final int NO_OP = 0;
  public static final int EQUALS = 1;
  public static final int NOT_EQUALS = 2;
  public static final int LESS_THAN = 3;
  public static final int LESS_THAN_EQUALS = 4;
  public static final int GREATER_THAN = 5;
  public static final int GREATER_THAN_EQUALS = 6;
  public static final int ADD = 7;
  public static final int SUBTRACT = 8;
  public static final int MULTIPLY = 9;
  public static final int MOD = 10;
  public static final int DIV = 11;
  public static final int NEGATIVE = 12;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.Operator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */