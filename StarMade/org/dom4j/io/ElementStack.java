/*     */ package org.dom4j.io;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ElementHandler;
/*     */ import org.dom4j.ElementPath;
/*     */ 
/*     */ class ElementStack
/*     */   implements ElementPath
/*     */ {
/*     */   protected Element[] stack;
/*  30 */   protected int lastElementIndex = -1;
/*     */ 
/*  32 */   private DispatchHandler handler = null;
/*     */ 
/*     */   public ElementStack() {
/*  35 */     this(50);
/*     */   }
/*     */ 
/*     */   public ElementStack(int defaultCapacity) {
/*  39 */     this.stack = new Element[defaultCapacity];
/*     */   }
/*     */ 
/*     */   public void setDispatchHandler(DispatchHandler dispatchHandler) {
/*  43 */     this.handler = dispatchHandler;
/*     */   }
/*     */ 
/*     */   public DispatchHandler getDispatchHandler() {
/*  47 */     return this.handler;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*  55 */     this.lastElementIndex = -1;
/*     */   }
/*     */ 
/*     */   public Element peekElement()
/*     */   {
/*  65 */     if (this.lastElementIndex < 0) {
/*  66 */       return null;
/*     */     }
/*     */ 
/*  69 */     return this.stack[this.lastElementIndex];
/*     */   }
/*     */ 
/*     */   public Element popElement()
/*     */   {
/*  78 */     if (this.lastElementIndex < 0) {
/*  79 */       return null;
/*     */     }
/*     */ 
/*  82 */     return this.stack[(this.lastElementIndex--)];
/*     */   }
/*     */ 
/*     */   public void pushElement(Element element)
/*     */   {
/*  92 */     int length = this.stack.length;
/*     */ 
/*  94 */     if (++this.lastElementIndex >= length) {
/*  95 */       reallocate(length * 2);
/*     */     }
/*     */ 
/*  98 */     this.stack[this.lastElementIndex] = element;
/*     */   }
/*     */ 
/*     */   protected void reallocate(int size)
/*     */   {
/* 108 */     Element[] oldStack = this.stack;
/* 109 */     this.stack = new Element[size];
/* 110 */     System.arraycopy(oldStack, 0, this.stack, 0, oldStack.length);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 116 */     return this.lastElementIndex + 1;
/*     */   }
/*     */ 
/*     */   public Element getElement(int depth)
/*     */   {
/*     */     Element element;
/*     */     try {
/* 123 */       element = this.stack[depth];
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException e)
/*     */     {
/*     */       Element element;
/* 125 */       element = null;
/*     */     }
/*     */ 
/* 128 */     return element;
/*     */   }
/*     */ 
/*     */   public String getPath() {
/* 132 */     if (this.handler == null) {
/* 133 */       setDispatchHandler(new DispatchHandler());
/*     */     }
/*     */ 
/* 136 */     return this.handler.getPath();
/*     */   }
/*     */ 
/*     */   public Element getCurrent() {
/* 140 */     return peekElement();
/*     */   }
/*     */ 
/*     */   public void addHandler(String path, ElementHandler elementHandler) {
/* 144 */     this.handler.addHandler(getHandlerPath(path), elementHandler);
/*     */   }
/*     */ 
/*     */   public void removeHandler(String path) {
/* 148 */     this.handler.removeHandler(getHandlerPath(path));
/*     */   }
/*     */ 
/*     */   public boolean containsHandler(String path)
/*     */   {
/* 161 */     return this.handler.containsHandler(path);
/*     */   }
/*     */ 
/*     */   private String getHandlerPath(String path)
/*     */   {
/* 167 */     if (this.handler == null)
/* 168 */       setDispatchHandler(new DispatchHandler());
/*     */     String handlerPath;
/*     */     String handlerPath;
/* 171 */     if (path.startsWith("/")) {
/* 172 */       handlerPath = path;
/*     */     }
/*     */     else
/*     */     {
/*     */       String handlerPath;
/* 173 */       if (getPath().equals("/"))
/* 174 */         handlerPath = getPath() + path;
/*     */       else {
/* 176 */         handlerPath = getPath() + "/" + path;
/*     */       }
/*     */     }
/* 179 */     return handlerPath;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.ElementStack
 * JD-Core Version:    0.6.2
 */