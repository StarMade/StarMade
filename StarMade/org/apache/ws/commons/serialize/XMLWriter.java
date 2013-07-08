package org.apache.ws.commons.serialize;

import java.io.Writer;
import org.xml.sax.ContentHandler;

public abstract interface XMLWriter
  extends ContentHandler
{
  public abstract void setEncoding(String paramString);
  
  public abstract String getEncoding();
  
  public abstract void setDeclarating(boolean paramBoolean);
  
  public abstract boolean isDeclarating();
  
  public abstract void setWriter(Writer paramWriter);
  
  public abstract Writer getWriter();
  
  public abstract boolean canEncode(char paramChar);
  
  public abstract void setIndenting(boolean paramBoolean);
  
  public abstract boolean isIndenting();
  
  public abstract void setIndentString(String paramString);
  
  public abstract String getIndentString();
  
  public abstract void setLineFeed(String paramString);
  
  public abstract String getLineFeed();
  
  public abstract void setFlushing(boolean paramBoolean);
  
  public abstract boolean isFlushing();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.XMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */