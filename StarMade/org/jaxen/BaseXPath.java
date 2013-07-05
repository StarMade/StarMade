/*     */ package org.jaxen;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.jaxen.expr.Expr;
/*     */ import org.jaxen.expr.XPathExpr;
/*     */ import org.jaxen.function.BooleanFunction;
/*     */ import org.jaxen.function.NumberFunction;
/*     */ import org.jaxen.function.StringFunction;
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ import org.jaxen.saxpath.XPathReader;
/*     */ import org.jaxen.saxpath.helpers.XPathReaderFactory;
/*     */ import org.jaxen.util.SingletonList;
/*     */ 
/*     */ public class BaseXPath
/*     */   implements XPath, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1993731281300293168L;
/*     */   private final String exprText;
/*     */   private final XPathExpr xpath;
/*     */   private ContextSupport support;
/*     */   private Navigator navigator;
/*     */ 
/*     */   protected BaseXPath(String xpathExpr)
/*     */     throws JaxenException
/*     */   {
/*     */     try
/*     */     {
/* 113 */       XPathReader reader = XPathReaderFactory.createReader();
/* 114 */       JaxenHandler handler = new JaxenHandler();
/* 115 */       reader.setXPathHandler(handler);
/* 116 */       reader.parse(xpathExpr);
/* 117 */       this.xpath = handler.getXPathExpr();
/*     */     }
/*     */     catch (org.jaxen.saxpath.XPathSyntaxException e)
/*     */     {
/* 121 */       throw new XPathSyntaxException(e);
/*     */     }
/*     */     catch (SAXPathException e)
/*     */     {
/* 125 */       throw new JaxenException(e);
/*     */     }
/*     */ 
/* 128 */     this.exprText = xpathExpr;
/*     */   }
/*     */ 
/*     */   public BaseXPath(String xpathExpr, Navigator navigator)
/*     */     throws JaxenException
/*     */   {
/* 142 */     this(xpathExpr);
/* 143 */     this.navigator = navigator;
/*     */   }
/*     */ 
/*     */   public Object evaluate(Object context)
/*     */     throws JaxenException
/*     */   {
/* 175 */     List answer = selectNodes(context);
/*     */ 
/* 177 */     if ((answer != null) && (answer.size() == 1))
/*     */     {
/* 181 */       Object first = answer.get(0);
/*     */ 
/* 183 */       if (((first instanceof String)) || ((first instanceof Number)) || ((first instanceof Boolean)))
/*     */       {
/* 189 */         return first;
/*     */       }
/*     */     }
/* 192 */     return answer;
/*     */   }
/*     */ 
/*     */   public List selectNodes(Object node)
/*     */     throws JaxenException
/*     */   {
/* 215 */     Context context = getContext(node);
/* 216 */     return selectNodesForContext(context);
/*     */   }
/*     */ 
/*     */   public Object selectSingleNode(Object node)
/*     */     throws JaxenException
/*     */   {
/* 237 */     List results = selectNodes(node);
/*     */ 
/* 239 */     if (results.isEmpty())
/*     */     {
/* 241 */       return null;
/*     */     }
/*     */ 
/* 244 */     return results.get(0);
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public String valueOf(Object node)
/*     */     throws JaxenException
/*     */   {
/* 257 */     return stringValueOf(node);
/*     */   }
/*     */ 
/*     */   public String stringValueOf(Object node)
/*     */     throws JaxenException
/*     */   {
/* 280 */     Context context = getContext(node);
/*     */ 
/* 282 */     Object result = selectSingleNodeForContext(context);
/*     */ 
/* 284 */     if (result == null)
/*     */     {
/* 286 */       return "";
/*     */     }
/*     */ 
/* 289 */     return StringFunction.evaluate(result, context.getNavigator());
/*     */   }
/*     */ 
/*     */   public boolean booleanValueOf(Object node)
/*     */     throws JaxenException
/*     */   {
/* 312 */     Context context = getContext(node);
/* 313 */     List result = selectNodesForContext(context);
/* 314 */     if (result == null) return false;
/* 315 */     return BooleanFunction.evaluate(result, context.getNavigator()).booleanValue();
/*     */   }
/*     */ 
/*     */   public Number numberValueOf(Object node)
/*     */     throws JaxenException
/*     */   {
/* 337 */     Context context = getContext(node);
/* 338 */     Object result = selectSingleNodeForContext(context);
/* 339 */     return NumberFunction.evaluate(result, context.getNavigator());
/*     */   }
/*     */ 
/*     */   public void addNamespace(String prefix, String uri)
/*     */     throws JaxenException
/*     */   {
/* 372 */     NamespaceContext nsContext = getNamespaceContext();
/* 373 */     if ((nsContext instanceof SimpleNamespaceContext))
/*     */     {
/* 375 */       ((SimpleNamespaceContext)nsContext).addNamespace(prefix, uri);
/*     */ 
/* 377 */       return;
/*     */     }
/*     */ 
/* 380 */     throw new JaxenException("Operation not permitted while using a non-simple namespace context.");
/*     */   }
/*     */ 
/*     */   public void setNamespaceContext(NamespaceContext namespaceContext)
/*     */   {
/* 407 */     getContextSupport().setNamespaceContext(namespaceContext);
/*     */   }
/*     */ 
/*     */   public void setFunctionContext(FunctionContext functionContext)
/*     */   {
/* 426 */     getContextSupport().setFunctionContext(functionContext);
/*     */   }
/*     */ 
/*     */   public void setVariableContext(VariableContext variableContext)
/*     */   {
/* 445 */     getContextSupport().setVariableContext(variableContext);
/*     */   }
/*     */ 
/*     */   public NamespaceContext getNamespaceContext()
/*     */   {
/* 468 */     return getContextSupport().getNamespaceContext();
/*     */   }
/*     */ 
/*     */   public FunctionContext getFunctionContext()
/*     */   {
/* 491 */     return getContextSupport().getFunctionContext();
/*     */   }
/*     */ 
/*     */   public VariableContext getVariableContext()
/*     */   {
/* 514 */     return getContextSupport().getVariableContext();
/*     */   }
/*     */ 
/*     */   public Expr getRootExpr()
/*     */   {
/* 534 */     return this.xpath.getRootExpr();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 543 */     return this.exprText;
/*     */   }
/*     */ 
/*     */   public String debug()
/*     */   {
/* 552 */     return this.xpath.toString();
/*     */   }
/*     */ 
/*     */   protected Context getContext(Object node)
/*     */   {
/* 572 */     if ((node instanceof Context))
/*     */     {
/* 574 */       return (Context)node;
/*     */     }
/*     */ 
/* 577 */     Context fullContext = new Context(getContextSupport());
/*     */ 
/* 579 */     if ((node instanceof List))
/*     */     {
/* 581 */       fullContext.setNodeSet((List)node);
/*     */     }
/*     */     else
/*     */     {
/* 585 */       List list = new SingletonList(node);
/* 586 */       fullContext.setNodeSet(list);
/*     */     }
/*     */ 
/* 589 */     return fullContext;
/*     */   }
/*     */ 
/*     */   protected ContextSupport getContextSupport()
/*     */   {
/* 601 */     if (this.support == null)
/*     */     {
/* 603 */       this.support = new ContextSupport(createNamespaceContext(), createFunctionContext(), createVariableContext(), getNavigator());
/*     */     }
/*     */ 
/* 611 */     return this.support;
/*     */   }
/*     */ 
/*     */   public Navigator getNavigator()
/*     */   {
/* 621 */     return this.navigator;
/*     */   }
/*     */ 
/*     */   protected FunctionContext createFunctionContext()
/*     */   {
/* 638 */     return XPathFunctionContext.getInstance();
/*     */   }
/*     */ 
/*     */   protected NamespaceContext createNamespaceContext()
/*     */   {
/* 647 */     return new SimpleNamespaceContext();
/*     */   }
/*     */ 
/*     */   protected VariableContext createVariableContext()
/*     */   {
/* 656 */     return new SimpleVariableContext();
/*     */   }
/*     */ 
/*     */   protected List selectNodesForContext(Context context)
/*     */     throws JaxenException
/*     */   {
/* 677 */     List list = this.xpath.asList(context);
/* 678 */     return list;
/*     */   }
/*     */ 
/*     */   protected Object selectSingleNodeForContext(Context context)
/*     */     throws JaxenException
/*     */   {
/* 701 */     List results = selectNodesForContext(context);
/*     */ 
/* 703 */     if (results.isEmpty())
/*     */     {
/* 705 */       return null;
/*     */     }
/*     */ 
/* 708 */     return results.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.BaseXPath
 * JD-Core Version:    0.6.2
 */