package de.jarnbjo.util.io;

import java.io.IOException;

public final class HuffmanNode
{
  private HuffmanNode parent;
  private int depth = 0;
  protected HuffmanNode field_2235;
  protected HuffmanNode field_2236;
  protected Integer value;
  private boolean full = false;
  
  public HuffmanNode()
  {
    this(null);
  }
  
  protected HuffmanNode(HuffmanNode parent)
  {
    this.parent = parent;
    if (parent != null) {
      this.depth = (parent.getDepth() + 1);
    }
  }
  
  protected HuffmanNode(HuffmanNode parent, int value)
  {
    this(parent);
    this.value = new Integer(value);
    this.full = true;
  }
  
  protected int read(BitInputStream bis)
    throws IOException
  {
    for (HuffmanNode iter = this; iter.value == null; iter = bis.getBit() ? iter.field_2236 : iter.field_2235) {}
    return iter.value.intValue();
  }
  
  protected HuffmanNode get0()
  {
    return this.field_2235 == null ? set0(new HuffmanNode(this)) : this.field_2235;
  }
  
  protected HuffmanNode get1()
  {
    return this.field_2236 == null ? set1(new HuffmanNode(this)) : this.field_2236;
  }
  
  protected Integer getValue()
  {
    return this.value;
  }
  
  private HuffmanNode getParent()
  {
    return this.parent;
  }
  
  protected int getDepth()
  {
    return this.depth;
  }
  
  private boolean isFull()
  {
    return this.full = (this.field_2235 != null) && (this.field_2235.isFull()) && (this.field_2236 != null) && (this.field_2236.isFull()) ? 1 : 0;
  }
  
  private HuffmanNode set0(HuffmanNode value)
  {
    return this.field_2235 = value;
  }
  
  private HuffmanNode set1(HuffmanNode value)
  {
    return this.field_2236 = value;
  }
  
  private void setValue(Integer value)
  {
    this.full = true;
    this.value = value;
  }
  
  public boolean setNewValue(int depth, int value)
  {
    if (isFull()) {
      return false;
    }
    if (depth == 1)
    {
      if (this.field_2235 == null)
      {
        set0(new HuffmanNode(this, value));
        return true;
      }
      if (this.field_2236 == null)
      {
        set1(new HuffmanNode(this, value));
        return true;
      }
      return false;
    }
    return get0().setNewValue(depth - 1, value) ? true : get1().setNewValue(depth - 1, value);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.util.io.HuffmanNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */