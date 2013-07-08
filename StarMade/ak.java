/*  1:   */import org.lwjgl.input.Keyboard;
/*  2:   */import org.schema.schine.network.ChatSystem;
/*  3:   */
/* 13:   */public final class ak
/* 14:   */  extends U
/* 15:   */{
/* 16:   */  public ak(ct paramct)
/* 17:   */  {
/* 18:18 */    super(paramct);
/* 19:   */  }
/* 20:   */  
/* 21:   */  public final void handleKeyEvent()
/* 22:   */  {
/* 23:23 */    a().getChat().handleKeyEvent();
/* 24:   */  }
/* 25:   */  
/* 26:   */  public final void a(xp paramxp)
/* 27:   */  {
/* 28:28 */    super.a(paramxp);
/* 29:   */  }
/* 30:   */  
/* 36:   */  public final void b(boolean paramBoolean)
/* 37:   */  {
/* 38:38 */    a().a().a.jdField_a_of_type_Aq.e(paramBoolean);
/* 39:39 */    a().a().a.jdField_a_of_type_Ae.e(paramBoolean);
/* 40:40 */    a().a().a.jdField_a_of_type_Ac.e(paramBoolean);
/* 41:41 */    super.b(paramBoolean);
/* 42:   */  }
/* 43:   */  
/* 47:   */  public final void c(boolean paramBoolean)
/* 48:   */  {
/* 49:49 */    super.c(paramBoolean);
/* 50:50 */    Keyboard.enableRepeatEvents(paramBoolean);
/* 51:   */  }
/* 52:   */  
/* 55:   */  public final void a(xq paramxq)
/* 56:   */  {
/* 57:57 */    super.a(paramxq);
/* 58:58 */    wV.a = false;
/* 59:   */  }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ak
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */