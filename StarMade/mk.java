/*  1:   */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*  2:   */import java.util.HashSet;
/*  3:   */import java.util.Iterator;
/*  4:   */import org.schema.game.common.data.element.BlockLevel;
/*  5:   */import org.schema.game.common.data.element.ElementInformation;
/*  6:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  7:   */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*  8:   */
/* 10:   */public final class mk
/* 11:   */  extends md
/* 12:   */{
/* 13:   */  public mk(mh parammh, q paramq)
/* 14:   */  {
/* 15:15 */    super(parammh, paramq);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public final int a()
/* 19:   */  {
/* 20:20 */    return 0;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public final int b() {
/* 24:24 */    return 45;
/* 25:   */  }
/* 26:   */  
/* 28:   */  public final int c()
/* 29:   */  {
/* 30:30 */    return 1;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public final int d()
/* 34:   */  {
/* 35:35 */    return 45;
/* 36:   */  }
/* 37:   */  
/* 39:   */  public final void a()
/* 40:   */  {
/* 41:41 */    Iterator localIterator = ElementKeyMap.getLeveldkeyset().iterator(); short s; do { if (!localIterator.hasNext()) break; s = ((Short)localIterator.next()).shortValue();
/* 42:   */    }
/* 43:43 */    while ((b(s) >= 10) && 
/* 44:44 */      (!a(s)));
/* 45:   */    
/* 49:49 */    this.a = false;
/* 50:   */  }
/* 51:   */  
/* 52:   */  private boolean a(short paramShort) {
/* 53:   */    ElementInformation localElementInformation;
/* 54:54 */    short s = ElementKeyMap.getLevel((localElementInformation = ElementKeyMap.getInfo(paramShort)).getLevel().getIdBase(), localElementInformation.getLevel().getLevel() + 1);
/* 55:55 */    IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet();
/* 56:56 */    boolean bool = false;
/* 57:57 */    if (s > 0)
/* 58:   */    {
/* 59:   */      try {
/* 60:60 */        int j = b(paramShort);
/* 61:61 */        a(paramShort, localIntOpenHashSet);
/* 62:62 */        int k = j;
/* 63:63 */        if (j >= 10) {
/* 64:64 */          int i = j / 10 * 10;
/* 65:65 */          k = j - i;
/* 66:   */          
/* 67:67 */          i /= 10;
/* 68:68 */          localIntOpenHashSet.add(b(s, i));
/* 69:69 */          bool = true;
/* 70:   */        }
/* 71:   */        
/* 72:72 */        localIntOpenHashSet.add(b(paramShort, k));
/* 73:   */      }
/* 74:   */      catch (NoSlotFreeException localNoSlotFreeException) {}
/* 75:   */      
/* 76:76 */      a(localIntOpenHashSet);
/* 77:   */    }
/* 78:   */    
/* 82:82 */    return bool;
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */