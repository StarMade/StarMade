package org.dom4j.swing;

import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import org.dom4j.Node;

public class LeafTreeNode
  implements TreeNode
{
  protected static final Enumeration EMPTY_ENUMERATION = new Enumeration()
  {
    public boolean hasMoreElements()
    {
      return false;
    }
    
    public Object nextElement()
    {
      return null;
    }
  };
  private TreeNode parent;
  protected Node xmlNode;
  
  public LeafTreeNode() {}
  
  public LeafTreeNode(Node xmlNode)
  {
    this.xmlNode = xmlNode;
  }
  
  public LeafTreeNode(TreeNode parent, Node xmlNode)
  {
    this.parent = parent;
    this.xmlNode = xmlNode;
  }
  
  public Enumeration children()
  {
    return EMPTY_ENUMERATION;
  }
  
  public boolean getAllowsChildren()
  {
    return false;
  }
  
  public TreeNode getChildAt(int childIndex)
  {
    return null;
  }
  
  public int getChildCount()
  {
    return 0;
  }
  
  public int getIndex(TreeNode node)
  {
    return -1;
  }
  
  public TreeNode getParent()
  {
    return this.parent;
  }
  
  public boolean isLeaf()
  {
    return true;
  }
  
  public String toString()
  {
    String text = this.xmlNode.getText();
    return text != null ? text.trim() : "";
  }
  
  public void setParent(LeafTreeNode parent)
  {
    this.parent = parent;
  }
  
  public Node getXmlNode()
  {
    return this.xmlNode;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.swing.LeafTreeNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */