package org.dom4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract interface Element extends Branch
{
  public abstract QName getQName();

  public abstract void setQName(QName paramQName);

  public abstract Namespace getNamespace();

  public abstract QName getQName(String paramString);

  public abstract Namespace getNamespaceForPrefix(String paramString);

  public abstract Namespace getNamespaceForURI(String paramString);

  public abstract List getNamespacesForURI(String paramString);

  public abstract String getNamespacePrefix();

  public abstract String getNamespaceURI();

  public abstract String getQualifiedName();

  public abstract List additionalNamespaces();

  public abstract List declaredNamespaces();

  public abstract Element addAttribute(String paramString1, String paramString2);

  public abstract Element addAttribute(QName paramQName, String paramString);

  public abstract Element addComment(String paramString);

  public abstract Element addCDATA(String paramString);

  public abstract Element addEntity(String paramString1, String paramString2);

  public abstract Element addNamespace(String paramString1, String paramString2);

  public abstract Element addProcessingInstruction(String paramString1, String paramString2);

  public abstract Element addProcessingInstruction(String paramString, Map paramMap);

  public abstract Element addText(String paramString);

  public abstract void add(Attribute paramAttribute);

  public abstract void add(CDATA paramCDATA);

  public abstract void add(Entity paramEntity);

  public abstract void add(Text paramText);

  public abstract void add(Namespace paramNamespace);

  public abstract boolean remove(Attribute paramAttribute);

  public abstract boolean remove(CDATA paramCDATA);

  public abstract boolean remove(Entity paramEntity);

  public abstract boolean remove(Namespace paramNamespace);

  public abstract boolean remove(Text paramText);

  public abstract String getText();

  public abstract String getTextTrim();

  public abstract String getStringValue();

  public abstract Object getData();

  public abstract void setData(Object paramObject);

  public abstract List attributes();

  public abstract void setAttributes(List paramList);

  public abstract int attributeCount();

  public abstract Iterator attributeIterator();

  public abstract Attribute attribute(int paramInt);

  public abstract Attribute attribute(String paramString);

  public abstract Attribute attribute(QName paramQName);

  public abstract String attributeValue(String paramString);

  public abstract String attributeValue(String paramString1, String paramString2);

  public abstract String attributeValue(QName paramQName);

  public abstract String attributeValue(QName paramQName, String paramString);

  /** @deprecated */
  public abstract void setAttributeValue(String paramString1, String paramString2);

  /** @deprecated */
  public abstract void setAttributeValue(QName paramQName, String paramString);

  public abstract Element element(String paramString);

  public abstract Element element(QName paramQName);

  public abstract List elements();

  public abstract List elements(String paramString);

  public abstract List elements(QName paramQName);

  public abstract Iterator elementIterator();

  public abstract Iterator elementIterator(String paramString);

  public abstract Iterator elementIterator(QName paramQName);

  public abstract boolean isRootElement();

  public abstract boolean hasMixedContent();

  public abstract boolean isTextOnly();

  public abstract void appendAttributes(Element paramElement);

  public abstract Element createCopy();

  public abstract Element createCopy(String paramString);

  public abstract Element createCopy(QName paramQName);

  public abstract String elementText(String paramString);

  public abstract String elementText(QName paramQName);

  public abstract String elementTextTrim(String paramString);

  public abstract String elementTextTrim(QName paramQName);

  public abstract Node getXPathResult(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.Element
 * JD-Core Version:    0.6.2
 */