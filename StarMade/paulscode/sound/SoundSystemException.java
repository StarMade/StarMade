/*    */ package paulscode.sound;
/*    */ 
/*    */ public class SoundSystemException extends Exception
/*    */ {
/*    */   public static final int ERROR_NONE = 0;
/*    */   public static final int UNKNOWN_ERROR = 1;
/*    */   public static final int NULL_PARAMETER = 2;
/*    */   public static final int CLASS_TYPE_MISMATCH = 3;
/*    */   public static final int LIBRARY_NULL = 4;
/*    */   public static final int LIBRARY_TYPE = 5;
/* 69 */   private int myType = 1;
/*    */ 
/*    */   public SoundSystemException(String message)
/*    */   {
/* 76 */     super(message);
/*    */   }
/*    */ 
/*    */   public SoundSystemException(String message, int type)
/*    */   {
/* 86 */     super(message);
/* 87 */     this.myType = type;
/*    */   }
/*    */ 
/*    */   public int getType()
/*    */   {
/* 92 */     return this.myType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystemException
 * JD-Core Version:    0.6.2
 */