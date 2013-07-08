/*   1:    */import java.io.PrintStream;
/*   2:    */import org.lwjgl.input.Keyboard;
/*   3:    */import org.lwjgl.input.Mouse;
/*   4:    */
/*  47:    */final class ie
/*  48:    */  extends H
/*  49:    */{
/*  50:    */  private boolean b;
/*  51:    */  
/*  52:    */  ie(id paramid, ct paramct)
/*  53:    */  {
/*  54: 54 */    super(paramct);
/*  55:    */  }
/*  56:    */  
/*  59:    */  public final void a(yz paramyz, xp paramxp)
/*  60:    */  {
/*  61: 61 */    if ((Mouse.isButtonDown(0)) && (!this.b))
/*  62:    */    {
/*  63: 63 */      if ((paramyz.b().equals("CANCEL")) || (paramyz.b().equals("X"))) {
/*  64: 64 */        System.err.println("CANCEL");
/*  65: 65 */        d();
/*  66:    */      }
/*  67:    */    }
/*  68:    */    
/*  70: 70 */    this.b = Mouse.isButtonDown(0);
/*  71:    */  }
/*  72:    */  
/*  74:    */  public final yz a()
/*  75:    */  {
/*  76: 76 */    return id.a(this.a);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public final void handleKeyEvent()
/*  80:    */  {
/*  81: 81 */    if (Keyboard.getEventKeyState()) {
/*  82: 82 */      if (Keyboard.getEventKey() == 1) {
/*  83: 83 */        d();return;
/*  84:    */      }
/*  85: 85 */      if (!xu.C.b()) {
/*  86: 86 */        Keyboard.getEventKey();this.a.c();
/*  87:    */      }
/*  88:    */      
/*  89: 89 */      id.a(this.a).a(Keyboard.getEventKey());
/*  90: 90 */      cv.b();
/*  91: 91 */      d();
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/* 103:    */  protected final void e()
/* 104:    */  {
/* 105:105 */    id.a(this.a, new eX(this.a, this, "Assign New Key to " + id.a(this.a).a(), "Press a Key to assign it to \n\n<" + id.a(this.a).a() + "> \n\nor press ESC to cancel."));
/* 106:    */    
/* 107:107 */    id.a(this.a).e();
/* 108:108 */    id.a(this.a).a(this);
/* 109:    */  }
/* 110:    */  
/* 112:    */  public final boolean a()
/* 113:    */  {
/* 114:114 */    return false;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public final void a()
/* 118:    */  {
/* 119:119 */    id.d();
/* 120:120 */    id.a(System.currentTimeMillis());
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ie
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */