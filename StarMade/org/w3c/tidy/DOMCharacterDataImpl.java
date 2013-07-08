package org.w3c.tidy;

import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;

public class DOMCharacterDataImpl
  extends DOMNodeImpl
  implements CharacterData
{
  protected DOMCharacterDataImpl(Node paramNode)
  {
    super(paramNode);
  }
  
  public String getData()
    throws DOMException
  {
    return getNodeValue();
  }
  
  public int getLength()
  {
    int i = 0;
    if ((this.adaptee.textarray != null) && (this.adaptee.start < this.adaptee.end)) {
      i = this.adaptee.end - this.adaptee.start;
    }
    return i;
  }
  
  public String substringData(int paramInt1, int paramInt2)
    throws DOMException
  {
    String str = null;
    if (paramInt2 < 0) {
      throw new DOMException((short)1, "Invalid length");
    }
    if ((this.adaptee.textarray != null) && (this.adaptee.start < this.adaptee.end))
    {
      if (this.adaptee.start + paramInt1 >= this.adaptee.end) {
        throw new DOMException((short)1, "Invalid offset");
      }
      int i = paramInt2;
      if (this.adaptee.start + paramInt1 + i - 1 >= this.adaptee.end) {
        i = this.adaptee.end - this.adaptee.start - paramInt1;
      }
      str = TidyUtils.getString(this.adaptee.textarray, this.adaptee.start + paramInt1, i);
    }
    return str;
  }
  
  public void setData(String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public void appendData(String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public void insertData(int paramInt, String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public void deleteData(int paramInt1, int paramInt2)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public void replaceData(int paramInt1, int paramInt2, String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMCharacterDataImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */