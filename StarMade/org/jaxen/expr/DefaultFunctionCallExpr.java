/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.JaxenException;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class DefaultFunctionCallExpr extends DefaultExpr
/*     */   implements FunctionCallExpr
/*     */ {
/*     */   private static final long serialVersionUID = -4747789292572193708L;
/*     */   private String prefix;
/*     */   private String functionName;
/*     */   private List parameters;
/*     */ 
/*     */   public DefaultFunctionCallExpr(String prefix, String functionName)
/*     */   {
/*  75 */     this.prefix = prefix;
/*  76 */     this.functionName = functionName;
/*  77 */     this.parameters = new ArrayList();
/*     */   }
/*     */ 
/*     */   public void addParameter(Expr parameter)
/*     */   {
/*  82 */     this.parameters.add(parameter);
/*     */   }
/*     */ 
/*     */   public List getParameters()
/*     */   {
/*  88 */     return this.parameters;
/*     */   }
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/*  93 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   public String getFunctionName()
/*     */   {
/*  98 */     return this.functionName;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 104 */     StringBuffer buf = new StringBuffer();
/* 105 */     String prefix = getPrefix();
/*     */ 
/* 107 */     if ((prefix != null) && (prefix.length() > 0))
/*     */     {
/* 110 */       buf.append(prefix);
/* 111 */       buf.append(":");
/*     */     }
/*     */ 
/* 114 */     buf.append(getFunctionName());
/* 115 */     buf.append("(");
/*     */ 
/* 117 */     Iterator paramIter = getParameters().iterator();
/*     */ 
/* 119 */     while (paramIter.hasNext()) {
/* 120 */       Expr eachParam = (Expr)paramIter.next();
/*     */ 
/* 122 */       buf.append(eachParam.getText());
/*     */ 
/* 124 */       if (paramIter.hasNext())
/*     */       {
/* 126 */         buf.append(", ");
/*     */       }
/*     */     }
/*     */ 
/* 130 */     buf.append(")");
/*     */ 
/* 132 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public Expr simplify()
/*     */   {
/* 137 */     List paramExprs = getParameters();
/* 138 */     int paramSize = paramExprs.size();
/*     */ 
/* 140 */     List newParams = new ArrayList(paramSize);
/*     */ 
/* 142 */     for (int i = 0; i < paramSize; i++)
/*     */     {
/* 144 */       Expr eachParam = (Expr)paramExprs.get(i);
/*     */ 
/* 146 */       newParams.add(eachParam.simplify());
/*     */     }
/*     */ 
/* 149 */     this.parameters = newParams;
/*     */ 
/* 151 */     return this;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 157 */     String prefix = getPrefix();
/*     */ 
/* 159 */     if (prefix == null)
/*     */     {
/* 161 */       return "[(DefaultFunctionCallExpr): " + getFunctionName() + "(" + getParameters() + ") ]";
/*     */     }
/*     */ 
/* 164 */     return "[(DefaultFunctionCallExpr): " + getPrefix() + ":" + getFunctionName() + "(" + getParameters() + ") ]";
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/* 169 */     String prefix = getPrefix();
/* 170 */     String namespaceURI = null;
/*     */ 
/* 172 */     if ((prefix != null) && (!"".equals(prefix))) {
/* 173 */       namespaceURI = context.translateNamespacePrefixToUri(prefix);
/*     */     }
/*     */ 
/* 176 */     Function func = context.getFunction(namespaceURI, prefix, getFunctionName());
/*     */ 
/* 179 */     List paramValues = evaluateParams(context);
/*     */ 
/* 181 */     return func.call(context, paramValues);
/*     */   }
/*     */ 
/*     */   public List evaluateParams(Context context) throws JaxenException
/*     */   {
/* 186 */     List paramExprs = getParameters();
/* 187 */     int paramSize = paramExprs.size();
/*     */ 
/* 189 */     List paramValues = new ArrayList(paramSize);
/*     */ 
/* 191 */     for (int i = 0; i < paramSize; i++)
/*     */     {
/* 193 */       Expr eachParam = (Expr)paramExprs.get(i);
/*     */ 
/* 195 */       Object eachValue = eachParam.evaluate(context);
/*     */ 
/* 197 */       paramValues.add(eachValue);
/*     */     }
/* 199 */     return paramValues;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultFunctionCallExpr
 * JD-Core Version:    0.6.2
 */