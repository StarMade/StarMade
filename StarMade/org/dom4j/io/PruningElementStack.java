/*     */ package org.dom4j.io;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ElementHandler;
/*     */ 
/*     */ class PruningElementStack extends ElementStack
/*     */ {
/*     */   private ElementHandler elementHandler;
/*     */   private String[] path;
/*     */   private int matchingElementIndex;
/*     */ 
/*     */   public PruningElementStack(String[] path, ElementHandler elementHandler)
/*     */   {
/*  44 */     this.path = path;
/*  45 */     this.elementHandler = elementHandler;
/*  46 */     checkPath();
/*     */   }
/*     */ 
/*     */   public PruningElementStack(String[] path, ElementHandler elementHandler, int defaultCapacity)
/*     */   {
/*  51 */     super(defaultCapacity);
/*  52 */     this.path = path;
/*  53 */     this.elementHandler = elementHandler;
/*  54 */     checkPath();
/*     */   }
/*     */ 
/*     */   public Element popElement() {
/*  58 */     Element answer = super.popElement();
/*     */ 
/*  60 */     if ((this.lastElementIndex == this.matchingElementIndex) && (this.lastElementIndex >= 0))
/*     */     {
/*  67 */       if (validElement(answer, this.lastElementIndex + 1)) {
/*  68 */         Element parent = null;
/*     */ 
/*  70 */         for (int i = 0; i <= this.lastElementIndex; i++) {
/*  71 */           parent = this.stack[i];
/*     */ 
/*  73 */           if (!validElement(parent, i)) {
/*  74 */             parent = null;
/*     */ 
/*  76 */             break;
/*     */           }
/*     */         }
/*     */ 
/*  80 */         if (parent != null) {
/*  81 */           pathMatches(parent, answer);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  86 */     return answer;
/*     */   }
/*     */ 
/*     */   protected void pathMatches(Element parent, Element selectedNode) {
/*  90 */     this.elementHandler.onEnd(this);
/*  91 */     parent.remove(selectedNode);
/*     */   }
/*     */ 
/*     */   protected boolean validElement(Element element, int index) {
/*  95 */     String requiredName = this.path[index];
/*  96 */     String name = element.getName();
/*     */ 
/*  98 */     if (requiredName == name) {
/*  99 */       return true;
/*     */     }
/*     */ 
/* 102 */     if ((requiredName != null) && (name != null)) {
/* 103 */       return requiredName.equals(name);
/*     */     }
/*     */ 
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   private void checkPath() {
/* 110 */     if (this.path.length < 2) {
/* 111 */       throw new RuntimeException("Invalid path of length: " + this.path.length + " it must be greater than 2");
/*     */     }
/*     */ 
/* 115 */     this.matchingElementIndex = (this.path.length - 2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.PruningElementStack
 * JD-Core Version:    0.6.2
 */