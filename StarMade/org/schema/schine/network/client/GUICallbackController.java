/*  1:   */package org.schema.schine.network.client;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import xp;
/*  5:   */import ys;
/*  6:   */import yz;
/*  7:   */
/*  9:   */public class GUICallbackController
/* 10:   */{
/* 11:11 */  private final ArrayList guiCallbacks = new ArrayList();
/* 12:12 */  private final ArrayList callingGUIElements = new ArrayList();
/* 13:   */  
/* 14:   */  public void addCallback(ys paramys, yz paramyz) {
/* 15:   */    int i;
/* 16:16 */    if (((i = this.guiCallbacks.indexOf(paramys)) < 0) || (i != this.callingGUIElements.indexOf(paramyz))) {
/* 17:17 */      this.guiCallbacks.add(paramys);
/* 18:18 */      this.callingGUIElements.add(paramyz);
/* 19:   */    }
/* 20:   */  }
/* 21:   */  
/* 22:   */  public void execute(xp paramxp) {
/* 23:23 */    assert (this.guiCallbacks.size() == this.callingGUIElements.size());
/* 24:24 */    for (int i = 0; i < this.guiCallbacks.size(); i++) {
/* 25:25 */      ys localys = (ys)this.guiCallbacks.get(i);
/* 26:26 */      yz localyz = (yz)this.callingGUIElements.get(i);
/* 27:27 */      localys.a(localyz, paramxp);
/* 28:   */    }
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void reset() {
/* 32:32 */    this.guiCallbacks.clear();
/* 33:33 */    this.callingGUIElements.clear();
/* 34:   */  }
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.GUICallbackController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */