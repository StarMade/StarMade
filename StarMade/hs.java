/*     */ final class hs
/*     */ {
/* 107 */   private String jdField_a_of_type_JavaLangString = "";
/* 108 */   private String b = "";
/*     */ 
/*     */   hs(hp paramhp) {  } 
/* 111 */   public final String toString() { int i = ((ct)this.jdField_a_of_type_Hp.a()).a().h();
/*     */     lP locallP;
/* 113 */     if (((
/* 113 */       locallP = ((ct)this.jdField_a_of_type_Hp.a()).a().a(i)) != null) && 
/* 113 */       (locallP.d().length() > 0)) {
/* 114 */       hp.a(this.jdField_a_of_type_Hp, locallP);
/*     */ 
/* 116 */       if (!this.jdField_a_of_type_JavaLangString.equals(locallP.d()))
/*     */       {
/* 118 */         this.b = ("Home Base: " + locallP.d().replaceFirst("ENTITY_", "").replaceFirst("SPACESTATION_", "").replaceFirst("PLANET_", "") + " at " + locallP.a().a + ", " + locallP.a().b + ", " + locallP.a().c);
/*     */ 
/* 121 */         this.jdField_a_of_type_JavaLangString = new String(locallP.d());
/*     */       }
/*     */ 
/* 124 */       return this.b;
/*     */     }
/* 126 */     hp.a(this.jdField_a_of_type_Hp, null);
/* 127 */     return "No Home Base";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hs
 * JD-Core Version:    0.6.2
 */