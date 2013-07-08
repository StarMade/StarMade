package org.newdawn.slick.util.xml;

import java.util.ArrayList;
import java.util.Collection;

public class XMLElementList
{
  private ArrayList list = new ArrayList();
  
  public void add(XMLElement element)
  {
    this.list.add(element);
  }
  
  public int size()
  {
    return this.list.size();
  }
  
  public XMLElement get(int local_i)
  {
    return (XMLElement)this.list.get(local_i);
  }
  
  public boolean contains(XMLElement element)
  {
    return this.list.contains(element);
  }
  
  public void addAllTo(Collection collection)
  {
    collection.addAll(this.list);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.xml.XMLElementList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */