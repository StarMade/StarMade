/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.function.BooleanFunction;
/*     */ 
/*     */ public class PredicateSet
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7166491740228977853L;
/*     */   private List predicates;
/*     */ 
/*     */   public PredicateSet()
/*     */   {
/*  88 */     this.predicates = Collections.EMPTY_LIST;
/*     */   }
/*     */ 
/*     */   public void addPredicate(Predicate predicate)
/*     */   {
/*  98 */     if (this.predicates == Collections.EMPTY_LIST)
/*     */     {
/* 100 */       this.predicates = new ArrayList();
/*     */     }
/*     */ 
/* 103 */     this.predicates.add(predicate);
/*     */   }
/*     */ 
/*     */   public List getPredicates()
/*     */   {
/* 114 */     return this.predicates;
/*     */   }
/*     */ 
/*     */   public void simplify()
/*     */   {
/* 122 */     Iterator predIter = this.predicates.iterator();
/* 123 */     Predicate eachPred = null;
/*     */ 
/* 125 */     while (predIter.hasNext())
/*     */     {
/* 127 */       eachPred = (Predicate)predIter.next();
/* 128 */       eachPred.simplify();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 139 */     StringBuffer buf = new StringBuffer();
/*     */ 
/* 141 */     Iterator predIter = this.predicates.iterator();
/* 142 */     Predicate eachPred = null;
/*     */ 
/* 144 */     while (predIter.hasNext())
/*     */     {
/* 146 */       eachPred = (Predicate)predIter.next();
/* 147 */       buf.append(eachPred.getText());
/*     */     }
/*     */ 
/* 150 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   protected boolean evaluateAsBoolean(List contextNodeSet, ContextSupport support)
/*     */     throws JaxenException
/*     */   {
/* 167 */     return anyMatchingNode(contextNodeSet, support);
/*     */   }
/*     */ 
/*     */   private boolean anyMatchingNode(List contextNodeSet, ContextSupport support)
/*     */     throws JaxenException
/*     */   {
/* 173 */     if (this.predicates.size() == 0) {
/* 174 */       return false;
/*     */     }
/* 176 */     Iterator predIter = this.predicates.iterator();
/*     */ 
/* 179 */     List nodes2Filter = contextNodeSet;
/*     */ 
/* 181 */     while (predIter.hasNext()) {
/* 182 */       int nodes2FilterSize = nodes2Filter.size();
/*     */ 
/* 184 */       Context predContext = new Context(support);
/* 185 */       List tempList = new ArrayList(1);
/* 186 */       predContext.setNodeSet(tempList);
/*     */ 
/* 189 */       for (int i = 0; i < nodes2FilterSize; i++) {
/* 190 */         Object contextNode = nodes2Filter.get(i);
/* 191 */         tempList.clear();
/* 192 */         tempList.add(contextNode);
/* 193 */         predContext.setNodeSet(tempList);
/*     */ 
/* 195 */         predContext.setPosition(i + 1);
/* 196 */         predContext.setSize(nodes2FilterSize);
/* 197 */         Object predResult = ((Predicate)predIter.next()).evaluate(predContext);
/* 198 */         if ((predResult instanceof Number))
/*     */         {
/* 201 */           int proximity = ((Number)predResult).intValue();
/* 202 */           if (proximity == i + 1)
/* 203 */             return true;
/*     */         }
/*     */         else
/*     */         {
/* 207 */           Boolean includes = BooleanFunction.evaluate(predResult, predContext.getNavigator());
/*     */ 
/* 210 */           if (includes.booleanValue()) {
/* 211 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   protected List evaluatePredicates(List contextNodeSet, ContextSupport support)
/*     */     throws JaxenException
/*     */   {
/* 235 */     if (this.predicates.size() == 0) {
/* 236 */       return contextNodeSet;
/*     */     }
/* 238 */     Iterator predIter = this.predicates.iterator();
/*     */ 
/* 241 */     List nodes2Filter = contextNodeSet;
/*     */ 
/* 243 */     while (predIter.hasNext()) {
/* 244 */       nodes2Filter = applyPredicate((Predicate)predIter.next(), nodes2Filter, support);
/*     */     }
/*     */ 
/* 248 */     return nodes2Filter;
/*     */   }
/*     */ 
/*     */   public List applyPredicate(Predicate predicate, List nodes2Filter, ContextSupport support) throws JaxenException
/*     */   {
/* 253 */     int nodes2FilterSize = nodes2Filter.size();
/* 254 */     List filteredNodes = new ArrayList(nodes2FilterSize);
/*     */ 
/* 256 */     Context predContext = new Context(support);
/* 257 */     List tempList = new ArrayList(1);
/* 258 */     predContext.setNodeSet(tempList);
/*     */ 
/* 261 */     for (int i = 0; i < nodes2FilterSize; i++) {
/* 262 */       Object contextNode = nodes2Filter.get(i);
/* 263 */       tempList.clear();
/* 264 */       tempList.add(contextNode);
/* 265 */       predContext.setNodeSet(tempList);
/*     */ 
/* 267 */       predContext.setPosition(i + 1);
/* 268 */       predContext.setSize(nodes2FilterSize);
/* 269 */       Object predResult = predicate.evaluate(predContext);
/* 270 */       if ((predResult instanceof Number))
/*     */       {
/* 273 */         int proximity = ((Number)predResult).intValue();
/* 274 */         if (proximity == i + 1)
/* 275 */           filteredNodes.add(contextNode);
/*     */       }
/*     */       else
/*     */       {
/* 279 */         Boolean includes = BooleanFunction.evaluate(predResult, predContext.getNavigator());
/*     */ 
/* 282 */         if (includes.booleanValue()) {
/* 283 */           filteredNodes.add(contextNode);
/*     */         }
/*     */       }
/*     */     }
/* 287 */     return filteredNodes;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.PredicateSet
 * JD-Core Version:    0.6.2
 */