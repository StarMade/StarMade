/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import java.util.Set;
/*   4:    */import org.schema.game.common.data.element.ElementInformation;
/*   5:    */
/* 124:    */final class nu
/* 125:    */  implements ActionListener
/* 126:    */{
/* 127:    */  nu(nt paramnt, Set paramSet1, Set paramSet2) {}
/* 128:    */  
/* 129:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 130:    */  {
/* 131:131 */    this.jdField_a_of_type_JavaUtilSet.clear();
/* 132:132 */    this.jdField_a_of_type_JavaUtilSet.addAll(nt.a(this.jdField_a_of_type_Nt).a());
/* 133:133 */    this.b.clear();
/* 134:134 */    for (ElementInformation localElementInformation : this.jdField_a_of_type_JavaUtilSet) {
/* 135:135 */      this.b.add(Short.valueOf(localElementInformation.getId()));
/* 136:    */    }
/* 137:137 */    this.jdField_a_of_type_Nt.dispose();
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */