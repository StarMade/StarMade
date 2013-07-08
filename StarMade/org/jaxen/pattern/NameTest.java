package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.Navigator;

public class NameTest
  extends NodeTest
{
  private String name;
  private short nodeType;
  
  public NameTest(String name, short nodeType)
  {
    this.name = name;
    this.nodeType = nodeType;
  }
  
  public boolean matches(Object node, Context context)
  {
    Navigator navigator = context.getNavigator();
    if (this.nodeType == 1) {
      return (navigator.isElement(node)) && (this.name.equals(navigator.getElementName(node)));
    }
    if (this.nodeType == 2) {
      return (navigator.isAttribute(node)) && (this.name.equals(navigator.getAttributeName(node)));
    }
    if (navigator.isElement(node)) {
      return this.name.equals(navigator.getElementName(node));
    }
    if (navigator.isAttribute(node)) {
      return this.name.equals(navigator.getAttributeName(node));
    }
    return false;
  }
  
  public double getPriority()
  {
    return 0.0D;
  }
  
  public short getMatchType()
  {
    return this.nodeType;
  }
  
  public String getText()
  {
    if (this.nodeType == 2) {
      return "@" + this.name;
    }
    return this.name;
  }
  
  public String toString()
  {
    return super.toString() + "[ name: " + this.name + " type: " + this.nodeType + " ]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.NameTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */