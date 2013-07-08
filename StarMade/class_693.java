import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.schema.game.common.data.element.ElementInformation;

public final class class_693
  extends JPanel
{
  private static final long serialVersionUID = 7615909165008598994L;
  private ElementInformation field_959;
  
  public class_693(ElementInformation paramElementInformation)
  {
    this.field_959 = paramElementInformation;
    a();
  }
  
  final void a()
  {
    int i = this.field_959.getTextureId();
    b(i, this.field_959.getIndividualSides());
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 6)
    {
      add(a1(paramInt1, "front"));
      add(a1(paramInt1 + 1, "back"));
      add(a1(paramInt1 + 2, "top"));
      add(a1(paramInt1 + 3, "bottom"));
      add(a1(paramInt1 + 4, "left"));
      add(a1(paramInt1 + 5, "right"));
      return;
    }
    if (paramInt2 == 3)
    {
      add(a1(paramInt1, "top"));
      add(a1(paramInt1 + 1, "bottom"));
      add(a1(paramInt1 + 2, "sides"));
      return;
    }
    add(a1(paramInt1, "sides"));
  }
  
  private static JPanel a1(int paramInt, String paramString)
  {
    JPanel localJPanel;
    (localJPanel = new JPanel()).setBorder(new TitledBorder(paramString));
    paramInt = new JLabel(class_29.a8(paramInt));
    localJPanel.add(paramInt);
    return localJPanel;
  }
  
  public final void a2(int paramInt1, int paramInt2)
  {
    removeAll();
    b(paramInt1, paramInt2);
    doLayout();
    revalidate();
    repaint();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_693
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */