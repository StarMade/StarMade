/*   1:    */package org.dom4j.swing;
/*   2:    */
/*   3:    */import java.util.Enumeration;
/*   4:    */import javax.swing.tree.TreeNode;
/*   5:    */import org.dom4j.Node;
/*   6:    */
/*  24:    */public class LeafTreeNode
/*  25:    */  implements TreeNode
/*  26:    */{
/*  27: 27 */  protected static final Enumeration EMPTY_ENUMERATION = new Enumeration() {
/*  28:    */    public boolean hasMoreElements() {
/*  29: 29 */      return false;
/*  30:    */    }
/*  31:    */    
/*  32:    */    public Object nextElement() {
/*  33: 33 */      return null;
/*  34:    */    }
/*  35:    */  };
/*  36:    */  
/*  37:    */  private TreeNode parent;
/*  38:    */  
/*  39:    */  protected Node xmlNode;
/*  40:    */  
/*  42:    */  public LeafTreeNode() {}
/*  43:    */  
/*  45:    */  public LeafTreeNode(Node xmlNode)
/*  46:    */  {
/*  47: 47 */    this.xmlNode = xmlNode;
/*  48:    */  }
/*  49:    */  
/*  50:    */  public LeafTreeNode(TreeNode parent, Node xmlNode) {
/*  51: 51 */    this.parent = parent;
/*  52: 52 */    this.xmlNode = xmlNode;
/*  53:    */  }
/*  54:    */  
/*  56:    */  public Enumeration children()
/*  57:    */  {
/*  58: 58 */    return EMPTY_ENUMERATION;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public boolean getAllowsChildren() {
/*  62: 62 */    return false;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public TreeNode getChildAt(int childIndex) {
/*  66: 66 */    return null;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public int getChildCount() {
/*  70: 70 */    return 0;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public int getIndex(TreeNode node) {
/*  74: 74 */    return -1;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public TreeNode getParent() {
/*  78: 78 */    return this.parent;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public boolean isLeaf() {
/*  82: 82 */    return true;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String toString()
/*  86:    */  {
/*  87: 87 */    String text = this.xmlNode.getText();
/*  88:    */    
/*  89: 89 */    return text != null ? text.trim() : "";
/*  90:    */  }
/*  91:    */  
/* 100:    */  public void setParent(LeafTreeNode parent)
/* 101:    */  {
/* 102:102 */    this.parent = parent;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public Node getXmlNode() {
/* 106:106 */    return this.xmlNode;
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.LeafTreeNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */