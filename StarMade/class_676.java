import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.schema.game.common.data.element.ElementInformation;

public final class class_676
  extends JScrollPane
{
  private static final long serialVersionUID = 4286206957665246467L;
  private JLabel[] jdField_field_944_of_type_ArrayOfJavaxSwingJLabel = new JLabel[256];
  private int jdField_field_944_of_type_Int = -1;
  private int field_945;
  
  public class_676(ElementInformation paramElementInformation, int paramInt, class_685 paramclass_685)
  {
    class_683 localclass_683 = new class_683(this, (byte)0);
    this.field_945 = paramInt;
    if ((paramElementInformation.getTextureId() > paramInt << 8) && (paramElementInformation.getTextureId() < (paramInt << 8) + 256)) {
      this.jdField_field_944_of_type_Int = (paramElementInformation.getTextureId() % 256);
    }
    (paramElementInformation = new class_677(localclass_683)).setDragEnabled(false);
    paramElementInformation.setCellSelectionEnabled(true);
    paramElementInformation.setSelectionMode(0);
    paramElementInformation.setRowSelectionAllowed(false);
    paramElementInformation.setColumnSelectionAllowed(false);
    for (paramInt = 0; paramInt < this.jdField_field_944_of_type_ArrayOfJavaxSwingJLabel.length; paramInt++) {
      this.jdField_field_944_of_type_ArrayOfJavaxSwingJLabel[paramInt] = new JLabel();
    }
    paramElementInformation.setModel(new class_679());
    paramElementInformation.addMouseListener(new class_681(this, paramElementInformation, paramclass_685));
    paramElementInformation.getColumnModel().setColumnMargin(0);
    for (paramInt = 0; paramInt < 16; paramInt++)
    {
      paramElementInformation.setRowHeight(paramInt, 65);
      paramElementInformation.getColumnModel().getColumn(paramInt).setWidth(64);
      paramElementInformation.getColumnModel().getColumn(paramInt).setPreferredWidth(64);
      paramElementInformation.getColumnModel().getColumn(paramInt).setMaxWidth(64);
    }
    paramElementInformation.doLayout();
    (paramInt = new JPanel(new GridLayout())).add(paramElementInformation);
    setViewportView(paramInt);
  }
  
  public final int a()
  {
    return this.jdField_field_944_of_type_Int;
  }
  
  public final int b()
  {
    return this.field_945;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_676
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */