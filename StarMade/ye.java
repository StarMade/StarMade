/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.IntSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public class ye
/*     */ {
/*     */   private xN jdField_a_of_type_XN;
/*  72 */   private final Int2ObjectOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*  73 */   private final Int2ObjectOpenHashMap jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private Transform[] jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/*  83 */   private final HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*     */ 
/*     */   public final Transform[] a()
/*     */   {
/*  97 */     if (this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform == null) {
/*  98 */       this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform = new Transform[this.jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size()];
/*  99 */       for (int i = 0; i < this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++) {
/* 100 */         this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i] = new Transform();
/* 101 */         this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i].setIdentity();
/*     */       }
/* 103 */       if ((!jdField_b_of_type_Boolean) && (this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length != 29)) throw new AssertionError(this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length);
/*     */     }
/* 105 */     for (Map.Entry localEntry : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.entrySet()) {
/* 106 */       Transform localTransform = (Transform)this.jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get((Integer)localEntry.getKey());
/* 107 */       ((xN)localEntry.getValue()).a(localTransform);
/* 108 */       this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[((xN)localEntry.getValue()).jdField_a_of_type_Int].set(localTransform);
/*     */     }
/* 110 */     return this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/*     */   }
/*     */ 
/*     */   public final wL a(String paramString)
/*     */   {
/*     */     wL localwL;
/* 130 */     if ((
/* 130 */       localwL = (wL)this.jdField_a_of_type_JavaUtilHashMap.get(paramString)) == null)
/*     */     {
/* 131 */       throw new IllegalArgumentException("animation '" + paramString + "' not found in " + this.jdField_a_of_type_JavaUtilHashMap);
/*     */     }
/* 133 */     return localwL;
/*     */   }
/*     */ 
/*     */   public final Collection a()
/*     */   {
/* 164 */     return this.jdField_a_of_type_JavaUtilHashMap.keySet();
/*     */   }
/*     */ 
/*     */   public final xN a(String paramString)
/*     */   {
/* 171 */     return (xN)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(a(paramString));
/*     */   }
/*     */ 
/*     */   public final int a() {
/* 175 */     return this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size();
/*     */   }
/*     */ 
/*     */   public final int a(String paramString) {
/* 179 */     for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       xN localxN;
/* 180 */       if ((
/* 180 */         localxN = (xN)localIterator.next()).jdField_a_of_type_JavaLangString
/* 180 */         .equals(paramString)) {
/* 181 */         return localxN.jdField_a_of_type_Int;
/*     */       }
/*     */     }
/* 184 */     System.err.println("WARNING: bone not found in skeleton: " + paramString);
/* 185 */     if (!jdField_b_of_type_Boolean) throw new AssertionError();
/* 186 */     return -1;
/*     */   }
/*     */ 
/*     */   public final Int2ObjectOpenHashMap a()
/*     */   {
/* 195 */     return this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap;
/*     */   }
/*     */ 
/*     */   public final xN a()
/*     */   {
/* 204 */     return this.jdField_a_of_type_XN;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 209 */     ye localye = this;
/*     */     Integer localInteger;
/*     */     Transform localTransform;
/* 209 */     for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet().iterator(); localIterator.hasNext(); localye.jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(localInteger, localTransform)) { localInteger = (Integer)localIterator.next(); (localTransform = new Transform()).setIdentity();
/*     */     }
/* 211 */     this.jdField_a_of_type_XN.a(paramxq);
/*     */ 
/* 213 */     this.jdField_a_of_type_XN.e();
/*     */ 
/* 215 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 222 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 243 */     this.jdField_a_of_type_XN.d();
/*     */   }
/*     */ 
/*     */   public final void a(List paramList)
/*     */   {
/* 256 */     for (wL localwL : paramList)
/* 257 */       this.jdField_a_of_type_JavaUtilHashMap.put(localwL.jdField_a_of_type_JavaLangString, localwL);
/*     */   }
/*     */ 
/*     */   public final void a(xN paramxN)
/*     */   {
/* 267 */     this.jdField_a_of_type_XN = paramxN;
/*     */   }
/*     */ 
/*     */   public final void b(xq paramxq)
/*     */   {
/* 278 */     if (!this.jdField_a_of_type_Boolean) {
/* 279 */       a(paramxq);
/*     */     }
/* 281 */     this.jdField_a_of_type_XN.a(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ye
 * JD-Core Version:    0.6.2
 */