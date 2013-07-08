/*  1:   */import javax.swing.AbstractListModel;
/*  2:   */import javax.swing.ComboBoxModel;
/*  3:   */
/*  9:   */public final class pw
/* 10:   */  extends AbstractListModel
/* 11:   */  implements ComboBoxModel
/* 12:   */{
/* 13:   */  private static final long serialVersionUID = -1012982137072739776L;
/* 14:   */  private xu a;
/* 15:   */  
/* 16:   */  public pw(xu paramxu)
/* 17:   */  {
/* 18:18 */    this.a = paramxu;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public final Object getElementAt(int paramInt) {
/* 22:22 */    if (paramInt >= 0) {
/* 23:23 */      if ((this.a.a().a[paramInt] instanceof Boolean)) {
/* 24:24 */        if (((Boolean)this.a.a().a[paramInt]).booleanValue()) return "On"; return "Off";
/* 25:   */      }
/* 26:26 */      return this.a.a().a[paramInt];
/* 27:   */    }
/* 28:28 */    return this.a.a();
/* 29:   */  }
/* 30:   */  
/* 32:   */  public final Object getSelectedItem()
/* 33:   */  {
/* 34:34 */    if ((this.a.a() instanceof Boolean)) {
/* 35:35 */      if (((Boolean)this.a.a()).booleanValue()) return "ON"; return "OFF";
/* 36:   */    }
/* 37:37 */    return this.a.a();
/* 38:   */  }
/* 39:   */  
/* 40:   */  public final int getSize()
/* 41:   */  {
/* 42:42 */    return this.a.a().a.length;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public final void setSelectedItem(Object paramObject)
/* 46:   */  {
/* 47:47 */    if ("On".equals(paramObject)) {
/* 48:48 */      this.a.a(Boolean.valueOf(true));return; }
/* 49:49 */    if ("Off".equals(paramObject)) {
/* 50:50 */      this.a.a(Boolean.valueOf(false));return;
/* 51:   */    }
/* 52:52 */    this.a.a(paramObject);
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */