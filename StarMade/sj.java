/*   1:    */import javax.swing.JScrollBar;
/*   2:    */import javax.swing.JScrollPane;
/*   3:    */
/* 169:    */public final class sj
/* 170:    */  implements Runnable
/* 171:    */{
/* 172:    */  public sj(JScrollPane paramJScrollPane) {}
/* 173:    */  
/* 174:    */  public final void run()
/* 175:    */  {
/* 176:176 */    this.a.getVerticalScrollBar().setValue(this.a.getVerticalScrollBar().getMinimum());
/* 177:    */  }
/* 178:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */