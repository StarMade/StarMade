import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.dom4j.DocumentException;
import org.schema.game.common.api.exceptions.NotLoggedInException;

public final class class_1195
  extends JPanel
{
  private static final long serialVersionUID = -1166052783895269152L;
  private JScrollPane jdField_field_1402_of_type_JavaxSwingJScrollPane;
  private GridBagConstraints jdField_field_1402_of_type_JavaAwtGridBagConstraints;
  private class_1211 jdField_field_1402_of_type_Class_1211;
  
  public class_1195()
  {
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 450, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 10, 290, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    Object localObject1 = new JPanel();
    Object localObject2;
    (localObject2 = new GridBagConstraints()).anchor = 14;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 2;
    add((Component)localObject1, localObject2);
    (localObject2 = new JButton("Refresh News")).setHorizontalAlignment(4);
    ((JPanel)localObject1).add((Component)localObject2);
    this.jdField_field_1402_of_type_JavaAwtGridBagConstraints = new GridBagConstraints();
    this.jdField_field_1402_of_type_JavaAwtGridBagConstraints.gridheight = 2;
    this.jdField_field_1402_of_type_JavaAwtGridBagConstraints.weighty = 1.0D;
    this.jdField_field_1402_of_type_JavaAwtGridBagConstraints.weightx = 1.0D;
    this.jdField_field_1402_of_type_JavaAwtGridBagConstraints.fill = 1;
    this.jdField_field_1402_of_type_JavaAwtGridBagConstraints.gridx = 0;
    this.jdField_field_1402_of_type_JavaAwtGridBagConstraints.gridy = 0;
    this.jdField_field_1402_of_type_JavaxSwingJScrollPane = new JScrollPane(this.jdField_field_1402_of_type_Class_1211 = new class_1211());
    add(this.jdField_field_1402_of_type_JavaxSwingJScrollPane, this.jdField_field_1402_of_type_JavaAwtGridBagConstraints);
    b();
    add(this.jdField_field_1402_of_type_JavaxSwingJScrollPane, this.jdField_field_1402_of_type_JavaAwtGridBagConstraints);
    ((JButton)localObject2).addActionListener(new class_1193(this));
  }
  
  private void b()
  {
    new Thread(new class_1199(this)).start();
  }
  
  public final void a()
  {
    try
    {
      Object localObject;
      if ((localObject = class_1203.a()).size() > 0)
      {
        try
        {
          this.jdField_field_1402_of_type_Class_1211.setText((String)((ArrayList)localObject).get(0));
        }
        catch (Exception localException)
        {
          (localObject = localException).printStackTrace();
          this.jdField_field_1402_of_type_Class_1211.setText("Error: " + localObject.getClass() + ": " + ((Exception)localObject).getMessage());
        }
        invalidate();
        validate();
        repaint();
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (NotLoggedInException localNotLoggedInException)
    {
      localNotLoggedInException.printStackTrace();
      return;
    }
    catch (DocumentException localDocumentException)
    {
      localDocumentException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1195
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */