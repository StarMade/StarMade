/*     */ package org.jaxen.pattern;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.expr.FilterExpr;
/*     */ import org.jaxen.util.SingletonList;
/*     */ 
/*     */ public class LocationPathPattern extends Pattern
/*     */ {
/*  71 */   private NodeTest nodeTest = AnyNodeTest.getInstance();
/*     */   private Pattern parentPattern;
/*     */   private Pattern ancestorPattern;
/*     */   private List filters;
/*     */   private boolean absolute;
/*     */ 
/*     */   public LocationPathPattern()
/*     */   {
/*     */   }
/*     */ 
/*     */   public LocationPathPattern(NodeTest nodeTest)
/*     */   {
/*  92 */     this.nodeTest = nodeTest;
/*     */   }
/*     */ 
/*     */   public Pattern simplify()
/*     */   {
/*  97 */     if (this.parentPattern != null)
/*     */     {
/*  99 */       this.parentPattern = this.parentPattern.simplify();
/*     */     }
/* 101 */     if (this.ancestorPattern != null)
/*     */     {
/* 103 */       this.ancestorPattern = this.ancestorPattern.simplify();
/*     */     }
/* 105 */     if (this.filters == null)
/*     */     {
/* 107 */       if ((this.parentPattern == null) && (this.ancestorPattern == null))
/*     */       {
/* 109 */         return this.nodeTest;
/*     */       }
/* 111 */       if ((this.parentPattern != null) && (this.ancestorPattern == null))
/*     */       {
/* 113 */         if ((this.nodeTest instanceof AnyNodeTest))
/*     */         {
/* 115 */           return this.parentPattern;
/*     */         }
/*     */       }
/*     */     }
/* 119 */     return this;
/*     */   }
/*     */ 
/*     */   public void addFilter(FilterExpr filter)
/*     */   {
/* 126 */     if (this.filters == null)
/*     */     {
/* 128 */       this.filters = new ArrayList();
/*     */     }
/* 130 */     this.filters.add(filter);
/*     */   }
/*     */ 
/*     */   public void setParentPattern(Pattern parentPattern)
/*     */   {
/* 138 */     this.parentPattern = parentPattern;
/*     */   }
/*     */ 
/*     */   public void setAncestorPattern(Pattern ancestorPattern)
/*     */   {
/* 146 */     this.ancestorPattern = ancestorPattern;
/*     */   }
/*     */ 
/*     */   public void setNodeTest(NodeTest nodeTest)
/*     */     throws JaxenException
/*     */   {
/* 153 */     if ((this.nodeTest instanceof AnyNodeTest))
/*     */     {
/* 155 */       this.nodeTest = nodeTest;
/*     */     }
/*     */     else
/*     */     {
/* 159 */       throw new JaxenException("Attempt to overwrite nodeTest: " + this.nodeTest + " with: " + nodeTest);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean matches(Object node, Context context)
/*     */     throws JaxenException
/*     */   {
/* 167 */     Navigator navigator = context.getNavigator();
/*     */ 
/* 175 */     if (!this.nodeTest.matches(node, context))
/*     */     {
/* 177 */       return false;
/*     */     }
/*     */ 
/* 180 */     if (this.parentPattern != null)
/*     */     {
/* 182 */       Object parent = navigator.getParentNode(node);
/* 183 */       if (parent == null)
/*     */       {
/* 185 */         return false;
/*     */       }
/* 187 */       if (!this.parentPattern.matches(parent, context))
/*     */       {
/* 189 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 193 */     if (this.ancestorPattern != null) {
/* 194 */       Object ancestor = navigator.getParentNode(node);
/*     */ 
/* 197 */       while (!this.ancestorPattern.matches(ancestor, context))
/*     */       {
/* 201 */         if (ancestor == null)
/*     */         {
/* 203 */           return false;
/*     */         }
/* 205 */         if (navigator.isDocument(ancestor))
/*     */         {
/* 207 */           return false;
/*     */         }
/* 209 */         ancestor = navigator.getParentNode(ancestor);
/*     */       }
/*     */     }
/*     */ 
/* 213 */     if (this.filters != null)
/*     */     {
/* 215 */       List list = new SingletonList(node);
/*     */ 
/* 217 */       context.setNodeSet(list);
/*     */ 
/* 221 */       boolean answer = true;
/*     */ 
/* 223 */       for (Iterator iter = this.filters.iterator(); iter.hasNext(); )
/*     */       {
/* 225 */         FilterExpr filter = (FilterExpr)iter.next();
/*     */ 
/* 227 */         if (!filter.asBoolean(context))
/*     */         {
/* 229 */           answer = false;
/* 230 */           break;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 235 */       context.setNodeSet(list);
/*     */ 
/* 237 */       return answer;
/*     */     }
/* 239 */     return true;
/*     */   }
/*     */ 
/*     */   public double getPriority()
/*     */   {
/* 244 */     if (this.filters != null)
/*     */     {
/* 246 */       return 0.5D;
/*     */     }
/* 248 */     return this.nodeTest.getPriority();
/*     */   }
/*     */ 
/*     */   public short getMatchType()
/*     */   {
/* 254 */     return this.nodeTest.getMatchType();
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 259 */     StringBuffer buffer = new StringBuffer();
/* 260 */     if (this.absolute)
/*     */     {
/* 262 */       buffer.append("/");
/*     */     }
/* 264 */     if (this.ancestorPattern != null)
/*     */     {
/* 266 */       String text = this.ancestorPattern.getText();
/* 267 */       if (text.length() > 0)
/*     */       {
/* 269 */         buffer.append(text);
/* 270 */         buffer.append("//");
/*     */       }
/*     */     }
/* 273 */     if (this.parentPattern != null)
/*     */     {
/* 275 */       String text = this.parentPattern.getText();
/* 276 */       if (text.length() > 0)
/*     */       {
/* 278 */         buffer.append(text);
/* 279 */         buffer.append("/");
/*     */       }
/*     */     }
/* 282 */     buffer.append(this.nodeTest.getText());
/*     */ 
/* 284 */     if (this.filters != null)
/*     */     {
/* 286 */       buffer.append("[");
/* 287 */       for (Iterator iter = this.filters.iterator(); iter.hasNext(); )
/*     */       {
/* 289 */         FilterExpr filter = (FilterExpr)iter.next();
/* 290 */         buffer.append(filter.getText());
/*     */       }
/* 292 */       buffer.append("]");
/*     */     }
/* 294 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 299 */     return super.toString() + "[ absolute: " + this.absolute + " parent: " + this.parentPattern + " ancestor: " + this.ancestorPattern + " filters: " + this.filters + " nodeTest: " + this.nodeTest + " ]";
/*     */   }
/*     */ 
/*     */   public boolean isAbsolute()
/*     */   {
/* 306 */     return this.absolute;
/*     */   }
/*     */ 
/*     */   public void setAbsolute(boolean absolute)
/*     */   {
/* 311 */     this.absolute = absolute;
/*     */   }
/*     */ 
/*     */   public boolean hasAnyNodeTest()
/*     */   {
/* 316 */     return this.nodeTest instanceof AnyNodeTest;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.LocationPathPattern
 * JD-Core Version:    0.6.2
 */