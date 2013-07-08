/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.Node;
/*   5:    */
/*  43:    */public class FlyweightEntity
/*  44:    */  extends AbstractEntity
/*  45:    */{
/*  46:    */  protected String name;
/*  47:    */  protected String text;
/*  48:    */  
/*  49:    */  protected FlyweightEntity() {}
/*  50:    */  
/*  51:    */  public FlyweightEntity(String name)
/*  52:    */  {
/*  53: 53 */    this.name = name;
/*  54:    */  }
/*  55:    */  
/*  63:    */  public FlyweightEntity(String name, String text)
/*  64:    */  {
/*  65: 65 */    this.name = name;
/*  66: 66 */    this.text = text;
/*  67:    */  }
/*  68:    */  
/*  73:    */  public String getName()
/*  74:    */  {
/*  75: 75 */    return this.name;
/*  76:    */  }
/*  77:    */  
/*  82:    */  public String getText()
/*  83:    */  {
/*  84: 84 */    return this.text;
/*  85:    */  }
/*  86:    */  
/*  97:    */  public void setText(String text)
/*  98:    */  {
/*  99: 99 */    if (this.text != null) {
/* 100:100 */      this.text = text;
/* 101:    */    } else {
/* 102:102 */      throw new UnsupportedOperationException("This Entity is read-only. It cannot be modified");
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 106:    */  protected Node createXPathResult(Element parent)
/* 107:    */  {
/* 108:108 */    return new DefaultEntity(parent, getName(), getText());
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */