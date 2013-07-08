/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Set;
/*   5:    */import org.schema.game.common.data.element.BlockFactory;
/*   6:    */import org.schema.game.common.data.element.ElementInformation;
/*   7:    */import org.schema.game.common.data.element.FactoryResource;
/*   8:    */
/* 146:    */final class nm
/* 147:    */  implements ActionListener
/* 148:    */{
/* 149:    */  nm(nl paramnl, Set paramSet, oy paramoy, ElementInformation paramElementInformation, ol paramol) {}
/* 150:    */  
/* 151:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 152:    */  {
/* 153:153 */    this.jdField_a_of_type_JavaUtilSet.clear();
/* 154:154 */    this.jdField_a_of_type_JavaUtilSet.addAll(nl.a(this.jdField_a_of_type_Nl).a());
/* 155:    */    
/* 156:156 */    this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.clear();
/* 157:157 */    this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.addAll(this.jdField_a_of_type_JavaUtilSet);
/* 158:158 */    for (paramActionEvent = 0; paramActionEvent < this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.size(); paramActionEvent++)
/* 159:    */    {
/* 160:160 */      if (((oz)this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.get(paramActionEvent)).jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 161:161 */        this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.remove(paramActionEvent);
/* 162:162 */        paramActionEvent--;
/* 163:    */      }
/* 164:    */    }
/* 165:165 */    ElementInformation localElementInformation = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;paramActionEvent = this.jdField_a_of_type_Oy; BlockFactory localBlockFactory; (localBlockFactory = new BlockFactory()).enhancer = paramActionEvent.jdField_a_of_type_Short;localBlockFactory.output = new FactoryResource[paramActionEvent.jdField_a_of_type_JavaUtilArrayList.size()][];localBlockFactory.input = new FactoryResource[paramActionEvent.jdField_a_of_type_JavaUtilArrayList.size()][]; for (int i = 0; i < paramActionEvent.jdField_a_of_type_JavaUtilArrayList.size(); i++) { localBlockFactory.input[i] = new FactoryResource[((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).jdField_a_of_type_JavaUtilArrayList.size()]; for (int j = 0; j < ((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).jdField_a_of_type_JavaUtilArrayList.size(); j++) localBlockFactory.input[i][j] = ((FactoryResource)((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).jdField_a_of_type_JavaUtilArrayList.get(j)); localBlockFactory.output[i] = new FactoryResource[((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).b.size()]; for (j = 0; j < ((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).b.size(); j++) localBlockFactory.output[i][j] = ((FactoryResource)((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).b.get(j)); } localElementInformation.setFactory(localBlockFactory);
/* 166:166 */    this.jdField_a_of_type_Ol.a();
/* 167:    */    
/* 168:168 */    this.jdField_a_of_type_Nl.dispose();
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */