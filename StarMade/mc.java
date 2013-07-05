/*    */ public class mc
/*    */   implements Ag
/*    */ {
/* 13 */   private final long[] jdField_a_of_type_ArrayOfLong = { 0L, 0L, 0L, 0L, 2047L };
/* 14 */   private final String[] jdField_a_of_type_ArrayOfJavaLangString = { "Member 4th Rank", "Member 3rd Rank", "Member 2rd Rank", "Member 1st Rank", "Founder" };
/*    */   public int a;
/*    */ 
/*    */   public String getUniqueIdentifier()
/*    */   {
/* 22 */     return null;
/*    */   }
/*    */ 
/*    */   public boolean isVolatile()
/*    */   {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   public void fromTagStructure(Ad paramAd)
/*    */   {
/* 32 */     if (paramAd.a().equals("0")) {
/* 33 */       paramAd = (Ad[])paramAd.a();
/* 34 */       this.jdField_a_of_type_Int = ((Integer)paramAd[0].a()).intValue();
/* 35 */       Ad[] arrayOfAd = (Ad[])paramAd[1].a();
/* 36 */       paramAd = (Ad[])paramAd[2].a();
/* 37 */       for (int i = 0; i < 5; i++) {
/* 38 */         this.jdField_a_of_type_ArrayOfLong[i] = ((Long)arrayOfAd[i].a()).longValue();
/* 39 */         this.jdField_a_of_type_ArrayOfJavaLangString[i] = ((String)paramAd[i].a());
/*    */       }
/* 41 */       return;
/* 42 */     }if (!jdField_a_of_type_Boolean) throw new AssertionError();
/*    */   }
/*    */ 
/*    */   public Ad toTagStructure()
/*    */   {
/* 47 */     Ad localAd1 = new Ad(Af.n, null, new Ad[] { new Ad(Af.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[0])), new Ad(Af.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[1])), new Ad(Af.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[2])), new Ad(Af.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[3])), new Ad(Af.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[4])), new Ad(Af.a, null, null) });
/*    */ 
/* 56 */     Ad localAd2 = new Ad(Af.n, null, new Ad[] { new Ad(Af.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[0]), new Ad(Af.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[1]), new Ad(Af.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[2]), new Ad(Af.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[3]), new Ad(Af.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[4]), new Ad(Af.a, null, null) });
/*    */ 
/* 66 */     return new Ad(Af.n, "0", new Ad[] { new Ad(Af.d, null, Integer.valueOf(this.jdField_a_of_type_Int)), localAd1, localAd2, new Ad(Af.a, null, null) });
/*    */   }
/*    */ 
/*    */   public final long[] a()
/*    */   {
/* 72 */     return this.jdField_a_of_type_ArrayOfLong;
/*    */   }
/*    */ 
/*    */   public final String[] a()
/*    */   {
/* 78 */     return this.jdField_a_of_type_ArrayOfJavaLangString;
/*    */   }
/*    */   public final boolean a(int paramInt) {
/* 81 */     return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 1L) == 1L;
/*    */   }
/*    */   public final boolean b(int paramInt) {
/* 84 */     return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 0x2) == 2L;
/*    */   }
/*    */   public final boolean c(int paramInt) {
/* 87 */     return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 0x4) == 4L;
/*    */   }
/*    */   public final boolean d(int paramInt) {
/* 90 */     return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 0x8) == 8L;
/*    */   }
/*    */   public final void a(mc parammc) {
/* 93 */     for (int i = 0; i < 5; i++) {
/* 94 */       if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Int != parammc.jdField_a_of_type_Int)) throw new AssertionError();
/* 95 */       this.jdField_a_of_type_ArrayOfLong[i] = parammc.jdField_a_of_type_ArrayOfLong[i];
/* 96 */       this.jdField_a_of_type_ArrayOfJavaLangString[i] = parammc.jdField_a_of_type_ArrayOfJavaLangString[i];
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mc
 * JD-Core Version:    0.6.2
 */