/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ import org.schema.game.common.data.element.BlockFactory;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.FactoryResource;
/*     */ 
/*     */ final class nm
/*     */   implements ActionListener
/*     */ {
/*     */   nm(nl paramnl, Set paramSet, oy paramoy, ElementInformation paramElementInformation, ol paramol)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 153 */     this.jdField_a_of_type_JavaUtilSet.clear();
/* 154 */     this.jdField_a_of_type_JavaUtilSet.addAll(nl.a(this.jdField_a_of_type_Nl).a());
/*     */ 
/* 156 */     this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.clear();
/* 157 */     this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.addAll(this.jdField_a_of_type_JavaUtilSet);
/* 158 */     for (paramActionEvent = 0; paramActionEvent < this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.size(); paramActionEvent++)
/*     */     {
/* 160 */       if (((oz)this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.get(paramActionEvent)).jdField_a_of_type_JavaUtilArrayList
/* 160 */         .isEmpty()) {
/* 161 */         this.jdField_a_of_type_Oy.jdField_a_of_type_JavaUtilArrayList.remove(paramActionEvent);
/* 162 */         paramActionEvent--;
/*     */       }
/*     */     }
/* 165 */     ElementInformation localElementInformation = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation; paramActionEvent = this.jdField_a_of_type_Oy;
/*     */     BlockFactory localBlockFactory;
/* 165 */     (localBlockFactory = new BlockFactory()).enhancer = paramActionEvent.jdField_a_of_type_Short; localBlockFactory.output = new FactoryResource[paramActionEvent.jdField_a_of_type_JavaUtilArrayList.size()][]; localBlockFactory.input = new FactoryResource[paramActionEvent.jdField_a_of_type_JavaUtilArrayList.size()][]; for (int i = 0; i < paramActionEvent.jdField_a_of_type_JavaUtilArrayList.size(); i++) { localBlockFactory.input[i] = new FactoryResource[((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).jdField_a_of_type_JavaUtilArrayList.size()]; for (int j = 0; j < ((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).jdField_a_of_type_JavaUtilArrayList.size(); j++) localBlockFactory.input[i][j] = ((FactoryResource)((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).jdField_a_of_type_JavaUtilArrayList.get(j)); localBlockFactory.output[i] = new FactoryResource[((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).b.size()]; for (j = 0; j < ((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).b.size(); j++) localBlockFactory.output[i][j] = ((FactoryResource)((oz)paramActionEvent.jdField_a_of_type_JavaUtilArrayList.get(i)).b.get(j));  } localElementInformation.setFactory(localBlockFactory);
/* 166 */     this.jdField_a_of_type_Ol.a();
/*     */ 
/* 168 */     this.jdField_a_of_type_Nl.dispose();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nm
 * JD-Core Version:    0.6.2
 */