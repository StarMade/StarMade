/*   1:    */import java.util.ArrayList;
/*   2:    */
/*  15:    */public final class an
/*  16:    */  extends U
/*  17:    */  implements ys
/*  18:    */{
/*  19:    */  public mf a;
/*  20:    */  
/*  21:    */  public an(ct paramct)
/*  22:    */  {
/*  23: 23 */    super(paramct);
/*  24:    */  }
/*  25:    */  
/*  36:    */  public final ct a()
/*  37:    */  {
/*  38: 38 */    return super.a();
/*  39:    */  }
/*  40:    */  
/*  42:    */  public final void handleKeyEvent() {}
/*  43:    */  
/*  45:    */  public final void a(xp paramxp)
/*  46:    */  {
/*  47: 47 */    super.a(paramxp);
/*  48:    */  }
/*  49:    */  
/*  56:    */  public final void b(boolean paramBoolean)
/*  57:    */  {
/*  58: 58 */    wV.jdField_a_of_type_Boolean = !paramBoolean;
/*  59:    */    
/*  60: 60 */    if (paramBoolean) {
/*  61: 61 */      xe.b("0022_menu_ui - swoosh scroll large");
/*  62: 62 */      setChanged();
/*  63: 63 */      notifyObservers();
/*  64:    */    } else {
/*  65: 65 */      xe.b("0022_menu_ui - swoosh scroll small");
/*  66:    */    }
/*  67: 67 */    super.a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*  68:    */    
/*  70: 70 */    super.a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*  71:    */    
/*  75: 75 */    super.b(paramBoolean);
/*  76:    */  }
/*  77:    */  
/*  83:    */  public final void a(xq paramxq)
/*  84:    */  {
/*  85: 85 */    wV.jdField_a_of_type_Boolean = false;
/*  86: 86 */    super.a().a().a.a.a.e(true);
/*  87:    */  }
/*  88:    */  
/*  90:    */  public final void a(yz paramyz, xp paramxp)
/*  91:    */  {
/*  92: 92 */    if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0) && 
/*  93: 93 */      ("CONVERT".equals(paramyz.b())) && 
/*  94: 94 */      (this.a != null) && ((this.a instanceof md))) {
/*  95: 95 */      ((md)this.a).c();
/*  96:    */    }
/*  97:    */  }
/*  98:    */  
/* 102:    */  public final boolean a()
/* 103:    */  {
/* 104:104 */    return false;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public final void b()
/* 108:    */  {
/* 109:109 */    synchronized (super.a().b()) {
/* 110:110 */      super.a().b().add(new br(super.a()));
/* 111:111 */      e(true);
/* 112:112 */      return;
/* 113:    */    }
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     an
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */