/*     */ import java.lang.reflect.Field;
/*     */ import javax.swing.JTextPane;
/*     */ import org.schema.game.common.data.element.BlockFactory;
/*     */ 
/*     */ final class ob
/*     */   implements ol
/*     */ {
/*     */   ob(oa paramoa)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */     try
/*     */     {
/* 298 */       if ((
/* 298 */         localObject = this.a.jdField_a_of_type_JavaLangReflectField.get(nM.a(this.a.jdField_a_of_type_NM))) != null)
/*     */       {
/* 299 */         localObject = (BlockFactory)localObject;
/* 300 */         this.a.jdField_a_of_type_JavaxSwingJTextPane.setText(((BlockFactory)localObject).toString());
/* 301 */         return; } this.a.jdField_a_of_type_JavaxSwingJTextPane.setText("   -   ");
/*     */       return; } catch (IllegalArgumentException localIllegalArgumentException) {
/* 304 */       (
/* 306 */         localObject = 
/* 312 */         localIllegalArgumentException).printStackTrace();
/* 307 */       d.a((Exception)localObject);
/*     */       return;
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/*     */       Object localObject;
/* 308 */       (
/* 310 */         localObject = 
/* 312 */         localIllegalAccessException).printStackTrace();
/* 311 */       d.a((Exception)localObject);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ob
 * JD-Core Version:    0.6.2
 */