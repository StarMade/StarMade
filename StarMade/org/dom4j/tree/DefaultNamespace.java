package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Namespace;

public class DefaultNamespace
  extends Namespace
{
  private Element parent;
  
  public DefaultNamespace(String prefix, String uri)
  {
    super(prefix, uri);
  }
  
  public DefaultNamespace(Element parent, String prefix, String uri)
  {
    super(prefix, uri);
    this.parent = parent;
  }
  
  protected int createHashCode()
  {
    int hashCode = super.createHashCode();
    if (this.parent != null) {
      hashCode ^= this.parent.hashCode();
    }
    return hashCode;
  }
  
  public boolean equals(Object object)
  {
    if ((object instanceof DefaultNamespace))
    {
      DefaultNamespace that = (DefaultNamespace)object;
      if (that.parent == this.parent) {
        return super.equals(object);
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode();
  }
  
  public Element getParent()
  {
    return this.parent;
  }
  
  public void setParent(Element parent)
  {
    this.parent = parent;
  }
  
  public boolean supportsParent()
  {
    return true;
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.DefaultNamespace
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */