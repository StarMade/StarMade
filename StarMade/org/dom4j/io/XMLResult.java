/*  1:   */package org.dom4j.io;
/*  2:   */
/*  3:   */import java.io.OutputStream;
/*  4:   */import java.io.UnsupportedEncodingException;
/*  5:   */import java.io.Writer;
/*  6:   */import javax.xml.transform.sax.SAXResult;
/*  7:   */import org.xml.sax.ContentHandler;
/*  8:   */import org.xml.sax.ext.LexicalHandler;
/*  9:   */
/* 26:   */public class XMLResult
/* 27:   */  extends SAXResult
/* 28:   */{
/* 29:   */  private XMLWriter xmlWriter;
/* 30:   */  
/* 31:   */  public XMLResult()
/* 32:   */  {
/* 33:33 */    this(new XMLWriter());
/* 34:   */  }
/* 35:   */  
/* 36:   */  public XMLResult(Writer writer) {
/* 37:37 */    this(new XMLWriter(writer));
/* 38:   */  }
/* 39:   */  
/* 40:   */  public XMLResult(Writer writer, OutputFormat format) {
/* 41:41 */    this(new XMLWriter(writer, format));
/* 42:   */  }
/* 43:   */  
/* 44:   */  public XMLResult(OutputStream out) throws UnsupportedEncodingException {
/* 45:45 */    this(new XMLWriter(out));
/* 46:   */  }
/* 47:   */  
/* 48:   */  public XMLResult(OutputStream out, OutputFormat format) throws UnsupportedEncodingException
/* 49:   */  {
/* 50:50 */    this(new XMLWriter(out, format));
/* 51:   */  }
/* 52:   */  
/* 53:   */  public XMLResult(XMLWriter xmlWriter) {
/* 54:54 */    super(xmlWriter);
/* 55:55 */    this.xmlWriter = xmlWriter;
/* 56:56 */    setLexicalHandler(xmlWriter);
/* 57:   */  }
/* 58:   */  
/* 59:   */  public XMLWriter getXMLWriter() {
/* 60:60 */    return this.xmlWriter;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public void setXMLWriter(XMLWriter writer) {
/* 64:64 */    this.xmlWriter = writer;
/* 65:65 */    setHandler(this.xmlWriter);
/* 66:66 */    setLexicalHandler(this.xmlWriter);
/* 67:   */  }
/* 68:   */  
/* 69:   */  public ContentHandler getHandler() {
/* 70:70 */    return this.xmlWriter;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public LexicalHandler getLexicalHandler() {
/* 74:74 */    return this.xmlWriter;
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XMLResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */