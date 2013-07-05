package org.w3c.tidy;

public abstract interface StreamIn
{
  public static final int END_OF_STREAM = -1;

  public abstract int getCurcol();

  public abstract int getCurline();

  public abstract int readCharFromStream();

  public abstract int readChar();

  public abstract void ungetChar(int paramInt);

  public abstract boolean isEndOfStream();

  public abstract void setLexer(Lexer paramLexer);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.StreamIn
 * JD-Core Version:    0.6.2
 */