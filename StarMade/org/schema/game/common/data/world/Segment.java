/*     */ package org.schema.game.common.data.world;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import jR;
/*     */ import jY;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import ld;
/*     */ import le;
/*     */ import o;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.schine.network.IdGen;
/*     */ import org.schema.schine.network.Identifiable;
/*     */ import q;
/*     */ 
/*     */ public abstract class Segment
/*     */   implements Identifiable
/*     */ {
/*     */   public float[] a;
/* 119 */   private int jdField_a_of_type_Int = 0;
/*     */   private int b;
/*     */   public SegmentData a;
/*     */   public final SegmentController a;
/*     */   public final q a;
/*     */   public final q b;
/*     */   public Object c;
/*     */   private o jdField_a_of_type_O;
/*     */   public float[] b;
/*     */   public float[] c;
/*     */   public short a;
/*     */   public boolean c;
/*     */ 
/*     */   public static q a(int paramInt1, int paramInt2, int paramInt3, q paramq)
/*     */   {
/*  90 */     paramq.b(ByteUtil.b(paramInt1), ByteUtil.b(paramInt2), ByteUtil.b(paramInt3));
/*  91 */     return paramq;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 113 */     for (paramArrayOfString = -64; paramArrayOfString < 64; paramArrayOfString++) {
/* 114 */       o localo = new o(); int i = paramArrayOfString; localo.b((byte)ByteUtil.d(0), (byte)ByteUtil.d(0), (byte)ByteUtil.d(i)); System.err.println("0, 0, " + paramArrayOfString + " -> " + localo);
/*     */     }
/*     */   }
/* 117 */   public Segment(SegmentController paramSegmentController) { this.jdField_a_of_type_ArrayOfFloat = new float[16];
/*     */ 
/* 139 */     new q();
/*     */ 
/* 141 */     this.jdField_c_of_type_JavaLangObject = new Object();
/*     */ 
/* 143 */     this.jdField_a_of_type_O = new o();
/*     */ 
/* 147 */     this.jdField_b_of_type_ArrayOfFloat = new float[3];
/* 148 */     this.jdField_c_of_type_ArrayOfFloat = new float[3];
/*     */ 
/* 157 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*     */ 
/* 161 */     this.jdField_b_of_type_Int = IdGen.getIndependentId();
/*     */ 
/* 163 */     this.jdField_a_of_type_Q = new q();
/* 164 */     this.jdField_b_of_type_Q = new q();
/*     */   }
/*     */ 
/*     */   public final boolean a(short paramShort, o paramo, int paramInt, boolean paramBoolean)
/*     */   {
/* 169 */     boolean bool = false;
/*     */ 
/* 171 */     if ((g()) && 
/* 172 */       (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null)) {
/* 173 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a()
/* 174 */         .assignData(this);
/*     */     }
/*     */ 
/* 178 */     SegmentData.getInfoIndex(paramo);
/* 179 */     if (!this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.contains(paramo))
/*     */     {
/* 183 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer();
/* 184 */       paramBoolean = !paramBoolean ? 0 : ElementInformation.defaultActive(paramShort);
/*     */ 
/* 190 */       this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.setInfoElement(paramo, paramShort, (byte)paramInt, paramBoolean, true);
/*     */ 
/* 194 */       bool = true;
/*     */     }
/*     */ 
/* 201 */     return bool;
/*     */   }
/*     */ 
/*     */   public abstract void a(boolean paramBoolean);
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 227 */     return this.jdField_b_of_type_Int == ((Segment)paramObject).jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final boolean a(q paramq, byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/* 236 */     return paramq.a(this.jdField_a_of_type_Q.jdField_a_of_type_Int + paramByte1, this.jdField_a_of_type_Q.jdField_b_of_type_Int + paramByte2, this.jdField_a_of_type_Q.c + paramByte3);
/*     */   }
/*     */ 
/*     */   public final q a(byte paramByte1, byte paramByte2, byte paramByte3, q paramq)
/*     */   {
/* 246 */     paramq.jdField_a_of_type_Int = (this.jdField_a_of_type_Q.jdField_a_of_type_Int + paramByte1);
/* 247 */     paramq.jdField_b_of_type_Int = (this.jdField_a_of_type_Q.jdField_b_of_type_Int + paramByte2);
/* 248 */     paramq.c = (this.jdField_a_of_type_Q.c + paramByte3);
/* 249 */     return paramq;
/*     */   }
/*     */ 
/*     */   public final q a(o paramo, q paramq)
/*     */   {
/* 258 */     return a(paramo.a, paramo.b, paramo.c, paramq);
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/* 276 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final SegmentController a()
/*     */   {
/* 376 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   }
/*     */ 
/*     */   public final SegmentData a()
/*     */   {
/* 384 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData;
/*     */   }
/*     */ 
/*     */   public final int b()
/*     */   {
/* 391 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final Vector3f a(Vector3f paramVector3f)
/*     */   {
/* 426 */     paramVector3f.set(this.jdField_a_of_type_Q.jdField_a_of_type_Int, this.jdField_a_of_type_Q.jdField_b_of_type_Int, this.jdField_a_of_type_Q.c);
/* 427 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis.transform(paramVector3f);
/* 428 */     paramVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().origin);
/* 429 */     return paramVector3f;
/*     */   }
/*     */ 
/*     */   public void a(le paramle)
/*     */   {
/* 443 */     if (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null)
/*     */     {
/* 445 */       System.err.println("[SEGMENT][" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] RECEIVED SEG PIECE: SEGMENT DATA WAS NULL!!!! " + g() + " " + this.jdField_a_of_type_Int);
/* 446 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a()
/* 447 */         .assignData(this);
/*     */     }
/*     */     int i;
/* 453 */     if (((
/* 453 */       i = this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.applySegmentData(paramle.a(this.jdField_a_of_type_O), paramle.a())) == 0) && 
/* 454 */       (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (paramle.a() == 291)) {
/* 455 */       System.err.println("[SREVER] FACTION BLOCK ADDED TO " + this + "; resetting faction !!!!!!!!!!!!!!");
/* 456 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setFactionId(0);
/*     */     }
/*     */ 
/* 459 */     if ((i == 4) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()))
/*     */     {
/* 461 */       if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 462 */         System.err.println("PIECE ACTIVE CHANGED");
/* 463 */         if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof ld)) {
/* 464 */           synchronized (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNeedsActiveUpdateClient()) {
/* 465 */             this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNeedsActiveUpdateClient().add(paramle);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 474 */     if (g()) {
/* 475 */       if (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null) {
/* 476 */         System.err.println("[SEGMENT][" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] SEGMENT DATA SET TO NULL BECAUSE ITS NOT EMPTY!!!!");
/* 477 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData);
/*     */       }
/*     */ 
/*     */     }
/* 481 */     else if ((paramle.a() == 122) && (paramle.b())) {
/* 482 */       int j = paramle.a();
/* 483 */       for (paramle = 0; paramle < 6; paramle++) {
/* 484 */         if ((org.schema.game.common.data.element.Element.SIDE_FLAG[paramle] & j) == org.schema.game.common.data.element.Element.SIDE_FLAG[paramle]) {
/* 485 */           a(org.schema.game.common.data.element.Element.DIRECTIONSb[paramle]);
/*     */         }
/*     */       }
/* 488 */       paramle = new o();
/* 489 */       int k = 0;
/* 490 */       for (int m = 0; m < 6; m++) {
/* 491 */         if ((org.schema.game.common.data.element.Element.SIDE_FLAG[m] & j) == org.schema.game.common.data.element.Element.SIDE_FLAG[m]) {
/* 492 */           k++;
/* 493 */           paramle.a(org.schema.game.common.data.element.Element.DIRECTIONSb[m]);
/*     */         }
/*     */       }
/* 496 */       if (k > 1)
/*     */       {
/* 498 */         a(paramle);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 504 */     a(true);
/*     */   }
/*     */ 
/*     */   public final boolean a(q paramq1, q paramq2)
/*     */   {
/* 510 */     if ((paramq1.jdField_a_of_type_Int < this.jdField_a_of_type_Q.jdField_a_of_type_Int) && (paramq1.jdField_b_of_type_Int < this.jdField_a_of_type_Q.jdField_b_of_type_Int) && (paramq1.c < this.jdField_a_of_type_Q.c) && (paramq2.jdField_a_of_type_Int > this.jdField_a_of_type_Q.jdField_a_of_type_Int + 16) && (paramq2.jdField_b_of_type_Int > this.jdField_a_of_type_Q.jdField_b_of_type_Int + 16) && (paramq2.c > this.jdField_a_of_type_Q.c + 16))
/*     */     {
/* 514 */       return !g();
/*     */     }
/* 516 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCollisionChecker().a(this, paramq1, paramq2);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 523 */     return this.jdField_a_of_type_Q.hashCode() + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getId();
/*     */   }
/*     */ 
/*     */   public final boolean g()
/*     */   {
/* 661 */     return ((this.jdField_a_of_type_Int == 0) && (!this.jdField_c_of_type_Boolean)) || (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null);
/*     */   }
/*     */ 
/*     */   public final boolean a(o paramo, boolean paramBoolean)
/*     */   {
/* 684 */     boolean bool1 = paramBoolean; paramBoolean = paramo; paramo = this; boolean bool2 = false; if (!paramo.g()) { bool2 = paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.contains(paramBoolean); paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.setInfoElement(paramBoolean, (short)0, bool1); } if ((paramo.g()) && (paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null)) paramo.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a(paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData); return bool2;
/*     */   }
/*     */ 
/*     */   private void a(o paramo)
/*     */   {
/* 712 */     if ((
/* 712 */       paramo = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentFromCache(this.jdField_a_of_type_Q.jdField_a_of_type_Int + paramo.a, this.jdField_a_of_type_Q.jdField_b_of_type_Int + paramo.b, this.jdField_a_of_type_Q.c + paramo.c)) != null)
/*     */     {
/* 713 */       paramo.a(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 729 */     this.jdField_b_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public static void d()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(SegmentData paramSegmentData)
/*     */   {
/* 746 */     synchronized (this.jdField_c_of_type_JavaLangObject) {
/* 747 */       this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData = paramSegmentData;
/* 748 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(int paramInt)
/*     */   {
/* 754 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 764 */     return "DATA:" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getId() + ":" + this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void a(float paramFloat1, Vector3f paramVector3f, float paramFloat2)
/*     */   {
/* 790 */     if (!g()) {
/* 791 */       (
/* 792 */         paramVector3f = new q(paramVector3f))
/* 792 */         .c(this.jdField_a_of_type_Q);
/* 793 */       this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.damage(paramFloat1, paramVector3f, paramFloat2);
/*     */     }
/*     */   }
/*     */ 
/* 797 */   public final void a(q paramq) { a(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.c); }
/*     */ 
/*     */   public final void a(int paramInt1, int paramInt2, int paramInt3) {
/* 800 */     this.jdField_a_of_type_Q.b(paramInt1, paramInt2, paramInt3);
/* 801 */     this.jdField_b_of_type_Q.b(ByteUtil.b(paramInt1), ByteUtil.b(paramInt2), ByteUtil.b(paramInt3));
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  30 */     Segment.class.desiredAssertionStatus();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.Segment
 * JD-Core Version:    0.6.2
 */