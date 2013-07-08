/*   1:    */package org.dom4j.swing;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Enumeration;
/*   5:    */import java.util.List;
/*   6:    */import javax.swing.tree.TreeNode;
/*   7:    */import org.dom4j.Branch;
/*   8:    */import org.dom4j.CharacterData;
/*   9:    */import org.dom4j.Node;
/*  10:    */
/*  30:    */public class BranchTreeNode
/*  31:    */  extends LeafTreeNode
/*  32:    */{
/*  33:    */  protected List children;
/*  34:    */  
/*  35:    */  public BranchTreeNode() {}
/*  36:    */  
/*  37:    */  public BranchTreeNode(Branch xmlNode)
/*  38:    */  {
/*  39: 39 */    super(xmlNode);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public BranchTreeNode(TreeNode parent, Branch xmlNode) {
/*  43: 43 */    super(parent, xmlNode);
/*  44:    */  }
/*  45:    */  
/*  47:    */  public Enumeration children()
/*  48:    */  {
/*  49: 49 */    new Enumeration() {
/*  50: 50 */      private int index = -1;
/*  51:    */      
/*  52:    */      public boolean hasMoreElements() {
/*  53: 53 */        return this.index + 1 < BranchTreeNode.this.getChildCount();
/*  54:    */      }
/*  55:    */      
/*  56:    */      public Object nextElement() {
/*  57: 57 */        return BranchTreeNode.this.getChildAt(++this.index);
/*  58:    */      }
/*  59:    */    };
/*  60:    */  }
/*  61:    */  
/*  62:    */  public boolean getAllowsChildren() {
/*  63: 63 */    return true;
/*  64:    */  }
/*  65:    */  
/*  66:    */  public TreeNode getChildAt(int childIndex) {
/*  67: 67 */    return (TreeNode)getChildList().get(childIndex);
/*  68:    */  }
/*  69:    */  
/*  70:    */  public int getChildCount() {
/*  71: 71 */    return getChildList().size();
/*  72:    */  }
/*  73:    */  
/*  74:    */  public int getIndex(TreeNode node) {
/*  75: 75 */    return getChildList().indexOf(node);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public boolean isLeaf() {
/*  79: 79 */    return getXmlBranch().nodeCount() <= 0;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public String toString() {
/*  83: 83 */    return this.xmlNode.getName();
/*  84:    */  }
/*  85:    */  
/*  96:    */  protected List getChildList()
/*  97:    */  {
/*  98: 98 */    if (this.children == null) {
/*  99: 99 */      this.children = createChildList();
/* 100:    */    }
/* 101:    */    
/* 102:102 */    return this.children;
/* 103:    */  }
/* 104:    */  
/* 110:    */  protected List createChildList()
/* 111:    */  {
/* 112:112 */    Branch branch = getXmlBranch();
/* 113:113 */    int size = branch.nodeCount();
/* 114:114 */    List childList = new ArrayList(size);
/* 115:    */    
/* 116:116 */    for (int i = 0; i < size; i++) {
/* 117:117 */      Node node = branch.node(i);
/* 118:    */      
/* 120:120 */      if ((node instanceof CharacterData)) {
/* 121:121 */        String text = node.getText();
/* 122:    */        
/* 123:123 */        if (text != null)
/* 124:    */        {
/* 127:127 */          text = text.trim();
/* 128:    */          
/* 129:129 */          if (text.length() <= 0) {}
/* 130:    */        }
/* 131:    */      }
/* 132:    */      else
/* 133:    */      {
/* 134:134 */        childList.add(createChildTreeNode(node));
/* 135:    */      }
/* 136:    */    }
/* 137:137 */    return childList;
/* 138:    */  }
/* 139:    */  
/* 147:    */  protected TreeNode createChildTreeNode(Node xmlNode)
/* 148:    */  {
/* 149:149 */    if ((xmlNode instanceof Branch)) {
/* 150:150 */      return new BranchTreeNode(this, (Branch)xmlNode);
/* 151:    */    }
/* 152:152 */    return new LeafTreeNode(this, xmlNode);
/* 153:    */  }
/* 154:    */  
/* 155:    */  protected Branch getXmlBranch()
/* 156:    */  {
/* 157:157 */    return (Branch)this.xmlNode;
/* 158:    */  }
/* 159:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.BranchTreeNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */