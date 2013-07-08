package org.w3c.tidy;

import org.w3c.dom.Comment;

public class DOMCommentImpl
  extends DOMCharacterDataImpl
  implements Comment
{
  protected DOMCommentImpl(Node paramNode)
  {
    super(paramNode);
  }
  
  public String getNodeName()
  {
    return "#comment";
  }
  
  public short getNodeType()
  {
    return 8;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.DOMCommentImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */