package org.dom4j.tree;

import java.util.List;

public class DefaultDocumentType
  extends AbstractDocumentType
{
  protected String elementName;
  private String publicID;
  private String systemID;
  private List internalDeclarations;
  private List externalDeclarations;
  
  public DefaultDocumentType() {}
  
  public DefaultDocumentType(String elementName, String systemID)
  {
    this.elementName = elementName;
    this.systemID = systemID;
  }
  
  public DefaultDocumentType(String elementName, String publicID, String systemID)
  {
    this.elementName = elementName;
    this.publicID = publicID;
    this.systemID = systemID;
  }
  
  public String getElementName()
  {
    return this.elementName;
  }
  
  public void setElementName(String elementName)
  {
    this.elementName = elementName;
  }
  
  public String getPublicID()
  {
    return this.publicID;
  }
  
  public void setPublicID(String publicID)
  {
    this.publicID = publicID;
  }
  
  public String getSystemID()
  {
    return this.systemID;
  }
  
  public void setSystemID(String systemID)
  {
    this.systemID = systemID;
  }
  
  public List getInternalDeclarations()
  {
    return this.internalDeclarations;
  }
  
  public void setInternalDeclarations(List internalDeclarations)
  {
    this.internalDeclarations = internalDeclarations;
  }
  
  public List getExternalDeclarations()
  {
    return this.externalDeclarations;
  }
  
  public void setExternalDeclarations(List externalDeclarations)
  {
    this.externalDeclarations = externalDeclarations;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.DefaultDocumentType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */