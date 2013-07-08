package org.jaxen.saxpath;

public abstract interface XPathReader
  extends SAXPathEventSource
{
  public abstract void parse(String paramString)
    throws SAXPathException;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.XPathReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */