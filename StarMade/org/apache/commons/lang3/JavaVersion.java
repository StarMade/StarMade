/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ public enum JavaVersion
/*     */ {
/*  32 */   JAVA_0_9(1.5F, "0.9"), 
/*     */ 
/*  37 */   JAVA_1_1(1.1F, "1.1"), 
/*     */ 
/*  42 */   JAVA_1_2(1.2F, "1.2"), 
/*     */ 
/*  47 */   JAVA_1_3(1.3F, "1.3"), 
/*     */ 
/*  52 */   JAVA_1_4(1.4F, "1.4"), 
/*     */ 
/*  57 */   JAVA_1_5(1.5F, "1.5"), 
/*     */ 
/*  62 */   JAVA_1_6(1.6F, "1.6"), 
/*     */ 
/*  67 */   JAVA_1_7(1.7F, "1.7"), 
/*     */ 
/*  72 */   JAVA_1_8(1.8F, "1.8");
/*     */ 
/*     */   private float value;
/*     */   private String name;
/*     */ 
/*     */   private JavaVersion(float value, String name)
/*     */   {
/*  90 */     this.value = value;
/*  91 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public boolean atLeast(JavaVersion requiredVersion)
/*     */   {
/* 105 */     return this.value >= requiredVersion.value;
/*     */   }
/*     */ 
/*     */   static JavaVersion getJavaVersion(String nom)
/*     */   {
/* 119 */     return get(nom);
/*     */   }
/*     */ 
/*     */   static JavaVersion get(String nom)
/*     */   {
/* 132 */     if ("0.9".equals(nom))
/* 133 */       return JAVA_0_9;
/* 134 */     if ("1.1".equals(nom))
/* 135 */       return JAVA_1_1;
/* 136 */     if ("1.2".equals(nom))
/* 137 */       return JAVA_1_2;
/* 138 */     if ("1.3".equals(nom))
/* 139 */       return JAVA_1_3;
/* 140 */     if ("1.4".equals(nom))
/* 141 */       return JAVA_1_4;
/* 142 */     if ("1.5".equals(nom))
/* 143 */       return JAVA_1_5;
/* 144 */     if ("1.6".equals(nom))
/* 145 */       return JAVA_1_6;
/* 146 */     if ("1.7".equals(nom))
/* 147 */       return JAVA_1_7;
/* 148 */     if ("1.8".equals(nom)) {
/* 149 */       return JAVA_1_8;
/*     */     }
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 165 */     return this.name;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.JavaVersion
 * JD-Core Version:    0.6.2
 */