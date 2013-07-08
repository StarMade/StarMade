/*  1:   */package paulscode.sound;
/*  2:   */
/* 12:   */public class SoundSystemException
/* 13:   */  extends Exception
/* 14:   */{
/* 15:   */  public static final int ERROR_NONE = 0;
/* 16:   */  
/* 24:   */  public static final int UNKNOWN_ERROR = 1;
/* 25:   */  
/* 33:   */  public static final int NULL_PARAMETER = 2;
/* 34:   */  
/* 42:   */  public static final int CLASS_TYPE_MISMATCH = 3;
/* 43:   */  
/* 51:   */  public static final int LIBRARY_NULL = 4;
/* 52:   */  
/* 60:   */  public static final int LIBRARY_TYPE = 5;
/* 61:   */  
/* 69:69 */  private int myType = 1;
/* 70:   */  
/* 74:   */  public SoundSystemException(String message)
/* 75:   */  {
/* 76:76 */    super(message);
/* 77:   */  }
/* 78:   */  
/* 84:   */  public SoundSystemException(String message, int type)
/* 85:   */  {
/* 86:86 */    super(message);
/* 87:87 */    this.myType = type;
/* 88:   */  }
/* 89:   */  
/* 90:   */  public int getType()
/* 91:   */  {
/* 92:92 */    return this.myType;
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystemException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */