/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.Iterator;
/*   3:    */
/*  15:    */public final class bg
/*  16:    */  extends U
/*  17:    */  implements ys
/*  18:    */{
/*  19:    */  public bg(ct paramct)
/*  20:    */  {
/*  21: 21 */    super(paramct);
/*  22:    */  }
/*  23:    */  
/*  24: 24 */  public bh a = new bh();
/*  25:    */  
/*  26:    */  public final void a(yz paramyz, xp paramxp)
/*  27:    */  {
/*  28: 28 */    if ((this.c) && (!this.jdField_a_of_type_Boolean)) {
/*  29: 29 */      for (paramxp = a().getMouseEvents().iterator(); paramxp.hasNext();) { Object localObject;
/*  30: 30 */        if (((localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && (!((xp)localObject).jdField_a_of_type_Boolean) && 
/*  31: 31 */          ((paramyz instanceof yD)))
/*  32:    */        {
/*  33:    */          yA localyA;
/*  34: 34 */          (localyA = (yA)(localObject = (yD)paramyz).a()).indexOf(localObject);
/*  35: 35 */          localyA.e();
/*  36: 36 */          ((yD)localObject).a(true);
/*  37: 37 */          a().a().a.a.a.a(((ib)((yD)localObject).a()).a());
/*  38:    */        }
/*  39:    */      }
/*  40:    */    }
/*  41:    */  }
/*  42:    */  
/*  53:    */  public final void handleKeyEvent()
/*  54:    */  {
/*  55: 55 */    super.handleKeyEvent();
/*  56:    */  }
/*  57:    */  
/*  60:    */  public final void a(xp paramxp)
/*  61:    */  {
/*  62: 62 */    super.a(paramxp);
/*  63:    */  }
/*  64:    */  
/*  66:    */  public final boolean a()
/*  67:    */  {
/*  68: 68 */    return !a().b().isEmpty();
/*  69:    */  }
/*  70:    */  
/*  73:    */  public final void b(boolean paramBoolean)
/*  74:    */  {
/*  75: 75 */    wV.jdField_a_of_type_Boolean = !paramBoolean;
/*  76:    */    
/*  77: 77 */    if (paramBoolean) {
/*  78: 78 */      xe.b("0022_menu_ui - swoosh scroll large");
/*  79:    */    } else {
/*  80: 80 */      xe.b("0022_menu_ui - swoosh scroll small");
/*  81:    */    }
/*  82: 82 */    a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*  83:    */    
/*  85: 85 */    a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*  86:    */    
/*  88: 88 */    super.b(paramBoolean);
/*  89:    */  }
/*  90:    */  
/*  92:    */  public final void a(xq paramxq)
/*  93:    */  {
/*  94: 94 */    wV.jdField_a_of_type_Boolean = false;
/*  95:    */    
/*  96: 96 */    a().a().a.a.a.e(true);
/*  97:    */  }
/*  98:    */  
/* 108:    */  public final bh a()
/* 109:    */  {
/* 110:110 */    return new bh(this.a);
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */