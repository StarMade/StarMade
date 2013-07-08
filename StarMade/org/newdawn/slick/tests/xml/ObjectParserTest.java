package org.newdawn.slick.tests.xml;

import org.newdawn.slick.util.xml.ObjectTreeParser;
import org.newdawn.slick.util.xml.SlickXMLException;

public class ObjectParserTest
{
  public static void main(String[] argv)
    throws SlickXMLException
  {
    ObjectTreeParser parser = new ObjectTreeParser("org.newdawn.slick.tests.xml");
    parser.addElementMapping("Bag", ItemContainer.class);
    GameData parsedData = (GameData)parser.parse("testdata/objxmltest.xml");
    parsedData.dump("");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.xml.ObjectParserTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */