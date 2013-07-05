/*    */ import java.io.PrintStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ 
/*    */ public final class dz extends dy
/*    */   implements wx
/*    */ {
/*    */   public final void handleKeyEvent()
/*    */   {
/*    */     q localq;
/* 24 */     (
/* 25 */       localq = new q(this.jdField_a_of_type_Q))
/* 25 */       .a(this.jdField_a_of_type_Al.a());
/* 26 */     if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b(localq)) {
/* 27 */       while ((this.jdField_a_of_type_Q.a() > 0.0F) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b(localq))) {
/* 28 */         this.jdField_a_of_type_Q.a /= 2;
/* 29 */         this.jdField_a_of_type_Q.b /= 2;
/* 30 */         this.jdField_a_of_type_Q.c /= 2;
/* 31 */         localq.b(this.jdField_a_of_type_Q);
/* 32 */         localq.a(this.jdField_a_of_type_Al.a());
/* 33 */         System.err.println("[CAM] SEARCHING anchor");
/*    */       }
/*    */     }
/* 36 */     int i = 1;
/* 37 */     Vector3f localVector3f = new Vector3f(localq.a, localq.b, localq.c);
/* 38 */     if (Keyboard.getEventKeyState()) {
/* 39 */       if (!Keyboard.isKeyDown(42)) {
/* 40 */         if (Keyboard.getEventKey() == cv.a.a())
/* 41 */           localVector3f.sub(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
/* 42 */         else if (Keyboard.getEventKey() == cv.b.a())
/* 43 */           localVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
/* 44 */         else if (Keyboard.getEventKey() == cv.c.a())
/* 45 */           localVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
/* 46 */         else if (Keyboard.getEventKey() == cv.d.a())
/* 47 */           localVector3f.sub(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
/* 48 */         else if (Keyboard.getEventKey() == cv.e.a())
/* 49 */           localVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
/* 50 */         else if (Keyboard.getEventKey() == cv.f.a())
/* 51 */           localVector3f.sub(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
/*    */         else
/* 53 */           i = 0;
/*    */       }
/*    */       else {
/* 56 */         i = 0;
/*    */       }
/*    */ 
/* 59 */       localq.b(Math.round(localVector3f.x), Math.round(localVector3f.y), Math.round(localVector3f.z));
/* 60 */       if (i != 0) {
/* 61 */         if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b(localq)) {
/* 62 */           localq.c(this.jdField_a_of_type_Al.a());
/* 63 */           System.err.println("EXISTS!. pos mod set to " + localq);
/* 64 */           this.jdField_a_of_type_Q.b(localq); return;
/*    */         }
/* 66 */         System.err.println("NOT EXISTS!. pos mod NOT set to " + localq);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dz
 * JD-Core Version:    0.6.2
 */