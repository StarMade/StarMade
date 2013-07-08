import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public final class class_642
  extends JPanel
{
  private static final long serialVersionUID = 5888372813950594327L;
  private class_693 field_918;
  
  public class_642(JFrame paramJFrame, short paramShort)
  {
    Object localObject;
    class_644[] arrayOfclass_644 = new class_644[(localObject = ElementInformation.class.getDeclaredFields()).length];
    for (int i = 0; i < localObject.length; i++) {
      arrayOfclass_644[i] = new class_644(paramJFrame, localObject[i], paramShort, i, this);
    }
    GridBagLayout localGridBagLayout;
    (localGridBagLayout = new GridBagLayout()).columnWidths = new int[] { 200 };
    localGridBagLayout.rowHeights = new int[arrayOfclass_644.length + 2];
    for (paramJFrame = 0; paramJFrame < localGridBagLayout.rowHeights.length; paramJFrame++) {
      localGridBagLayout.rowHeights[paramJFrame] = 33;
    }
    localGridBagLayout.columnWeights = new double[] { 0.0D };
    localGridBagLayout.rowWeights = new double[] { 0.0D };
    setLayout(localGridBagLayout);
    (paramJFrame = new JLabel(ElementKeyMap.getInfo(paramShort).getName())).setFont(new Font("Arial", 0, 19));
    (localObject = new GridBagConstraints()).anchor = 18;
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 0;
    add(paramJFrame, localObject);
    this.field_918 = new class_693(ElementKeyMap.getInfo(paramShort));
    (paramJFrame = new GridBagConstraints()).anchor = 18;
    paramJFrame.gridx = 0;
    paramJFrame.gridy = 1;
    add(this.field_918, paramJFrame);
    for (paramJFrame = 0; paramJFrame < arrayOfclass_644.length; paramJFrame++)
    {
      (paramShort = new GridBagConstraints()).anchor = 17;
      paramShort.fill = 1;
      paramShort.gridx = 0;
      paramShort.gridy = (paramJFrame + 2);
      add(arrayOfclass_644[paramJFrame], paramShort);
    }
  }
  
  public final void a()
  {
    class_693 localclass_693;
    (localclass_693 = this.field_918).removeAll();
    localclass_693.a();
    localclass_693.validate();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_642
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */