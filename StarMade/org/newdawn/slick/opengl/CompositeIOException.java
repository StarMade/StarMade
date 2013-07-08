/*  1:   */package org.newdawn.slick.opengl;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.util.ArrayList;
/*  5:   */
/* 10:   */public class CompositeIOException
/* 11:   */  extends IOException
/* 12:   */{
/* 13:13 */  private ArrayList exceptions = new ArrayList();
/* 14:   */  
/* 26:   */  public void addException(Exception e)
/* 27:   */  {
/* 28:28 */    this.exceptions.add(e);
/* 29:   */  }
/* 30:   */  
/* 33:   */  public String getMessage()
/* 34:   */  {
/* 35:35 */    String msg = "Composite Exception: \n";
/* 36:36 */    for (int i = 0; i < this.exceptions.size(); i++) {
/* 37:37 */      msg = msg + "\t" + ((IOException)this.exceptions.get(i)).getMessage() + "\n";
/* 38:   */    }
/* 39:   */    
/* 40:40 */    return msg;
/* 41:   */  }
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.CompositeIOException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */