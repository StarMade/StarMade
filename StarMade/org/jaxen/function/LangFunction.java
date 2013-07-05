/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class LangFunction
/*     */   implements Function
/*     */ {
/*     */   private static final String LANG_LOCALNAME = "lang";
/*     */   private static final String XMLNS_URI = "http://www.w3.org/XML/1998/namespace";
/*     */ 
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 130 */     if (args.size() != 1) {
/* 131 */       throw new FunctionCallException("lang() requires exactly one argument.");
/*     */     }
/*     */ 
/* 134 */     Object arg = args.get(0);
/*     */     try
/*     */     {
/* 137 */       return evaluate(context.getNodeSet(), arg, context.getNavigator());
/*     */     }
/*     */     catch (UnsupportedAxisException e) {
/* 140 */       throw new FunctionCallException("Can't evaluate lang()", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static Boolean evaluate(List contextNodes, Object lang, Navigator nav)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 149 */     return evaluate(contextNodes.get(0), StringFunction.evaluate(lang, nav), nav) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ 
/*     */   private static boolean evaluate(Object node, String lang, Navigator nav)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 158 */     Object element = node;
/* 159 */     if (!nav.isElement(element)) {
/* 160 */       element = nav.getParentNode(node);
/*     */     }
/* 162 */     while ((element != null) && (nav.isElement(element)))
/*     */     {
/* 164 */       Iterator attrs = nav.getAttributeAxisIterator(element);
/* 165 */       while (attrs.hasNext())
/*     */       {
/* 167 */         Object attr = attrs.next();
/* 168 */         if (("lang".equals(nav.getAttributeName(attr))) && ("http://www.w3.org/XML/1998/namespace".equals(nav.getAttributeNamespaceUri(attr))))
/*     */         {
/* 171 */           return isSublang(nav.getAttributeStringValue(attr), lang);
/*     */         }
/*     */       }
/*     */ 
/* 175 */       element = nav.getParentNode(element);
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean isSublang(String sublang, String lang)
/*     */   {
/* 182 */     if (sublang.equalsIgnoreCase(lang))
/*     */     {
/* 184 */       return true;
/*     */     }
/* 186 */     int ll = lang.length();
/* 187 */     return (sublang.length() > ll) && (sublang.charAt(ll) == '-') && (sublang.substring(0, ll).equalsIgnoreCase(lang));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.LangFunction
 * JD-Core Version:    0.6.2
 */