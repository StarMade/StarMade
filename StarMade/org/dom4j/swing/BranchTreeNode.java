package org.dom4j.swing;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;
import org.dom4j.Branch;
import org.dom4j.CharacterData;
import org.dom4j.Node;

public class BranchTreeNode
  extends LeafTreeNode
{
  protected List children;
  
  public BranchTreeNode() {}
  
  public BranchTreeNode(Branch xmlNode)
  {
    super(xmlNode);
  }
  
  public BranchTreeNode(TreeNode parent, Branch xmlNode)
  {
    super(parent, xmlNode);
  }
  
  public Enumeration children()
  {
    new Enumeration()
    {
      private int index = -1;
      
      public boolean hasMoreElements()
      {
        return this.index + 1 < BranchTreeNode.this.getChildCount();
      }
      
      public Object nextElement()
      {
        return BranchTreeNode.this.getChildAt(++this.index);
      }
    };
  }
  
  public boolean getAllowsChildren()
  {
    return true;
  }
  
  public TreeNode getChildAt(int childIndex)
  {
    return (TreeNode)getChildList().get(childIndex);
  }
  
  public int getChildCount()
  {
    return getChildList().size();
  }
  
  public int getIndex(TreeNode node)
  {
    return getChildList().indexOf(node);
  }
  
  public boolean isLeaf()
  {
    return getXmlBranch().nodeCount() <= 0;
  }
  
  public String toString()
  {
    return this.xmlNode.getName();
  }
  
  protected List getChildList()
  {
    if (this.children == null) {
      this.children = createChildList();
    }
    return this.children;
  }
  
  protected List createChildList()
  {
    Branch branch = getXmlBranch();
    int size = branch.nodeCount();
    List childList = new ArrayList(size);
    for (int local_i = 0; local_i < size; local_i++)
    {
      Node node = branch.node(local_i);
      if ((node instanceof CharacterData))
      {
        String text = node.getText();
        if (text != null)
        {
          text = text.trim();
          if (text.length() <= 0) {}
        }
      }
      else
      {
        childList.add(createChildTreeNode(node));
      }
    }
    return childList;
  }
  
  protected TreeNode createChildTreeNode(Node xmlNode)
  {
    if ((xmlNode instanceof Branch)) {
      return new BranchTreeNode(this, (Branch)xmlNode);
    }
    return new LeafTreeNode(this, xmlNode);
  }
  
  protected Branch getXmlBranch()
  {
    return (Branch)this.xmlNode;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.swing.BranchTreeNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */