/*   1:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.Set;
/*   6:    */import org.schema.game.common.controller.SegmentController;
/*   7:    */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*   8:    */import org.schema.schine.network.server.ServerMessage;
/*   9:    */
/*  37:    */public class wf
/*  38:    */  extends vW
/*  39:    */{
/*  40:    */  public q b;
/*  41:    */  public boolean a;
/*  42:    */  
/*  43:    */  public wf(vg paramvg, q paramq)
/*  44:    */  {
/*  45: 45 */    super(paramvg);
/*  46: 46 */    this.b = paramq;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public wf(vg paramvg) {
/*  50: 50 */    super(paramvg);
/*  51:    */  }
/*  52:    */  
/*  56:    */  public final void c()
/*  57:    */  {
/*  58: 58 */    super.c();
/*  59:    */    
/*  60: 60 */    if (this.a)
/*  61:    */    {
/*  62: 62 */      wf localwf = this; for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.iterator(); localIterator.hasNext(); localwf.a = false) { Object localObject = (String)localIterator.next(); if ((localwf.a((String)localObject)) && ((localObject = (SegmentController)localwf.jdField_a_of_type_Vg.c().get(localObject)) != null) && ((localObject instanceof kh)) && (!(localObject = (kh)localObject).a().isEmpty())) { localObject = (kf)((kh)localObject).a().iterator().next();System.err.println("[SIMULATION] " + localwf + " filling stock of: " + localObject); try { ((kf)localObject).a(true); } catch (NoSlotFreeException localNoSlotFreeException) { localNoSlotFreeException;
/*  63:    */          }
/*  64:    */        }
/*  65:    */      }
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  93:    */  public void a(lE paramlE)
/*  94:    */  {
/*  95: 95 */    paramlE.a(new ServerMessage("#### Transmission Start\nHostile identified...\nExterminate...\n#### Transmission End\n", 2, paramlE.getId()));
/*  96:    */  }
/*  97:    */  
/* 116:    */  protected Ah a()
/* 117:    */  {
/* 118:118 */    return new Ah(Aj.k, null, this.b);
/* 119:    */  }
/* 120:    */  
/* 124:    */  protected void a(Ah paramAh)
/* 125:    */  {
/* 126:126 */    this.b = ((q)paramAh.a());
/* 127:127 */    if (this.a != null) {
/* 128:128 */      ((sL)this.a).a(this.b);
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 134:    */  public wb a()
/* 135:    */  {
/* 136:136 */    return wb.a;
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */