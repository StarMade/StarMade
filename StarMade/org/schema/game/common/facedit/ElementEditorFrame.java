package org.schema.game.common.facedit;

import class_503;
import class_624;
import class_626;
import class_628;
import class_632;
import class_634;
import class_636;
import class_638;
import class_640;
import class_642;
import class_648;
import class_682;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.schema.game.common.data.element.ElementInformation;

public class ElementEditorFrame
  extends JFrame
  implements Observer, TreeSelectionListener
{
  private static final long serialVersionUID = -1919570665689983369L;
  private JPanel jdField_field_2213_of_type_JavaxSwingJPanel;
  private JSplitPane jdField_field_2213_of_type_JavaxSwingJSplitPane;
  private JMenuBar jdField_field_2213_of_type_JavaxSwingJMenuBar;
  private JMenu jdField_field_2213_of_type_JavaxSwingJMenu;
  private JMenu jdField_field_2214_of_type_JavaxSwingJMenu;
  private JMenuItem jdField_field_2213_of_type_JavaxSwingJMenuItem;
  private JMenuItem jdField_field_2214_of_type_JavaxSwingJMenuItem;
  private JMenuItem field_2215;
  private JMenuItem field_2216;
  private JMenuItem field_2217;
  private JMenuItem field_2218;
  private JMenuItem field_2219;
  private class_682 jdField_field_2213_of_type_Class_682 = new class_682();
  private JMenuItem field_2220;
  private JMenuItem field_2221;
  private JFileChooser jdField_field_2213_of_type_JavaxSwingJFileChooser;
  private JMenuItem field_2222;
  private JMenuItem field_2223;
  private ElementInformation jdField_field_2213_of_type_OrgSchemaGameCommonDataElementElementInformation;
  
  public static void main(String[] paramArrayOfString)
  {
    EventQueue.invokeLater(new class_503());
  }
  
  public ElementEditorFrame()
  {
    setTitle("StarMade - Block Editor");
    setDefaultCloseOperation(3);
    setBounds(100, 100, 1200, 700);
    this.jdField_field_2213_of_type_JavaxSwingJMenuBar = new JMenuBar();
    setJMenuBar(this.jdField_field_2213_of_type_JavaxSwingJMenuBar);
    this.jdField_field_2213_of_type_JavaxSwingJMenu = new JMenu("File");
    this.jdField_field_2213_of_type_JavaxSwingJMenuBar.add(this.jdField_field_2213_of_type_JavaxSwingJMenu);
    this.jdField_field_2213_of_type_JavaxSwingJMenuItem = new JMenuItem("New");
    this.jdField_field_2213_of_type_JavaxSwingJMenuItem.addActionListener(new class_632(this));
    this.jdField_field_2213_of_type_JavaxSwingJMenu.add(this.jdField_field_2213_of_type_JavaxSwingJMenuItem);
    this.field_2216 = new JMenuItem("Open");
    this.field_2216.addActionListener(new class_626(this));
    this.jdField_field_2213_of_type_JavaxSwingJMenu.add(this.field_2216);
    this.jdField_field_2214_of_type_JavaxSwingJMenuItem = new JMenuItem("Save");
    this.jdField_field_2214_of_type_JavaxSwingJMenuItem.addActionListener(new class_628(this));
    this.jdField_field_2213_of_type_JavaxSwingJMenu.add(this.jdField_field_2214_of_type_JavaxSwingJMenuItem);
    this.field_2215 = new JMenuItem("Save As...");
    this.field_2215.addActionListener(new class_638(this));
    this.jdField_field_2213_of_type_JavaxSwingJMenu.add(this.field_2215);
    this.field_2217 = new JMenuItem("Exit");
    this.field_2217.addActionListener(new class_640(this));
    this.jdField_field_2213_of_type_JavaxSwingJMenu.add(this.field_2217);
    this.jdField_field_2214_of_type_JavaxSwingJMenu = new JMenu("Edit");
    this.jdField_field_2213_of_type_JavaxSwingJMenuBar.add(this.jdField_field_2214_of_type_JavaxSwingJMenu);
    this.field_2218 = new JMenuItem("Move Entry");
    this.field_2218.addActionListener(new class_634());
    this.field_2222 = new JMenuItem("Create New Entry");
    this.jdField_field_2214_of_type_JavaxSwingJMenu.add(this.field_2222);
    this.jdField_field_2214_of_type_JavaxSwingJMenu.add(this.field_2218);
    this.field_2222.addActionListener(new class_636(this));
    this.field_2219 = new JMenuItem("Duplicate Entry");
    this.field_2219.addActionListener(new class_648());
    this.jdField_field_2214_of_type_JavaxSwingJMenu.add(this.field_2219);
    this.field_2223 = new JMenuItem("Remove Entry");
    this.field_2223.setEnabled(this.jdField_field_2213_of_type_OrgSchemaGameCommonDataElementElementInformation != null);
    this.field_2223.addActionListener(new class_624(this));
    this.jdField_field_2214_of_type_JavaxSwingJMenu.add(this.field_2223);
    this.field_2220 = new JMenuItem("Add Category");
    this.jdField_field_2214_of_type_JavaxSwingJMenu.add(this.field_2220);
    this.field_2221 = new JMenuItem("Remove Category");
    this.jdField_field_2214_of_type_JavaxSwingJMenu.add(this.field_2221);
    this.jdField_field_2213_of_type_JavaxSwingJPanel = new JPanel();
    this.jdField_field_2213_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.jdField_field_2213_of_type_JavaxSwingJPanel.setLayout(new BorderLayout(0, 0));
    setContentPane(this.jdField_field_2213_of_type_JavaxSwingJPanel);
    this.jdField_field_2213_of_type_JavaxSwingJSplitPane = new JSplitPane();
    this.jdField_field_2213_of_type_JavaxSwingJPanel.add(this.jdField_field_2213_of_type_JavaxSwingJSplitPane, "Center");
    this.jdField_field_2213_of_type_JavaxSwingJSplitPane.setDividerLocation(400);
    ElementTreePanel localElementTreePanel = new ElementTreePanel(this);
    this.jdField_field_2213_of_type_JavaxSwingJSplitPane.setLeftComponent(new JScrollPane(localElementTreePanel));
    this.jdField_field_2213_of_type_JavaxSwingJSplitPane.setRightComponent(new JLabel("nothing selected"));
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    paramObservable = new class_642(this, ((Short)paramObject).shortValue());
    this.jdField_field_2213_of_type_JavaxSwingJSplitPane.setRightComponent(paramObservable);
  }
  
  public void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
  {
    if (((paramTreeSelectionEvent = (DefaultMutableTreeNode)paramTreeSelectionEvent.getPath().getLastPathComponent()).getUserObject() instanceof ElementInformation))
    {
      System.err.println("Setting content pane: " + paramTreeSelectionEvent.getUserObject());
      this.jdField_field_2213_of_type_OrgSchemaGameCommonDataElementElementInformation = ((ElementInformation)paramTreeSelectionEvent.getUserObject());
      this.field_2223.setEnabled(this.jdField_field_2213_of_type_OrgSchemaGameCommonDataElementElementInformation != null);
      paramTreeSelectionEvent = new class_642(this, this.jdField_field_2213_of_type_OrgSchemaGameCommonDataElementElementInformation.getId());
      paramTreeSelectionEvent = new JScrollPane(paramTreeSelectionEvent);
      ScrollPaneLayout localScrollPaneLayout = new ScrollPaneLayout();
      paramTreeSelectionEvent.setLayout(localScrollPaneLayout);
      this.jdField_field_2213_of_type_JavaxSwingJSplitPane.setRightComponent(paramTreeSelectionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.facedit.ElementEditorFrame
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */