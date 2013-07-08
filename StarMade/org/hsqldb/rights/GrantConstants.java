package org.hsqldb.rights;

public abstract interface GrantConstants
{
  public static final int SELECT = 1;
  public static final int DELETE = 2;
  public static final int INSERT = 4;
  public static final int UPDATE = 8;
  public static final int USAGE = 16;
  public static final int EXECUTE = 32;
  public static final int REFERENCES = 64;
  public static final int TRIGGER = 128;
  public static final int ALL = 63;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rights.GrantConstants
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */