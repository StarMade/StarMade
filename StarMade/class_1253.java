import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public final class class_1253
  extends JLabel
  implements ListCellRenderer
{
  private static final long serialVersionUID = 6987354272651292846L;
  
  public final Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramObject instanceof class_748))
    {
      paramJList = (class_748)paramObject;
      setText(paramJList.getName());
    }
    else
    {
      setText("StarmoteSynchException");
    }
    setOpaque(true);
    if (paramBoolean1)
    {
      setForeground(UIManager.getColor("List.selectionForeground"));
      setBackground(UIManager.getColor("List.selectionBackground"));
    }
    else
    {
      setBackground(UIManager.getColor("List.background"));
    }
    return this;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1253
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */