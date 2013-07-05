/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*    */ 
/*    */ public final class ma extends lY
/*    */ {
/*    */   private String a;
/*    */   private String b;
/*    */ 
/*    */   public final long a()
/*    */   {
/* 18 */     return Math.abs(this.jdField_a_of_type_Int) * 2147483647 + Math.abs(this.jdField_b_of_type_Int);
/*    */   }
/*    */ 
/*    */   public final void a(int paramInt1, int paramInt2)
/*    */   {
/* 26 */     this.jdField_a_of_type_Int = paramInt1;
/* 27 */     this.jdField_b_of_type_Int = paramInt2;
/*    */   }
/*    */ 
/*    */   public final void a(String paramString1, int paramInt1, int paramInt2, byte paramByte, String paramString2) {
/* 31 */     this.jdField_a_of_type_Int = paramInt1;
/* 32 */     this.jdField_b_of_type_Int = paramInt2;
/* 33 */     this.jdField_a_of_type_Byte = paramByte;
/* 34 */     this.jdField_a_of_type_JavaLangString = paramString2;
/* 35 */     this.jdField_b_of_type_JavaLangString = paramString1;
/*    */   }
/*    */ 
/*    */   public final String a()
/*    */   {
/* 42 */     return this.jdField_a_of_type_JavaLangString;
/*    */   }
/*    */   public final RemoteStringArray a(NetworkObject paramNetworkObject) {
/* 45 */     (
/* 46 */       paramNetworkObject = new RemoteStringArray(5, paramNetworkObject))
/* 46 */       .set(0, String.valueOf(this.jdField_a_of_type_Int));
/* 47 */     paramNetworkObject.set(1, String.valueOf(this.jdField_b_of_type_Int));
/* 48 */     paramNetworkObject.set(2, String.valueOf(this.jdField_a_of_type_Byte));
/* 49 */     paramNetworkObject.set(3, this.jdField_a_of_type_JavaLangString);
/* 50 */     paramNetworkObject.set(4, this.jdField_b_of_type_JavaLangString);
/* 51 */     return paramNetworkObject;
/*    */   }
/*    */ 
/*    */   public final void fromTagStructure(Ad paramAd) {
/* 55 */     paramAd = (Ad[])paramAd.a();
/*    */ 
/* 57 */     this.jdField_a_of_type_Int = ((Integer)paramAd[0].a()).intValue();
/* 58 */     this.jdField_b_of_type_Int = ((Integer)paramAd[1].a()).intValue();
/* 59 */     this.jdField_a_of_type_Byte = ((Byte)paramAd[2].a()).byteValue();
/* 60 */     this.jdField_a_of_type_JavaLangString = ((String)paramAd[3].a());
/* 61 */     this.jdField_b_of_type_JavaLangString = ((String)paramAd[4].a());
/*    */   }
/*    */ 
/*    */   public final Ad toTagStructure() {
/* 65 */     return new Ad(Af.n, null, new Ad[] { new Ad(Af.d, null, Integer.valueOf(this.jdField_a_of_type_Int)), new Ad(Af.d, null, Integer.valueOf(this.jdField_b_of_type_Int)), new Ad(Af.b, null, Byte.valueOf(this.jdField_a_of_type_Byte)), new Ad(Af.i, null, this.jdField_a_of_type_JavaLangString), new Ad(Af.i, null, this.jdField_b_of_type_JavaLangString), new Ad(Af.a, null, null) });
/*    */   }
/*    */ 
/*    */   public final String b()
/*    */   {
/* 78 */     return this.jdField_b_of_type_JavaLangString;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 89 */     return "RelOffer[a=" + this.jdField_a_of_type_Int + ", b=" + this.jdField_b_of_type_Int + ", rel=" + a().name() + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ma
 * JD-Core Version:    0.6.2
 */