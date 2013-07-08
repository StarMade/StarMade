/*   1:    */package org.dom4j.jaxb;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileOutputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.OutputStream;
/*   7:    */import java.io.Writer;
/*   8:    */import javax.xml.bind.JAXBException;
/*   9:    */import org.dom4j.io.OutputFormat;
/*  10:    */import org.dom4j.io.XMLWriter;
/*  11:    */import org.xml.sax.SAXException;
/*  12:    */
/*  42:    */public class JAXBWriter
/*  43:    */  extends JAXBSupport
/*  44:    */{
/*  45:    */  private XMLWriter xmlWriter;
/*  46:    */  private OutputFormat outputFormat;
/*  47:    */  
/*  48:    */  public JAXBWriter(String contextPath)
/*  49:    */  {
/*  50: 50 */    super(contextPath);
/*  51: 51 */    this.outputFormat = new OutputFormat();
/*  52:    */  }
/*  53:    */  
/*  65:    */  public JAXBWriter(String contextPath, OutputFormat outputFormat)
/*  66:    */  {
/*  67: 67 */    super(contextPath);
/*  68: 68 */    this.outputFormat = outputFormat;
/*  69:    */  }
/*  70:    */  
/*  83:    */  public JAXBWriter(String contextPath, ClassLoader classloader)
/*  84:    */  {
/*  85: 85 */    super(contextPath, classloader);
/*  86:    */  }
/*  87:    */  
/* 102:    */  public JAXBWriter(String contextPath, ClassLoader classloader, OutputFormat outputFormat)
/* 103:    */  {
/* 104:104 */    super(contextPath, classloader);
/* 105:105 */    this.outputFormat = outputFormat;
/* 106:    */  }
/* 107:    */  
/* 112:    */  public OutputFormat getOutputFormat()
/* 113:    */  {
/* 114:114 */    return this.outputFormat;
/* 115:    */  }
/* 116:    */  
/* 125:    */  public void setOutput(File file)
/* 126:    */    throws IOException
/* 127:    */  {
/* 128:128 */    getWriter().setOutputStream(new FileOutputStream(file));
/* 129:    */  }
/* 130:    */  
/* 139:    */  public void setOutput(OutputStream outputStream)
/* 140:    */    throws IOException
/* 141:    */  {
/* 142:142 */    getWriter().setOutputStream(outputStream);
/* 143:    */  }
/* 144:    */  
/* 151:    */  public void setOutput(Writer writer)
/* 152:    */    throws IOException
/* 153:    */  {
/* 154:154 */    getWriter().setWriter(writer);
/* 155:    */  }
/* 156:    */  
/* 164:    */  public void startDocument()
/* 165:    */    throws IOException, SAXException
/* 166:    */  {
/* 167:167 */    getWriter().startDocument();
/* 168:    */  }
/* 169:    */  
/* 177:    */  public void endDocument()
/* 178:    */    throws IOException, SAXException
/* 179:    */  {
/* 180:180 */    getWriter().endDocument();
/* 181:    */  }
/* 182:    */  
/* 194:    */  public void write(javax.xml.bind.Element jaxbObject)
/* 195:    */    throws IOException, JAXBException
/* 196:    */  {
/* 197:197 */    getWriter().write(marshal(jaxbObject));
/* 198:    */  }
/* 199:    */  
/* 213:    */  public void writeClose(javax.xml.bind.Element jaxbObject)
/* 214:    */    throws IOException, JAXBException
/* 215:    */  {
/* 216:216 */    getWriter().writeClose(marshal(jaxbObject));
/* 217:    */  }
/* 218:    */  
/* 231:    */  public void writeOpen(javax.xml.bind.Element jaxbObject)
/* 232:    */    throws IOException, JAXBException
/* 233:    */  {
/* 234:234 */    getWriter().writeOpen(marshal(jaxbObject));
/* 235:    */  }
/* 236:    */  
/* 244:    */  public void writeElement(org.dom4j.Element element)
/* 245:    */    throws IOException
/* 246:    */  {
/* 247:247 */    getWriter().write(element);
/* 248:    */  }
/* 249:    */  
/* 258:    */  public void writeCloseElement(org.dom4j.Element element)
/* 259:    */    throws IOException
/* 260:    */  {
/* 261:261 */    getWriter().writeClose(element);
/* 262:    */  }
/* 263:    */  
/* 272:    */  public void writeOpenElement(org.dom4j.Element element)
/* 273:    */    throws IOException
/* 274:    */  {
/* 275:275 */    getWriter().writeOpen(element);
/* 276:    */  }
/* 277:    */  
/* 278:    */  private XMLWriter getWriter() throws IOException {
/* 279:279 */    if (this.xmlWriter == null) {
/* 280:280 */      if (this.outputFormat != null) {
/* 281:281 */        this.xmlWriter = new XMLWriter(this.outputFormat);
/* 282:    */      } else {
/* 283:283 */        this.xmlWriter = new XMLWriter();
/* 284:    */      }
/* 285:    */    }
/* 286:    */    
/* 287:287 */    return this.xmlWriter;
/* 288:    */  }
/* 289:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */