/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import javax.swing.JSlider;
/*   4:    */import javax.swing.JTextPane;
/*   5:    */import org.schema.game.common.data.element.BlockLevel;
/*   6:    */import org.schema.game.common.data.element.ElementInformation;
/*   7:    */
/* 133:    */final class oi
/* 134:    */  implements ActionListener
/* 135:    */{
/* 136:    */  oi(of paramof, ElementInformation paramElementInformation, JTextPane paramJTextPane) {}
/* 137:    */  
/* 138:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 139:    */  {
/* 140:140 */    if (of.a(this.jdField_a_of_type_Of) > 0) {
/* 141:141 */      this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.setLevel(new BlockLevel(of.a(this.jdField_a_of_type_Of), of.a(this.jdField_a_of_type_Of).getValue()));
/* 142:    */    } else {
/* 143:143 */      this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.setLevel(null);
/* 144:    */    }
/* 145:    */    
/* 146:146 */    this.jdField_a_of_type_JavaxSwingJTextPane.setText(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getLevel() != null ? this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getLevel().toString() : "   -   ");
/* 147:147 */    this.jdField_a_of_type_Of.dispose();
/* 148:    */  }
/* 149:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */