/*     */ package org.jaxen.expr.iter;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public abstract class IterableAxis
/*     */   implements Serializable
/*     */ {
/*     */   private int value;
/*     */ 
/*     */   public IterableAxis(int axisValue)
/*     */   {
/*  60 */     this.value = axisValue;
/*     */   }
/*     */ 
/*     */   public int value()
/*     */   {
/*  69 */     return this.value;
/*     */   }
/*     */ 
/*     */   public abstract Iterator iterator(Object paramObject, ContextSupport paramContextSupport)
/*     */     throws UnsupportedAxisException;
/*     */ 
/*     */   public Iterator namedAccessIterator(Object contextNode, ContextSupport support, String localName, String namespacePrefix, String namespaceURI)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  99 */     throw new UnsupportedOperationException("Named access unsupported");
/*     */   }
/*     */ 
/*     */   public boolean supportsNamedAccess(ContextSupport support)
/*     */   {
/* 109 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableAxis
 * JD-Core Version:    0.6.2
 */