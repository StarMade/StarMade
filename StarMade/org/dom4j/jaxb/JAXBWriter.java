package org.dom4j.jaxb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.bind.JAXBException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

public class JAXBWriter
  extends JAXBSupport
{
  private XMLWriter xmlWriter;
  private OutputFormat outputFormat;
  
  public JAXBWriter(String contextPath)
  {
    super(contextPath);
    this.outputFormat = new OutputFormat();
  }
  
  public JAXBWriter(String contextPath, OutputFormat outputFormat)
  {
    super(contextPath);
    this.outputFormat = outputFormat;
  }
  
  public JAXBWriter(String contextPath, ClassLoader classloader)
  {
    super(contextPath, classloader);
  }
  
  public JAXBWriter(String contextPath, ClassLoader classloader, OutputFormat outputFormat)
  {
    super(contextPath, classloader);
    this.outputFormat = outputFormat;
  }
  
  public OutputFormat getOutputFormat()
  {
    return this.outputFormat;
  }
  
  public void setOutput(File file)
    throws IOException
  {
    getWriter().setOutputStream(new FileOutputStream(file));
  }
  
  public void setOutput(OutputStream outputStream)
    throws IOException
  {
    getWriter().setOutputStream(outputStream);
  }
  
  public void setOutput(Writer writer)
    throws IOException
  {
    getWriter().setWriter(writer);
  }
  
  public void startDocument()
    throws IOException, SAXException
  {
    getWriter().startDocument();
  }
  
  public void endDocument()
    throws IOException, SAXException
  {
    getWriter().endDocument();
  }
  
  public void write(javax.xml.bind.Element jaxbObject)
    throws IOException, JAXBException
  {
    getWriter().write(marshal(jaxbObject));
  }
  
  public void writeClose(javax.xml.bind.Element jaxbObject)
    throws IOException, JAXBException
  {
    getWriter().writeClose(marshal(jaxbObject));
  }
  
  public void writeOpen(javax.xml.bind.Element jaxbObject)
    throws IOException, JAXBException
  {
    getWriter().writeOpen(marshal(jaxbObject));
  }
  
  public void writeElement(org.dom4j.Element element)
    throws IOException
  {
    getWriter().write(element);
  }
  
  public void writeCloseElement(org.dom4j.Element element)
    throws IOException
  {
    getWriter().writeClose(element);
  }
  
  public void writeOpenElement(org.dom4j.Element element)
    throws IOException
  {
    getWriter().writeOpen(element);
  }
  
  private XMLWriter getWriter()
    throws IOException
  {
    if (this.xmlWriter == null) {
      if (this.outputFormat != null) {
        this.xmlWriter = new XMLWriter(this.outputFormat);
      } else {
        this.xmlWriter = new XMLWriter();
      }
    }
    return this.xmlWriter;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.jaxb.JAXBWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */