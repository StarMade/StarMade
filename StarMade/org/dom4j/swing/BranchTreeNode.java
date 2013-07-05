/*     */ package org.dom4j.swing;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import javax.swing.tree.TreeNode;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.CharacterData;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class BranchTreeNode extends LeafTreeNode
/*     */ {
/*     */   protected List children;
/*     */ 
/*     */   public BranchTreeNode()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BranchTreeNode(Branch xmlNode)
/*     */   {
/*  39 */     super(xmlNode);
/*     */   }
/*     */ 
/*     */   public BranchTreeNode(TreeNode parent, Branch xmlNode) {
/*  43 */     super(parent, xmlNode);
/*     */   }
/*     */ 
/*     */   public Enumeration children()
/*     */   {
/*  49 */     return new Enumeration() {
/*  50 */       private int index = -1;
/*     */ 
/*     */       public boolean hasMoreElements() {
/*  53 */         return this.index + 1 < BranchTreeNode.this.getChildCount();
/*     */       }
/*     */ 
/*     */       public Object nextElement() {
/*  57 */         return BranchTreeNode.this.getChildAt(++this.index);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public boolean getAllowsChildren() {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   public TreeNode getChildAt(int childIndex) {
/*  67 */     return (TreeNode)getChildList().get(childIndex);
/*     */   }
/*     */ 
/*     */   public int getChildCount() {
/*  71 */     return getChildList().size();
/*     */   }
/*     */ 
/*     */   public int getIndex(TreeNode node) {
/*  75 */     return getChildList().indexOf(node);
/*     */   }
/*     */ 
/*     */   public boolean isLeaf() {
/*  79 */     return getXmlBranch().nodeCount() <= 0;
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  83 */     return this.xmlNode.getName();
/*     */   }
/*     */ 
/*     */   protected List getChildList()
/*     */   {
/*  98 */     if (this.children == null) {
/*  99 */       this.children = createChildList();
/*     */     }
/*     */ 
/* 102 */     return this.children;
/*     */   }
/*     */ 
/*     */   protected List createChildList()
/*     */   {
/* 112 */     Branch branch = getXmlBranch();
/* 113 */     int size = branch.nodeCount();
/* 114 */     List childList = new ArrayList(size);
/*     */ 
/* 116 */     for (int i = 0; i < size; i++) {
/* 117 */       Node node = branch.node(i);
/*     */ 
/* 120 */       if ((node instanceof CharacterData)) {
/* 121 */         String text = node.getText();
/*     */ 
/* 123 */         if (text != null)
/*     */         {
/* 127 */           text = text.trim();
/*     */ 
/* 129 */           if (text.length() <= 0);
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 134 */         childList.add(createChildTreeNode(node));
/*     */       }
/*     */     }
/* 137 */     return childList;
/*     */   }
/*     */ 
/*     */   protected TreeNode createChildTreeNode(Node xmlNode)
/*     */   {
/* 149 */     if ((xmlNode instanceof Branch)) {
/* 150 */       return new BranchTreeNode(this, (Branch)xmlNode);
/*     */     }
/* 152 */     return new LeafTreeNode(this, xmlNode);
/*     */   }
/*     */ 
/*     */   protected Branch getXmlBranch()
/*     */   {
/* 157 */     return (Branch)this.xmlNode;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.BranchTreeNode
 * JD-Core Version:    0.6.2
 */