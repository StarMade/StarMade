/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Reader;
/*   5:    */import java.io.StringReader;
/*   6:    */import java.io.StringWriter;
/*   7:    */import org.dom4j.Document;
/*   8:    */import org.xml.sax.InputSource;
/*   9:    */
/*  27:    */class DocumentInputSource
/*  28:    */  extends InputSource
/*  29:    */{
/*  30:    */  private Document document;
/*  31:    */  
/*  32:    */  public DocumentInputSource() {}
/*  33:    */  
/*  34:    */  public DocumentInputSource(Document document)
/*  35:    */  {
/*  36: 36 */    this.document = document;
/*  37: 37 */    setSystemId(document.getName());
/*  38:    */  }
/*  39:    */  
/*  47:    */  public Document getDocument()
/*  48:    */  {
/*  49: 49 */    return this.document;
/*  50:    */  }
/*  51:    */  
/*  57:    */  public void setDocument(Document document)
/*  58:    */  {
/*  59: 59 */    this.document = document;
/*  60: 60 */    setSystemId(document.getName());
/*  61:    */  }
/*  62:    */  
/*  75:    */  public void setCharacterStream(Reader characterStream)
/*  76:    */    throws UnsupportedOperationException
/*  77:    */  {
/*  78: 78 */    throw new UnsupportedOperationException();
/*  79:    */  }
/*  80:    */  
/*  86:    */  public Reader getCharacterStream()
/*  87:    */  {
/*  88:    */    try
/*  89:    */    {
/*  90: 90 */      StringWriter out = new StringWriter();
/*  91: 91 */      XMLWriter writer = new XMLWriter(out);
/*  92: 92 */      writer.write(this.document);
/*  93: 93 */      writer.flush();
/*  94:    */      
/*  95: 95 */      return new StringReader(out.toString());
/*  97:    */    }
/*  98:    */    catch (IOException e)
/*  99:    */    {
/* 100:100 */      new Reader() {
/* 101:    */        private final IOException val$e;
/* 102:    */        
/* 103:103 */        public int read(char[] ch, int offset, int length) throws IOException { throw this.val$e; }
/* 104:    */        
/* 105:    */        public void close()
/* 106:    */          throws IOException
/* 107:    */        {}
/* 108:    */      };
/* 109:    */    }
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DocumentInputSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */