/*     */ package org.dom4j.swing;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import javax.swing.tree.TreeNode;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class LeafTreeNode
/*     */   implements TreeNode
/*     */ {
/*  27 */   protected static final Enumeration EMPTY_ENUMERATION = new Enumeration() {
/*     */     public boolean hasMoreElements() {
/*  29 */       return false;
/*     */     }
/*     */ 
/*     */     public Object nextElement() {
/*  33 */       return null;
/*     */     }
/*  27 */   };
/*     */   private TreeNode parent;
/*     */   protected Node xmlNode;
/*     */ 
/*     */   public LeafTreeNode()
/*     */   {
/*     */   }
/*     */ 
/*     */   public LeafTreeNode(Node xmlNode)
/*     */   {
/*  47 */     this.xmlNode = xmlNode;
/*     */   }
/*     */ 
/*     */   public LeafTreeNode(TreeNode parent, Node xmlNode) {
/*  51 */     this.parent = parent;
/*  52 */     this.xmlNode = xmlNode;
/*     */   }
/*     */ 
/*     */   public Enumeration children()
/*     */   {
/*  58 */     return EMPTY_ENUMERATION;
/*     */   }
/*     */ 
/*     */   public boolean getAllowsChildren() {
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */   public TreeNode getChildAt(int childIndex) {
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */   public int getChildCount() {
/*  70 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getIndex(TreeNode node) {
/*  74 */     return -1;
/*     */   }
/*     */ 
/*     */   public TreeNode getParent() {
/*  78 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public boolean isLeaf() {
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  87 */     String text = this.xmlNode.getText();
/*     */ 
/*  89 */     return text != null ? text.trim() : "";
/*     */   }
/*     */ 
/*     */   public void setParent(LeafTreeNode parent)
/*     */   {
/* 102 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public Node getXmlNode() {
/* 106 */     return this.xmlNode;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.LeafTreeNode
 * JD-Core Version:    0.6.2
 */