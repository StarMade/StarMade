/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import Ab;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap.FastEntrySet;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectMap.Entry;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectMap.FastEntrySet;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
/*     */ import ja;
/*     */ import java.io.DataOutputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import q;
/*     */ 
/*     */ public class ControlElementMapper extends Long2ObjectOpenHashMap
/*     */   implements Ab
/*     */ {
/*     */   private static final long serialVersionUID = 8953098951065383692L;
/*  28 */   private final Long2ObjectOpenHashMap all = new Long2ObjectOpenHashMap();
/*  29 */   private final Long2ObjectOpenHashMap controllers = new Long2ObjectOpenHashMap();
/*     */ 
/* 103 */   ja tmp = new ja();
/*     */ 
/*     */   public void writeToTag(DataOutputStream paramDataOutputStream)
/*     */   {
/*  26 */     ControlElementMap.serialize(paramDataOutputStream, this);
/*     */   }
/*     */ 
/*     */   public byte getFactoryId()
/*     */   {
/*  34 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean put(q paramq1, q paramq2, short paramShort) {
/*  38 */     long l = ElementCollection.getIndex(paramq1);
/*     */ 
/*  40 */     return put(l, paramq2, paramShort);
/*     */   }
/*     */ 
/*     */   public boolean put(long paramLong, q paramq, short paramShort) {
/*  44 */     assert (paramShort != 0);
/*  45 */     assert (paramShort != 32767);
/*     */     Object localObject;
/*  49 */     if (!containsKey(paramLong)) {
/*  50 */       localObject = new Short2ObjectOpenHashMap();
/*  51 */       put(paramLong, localObject);
/*     */     } else {
/*  53 */       localObject = (Short2ObjectOpenHashMap)get(paramLong);
/*     */     }
/*     */     ObjectOpenHashSet localObjectOpenHashSet;
/*  58 */     if (!((Short2ObjectOpenHashMap)localObject).containsKey(paramShort)) {
/*  59 */       localObjectOpenHashSet = new ObjectOpenHashSet();
/*  60 */       ((Short2ObjectOpenHashMap)localObject).put(paramShort, localObjectOpenHashSet);
/*     */     } else {
/*  62 */       localObjectOpenHashSet = (ObjectOpenHashSet)((Short2ObjectOpenHashMap)localObject).get(paramShort);
/*     */     }
/*     */ 
/*  66 */     paramq = new ja(paramq, paramShort);
/*     */ 
/*  69 */     if (!getAll().containsKey(paramLong)) {
/*  70 */       localObject = new ObjectOpenHashSet();
/*  71 */       getAll().put(paramLong, localObject);
/*     */     } else {
/*  73 */       localObject = (ObjectOpenHashSet)getAll().get(paramLong);
/*     */     }
/*  75 */     ((ObjectOpenHashSet)localObject).add(paramq);
/*     */ 
/*  77 */     if (!ElementKeyMap.getInfo(paramShort).getControlling().isEmpty())
/*     */     {
/*  79 */       if (!getControllers().containsKey(paramLong)) {
/*  80 */         paramShort = new ObjectOpenHashSet();
/*  81 */         getControllers().put(paramLong, paramShort);
/*     */       } else {
/*  83 */         paramShort = (ObjectOpenHashSet)getControllers().get(paramLong);
/*     */       }
/*  85 */       paramShort.add(paramq);
/*     */     }
/*     */ 
/*  90 */     return localObjectOpenHashSet.add(paramq);
/*     */   }
/*     */ 
/*     */   public Short2ObjectOpenHashMap remove(q paramq)
/*     */   {
/*  97 */     long l = ElementCollection.getIndex(paramq);
/*  98 */     getAll().remove(l);
/*  99 */     getControllers().remove(l);
/* 100 */     return (Short2ObjectOpenHashMap)super.remove(l);
/*     */   }
/*     */ 
/*     */   public boolean remove(q paramq1, q paramq2, short paramShort)
/*     */   {
/* 107 */     long l = ElementCollection.getIndex(paramq1);
/*     */ 
/* 109 */     return remove(l, paramq2, paramShort);
/*     */   }
/*     */ 
/*     */   public boolean remove(long paramLong, q paramq, short paramShort)
/*     */   {
/* 117 */     if ((containsKey(paramLong)) && (((Short2ObjectOpenHashMap)get(paramLong)).containsKey(paramShort)))
/*     */     {
/* 119 */       this.tmp.a(paramq, paramShort);
/* 120 */       ((ObjectOpenHashSet)getAll().get(paramLong)).remove(this.tmp);
/* 121 */       if (getControllers().containsKey(paramLong)) {
/* 122 */         ((ObjectOpenHashSet)getControllers().get(paramLong)).remove(this.tmp);
/*     */       }
/*     */ 
/* 125 */       return ((ObjectOpenHashSet)((Short2ObjectOpenHashMap)get(paramLong)).get(paramShort)).remove(paramq);
/*     */     }
/*     */ 
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */   public Long2ObjectOpenHashMap getAll()
/*     */   {
/* 134 */     return this.all;
/*     */   }
/*     */ 
/*     */   public Long2ObjectOpenHashMap getControllers()
/*     */   {
/* 141 */     return this.controllers;
/*     */   }
/*     */ 
/*     */   public void set(ControlElementMapper paramControlElementMapper)
/*     */   {
/* 146 */     for (paramControlElementMapper = paramControlElementMapper.long2ObjectEntrySet().iterator(); paramControlElementMapper.hasNext(); ) { localObject1 = (Long2ObjectMap.Entry)paramControlElementMapper.next();
/* 147 */       localObject2 = new Short2ObjectOpenHashMap();
/* 148 */       put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject2);
/*     */ 
/* 150 */       localObject3 = new ObjectOpenHashSet();
/* 151 */       this.all.put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject3);
/*     */ 
/* 156 */       for (localObject1 = ((Short2ObjectOpenHashMap)((Long2ObjectMap.Entry)localObject1).getValue()).short2ObjectEntrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject4 = (Short2ObjectMap.Entry)((Iterator)localObject1).next();
/* 157 */         localObjectOpenHashSet = new ObjectOpenHashSet();
/* 158 */         ((Short2ObjectOpenHashMap)localObject2).put(((Short2ObjectMap.Entry)localObject4).getShortKey(), localObjectOpenHashSet);
/* 159 */         for (localObject4 = ((ObjectOpenHashSet)((Short2ObjectMap.Entry)localObject4).getValue()).iterator(); ((Iterator)localObject4).hasNext(); ) { ja localja = (ja)((Iterator)localObject4).next();
/* 160 */           localja = new ja(localja);
/* 161 */           localObjectOpenHashSet.add(localja);
/* 162 */           ((ObjectOpenHashSet)localObject3).add(localja);
/*     */         }
/*     */       }
/*     */     }
/* 167 */     Object localObject1;
/*     */     Object localObject2;
/*     */     Object localObject3;
/*     */     Object localObject4;
/*     */     ObjectOpenHashSet localObjectOpenHashSet;
/* 167 */     for (paramControlElementMapper = this.controllers.long2ObjectEntrySet().iterator(); paramControlElementMapper.hasNext(); ) { localObject1 = (Long2ObjectMap.Entry)paramControlElementMapper.next();
/* 168 */       localObject2 = new ObjectOpenHashSet();
/* 169 */       this.controllers.put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject2);
/* 170 */       for (localObject3 = ((ObjectOpenHashSet)((Long2ObjectMap.Entry)localObject1).getValue()).iterator(); ((Iterator)localObject3).hasNext(); ) { localObject1 = (ja)((Iterator)localObject3).next();
/* 171 */         ((ObjectOpenHashSet)localObject2).add(new ja((ja)localObject1));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 180 */     this.all.clear();
/* 181 */     this.controllers.clear();
/* 182 */     super.clear();
/*     */   }
/*     */   public void clearAndTrim() {
/* 185 */     clear();
/* 186 */     this.all.trim();
/* 187 */     this.controllers.trim();
/* 188 */     trim();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMapper
 * JD-Core Version:    0.6.2
 */