import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

final class class_522
  extends MouseAdapter
{
  class_522(class_520 paramclass_520) {}
  
  public final void mouseReleased(MouseEvent paramMouseEvent)
  {
    int i;
    if (((i = class_520.a(this.field_829).rowAtPoint(paramMouseEvent.getPoint())) >= 0) && (i < class_520.a(this.field_829).getRowCount())) {
      class_520.a(this.field_829).setRowSelectionInterval(i, i);
    } else {
      class_520.a(this.field_829).clearSelection();
    }
    if ((i = class_520.a(this.field_829).getSelectedRow()) < 0) {
      return;
    }
    if ((paramMouseEvent.isPopupTrigger()) && ((paramMouseEvent.getComponent() instanceof JTable))) {
      class_520.a1(this.field_829, i).show(paramMouseEvent.getComponent(), paramMouseEvent.getX(), paramMouseEvent.getY());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_522
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */