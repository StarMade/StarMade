import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.schema.game.common.crashreporter.CrashReportGUI;
import org.schema.game.common.crashreporter.CrashReporter;

public final class class_706
  implements ActionListener
{
  public class_706(CrashReportGUI paramCrashReportGUI, JTextArea paramJTextArea) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    (paramActionEvent = new CrashReporter()).addObserver(this.jdField_field_972_of_type_OrgSchemaGameCommonCrashreporterCrashReportGUI);
    try
    {
      if (this.jdField_field_972_of_type_JavaxSwingJTextArea.getText().length() > 10000) {
        throw new IllegalArgumentException("The description is to long! \n\nIf this is an attempt to spam me:  :(");
      }
      if (CrashReportGUI.a(this.jdField_field_972_of_type_OrgSchemaGameCommonCrashreporterCrashReportGUI).getText().length() > 300) {
        throw new IllegalArgumentException("The Email is to long! \n\nIf this is an attempt to spam me:  :(");
      }
      paramActionEvent.a2(CrashReportGUI.a(this.jdField_field_972_of_type_OrgSchemaGameCommonCrashreporterCrashReportGUI).getText(), this.jdField_field_972_of_type_JavaxSwingJTextArea.getText());
      paramActionEvent.b();
      return;
    }
    catch (Exception localException)
    {
      class_29.a13(localException, false);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_706
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */