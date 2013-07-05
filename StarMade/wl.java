/*    */ import java.util.HashMap;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ 
/*    */ public abstract class wl
/*    */ {
/*    */   public final wo a;
/* 21 */   private final HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*    */   public ws a;
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */ 
/*    */   public wl(wo paramwo, boolean paramBoolean)
/*    */   {
/* 27 */     this.jdField_a_of_type_Wo = paramwo;
/*    */ 
/* 29 */     a(this.jdField_a_of_type_JavaUtilHashMap);
/* 30 */     this.jdField_a_of_type_Ws = ((ws)this.jdField_a_of_type_JavaUtilHashMap.get(a()));
/* 31 */     if ((!b) && (this.jdField_a_of_type_Ws == null)) throw new AssertionError();
/* 32 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*    */   }
/*    */ 
/*    */   protected abstract void a(HashMap paramHashMap);
/*    */ 
/*    */   protected abstract String a();
/*    */ 
/*    */   public StateInterface a()
/*    */   {
/* 42 */     return this.jdField_a_of_type_Wo.a();
/*    */   }
/*    */   public final ws a() {
/* 45 */     return this.jdField_a_of_type_Ws;
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq) {
/* 49 */     this.jdField_a_of_type_Wo.a(paramxq);
/*    */   }
/*    */ 
/*    */   public final boolean b() {
/* 53 */     return this.jdField_a_of_type_Boolean;
/*    */   }
/*    */ 
/*    */   public final void b(boolean paramBoolean) {
/* 57 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*    */   }
/*    */ 
/*    */   public static void g()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wl
 * JD-Core Version:    0.6.2
 */