/*     */ package org.jaxen.javabean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.BaseXPath;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ 
/*     */ public class JavaBeanXPath extends BaseXPath
/*     */ {
/*     */   private static final long serialVersionUID = -1567521943360266313L;
/*     */ 
/*     */   public JavaBeanXPath(String xpathExpr)
/*     */     throws JaxenException
/*     */   {
/*  94 */     super(xpathExpr, DocumentNavigator.getInstance());
/*     */   }
/*     */ 
/*     */   protected Context getContext(Object node)
/*     */   {
/*  99 */     if ((node instanceof Context))
/*     */     {
/* 101 */       return (Context)node;
/*     */     }
/*     */ 
/* 104 */     if ((node instanceof Element))
/*     */     {
/* 106 */       return super.getContext(node);
/*     */     }
/*     */ 
/* 109 */     if ((node instanceof List))
/*     */     {
/* 111 */       List newList = new ArrayList();
/*     */ 
/* 113 */       Iterator listIter = ((List)node).iterator();
/* 114 */       while (listIter.hasNext())
/*     */       {
/* 116 */         newList.add(new Element(null, "root", listIter.next()));
/*     */       }
/*     */ 
/* 119 */       return super.getContext(newList);
/*     */     }
/*     */ 
/* 122 */     return super.getContext(new Element(null, "root", node));
/*     */   }
/*     */ 
/*     */   public Object evaluate(Object node)
/*     */     throws JaxenException
/*     */   {
/* 128 */     Object result = super.evaluate(node);
/*     */ 
/* 130 */     if ((result instanceof Element))
/*     */     {
/* 132 */       return ((Element)result).getObject();
/*     */     }
/* 134 */     if ((result instanceof Collection))
/*     */     {
/* 136 */       List newList = new ArrayList();
/*     */ 
/* 138 */       Iterator listIter = ((Collection)result).iterator();
/* 139 */       while (listIter.hasNext())
/*     */       {
/* 141 */         Object member = listIter.next();
/*     */ 
/* 143 */         if ((member instanceof Element))
/*     */         {
/* 145 */           newList.add(((Element)member).getObject());
/*     */         }
/*     */         else
/*     */         {
/* 149 */           newList.add(member);
/*     */         }
/*     */       }
/*     */ 
/* 153 */       return newList;
/*     */     }
/*     */ 
/* 156 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.JavaBeanXPath
 * JD-Core Version:    0.6.2
 */