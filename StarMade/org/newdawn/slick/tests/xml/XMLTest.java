/*   1:    */package org.newdawn.slick.tests.xml;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.newdawn.slick.SlickException;
/*   5:    */import org.newdawn.slick.util.xml.XMLElement;
/*   6:    */import org.newdawn.slick.util.xml.XMLElementList;
/*   7:    */import org.newdawn.slick.util.xml.XMLParser;
/*   8:    */
/*  16:    */public class XMLTest
/*  17:    */{
/*  18:    */  private static void fail(String message)
/*  19:    */  {
/*  20: 20 */    throw new RuntimeException(message);
/*  21:    */  }
/*  22:    */  
/*  27:    */  private static void assertNotNull(Object object1)
/*  28:    */  {
/*  29: 29 */    if (object1 == null) {
/*  30: 30 */      throw new RuntimeException("TEST FAILS: " + object1 + " must not be null");
/*  31:    */    }
/*  32:    */  }
/*  33:    */  
/*  39:    */  private static void assertEquals(float a1, float a2)
/*  40:    */  {
/*  41: 41 */    if (a1 != a2) {
/*  42: 42 */      throw new RuntimeException("TEST FAILS: " + a1 + " should be " + a2);
/*  43:    */    }
/*  44:    */  }
/*  45:    */  
/*  51:    */  private static void assertEquals(int a1, int a2)
/*  52:    */  {
/*  53: 53 */    if (a1 != a2) {
/*  54: 54 */      throw new RuntimeException("TEST FAILS: " + a1 + " should be " + a2);
/*  55:    */    }
/*  56:    */  }
/*  57:    */  
/*  63:    */  private static void assertEquals(Object a1, Object a2)
/*  64:    */  {
/*  65: 65 */    if (!a1.equals(a2)) {
/*  66: 66 */      throw new RuntimeException("TEST FAILS: " + a1 + " should be " + a2);
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  74:    */  public static void main(String[] argv)
/*  75:    */    throws SlickException
/*  76:    */  {
/*  77: 77 */    XMLParser parser = new XMLParser();
/*  78:    */    
/*  79: 79 */    XMLElement root = parser.parse("testdata/test.xml");
/*  80:    */    
/*  81: 81 */    assertEquals(root.getName(), "testRoot");
/*  82: 82 */    System.out.println(root);
/*  83: 83 */    assertNotNull(root.getChildrenByName("simple").get(0).getContent());
/*  84: 84 */    System.out.println(root.getChildrenByName("simple").get(0).getContent());
/*  85:    */    
/*  86: 86 */    XMLElement parent = root.getChildrenByName("parent").get(0);
/*  87: 87 */    assertEquals(parent.getChildrenByName("grandchild").size(), 0);
/*  88: 88 */    assertEquals(parent.getChildrenByName("child").size(), 2);
/*  89:    */    
/*  90: 90 */    assertEquals(parent.getChildrenByName("child").get(0).getChildren().size(), 2);
/*  91: 91 */    XMLElement child = parent.getChildrenByName("child").get(0).getChildren().get(0);
/*  92:    */    
/*  93: 93 */    String name = child.getAttribute("name");
/*  94: 94 */    String test = child.getAttribute("nothere", "defaultValue");
/*  95: 95 */    int age = child.getIntAttribute("age");
/*  96:    */    
/*  97: 97 */    assertEquals(name, "bob");
/*  98: 98 */    assertEquals(test, "defaultValue");
/*  99: 99 */    assertEquals(age, 1);
/* 100:    */    
/* 101:101 */    XMLElement other = root.getChildrenByName("other").get(0);
/* 102:102 */    float x = (float)other.getDoubleAttribute("x");
/* 103:103 */    float y = (float)other.getDoubleAttribute("y", 1.0D);
/* 104:104 */    float z = (float)other.getDoubleAttribute("z", 83.0D);
/* 105:    */    
/* 106:106 */    assertEquals(x, 5.3F);
/* 107:107 */    assertEquals(y, 5.4F);
/* 108:108 */    assertEquals(z, 83.0F);
/* 109:    */    try
/* 110:    */    {
/* 111:111 */      z = (float)other.getDoubleAttribute("z");
/* 112:112 */      fail("Attribute z as a double should fail");
/* 113:    */    }
/* 114:    */    catch (SlickException e) {}
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.xml.XMLTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */