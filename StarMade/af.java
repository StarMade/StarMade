/*    */ import java.util.Set;
/*    */ import org.schema.game.common.controller.CannotBeControlledException;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ 
/*    */ public abstract class af extends U
/*    */ {
/*    */   public af(ct paramct)
/*    */   {
/* 10 */     super(paramct);
/*    */   }
/*    */ 
/*    */   public final void a(CannotBeControlledException paramCannotBeControlledException) {
/* 14 */     if ((paramCannotBeControlledException != null) && (paramCannotBeControlledException.a != null) && (paramCannotBeControlledException.a.controlledBy != null) && (paramCannotBeControlledException.a.controlledBy.size() > 0))
/* 15 */       a().a().b(paramCannotBeControlledException.a.getName() + " cannot be\nconnected to selected block:\n" + paramCannotBeControlledException.b.getName());
/*    */   }
/*    */ 
/*    */   public abstract void b();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     af
 * JD-Core Version:    0.6.2
 */