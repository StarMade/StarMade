/*  1:   */import java.awt.Component;
/*  2:   */import javax.swing.JLabel;
/*  3:   */import javax.swing.JList;
/*  4:   */import javax.swing.ListCellRenderer;
/*  5:   */import javax.swing.UIManager;
/*  6:   */
/* 14:   */public final class rl
/* 15:   */  extends JLabel
/* 16:   */  implements ListCellRenderer
/* 17:   */{
/* 18:   */  private static final long serialVersionUID = 6987354272651292846L;
/* 19:   */  
/* 20:   */  public final Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/* 21:   */  {
/* 22:22 */    if ((paramObject instanceof lE)) {
/* 23:23 */      paramJList = (lE)paramObject;
/* 24:   */      
/* 25:25 */      setText(paramJList.getName());
/* 26:   */    } else {
/* 27:27 */      setText("StarmoteSynchException");
/* 28:   */    }
/* 29:   */    
/* 30:30 */    setOpaque(true);
/* 31:   */    
/* 32:32 */    if (paramBoolean1) {
/* 33:33 */      setForeground(UIManager.getColor("List.selectionForeground"));
/* 34:34 */      setBackground(UIManager.getColor("List.selectionBackground"));
/* 35:   */    }
/* 36:   */    else
/* 37:   */    {
/* 38:38 */      setBackground(UIManager.getColor("List.background"));
/* 39:   */    }
/* 40:   */    
/* 41:41 */    return this;
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */