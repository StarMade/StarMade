/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ElementHandler;
/*     */ import org.dom4j.ElementPath;
/*     */ 
/*     */ class DispatchHandler
/*     */   implements ElementHandler
/*     */ {
/*     */   private boolean atRoot;
/*     */   private String path;
/*     */   private ArrayList pathStack;
/*     */   private ArrayList handlerStack;
/*     */   private HashMap handlers;
/*     */   private ElementHandler defaultHandler;
/*     */ 
/*     */   public DispatchHandler()
/*     */   {
/*  58 */     this.atRoot = true;
/*  59 */     this.path = "/";
/*  60 */     this.pathStack = new ArrayList();
/*  61 */     this.handlerStack = new ArrayList();
/*  62 */     this.handlers = new HashMap();
/*     */   }
/*     */ 
/*     */   public void addHandler(String handlerPath, ElementHandler handler)
/*     */   {
/*  76 */     this.handlers.put(handlerPath, handler);
/*     */   }
/*     */ 
/*     */   public ElementHandler removeHandler(String handlerPath)
/*     */   {
/*  89 */     return (ElementHandler)this.handlers.remove(handlerPath);
/*     */   }
/*     */ 
/*     */   public boolean containsHandler(String handlerPath)
/*     */   {
/* 102 */     return this.handlers.containsKey(handlerPath);
/*     */   }
/*     */ 
/*     */   public ElementHandler getHandler(String handlerPath)
/*     */   {
/* 114 */     return (ElementHandler)this.handlers.get(handlerPath);
/*     */   }
/*     */ 
/*     */   public int getActiveHandlerCount()
/*     */   {
/* 124 */     return this.handlerStack.size();
/*     */   }
/*     */ 
/*     */   public void setDefaultHandler(ElementHandler handler)
/*     */   {
/* 137 */     this.defaultHandler = handler;
/*     */   }
/*     */ 
/*     */   public void resetHandlers()
/*     */   {
/* 145 */     this.atRoot = true;
/* 146 */     this.path = "/";
/* 147 */     this.pathStack.clear();
/* 148 */     this.handlerStack.clear();
/* 149 */     this.handlers.clear();
/* 150 */     this.defaultHandler = null;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 159 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void onStart(ElementPath elementPath)
/*     */   {
/* 164 */     Element element = elementPath.getCurrent();
/*     */ 
/* 167 */     this.pathStack.add(this.path);
/*     */ 
/* 170 */     if (this.atRoot) {
/* 171 */       this.path += element.getName();
/* 172 */       this.atRoot = false;
/*     */     } else {
/* 174 */       this.path = (this.path + "/" + element.getName());
/*     */     }
/*     */ 
/* 177 */     if ((this.handlers != null) && (this.handlers.containsKey(this.path)))
/*     */     {
/* 180 */       ElementHandler handler = (ElementHandler)this.handlers.get(this.path);
/* 181 */       this.handlerStack.add(handler);
/*     */ 
/* 184 */       handler.onStart(elementPath);
/*     */     }
/* 188 */     else if ((this.handlerStack.isEmpty()) && (this.defaultHandler != null)) {
/* 189 */       this.defaultHandler.onStart(elementPath);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onEnd(ElementPath elementPath)
/*     */   {
/* 195 */     if ((this.handlers != null) && (this.handlers.containsKey(this.path)))
/*     */     {
/* 198 */       ElementHandler handler = (ElementHandler)this.handlers.get(this.path);
/* 199 */       this.handlerStack.remove(this.handlerStack.size() - 1);
/*     */ 
/* 202 */       handler.onEnd(elementPath);
/*     */     }
/* 206 */     else if ((this.handlerStack.isEmpty()) && (this.defaultHandler != null)) {
/* 207 */       this.defaultHandler.onEnd(elementPath);
/*     */     }
/*     */ 
/* 212 */     this.path = ((String)this.pathStack.remove(this.pathStack.size() - 1));
/*     */ 
/* 214 */     if (this.pathStack.size() == 0)
/* 215 */       this.atRoot = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DispatchHandler
 * JD-Core Version:    0.6.2
 */