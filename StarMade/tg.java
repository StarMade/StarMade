/*   1:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.sql.SQLException;
/*   4:    */import org.schema.game.common.data.world.Universe;
/*   5:    */import org.schema.game.server.controller.EntityNotFountException;
/*   6:    */
/*  18:    */public final class tg
/*  19:    */  extends ti
/*  20:    */{
/*  21:    */  private static final long serialVersionUID = 1L;
/*  22:    */  private long a;
/*  23:    */  
/*  24:    */  public tg(wk paramwk)
/*  25:    */  {
/*  26: 26 */    super(paramwk);
/*  27:    */  }
/*  28:    */  
/*  30:    */  public final boolean c()
/*  31:    */  {
/*  32: 32 */    return false;
/*  33:    */  }
/*  34:    */  
/*  36:    */  public final boolean b()
/*  37:    */  {
/*  38: 38 */    return false;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public final boolean d()
/*  42:    */  {
/*  43:    */    sL localsL;
/*  44: 44 */    if ((localsL = (sL)a().a()).a() == null) {
/*  45: 45 */      a(new tx());
/*  46: 46 */      return true;
/*  47:    */    }
/*  48:    */    
/*  51: 51 */    q localq1 = new q();
/*  52: 52 */    for (int i = 0; i < ((vY)a()).a().size(); i++) {
/*  53:    */      try {
/*  54: 54 */        ((vY)a()).a((String)((vY)a()).a().get(i), localq1);
/*  55:    */        
/*  56: 56 */        if (localq1.equals(localsL.a())) {
/*  57: 57 */          a(new tF());
/*  58: 58 */          return true;
/*  59:    */        }
/*  60:    */      } catch (EntityNotFountException localEntityNotFountException1) {
/*  61: 61 */        
/*  62:    */        
/*  65: 65 */          localEntityNotFountException1;
/*  66:    */      } catch (SQLException localSQLException1) {
/*  67: 63 */        
/*  68:    */        
/*  69: 65 */          localSQLException1;
/*  70:    */      }
/*  71:    */    }
/*  72:    */    
/*  74: 68 */    if (System.currentTimeMillis() - this.a > 1000L) {
/*  75: 69 */      boolean bool2 = ((vY)a()).a().a().a(localsL.a());
/*  76: 70 */      for (int j = 0; j < ((vY)a()).a().size(); j++) {
/*  77:    */        boolean bool1;
/*  78: 72 */        if (((vY)a()).a().a().isSectorLoaded(localsL.a()))
/*  79:    */        {
/*  80: 74 */          bool1 = ((vY)a()).a((String)((vY)a()).a().get(j), localsL.a());
/*  81:    */        }
/*  82: 76 */        else if (bool2)
/*  83:    */        {
/*  84: 78 */          bool1 = true;
/*  85: 79 */          new q();
/*  86: 80 */          q localq2 = org.schema.game.common.data.element.Element.DIRECTIONSi[Universe.getRandom().nextInt(org.schema.game.common.data.element.Element.DIRECTIONSi.length)];
/*  87:    */          try {
/*  88:    */            q localq3;
/*  89: 83 */            (localq3 = ((vY)a()).a((String)((vY)a()).a().get(j), new q())).a(localq2);
/*  90: 84 */            System.err.println("[MOVING TO SECTOR] Position occupied for " + (String)((vY)a()).a().get(j));
/*  91: 85 */            ((vY)a()).a((String)((vY)a()).a().get(j), localq3);
/*  92: 86 */          } catch (EntityNotFountException localEntityNotFountException2) { 
/*  93:    */            
/*  98: 92 */              localEntityNotFountException2;
/*  99:    */          } catch (SQLException localSQLException2) {
/* 100: 88 */            
/* 101:    */            
/* 104: 92 */              localSQLException2;
/* 105:    */          } catch (Exception localException) {
/* 106: 90 */            
/* 107:    */            
/* 108: 92 */              localException;
/* 109:    */          }
/* 110:    */        }
/* 111:    */        else {
/* 112: 94 */          bool1 = ((vY)a()).a((String)((vY)a()).a().get(j), localsL.a());
/* 113:    */        }
/* 114:    */        
/* 115: 97 */        if (!bool1) {
/* 116: 98 */          System.err.println("[SIMULATION] Exception while moving entity: REMOVING FROM MEMBERS: " + (String)((vY)a()).a().get(j));
/* 117: 99 */          ((vY)a()).a().remove(j);
/* 118:100 */          j--;
/* 119:    */        }
/* 120:102 */        this.a = System.currentTimeMillis();
/* 121:    */      }
/* 122:    */    }
/* 123:    */    
/* 124:106 */    return false;
/* 125:    */  }
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */