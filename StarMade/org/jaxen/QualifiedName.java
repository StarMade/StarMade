package org.jaxen;

import java.io.Serializable;

class QualifiedName
  implements Serializable
{
  private static final long serialVersionUID = 2734958615642751535L;
  private String namespaceURI;
  private String localName;
  
  QualifiedName(String namespaceURI, String localName)
  {
    if (namespaceURI == null) {
      namespaceURI = "";
    }
    this.namespaceURI = namespaceURI;
    this.localName = localName;
  }
  
  public int hashCode()
  {
    return this.localName.hashCode() ^ this.namespaceURI.hashCode();
  }
  
  public boolean equals(Object local_o)
  {
    QualifiedName other = (QualifiedName)local_o;
    return (this.namespaceURI.equals(other.namespaceURI)) && (other.localName.equals(this.localName));
  }
  
  String getClarkForm()
  {
    if ("".equals(this.namespaceURI)) {
      return this.localName;
    }
    return "{" + this.namespaceURI + "}" + ":" + this.localName;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.QualifiedName
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */