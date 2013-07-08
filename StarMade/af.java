/*  1:   */import java.util.Set;
/*  2:   */import org.schema.game.common.controller.CannotBeControlledException;
/*  3:   */import org.schema.game.common.data.element.ElementInformation;
/*  4:   */
/*  5:   */public abstract class af
/*  6:   */  extends U
/*  7:   */{
/*  8:   */  public af(ct paramct)
/*  9:   */  {
/* 10:10 */    super(paramct);
/* 11:   */  }
/* 12:   */  
/* 13:   */  public final void a(CannotBeControlledException paramCannotBeControlledException) {
/* 14:14 */    if ((paramCannotBeControlledException != null) && (paramCannotBeControlledException.a != null) && (paramCannotBeControlledException.a.controlledBy != null) && (paramCannotBeControlledException.a.controlledBy.size() > 0)) {
/* 15:15 */      a().a().b(paramCannotBeControlledException.a.getName() + " cannot be\nconnected to selected block:\n" + paramCannotBeControlledException.b.getName());
/* 16:   */    }
/* 17:   */  }
/* 18:   */  
/* 19:   */  public abstract void b();
/* 20:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     af
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */