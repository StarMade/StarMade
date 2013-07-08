/*   1:    */package org.newdawn.slick.loading;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.openal.SoundStore;
/*   5:    */import org.newdawn.slick.opengl.InternalTextureLoader;
/*   6:    */import org.newdawn.slick.util.Log;
/*   7:    */
/*  15:    */public class LoadingList
/*  16:    */{
/*  17: 17 */  private static LoadingList single = new LoadingList();
/*  18:    */  
/*  23:    */  public static LoadingList get()
/*  24:    */  {
/*  25: 25 */    return single;
/*  26:    */  }
/*  27:    */  
/*  32:    */  public static void setDeferredLoading(boolean loading)
/*  33:    */  {
/*  34: 34 */    single = new LoadingList();
/*  35:    */    
/*  36: 36 */    InternalTextureLoader.get().setDeferredLoading(loading);
/*  37: 37 */    SoundStore.get().setDeferredLoading(loading);
/*  38:    */  }
/*  39:    */  
/*  44:    */  public static boolean isDeferredLoading()
/*  45:    */  {
/*  46: 46 */    return InternalTextureLoader.get().isDeferredLoading();
/*  47:    */  }
/*  48:    */  
/*  50: 50 */  private ArrayList deferred = new ArrayList();
/*  51:    */  
/*  57:    */  private int total;
/*  58:    */  
/*  64:    */  public void add(DeferredResource resource)
/*  65:    */  {
/*  66: 66 */    this.total += 1;
/*  67: 67 */    this.deferred.add(resource);
/*  68:    */  }
/*  69:    */  
/*  75:    */  public void remove(DeferredResource resource)
/*  76:    */  {
/*  77: 77 */    Log.info("Early loading of deferred resource due to req: " + resource.getDescription());
/*  78: 78 */    this.total -= 1;
/*  79: 79 */    this.deferred.remove(resource);
/*  80:    */  }
/*  81:    */  
/*  86:    */  public int getTotalResources()
/*  87:    */  {
/*  88: 88 */    return this.total;
/*  89:    */  }
/*  90:    */  
/*  95:    */  public int getRemainingResources()
/*  96:    */  {
/*  97: 97 */    return this.deferred.size();
/*  98:    */  }
/*  99:    */  
/* 104:    */  public DeferredResource getNext()
/* 105:    */  {
/* 106:106 */    if (this.deferred.size() == 0) {
/* 107:107 */      return null;
/* 108:    */    }
/* 109:    */    
/* 110:110 */    return (DeferredResource)this.deferred.remove(0);
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.loading.LoadingList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */