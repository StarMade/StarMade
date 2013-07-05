/*     */ package org.jaxen;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import org.jaxen.expr.DefaultXPathFactory;
/*     */ import org.jaxen.expr.Expr;
/*     */ import org.jaxen.expr.FilterExpr;
/*     */ import org.jaxen.expr.FunctionCallExpr;
/*     */ import org.jaxen.expr.LocationPath;
/*     */ import org.jaxen.expr.Predicate;
/*     */ import org.jaxen.expr.Predicated;
/*     */ import org.jaxen.expr.Step;
/*     */ import org.jaxen.expr.XPathExpr;
/*     */ import org.jaxen.expr.XPathFactory;
/*     */ import org.jaxen.saxpath.XPathHandler;
/*     */ 
/*     */ public class JaxenHandler
/*     */   implements XPathHandler
/*     */ {
/*     */   private XPathFactory xpathFactory;
/*     */   private XPathExpr xpath;
/*     */   protected boolean simplified;
/*     */   protected LinkedList stack;
/*     */ 
/*     */   public JaxenHandler()
/*     */   {
/*  96 */     this.stack = new LinkedList();
/*  97 */     this.xpathFactory = new DefaultXPathFactory();
/*     */   }
/*     */ 
/*     */   public void setXPathFactory(XPathFactory xpathFactory)
/*     */   {
/* 107 */     this.xpathFactory = xpathFactory;
/*     */   }
/*     */ 
/*     */   public XPathFactory getXPathFactory()
/*     */   {
/* 117 */     return this.xpathFactory;
/*     */   }
/*     */ 
/*     */   public XPathExpr getXPathExpr()
/*     */   {
/* 131 */     return getXPathExpr(true);
/*     */   }
/*     */ 
/*     */   public XPathExpr getXPathExpr(boolean shouldSimplify)
/*     */   {
/* 148 */     if ((shouldSimplify) && (!this.simplified))
/*     */     {
/* 150 */       this.xpath.simplify();
/* 151 */       this.simplified = true;
/*     */     }
/*     */ 
/* 154 */     return this.xpath;
/*     */   }
/*     */ 
/*     */   public void startXPath()
/*     */   {
/* 159 */     this.simplified = false;
/* 160 */     pushFrame();
/*     */   }
/*     */ 
/*     */   public void endXPath() throws JaxenException
/*     */   {
/* 165 */     this.xpath = getXPathFactory().createXPath((Expr)pop());
/* 166 */     popFrame();
/*     */   }
/*     */ 
/*     */   public void startPathExpr()
/*     */   {
/* 171 */     pushFrame();
/*     */   }
/*     */ 
/*     */   public void endPathExpr()
/*     */     throws JaxenException
/*     */   {
/*     */     FilterExpr filterExpr;
/*     */     LocationPath locationPath;
/*     */     FilterExpr filterExpr;
/* 195 */     if (stackSize() == 2)
/*     */     {
/* 197 */       LocationPath locationPath = (LocationPath)pop();
/* 198 */       filterExpr = (FilterExpr)pop();
/*     */     }
/*     */     else
/*     */     {
/* 202 */       Object popped = pop();
/*     */       FilterExpr filterExpr;
/* 204 */       if ((popped instanceof LocationPath))
/*     */       {
/* 206 */         LocationPath locationPath = (LocationPath)popped;
/* 207 */         filterExpr = null;
/*     */       }
/*     */       else
/*     */       {
/* 211 */         locationPath = null;
/* 212 */         filterExpr = (FilterExpr)popped;
/*     */       }
/*     */     }
/* 215 */     popFrame();
/*     */ 
/* 217 */     push(getXPathFactory().createPathExpr(filterExpr, locationPath));
/*     */   }
/*     */ 
/*     */   public void startAbsoluteLocationPath()
/*     */     throws JaxenException
/*     */   {
/* 223 */     pushFrame();
/*     */ 
/* 225 */     push(getXPathFactory().createAbsoluteLocationPath());
/*     */   }
/*     */ 
/*     */   public void endAbsoluteLocationPath() throws JaxenException
/*     */   {
/* 230 */     endLocationPath();
/*     */   }
/*     */ 
/*     */   public void startRelativeLocationPath() throws JaxenException
/*     */   {
/* 235 */     pushFrame();
/*     */ 
/* 237 */     push(getXPathFactory().createRelativeLocationPath());
/*     */   }
/*     */ 
/*     */   public void endRelativeLocationPath() throws JaxenException
/*     */   {
/* 242 */     endLocationPath();
/*     */   }
/*     */ 
/*     */   protected void endLocationPath() throws JaxenException
/*     */   {
/* 247 */     LocationPath path = (LocationPath)peekFrame().removeFirst();
/*     */ 
/* 249 */     addSteps(path, popFrame().iterator());
/*     */ 
/* 252 */     push(path);
/*     */   }
/*     */ 
/*     */   protected void addSteps(LocationPath locationPath, Iterator stepIter)
/*     */   {
/* 258 */     while (stepIter.hasNext())
/*     */     {
/* 260 */       locationPath.addStep((Step)stepIter.next());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startNameStep(int axis, String prefix, String localName)
/*     */     throws JaxenException
/*     */   {
/* 268 */     pushFrame();
/*     */ 
/* 270 */     push(getXPathFactory().createNameStep(axis, prefix, localName));
/*     */   }
/*     */ 
/*     */   public void endNameStep()
/*     */   {
/* 277 */     endStep();
/*     */   }
/*     */ 
/*     */   public void startTextNodeStep(int axis)
/*     */     throws JaxenException
/*     */   {
/* 283 */     pushFrame();
/*     */ 
/* 285 */     push(getXPathFactory().createTextNodeStep(axis));
/*     */   }
/*     */ 
/*     */   public void endTextNodeStep()
/*     */   {
/* 290 */     endStep();
/*     */   }
/*     */ 
/*     */   public void startCommentNodeStep(int axis) throws JaxenException
/*     */   {
/* 295 */     pushFrame();
/*     */ 
/* 297 */     push(getXPathFactory().createCommentNodeStep(axis));
/*     */   }
/*     */ 
/*     */   public void endCommentNodeStep()
/*     */   {
/* 302 */     endStep();
/*     */   }
/*     */ 
/*     */   public void startAllNodeStep(int axis) throws JaxenException
/*     */   {
/* 307 */     pushFrame();
/*     */ 
/* 309 */     push(getXPathFactory().createAllNodeStep(axis));
/*     */   }
/*     */ 
/*     */   public void endAllNodeStep()
/*     */   {
/* 314 */     endStep();
/*     */   }
/*     */ 
/*     */   public void startProcessingInstructionNodeStep(int axis, String name)
/*     */     throws JaxenException
/*     */   {
/* 320 */     pushFrame();
/*     */ 
/* 322 */     push(getXPathFactory().createProcessingInstructionNodeStep(axis, name));
/*     */   }
/*     */ 
/*     */   public void endProcessingInstructionNodeStep()
/*     */   {
/* 328 */     endStep();
/*     */   }
/*     */ 
/*     */   protected void endStep()
/*     */   {
/* 333 */     Step step = (Step)peekFrame().removeFirst();
/*     */ 
/* 335 */     addPredicates(step, popFrame().iterator());
/*     */ 
/* 338 */     push(step);
/*     */   }
/*     */ 
/*     */   public void startPredicate()
/*     */   {
/* 343 */     pushFrame();
/*     */   }
/*     */ 
/*     */   public void endPredicate() throws JaxenException
/*     */   {
/* 348 */     Predicate predicate = getXPathFactory().createPredicate((Expr)pop());
/*     */ 
/* 350 */     popFrame();
/*     */ 
/* 352 */     push(predicate);
/*     */   }
/*     */ 
/*     */   public void startFilterExpr()
/*     */   {
/* 357 */     pushFrame();
/*     */   }
/*     */ 
/*     */   public void endFilterExpr() throws JaxenException
/*     */   {
/* 362 */     Expr expr = (Expr)peekFrame().removeFirst();
/*     */ 
/* 364 */     FilterExpr filter = getXPathFactory().createFilterExpr(expr);
/*     */ 
/* 366 */     Iterator predIter = popFrame().iterator();
/*     */ 
/* 368 */     addPredicates(filter, predIter);
/*     */ 
/* 371 */     push(filter);
/*     */   }
/*     */ 
/*     */   protected void addPredicates(Predicated obj, Iterator predIter)
/*     */   {
/* 377 */     while (predIter.hasNext())
/*     */     {
/* 379 */       obj.addPredicate((Predicate)predIter.next());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void returnExpr()
/*     */   {
/* 385 */     Expr expr = (Expr)pop();
/* 386 */     popFrame();
/* 387 */     push(expr);
/*     */   }
/*     */ 
/*     */   public void startOrExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endOrExpr(boolean create)
/*     */     throws JaxenException
/*     */   {
/* 397 */     if (create)
/*     */     {
/* 399 */       Expr rhs = (Expr)pop();
/* 400 */       Expr lhs = (Expr)pop();
/*     */ 
/* 402 */       push(getXPathFactory().createOrExpr(lhs, rhs));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startAndExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endAndExpr(boolean create)
/*     */     throws JaxenException
/*     */   {
/* 414 */     if (create)
/*     */     {
/* 417 */       Expr rhs = (Expr)pop();
/* 418 */       Expr lhs = (Expr)pop();
/*     */ 
/* 420 */       push(getXPathFactory().createAndExpr(lhs, rhs));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startEqualityExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endEqualityExpr(int operator)
/*     */     throws JaxenException
/*     */   {
/* 432 */     if (operator != 0)
/*     */     {
/* 435 */       Expr rhs = (Expr)pop();
/* 436 */       Expr lhs = (Expr)pop();
/*     */ 
/* 438 */       push(getXPathFactory().createEqualityExpr(lhs, rhs, operator));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startRelationalExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endRelationalExpr(int operator)
/*     */     throws JaxenException
/*     */   {
/* 451 */     if (operator != 0)
/*     */     {
/* 454 */       Expr rhs = (Expr)pop();
/* 455 */       Expr lhs = (Expr)pop();
/*     */ 
/* 457 */       push(getXPathFactory().createRelationalExpr(lhs, rhs, operator));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startAdditiveExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endAdditiveExpr(int operator)
/*     */     throws JaxenException
/*     */   {
/* 470 */     if (operator != 0)
/*     */     {
/* 473 */       Expr rhs = (Expr)pop();
/* 474 */       Expr lhs = (Expr)pop();
/*     */ 
/* 476 */       push(getXPathFactory().createAdditiveExpr(lhs, rhs, operator));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startMultiplicativeExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endMultiplicativeExpr(int operator)
/*     */     throws JaxenException
/*     */   {
/* 489 */     if (operator != 0)
/*     */     {
/* 492 */       Expr rhs = (Expr)pop();
/* 493 */       Expr lhs = (Expr)pop();
/*     */ 
/* 495 */       push(getXPathFactory().createMultiplicativeExpr(lhs, rhs, operator));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startUnaryExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endUnaryExpr(int operator)
/*     */     throws JaxenException
/*     */   {
/* 508 */     if (operator != 0)
/*     */     {
/* 510 */       push(getXPathFactory().createUnaryExpr((Expr)pop(), operator));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startUnionExpr()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endUnionExpr(boolean create)
/*     */     throws JaxenException
/*     */   {
/* 522 */     if (create)
/*     */     {
/* 525 */       Expr rhs = (Expr)pop();
/* 526 */       Expr lhs = (Expr)pop();
/*     */ 
/* 528 */       push(getXPathFactory().createUnionExpr(lhs, rhs));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void number(int number)
/*     */     throws JaxenException
/*     */   {
/* 535 */     push(getXPathFactory().createNumberExpr(number));
/*     */   }
/*     */ 
/*     */   public void number(double number) throws JaxenException
/*     */   {
/* 540 */     push(getXPathFactory().createNumberExpr(number));
/*     */   }
/*     */ 
/*     */   public void literal(String literal) throws JaxenException
/*     */   {
/* 545 */     push(getXPathFactory().createLiteralExpr(literal));
/*     */   }
/*     */ 
/*     */   public void variableReference(String prefix, String variableName)
/*     */     throws JaxenException
/*     */   {
/* 551 */     push(getXPathFactory().createVariableReferenceExpr(prefix, variableName));
/*     */   }
/*     */ 
/*     */   public void startFunction(String prefix, String functionName)
/*     */     throws JaxenException
/*     */   {
/* 558 */     pushFrame();
/* 559 */     push(getXPathFactory().createFunctionCallExpr(prefix, functionName));
/*     */   }
/*     */ 
/*     */   public void endFunction()
/*     */   {
/* 565 */     FunctionCallExpr function = (FunctionCallExpr)peekFrame().removeFirst();
/*     */ 
/* 567 */     addParameters(function, popFrame().iterator());
/*     */ 
/* 570 */     push(function);
/*     */   }
/*     */ 
/*     */   protected void addParameters(FunctionCallExpr function, Iterator paramIter)
/*     */   {
/* 576 */     while (paramIter.hasNext())
/*     */     {
/* 578 */       function.addParameter((Expr)paramIter.next());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected int stackSize()
/*     */   {
/* 584 */     return peekFrame().size();
/*     */   }
/*     */ 
/*     */   protected void push(Object obj)
/*     */   {
/* 589 */     peekFrame().addLast(obj);
/*     */   }
/*     */ 
/*     */   protected Object pop()
/*     */   {
/* 594 */     return peekFrame().removeLast();
/*     */   }
/*     */ 
/*     */   protected boolean canPop()
/*     */   {
/* 599 */     return peekFrame().size() > 0;
/*     */   }
/*     */ 
/*     */   protected void pushFrame()
/*     */   {
/* 604 */     this.stack.addLast(new LinkedList());
/*     */   }
/*     */ 
/*     */   protected LinkedList popFrame()
/*     */   {
/* 609 */     return (LinkedList)this.stack.removeLast();
/*     */   }
/*     */ 
/*     */   protected LinkedList peekFrame()
/*     */   {
/* 614 */     return (LinkedList)this.stack.getLast();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.JaxenHandler
 * JD-Core Version:    0.6.2
 */