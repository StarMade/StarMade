/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.ArrayList;
/*   3:    */import java.util.HashSet;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.schema.schine.network.ChatSystem;
/*   6:    */
/*  14:    */public final class am
/*  15:    */  extends U
/*  16:    */{
/*  17:    */  private ak a;
/*  18:    */  public ae a;
/*  19:    */  public ac a;
/*  20:    */  public aq a;
/*  21:    */  
/*  22:    */  public am(ct paramct)
/*  23:    */  {
/*  24: 24 */    super(paramct);
/*  25: 25 */    paramct = this;this.jdField_a_of_type_Ak = new ak(paramct.a());paramct.jdField_a_of_type_Ae = new ae(paramct.a());paramct.jdField_a_of_type_Ac = new ac(paramct.a());paramct.jdField_a_of_type_Aq = new aq(paramct.a());paramct.a.add(paramct.jdField_a_of_type_Ak);paramct.a.add(paramct.jdField_a_of_type_Ae);paramct.a.add(paramct.jdField_a_of_type_Ac);paramct.a.add(paramct.jdField_a_of_type_Aq);paramct.jdField_a_of_type_Ac.c(true);
/*  26:    */  }
/*  27:    */  
/*  34:    */  public final ac a()
/*  35:    */  {
/*  36: 36 */    return this.jdField_a_of_type_Ac;
/*  37:    */  }
/*  38:    */  
/*  42:    */  public final ak a()
/*  43:    */  {
/*  44: 44 */    return this.jdField_a_of_type_Ak;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public final ae a()
/*  48:    */  {
/*  49: 49 */    return this.jdField_a_of_type_Ae;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public final aq a()
/*  53:    */  {
/*  54: 54 */    return this.jdField_a_of_type_Aq;
/*  55:    */  }
/*  56:    */  
/*  61:    */  public final void handleKeyEvent()
/*  62:    */  {
/*  63: 63 */    synchronized (a().b())
/*  64:    */    {
/*  65: 65 */      int i = 0; if (b()) {
/*  66: 66 */        return;
/*  67:    */      }
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    boolean bool1 = this.jdField_a_of_type_Ak.b;
/*  71:    */    
/*  72: 72 */    if (Keyboard.getEventKeyState()) {
/*  73: 73 */      if (Keyboard.getEventKey() == cv.F.a()) {
/*  74: 74 */        if (!bool1) {
/*  75: 75 */          if ((Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54))) {
/*  76: 76 */            System.err.println("USING FACTION CHAT");
/*  77: 77 */            a().getChat().getTextInput().a("/f ");
/*  78:    */          }
/*  79: 79 */          this.jdField_a_of_type_Ak.d(true);
/*  81:    */        }
/*  82:    */        else
/*  83:    */        {
/*  84: 84 */          this.jdField_a_of_type_Ak.d(false);
/*  85:    */        }
/*  86:    */      }
/*  87: 87 */      else if ((Keyboard.getEventKey() == 87) && 
/*  88: 88 */        (!bool1))
/*  89:    */      {
/*  90:    */        boolean bool2;
/*  91:    */        
/*  92: 92 */        if ((!(bool2 = this.jdField_a_of_type_Ae.b)) && (!a().b().isEmpty()))
/*  93:    */        {
/*  95: 95 */          return;
/*  96:    */        }
/*  97: 97 */        if ((bool2) && (a().a() == null)) {
/*  98: 98 */          System.out.println("no player character: spawning");
/*  99: 99 */          a().a().e();
/* 100:    */        }
/* 101:    */        else {
/* 102:102 */          this.jdField_a_of_type_Aq.c(bool2);
/* 103:103 */          this.jdField_a_of_type_Ae.c(!bool2);
/* 104:    */        }
/* 105:    */        
/* 106:106 */        if ((this.jdField_a_of_type_Ac.b) && (!bool2)) {
/* 107:107 */          this.jdField_a_of_type_Ac.c(false);
/* 108:108 */          this.jdField_a_of_type_Ae.c(true);
/* 109:    */        }
/* 110:    */      }
/* 111:    */    }
/* 112:    */    
/* 120:120 */    super.handleKeyEvent();
/* 121:    */  }
/* 122:    */  
/* 124:    */  public final void a(xp paramxp)
/* 125:    */  {
/* 126:126 */    super.a(paramxp);
/* 127:    */  }
/* 128:    */  
/* 171:    */  public final void a(xq paramxq)
/* 172:    */  {
/* 173:173 */    super.a(paramxq);
/* 174:174 */    if (!a().b().isEmpty()) {
/* 175:175 */      wV.a = false;
/* 176:    */    }
/* 177:    */  }
/* 178:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     am
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */