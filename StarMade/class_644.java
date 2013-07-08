import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.lang.reflect.Field;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.border.EtchedBorder;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.BlockFactory;
import org.schema.game.common.data.element.BlockLevel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.ElementParser;
import org.schema.game.common.data.element.annotation.Element;

public final class class_644
  extends JPanel
{
  private static final long serialVersionUID = 2663673122464263576L;
  private ElementInformation jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation;
  private class_678 jdField_field_919_of_type_Class_678;
  private Field jdField_field_919_of_type_JavaLangReflectField;
  private Component jdField_field_919_of_type_JavaAwtComponent;
  
  public class_644(JFrame paramJFrame, Field paramField, short paramShort, int paramInt, class_642 paramclass_642)
  {
    setBorder(new EtchedBorder(1, null, null));
    this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation = ElementKeyMap.getInfo(paramShort);
    this.jdField_field_919_of_type_JavaLangReflectField = paramField;
    if (paramInt % 2 == 0) {
      setBackground(Color.gray.brighter());
    }
    (paramShort = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    paramShort.rowHeights = new int[] { 0, 0 };
    paramShort.columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    paramShort.rowWeights = new double[] { 1.0D, 4.9E-324D };
    setLayout(paramShort);
    (paramShort = new JLabel(paramField.getName())).setFont(new Font("Tahoma", 0, 15));
    Object localObject;
    (localObject = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 0;
    add(paramShort, localObject);
    paramShort = new JPanel();
    (localObject = new GridBagLayout()).columnWidths = new int[] { 300 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 1.0D };
    paramShort.setLayout((LayoutManager)localObject);
    (localObject = new GridBagConstraints()).gridx = 1;
    ((GridBagConstraints)localObject).gridy = 0;
    ((GridBagConstraints)localObject).weightx = 2.0D;
    ((GridBagConstraints)localObject).anchor = 13;
    add(paramShort, localObject);
    if (paramInt % 2 == 0) {
      paramShort.setBackground(Color.gray.brighter());
    }
    try
    {
      (paramInt = new GridBagConstraints()).gridx = 0;
      paramInt.gridy = 0;
      paramInt.weightx = 1.0D;
      paramInt.fill = 1;
      this.jdField_field_919_of_type_JavaAwtComponent = a(paramJFrame, paramField, paramInt, paramclass_642);
      if (this.jdField_field_919_of_type_Class_678 != null)
      {
        if (((paramJFrame = this.jdField_field_919_of_type_JavaAwtComponent) instanceof JScrollPane)) {
          paramJFrame = ((JScrollPane)paramJFrame).getViewport().getComponent(0);
        }
        paramJFrame.addKeyListener(new class_652(this));
        paramJFrame.addFocusListener(new class_668(this));
      }
      paramShort.add(this.jdField_field_919_of_type_JavaAwtComponent, paramInt);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      (paramInt = localIllegalArgumentException).printStackTrace();
      class_29.a12(paramInt);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      (paramInt = localIllegalAccessException).printStackTrace();
      class_29.a12(paramInt);
    }
  }
  
  private Component a(JFrame paramJFrame, Field paramField, GridBagConstraints paramGridBagConstraints, class_642 paramclass_642)
  {
    Object localObject1 = paramField.getType();
    paramGridBagConstraints.insets = new Insets(0, 50, 0, 0);
    Object localObject2;
    Object localObject3;
    if (paramField.getName().equals("textureId"))
    {
      localObject1 = new JPanel();
      localObject2 = new JTextPane();
      paramGridBagConstraints = paramField.getShort(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation);
      ((JTextPane)localObject2).setText(String.valueOf(paramGridBagConstraints));
      (localObject3 = new JButton("Edit")).addActionListener(new class_666(this, paramJFrame, paramField, (JTextPane)localObject2, paramclass_642));
      ((JPanel)localObject1).add((Component)localObject2);
      ((JPanel)localObject1).add((Component)localObject3);
      return localObject1;
    }
    if (localObject1.equals(Class.class))
    {
      (localObject1 = new JTextField()).setText(ElementParser.getStringFromType((Class)paramField.get(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
      ((JTextField)localObject1).setEditable(false);
      return localObject1;
    }
    if (localObject1.equals(Set.class))
    {
      (localObject1 = new JButton("Edit")).addActionListener(new class_669(this, paramJFrame, paramField));
      return localObject1;
    }
    if (localObject1.equals(BlockLevel.class))
    {
      localObject1 = new JPanel();
      localObject2 = new JTextPane();
      if ((paramGridBagConstraints = paramField.get(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)) != null)
      {
        localObject3 = (BlockLevel)paramGridBagConstraints;
        ((JTextPane)localObject2).setText(((BlockLevel)localObject3).toString());
      }
      else
      {
        ((JTextPane)localObject2).setText("   -   ");
      }
      (localObject3 = new JButton("Edit")).addActionListener(new class_674(this, paramJFrame, (JTextPane)localObject2));
      ((JPanel)localObject1).add((Component)localObject2);
      ((JPanel)localObject1).add((Component)localObject3);
      return localObject1;
    }
    if (localObject1.equals(BlockFactory.class))
    {
      localObject1 = new JPanel();
      localObject2 = new JTextPane();
      if ((paramGridBagConstraints = paramField.get(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)) != null)
      {
        localObject3 = (BlockFactory)paramGridBagConstraints;
        ((JTextPane)localObject2).setText(((BlockFactory)localObject3).toString());
      }
      else
      {
        ((JTextPane)localObject2).setText("   -   ");
      }
      (localObject3 = new JButton("Edit")).addActionListener(new class_521(this, paramJFrame, paramField, (JTextPane)localObject2));
      ((JPanel)localObject1).add((Component)localObject2);
      ((JPanel)localObject1).add((Component)localObject3);
      return localObject1;
    }
    if (localObject1.equals(String.class))
    {
      if ((localObject1 = (Element)paramField.getAnnotation(Element.class)) != null)
      {
        if (((Element)localObject1).textArea())
        {
          (localObject2 = new JTextArea()).setText(paramField.get(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation).toString().replace("\t", ""));
          paramGridBagConstraints.insets = new Insets(0, 50, 0, 0);
          this.jdField_field_919_of_type_Class_678 = new class_517((JTextArea)localObject2);
          (paramGridBagConstraints = new JScrollPane((Component)localObject2)).setPreferredSize(new Dimension(320, 200));
          return paramGridBagConstraints;
        }
        (localObject2 = new JTextField()).setText(paramField.get(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation).toString());
        this.jdField_field_919_of_type_Class_678 = new class_519((JTextField)localObject2);
        return localObject2;
      }
      (localObject2 = new JTextField()).setText(paramField.get(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation).toString());
      ((JTextField)localObject2).setEditable(false);
      return localObject2;
    }
    if (localObject1.equals(Vector3f.class))
    {
      localObject1 = new JPanel();
      localObject2 = (Vector3f)paramField.get(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation);
      paramGridBagConstraints = new JColorChooser(new Color(((Vector3f)localObject2).field_615, ((Vector3f)localObject2).field_616, ((Vector3f)localObject2).field_617, 1.0F));
      ((JPanel)localObject1).add(paramGridBagConstraints);
      localObject3 = new float[4];
      paramGridBagConstraints.getSelectionModel().addChangeListener(new class_513(paramGridBagConstraints, (float[])localObject3, (Vector3f)localObject2));
      return localObject1;
    }
    if (localObject1.equals(Boolean.TYPE))
    {
      (localObject1 = new JComboBox()).addItem(Boolean.valueOf(false));
      ((JComboBox)localObject1).addItem(Boolean.valueOf(true));
      ((JComboBox)localObject1).setSelectedItem(Boolean.valueOf(paramField.getBoolean(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
      ((JComboBox)localObject1).addActionListener(new class_650(this, paramField, (JComboBox)localObject1));
      return localObject1;
    }
    if (localObject1 == Integer.TYPE)
    {
      if ((localObject1 = (Element)paramField.getAnnotation(Element.class)) != null)
      {
        if (((Element)localObject1).states().length > 0)
        {
          localObject2 = new JComboBox();
          for (paramGridBagConstraints = 0; paramGridBagConstraints < ((Element)localObject1).states().length; paramGridBagConstraints++) {
            ((JComboBox)localObject2).addItem(localObject1.states()[paramGridBagConstraints]);
          }
          ((JComboBox)localObject2).setSelectedItem(String.valueOf(paramField.getInt(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
          ((JComboBox)localObject2).addActionListener(new class_656(this, (JComboBox)localObject2, paramField, (Element)localObject1, paramclass_642));
          return localObject2;
        }
        (localObject2 = new JTextField()).setText(String.valueOf(paramField.getInt(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
        this.jdField_field_919_of_type_Class_678 = new class_654((JTextField)localObject2, (Element)localObject1);
        new JPanel().add((Component)localObject2);
        return localObject2;
      }
      (localObject2 = new JTextField()).setText(String.valueOf(paramField.getInt(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
      ((JTextField)localObject2).setEditable(false);
      return localObject2;
    }
    if (localObject1 == Long.TYPE)
    {
      if ((localObject1 = (Element)paramField.getAnnotation(Element.class)) != null)
      {
        if (((Element)localObject1).states().length > 0)
        {
          localObject2 = new JComboBox();
          for (paramGridBagConstraints = 0; paramGridBagConstraints < ((Element)localObject1).states().length; paramGridBagConstraints++) {
            ((JComboBox)localObject2).addItem(localObject1.states()[paramGridBagConstraints]);
          }
          ((JComboBox)localObject2).setSelectedItem(String.valueOf(paramField.getLong(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
          ((JComboBox)localObject2).addActionListener(new class_660(this, (JComboBox)localObject2, paramField));
          return localObject2;
        }
        (localObject2 = new JTextField()).setText(String.valueOf(paramField.getLong(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
        this.jdField_field_919_of_type_Class_678 = new class_658((JTextField)localObject2, (Element)localObject1);
        return localObject2;
      }
      (localObject2 = new JTextField()).setText(String.valueOf(paramField.getLong(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
      ((JTextField)localObject2).setEditable(false);
      return localObject2;
    }
    if (localObject1 == Short.TYPE)
    {
      if ((localObject1 = (Element)paramField.getAnnotation(Element.class)) != null)
      {
        if (((Element)localObject1).states().length > 0)
        {
          localObject2 = new JComboBox();
          for (paramGridBagConstraints = 0; paramGridBagConstraints < ((Element)localObject1).states().length; paramGridBagConstraints++) {
            ((JComboBox)localObject2).addItem(localObject1.states()[paramGridBagConstraints]);
          }
          ((JComboBox)localObject2).setSelectedItem(String.valueOf(paramField.getShort(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
          ((JComboBox)localObject2).addActionListener(new class_664(this, (JComboBox)localObject2, paramField));
          return localObject2;
        }
        (localObject2 = new JTextField()).setText(String.valueOf(paramField.getShort(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
        this.jdField_field_919_of_type_Class_678 = new class_662((JTextField)localObject2, (Element)localObject1);
        return localObject2;
      }
      (localObject2 = new JTextField()).setText(String.valueOf(paramField.getShort(this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation)));
      ((JTextField)localObject2).setEditable(false);
      return localObject2;
    }
    return new JLabel("Cannot parse " + paramField.getName() + ": " + ((Class)localObject1).getSimpleName());
  }
  
  public final void a1()
  {
    Object localObject;
    try
    {
      this.jdField_field_919_of_type_Class_678.a(this.jdField_field_919_of_type_JavaLangReflectField, this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation);
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      (localObject = localIllegalArgumentException1).printStackTrace();
      class_29.a12((Exception)localObject);
    }
    catch (IllegalAccessException localIllegalAccessException1)
    {
      (localObject = localIllegalAccessException1).printStackTrace();
      class_29.a12((Exception)localObject);
    }
    try
    {
      this.jdField_field_919_of_type_Class_678.b(this.jdField_field_919_of_type_JavaLangReflectField, this.jdField_field_919_of_type_OrgSchemaGameCommonDataElementElementInformation);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException2)
    {
      (localObject = localIllegalArgumentException2).printStackTrace();
      class_29.a12((Exception)localObject);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException2)
    {
      (localObject = localIllegalAccessException2).printStackTrace();
      class_29.a12((Exception)localObject);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_644
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */