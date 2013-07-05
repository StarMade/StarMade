/*     */ package org.jaxen;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Context
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2315979994685591055L;
/*     */   private ContextSupport contextSupport;
/*     */   private List nodeSet;
/*     */   private int size;
/*     */   private int position;
/*     */ 
/*     */   public Context(ContextSupport contextSupport)
/*     */   {
/*  99 */     this.contextSupport = contextSupport;
/* 100 */     this.nodeSet = Collections.EMPTY_LIST;
/* 101 */     this.size = 0;
/* 102 */     this.position = 0;
/*     */   }
/*     */ 
/*     */   public void setNodeSet(List nodeSet)
/*     */   {
/* 127 */     this.nodeSet = nodeSet;
/* 128 */     this.size = nodeSet.size();
/* 129 */     if (this.position >= this.size) this.position = 0;
/*     */   }
/*     */ 
/*     */   public List getNodeSet()
/*     */   {
/* 140 */     return this.nodeSet;
/*     */   }
/*     */ 
/*     */   public void setContextSupport(ContextSupport contextSupport)
/*     */   {
/* 149 */     this.contextSupport = contextSupport;
/*     */   }
/*     */ 
/*     */   public ContextSupport getContextSupport()
/*     */   {
/* 158 */     return this.contextSupport;
/*     */   }
/*     */ 
/*     */   public Navigator getNavigator()
/*     */   {
/* 167 */     return getContextSupport().getNavigator();
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String prefix)
/*     */   {
/* 178 */     return getContextSupport().translateNamespacePrefixToUri(prefix);
/*     */   }
/*     */ 
/*     */   public Object getVariableValue(String namespaceURI, String prefix, String localName)
/*     */     throws UnresolvableException
/*     */   {
/* 196 */     return getContextSupport().getVariableValue(namespaceURI, prefix, localName);
/*     */   }
/*     */ 
/*     */   public Function getFunction(String namespaceURI, String prefix, String localName)
/*     */     throws UnresolvableException
/*     */   {
/* 216 */     return getContextSupport().getFunction(namespaceURI, prefix, localName);
/*     */   }
/*     */ 
/*     */   public void setSize(int size)
/*     */   {
/* 231 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public int getSize()
/*     */   {
/* 240 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setPosition(int position)
/*     */   {
/* 249 */     this.position = position;
/*     */   }
/*     */ 
/*     */   public int getPosition()
/*     */   {
/* 258 */     return this.position;
/*     */   }
/*     */ 
/*     */   public Context duplicate()
/*     */   {
/* 271 */     Context dupe = new Context(getContextSupport());
/*     */ 
/* 273 */     List thisNodeSet = getNodeSet();
/*     */ 
/* 275 */     if (thisNodeSet != null)
/*     */     {
/* 277 */       List dupeNodeSet = new ArrayList(thisNodeSet.size());
/* 278 */       dupeNodeSet.addAll(thisNodeSet);
/* 279 */       dupe.setNodeSet(dupeNodeSet);
/* 280 */       dupe.setPosition(this.position);
/*     */     }
/*     */ 
/* 283 */     return dupe;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.Context
 * JD-Core Version:    0.6.2
 */