/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*   4:    */import jL;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.Collection;
/*   7:    */import org.schema.game.common.data.world.Segment;
/*   8:    */
/*   9:    */public abstract class PointDistributionCustomOutputUnit extends PointDistributionUnit
/*  10:    */{
/*  11: 11 */  private q output = new q(0, 0, 0);
/*  12:    */  
/*  16: 16 */  public q getOutput() { return this.output; }
/*  17:    */  
/*  18:    */  public void onChangeFinished() {
/*  19: 19 */    super.onChangeFinished();
/*  20: 20 */    this.output.b(getSignificator());
/*  21: 21 */    le localle1 = new le();
/*  22: 22 */    int i = 0;
/*  23:    */    try
/*  24:    */    {
/*  25: 25 */      localq = new q();
/*  26: 26 */      for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext();) {
/*  27: 27 */        getPosFromIndex(((Long)localIterator.next()).longValue(), localq);
/*  28:    */        
/*  29:    */        le localle2;
/*  30: 30 */        if (((localle2 = getController().getSegmentBuffer().a(localq, false, localle1)) != null) && (localle2.a()) && (localle2.a() == getClazzId())) {
/*  31: 31 */          this.output.b(localq);
/*  32:    */          
/*  33: 33 */          i = 1;
/*  34: 34 */          break;
/*  35:    */        }
/*  36:    */      } } catch (java.io.IOException localIOException) { java.util.Iterator localIterator;
/*  37: 37 */      localq = null;
/*  38:    */      
/*  41: 41 */      localIOException.printStackTrace();
/*  42:    */    } catch (InterruptedException localInterruptedException) {
/*  43: 39 */      q localq = null;
/*  44:    */      
/*  45: 41 */      localInterruptedException.printStackTrace();
/*  46:    */    }
/*  47:    */    
/*  48: 42 */    if (i == 0)
/*  49:    */    {
/*  50: 44 */      this.output.b(getSignificator());
/*  51:    */    }
/*  52:    */  }
/*  53:    */  
/*  55:    */  public void setMainPiece(le paramle, boolean paramBoolean)
/*  56:    */  {
/*  57: 51 */    if (paramBoolean) { le localle;
/*  58: 52 */      q localq; java.util.Iterator localIterator; if (paramle.a().a().isOnServer())
/*  59:    */      {
/*  60: 54 */        localle = new le();
/*  61:    */        
/*  62: 56 */        localq = new q();
/*  63:    */        
/*  64: 58 */        for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext();) {
/*  65: 59 */          getPosFromIndex(((Long)localIterator.next()).longValue(), localq);
/*  66: 60 */          if (!paramle.a(localq))
/*  67:    */          {
/*  68:    */            try
/*  69:    */            {
/*  70: 64 */              getController().getSegmentBuffer().a(localq, false, localle);
/*  71: 65 */              if (localle.a())
/*  72:    */              {
/*  73:    */                s locals;
/*  74: 68 */                (locals = new s()).a(localq.a, localq.b, localq.c, -2);
/*  75: 69 */                ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/*  76:    */              }
/*  77: 71 */            } catch (java.io.IOException localIOException) { 
/*  78:    */              
/*  83: 77 */                localIOException;
/*  84:    */            }
/*  85:    */            catch (InterruptedException localInterruptedException) {
/*  86: 74 */              
/*  87:    */              
/*  89: 77 */                localInterruptedException;
/*  90:    */            }
/*  91:    */          }
/*  92:    */        }
/*  93:    */      }
/*  94:    */      
/*  96: 81 */      System.err.println("NEW OUTPUT SET: " + paramle.a(new q()) + "; ACTIVE: " + paramBoolean);
/*  97: 82 */      this.output.b(paramle.a(new q()));
/*  98:    */    }
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void setMainPiece()
/* 102:    */  {
/* 103: 88 */    le localle = new le();
/* 104:    */    
/* 105: 90 */    q localq = new q();
/* 106:    */    
/* 107: 92 */    for (java.util.Iterator localIterator = getNeighboringCollection().iterator(); localIterator.hasNext();) {
/* 108: 93 */      getPosFromIndex(((Long)localIterator.next()).longValue(), localq);
/* 109:    */      
/* 111:    */      try
/* 112:    */      {
/* 113: 98 */        if ((localle = getController().getSegmentBuffer().a(localq, false, localle)).a())
/* 114:    */        {
/* 115:    */          s locals;
/* 116:101 */          (locals = new s()).a(localq.a, localq.b, localq.c, -2);
/* 117:102 */          ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/* 118:    */        }
/* 119:104 */      } catch (java.io.IOException localIOException) { 
/* 120:    */        
/* 125:110 */          localIOException;
/* 126:    */      }
/* 127:    */      catch (InterruptedException localInterruptedException) {
/* 128:107 */        
/* 129:    */        
/* 131:110 */          localInterruptedException;
/* 132:    */      }
/* 133:    */    }
/* 134:    */    
/* 136:112 */    System.err.println("NEW OUTPUT SET: " + localle.a(new q()) + ";");
/* 137:113 */    this.output.b(localle.a(new q()));
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionCustomOutputUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */