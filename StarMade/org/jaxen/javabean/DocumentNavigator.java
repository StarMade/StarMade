/*     */ package org.jaxen.javabean;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.jaxen.DefaultNavigator;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.NamedAccessNavigator;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.XPath;
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ import org.jaxen.util.SingleObjectIterator;
/*     */ 
/*     */ public class DocumentNavigator extends DefaultNavigator
/*     */   implements NamedAccessNavigator
/*     */ {
/*     */   private static final long serialVersionUID = -1768605107626726499L;
/*  72 */   private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
/*     */ 
/*  75 */   private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*     */ 
/*  79 */   private static final DocumentNavigator instance = new DocumentNavigator();
/*     */ 
/*     */   public static Navigator getInstance()
/*     */   {
/*  85 */     return instance;
/*     */   }
/*     */ 
/*     */   public boolean isElement(Object obj)
/*     */   {
/*  90 */     return obj instanceof Element;
/*     */   }
/*     */ 
/*     */   public boolean isComment(Object obj)
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isText(Object obj)
/*     */   {
/* 100 */     return obj instanceof String;
/*     */   }
/*     */ 
/*     */   public boolean isAttribute(Object obj)
/*     */   {
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isProcessingInstruction(Object obj)
/*     */   {
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isDocument(Object obj)
/*     */   {
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isNamespace(Object obj)
/*     */   {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   public String getElementName(Object obj)
/*     */   {
/* 125 */     return ((Element)obj).getName();
/*     */   }
/*     */ 
/*     */   public String getElementNamespaceUri(Object obj)
/*     */   {
/* 130 */     return "";
/*     */   }
/*     */ 
/*     */   public String getElementQName(Object obj)
/*     */   {
/* 135 */     return "";
/*     */   }
/*     */ 
/*     */   public String getAttributeName(Object obj)
/*     */   {
/* 140 */     return "";
/*     */   }
/*     */ 
/*     */   public String getAttributeNamespaceUri(Object obj)
/*     */   {
/* 145 */     return "";
/*     */   }
/*     */ 
/*     */   public String getAttributeQName(Object obj)
/*     */   {
/* 150 */     return "";
/*     */   }
/*     */ 
/*     */   public Iterator getChildAxisIterator(Object contextNode)
/*     */   {
/* 155 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/*     */   {
/* 173 */     Class cls = ((Element)contextNode).getObject().getClass();
/*     */ 
/* 175 */     String methodName = javacase(localName);
/*     */ 
/* 177 */     Method method = null;
/*     */     try
/*     */     {
/* 181 */       method = cls.getMethod("get" + methodName, EMPTY_CLASS_ARRAY);
/*     */     }
/*     */     catch (NoSuchMethodException e)
/*     */     {
/*     */       try
/*     */       {
/* 187 */         method = cls.getMethod("get" + methodName + "s", EMPTY_CLASS_ARRAY);
/*     */       }
/*     */       catch (NoSuchMethodException ee)
/*     */       {
/*     */         try
/*     */         {
/* 193 */           method = cls.getMethod(localName, EMPTY_CLASS_ARRAY);
/*     */         }
/*     */         catch (NoSuchMethodException eee)
/*     */         {
/* 197 */           method = null;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 202 */     if (method == null)
/*     */     {
/* 204 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 209 */       Object result = method.invoke(((Element)contextNode).getObject(), EMPTY_OBJECT_ARRAY);
/*     */ 
/* 211 */       if (result == null)
/*     */       {
/* 213 */         return JaxenConstants.EMPTY_ITERATOR;
/*     */       }
/*     */ 
/* 216 */       if ((result instanceof Collection))
/*     */       {
/* 218 */         return new ElementIterator((Element)contextNode, localName, ((Collection)result).iterator());
/*     */       }
/*     */ 
/* 221 */       if (result.getClass().isArray())
/*     */       {
/* 223 */         return JaxenConstants.EMPTY_ITERATOR;
/*     */       }
/*     */ 
/* 226 */       return new SingleObjectIterator(new Element((Element)contextNode, localName, result));
/*     */     }
/*     */     catch (IllegalAccessException e)
/*     */     {
/*     */     }
/*     */     catch (InvocationTargetException e)
/*     */     {
/*     */     }
/*     */ 
/* 237 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getParentAxisIterator(Object contextNode)
/*     */   {
/* 242 */     if ((contextNode instanceof Element))
/*     */     {
/* 244 */       return new SingleObjectIterator(((Element)contextNode).getParent());
/*     */     }
/*     */ 
/* 247 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object contextNode)
/*     */   {
/* 252 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/*     */   {
/* 269 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getNamespaceAxisIterator(Object contextNode)
/*     */   {
/* 274 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Object getDocumentNode(Object contextNode)
/*     */   {
/* 279 */     return null;
/*     */   }
/*     */ 
/*     */   public Object getParentNode(Object contextNode)
/*     */   {
/* 284 */     if ((contextNode instanceof Element))
/*     */     {
/* 286 */       return ((Element)contextNode).getParent();
/*     */     }
/*     */ 
/* 289 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public String getTextStringValue(Object obj)
/*     */   {
/* 294 */     if ((obj instanceof Element))
/*     */     {
/* 296 */       return ((Element)obj).getObject().toString();
/*     */     }
/* 298 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public String getElementStringValue(Object obj)
/*     */   {
/* 303 */     if ((obj instanceof Element))
/*     */     {
/* 305 */       return ((Element)obj).getObject().toString();
/*     */     }
/* 307 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public String getAttributeStringValue(Object obj)
/*     */   {
/* 312 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public String getNamespaceStringValue(Object obj)
/*     */   {
/* 317 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix(Object obj)
/*     */   {
/* 322 */     return null;
/*     */   }
/*     */ 
/*     */   public String getCommentStringValue(Object obj)
/*     */   {
/* 327 */     return null;
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String prefix, Object context)
/*     */   {
/* 332 */     return null;
/*     */   }
/*     */ 
/*     */   public short getNodeType(Object node)
/*     */   {
/* 337 */     return 0;
/*     */   }
/*     */ 
/*     */   public Object getDocument(String uri) throws FunctionCallException
/*     */   {
/* 342 */     return null;
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionTarget(Object obj)
/*     */   {
/* 347 */     return null;
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionData(Object obj)
/*     */   {
/* 352 */     return null;
/*     */   }
/*     */ 
/*     */   public XPath parseXPath(String xpath)
/*     */     throws SAXPathException
/*     */   {
/* 358 */     return new JavaBeanXPath(xpath);
/*     */   }
/*     */ 
/*     */   protected String javacase(String name)
/*     */   {
/* 363 */     if (name.length() == 0)
/*     */     {
/* 365 */       return name;
/*     */     }
/* 367 */     if (name.length() == 1)
/*     */     {
/* 369 */       return name.toUpperCase();
/*     */     }
/*     */ 
/* 372 */     return name.substring(0, 1).toUpperCase() + name.substring(1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.DocumentNavigator
 * JD-Core Version:    0.6.2
 */