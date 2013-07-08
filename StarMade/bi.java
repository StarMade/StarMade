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
/*  11:    */import org.schema.game.common.controller.SegmentController;
/*  12:    */import org.schema.game.common.data.element.ControlElementMap;
/*  13:    */import org.schema.game.common.data.element.ControlElementMapper;
/*  14:    */import org.schema.game.common.data.element.ElementCollection;
/*  15:    */import org.schema.game.common.data.world.Segment;
/*  16:    */import org.schema.schine.network.NetworkStateContainer;
/*  17:    */
/*  22:    */public class bi
/*  23:    */  extends U
/*  24:    */{
/*  25: 25 */  private Object jdField_a_of_type_JavaLangObject = new Object();
/*  26:    */  
/*  27:    */  private bj jdField_a_of_type_Bj;
/*  28:    */  
/*  29:    */  private le jdField_a_of_type_Le;
/*  30:    */  
/*  31:    */  public bi(ct paramct)
/*  32:    */  {
/*  33: 33 */    super(paramct);
/*  34: 34 */    paramct = this;this.jdField_a_of_type_Bj = new bj(paramct.a());paramct.a.add(paramct.jdField_a_of_type_Bj);
/*  35:    */  }
/*  36:    */  
/*  40:    */  public final void b()
/*  41:    */  {
/*  42: 42 */    synchronized (this.jdField_a_of_type_JavaLangObject) {
/*  43: 43 */      q localq = null; if (this.jdField_a_of_type_Le != null)
/*  44:    */      {
/*  45: 45 */        SegmentController localSegmentController = this.jdField_a_of_type_Le.a().a();
/*  46: 46 */        localq = this.jdField_a_of_type_Le.a(new q());
/*  47: 47 */        System.err.println("[CLIENT] EXIT SHIP FROM EXTRYPOINT " + localq + " OF " + localSegmentController);
/*  48: 48 */        a().a().a((cw)localSegmentController, a().a(), localq, new q(), true);
/*  49:    */      }
/*  50:    */      
/*  57: 57 */      return;
/*  58:    */    }
/*  59:    */  }
/*  60:    */  
/*  67:    */  public final le a()
/*  68:    */  {
/*  69: 69 */    return this.jdField_a_of_type_Le;
/*  70:    */  }
/*  71:    */  
/*  75:    */  public final bj a()
/*  76:    */  {
/*  77: 77 */    return this.jdField_a_of_type_Bj;
/*  78:    */  }
/*  79:    */  
/*  83:    */  public void handleKeyEvent()
/*  84:    */  {
/*  85: 85 */    super.handleKeyEvent();
/*  86:    */    
/*  87: 87 */    if ((Keyboard.getEventKeyState()) && 
/*  88: 88 */      (Keyboard.getEventKey() == cv.v.a())) {
/*  89: 89 */      b();
/*  90:    */    }
/*  91:    */  }
/*  92:    */  
/* 115:    */  public final void b(boolean paramBoolean)
/* 116:    */  {
/* 117:117 */    if (paramBoolean)
/* 118:    */    {
/* 120:120 */      Object localObject = (kd)this.jdField_a_of_type_Le.a().a();
/* 121:    */      
/* 122:122 */      a().a((kd)localObject);
/* 123:    */      
/* 126:126 */      if ((!d) && (this.jdField_a_of_type_Le == null)) { throw new AssertionError();
/* 127:    */      }
/* 128:128 */      jD localjD = ((kd)localObject).getControlElementMap().getControlledElements((short)32767, this.jdField_a_of_type_Le.a(new q()));
/* 129:    */      
/* 130:130 */      if (!a().a().a((SegmentController)localObject)) {
/* 131:131 */        cz localcz = new cz(a().a(), ((kd)localObject).getUniqueIdentifier());
/* 132:132 */        int i = 0;
/* 133:    */        try {
/* 134:134 */          localq = new q();
/* 135:135 */          for (localObject = ((kd)localObject).getControlElementMap().getControllingMap().keySet().iterator(); ((Iterator)localObject).hasNext();) { long l = ((Long)((Iterator)localObject).next()).longValue();
/* 136:136 */            if (localjD.a.contains(ElementCollection.getPosFromIndex(l, localq))) {
/* 137:137 */              localcz.a(i, new q(localq), true);
/* 138:    */            }
/* 139:139 */            if (i > 10) break;
/* 140:140 */            i++;
/* 141:    */          }
/* 142:    */        }
/* 143:    */        catch (ConcurrentModificationException localConcurrentModificationException) {
/* 144:144 */          q localq = null;
/* 145:    */          
/* 146:146 */          localConcurrentModificationException.printStackTrace();
/* 147:    */        }
/* 148:    */        
/* 149:147 */        a().a().a().add(localcz);
/* 150:    */      }
/* 151:    */      
/* 153:151 */      this.jdField_a_of_type_Bj.c(true);
/* 154:    */      
/* 155:153 */      this.jdField_a_of_type_Le.a().a();
/* 156:154 */      this.jdField_a_of_type_Le.a(new q());
/* 158:    */    }
/* 159:    */    else
/* 160:    */    {
/* 161:159 */      if (a().a() == a().a()) {
/* 162:160 */        a().a(null);
/* 163:    */      }
/* 164:    */      
/* 168:166 */      a().a(null);
/* 169:    */    }
/* 170:    */    
/* 171:169 */    if ((!d) && (paramBoolean) && (a().a() == null)) throw new AssertionError(": Entered: " + this.jdField_a_of_type_Le.a().a() + " -> " + this.jdField_a_of_type_Le.a(new q()));
/* 172:170 */    super.b(paramBoolean);
/* 173:    */  }
/* 174:    */  
/* 177:    */  public final void a(le paramle)
/* 178:    */  {
/* 179:177 */    synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 180:178 */      this.jdField_a_of_type_Le = paramle;
/* 181:179 */      return;
/* 182:    */    }
/* 183:    */  }
/* 184:    */  
/* 185:    */  public final void a(xq arg1)
/* 186:    */  {
/* 187:185 */    super.a(???);
/* 188:186 */    if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId())) {
/* 189:187 */      b();
/* 190:    */    }
/* 191:189 */    synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 192:190 */      if (this.jdField_a_of_type_Le != null) {
/* 193:191 */        this.jdField_a_of_type_Le.a();
/* 194:192 */        if ((!this.jdField_a_of_type_Le.a().c) && (this.jdField_a_of_type_Le.a().a().getSegmentBuffer().a(this.jdField_a_of_type_Le.a().a)) && 
/* 195:193 */          (((mw)this.jdField_a_of_type_Le.a()).a() > 0L) && (this.jdField_a_of_type_Le.a() == 0)) {
/* 196:194 */          Object localObject1 = this.jdField_a_of_type_Le.a(new q());
/* 197:    */          try
/* 198:    */          {
/* 199:197 */            if ((localObject1 = this.jdField_a_of_type_Le.a().a().getSegmentBuffer().a((q)localObject1, true)).a() == 0) {
/* 200:198 */              System.err.println("POINT BECAME AIR -> exiting ship LC: " + ((mw)this.jdField_a_of_type_Le.a()).a());
/* 201:199 */              b();
/* 202:    */            } else {
/* 203:201 */              a((le)localObject1);
/* 204:    */            }
/* 205:    */          }
/* 206:    */          catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
/* 207:    */        }
/* 208:    */        
/* 209:207 */        if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId())) {
/* 210:208 */          b();
/* 211:    */        }
/* 212:    */      }
/* 213:    */      return;
/* 214:    */    }
/* 215:    */  }
/* 216:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */