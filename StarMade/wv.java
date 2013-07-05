/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class wv
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8643354607753925915L;
/*    */ 
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 75 */     return (paramObject != null) && (getClass().equals(paramObject.getClass()));
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 80 */     return getClass().hashCode();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 88 */     return getClass().getSimpleName();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wv
 * JD-Core Version:    0.6.2
 */