/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Field;
/*     */ import javax.swing.JTextArea;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ 
/*     */ final class oc
/*     */   implements na
/*     */ {
/*     */   oc(JTextArea paramJTextArea)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(Field paramField, ElementInformation paramElementInformation)
/*     */   {
/* 340 */     paramField.set(paramElementInformation, this.a.getText());
/* 341 */     System.err.println("APPLIED TEXT AREA");
/*     */   }
/*     */ 
/*     */   public final void b(Field paramField, ElementInformation paramElementInformation)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oc
 * JD-Core Version:    0.6.2
 */