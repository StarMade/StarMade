/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class Dict
/*     */ {
/*     */   DictNode head;
/*     */   Object frame;
/*     */   DictLeq leq;
/*     */ 
/*     */   static Dict dictNewDict(Object frame, DictLeq leq)
/*     */   {
/*  96 */     Dict dict = new Dict();
/*  97 */     dict.head = new DictNode();
/*     */ 
/*  99 */     dict.head.key = null;
/* 100 */     dict.head.next = dict.head;
/* 101 */     dict.head.prev = dict.head;
/*     */ 
/* 103 */     dict.frame = frame;
/* 104 */     dict.leq = leq;
/*     */ 
/* 106 */     return dict;
/*     */   }
/*     */ 
/*     */   static void dictDeleteDict(Dict dict) {
/* 110 */     dict.head = null;
/* 111 */     dict.frame = null;
/* 112 */     dict.leq = null;
/*     */   }
/*     */ 
/*     */   static DictNode dictInsert(Dict dict, Object key) {
/* 116 */     return dictInsertBefore(dict, dict.head, key);
/*     */   }
/*     */ 
/*     */   static DictNode dictInsertBefore(Dict dict, DictNode node, Object key) {
/*     */     do
/* 121 */       node = node.prev;
/* 122 */     while ((node.key != null) && (!dict.leq.leq(dict.frame, node.key, key)));
/*     */ 
/* 124 */     DictNode newNode = new DictNode();
/* 125 */     newNode.key = key;
/* 126 */     newNode.next = node.next;
/* 127 */     node.next.prev = newNode;
/* 128 */     newNode.prev = node;
/* 129 */     node.next = newNode;
/*     */ 
/* 131 */     return newNode;
/*     */   }
/*     */ 
/*     */   static Object dictKey(DictNode aNode) {
/* 135 */     return aNode.key;
/*     */   }
/*     */ 
/*     */   static DictNode dictSucc(DictNode aNode) {
/* 139 */     return aNode.next;
/*     */   }
/*     */ 
/*     */   static DictNode dictPred(DictNode aNode) {
/* 143 */     return aNode.prev;
/*     */   }
/*     */ 
/*     */   static DictNode dictMin(Dict aDict) {
/* 147 */     return aDict.head.next;
/*     */   }
/*     */ 
/*     */   static DictNode dictMax(Dict aDict) {
/* 151 */     return aDict.head.prev;
/*     */   }
/*     */ 
/*     */   static void dictDelete(Dict dict, DictNode node) {
/* 155 */     node.next.prev = node.prev;
/* 156 */     node.prev.next = node.next;
/*     */   }
/*     */ 
/*     */   static DictNode dictSearch(Dict dict, Object key) {
/* 160 */     DictNode node = dict.head;
/*     */     do
/*     */     {
/* 163 */       node = node.next;
/* 164 */     }while ((node.key != null) && (!dict.leq.leq(dict.frame, key, node.key)));
/*     */ 
/* 166 */     return node;
/*     */   }
/*     */ 
/*     */   public static abstract interface DictLeq
/*     */   {
/*     */     public abstract boolean leq(Object paramObject1, Object paramObject2, Object paramObject3);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Dict
 * JD-Core Version:    0.6.2
 */