package org.apache.ws.commons.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.NamespaceContext;

public class NamespaceContextImpl
  implements NamespaceContext
{
  private List prefixList;
  private String cachedPrefix;
  private String cachedURI;
  
  public void reset()
  {
    this.cachedURI = (this.cachedPrefix = null);
    if (this.prefixList != null) {
      this.prefixList.clear();
    }
  }
  
  public void startPrefixMapping(String pPrefix, String pURI)
  {
    if (pPrefix == null) {
      throw new IllegalArgumentException("The namespace prefix must not be null.");
    }
    if (pURI == null) {
      throw new IllegalArgumentException("The namespace prefix must not be null.");
    }
    if (this.cachedURI != null)
    {
      if (this.prefixList == null) {
        this.prefixList = new ArrayList();
      }
      this.prefixList.add(this.cachedPrefix);
      this.prefixList.add(this.cachedURI);
    }
    this.cachedURI = pURI;
    this.cachedPrefix = pPrefix;
  }
  
  public void endPrefixMapping(String pPrefix)
  {
    if (pPrefix == null) {
      throw new IllegalArgumentException("The namespace prefix must not be null.");
    }
    if (pPrefix.equals(this.cachedPrefix))
    {
      if ((this.prefixList != null) && (this.prefixList.size() > 0))
      {
        this.cachedURI = this.prefixList.remove(this.prefixList.size() - 1).toString();
        this.cachedPrefix = this.prefixList.remove(this.prefixList.size() - 1).toString();
      }
      else
      {
        this.cachedPrefix = (this.cachedURI = null);
      }
    }
    else {
      throw new IllegalStateException("The prefix " + pPrefix + " isn't the prefix, which has been defined last.");
    }
  }
  
  public String getNamespaceURI(String pPrefix)
  {
    if (pPrefix == null) {
      throw new IllegalArgumentException("The namespace prefix must not be null.");
    }
    if (this.cachedURI != null)
    {
      if (this.cachedPrefix.equals(pPrefix)) {
        return this.cachedURI;
      }
      if (this.prefixList != null) {
        for (int local_i = this.prefixList.size(); local_i > 0; local_i -= 2) {
          if (pPrefix.equals(this.prefixList.get(local_i - 2))) {
            return (String)this.prefixList.get(local_i - 1);
          }
        }
      }
    }
    if ("xml".equals(pPrefix)) {
      return "http://www.w3.org/XML/1998/namespace";
    }
    if ("xmlns".equals(pPrefix)) {
      return "http://www.w3.org/2000/xmlns/";
    }
    return null;
  }
  
  public String getPrefix(String pURI)
  {
    if (pURI == null) {
      throw new IllegalArgumentException("The namespace URI must not be null.");
    }
    if (this.cachedURI != null)
    {
      if (this.cachedURI.equals(pURI)) {
        return this.cachedPrefix;
      }
      if (this.prefixList != null) {
        for (int local_i = this.prefixList.size(); local_i > 0; local_i -= 2) {
          if (pURI.equals(this.prefixList.get(local_i - 1))) {
            return (String)this.prefixList.get(local_i - 2);
          }
        }
      }
    }
    if ("http://www.w3.org/XML/1998/namespace".equals(pURI)) {
      return "xml";
    }
    if ("http://www.w3.org/2000/xmlns/".equals(pURI)) {
      return "xmlns";
    }
    return null;
  }
  
  public String getAttributePrefix(String pURI)
  {
    if (pURI == null) {
      throw new IllegalArgumentException("The namespace URI must not be null.");
    }
    if (pURI.length() == 0) {
      return "";
    }
    if (this.cachedURI != null)
    {
      if ((this.cachedURI.equals(pURI)) && (this.cachedPrefix.length() > 0)) {
        return this.cachedPrefix;
      }
      if (this.prefixList != null) {
        for (int local_i = this.prefixList.size(); local_i > 0; local_i -= 2) {
          if (pURI.equals(this.prefixList.get(local_i - 1)))
          {
            String prefix = (String)this.prefixList.get(local_i - 2);
            if (prefix.length() > 0) {
              return prefix;
            }
          }
        }
      }
    }
    if ("http://www.w3.org/XML/1998/namespace".equals(pURI)) {
      return "xml";
    }
    if ("http://www.w3.org/2000/xmlns/".equals(pURI)) {
      return "xmlns";
    }
    return null;
  }
  
  public Iterator getPrefixes(String pURI)
  {
    if (pURI == null) {
      throw new IllegalArgumentException("The namespace URI must not be null.");
    }
    List list = new ArrayList();
    if (this.cachedURI != null)
    {
      if (this.cachedURI.equals(pURI)) {
        list.add(this.cachedPrefix);
      }
      if (this.prefixList != null) {
        for (int local_i = this.prefixList.size(); local_i > 0; local_i -= 2) {
          if (pURI.equals(this.prefixList.get(local_i - 1))) {
            list.add(this.prefixList.get(local_i - 2));
          }
        }
      }
    }
    if (pURI.equals("http://www.w3.org/2000/xmlns/")) {
      list.add("xmlns");
    } else if (pURI.equals("http://www.w3.org/XML/1998/namespace")) {
      list.add("xml");
    }
    return list.iterator();
  }
  
  public boolean isPrefixDeclared(String pPrefix)
  {
    if (this.cachedURI != null)
    {
      if ((this.cachedPrefix != null) && (this.cachedPrefix.equals(pPrefix))) {
        return true;
      }
      if (this.prefixList != null) {
        for (int local_i = this.prefixList.size(); local_i > 0; local_i -= 2) {
          if (this.prefixList.get(local_i - 2).equals(pPrefix)) {
            return true;
          }
        }
      }
    }
    return "xml".equals(pPrefix);
  }
  
  public int getContext()
  {
    return (this.prefixList == null ? 0 : this.prefixList.size()) + (this.cachedURI == null ? 0 : 2);
  }
  
  public String checkContext(int local_i)
  {
    if (getContext() == local_i) {
      return null;
    }
    String result = this.cachedPrefix;
    if ((this.prefixList != null) && (this.prefixList.size() > 0))
    {
      this.cachedURI = this.prefixList.remove(this.prefixList.size() - 1).toString();
      this.cachedPrefix = this.prefixList.remove(this.prefixList.size() - 1).toString();
    }
    else
    {
      this.cachedURI = null;
      this.cachedPrefix = null;
    }
    return result;
  }
  
  public List getPrefixes()
  {
    if (this.cachedPrefix == null) {
      return Collections.EMPTY_LIST;
    }
    if (this.prefixList == null) {
      return Collections.singletonList(this.cachedPrefix);
    }
    List result = new ArrayList(this.prefixList.size() + 1);
    for (int local_i = 0; local_i < this.prefixList.size(); local_i += 2) {
      result.add(this.prefixList.get(local_i));
    }
    result.add(this.cachedPrefix);
    return result;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.util.NamespaceContextImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */