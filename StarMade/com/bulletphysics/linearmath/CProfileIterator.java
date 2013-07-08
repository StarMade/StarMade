package com.bulletphysics.linearmath;

public class CProfileIterator
{
  private CProfileNode currentParent;
  private CProfileNode currentChild;
  
  CProfileIterator(CProfileNode start)
  {
    this.currentParent = start;
    this.currentChild = this.currentParent.getChild();
  }
  
  public void first()
  {
    this.currentChild = this.currentParent.getChild();
  }
  
  public void next()
  {
    this.currentChild = this.currentChild.getSibling();
  }
  
  public boolean isDone()
  {
    return this.currentChild == null;
  }
  
  public boolean isRoot()
  {
    return this.currentParent.getParent() == null;
  }
  
  public void enterChild(int index)
  {
    for (this.currentChild = this.currentParent.getChild(); (this.currentChild != null) && (index != 0); this.currentChild = this.currentChild.getSibling()) {
      index--;
    }
    if (this.currentChild != null)
    {
      this.currentParent = this.currentChild;
      this.currentChild = this.currentParent.getChild();
    }
  }
  
  public void enterParent()
  {
    if (this.currentParent.getParent() != null) {
      this.currentParent = this.currentParent.getParent();
    }
    this.currentChild = this.currentParent.getChild();
  }
  
  public String getCurrentName()
  {
    return this.currentChild.getName();
  }
  
  public int getCurrentTotalCalls()
  {
    return this.currentChild.getTotalCalls();
  }
  
  public float getCurrentTotalTime()
  {
    return this.currentChild.getTotalTime();
  }
  
  public String getCurrentParentName()
  {
    return this.currentParent.getName();
  }
  
  public int getCurrentParentTotalCalls()
  {
    return this.currentParent.getTotalCalls();
  }
  
  public float getCurrentParentTotalTime()
  {
    return this.currentParent.getTotalTime();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.CProfileIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */