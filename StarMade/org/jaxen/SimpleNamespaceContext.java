/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.Map;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.Set;
/*   9:    */
/*  67:    */public class SimpleNamespaceContext
/*  68:    */  implements NamespaceContext, Serializable
/*  69:    */{
/*  70:    */  private static final long serialVersionUID = -808928409643497762L;
/*  71:    */  private Map namespaces;
/*  72:    */  
/*  73:    */  public SimpleNamespaceContext()
/*  74:    */  {
/*  75: 75 */    this.namespaces = new HashMap();
/*  76:    */  }
/*  77:    */  
/*  88:    */  public SimpleNamespaceContext(Map namespaces)
/*  89:    */  {
/*  90: 90 */    Iterator entries = namespaces.entrySet().iterator();
/*  91: 91 */    while (entries.hasNext()) {
/*  92: 92 */      Map.Entry entry = (Map.Entry)entries.next();
/*  93: 93 */      if ((!(entry.getKey() instanceof String)) || (!(entry.getValue() instanceof String)))
/*  94:    */      {
/*  95: 95 */        throw new ClassCastException("Non-string namespace binding");
/*  96:    */      }
/*  97:    */    }
/*  98: 98 */    this.namespaces = new HashMap(namespaces);
/*  99:    */  }
/* 100:    */  
/* 113:    */  public void addElementNamespaces(Navigator nav, Object element)
/* 114:    */    throws UnsupportedAxisException
/* 115:    */  {
/* 116:116 */    Iterator namespaceAxis = nav.getNamespaceAxisIterator(element);
/* 117:    */    
/* 118:118 */    while (namespaceAxis.hasNext()) {
/* 119:119 */      Object namespace = namespaceAxis.next();
/* 120:120 */      String prefix = nav.getNamespacePrefix(namespace);
/* 121:121 */      String uri = nav.getNamespaceStringValue(namespace);
/* 122:122 */      if (translateNamespacePrefixToUri(prefix) == null) {
/* 123:123 */        addNamespace(prefix, uri);
/* 124:    */      }
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 135:    */  public void addNamespace(String prefix, String URI)
/* 136:    */  {
/* 137:137 */    this.namespaces.put(prefix, URI);
/* 138:    */  }
/* 139:    */  
/* 140:    */  public String translateNamespacePrefixToUri(String prefix)
/* 141:    */  {
/* 142:142 */    if (this.namespaces.containsKey(prefix))
/* 143:    */    {
/* 144:144 */      return (String)this.namespaces.get(prefix);
/* 145:    */    }
/* 146:    */    
/* 147:147 */    return null;
/* 148:    */  }
/* 149:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.SimpleNamespaceContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */