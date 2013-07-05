/*     */ package org.jaxen.pattern;
/*     */ 
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NodeTypeTest extends NodeTest
/*     */ {
/*  60 */   public static final NodeTypeTest DOCUMENT_TEST = new NodeTypeTest((short)9);
/*     */ 
/*  63 */   public static final NodeTypeTest ELEMENT_TEST = new NodeTypeTest((short)1);
/*     */ 
/*  66 */   public static final NodeTypeTest ATTRIBUTE_TEST = new NodeTypeTest((short)2);
/*     */ 
/*  69 */   public static final NodeTypeTest COMMENT_TEST = new NodeTypeTest((short)8);
/*     */ 
/*  72 */   public static final NodeTypeTest TEXT_TEST = new NodeTypeTest((short)3);
/*     */ 
/*  75 */   public static final NodeTypeTest PROCESSING_INSTRUCTION_TEST = new NodeTypeTest((short)7);
/*     */ 
/*  78 */   public static final NodeTypeTest NAMESPACE_TEST = new NodeTypeTest((short)13);
/*     */   private short nodeType;
/*     */ 
/*     */   public NodeTypeTest(short nodeType)
/*     */   {
/*  86 */     this.nodeType = nodeType;
/*     */   }
/*     */ 
/*     */   public boolean matches(Object node, Context context)
/*     */   {
/*  93 */     return this.nodeType == context.getNavigator().getNodeType(node);
/*     */   }
/*     */ 
/*     */   public double getPriority()
/*     */   {
/*  98 */     return -0.5D;
/*     */   }
/*     */ 
/*     */   public short getMatchType()
/*     */   {
/* 104 */     return this.nodeType;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 109 */     switch (this.nodeType)
/*     */     {
/*     */     case 1:
/* 112 */       return "child()";
/*     */     case 2:
/* 114 */       return "@*";
/*     */     case 13:
/* 116 */       return "namespace()";
/*     */     case 9:
/* 118 */       return "/";
/*     */     case 8:
/* 120 */       return "comment()";
/*     */     case 3:
/* 122 */       return "text()";
/*     */     case 7:
/* 124 */       return "processing-instruction()";
/*     */     case 4:
/*     */     case 5:
/*     */     case 6:
/*     */     case 10:
/*     */     case 11:
/* 126 */     case 12: } return "";
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 131 */     return super.toString() + "[ type: " + this.nodeType + " ]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NodeTypeTest
 * JD-Core Version:    0.6.2
 */