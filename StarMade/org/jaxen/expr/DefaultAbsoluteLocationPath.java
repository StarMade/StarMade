/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.util.SingletonList;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class DefaultAbsoluteLocationPath extends DefaultLocationPath
/*     */ {
/*     */   private static final long serialVersionUID = 2174836928310146874L;
/*     */ 
/*     */   public String toString()
/*     */   {
/*  76 */     return "[(DefaultAbsoluteLocationPath): " + super.toString() + "]";
/*     */   }
/*     */ 
/*     */   public boolean isAbsolute()
/*     */   {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  86 */     return "/" + super.getText();
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/*  91 */     ContextSupport support = context.getContextSupport();
/*  92 */     Navigator nav = support.getNavigator();
/*  93 */     Context absContext = new Context(support);
/*  94 */     List contextNodes = context.getNodeSet();
/*     */ 
/*  96 */     if (contextNodes.isEmpty())
/*     */     {
/*  98 */       return Collections.EMPTY_LIST;
/*     */     }
/*     */ 
/* 101 */     Object firstNode = contextNodes.get(0);
/* 102 */     Object docNode = nav.getDocumentNode(firstNode);
/*     */ 
/* 104 */     if (docNode == null)
/*     */     {
/* 106 */       return Collections.EMPTY_LIST;
/*     */     }
/*     */ 
/* 109 */     List list = new SingletonList(docNode);
/*     */ 
/* 111 */     absContext.setNodeSet(list);
/*     */ 
/* 113 */     return super.evaluate(absContext);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAbsoluteLocationPath
 * JD-Core Version:    0.6.2
 */