package org.dom4j.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.dom4j.Document;
import org.xml.sax.InputSource;

class DocumentInputSource
  extends InputSource
{
  private Document document;
  
  public DocumentInputSource() {}
  
  public DocumentInputSource(Document document)
  {
    this.document = document;
    setSystemId(document.getName());
  }
  
  public Document getDocument()
  {
    return this.document;
  }
  
  public void setDocument(Document document)
  {
    this.document = document;
    setSystemId(document.getName());
  }
  
  public void setCharacterStream(Reader characterStream)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
  
  public Reader getCharacterStream()
  {
    try
    {
      StringWriter out = new StringWriter();
      XMLWriter writer = new XMLWriter(out);
      writer.write(this.document);
      writer.flush();
      return new StringReader(out.toString());
    }
    catch (IOException out)
    {
      new Reader()
      {
        private final IOException val$e;
        
        public int read(char[] local_ch, int offset, int length)
          throws IOException
        {
          throw this.val$e;
        }
        
        public void close()
          throws IOException
        {}
      };
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.DocumentInputSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */