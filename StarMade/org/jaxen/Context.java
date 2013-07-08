package org.jaxen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Context
  implements Serializable
{
  private static final long serialVersionUID = 2315979994685591055L;
  private ContextSupport contextSupport;
  private List nodeSet;
  private int size;
  private int position;
  
  public Context(ContextSupport contextSupport)
  {
    this.contextSupport = contextSupport;
    this.nodeSet = Collections.EMPTY_LIST;
    this.size = 0;
    this.position = 0;
  }
  
  public void setNodeSet(List nodeSet)
  {
    this.nodeSet = nodeSet;
    this.size = nodeSet.size();
    if (this.position >= this.size) {
      this.position = 0;
    }
  }
  
  public List getNodeSet()
  {
    return this.nodeSet;
  }
  
  public void setContextSupport(ContextSupport contextSupport)
  {
    this.contextSupport = contextSupport;
  }
  
  public ContextSupport getContextSupport()
  {
    return this.contextSupport;
  }
  
  public Navigator getNavigator()
  {
    return getContextSupport().getNavigator();
  }
  
  public String translateNamespacePrefixToUri(String prefix)
  {
    return getContextSupport().translateNamespacePrefixToUri(prefix);
  }
  
  public Object getVariableValue(String namespaceURI, String prefix, String localName)
    throws UnresolvableException
  {
    return getContextSupport().getVariableValue(namespaceURI, prefix, localName);
  }
  
  public Function getFunction(String namespaceURI, String prefix, String localName)
    throws UnresolvableException
  {
    return getContextSupport().getFunction(namespaceURI, prefix, localName);
  }
  
  public void setSize(int size)
  {
    this.size = size;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public void setPosition(int position)
  {
    this.position = position;
  }
  
  public int getPosition()
  {
    return this.position;
  }
  
  public Context duplicate()
  {
    Context dupe = new Context(getContextSupport());
    List thisNodeSet = getNodeSet();
    if (thisNodeSet != null)
    {
      List dupeNodeSet = new ArrayList(thisNodeSet.size());
      dupeNodeSet.addAll(thisNodeSet);
      dupe.setNodeSet(dupeNodeSet);
      dupe.setPosition(this.position);
    }
    return dupe;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.Context
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */