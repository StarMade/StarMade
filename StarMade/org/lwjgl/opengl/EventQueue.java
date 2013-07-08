/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */
/* 41:   */class EventQueue
/* 42:   */{
/* 43:   */  private static final int QUEUE_SIZE = 200;
/* 44:   */  private final int event_size;
/* 45:   */  private final ByteBuffer queue;
/* 46:   */  
/* 47:   */  protected EventQueue(int event_size)
/* 48:   */  {
/* 49:49 */    this.event_size = event_size;
/* 50:50 */    this.queue = ByteBuffer.allocate(200 * event_size);
/* 51:   */  }
/* 52:   */  
/* 53:   */  protected synchronized void clearEvents() {
/* 54:54 */    this.queue.clear();
/* 55:   */  }
/* 56:   */  
/* 59:   */  public synchronized void copyEvents(ByteBuffer dest)
/* 60:   */  {
/* 61:61 */    this.queue.flip();
/* 62:62 */    int old_limit = this.queue.limit();
/* 63:63 */    if (dest.remaining() < this.queue.remaining())
/* 64:64 */      this.queue.limit(dest.remaining() + this.queue.position());
/* 65:65 */    dest.put(this.queue);
/* 66:66 */    this.queue.limit(old_limit);
/* 67:67 */    this.queue.compact();
/* 68:   */  }
/* 69:   */  
/* 73:   */  public synchronized boolean putEvent(ByteBuffer event)
/* 74:   */  {
/* 75:75 */    if (event.remaining() != this.event_size)
/* 76:76 */      throw new IllegalArgumentException("Internal error: event size " + this.event_size + " does not equal the given event size " + event.remaining());
/* 77:77 */    if (this.queue.remaining() >= event.remaining()) {
/* 78:78 */      this.queue.put(event);
/* 79:79 */      return true;
/* 80:   */    }
/* 81:81 */    return false;
/* 82:   */  }
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EventQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */