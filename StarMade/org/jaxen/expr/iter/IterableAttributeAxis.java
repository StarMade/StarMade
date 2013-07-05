/*     */ package org.jaxen.expr.iter;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.NamedAccessNavigator;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class IterableAttributeAxis extends IterableAxis
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public IterableAttributeAxis(int value)
/*     */   {
/*  65 */     super(value);
/*     */   }
/*     */ 
/*     */   public Iterator iterator(Object contextNode, ContextSupport support)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  75 */     return support.getNavigator().getAttributeAxisIterator(contextNode);
/*     */   }
/*     */ 
/*     */   public Iterator namedAccessIterator(Object contextNode, ContextSupport support, String localName, String namespacePrefix, String namespaceURI)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  95 */     NamedAccessNavigator nav = (NamedAccessNavigator)support.getNavigator();
/*  96 */     return nav.getAttributeAxisIterator(contextNode, localName, namespacePrefix, namespaceURI);
/*     */   }
/*     */ 
/*     */   public boolean supportsNamedAccess(ContextSupport support)
/*     */   {
/* 106 */     return support.getNavigator() instanceof NamedAccessNavigator;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableAttributeAxis
 * JD-Core Version:    0.6.2
 */