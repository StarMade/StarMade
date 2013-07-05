/*     */ package org.jaxen.pattern;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.LinkedList;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.JaxenHandler;
/*     */ import org.jaxen.expr.Expr;
/*     */ import org.jaxen.expr.FilterExpr;
/*     */ import org.jaxen.expr.XPathFactory;
/*     */ 
/*     */ public class PatternHandler extends JaxenHandler
/*     */ {
/*     */   private Pattern pattern;
/*     */ 
/*     */   public Pattern getPattern()
/*     */   {
/*  84 */     return getPattern(true);
/*     */   }
/*     */ 
/*     */   public Pattern getPattern(boolean shouldSimplify)
/*     */   {
/* 101 */     if ((shouldSimplify) && (!this.simplified))
/*     */     {
/* 104 */       this.pattern.simplify();
/* 105 */       this.simplified = true;
/*     */     }
/*     */ 
/* 108 */     return this.pattern;
/*     */   }
/*     */ 
/*     */   public void endXPath()
/*     */   {
/* 116 */     this.pattern = ((Pattern)pop());
/*     */ 
/* 118 */     System.out.println("stack is: " + this.stack);
/*     */ 
/* 120 */     popFrame();
/*     */   }
/*     */ 
/*     */   public void endPathExpr()
/*     */   {
/* 140 */     LinkedList frame = popFrame();
/*     */ 
/* 142 */     System.out.println("endPathExpr(): " + frame);
/*     */ 
/* 144 */     push(frame.removeFirst());
/*     */   }
/*     */ 
/*     */   public void startAbsoluteLocationPath()
/*     */   {
/* 176 */     pushFrame();
/*     */ 
/* 178 */     push(createAbsoluteLocationPath());
/*     */   }
/*     */ 
/*     */   public void endAbsoluteLocationPath()
/*     */     throws JaxenException
/*     */   {
/* 184 */     endLocationPath();
/*     */   }
/*     */ 
/*     */   public void startRelativeLocationPath()
/*     */   {
/* 190 */     pushFrame();
/*     */ 
/* 192 */     push(createRelativeLocationPath());
/*     */   }
/*     */ 
/*     */   public void endRelativeLocationPath()
/*     */     throws JaxenException
/*     */   {
/* 198 */     endLocationPath();
/*     */   }
/*     */ 
/*     */   protected void endLocationPath()
/*     */     throws JaxenException
/*     */   {
/* 204 */     LinkedList list = popFrame();
/*     */ 
/* 206 */     System.out.println("endLocationPath: " + list);
/*     */ 
/* 208 */     LocationPathPattern locationPath = (LocationPathPattern)list.removeFirst();
/* 209 */     push(locationPath);
/* 210 */     boolean doneNodeTest = false;
/* 211 */     while (!list.isEmpty())
/*     */     {
/* 213 */       Object filter = list.removeFirst();
/* 214 */       if ((filter instanceof NodeTest))
/*     */       {
/* 216 */         if (doneNodeTest)
/*     */         {
/* 218 */           LocationPathPattern parent = new LocationPathPattern((NodeTest)filter);
/* 219 */           locationPath.setParentPattern(parent);
/* 220 */           locationPath = parent;
/* 221 */           doneNodeTest = false;
/*     */         }
/*     */         else
/*     */         {
/* 225 */           locationPath.setNodeTest((NodeTest)filter);
/*     */         }
/*     */       }
/* 228 */       else if ((filter instanceof FilterExpr))
/*     */       {
/* 230 */         locationPath.addFilter((FilterExpr)filter);
/*     */       }
/* 232 */       else if ((filter instanceof LocationPathPattern))
/*     */       {
/* 234 */         LocationPathPattern parent = (LocationPathPattern)filter;
/* 235 */         locationPath.setParentPattern(parent);
/* 236 */         locationPath = parent;
/* 237 */         doneNodeTest = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startNameStep(int axis, String prefix, String localName)
/*     */   {
/* 248 */     pushFrame();
/*     */ 
/* 250 */     short nodeType = 1;
/* 251 */     switch (axis)
/*     */     {
/*     */     case 9:
/* 254 */       nodeType = 2;
/* 255 */       break;
/*     */     case 10:
/* 257 */       nodeType = 13;
/*     */     }
/*     */ 
/* 261 */     if ((prefix != null) && (prefix.length() > 0) && (!prefix.equals("*")))
/*     */     {
/* 263 */       push(new NamespaceTest(prefix, nodeType));
/*     */     }
/* 265 */     if ((localName != null) && (localName.length() > 0) && (!localName.equals("*")))
/*     */     {
/* 267 */       push(new NameTest(localName, nodeType));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startTextNodeStep(int axis)
/*     */   {
/* 274 */     pushFrame();
/*     */ 
/* 276 */     push(new NodeTypeTest((short)3));
/*     */   }
/*     */ 
/*     */   public void startCommentNodeStep(int axis)
/*     */   {
/* 282 */     pushFrame();
/*     */ 
/* 284 */     push(new NodeTypeTest((short)8));
/*     */   }
/*     */ 
/*     */   public void startAllNodeStep(int axis)
/*     */   {
/* 290 */     pushFrame();
/*     */ 
/* 292 */     push(AnyNodeTest.getInstance());
/*     */   }
/*     */ 
/*     */   public void startProcessingInstructionNodeStep(int axis, String name)
/*     */   {
/* 299 */     pushFrame();
/*     */ 
/* 302 */     push(new NodeTypeTest((short)7));
/*     */   }
/*     */ 
/*     */   protected void endStep()
/*     */   {
/* 307 */     LinkedList list = popFrame();
/* 308 */     if (!list.isEmpty())
/*     */     {
/* 310 */       push(list.removeFirst());
/*     */ 
/* 312 */       if (!list.isEmpty())
/*     */       {
/* 314 */         System.out.println("List should now be empty!" + list);
/*     */       }
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
/* 329 */     if (create)
/*     */     {
/* 333 */       Expr rhs = (Expr)pop();
/* 334 */       Expr lhs = (Expr)pop();
/*     */ 
/* 336 */       push(getXPathFactory().createUnionExpr(lhs, rhs));
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Pattern createAbsoluteLocationPath()
/*     */   {
/* 343 */     return new LocationPathPattern(NodeTypeTest.DOCUMENT_TEST);
/*     */   }
/*     */ 
/*     */   protected Pattern createRelativeLocationPath()
/*     */   {
/* 348 */     return new LocationPathPattern();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.PatternHandler
 * JD-Core Version:    0.6.2
 */