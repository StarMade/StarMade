/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlledElementContainer;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ 
/*     */ public final class up extends uC
/*     */ {
/*  24 */   private int jdField_b_of_type_Int = 0;
/*     */   private final int jdField_c_of_type_Int;
/*  41 */   private q d = new q();
/*     */   private boolean a;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private boolean jdField_c_of_type_Boolean;
/*     */ 
/*     */   public up(q paramq1, uu[] paramArrayOfuu, q paramq2, q paramq3, byte paramByte)
/*     */   {
/*  32 */     super(paramq1, paramArrayOfuu, paramq2, paramq3, 8, paramByte);
/*     */ 
/*  35 */     this.jdField_c_of_type_Int = ((paramq3.a - paramq2.a) / 2 + (paramq3.jdField_b_of_type_Int - paramq2.jdField_b_of_type_Int) / 2 + (paramq3.jdField_c_of_type_Int - paramq2.jdField_c_of_type_Int) / 2 - 4);
/*     */   }
/*     */ 
/*     */   protected final short a(q paramq)
/*     */   {
/*  50 */     if (paramq.equals(this.jdField_c_of_type_Q)) {
/*  51 */       this.a = true;
/*  52 */       this.jdField_b_of_type_Int += 1;
/*  53 */       this.jdField_b_of_type_Boolean = ((!this.jdField_c_of_type_Boolean) && (this.jdField_b_of_type_Int >= this.jdField_c_of_type_Int) && (this.a));
/*  54 */       return 7;
/*     */     }
/*     */ 
/*  57 */     this.d.b(this.jdField_c_of_type_Q);
/*  58 */     q localq = org.schema.game.common.data.element.Element.DIRECTIONSi[a(this.jdField_c_of_type_Q)];
/*  59 */     this.d.a(localq);
/*     */ 
/*  61 */     if (((localq.a > 0) && (paramq.a > this.jdField_c_of_type_Q.a)) || ((localq.a < 0) && (paramq.a < this.jdField_c_of_type_Q.a)) || ((localq.jdField_b_of_type_Int > 0) && (paramq.jdField_b_of_type_Int > this.jdField_c_of_type_Q.jdField_b_of_type_Int)) || ((localq.jdField_b_of_type_Int < 0) && (paramq.jdField_b_of_type_Int < this.jdField_c_of_type_Q.jdField_b_of_type_Int)) || ((localq.jdField_c_of_type_Int > 0) && (paramq.jdField_c_of_type_Int > this.jdField_c_of_type_Q.jdField_c_of_type_Int)) || ((localq.jdField_c_of_type_Int < 0) && (paramq.jdField_c_of_type_Int < this.jdField_c_of_type_Q.jdField_c_of_type_Int)))
/*     */     {
/*  66 */       return 0;
/*     */     }
/*  68 */     this.d.b(this.jdField_c_of_type_Q);
/*  69 */     this.d.c(localq);
/*  70 */     if ((paramq.equals(this.d)) || ((paramq.a == this.d.a) && (paramq.jdField_b_of_type_Int == this.d.jdField_b_of_type_Int) && (paramq.jdField_c_of_type_Int != this.d.jdField_c_of_type_Int)) || ((paramq.a == this.d.a) && (paramq.jdField_c_of_type_Int == this.d.jdField_c_of_type_Int) && (paramq.jdField_b_of_type_Int != this.d.jdField_b_of_type_Int)) || ((paramq.jdField_c_of_type_Int == this.d.jdField_c_of_type_Int) && (paramq.jdField_b_of_type_Int == this.d.jdField_b_of_type_Int) && (paramq.a != this.d.a)))
/*     */     {
/*  77 */       this.a.addDelayed(new ControlledElementContainer(ElementCollection.getIndex(this.jdField_c_of_type_Q), new q(paramq), (short)88, true, true));
/*     */ 
/*  79 */       this.jdField_b_of_type_Int += 1;
/*  80 */       this.jdField_b_of_type_Boolean = ((!this.jdField_c_of_type_Boolean) && (this.jdField_b_of_type_Int >= this.jdField_c_of_type_Int) && (this.a));
/*     */ 
/*  85 */       return 88;
/*     */     }
/*     */ 
/*  89 */     return 32767;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 103 */     return this.jdField_b_of_type_Boolean;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     up
 * JD-Core Version:    0.6.2
 */