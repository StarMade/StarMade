/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import it.unimi.dsi.fastutil.longs.LongSet;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.ConcurrentModificationException;
/*   7:    */import java.util.HashSet;
/*   8:    */import java.util.Iterator;
/*   9:    */import org.lwjgl.input.Keyboard;
/*  10:    */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*  11:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  12:    */import org.schema.game.common.controller.SegmentController;
/*  13:    */import org.schema.game.common.data.element.ControlElementMap;
/*  14:    */import org.schema.game.common.data.element.ControlElementMapper;
/*  15:    */import org.schema.game.common.data.element.ElementCollection;
/*  16:    */import org.schema.game.common.data.world.Segment;
/*  17:    */import org.schema.schine.network.NetworkStateContainer;
/*  18:    */
/*  21:    */public class ax
/*  22:    */  extends U
/*  23:    */  implements al
/*  24:    */{
/*  25: 25 */  private Object jdField_a_of_type_JavaLangObject = new Object();
/*  26:    */  
/*  27:    */  private le jdField_a_of_type_Le;
/*  28:    */  
/*  29:    */  private ay jdField_a_of_type_Ay;
/*  30:    */  
/*  31:    */  private au jdField_a_of_type_Au;
/*  32:    */  
/*  34:    */  public ax(ct paramct)
/*  35:    */  {
/*  36: 36 */    super(paramct);
/*  37: 37 */    paramct = this;this.jdField_a_of_type_Ay = new ay(paramct.a());paramct.jdField_a_of_type_Au = new au(paramct.a(), paramct);paramct.a.add(paramct.jdField_a_of_type_Ay);paramct.a.add(paramct.jdField_a_of_type_Au);
/*  38:    */  }
/*  39:    */  
/*  40:    */  private void b()
/*  41:    */  {
/*  42: 42 */    synchronized (this.jdField_a_of_type_JavaLangObject) {
/*  43: 43 */      q localq = null; if (this.jdField_a_of_type_Le != null)
/*  44:    */      {
/*  45: 45 */        SegmentController localSegmentController = this.jdField_a_of_type_Le.a().a();
/*  46: 46 */        localq = this.jdField_a_of_type_Le.a(new q());
/*  47: 47 */        System.err.println("EXIT SHIP FROM EXTRYPOINT " + localq);
/*  48: 48 */        a().a().a((cw)localSegmentController, a().a(), localq, new q(), true);
/*  49:    */      }
/*  50:    */      
/*  57: 57 */      return;
/*  58:    */    }
/*  59:    */  }
/*  60:    */  
/*  65:    */  public final q a()
/*  66:    */  {
/*  67: 67 */    return this.jdField_a_of_type_Le.a(new q());
/*  68:    */  }
/*  69:    */  
/*  73:    */  public final le a()
/*  74:    */  {
/*  75: 75 */    return this.jdField_a_of_type_Le;
/*  76:    */  }
/*  77:    */  
/*  83:    */  public final au a()
/*  84:    */  {
/*  85: 85 */    return this.jdField_a_of_type_Au;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public final EditableSendableSegmentController a()
/*  89:    */  {
/*  90: 90 */    return (EditableSendableSegmentController)this.jdField_a_of_type_Le.a().a();
/*  91:    */  }
/*  92:    */  
/* 101:    */  public final ay a()
/* 102:    */  {
/* 103:103 */    return this.jdField_a_of_type_Ay;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public void handleKeyEvent()
/* 107:    */  {
/* 108:108 */    super.handleKeyEvent();
/* 109:    */    
/* 110:110 */    if ((Keyboard.getEventKeyState()) && 
/* 111:111 */      (Keyboard.getEventKey() == cv.v.a())) {
/* 112:112 */      b();
/* 113:    */    }
/* 114:    */  }
/* 115:    */  
/* 146:    */  public final void b(boolean paramBoolean)
/* 147:    */  {
/* 148:148 */    if (paramBoolean)
/* 149:    */    {
/* 150:150 */      if (this.jdField_a_of_type_Le.a() == null) {
/* 151:151 */        System.err.println("Exception: entered has been null");
/* 152:152 */        if ((!d) && (this.jdField_a_of_type_Le == null)) { throw new AssertionError();
/* 153:    */        }
/* 154:    */        
/* 156:    */        return;
/* 157:    */      }
/* 158:    */      
/* 160:    */      Object localObject;
/* 161:    */      
/* 163:163 */      jD localjD = (localObject = this.jdField_a_of_type_Le.a().a()).getControlElementMap().getControlledElements((short)32767, this.jdField_a_of_type_Le.a(new q()));
/* 164:    */      
/* 165:165 */      if (!a().a().a((SegmentController)localObject)) {
/* 166:166 */        cz localcz = new cz(a().a(), ((SegmentController)localObject).getUniqueIdentifier());
/* 167:167 */        int i = 0;
/* 168:    */        try {
/* 169:169 */          localq = new q();
/* 170:170 */          for (localObject = ((SegmentController)localObject).getControlElementMap().getControllingMap().keySet().iterator(); ((Iterator)localObject).hasNext();) { long l = ((Long)((Iterator)localObject).next()).longValue();
/* 171:171 */            if (localjD.a.contains(ElementCollection.getPosFromIndex(l, localq))) {
/* 172:172 */              localcz.a(i, new q(localq), true);
/* 173:    */            }
/* 174:174 */            if (i > 10) break;
/* 175:175 */            i++;
/* 176:    */          }
/* 177:    */        }
/* 178:    */        catch (ConcurrentModificationException localConcurrentModificationException) {
/* 179:179 */          q localq = null;
/* 180:    */          
/* 181:181 */          localConcurrentModificationException.printStackTrace();
/* 182:    */        }
/* 183:    */        
/* 184:182 */        a().a().a().add(localcz);
/* 185:    */      }
/* 186:    */      
/* 187:185 */      if (this.jdField_a_of_type_Le.a() == 123) {
/* 188:186 */        this.jdField_a_of_type_Au.c(true);
/* 189:    */      } else {
/* 190:188 */        this.jdField_a_of_type_Ay.c(true);
/* 191:    */      }
/* 192:    */      
/* 193:191 */      this.jdField_a_of_type_Le.a().a();
/* 194:192 */      this.jdField_a_of_type_Le.a(new q());
/* 196:    */    }
/* 197:    */    else
/* 198:    */    {
/* 199:197 */      if ((this.jdField_a_of_type_Le != null) && (this.jdField_a_of_type_Le.a().a() == a().a())) {
/* 200:198 */        a().a(null);
/* 201:    */      }
/* 202:200 */      this.jdField_a_of_type_Ay.c(false);
/* 203:201 */      this.jdField_a_of_type_Au.c(false);
/* 204:    */    }
/* 205:203 */    if ((!d) && (paramBoolean) && (this.jdField_a_of_type_Le == null)) throw new AssertionError(": Entered: " + this.jdField_a_of_type_Le.a().a() + " -> " + this.jdField_a_of_type_Le.a(new q()));
/* 206:204 */    super.b(paramBoolean);
/* 207:    */  }
/* 208:    */  
/* 213:    */  public final void a(le paramle)
/* 214:    */  {
/* 215:213 */    synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 216:214 */      this.jdField_a_of_type_Le = paramle;
/* 217:215 */      return;
/* 218:    */    }
/* 219:    */  }
/* 220:    */  
/* 225:    */  public final void a(xq arg1)
/* 226:    */  {
/* 227:225 */    super.a(???);
/* 228:226 */    if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId())) {
/* 229:227 */      b();
/* 230:    */    }
/* 231:229 */    synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 232:230 */      if (this.jdField_a_of_type_Le != null) {
/* 233:231 */        this.jdField_a_of_type_Le.a();
/* 234:232 */        if (this.jdField_a_of_type_Le.a() == 0) {
/* 235:233 */          Object localObject1 = this.jdField_a_of_type_Le.a(new q());
/* 236:    */          try
/* 237:    */          {
/* 238:236 */            if ((localObject1 = this.jdField_a_of_type_Le.a().a().getSegmentBuffer().a((q)localObject1, true)).a() == 0) {
/* 239:237 */              b();
/* 240:    */            } else {
/* 241:239 */              this.jdField_a_of_type_Le = ((le)localObject1);
/* 242:    */            }
/* 243:    */          }
/* 244:    */          catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
/* 245:    */        }
/* 246:244 */        if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId())) {
/* 247:245 */          b();
/* 248:    */        }
/* 249:    */      }
/* 250:    */      return;
/* 251:    */    }
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ax
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */