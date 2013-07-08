/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.ArrayList;
/*   3:    */import java.util.List;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   6:    */import org.schema.schine.network.client.ClientState;
/*   7:    */
/*  14:    */public final class id
/*  15:    */  extends yz
/*  16:    */  implements ys
/*  17:    */{
/*  18:    */  private yP jdField_a_of_type_YP;
/*  19:    */  private boolean jdField_a_of_type_Boolean;
/*  20:    */  private cv jdField_a_of_type_Cv;
/*  21:    */  private eX jdField_a_of_type_EX;
/*  22:    */  
/*  23:    */  public static boolean b()
/*  24:    */  {
/*  25: 25 */    return (b) || (System.currentTimeMillis() - jdField_a_of_type_Long < 200L);
/*  26:    */  }
/*  27:    */  
/*  31: 31 */  private static long jdField_a_of_type_Long = 0L;
/*  32:    */  
/*  34: 34 */  private static boolean b = false;
/*  35:    */  
/*  36:    */  public id(ClientState paramClientState, cv paramcv) {
/*  37: 37 */    super(paramClientState);
/*  38: 38 */    this.jdField_a_of_type_Cv = paramcv;
/*  39: 39 */    this.jdField_a_of_type_YP = new yP(140, 30, d.h(), a());
/*  40:    */  }
/*  41:    */  
/*  44:    */  public final void a(yz paramyz, xp paramxp)
/*  45:    */  {
/*  46: 46 */    paramyz = null; if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/*  47:    */    {
/*  48: 48 */      paramyz = (ct)a();
/*  49:    */      
/*  50: 50 */      if (!b()) {
/*  51: 51 */        System.err.println("PRESSED MOUSE TO ACTIVATE");
/*  52: 52 */        b = true;
/*  53:    */        
/*  54: 54 */        paramyz.b().add(new ie(this, paramyz));
/*  55:    */      }
/*  56:    */    }
/*  57:    */  }
/*  58:    */  
/*  93:    */  public final boolean c()
/*  94:    */  {
/*  95:    */    cv[] arrayOfcv;
/*  96:    */    
/* 129:129 */    int i = (arrayOfcv = cv.values()).length; for (int j = 0; j < i; j++) { cv localcv;
/* 130:130 */      if (((localcv = arrayOfcv[j]) != this.jdField_a_of_type_Cv) && (localcv.a() == Keyboard.getEventKey()))
/* 131:    */      {
/* 132:132 */        System.err.println("DUIPLICATE KEY");
/* 133:    */        
/* 134:134 */        cu localcu2 = localcv.a(); cu localcu1; if (((a(localcu1 = this.jdField_a_of_type_Cv.a(), localcu2)) || (a(localcu2, localcu1)) ? 1 : 0) != 0) {
/* 135:135 */          System.err.println("KEYS RELATED: -> DUPLICATE");
/* 136:136 */          ((ct)a()).a().b("WARNING: duplicate detected:\nKeys for \"" + localcv.a() + "\"(" + localcv.b() + ") and\n\"" + this.jdField_a_of_type_Cv.a() + "\"(" + this.jdField_a_of_type_Cv.b() + ") have been\nswitched");
/* 137:    */          
/* 141:141 */          localcv.a(this.jdField_a_of_type_Cv.a());
/* 142:    */        }
/* 143:    */      }
/* 144:    */    }
/* 145:    */    
/* 146:146 */    return true;
/* 147:    */  }
/* 148:    */  
/* 153:    */  public final void a() {}
/* 154:    */  
/* 159:    */  public final void b()
/* 160:    */  {
/* 161:161 */    if (!this.jdField_a_of_type_Boolean) {
/* 162:162 */      c();
/* 163:    */    }
/* 164:164 */    GlUtil.d();
/* 165:165 */    r();
/* 166:166 */    this.jdField_a_of_type_YP.b.set(0, Keyboard.getKeyName(this.jdField_a_of_type_Cv.a()));
/* 167:167 */    this.jdField_a_of_type_YP.b();
/* 168:168 */    l();
/* 169:169 */    GlUtil.c();
/* 170:    */  }
/* 171:    */  
/* 173:    */  public final float a()
/* 174:    */  {
/* 175:175 */    return 30.0F;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public final float b()
/* 179:    */  {
/* 180:180 */    return 140.0F;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public final boolean a()
/* 184:    */  {
/* 185:185 */    return false;
/* 186:    */  }
/* 187:    */  
/* 189:    */  private static boolean a(cu paramcu1, cu paramcu2)
/* 190:    */  {
/* 191:    */    for (;;)
/* 192:    */    {
/* 193:193 */      System.err.println("CHEKCING " + paramcu1 + " AND " + paramcu2);
/* 194:194 */      if (paramcu1 == paramcu2) {
/* 195:195 */        return true;
/* 196:    */      }
/* 197:197 */      if (paramcu1.a()) break;
/* 198:198 */      paramcu1 = paramcu1.a();
/* 199:    */    }
/* 200:200 */    return false;
/* 201:    */  }
/* 202:    */  
/* 206:    */  public final void c()
/* 207:    */  {
/* 208:208 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 209:209 */    this.jdField_a_of_type_YP.b.add(Keyboard.getKeyName(this.jdField_a_of_type_Cv.a()));
/* 210:210 */    this.jdField_a_of_type_YP.c();
/* 211:    */    
/* 212:212 */    this.g = true;
/* 213:213 */    this.jdField_a_of_type_Boolean = true;
/* 214:    */  }
/* 215:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     id
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */