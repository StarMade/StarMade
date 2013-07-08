/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import javax.xml.transform.sax.SAXSource;
/*   4:    */import org.dom4j.Document;
/*   5:    */import org.dom4j.Node;
/*   6:    */import org.xml.sax.InputSource;
/*   7:    */import org.xml.sax.XMLFilter;
/*   8:    */import org.xml.sax.XMLReader;
/*   9:    */
/*  34:    */public class DocumentSource
/*  35:    */  extends SAXSource
/*  36:    */{
/*  37:    */  public static final String DOM4J_FEATURE = "http://org.dom4j.io.DoucmentSource/feature";
/*  38: 38 */  private XMLReader xmlReader = new SAXWriter();
/*  39:    */  
/*  45:    */  public DocumentSource(Node node)
/*  46:    */  {
/*  47: 47 */    setDocument(node.getDocument());
/*  48:    */  }
/*  49:    */  
/*  55:    */  public DocumentSource(Document document)
/*  56:    */  {
/*  57: 57 */    setDocument(document);
/*  58:    */  }
/*  59:    */  
/*  67:    */  public Document getDocument()
/*  68:    */  {
/*  69: 69 */    DocumentInputSource source = (DocumentInputSource)getInputSource();
/*  70: 70 */    return source.getDocument();
/*  71:    */  }
/*  72:    */  
/*  78:    */  public void setDocument(Document document)
/*  79:    */  {
/*  80: 80 */    super.setInputSource(new DocumentInputSource(document));
/*  81:    */  }
/*  82:    */  
/*  90:    */  public XMLReader getXMLReader()
/*  91:    */  {
/*  92: 92 */    return this.xmlReader;
/*  93:    */  }
/*  94:    */  
/* 104:    */  public void setInputSource(InputSource inputSource)
/* 105:    */    throws UnsupportedOperationException
/* 106:    */  {
/* 107:107 */    if ((inputSource instanceof DocumentInputSource)) {
/* 108:108 */      super.setInputSource((DocumentInputSource)inputSource);
/* 109:    */    } else {
/* 110:110 */      throw new UnsupportedOperationException();
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 122:    */  public void setXMLReader(XMLReader reader)
/* 123:    */    throws UnsupportedOperationException
/* 124:    */  {
/* 125:125 */    if ((reader instanceof SAXWriter)) {
/* 126:126 */      this.xmlReader = ((SAXWriter)reader);
/* 127:127 */    } else if ((reader instanceof XMLFilter)) {
/* 128:128 */      XMLFilter filter = (XMLFilter)reader;
/* 129:    */      for (;;)
/* 130:    */      {
/* 131:131 */        XMLReader parent = filter.getParent();
/* 132:    */        
/* 133:133 */        if (!(parent instanceof XMLFilter)) break;
/* 134:134 */        filter = (XMLFilter)parent;
/* 135:    */      }
/* 136:    */      
/* 141:141 */      filter.setParent(this.xmlReader);
/* 142:142 */      this.xmlReader = filter;
/* 143:    */    } else {
/* 144:144 */      throw new UnsupportedOperationException();
/* 145:    */    }
/* 146:    */  }
/* 147:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DocumentSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */