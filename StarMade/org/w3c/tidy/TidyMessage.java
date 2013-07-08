package org.w3c.tidy;

public final class TidyMessage
{
  private int line;
  private int column;
  private Level level;
  private String message;
  private int errorCode;
  
  public TidyMessage(int paramInt1, int paramInt2, int paramInt3, Level paramLevel, String paramString)
  {
    this.errorCode = paramInt1;
    this.line = paramInt2;
    this.column = paramInt3;
    this.level = paramLevel;
    this.message = paramString;
  }
  
  public int getErrorCode()
  {
    return this.errorCode;
  }
  
  public int getColumn()
  {
    return this.column;
  }
  
  public Level getLevel()
  {
    return this.level;
  }
  
  public int getLine()
  {
    return this.line;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public static final class Level
    implements Comparable
  {
    public static final Level SUMMARY = new Level(0);
    public static final Level INFO = new Level(1);
    public static final Level WARNING = new Level(2);
    public static final Level ERROR = new Level(3);
    private short code;
    
    private Level(int paramInt)
    {
      this.code = ((short)paramInt);
    }
    
    public short getCode()
    {
      return this.code;
    }
    
    public static Level fromCode(int paramInt)
    {
      switch (paramInt)
      {
      case 0: 
        return SUMMARY;
      case 1: 
        return INFO;
      case 2: 
        return WARNING;
      case 3: 
        return ERROR;
      }
      return null;
    }
    
    public int compareTo(Object paramObject)
    {
      return this.code - ((Level)paramObject).code;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof Level)) {
        return false;
      }
      return this.code == ((Level)paramObject).code;
    }
    
    public String toString()
    {
      switch (this.code)
      {
      case 0: 
        return "SUMMARY";
      case 1: 
        return "INFO";
      case 2: 
        return "WARNING";
      case 3: 
        return "ERROR";
      }
      return "?";
    }
    
    public int hashCode()
    {
      return super.hashCode();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.TidyMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */