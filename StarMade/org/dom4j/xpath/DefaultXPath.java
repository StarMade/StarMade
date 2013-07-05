/*     */ package org.dom4j.xpath;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.InvalidXPathException;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.NodeFilter;
/*     */ import org.dom4j.XPathException;
/*     */ import org.jaxen.FunctionContext;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.NamespaceContext;
/*     */ import org.jaxen.SimpleNamespaceContext;
/*     */ import org.jaxen.VariableContext;
/*     */ import org.jaxen.dom4j.Dom4jXPath;
/*     */ 
/*     */ public class DefaultXPath
/*     */   implements org.dom4j.XPath, NodeFilter, Serializable
/*     */ {
/*     */   private String text;
/*     */   private org.jaxen.XPath xpath;
/*     */   private NamespaceContext namespaceContext;
/*     */ 
/*     */   public DefaultXPath(String text)
/*     */     throws InvalidXPathException
/*     */   {
/*  58 */     this.text = text;
/*  59 */     this.xpath = parse(text);
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  63 */     return "[XPath: " + this.xpath + "]";
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  74 */     return this.text;
/*     */   }
/*     */ 
/*     */   public FunctionContext getFunctionContext() {
/*  78 */     return this.xpath.getFunctionContext();
/*     */   }
/*     */ 
/*     */   public void setFunctionContext(FunctionContext functionContext) {
/*  82 */     this.xpath.setFunctionContext(functionContext);
/*     */   }
/*     */ 
/*     */   public NamespaceContext getNamespaceContext() {
/*  86 */     return this.namespaceContext;
/*     */   }
/*     */ 
/*     */   public void setNamespaceURIs(Map map) {
/*  90 */     setNamespaceContext(new SimpleNamespaceContext(map));
/*     */   }
/*     */ 
/*     */   public void setNamespaceContext(NamespaceContext namespaceContext) {
/*  94 */     this.namespaceContext = namespaceContext;
/*  95 */     this.xpath.setNamespaceContext(namespaceContext);
/*     */   }
/*     */ 
/*     */   public VariableContext getVariableContext() {
/*  99 */     return this.xpath.getVariableContext();
/*     */   }
/*     */ 
/*     */   public void setVariableContext(VariableContext variableContext) {
/* 103 */     this.xpath.setVariableContext(variableContext);
/*     */   }
/*     */ 
/*     */   public Object evaluate(Object context) {
/*     */     try {
/* 108 */       setNSContext(context);
/*     */ 
/* 110 */       List answer = this.xpath.selectNodes(context);
/*     */ 
/* 112 */       if ((answer != null) && (answer.size() == 1)) {
/* 113 */         return answer.get(0);
/*     */       }
/*     */ 
/* 116 */       return answer;
/*     */     } catch (JaxenException e) {
/* 118 */       handleJaxenException(e);
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   public Object selectObject(Object context)
/*     */   {
/* 125 */     return evaluate(context);
/*     */   }
/*     */ 
/*     */   public List selectNodes(Object context) {
/*     */     try {
/* 130 */       setNSContext(context);
/*     */ 
/* 132 */       return this.xpath.selectNodes(context);
/*     */     } catch (JaxenException e) {
/* 134 */       handleJaxenException(e);
/*     */     }
/* 136 */     return Collections.EMPTY_LIST;
/*     */   }
/*     */ 
/*     */   public List selectNodes(Object context, org.dom4j.XPath sortXPath)
/*     */   {
/* 141 */     List answer = selectNodes(context);
/* 142 */     sortXPath.sort(answer);
/*     */ 
/* 144 */     return answer;
/*     */   }
/*     */ 
/*     */   public List selectNodes(Object context, org.dom4j.XPath sortXPath, boolean distinct)
/*     */   {
/* 149 */     List answer = selectNodes(context);
/* 150 */     sortXPath.sort(answer, distinct);
/*     */ 
/* 152 */     return answer;
/*     */   }
/*     */ 
/*     */   public Node selectSingleNode(Object context) {
/*     */     try {
/* 157 */       setNSContext(context);
/*     */ 
/* 159 */       Object answer = this.xpath.selectSingleNode(context);
/*     */ 
/* 161 */       if ((answer instanceof Node)) {
/* 162 */         return (Node)answer;
/*     */       }
/*     */ 
/* 165 */       if (answer == null) {
/* 166 */         return null;
/*     */       }
/*     */ 
/* 169 */       throw new XPathException("The result of the XPath expression is not a Node. It was: " + answer + " of type: " + answer.getClass().getName());
/*     */     }
/*     */     catch (JaxenException e)
/*     */     {
/* 173 */       handleJaxenException(e);
/*     */     }
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */   public String valueOf(Object context)
/*     */   {
/*     */     try {
/* 181 */       setNSContext(context);
/*     */ 
/* 183 */       return this.xpath.stringValueOf(context);
/*     */     } catch (JaxenException e) {
/* 185 */       handleJaxenException(e);
/*     */     }
/* 187 */     return "";
/*     */   }
/*     */ 
/*     */   public Number numberValueOf(Object context)
/*     */   {
/*     */     try {
/* 193 */       setNSContext(context);
/*     */ 
/* 195 */       return this.xpath.numberValueOf(context);
/*     */     } catch (JaxenException e) {
/* 197 */       handleJaxenException(e);
/*     */     }
/* 199 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean booleanValueOf(Object context)
/*     */   {
/*     */     try {
/* 205 */       setNSContext(context);
/*     */ 
/* 207 */       return this.xpath.booleanValueOf(context);
/*     */     } catch (JaxenException e) {
/* 209 */       handleJaxenException(e);
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   public void sort(List list)
/*     */   {
/* 225 */     sort(list, false);
/*     */   }
/*     */ 
/*     */   public void sort(List list, boolean distinct)
/*     */   {
/* 241 */     if ((list != null) && (!list.isEmpty())) {
/* 242 */       int size = list.size();
/* 243 */       HashMap sortValues = new HashMap(size);
/*     */ 
/* 245 */       for (int i = 0; i < size; i++) {
/* 246 */         Object object = list.get(i);
/*     */ 
/* 248 */         if ((object instanceof Node)) {
/* 249 */           Node node = (Node)object;
/* 250 */           Object expression = getCompareValue(node);
/* 251 */           sortValues.put(node, expression);
/*     */         }
/*     */       }
/*     */ 
/* 255 */       sort(list, sortValues);
/*     */ 
/* 257 */       if (distinct)
/* 258 */         removeDuplicates(list, sortValues);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean matches(Node node)
/*     */   {
/*     */     try {
/* 265 */       setNSContext(node);
/*     */ 
/* 267 */       List answer = this.xpath.selectNodes(node);
/*     */ 
/* 269 */       if ((answer != null) && (answer.size() > 0)) {
/* 270 */         Object item = answer.get(0);
/*     */ 
/* 272 */         if ((item instanceof Boolean)) {
/* 273 */           return ((Boolean)item).booleanValue();
/*     */         }
/*     */ 
/* 276 */         return answer.contains(node);
/*     */       }
/*     */ 
/* 279 */       return false;
/*     */     } catch (JaxenException e) {
/* 281 */       handleJaxenException(e);
/*     */     }
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   protected void sort(List list, Map sortValues)
/*     */   {
/* 296 */     Collections.sort(list, new Comparator() { private final Map val$sortValues;
/*     */ 
/* 298 */       public int compare(Object o1, Object o2) { o1 = this.val$sortValues.get(o1);
/* 299 */         o2 = this.val$sortValues.get(o2);
/*     */ 
/* 301 */         if (o1 == o2)
/* 302 */           return 0;
/* 303 */         if ((o1 instanceof Comparable)) {
/* 304 */           Comparable c1 = (Comparable)o1;
/*     */ 
/* 306 */           return c1.compareTo(o2);
/* 307 */         }if (o1 == null)
/* 308 */           return 1;
/* 309 */         if (o2 == null) {
/* 310 */           return -1;
/*     */         }
/* 312 */         return o1.equals(o2) ? 0 : -1;
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   protected void removeDuplicates(List list, Map sortValues)
/*     */   {
/* 330 */     HashSet distinctValues = new HashSet();
/*     */ 
/* 332 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/* 333 */       Object node = iter.next();
/* 334 */       Object value = sortValues.get(node);
/*     */ 
/* 336 */       if (distinctValues.contains(value))
/* 337 */         iter.remove();
/*     */       else
/* 339 */         distinctValues.add(value);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Object getCompareValue(Node node)
/*     */   {
/* 353 */     return valueOf(node);
/*     */   }
/*     */ 
/*     */   protected static org.jaxen.XPath parse(String text) {
/*     */     try {
/* 358 */       return new Dom4jXPath(text);
/*     */     } catch (JaxenException e) {
/* 360 */       throw new InvalidXPathException(text, e.getMessage());
/*     */     } catch (Throwable t) {
/* 362 */       throw new InvalidXPathException(text, t);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void setNSContext(Object context) {
/* 367 */     if (this.namespaceContext == null)
/* 368 */       this.xpath.setNamespaceContext(DefaultNamespaceContext.create(context));
/*     */   }
/*     */ 
/*     */   protected void handleJaxenException(JaxenException exception)
/*     */     throws XPathException
/*     */   {
/* 374 */     throw new XPathException(this.text, exception);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpath.DefaultXPath
 * JD-Core Version:    0.6.2
 */