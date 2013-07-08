package org.dom4j;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public abstract interface Node
  extends Cloneable
{
  public static final short ANY_NODE = 0;
  public static final short ELEMENT_NODE = 1;
  public static final short ATTRIBUTE_NODE = 2;
  public static final short TEXT_NODE = 3;
  public static final short CDATA_SECTION_NODE = 4;
  public static final short ENTITY_REFERENCE_NODE = 5;
  public static final short PROCESSING_INSTRUCTION_NODE = 7;
  public static final short COMMENT_NODE = 8;
  public static final short DOCUMENT_NODE = 9;
  public static final short DOCUMENT_TYPE_NODE = 10;
  public static final short NAMESPACE_NODE = 13;
  public static final short UNKNOWN_NODE = 14;
  public static final short MAX_NODE_TYPE = 14;
  
  public abstract boolean supportsParent();
  
  public abstract Element getParent();
  
  public abstract void setParent(Element paramElement);
  
  public abstract Document getDocument();
  
  public abstract void setDocument(Document paramDocument);
  
  public abstract boolean isReadOnly();
  
  public abstract boolean hasContent();
  
  public abstract String getName();
  
  public abstract void setName(String paramString);
  
  public abstract String getText();
  
  public abstract void setText(String paramString);
  
  public abstract String getStringValue();
  
  public abstract String getPath();
  
  public abstract String getPath(Element paramElement);
  
  public abstract String getUniquePath();
  
  public abstract String getUniquePath(Element paramElement);
  
  public abstract String asXML();
  
  public abstract void write(Writer paramWriter)
    throws IOException;
  
  public abstract short getNodeType();
  
  public abstract String getNodeTypeName();
  
  public abstract Node detach();
  
  public abstract List selectNodes(String paramString);
  
  public abstract Object selectObject(String paramString);
  
  public abstract List selectNodes(String paramString1, String paramString2);
  
  public abstract List selectNodes(String paramString1, String paramString2, boolean paramBoolean);
  
  public abstract Node selectSingleNode(String paramString);
  
  public abstract String valueOf(String paramString);
  
  public abstract Number numberValueOf(String paramString);
  
  public abstract boolean matches(String paramString);
  
  public abstract XPath createXPath(String paramString)
    throws InvalidXPathException;
  
  public abstract Node asXPathResult(Element paramElement);
  
  public abstract void accept(Visitor paramVisitor);
  
  public abstract Object clone();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.Node
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */