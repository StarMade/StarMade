/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashSet;
/*   5:    */import java.util.Set;
/*   6:    */import org.schema.game.common.controller.SegmentController;
/*   7:    */import org.schema.schine.network.StateInterface;
/*   8:    */import org.schema.schine.network.server.ServerStateInterface;
/*   9:    */
/*  13:    */public final class js
/*  14:    */  extends Thread
/*  15:    */{
/*  16:    */  private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  17:    */  private final boolean jdField_a_of_type_Boolean;
/*  18: 18 */  private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  19:    */  
/*  20: 20 */  private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  21: 21 */  private final ArrayList c = new ArrayList();
/*  22: 22 */  private final ArrayList d = new ArrayList();
/*  23: 23 */  private final Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*  24: 24 */  private boolean jdField_b_of_type_Boolean = true;
/*  25:    */  
/*  26:    */  public js(StateInterface paramStateInterface)
/*  27:    */  {
/*  28: 28 */    this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  29: 29 */    this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*  30: 30 */    setName((this.jdField_a_of_type_Boolean ? "[SERVER]" : "[CLIENT]") + "_CREATOR_THREAD");
/*  31:    */  }
/*  32:    */  
/*  33:    */  public final void a(SegmentController paramSegmentController) {
/*  34: 34 */    if (!this.jdField_a_of_type_Boolean)
/*  35: 35 */      synchronized (this.c) {
/*  36: 36 */        this.c.add(paramSegmentController);
/*  37: 37 */        return;
/*  38:    */      }
/*  39:    */  }
/*  40:    */  
/*  41:    */  public final void b(SegmentController paramSegmentController) {
/*  42: 42 */    if (!this.jdField_a_of_type_Boolean)
/*  43: 43 */      synchronized (this.d) {
/*  44: 44 */        this.d.add(paramSegmentController);
/*  45: 45 */        return;
/*  46:    */      }
/*  47:    */  }
/*  48:    */  
/*  49: 49 */  private void a() { System.currentTimeMillis();
/*  50:    */    
/*  51: 51 */    Object localObject4 = this; if (this.jdField_a_of_type_JavaUtilSet.isEmpty()) synchronized (((js)localObject4).jdField_a_of_type_JavaUtilSet) { while (((js)localObject4).jdField_a_of_type_JavaUtilSet.isEmpty()) ((js)localObject4).jdField_a_of_type_JavaUtilSet.wait(20000L); } if (((js)localObject4).jdField_b_of_type_Boolean) { ((js)localObject4).jdField_a_of_type_JavaUtilArrayList.clear(); synchronized (((js)localObject4).jdField_a_of_type_JavaUtilSet) { ((js)localObject4).jdField_a_of_type_JavaUtilArrayList.addAll(((js)localObject4).jdField_a_of_type_JavaUtilSet);((js)localObject4).jdField_b_of_type_Boolean = false;
/*  52:    */      }
/*  53:    */    }
/*  54: 54 */    ??? = this.jdField_a_of_type_JavaUtilArrayList.size();
/*  55:    */    try
/*  56:    */    {
/*  57: 57 */      for (Object localObject3 = 0; localObject3 < ???; localObject3++) {
/*  58: 58 */        localObject4 = (SegmentController)this.jdField_a_of_type_JavaUtilArrayList.get(localObject3);
/*  59:    */        
/*  60: 60 */        int i = 0;
/*  61: 61 */        if (((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().containsKey(((SegmentController)localObject4).getId())) {
/*  62: 62 */          if (((SegmentController)localObject4).getCreatorThread() != null)
/*  63:    */          {
/*  64: 64 */            i = (localObject4 = ((SegmentController)localObject4).getCreatorThread()).jdField_a_of_type_Boolean ? ((kG)localObject4).jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().c() : 0;
/*  65:    */          }
/*  66: 66 */          if (i != 0)
/*  67: 67 */            Thread.sleep(2L);
/*  68:    */        }
/*  69:    */      }
/*  70:    */      return;
/*  71:    */    } catch (Exception localException) {
/*  72: 72 */      System.err.println("[CreatorThreadController] Exception handled " + localException);
/*  73:    */    }
/*  74:    */  }
/*  75:    */  
/* 102:    */  public final void a(boolean paramBoolean, SegmentController paramSegmentController)
/* 103:    */  {
/* 104:104 */    if (paramBoolean) {
/* 105:105 */      if (!this.jdField_a_of_type_JavaUtilSet.contains(paramSegmentController)) {
/* 106:106 */        long l1 = System.currentTimeMillis();
/* 107:107 */        synchronized (this.jdField_a_of_type_JavaUtilSet) {
/* 108:108 */          this.jdField_a_of_type_JavaUtilSet.add(paramSegmentController);
/* 109:109 */          ct.g = this.jdField_a_of_type_JavaUtilSet.size();
/* 110:110 */          this.jdField_b_of_type_Boolean = true;
/* 111:111 */          this.jdField_a_of_type_JavaUtilSet.notify();
/* 112:    */        }
/* 113:    */        long l2;
/* 114:114 */        if ((l2 = System.currentTimeMillis() - l1) > 5L) {
/* 115:115 */          System.err.println("[CREATORTHREAD][CLIENT] WARNING: notify for " + paramSegmentController + " on queue " + this.jdField_a_of_type_JavaUtilSet.size() + " took " + l2 + " ms");
/* 116:    */        }
/* 117:    */      }
/* 118:    */    }
/* 119:119 */    else if (this.jdField_a_of_type_JavaUtilSet.contains(paramSegmentController)) {
/* 120:120 */      synchronized (this.jdField_a_of_type_JavaUtilSet)
/* 121:    */      {
/* 122:122 */        this.jdField_a_of_type_JavaUtilSet.remove(paramSegmentController);
/* 123:123 */        ct.g = this.jdField_a_of_type_JavaUtilSet.size();
/* 124:124 */        this.jdField_b_of_type_Boolean = true;
/* 125:    */        
/* 126:126 */        return;
/* 127:    */      }
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 133:    */  public final void run()
/* 134:    */  {
/* 135:    */    try
/* 136:    */    {
/* 137:137 */      if (!this.jdField_a_of_type_Boolean)
/* 138:    */      {
/* 139:139 */        while (((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a() == null) {
/* 140:140 */          Thread.sleep(70L);
/* 141:    */        }
/* 142:    */        
/* 143:143 */        localObject = this;localObject = new jt((js)localObject);(localObject = new Thread((Runnable)localObject, "[CLIENT]RequestNewSegments")).setPriority(3);((Thread)localObject).start();
/* 144:    */        
/* 145:    */        for (;;)
/* 146:    */        {
/* 147:147 */          a();
/* 148:    */        }
/* 149:    */      }
/* 150:    */      return; } catch (Exception localException) { Object localObject;
/* 151:151 */      (localObject = 
/* 152:    */      
/* 155:155 */        localException).printStackTrace();System.err.println("Creator Thread DIED!!!");d.a((Exception)localObject);System.exit(0);
/* 156:    */    }
/* 157:    */  }
/* 158:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     js
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */