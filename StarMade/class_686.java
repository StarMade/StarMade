import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.facedit.ElementTreePanel;

public final class class_686
  extends JDialog
  implements TreeSelectionListener
{
  private static final long serialVersionUID = 3170967672363822879L;
  private JTextField jdField_field_952_of_type_JavaxSwingJTextField;
  private final HashSet jdField_field_952_of_type_JavaUtilHashSet = new HashSet();
  private JTabbedPane jdField_field_952_of_type_JavaxSwingJTabbedPane;
  private JPanel jdField_field_952_of_type_JavaxSwingJPanel;
  private JPanel field_953;
  private ElementTreePanel jdField_field_952_of_type_OrgSchemaGameCommonFaceditElementTreePanel;
  private class_684 jdField_field_952_of_type_Class_684;
  private JList jdField_field_952_of_type_JavaxSwingJList;
  private ElementInformation jdField_field_952_of_type_OrgSchemaGameCommonDataElementElementInformation;
  
  private void a()
  {
    HashSet localHashSet = new HashSet(this.jdField_field_952_of_type_JavaUtilHashSet);
    String str;
    if ((str = this.jdField_field_952_of_type_JavaxSwingJTextField.getText()).trim().length() != 0)
    {
      System.err.println("CHECKING ALL FOR " + str.trim().toLowerCase(Locale.ENGLISH));
      localObject1 = this.jdField_field_952_of_type_JavaUtilHashSet.iterator();
      while (((Iterator)localObject1).hasNext()) {
        if (!(localObject2 = (ElementInformation)((Iterator)localObject1).next()).getName().toLowerCase(Locale.ENGLISH).contains(str.trim().toLowerCase(Locale.ENGLISH))) {
          localHashSet.remove(localObject2);
        }
      }
    }
    Object localObject1 = new class_688();
    System.err.println("FINAL SET " + localHashSet.size());
    Object localObject2 = new ElementInformation[localHashSet.size()];
    localHashSet.toArray((Object[])localObject2);
    Arrays.sort((Object[])localObject2, (Comparator)localObject1);
    this.jdField_field_952_of_type_JavaxSwingJList.setListData((Object[])localObject2);
  }
  
  public class_686(JFrame paramJFrame, class_684 paramclass_684)
  {
    super(paramJFrame, true);
    this.jdField_field_952_of_type_Class_684 = paramclass_684;
    paramclass_684 = (paramJFrame = ElementKeyMap.typeList()).length;
    for (class_684 localclass_684 = 0; localclass_684 < paramclass_684; localclass_684++)
    {
      short s = paramJFrame[localclass_684];
      this.jdField_field_952_of_type_JavaUtilHashSet.add(ElementKeyMap.getInfo(s));
    }
    setAlwaysOnTop(true);
    setBounds(100, 100, 702, 440);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_952_of_type_JavaxSwingJTabbedPane = new JTabbedPane(1);
    getContentPane().add(this.jdField_field_952_of_type_JavaxSwingJTabbedPane, "Center");
    this.jdField_field_952_of_type_JavaxSwingJPanel = new JPanel();
    this.jdField_field_952_of_type_JavaxSwingJTabbedPane.addTab("Search", null, this.jdField_field_952_of_type_JavaxSwingJPanel, null);
    (paramJFrame = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramJFrame.rowHeights = new int[] { 0, 0, 0 };
    paramJFrame.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramJFrame.rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    this.jdField_field_952_of_type_JavaxSwingJPanel.setLayout(paramJFrame);
    this.jdField_field_952_of_type_JavaxSwingJTextField = new JTextField("");
    (paramclass_684 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramclass_684.fill = 2;
    paramclass_684.gridx = 0;
    paramclass_684.gridy = 0;
    this.jdField_field_952_of_type_JavaxSwingJPanel.add(this.jdField_field_952_of_type_JavaxSwingJTextField, paramclass_684);
    this.jdField_field_952_of_type_JavaxSwingJTextField.setColumns(10);
    this.jdField_field_952_of_type_JavaxSwingJTextField.addKeyListener(new class_690(this));
    this.jdField_field_952_of_type_JavaxSwingJList = new JList();
    this.jdField_field_952_of_type_JavaxSwingJList.addMouseListener(new class_692(this));
    this.jdField_field_952_of_type_JavaxSwingJList.setSelectionMode(0);
    (paramclass_684 = new GridBagConstraints()).fill = 1;
    paramclass_684.gridx = 0;
    paramclass_684.gridy = 1;
    this.jdField_field_952_of_type_JavaxSwingJPanel.add(new JScrollPane(this.jdField_field_952_of_type_JavaxSwingJList), paramclass_684);
    this.field_953 = new JPanel();
    this.jdField_field_952_of_type_JavaxSwingJTabbedPane.addTab("Tree", null, this.field_953, null);
    this.field_953.setLayout(new GridLayout(0, 1, 0, 0));
    this.jdField_field_952_of_type_OrgSchemaGameCommonFaceditElementTreePanel = new ElementTreePanel(this);
    this.jdField_field_952_of_type_OrgSchemaGameCommonFaceditElementTreePanel.a1().setEditable(false);
    this.jdField_field_952_of_type_OrgSchemaGameCommonFaceditElementTreePanel.a1().addMouseListener(new class_694(this));
    this.field_953.add(this.jdField_field_952_of_type_OrgSchemaGameCommonFaceditElementTreePanel);
    (paramJFrame = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add(paramJFrame, "South");
    (paramclass_684 = new JButton("OK")).setActionCommand("OK");
    paramJFrame.add(paramclass_684);
    getRootPane().setDefaultButton(paramclass_684);
    paramclass_684.addActionListener(new class_696(this));
    (paramclass_684 = new JButton("Cancel")).setActionCommand("Cancel");
    paramclass_684.addActionListener(new class_698(this));
    paramJFrame.add(paramclass_684);
    a();
  }
  
  public final void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
  {
    if (((paramTreeSelectionEvent = (DefaultMutableTreeNode)paramTreeSelectionEvent.getPath().getLastPathComponent()).getUserObject() instanceof ElementInformation))
    {
      paramTreeSelectionEvent = (ElementInformation)paramTreeSelectionEvent.getUserObject();
      this.jdField_field_952_of_type_OrgSchemaGameCommonDataElementElementInformation = paramTreeSelectionEvent;
      System.err.println("NOW SELECTED " + this.jdField_field_952_of_type_OrgSchemaGameCommonDataElementElementInformation);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_686
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */