package org.dom4j;

import java.util.Map;
import org.xml.sax.EntityResolver;

public abstract interface Document extends Branch
{
  public abstract Element getRootElement();

  public abstract void setRootElement(Element paramElement);

  public abstract Document addComment(String paramString);

  public abstract Document addProcessingInstruction(String paramString1, String paramString2);

  public abstract Document addProcessingInstruction(String paramString, Map paramMap);

  public abstract Document addDocType(String paramString1, String paramString2, String paramString3);

  public abstract DocumentType getDocType();

  public abstract void setDocType(DocumentType paramDocumentType);

  public abstract EntityResolver getEntityResolver();

  public abstract void setEntityResolver(EntityResolver paramEntityResolver);

  public abstract String getXMLEncoding();

  public abstract void setXMLEncoding(String paramString);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.Document
 * JD-Core Version:    0.6.2
 */