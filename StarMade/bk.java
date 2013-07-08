/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.ArrayList;
/*   3:    */import java.util.Iterator;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*   6:    */
/*  25:    */public class bk
/*  26:    */  extends U
/*  27:    */  implements ys
/*  28:    */{
/*  29:    */  private le a;
/*  30:    */  
/*  31:    */  public final void a(yz paramyz, xp paramxp)
/*  32:    */  {
/*  33: 33 */    paramxp = null; if ((this.c) && (!this.jdField_a_of_type_Boolean)) {
/*  34: 34 */      for (paramxp = a().getMouseEvents().iterator(); paramxp.hasNext();) { Object localObject;
/*  35: 35 */        if (((localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && (!((xp)localObject).jdField_a_of_type_Boolean) && 
/*  36: 36 */          ((paramyz instanceof yD)))
/*  37:    */        {
/*  38:    */          yA localyA;
/*  39: 39 */          int i = (localyA = (yA)(localObject = (yD)paramyz).a()).indexOf(localObject);
/*  40: 40 */          localyA.e();
/*  41: 41 */          ((yD)localObject).a(true);
/*  42: 42 */          System.err.println("Controller manager call back: index: " + i);
/*  43: 43 */          this.a = ((le)((yD)localObject).a().b());
/*  44:    */        }
/*  45:    */      }
/*  46:    */    }
/*  47:    */  }
/*  48:    */  
/*  56:    */  public void handleKeyEvent()
/*  57:    */  {
/*  58: 58 */    super.handleKeyEvent();
/*  59:    */    
/*  60: 60 */    if ((Keyboard.getEventKeyState()) && 
/*  61: 61 */      (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11)) {
/*  62: 62 */      int i = Keyboard.getEventKey() - 2;bk localbk = this; if (this.a != null) { if ((!d) && (localbk.a().a() == null)) throw new AssertionError(); if (!localbk.a().a().a(localbk.a().a())) localbk.a().a().a().add(new cz(localbk.a().a(), localbk.a().a().getUniqueIdentifier())); try { cz localcz = localbk.a().a().a(localbk.a().a());int j = -1;q localq = localbk.a.a(new q()); if (localcz.a(localq)) { j = localcz.a(localq);localbk.setChanged(); } if (j != i) { System.err.println("ASSINGING: " + i + " to " + localbk.a);localcz.a(i, localq, true);localbk.setChanged(); } localbk.notifyObservers(localbk.a);return; } catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) { localShipConfigurationNotFoundException;
/*  63:    */        }
/*  64:    */      }
/*  65:    */    }
/*  66:    */  }
/*  67:    */  
/*  68: 68 */  public final boolean a() { return !a().b().isEmpty(); }
/*  69:    */  
/* 102:    */  public final void b(boolean paramBoolean)
/* 103:    */  {
/* 104:104 */    wV.jdField_a_of_type_Boolean = !paramBoolean;
/* 105:105 */    super.b(paramBoolean);
/* 106:    */    
/* 107:107 */    a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/* 108:    */    
/* 111:111 */    a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/* 112:    */  }
/* 113:    */  
/* 116:    */  public final void a(xq paramxq)
/* 117:    */  {
/* 118:118 */    wV.jdField_a_of_type_Boolean = false;
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */