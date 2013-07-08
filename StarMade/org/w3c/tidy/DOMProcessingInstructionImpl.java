package org.w3c.tidy;

import org.w3c.dom.DOMException;
import org.w3c.dom.ProcessingInstruction;

public class DOMProcessingInstructionImpl
  extends DOMNodeImpl
  implements ProcessingInstruction
{
  protected DOMProcessingInstructionImpl(Node paramNode)
  {
    super(paramNode);
  }
  
  public short getNodeType()
  {
    return 7;
  }
  
  public String getTarget()
  {
    return null;
  }
  
  public String getData()
  {
    return getNodeValue();
  }
  
  public void setData(String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "Node is read only");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMProcessingInstructionImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */