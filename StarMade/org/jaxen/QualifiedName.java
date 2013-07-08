/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  65:    */class QualifiedName
/*  66:    */  implements Serializable
/*  67:    */{
/*  68:    */  private static final long serialVersionUID = 2734958615642751535L;
/*  69:    */  private String namespaceURI;
/*  70:    */  private String localName;
/*  71:    */  
/*  72:    */  QualifiedName(String namespaceURI, String localName)
/*  73:    */  {
/*  74: 74 */    if (namespaceURI == null) namespaceURI = "";
/*  75: 75 */    this.namespaceURI = namespaceURI;
/*  76: 76 */    this.localName = localName;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public int hashCode()
/*  80:    */  {
/*  81: 81 */    return this.localName.hashCode() ^ this.namespaceURI.hashCode();
/*  82:    */  }
/*  83:    */  
/*  88:    */  public boolean equals(Object o)
/*  89:    */  {
/*  90: 90 */    QualifiedName other = (QualifiedName)o;
/*  91: 91 */    return (this.namespaceURI.equals(other.namespaceURI)) && (other.localName.equals(this.localName));
/*  92:    */  }
/*  93:    */  
/*  97:    */  String getClarkForm()
/*  98:    */  {
/*  99: 99 */    if ("".equals(this.namespaceURI)) return this.localName;
/* 100:100 */    return "{" + this.namespaceURI + "}" + ":" + this.localName;
/* 101:    */  }
/* 102:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.QualifiedName
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */