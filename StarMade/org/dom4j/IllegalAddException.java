package org.dom4j;

public class IllegalAddException
  extends IllegalArgumentException
{
  public IllegalAddException(String reason)
  {
    super(reason);
  }
  
  public IllegalAddException(Element parent, Node node, String reason)
  {
    super("The node \"" + node.toString() + "\" could not be added to the element \"" + parent.getQualifiedName() + "\" because: " + reason);
  }
  
  public IllegalAddException(Branch parent, Node node, String reason)
  {
    super("The node \"" + node.toString() + "\" could not be added to the branch \"" + parent.getName() + "\" because: " + reason);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.IllegalAddException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */