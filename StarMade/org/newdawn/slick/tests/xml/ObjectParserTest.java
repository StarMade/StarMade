/*  1:   */package org.newdawn.slick.tests.xml;
/*  2:   */
/*  3:   */import org.newdawn.slick.util.xml.ObjectTreeParser;
/*  4:   */import org.newdawn.slick.util.xml.SlickXMLException;
/*  5:   */
/* 17:   */public class ObjectParserTest
/* 18:   */{
/* 19:   */  public static void main(String[] argv)
/* 20:   */    throws SlickXMLException
/* 21:   */  {
/* 22:22 */    ObjectTreeParser parser = new ObjectTreeParser("org.newdawn.slick.tests.xml");
/* 23:23 */    parser.addElementMapping("Bag", ItemContainer.class);
/* 24:   */    
/* 25:25 */    GameData parsedData = (GameData)parser.parse("testdata/objxmltest.xml");
/* 26:26 */    parsedData.dump("");
/* 27:   */  }
/* 28:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.xml.ObjectParserTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */