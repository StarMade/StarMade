/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.HashSet;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.lwjgl.input.Mouse;
/*   6:    */
/*  19:    */public final class aa
/*  20:    */  extends U
/*  21:    */{
/*  22:    */  public static final HashSet b;
/*  23:    */  private long b;
/*  24:    */  
/*  25:    */  static
/*  26:    */  {
/*  27: 27 */    jdField_b_of_type_JavaUtilHashSet = new HashSet();
/*  28:    */  }
/*  29:    */  
/*  30:    */  public aa(ct paramct) {
/*  31: 31 */    super(paramct);
/*  32:    */  }
/*  33:    */  
/*  41:    */  public final void handleKeyEvent()
/*  42:    */  {
/*  43: 43 */    super.handleKeyEvent();
/*  44:    */    
/*  45: 45 */    if ((Keyboard.getEventKeyState()) && 
/*  46: 46 */      (Keyboard.getEventKey() == 88)) {
/*  47: 47 */      a().a().a().a().a(new q(0, 0, 0));
/*  48:    */    }
/*  49:    */    
/*  50: 50 */    a().a().a().handleKeyEvent();
/*  51:    */  }
/*  52:    */  
/*  55:    */  public final void a(xp paramxp)
/*  56:    */  {
/*  57: 57 */    if ((paramxp.jdField_a_of_type_Boolean) && 
/*  58: 58 */      (paramxp.jdField_a_of_type_Int == 0)) {
/*  59: 59 */      int i = System.currentTimeMillis() - this.jdField_b_of_type_Long < 300L ? 1 : 0;
/*  60: 60 */      for (cD localcD : jdField_b_of_type_JavaUtilHashSet) {
/*  61: 61 */        if (i != 0) {
/*  62: 62 */          q localq = a().a().a().a().b;
/*  63: 63 */          cG localcG = (cG)localcD;
/*  64: 64 */          a().a().a().a().a((int)(localcG.a().x / 6.25F) + (localq.jdField_a_of_type_Int << 4), (int)(localcG.a().y / 6.25F) + (localq.b << 4), (int)(localcG.a().z / 6.25F) + (localq.c << 4), false);
/*  65:    */        }
/*  66:    */        
/*  69: 69 */        System.err.println("[CLIENT][MAPMANAGER] clicked on " + localcD);
/*  70:    */      }
/*  71: 71 */      this.jdField_b_of_type_Long = System.currentTimeMillis();
/*  72:    */    }
/*  73:    */    
/*  75: 75 */    super.a(paramxp);
/*  76:    */  }
/*  77:    */  
/*  81:    */  public final void b(boolean paramBoolean)
/*  82:    */  {
/*  83: 83 */    System.err.println("MAP CONTROL MANAGER ACTIVE: " + paramBoolean);
/*  84: 84 */    if (paramBoolean) {
/*  85: 85 */      xe.b("0022_menu_ui - swoosh scroll large");
/*  86: 86 */      a().a().a().a().a(new q(0, 0, 0));
/*  87:    */    }
/*  88:    */    else {
/*  89: 89 */      xe.b("0022_menu_ui - swoosh scroll small");
/*  90:    */    }
/*  91: 91 */    a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*  92:    */    
/*  94: 94 */    a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*  95:    */    
/*  97: 97 */    if (paramBoolean) {
/*  98: 98 */      a().a().a().d();
/*  99:    */    }
/* 100:    */    
/* 101:101 */    super.b(paramBoolean);
/* 102:    */  }
/* 103:    */  
/* 105:    */  public final void a(xq paramxq)
/* 106:    */  {
/* 107:107 */    wV.jdField_a_of_type_Boolean = Mouse.isButtonDown(1);
/* 108:108 */    a().a().a.a.a.e(true);
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     aa
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */