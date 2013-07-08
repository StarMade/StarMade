/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.ArrayList;
/*   3:    */import java.util.Iterator;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*   6:    */
/*  22:    */public class bm
/*  23:    */  extends U
/*  24:    */  implements ys
/*  25:    */{
/*  26:    */  private le a;
/*  27:    */  private boolean d;
/*  28:    */  
/*  29:    */  public bm(ct paramct)
/*  30:    */  {
/*  31: 31 */    super(paramct);
/*  32:    */  }
/*  33:    */  
/*  34:    */  public final void a(yz paramyz, xp paramxp)
/*  35:    */  {
/*  36: 36 */    paramxp = null; if ((this.c) && (!this.jdField_a_of_type_Boolean)) { Object localObject;
/*  37: 37 */      for (paramxp = a().getMouseEvents().iterator(); paramxp.hasNext(); 
/*  38:    */          
/*  47: 47 */          ((bm)localObject).notifyObservers(((bm)localObject).a)) {
/*  48: 38 */        if (((localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && (!((xp)localObject).jdField_a_of_type_Boolean) && 
/*  49: 39 */          ((paramyz instanceof yD)))
/*  50:    */        {
/*  51:    */          yA localyA;
/*  52: 42 */          int i = (localyA = (yA)(localObject = (yD)paramyz).a()).indexOf(localObject);
/*  53: 43 */          localyA.e();
/*  54: 44 */          ((yD)localObject).a(true);
/*  55: 45 */          System.err.println("Controller manager call back: index: " + i);
/*  56: 46 */          this.a = ((le)((yD)localObject).a().b());
/*  57: 47 */          localObject = this;setChanged(); if (((bm)localObject).a != null) { ((bm)localObject).a.a(-2);
/*  58:    */          }
/*  59:    */        }
/*  60:    */      }
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  64:    */  public final void b()
/*  65:    */  {
/*  66: 56 */    this.d = true;
/*  67:    */  }
/*  68:    */  
/*  83:    */  public final le a()
/*  84:    */  {
/*  85: 75 */    return this.a;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void handleKeyEvent()
/*  89:    */  {
/*  90: 80 */    super.handleKeyEvent();
/*  91:    */    
/*  92: 82 */    if ((Keyboard.getEventKeyState()) && 
/*  93: 83 */      (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11)) {
/*  94: 84 */      int i = Keyboard.getEventKey() - 2;bm localbm = this; if (this.a != null) { if ((!e) && (localbm.a().a() == null)) throw new AssertionError(); if (!localbm.a().a().a(localbm.a().a())) localbm.a().a().a().add(new cz(localbm.a().a(), localbm.a().a().getUniqueIdentifier())); try { cz localcz = localbm.a().a().a(localbm.a().a());int j = -1;q localq = localbm.a.a(new q()); if (localcz.a(localq)) { j = localcz.a(localq);localbm.setChanged(); } System.err.println("PRESSED " + i + ": REMOVE: " + j); if (j != i) { System.err.println("ASSINGING: " + i + " to " + localbm.a);localcz.a(i, localq, true);localbm.setChanged(); } localbm.notifyObservers(localbm.a);return; } catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) { localShipConfigurationNotFoundException;
/*  95:    */        }
/*  96:    */      }
/*  97:    */    }
/*  98:    */  }
/*  99:    */  
/* 100: 90 */  public final void a(xp paramxp) { super.a(paramxp); }
/* 101:    */  
/* 105:    */  public final boolean h()
/* 106:    */  {
/* 107: 97 */    return this.d;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public final boolean a() {
/* 111:101 */    return !a().b().isEmpty();
/* 112:    */  }
/* 113:    */  
/* 146:    */  public final void b(boolean paramBoolean)
/* 147:    */  {
/* 148:138 */    wV.jdField_a_of_type_Boolean = !paramBoolean;
/* 149:    */    
/* 150:140 */    if (paramBoolean)
/* 151:    */    {
/* 152:142 */      xe.b("0022_menu_ui - swoosh scroll large");
/* 153:    */    } else {
/* 154:144 */      xe.b("0022_menu_ui - swoosh scroll small");
/* 155:    */    }
/* 156:    */    
/* 157:147 */    a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/* 158:    */    
/* 160:150 */    a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/* 161:    */    
/* 164:154 */    setChanged();
/* 165:155 */    notifyObservers();
/* 166:    */    
/* 167:157 */    super.b(paramBoolean);
/* 168:    */  }
/* 169:    */  
/* 183:    */  public final void c()
/* 184:    */  {
/* 185:175 */    this.d = false;
/* 186:    */  }
/* 187:    */  
/* 197:    */  public final void a(xq paramxq)
/* 198:    */  {
/* 199:189 */    wV.jdField_a_of_type_Boolean = false;
/* 200:    */    
/* 201:191 */    a().a().a.a.a.e(true);
/* 202:    */  }
/* 203:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */