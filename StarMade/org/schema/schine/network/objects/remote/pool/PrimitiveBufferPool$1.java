/*  1:   */package org.schema.schine.network.objects.remote.pool;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */import java.util.Map;
/*  5:   */
/* 24:   */final class PrimitiveBufferPool$1
/* 25:   */  extends ThreadLocal
/* 26:   */{
/* 27:   */  protected final Map initialValue()
/* 28:   */  {
/* 29:29 */    return new HashMap();
/* 30:   */  }
/* 31:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.pool.PrimitiveBufferPool.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */