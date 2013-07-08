/*   1:    */import java.util.HashSet;
/*   2:    */import org.lwjgl.input.Keyboard;
/*   3:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   4:    */
/*  21:    */public final class bj
/*  22:    */  extends U
/*  23:    */  implements al
/*  24:    */{
/*  25:    */  public bl a;
/*  26:    */  public au a;
/*  27:    */  
/*  28:    */  public bj(ct paramct)
/*  29:    */  {
/*  30: 30 */    super(paramct);
/*  31: 31 */    paramct = this;this.jdField_a_of_type_Au = new au(paramct.a(), paramct);paramct.a.add(paramct.jdField_a_of_type_Au);paramct.jdField_a_of_type_Bl = new bl(paramct);paramct.a.add(paramct.jdField_a_of_type_Bl);
/*  32:    */  }
/*  33:    */  
/*  34:    */  public final q a()
/*  35:    */  {
/*  36: 36 */    return new q(kd.a);
/*  37:    */  }
/*  38:    */  
/*  45:    */  public final au a()
/*  46:    */  {
/*  47: 47 */    return this.jdField_a_of_type_Au;
/*  48:    */  }
/*  49:    */  
/*  50:    */  public final EditableSendableSegmentController a()
/*  51:    */  {
/*  52: 52 */    return a().a();
/*  53:    */  }
/*  54:    */  
/*  64:    */  public final bl a()
/*  65:    */  {
/*  66: 66 */    return this.jdField_a_of_type_Bl;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public final void handleKeyEvent()
/*  70:    */  {
/*  71: 71 */    super.handleKeyEvent();
/*  72:    */    
/*  74: 74 */    if ((Keyboard.getEventKeyState()) && 
/*  75: 75 */      (Keyboard.getEventKey() == cv.r.a())) {
/*  76: 76 */      bj localbj = this;boolean bool = this.jdField_a_of_type_Au.b;localbj.jdField_a_of_type_Bl.c(bool);localbj.jdField_a_of_type_Au.c(!bool);
/*  77:    */    }
/*  78:    */  }
/*  79:    */  
/*  82:    */  public final void a(xp paramxp)
/*  83:    */  {
/*  84: 84 */    super.a(paramxp);
/*  85:    */  }
/*  86:    */  
/* 102:    */  public final void b(boolean paramBoolean)
/* 103:    */  {
/* 104:104 */    if ((paramBoolean) && 
/* 105:105 */      (!this.jdField_a_of_type_Au.b) && (!this.jdField_a_of_type_Bl.b)) {
/* 106:106 */      this.jdField_a_of_type_Au.c(true);
/* 107:    */    }
/* 108:    */    
/* 110:110 */    super.b(paramBoolean);
/* 111:    */  }
/* 112:    */  
/* 137:    */  public final void a(xq paramxq)
/* 138:    */  {
/* 139:139 */    if (a().a() == null) {
/* 140:140 */      return;
/* 141:    */    }
/* 142:    */    
/* 143:143 */    super.a(paramxq);
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */