package com.bulletphysics.linearmath;

import com.bulletphysics.BulletStats;

class CProfileNode
{
  protected String name;
  protected int totalCalls;
  protected float totalTime;
  protected long startTime;
  protected int recursionCounter;
  protected CProfileNode parent;
  protected CProfileNode child;
  protected CProfileNode sibling;
  
  public CProfileNode(String name, CProfileNode parent)
  {
    this.name = name;
    this.totalCalls = 0;
    this.totalTime = 0.0F;
    this.startTime = 0L;
    this.recursionCounter = 0;
    this.parent = parent;
    this.child = null;
    this.sibling = null;
    reset();
  }
  
  public CProfileNode getSubNode(String name)
  {
    for (CProfileNode child = this.child; child != null; child = child.sibling) {
      if (child.name == name) {
        return child;
      }
    }
    CProfileNode node = new CProfileNode(name, this);
    node.sibling = this.child;
    this.child = node;
    return node;
  }
  
  public CProfileNode getParent()
  {
    return this.parent;
  }
  
  public CProfileNode getSibling()
  {
    return this.sibling;
  }
  
  public CProfileNode getChild()
  {
    return this.child;
  }
  
  public void cleanupMemory()
  {
    this.child = null;
    this.sibling = null;
  }
  
  public void reset()
  {
    this.totalCalls = 0;
    this.totalTime = 0.0F;
    BulletStats.gProfileClock.reset();
    if (this.child != null) {
      this.child.reset();
    }
    if (this.sibling != null) {
      this.sibling.reset();
    }
  }
  
  public void call()
  {
    this.totalCalls += 1;
    if (this.recursionCounter++ == 0) {
      this.startTime = BulletStats.profileGetTicks();
    }
  }
  
  public boolean Return()
  {
    if ((--this.recursionCounter == 0) && (this.totalCalls != 0))
    {
      long time = BulletStats.profileGetTicks();
      time -= this.startTime;
      this.totalTime += (float)time / BulletStats.profileGetTickRate();
    }
    return this.recursionCounter == 0;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getTotalCalls()
  {
    return this.totalCalls;
  }
  
  public float getTotalTime()
  {
    return this.totalTime;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.CProfileNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */