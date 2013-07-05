/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ 
/*     */ final class StateTracker
/*     */ {
/*     */   private ReferencesStack references_stack;
/*     */   private final StateStack attrib_stack;
/*     */   private boolean insideBeginEnd;
/*  48 */   private final FastIntMap<VAOState> vaoMap = new FastIntMap();
/*     */ 
/*     */   StateTracker() {
/*  51 */     this.attrib_stack = new StateStack(0);
/*     */   }
/*     */ 
/*     */   void init()
/*     */   {
/*  56 */     this.references_stack = new ReferencesStack();
/*     */   }
/*     */ 
/*     */   static void setBeginEnd(ContextCapabilities caps, boolean inside) {
/*  60 */     caps.tracker.insideBeginEnd = inside;
/*     */   }
/*     */ 
/*     */   boolean isBeginEnd() {
/*  64 */     return this.insideBeginEnd;
/*     */   }
/*     */ 
/*     */   static void popAttrib(ContextCapabilities caps) {
/*  68 */     caps.tracker.doPopAttrib();
/*     */   }
/*     */ 
/*     */   private void doPopAttrib() {
/*  72 */     this.references_stack.popState(this.attrib_stack.popState());
/*     */   }
/*     */ 
/*     */   static void pushAttrib(ContextCapabilities caps, int mask) {
/*  76 */     caps.tracker.doPushAttrib(mask);
/*     */   }
/*     */ 
/*     */   private void doPushAttrib(int mask) {
/*  80 */     this.attrib_stack.pushState(mask);
/*  81 */     this.references_stack.pushState();
/*     */   }
/*     */ 
/*     */   static References getReferences(ContextCapabilities caps) {
/*  85 */     return caps.tracker.references_stack.getReferences();
/*     */   }
/*     */ 
/*     */   static void bindBuffer(ContextCapabilities caps, int target, int buffer) {
/*  89 */     BaseReferences references = getReferences(caps);
/*  90 */     switch (target) {
/*     */     case 34962:
/*  92 */       references.arrayBuffer = buffer;
/*  93 */       break;
/*     */     case 34963:
/*  97 */       if (references.vertexArrayObject != 0)
/*  98 */         ((VAOState)caps.tracker.vaoMap.get(references.vertexArrayObject)).elementArrayBuffer = buffer;
/*     */       else
/* 100 */         references.elementArrayBuffer = buffer;
/* 101 */       break;
/*     */     case 35051:
/* 103 */       references.pixelPackBuffer = buffer;
/* 104 */       break;
/*     */     case 35052:
/* 106 */       references.pixelUnpackBuffer = buffer;
/* 107 */       break;
/*     */     case 36671:
/* 109 */       references.indirectBuffer = buffer;
/*     */     }
/*     */   }
/*     */ 
/*     */   static void bindVAO(ContextCapabilities caps, int array)
/*     */   {
/* 115 */     FastIntMap vaoMap = caps.tracker.vaoMap;
/* 116 */     if (!vaoMap.containsKey(array)) {
/* 117 */       vaoMap.put(array, new VAOState(null));
/*     */     }
/* 119 */     getReferences(caps).vertexArrayObject = array;
/*     */   }
/*     */ 
/*     */   static void deleteVAO(ContextCapabilities caps, IntBuffer arrays) {
/* 123 */     for (int i = arrays.position(); i < arrays.limit(); i++)
/* 124 */       deleteVAO(caps, arrays.get(i));
/*     */   }
/*     */ 
/*     */   static void deleteVAO(ContextCapabilities caps, int array) {
/* 128 */     caps.tracker.vaoMap.remove(array);
/*     */ 
/* 130 */     BaseReferences references = getReferences(caps);
/* 131 */     if (references.vertexArrayObject == array)
/* 132 */       references.vertexArrayObject = 0;
/*     */   }
/*     */ 
/*     */   static int getElementArrayBufferBound(ContextCapabilities caps)
/*     */   {
/* 143 */     BaseReferences references = getReferences(caps);
/*     */ 
/* 145 */     if (references.vertexArrayObject == 0) {
/* 146 */       return references.elementArrayBuffer;
/*     */     }
/* 148 */     return ((VAOState)caps.tracker.vaoMap.get(references.vertexArrayObject)).elementArrayBuffer;
/*     */   }
/*     */ 
/*     */   private static class VAOState
/*     */   {
/*     */     int elementArrayBuffer;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.StateTracker
 * JD-Core Version:    0.6.2
 */