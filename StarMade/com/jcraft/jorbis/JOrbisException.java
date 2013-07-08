package com.jcraft.jorbis;

public class JOrbisException
  extends Exception
{
  public JOrbisException() {}
  
  public JOrbisException(String paramString)
  {
    super("JOrbis: " + paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.JOrbisException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */