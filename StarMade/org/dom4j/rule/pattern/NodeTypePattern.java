/*    */ package org.dom4j.rule.pattern;
/*    */ 
/*    */ import org.dom4j.Node;
/*    */ import org.dom4j.rule.Pattern;
/*    */ 
/*    */ public class NodeTypePattern
/*    */   implements Pattern
/*    */ {
/* 24 */   public static final NodeTypePattern ANY_ATTRIBUTE = new NodeTypePattern((short)2);
/*    */ 
/* 28 */   public static final NodeTypePattern ANY_COMMENT = new NodeTypePattern((short)8);
/*    */ 
/* 32 */   public static final NodeTypePattern ANY_DOCUMENT = new NodeTypePattern((short)9);
/*    */ 
/* 36 */   public static final NodeTypePattern ANY_ELEMENT = new NodeTypePattern((short)1);
/*    */ 
/* 40 */   public static final NodeTypePattern ANY_PROCESSING_INSTRUCTION = new NodeTypePattern((short)7);
/*    */ 
/* 44 */   public static final NodeTypePattern ANY_TEXT = new NodeTypePattern((short)3);
/*    */   private short nodeType;
/*    */ 
/*    */   public NodeTypePattern(short nodeType)
/*    */   {
/* 50 */     this.nodeType = nodeType;
/*    */   }
/*    */ 
/*    */   public boolean matches(Node node) {
/* 54 */     return node.getNodeType() == this.nodeType;
/*    */   }
/*    */ 
/*    */   public double getPriority() {
/* 58 */     return 0.5D;
/*    */   }
/*    */ 
/*    */   public Pattern[] getUnionPatterns() {
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */   public short getMatchType() {
/* 66 */     return this.nodeType;
/*    */   }
/*    */ 
/*    */   public String getMatchesNodeName() {
/* 70 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.pattern.NodeTypePattern
 * JD-Core Version:    0.6.2
 */