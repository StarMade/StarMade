/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*  4:   */import jL;
/*  5:   */import java.io.PrintStream;
/*  6:   */import java.util.Collection;
/*  7:   */import ka;
/*  8:   */
/*  9:   */public abstract class CustomOutputUnit extends ElementCollection
/* 10:   */{
/* 11:11 */  private q output = new q(0, 0, 0);
/* 12:   */  
/* 16:16 */  public q getOutput() { return this.output; }
/* 17:   */  
/* 18:   */  public void onChangeFinished() {
/* 19:19 */    super.onChangeFinished();
/* 20:20 */    le localle1 = new le();
/* 21:21 */    int i = 0;
/* 22:   */    try {
/* 23:23 */      localq = new q();
/* 24:24 */      for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext();) {
/* 25:25 */        getPosFromIndex(((Long)localIterator.next()).longValue(), localq);
/* 26:   */        
/* 27:   */        le localle2;
/* 28:   */        
/* 29:29 */        if (((localle2 = getController().getSegmentBuffer().a(localq, false, localle1)) != null) && (localle2.a())) {
/* 30:30 */          this.output.b(localq);
/* 31:   */          
/* 32:32 */          i = 1;
/* 33:33 */          break;
/* 34:   */        }
/* 35:   */      } } catch (java.io.IOException localIOException) { java.util.Iterator localIterator;
/* 36:36 */      localq = null;
/* 37:   */      
/* 40:40 */      localIOException.printStackTrace();
/* 41:   */    } catch (InterruptedException localInterruptedException) {
/* 42:38 */      q localq = null;
/* 43:   */      
/* 44:40 */      localInterruptedException.printStackTrace();
/* 45:   */    }
/* 46:   */    
/* 47:41 */    if (i == 0)
/* 48:42 */      this.output.b(getSignificator());
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void setMainPiece(le paramle, boolean paramBoolean) {
/* 52:46 */    System.err.println("SET NEW MAIN PIECE: " + paramle.a(new q()) + "; ACTIVE: " + paramBoolean);
/* 53:47 */    if (paramBoolean) { le localle;
/* 54:48 */      q localq; java.util.Iterator localIterator; if (paramle.a().a().isOnServer())
/* 55:   */      {
/* 56:50 */        localle = new le();
/* 57:   */        
/* 58:52 */        localq = new q();
/* 59:53 */        for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext();) {
/* 60:54 */          getPosFromIndex(((Long)localIterator.next()).longValue(), localq);
/* 61:55 */          if (!paramle.a(localq)) {
/* 62:   */            try
/* 63:   */            {
/* 64:58 */              paramle.a().a().getSegmentBuffer().a(localq, false, localle);
/* 65:59 */              if (localle.a())
/* 66:   */              {
/* 67:   */                s locals;
/* 68:62 */                (locals = new s()).a(localq.a, localq.b, localq.c, -2);
/* 69:63 */                ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/* 70:   */              }
/* 71:65 */            } catch (java.io.IOException localIOException) { 
/* 72:   */              
/* 77:71 */                localIOException;
/* 78:   */            }
/* 79:   */            catch (InterruptedException localInterruptedException) {
/* 80:68 */              
/* 81:   */              
/* 83:71 */                localInterruptedException;
/* 84:   */            }
/* 85:   */          }
/* 86:   */        }
/* 87:   */      }
/* 88:   */      
/* 90:75 */      System.err.println("NEW OUTPUT SET: " + paramle.a(new q()) + "; ACTIVE: " + paramBoolean);
/* 91:76 */      this.output.b(paramle.a(new q()));
/* 92:   */    }
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.CustomOutputUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */