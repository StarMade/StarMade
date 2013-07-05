/*     */ import java.lang.reflect.Field;
/*     */ import javax.swing.JTextField;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ 
/*     */ final class od
/*     */   implements na
/*     */ {
/*     */   od(JTextField paramJTextField)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(Field paramField, ElementInformation paramElementInformation)
/*     */   {
/* 358 */     paramField.set(paramElementInformation, this.a.getText());
/*     */   }
/*     */ 
/*     */   public final void b(Field paramField, ElementInformation paramElementInformation)
/*     */   {
/* 363 */     this.a.setText(paramField.get(paramElementInformation).toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     od
 * JD-Core Version:    0.6.2
 */