/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Observable;
/*    */ import java.util.Observer;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public abstract class gz extends yz
/*    */   implements Observer
/*    */ {
/*    */   private int jdField_a_of_type_Int;
/*    */   private int jdField_b_of_type_Int;
/*    */   private yr jdField_a_of_type_Yr;
/*    */   private yP jdField_a_of_type_YP;
/*    */   private String jdField_b_of_type_JavaLangString;
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */ 
/*    */   public gz(ClientState paramClientState, int paramInt)
/*    */   {
/* 25 */     super(paramClientState);
/* 26 */     this.jdField_a_of_type_Int = paramInt;
/* 27 */     this.jdField_b_of_type_Int = 80;
/*    */ 
/* 29 */     ((ct)a()).a().addObserver(this);
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/* 34 */     ((ct)a()).a().deleteObserver(this);
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 39 */     if (this.jdField_a_of_type_Boolean) {
/* 40 */       this.jdField_b_of_type_JavaLangString = a();
/* 41 */       this.jdField_a_of_type_YP.b = new ArrayList();
/* 42 */       String[] arrayOfString = this.jdField_b_of_type_JavaLangString.split("\\\\n");
/* 43 */       this.jdField_a_of_type_YP.b.clear();
/* 44 */       for (int i = 0; i < arrayOfString.length; i++) {
/* 45 */         this.jdField_a_of_type_YP.b.add(arrayOfString[i]);
/*    */       }
/* 47 */       this.jdField_a_of_type_Boolean = false;
/*    */     }
/*    */ 
/* 50 */     k();
/*    */   }
/*    */ 
/*    */   public abstract String a();
/*    */ 
/*    */   public final void c()
/*    */   {
/* 57 */     this.jdField_a_of_type_Yr = new yr(a(), this.jdField_a_of_type_Int, this.jdField_b_of_type_Int);
/* 58 */     this.jdField_a_of_type_YP = new yP(this.jdField_a_of_type_Int, this.jdField_b_of_type_Int, a());
/*    */ 
/* 61 */     this.jdField_b_of_type_JavaLangString = a();
/* 62 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 63 */     String[] arrayOfString = this.jdField_b_of_type_JavaLangString.split("\\\\n");
/* 64 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 65 */       this.jdField_a_of_type_YP.b.add(arrayOfString[i]);
/*    */     }
/* 67 */     this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YP);
/*    */ 
/* 69 */     a(this.jdField_a_of_type_Yr);
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 76 */     return this.jdField_b_of_type_Int;
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 81 */     return this.jdField_a_of_type_Int;
/*    */   }
/*    */ 
/*    */   public void update(Observable paramObservable, Object paramObject)
/*    */   {
/* 86 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gz
 * JD-Core Version:    0.6.2
 */