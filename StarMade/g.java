/*    */ import java.io.InvalidObjectException;
/*    */ import java.util.logging.Level;
/*    */ 
/*    */ public final class g extends Level
/*    */ {
/*    */   private static final long serialVersionUID = 4998223571342726511L;
/* 21 */   public static Level a = new g("STDOUT", Level.INFO.intValue() + 53);
/*    */ 
/* 26 */   public static Level b = new g("STDERR", Level.INFO.intValue() + 54);
/*    */ 
/*    */   private g(String paramString, int paramInt)
/*    */   {
/* 32 */     super(paramString, paramInt);
/*    */   }
/*    */ 
/*    */   protected final Object readResolve()
/*    */   {
/* 44 */     if (intValue() == a.intValue()) {
/* 45 */       return a;
/*    */     }
/* 47 */     if (intValue() == b.intValue()) {
/* 48 */       return b;
/*    */     }
/* 50 */     throw new InvalidObjectException("Unknown instance :" + this);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     g
 * JD-Core Version:    0.6.2
 */