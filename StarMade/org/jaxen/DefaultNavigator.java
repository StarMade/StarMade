/*     */ package org.jaxen;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.jaxen.util.AncestorAxisIterator;
/*     */ import org.jaxen.util.AncestorOrSelfAxisIterator;
/*     */ import org.jaxen.util.DescendantAxisIterator;
/*     */ import org.jaxen.util.DescendantOrSelfAxisIterator;
/*     */ import org.jaxen.util.FollowingAxisIterator;
/*     */ import org.jaxen.util.FollowingSiblingAxisIterator;
/*     */ import org.jaxen.util.PrecedingAxisIterator;
/*     */ import org.jaxen.util.PrecedingSiblingAxisIterator;
/*     */ import org.jaxen.util.SelfAxisIterator;
/*     */ 
/*     */ public abstract class DefaultNavigator
/*     */   implements Navigator
/*     */ {
/*     */   public Iterator getChildAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  95 */     throw new UnsupportedAxisException("child");
/*     */   }
/*     */ 
/*     */   public Iterator getDescendantAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 103 */     return new DescendantAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Iterator getParentAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 115 */     throw new UnsupportedAxisException("parent");
/*     */   }
/*     */ 
/*     */   public Iterator getAncestorAxisIterator(Object contextNode) throws UnsupportedAxisException
/*     */   {
/* 120 */     return new AncestorAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Iterator getFollowingSiblingAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 127 */     return new FollowingSiblingAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Iterator getPrecedingSiblingAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 134 */     return new PrecedingSiblingAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Iterator getFollowingAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 140 */     return new FollowingAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Iterator getPrecedingAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 149 */     return new PrecedingAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 164 */     throw new UnsupportedAxisException("attribute");
/*     */   }
/*     */ 
/*     */   public Iterator getNamespaceAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 176 */     throw new UnsupportedAxisException("namespace");
/*     */   }
/*     */ 
/*     */   public Iterator getSelfAxisIterator(Object contextNode) throws UnsupportedAxisException
/*     */   {
/* 181 */     return new SelfAxisIterator(contextNode);
/*     */   }
/*     */ 
/*     */   public Iterator getDescendantOrSelfAxisIterator(Object contextNode) throws UnsupportedAxisException
/*     */   {
/* 186 */     return new DescendantOrSelfAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Iterator getAncestorOrSelfAxisIterator(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 192 */     return new AncestorOrSelfAxisIterator(contextNode, this);
/*     */   }
/*     */ 
/*     */   public Object getDocumentNode(Object contextNode)
/*     */   {
/* 198 */     return null;
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String prefix, Object element)
/*     */   {
/* 203 */     return null;
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionTarget(Object obj)
/*     */   {
/* 208 */     return null;
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionData(Object obj)
/*     */   {
/* 213 */     return null;
/*     */   }
/*     */ 
/*     */   public short getNodeType(Object node)
/*     */   {
/* 218 */     if (isElement(node))
/*     */     {
/* 220 */       return 1;
/*     */     }
/* 222 */     if (isAttribute(node))
/*     */     {
/* 224 */       return 2;
/*     */     }
/* 226 */     if (isText(node))
/*     */     {
/* 228 */       return 3;
/*     */     }
/* 230 */     if (isComment(node))
/*     */     {
/* 232 */       return 8;
/*     */     }
/* 234 */     if (isDocument(node))
/*     */     {
/* 236 */       return 9;
/*     */     }
/* 238 */     if (isProcessingInstruction(node))
/*     */     {
/* 240 */       return 7;
/*     */     }
/* 242 */     if (isNamespace(node))
/*     */     {
/* 244 */       return 13;
/*     */     }
/*     */ 
/* 247 */     return 14;
/*     */   }
/*     */ 
/*     */   public Object getParentNode(Object contextNode)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 261 */     Iterator iter = getParentAxisIterator(contextNode);
/* 262 */     if ((iter != null) && (iter.hasNext()))
/*     */     {
/* 264 */       return iter.next();
/*     */     }
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */   public Object getDocument(String url)
/*     */     throws FunctionCallException
/*     */   {
/* 281 */     return null;
/*     */   }
/*     */ 
/*     */   public Object getElementById(Object contextNode, String elementId)
/*     */   {
/* 296 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.DefaultNavigator
 * JD-Core Version:    0.6.2
 */