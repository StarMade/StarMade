/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */import Ag;
/*  4:   */import java.io.DataInputStream;
/*  5:   */
/* 17:   */public class ControlElementMapperFactory
/* 18:   */  implements Ag
/* 19:   */{
/* 20:   */  private static final long serialVersionUID = 8953098951065383692L;
/* 21:   */  
/* 22:   */  public Object create(DataInputStream paramDataInputStream)
/* 23:   */  {
/* 24:24 */    ControlElementMapper localControlElementMapper = new ControlElementMapper();
/* 25:25 */    ControlElementMap.deserialize(paramDataInputStream, localControlElementMapper);
/* 26:   */    
/* 27:27 */    return localControlElementMapper;
/* 28:   */  }
/* 29:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMapperFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */