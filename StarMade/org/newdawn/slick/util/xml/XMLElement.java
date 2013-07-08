package org.newdawn.slick.util.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLElement
{
  private Element dom;
  private XMLElementList children;
  private String name;
  
  XMLElement(Element xmlElement)
  {
    this.dom = xmlElement;
    this.name = this.dom.getTagName();
  }
  
  public String[] getAttributeNames()
  {
    NamedNodeMap map = this.dom.getAttributes();
    String[] names = new String[map.getLength()];
    for (int local_i = 0; local_i < names.length; local_i++) {
      names[local_i] = map.item(local_i).getNodeName();
    }
    return names;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getAttribute(String name)
  {
    return this.dom.getAttribute(name);
  }
  
  public String getAttribute(String name, String def)
  {
    String value = this.dom.getAttribute(name);
    if ((value == null) || (value.length() == 0)) {
      return def;
    }
    return value;
  }
  
  public int getIntAttribute(String name)
    throws SlickXMLException
  {
    try
    {
      return Integer.parseInt(getAttribute(name));
    }
    catch (NumberFormatException local_e)
    {
      throw new SlickXMLException("Value read: '" + getAttribute(name) + "' is not an integer", local_e);
    }
  }
  
  public int getIntAttribute(String name, int def)
    throws SlickXMLException
  {
    try
    {
      return Integer.parseInt(getAttribute(name, "" + def));
    }
    catch (NumberFormatException local_e)
    {
      throw new SlickXMLException("Value read: '" + getAttribute(name, new StringBuilder().append("").append(def).toString()) + "' is not an integer", local_e);
    }
  }
  
  public double getDoubleAttribute(String name)
    throws SlickXMLException
  {
    try
    {
      return Double.parseDouble(getAttribute(name));
    }
    catch (NumberFormatException local_e)
    {
      throw new SlickXMLException("Value read: '" + getAttribute(name) + "' is not a double", local_e);
    }
  }
  
  public double getDoubleAttribute(String name, double def)
    throws SlickXMLException
  {
    try
    {
      return Double.parseDouble(getAttribute(name, "" + def));
    }
    catch (NumberFormatException local_e)
    {
      throw new SlickXMLException("Value read: '" + getAttribute(name, new StringBuilder().append("").append(def).toString()) + "' is not a double", local_e);
    }
  }
  
  public boolean getBooleanAttribute(String name)
    throws SlickXMLException
  {
    String value = getAttribute(name);
    if (value.equalsIgnoreCase("true")) {
      return true;
    }
    if (value.equalsIgnoreCase("false")) {
      return false;
    }
    throw new SlickXMLException("Value read: '" + getAttribute(name) + "' is not a boolean");
  }
  
  public boolean getBooleanAttribute(String name, boolean def)
    throws SlickXMLException
  {
    String value = getAttribute(name, "" + def);
    if (value.equalsIgnoreCase("true")) {
      return true;
    }
    if (value.equalsIgnoreCase("false")) {
      return false;
    }
    throw new SlickXMLException("Value read: '" + getAttribute(name, new StringBuilder().append("").append(def).toString()) + "' is not a boolean");
  }
  
  public String getContent()
  {
    String content = "";
    NodeList list = this.dom.getChildNodes();
    for (int local_i = 0; local_i < list.getLength(); local_i++) {
      if ((list.item(local_i) instanceof Text)) {
        content = content + list.item(local_i).getNodeValue();
      }
    }
    return content;
  }
  
  public XMLElementList getChildren()
  {
    if (this.children != null) {
      return this.children;
    }
    NodeList list = this.dom.getChildNodes();
    this.children = new XMLElementList();
    for (int local_i = 0; local_i < list.getLength(); local_i++) {
      if ((list.item(local_i) instanceof Element)) {
        this.children.add(new XMLElement((Element)list.item(local_i)));
      }
    }
    return this.children;
  }
  
  public XMLElementList getChildrenByName(String name)
  {
    XMLElementList selected = new XMLElementList();
    XMLElementList children = getChildren();
    for (int local_i = 0; local_i < children.size(); local_i++) {
      if (children.get(local_i).getName().equals(name)) {
        selected.add(children.get(local_i));
      }
    }
    return selected;
  }
  
  public String toString()
  {
    String value = "[XML " + getName();
    String[] attrs = getAttributeNames();
    for (int local_i = 0; local_i < attrs.length; local_i++) {
      value = value + " " + attrs[local_i] + "=" + getAttribute(attrs[local_i]);
    }
    value = value + "]";
    return value;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.xml.XMLElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */