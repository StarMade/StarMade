/*  1:   */package org.dom4j.io;
/*  2:   */
/*  3:   */import javax.xml.transform.sax.SAXResult;
/*  4:   */import org.dom4j.Document;
/*  5:   */import org.xml.sax.ContentHandler;
/*  6:   */import org.xml.sax.ext.LexicalHandler;
/*  7:   */
/* 23:   */public class DocumentResult
/* 24:   */  extends SAXResult
/* 25:   */{
/* 26:   */  private SAXContentHandler contentHandler;
/* 27:   */  
/* 28:   */  public DocumentResult()
/* 29:   */  {
/* 30:30 */    this(new SAXContentHandler());
/* 31:   */  }
/* 32:   */  
/* 33:   */  public DocumentResult(SAXContentHandler contentHandler) {
/* 34:34 */    this.contentHandler = contentHandler;
/* 35:35 */    super.setHandler(this.contentHandler);
/* 36:36 */    super.setLexicalHandler(this.contentHandler);
/* 37:   */  }
/* 38:   */  
/* 43:   */  public Document getDocument()
/* 44:   */  {
/* 45:45 */    return this.contentHandler.getDocument();
/* 46:   */  }
/* 47:   */  
/* 49:   */  public void setHandler(ContentHandler handler)
/* 50:   */  {
/* 51:51 */    if ((handler instanceof SAXContentHandler)) {
/* 52:52 */      this.contentHandler = ((SAXContentHandler)handler);
/* 53:53 */      super.setHandler(this.contentHandler);
/* 54:   */    }
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setLexicalHandler(LexicalHandler handler) {
/* 58:58 */    if ((handler instanceof SAXContentHandler)) {
/* 59:59 */      this.contentHandler = ((SAXContentHandler)handler);
/* 60:60 */      super.setLexicalHandler(this.contentHandler);
/* 61:   */    }
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DocumentResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */