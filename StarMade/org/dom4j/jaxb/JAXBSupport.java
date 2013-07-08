/*   1:    */package org.dom4j.jaxb;
/*   2:    */
/*   3:    */import java.io.StringReader;
/*   4:    */import javax.xml.bind.JAXBContext;
/*   5:    */import javax.xml.bind.JAXBException;
/*   6:    */import javax.xml.bind.Marshaller;
/*   7:    */import javax.xml.bind.Unmarshaller;
/*   8:    */import javax.xml.transform.Source;
/*   9:    */import javax.xml.transform.stream.StreamSource;
/*  10:    */import org.dom4j.dom.DOMDocument;
/*  11:    */
/*  28:    */abstract class JAXBSupport
/*  29:    */{
/*  30:    */  private String contextPath;
/*  31:    */  private ClassLoader classloader;
/*  32:    */  private JAXBContext jaxbContext;
/*  33:    */  private Marshaller marshaller;
/*  34:    */  private Unmarshaller unmarshaller;
/*  35:    */  
/*  36:    */  public JAXBSupport(String contextPath)
/*  37:    */  {
/*  38: 38 */    this.contextPath = contextPath;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public JAXBSupport(String contextPath, ClassLoader classloader) {
/*  42: 42 */    this.contextPath = contextPath;
/*  43: 43 */    this.classloader = classloader;
/*  44:    */  }
/*  45:    */  
/*  57:    */  protected org.dom4j.Element marshal(javax.xml.bind.Element element)
/*  58:    */    throws JAXBException
/*  59:    */  {
/*  60: 60 */    DOMDocument doc = new DOMDocument();
/*  61: 61 */    getMarshaller().marshal(element, doc);
/*  62:    */    
/*  63: 63 */    return doc.getRootElement();
/*  64:    */  }
/*  65:    */  
/*  77:    */  protected javax.xml.bind.Element unmarshal(org.dom4j.Element element)
/*  78:    */    throws JAXBException
/*  79:    */  {
/*  80: 80 */    Source source = new StreamSource(new StringReader(element.asXML()));
/*  81:    */    
/*  82: 82 */    return (javax.xml.bind.Element)getUnmarshaller().unmarshal(source);
/*  83:    */  }
/*  84:    */  
/*  85:    */  private Marshaller getMarshaller() throws JAXBException {
/*  86: 86 */    if (this.marshaller == null) {
/*  87: 87 */      this.marshaller = getContext().createMarshaller();
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    return this.marshaller;
/*  91:    */  }
/*  92:    */  
/*  93:    */  private Unmarshaller getUnmarshaller() throws JAXBException {
/*  94: 94 */    if (this.unmarshaller == null) {
/*  95: 95 */      this.unmarshaller = getContext().createUnmarshaller();
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    return this.unmarshaller;
/*  99:    */  }
/* 100:    */  
/* 101:    */  private JAXBContext getContext() throws JAXBException {
/* 102:102 */    if (this.jaxbContext == null) {
/* 103:103 */      if (this.classloader == null) {
/* 104:104 */        this.jaxbContext = JAXBContext.newInstance(this.contextPath);
/* 105:    */      } else {
/* 106:106 */        this.jaxbContext = JAXBContext.newInstance(this.contextPath, this.classloader);
/* 107:    */      }
/* 108:    */    }
/* 109:    */    
/* 110:110 */    return this.jaxbContext;
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBSupport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */