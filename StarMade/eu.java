/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.nio.ShortBuffer;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ 
/*     */ public final class eu
/*     */ {
/*  34 */   private static ObjectArrayList jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*     */ 
/*  51 */   private final Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*     */ 
/*  54 */   private final et[] jdField_a_of_type_ArrayOfEt = new et[64];
/*     */   private int jdField_a_of_type_Int;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public final void a(ld paramld)
/*     */   {
/*  61 */     et localet = null; paramld = paramld; (localet = (et)jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(0)).jdField_a_of_type_Ld = paramld; paramld = jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty() ? new et(paramld) : localet;
/*  62 */     this.jdField_a_of_type_JavaUtilMap.put(paramld.jdField_a_of_type_Ld, paramld);
/*     */   }
/*     */ 
/*     */   public final void a(Int2ObjectOpenHashMap paramInt2ObjectOpenHashMap) {
/*  66 */     Iterator localIterator1 = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/*  67 */     while (localIterator1.hasNext()) {
/*  68 */       localObject = (et)localIterator1.next();
/*  69 */       if (!paramInt2ObjectOpenHashMap.containsKey(((et)localObject).jdField_a_of_type_Ld.a().getId()))
/*     */       {
/*  71 */         (localObject = localObject).jdField_a_of_type_Ld = null; jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localObject);
/*  72 */         localIterator1.remove();
/*     */       }
/*     */     }
/*     */ 
/*  76 */     for (Object localObject = paramInt2ObjectOpenHashMap.values().iterator(); ((Iterator)localObject).hasNext(); ) {
/*  77 */       if ((((
/*  77 */         paramInt2ObjectOpenHashMap = (mF)((Iterator)localObject).next()) instanceof ld)) && 
/*  78 */         (!this.jdField_a_of_type_JavaUtilMap.containsKey(paramInt2ObjectOpenHashMap))) {
/*  79 */         a((ld)paramInt2ObjectOpenHashMap);
/*     */       }
/*     */     }
/*     */ 
/*  83 */     for (int i = 0; i < this.jdField_a_of_type_Int; i++) {
/*  84 */       this.jdField_a_of_type_ArrayOfEt[i] = null;
/*     */     }
/*  86 */     this.jdField_a_of_type_Int = 0;
/*  87 */     for (Iterator localIterator2 = this.jdField_a_of_type_JavaUtilMap.values().iterator(); localIterator2.hasNext(); )
/*     */     {
/*  90 */       if ((
/*  90 */         paramInt2ObjectOpenHashMap = (et)localIterator2.next())
/*  90 */         .a())
/*  91 */         a(paramInt2ObjectOpenHashMap, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 104 */     if (this.jdField_a_of_type_Boolean)
/* 105 */       for (int i = 0; i < this.jdField_a_of_type_Int; i++)
/* 106 */         this.jdField_a_of_type_ArrayOfEt[i].b();
/*     */   }
/*     */ 
/*     */   public static void b()
/*     */   {
/*     */   }
/*     */ 
/*     */   private void a(et paramet, boolean paramBoolean)
/*     */   {
/* 130 */     if (paramBoolean)
/*     */     {
/* 132 */       if (this.jdField_a_of_type_Int < this.jdField_a_of_type_ArrayOfEt.length) {
/* 133 */         this.jdField_a_of_type_ArrayOfEt[this.jdField_a_of_type_Int] = paramet;
/* 134 */         this.jdField_a_of_type_Int += 1;
/*     */       }
/*     */ 
/*     */     }
/* 138 */     else if (this.jdField_a_of_type_Int < this.jdField_a_of_type_ArrayOfEt.length) {
/* 139 */       for (paramBoolean = false; paramBoolean < this.jdField_a_of_type_ArrayOfEt.length; paramBoolean++) {
/* 140 */         if (this.jdField_a_of_type_ArrayOfEt[paramBoolean] == paramet) {
/* 141 */           this.jdField_a_of_type_ArrayOfEt[paramBoolean] = this.jdField_a_of_type_ArrayOfEt[(this.jdField_a_of_type_Int - 1)];
/* 142 */           this.jdField_a_of_type_Int -= 1;
/* 143 */           break;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 149 */     this.jdField_a_of_type_Boolean = (this.jdField_a_of_type_Int > 0);
/*     */   }
/*     */ 
/*     */   public final void a(mr parammr)
/*     */   {
/*     */     ShortBuffer localShortBuffer;
/* 155 */     int i = (
/* 155 */       localShortBuffer = parammr.b().a)
/* 155 */       .position();
/*     */ 
/* 160 */     localShortBuffer.rewind();
/* 161 */     o localo = new o();
/* 162 */     new q();
/* 163 */     le localle = new le();
/*     */     et localet;
/* 166 */     if ((
/* 166 */       localet = (et)this.jdField_a_of_type_JavaUtilMap.get(parammr.a())) != null)
/*     */     {
/* 167 */       boolean bool1 = localet.a();
/* 168 */       Object localObject2 = parammr; Object localObject1 = localet; for (int m = 0; m < ((et)localObject1).jdField_a_of_type_EK.b(); m++) { ((et)localObject1).jdField_a_of_type_EK.a.a(m, ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f); if ((((Segment)localObject2).jdField_a_of_type_Q.jdField_a_of_type_Int <= ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.x + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.b <= ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.y + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.c <= ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.z + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.jdField_a_of_type_Int + 16 > ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.x + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.b + 16 > ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.y + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.c + 16 > ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.z + 8.0F)) { ((et)localObject1).jdField_a_of_type_EK.b(m); m--; }
/*     */       }
/* 170 */       for (int j = 0; j < i; j++) {
/* 171 */         SegmentData.getPositionFromIndex(localShortBuffer.get(), localo);
/*     */ 
/* 173 */         localle.a(parammr, localo);
/* 174 */         localObject2 = localle; (localObject1 = localet).jdField_a_of_type_EK.b(); localObject1 = ((le)localObject2).a(((et)localObject1).jdField_a_of_type_Q);
/*     */         eK localeK;
/* 174 */         (localeK = ((et)localObject1).jdField_a_of_type_EK).jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F); int k = localeK.a(localeK.jdField_a_of_type_JavaxVecmathVector3f, localeK.jdField_a_of_type_JavaxVecmathVector3f); localeK.a.a(k, ((q)localObject1).jdField_a_of_type_Int - kd.jdField_a_of_type_Q.jdField_a_of_type_Int, ((q)localObject1).b - kd.jdField_a_of_type_Q.b, ((q)localObject1).c - kd.jdField_a_of_type_Q.c); localeK.a.b(k, ((q)localObject1).jdField_a_of_type_Int, ((q)localObject1).b, ((q)localObject1).c);
/*     */       }
/* 176 */       boolean bool2 = localet.a();
/* 177 */       if ((bool1) && (!bool2))
/* 178 */         a(localet, false);
/* 179 */       else if ((!bool1) && (bool2)) {
/* 180 */         a(localet, true);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 186 */     localShortBuffer.rewind();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eu
 * JD-Core Version:    0.6.2
 */