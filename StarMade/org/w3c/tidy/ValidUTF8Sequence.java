package org.w3c.tidy;

public class ValidUTF8Sequence
{
  int lowChar;
  int highChar;
  int numBytes;
  char[] validBytes = new char[8];
  
  public ValidUTF8Sequence(int paramInt1, int paramInt2, int paramInt3, char[] paramArrayOfChar)
  {
    this.lowChar = paramInt1;
    this.highChar = paramInt2;
    this.numBytes = paramInt3;
    this.validBytes = paramArrayOfChar;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.ValidUTF8Sequence
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */