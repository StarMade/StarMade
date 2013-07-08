package org.dom4j.io;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.QName;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XPP3Reader
{
  private DocumentFactory factory;
  private XmlPullParser xppParser;
  private XmlPullParserFactory xppFactory;
  private DispatchHandler dispatchHandler;
  
  public XPP3Reader() {}
  
  public XPP3Reader(DocumentFactory factory)
  {
    this.factory = factory;
  }
  
  public Document read(File file)
    throws DocumentException, IOException, XmlPullParserException
  {
    String systemID = file.getAbsolutePath();
    return read(new BufferedReader(new FileReader(file)), systemID);
  }
  
  public Document read(URL url)
    throws DocumentException, IOException, XmlPullParserException
  {
    String systemID = url.toExternalForm();
    return read(createReader(url.openStream()), systemID);
  }
  
  public Document read(String systemID)
    throws DocumentException, IOException, XmlPullParserException
  {
    if (systemID.indexOf(':') >= 0) {
      return read(new URL(systemID));
    }
    return read(new File(systemID));
  }
  
  public Document read(InputStream local_in)
    throws DocumentException, IOException, XmlPullParserException
  {
    return read(createReader(local_in));
  }
  
  public Document read(Reader reader)
    throws DocumentException, IOException, XmlPullParserException
  {
    getXPPParser().setInput(reader);
    return parseDocument();
  }
  
  public Document read(char[] text)
    throws DocumentException, IOException, XmlPullParserException
  {
    getXPPParser().setInput(new CharArrayReader(text));
    return parseDocument();
  }
  
  public Document read(InputStream local_in, String systemID)
    throws DocumentException, IOException, XmlPullParserException
  {
    return read(createReader(local_in), systemID);
  }
  
  public Document read(Reader reader, String systemID)
    throws DocumentException, IOException, XmlPullParserException
  {
    Document document = read(reader);
    document.setName(systemID);
    return document;
  }
  
  public XmlPullParser getXPPParser()
    throws XmlPullParserException
  {
    if (this.xppParser == null) {
      this.xppParser = getXPPFactory().newPullParser();
    }
    return this.xppParser;
  }
  
  public XmlPullParserFactory getXPPFactory()
    throws XmlPullParserException
  {
    if (this.xppFactory == null) {
      this.xppFactory = XmlPullParserFactory.newInstance();
    }
    this.xppFactory.setNamespaceAware(true);
    return this.xppFactory;
  }
  
  public void setXPPFactory(XmlPullParserFactory xPPfactory)
  {
    this.xppFactory = xPPfactory;
  }
  
  public DocumentFactory getDocumentFactory()
  {
    if (this.factory == null) {
      this.factory = DocumentFactory.getInstance();
    }
    return this.factory;
  }
  
  public void setDocumentFactory(DocumentFactory documentFactory)
  {
    this.factory = documentFactory;
  }
  
  public void addHandler(String path, ElementHandler handler)
  {
    getDispatchHandler().addHandler(path, handler);
  }
  
  public void removeHandler(String path)
  {
    getDispatchHandler().removeHandler(path);
  }
  
  public void setDefaultHandler(ElementHandler handler)
  {
    getDispatchHandler().setDefaultHandler(handler);
  }
  
  protected Document parseDocument()
    throws DocumentException, IOException, XmlPullParserException
  {
    DocumentFactory local_df = getDocumentFactory();
    Document document = local_df.createDocument();
    Element parent = null;
    XmlPullParser local_pp = getXPPParser();
    local_pp.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
    for (;;)
    {
      int type = local_pp.nextToken();
      switch (type)
      {
      case 8: 
        String text = local_pp.getText();
        int loc = text.indexOf(" ");
        if (loc >= 0)
        {
          String target = text.substring(0, loc);
          String txt = text.substring(loc + 1);
          document.addProcessingInstruction(target, txt);
        }
        else
        {
          document.addProcessingInstruction(text, "");
        }
        break;
      case 9: 
        if (parent != null) {
          parent.addComment(local_pp.getText());
        } else {
          document.addComment(local_pp.getText());
        }
        break;
      case 5: 
        if (parent != null)
        {
          parent.addCDATA(local_pp.getText());
        }
        else
        {
          String text = "Cannot have text content outside of the root document";
          throw new DocumentException(text);
        }
        break;
      case 6: 
        break;
      case 1: 
        return document;
      case 2: 
        QName text = local_pp.getPrefix() == null ? local_df.createQName(local_pp.getName(), local_pp.getNamespace()) : local_df.createQName(local_pp.getName(), local_pp.getPrefix(), local_pp.getNamespace());
        Element loc = local_df.createElement(text);
        int target = local_pp.getNamespaceCount(local_pp.getDepth() - 1);
        int txt = local_pp.getNamespaceCount(local_pp.getDepth());
        for (int local_i = target; local_i < txt; local_i++) {
          if (local_pp.getNamespacePrefix(local_i) != null) {
            loc.addNamespace(local_pp.getNamespacePrefix(local_i), local_pp.getNamespaceUri(local_i));
          }
        }
        for (int local_i = 0; local_i < local_pp.getAttributeCount(); local_i++)
        {
          QName local_qa = local_pp.getAttributePrefix(local_i) == null ? local_df.createQName(local_pp.getAttributeName(local_i)) : local_df.createQName(local_pp.getAttributeName(local_i), local_pp.getAttributePrefix(local_i), local_pp.getAttributeNamespace(local_i));
          loc.addAttribute(local_qa, local_pp.getAttributeValue(local_i));
        }
        if (parent != null) {
          parent.add(loc);
        } else {
          document.add(loc);
        }
        parent = loc;
        break;
      case 3: 
        if (parent != null) {
          parent = parent.getParent();
        }
        break;
      case 4: 
        String text = local_pp.getText();
        if (parent != null)
        {
          parent.addText(text);
        }
        else
        {
          String loc = "Cannot have text content outside of the root document";
          throw new DocumentException(loc);
        }
        break;
      }
    }
  }
  
  protected DispatchHandler getDispatchHandler()
  {
    if (this.dispatchHandler == null) {
      this.dispatchHandler = new DispatchHandler();
    }
    return this.dispatchHandler;
  }
  
  protected void setDispatchHandler(DispatchHandler dispatchHandler)
  {
    this.dispatchHandler = dispatchHandler;
  }
  
  protected Reader createReader(InputStream local_in)
    throws IOException
  {
    return new BufferedReader(new InputStreamReader(local_in));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.XPP3Reader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */