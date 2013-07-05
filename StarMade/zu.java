/*    */ import java.io.PrintStream;
/*    */ import org.lwjgl.opengl.GL20;
/*    */ import org.schema.schine.graphicsengine.core.GLException;
/*    */ 
/*    */ public final class zu extends zq
/*    */   implements xg
/*    */ {
/*    */   private zr jdField_a_of_type_Zr;
/*    */   private zs jdField_a_of_type_Zs;
/*    */   private zt jdField_a_of_type_Zt;
/*    */ 
/*    */   public zu(xi paramxi)
/*    */   {
/* 19 */     this.c = paramxi;
/*    */ 
/* 22 */     e();
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   private void e()
/*    */   {
/* 38 */     System.err.println("BLOOM SHADER INITIALIZING!!!");
/* 39 */     if (this.jdField_a_of_type_Xi != null) {
/* 40 */       this.jdField_a_of_type_Xi.a();
/*    */     }
/* 42 */     if (this.b != null) {
/* 43 */       this.b.a();
/*    */     }
/* 45 */     this.jdField_a_of_type_Xi = new xi(xm.b() / 2, xm.a() / 2);
/* 46 */     this.jdField_a_of_type_Xi.e();
/* 47 */     this.b = new xi(xm.b() / 2, xm.a() / 2);
/* 48 */     this.b.e();
/* 49 */     this.jdField_a_of_type_Zr = new zr(this.c, this.jdField_a_of_type_Xi, this);
/* 50 */     this.jdField_a_of_type_Zs = new zs(this.c, this.jdField_a_of_type_Xi, this.b);
/* 51 */     this.jdField_a_of_type_Zt = new zt(this.c, this.jdField_a_of_type_Xi, this.b);
/*    */   }
/*    */ 
/*    */   public final void b() {
/* 55 */     if (xm.b()) {
/*    */       try {
/* 57 */         e();
/*    */       }
/*    */       catch (GLException localGLException2)
/*    */       {
/*    */         GLException localGLException1;
/* 58 */         (
/* 59 */           localGLException1 = 
/* 61 */           localGLException2).printStackTrace();
/* 60 */         xm.a(localGLException1);
/*    */       }
/*    */     }
/* 63 */     d();
/* 64 */     this.jdField_a_of_type_Zr.b();
/* 65 */     this.jdField_a_of_type_Zs.b();
/* 66 */     this.jdField_a_of_type_Zt.b();
/*    */ 
/* 68 */     GL20.glUseProgram(0);
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zu
 * JD-Core Version:    0.6.2
 */