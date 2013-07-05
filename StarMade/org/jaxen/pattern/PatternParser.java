/*     */ package org.jaxen.pattern;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.JaxenHandler;
/*     */ import org.jaxen.expr.DefaultAllNodeStep;
/*     */ import org.jaxen.expr.DefaultCommentNodeStep;
/*     */ import org.jaxen.expr.DefaultFilterExpr;
/*     */ import org.jaxen.expr.DefaultNameStep;
/*     */ import org.jaxen.expr.DefaultProcessingInstructionNodeStep;
/*     */ import org.jaxen.expr.DefaultStep;
/*     */ import org.jaxen.expr.DefaultTextNodeStep;
/*     */ import org.jaxen.expr.DefaultXPathFactory;
/*     */ import org.jaxen.expr.Expr;
/*     */ import org.jaxen.expr.FilterExpr;
/*     */ import org.jaxen.expr.LocationPath;
/*     */ import org.jaxen.expr.Predicate;
/*     */ import org.jaxen.expr.PredicateSet;
/*     */ import org.jaxen.expr.Step;
/*     */ import org.jaxen.expr.UnionExpr;
/*     */ import org.jaxen.expr.XPathExpr;
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ import org.jaxen.saxpath.XPathReader;
/*     */ import org.jaxen.saxpath.helpers.XPathReaderFactory;
/*     */ 
/*     */ public class PatternParser
/*     */ {
/*     */   private static final boolean TRACE = false;
/*     */   private static final boolean USE_HANDLER = false;
/*     */ 
/*     */   public static Pattern parse(String text)
/*     */     throws JaxenException, SAXPathException
/*     */   {
/* 101 */     XPathReader reader = XPathReaderFactory.createReader();
/* 102 */     JaxenHandler handler = new JaxenHandler();
/*     */ 
/* 104 */     handler.setXPathFactory(new DefaultXPathFactory());
/* 105 */     reader.setXPathHandler(handler);
/* 106 */     reader.parse(text);
/*     */ 
/* 108 */     Pattern pattern = convertExpr(handler.getXPathExpr().getRootExpr());
/* 109 */     return pattern.simplify();
/*     */   }
/*     */ 
/*     */   protected static Pattern convertExpr(Expr expr)
/*     */     throws JaxenException
/*     */   {
/* 120 */     if ((expr instanceof LocationPath))
/*     */     {
/* 122 */       return convertExpr((LocationPath)expr);
/*     */     }
/* 124 */     if ((expr instanceof FilterExpr))
/*     */     {
/* 126 */       LocationPathPattern answer = new LocationPathPattern();
/* 127 */       answer.addFilter((FilterExpr)expr);
/* 128 */       return answer;
/*     */     }
/* 130 */     if ((expr instanceof UnionExpr))
/*     */     {
/* 132 */       UnionExpr unionExpr = (UnionExpr)expr;
/* 133 */       Pattern lhs = convertExpr(unionExpr.getLHS());
/* 134 */       Pattern rhs = convertExpr(unionExpr.getRHS());
/* 135 */       return new UnionPattern(lhs, rhs);
/*     */     }
/*     */ 
/* 139 */     LocationPathPattern answer = new LocationPathPattern();
/* 140 */     answer.addFilter(new DefaultFilterExpr(expr, new PredicateSet()));
/*     */ 
/* 142 */     return answer;
/*     */   }
/*     */ 
/*     */   protected static LocationPathPattern convertExpr(LocationPath locationPath)
/*     */     throws JaxenException
/*     */   {
/* 148 */     LocationPathPattern answer = new LocationPathPattern();
/*     */ 
/* 150 */     List steps = locationPath.getSteps();
/*     */ 
/* 153 */     LocationPathPattern path = answer;
/* 154 */     boolean first = true;
/* 155 */     for (ListIterator iter = steps.listIterator(steps.size()); iter.hasPrevious(); )
/*     */     {
/* 157 */       Step step = (Step)iter.previous();
/* 158 */       if (first)
/*     */       {
/* 160 */         first = false;
/* 161 */         path = convertStep(path, step);
/*     */       }
/*     */       else
/*     */       {
/* 165 */         if (navigationStep(step))
/*     */         {
/* 167 */           LocationPathPattern parent = new LocationPathPattern();
/* 168 */           int axis = step.getAxis();
/* 169 */           if ((axis == 2) || (axis == 12))
/*     */           {
/* 171 */             path.setAncestorPattern(parent);
/*     */           }
/*     */           else
/*     */           {
/* 175 */             path.setParentPattern(parent);
/*     */           }
/* 177 */           path = parent;
/*     */         }
/* 179 */         path = convertStep(path, step);
/*     */       }
/*     */     }
/* 182 */     if (locationPath.isAbsolute())
/*     */     {
/* 184 */       LocationPathPattern parent = new LocationPathPattern(NodeTypeTest.DOCUMENT_TEST);
/* 185 */       path.setParentPattern(parent);
/*     */     }
/* 187 */     return answer;
/*     */   }
/*     */ 
/*     */   protected static LocationPathPattern convertStep(LocationPathPattern path, Step step) throws JaxenException
/*     */   {
/* 192 */     if ((step instanceof DefaultAllNodeStep))
/*     */     {
/* 194 */       int axis = step.getAxis();
/* 195 */       if (axis == 9)
/*     */       {
/* 197 */         path.setNodeTest(NodeTypeTest.ATTRIBUTE_TEST);
/*     */       }
/*     */       else
/*     */       {
/* 201 */         path.setNodeTest(NodeTypeTest.ELEMENT_TEST);
/*     */       }
/*     */     }
/* 204 */     else if ((step instanceof DefaultCommentNodeStep))
/*     */     {
/* 206 */       path.setNodeTest(NodeTypeTest.COMMENT_TEST);
/*     */     }
/* 208 */     else if ((step instanceof DefaultProcessingInstructionNodeStep))
/*     */     {
/* 210 */       path.setNodeTest(NodeTypeTest.PROCESSING_INSTRUCTION_TEST);
/*     */     }
/* 212 */     else if ((step instanceof DefaultTextNodeStep))
/*     */     {
/* 214 */       path.setNodeTest(TextNodeTest.SINGLETON);
/*     */     }
/* 216 */     else if ((step instanceof DefaultCommentNodeStep))
/*     */     {
/* 218 */       path.setNodeTest(NodeTypeTest.COMMENT_TEST);
/*     */     } else {
/* 220 */       if ((step instanceof DefaultNameStep))
/*     */       {
/* 222 */         DefaultNameStep nameStep = (DefaultNameStep)step;
/* 223 */         String localName = nameStep.getLocalName();
/* 224 */         String prefix = nameStep.getPrefix();
/* 225 */         int axis = nameStep.getAxis();
/* 226 */         short nodeType = 1;
/* 227 */         if (axis == 9)
/*     */         {
/* 229 */           nodeType = 2;
/*     */         }
/* 231 */         if (nameStep.isMatchesAnyName())
/*     */         {
/* 233 */           if ((prefix.length() == 0) || (prefix.equals("*")))
/*     */           {
/* 235 */             if (axis == 9)
/*     */             {
/* 237 */               path.setNodeTest(NodeTypeTest.ATTRIBUTE_TEST);
/*     */             }
/*     */             else
/*     */             {
/* 241 */               path.setNodeTest(NodeTypeTest.ELEMENT_TEST);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 246 */             path.setNodeTest(new NamespaceTest(prefix, nodeType));
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 251 */           path.setNodeTest(new NameTest(localName, nodeType));
/*     */         }
/*     */ 
/* 254 */         return convertDefaultStep(path, nameStep);
/*     */       }
/* 256 */       if ((step instanceof DefaultStep))
/*     */       {
/* 258 */         return convertDefaultStep(path, (DefaultStep)step);
/*     */       }
/*     */ 
/* 262 */       throw new JaxenException("Cannot convert: " + step + " to a Pattern");
/*     */     }
/* 264 */     return path;
/*     */   }
/*     */ 
/*     */   protected static LocationPathPattern convertDefaultStep(LocationPathPattern path, DefaultStep step) throws JaxenException
/*     */   {
/* 269 */     List predicates = step.getPredicates();
/* 270 */     if (!predicates.isEmpty())
/*     */     {
/* 272 */       FilterExpr filter = new DefaultFilterExpr(new PredicateSet());
/* 273 */       for (Iterator iter = predicates.iterator(); iter.hasNext(); )
/*     */       {
/* 275 */         filter.addPredicate((Predicate)iter.next());
/*     */       }
/* 277 */       path.addFilter(filter);
/*     */     }
/* 279 */     return path;
/*     */   }
/*     */ 
/*     */   protected static boolean navigationStep(Step step)
/*     */   {
/* 284 */     if ((step instanceof DefaultNameStep))
/*     */     {
/* 286 */       return true;
/*     */     }
/*     */ 
/* 289 */     if (step.getClass().equals(DefaultStep.class))
/*     */     {
/* 291 */       return !step.getPredicates().isEmpty();
/*     */     }
/*     */ 
/* 295 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.PatternParser
 * JD-Core Version:    0.6.2
 */