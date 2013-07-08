package org.jaxen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SimpleNamespaceContext
  implements NamespaceContext, Serializable
{
  private static final long serialVersionUID = -808928409643497762L;
  private Map namespaces;
  
  public SimpleNamespaceContext()
  {
    this.namespaces = new HashMap();
  }
  
  public SimpleNamespaceContext(Map namespaces)
  {
    Iterator entries = namespaces.entrySet().iterator();
    while (entries.hasNext())
    {
      Map.Entry entry = (Map.Entry)entries.next();
      if ((!(entry.getKey() instanceof String)) || (!(entry.getValue() instanceof String))) {
        throw new ClassCastException("Non-string namespace binding");
      }
    }
    this.namespaces = new HashMap(namespaces);
  }
  
  public void addElementNamespaces(Navigator nav, Object element)
    throws UnsupportedAxisException
  {
    Iterator namespaceAxis = nav.getNamespaceAxisIterator(element);
    while (namespaceAxis.hasNext())
    {
      Object namespace = namespaceAxis.next();
      String prefix = nav.getNamespacePrefix(namespace);
      String uri = nav.getNamespaceStringValue(namespace);
      if (translateNamespacePrefixToUri(prefix) == null) {
        addNamespace(prefix, uri);
      }
    }
  }
  
  public void addNamespace(String prefix, String URI)
  {
    this.namespaces.put(prefix, URI);
  }
  
  public String translateNamespacePrefixToUri(String prefix)
  {
    if (this.namespaces.containsKey(prefix)) {
      return (String)this.namespaces.get(prefix);
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.SimpleNamespaceContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */