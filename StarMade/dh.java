/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.SegmentDrawer;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ 
/*     */ final class dh
/*     */   implements jM
/*     */ {
/* 429 */   Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*     */   private dh(df paramdf) {  } 
/* 432 */   public final boolean handle(Segment paramSegment) { mr localmr = (mr)paramSegment;
/*     */ 
/* 434 */     synchronized (paramSegment.c) {
/* 435 */       if (((!localmr.g()) || (localmr.c)) && (localmr.a()))
/*     */       {
/* 440 */         if ((!jdField_a_of_type_Boolean) && (localmr.a().getSegment() != localmr)) throw new AssertionError();
/*     */ 
/* 442 */         if (SegmentDrawer.a(paramSegment.a(), localmr, this.jdField_a_of_type_JavaxVecmathVector3f, df.a(this.jdField_a_of_type_Df))) {
/* 443 */           this.jdField_a_of_type_Df.jdField_a_of_type_JavaUtilArrayList.add(localmr);
/* 444 */           this.jdField_a_of_type_Df.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.c += 1;
/*     */         }
/* 446 */         else if (localmr.b()) {
/* 447 */           df.a(this.jdField_a_of_type_Df).add(localmr);
/*     */         }
/*     */ 
/*     */       }
/* 454 */       else if (localmr.g()) {
/* 455 */         paramSegment = null; this.jdField_a_of_type_Df.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.a.i += 1L;
/*     */       }
/* 457 */       else if (localmr.b()) {
/* 458 */         df.a(this.jdField_a_of_type_Df).add(localmr);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 465 */     return !xm.a();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dh
 * JD-Core Version:    0.6.2
 */