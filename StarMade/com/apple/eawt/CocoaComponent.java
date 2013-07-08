/* 1:  */package com.apple.eawt;
/* 2:  */
/* 3:  */import java.awt.Canvas;
/* 4:  */
/* 5:  */public abstract class CocoaComponent extends Canvas { public abstract int createNSView();
/* 6:  */  
/* 7:7 */  public long createNSViewLong() { return 0L; }
/* 8:  */  
/* 9:  */  public abstract java.awt.Dimension getMaximumSize();
/* 10:  */  
/* 11:  */  public abstract java.awt.Dimension getMinimumSize();
/* 12:  */  
/* 13:  */  public abstract java.awt.Dimension getPreferredSize();
/* 14:  */  
/* 15:  */  public final void sendMessage(int paramInt, Object paramObject) {}
/* 16:  */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.apple.eawt.CocoaComponent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */