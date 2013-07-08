/*  1:   */import java.awt.Component;
/*  2:   */import javax.swing.JLabel;
/*  3:   */import javax.swing.JList;
/*  4:   */import javax.swing.ListCellRenderer;
/*  5:   */import javax.swing.UIManager;
/*  6:   */import org.schema.schine.network.objects.Sendable;
/*  7:   */
/* 15:   */public final class qu
/* 16:   */  extends JLabel
/* 17:   */  implements ListCellRenderer
/* 18:   */{
/* 19:   */  private static final long serialVersionUID = 6987354272651292846L;
/* 20:   */  
/* 21:   */  public final Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/* 22:   */  {
/* 23:23 */    paramJList = (Sendable)paramObject;
/* 24:   */    
/* 25:25 */    setText(paramJList != null ? paramJList.toString() : "NULL");
/* 26:   */    
/* 27:27 */    setOpaque(true);
/* 28:   */    
/* 29:29 */    if (paramBoolean1) {
/* 30:30 */      setForeground(UIManager.getColor("List.selectionForeground"));
/* 31:31 */      setBackground(UIManager.getColor("List.selectionBackground"));
/* 32:   */    }
/* 33:   */    else
/* 34:   */    {
/* 35:35 */      setBackground(UIManager.getColor("List.background"));
/* 36:   */    }
/* 37:   */    
/* 38:38 */    return this;
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */