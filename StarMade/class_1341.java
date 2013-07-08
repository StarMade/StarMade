import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public final class class_1341
  extends AbstractListModel
  implements ComboBoxModel
{
  private static final long serialVersionUID = -1012982137072739776L;
  private class_949 field_1521;
  
  public class_1341(class_949 paramclass_949)
  {
    this.field_1521 = paramclass_949;
  }
  
  public final Object getElementAt(int paramInt)
  {
    if (paramInt >= 0)
    {
      if ((this.field_1521.a6().field_228[paramInt] instanceof Boolean))
      {
        if (((Boolean)this.field_1521.a6().field_228[paramInt]).booleanValue()) {
          return "On";
        }
        return "Off";
      }
      return this.field_1521.a6().field_228[paramInt];
    }
    return this.field_1521.a4();
  }
  
  public final Object getSelectedItem()
  {
    if ((this.field_1521.a4() instanceof Boolean))
    {
      if (((Boolean)this.field_1521.a4()).booleanValue()) {
        return "ON";
      }
      return "OFF";
    }
    return this.field_1521.a4();
  }
  
  public final int getSize()
  {
    return this.field_1521.a6().field_228.length;
  }
  
  public final void setSelectedItem(Object paramObject)
  {
    if ("On".equals(paramObject))
    {
      this.field_1521.a8(Boolean.valueOf(true));
      return;
    }
    if ("Off".equals(paramObject))
    {
      this.field_1521.a8(Boolean.valueOf(false));
      return;
    }
    this.field_1521.a8(paramObject);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1341
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */