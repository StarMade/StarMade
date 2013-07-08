/*   1:    */package net.rudp;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.net.DatagramPacket;
/*   6:    */import java.net.DatagramSocket;
/*   7:    */import java.net.InetAddress;
/*   8:    */import java.net.InetSocketAddress;
/*   9:    */import java.net.ServerSocket;
/*  10:    */import java.net.Socket;
/*  11:    */import java.net.SocketAddress;
/*  12:    */import java.net.SocketException;
/*  13:    */import java.net.SocketTimeoutException;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.HashMap;
/*  16:    */import net.rudp.impl.SYNSegment;
/*  17:    */import net.rudp.impl.Segment;
/*  18:    */
/*  53:    */public class ReliableServerSocket
/*  54:    */  extends ServerSocket
/*  55:    */{
/*  56:    */  private DatagramSocket _serverSock;
/*  57:    */  private int _timeout;
/*  58:    */  private int _backlogSize;
/*  59:    */  private boolean _closed;
/*  60:    */  private ArrayList _backlog;
/*  61:    */  private HashMap _clientSockTable;
/*  62:    */  private ReliableSocketStateListener _stateListener;
/*  63:    */  private static final int DEFAULT_BACKLOG_SIZE = 50;
/*  64:    */  
/*  65:    */  public ReliableServerSocket()
/*  66:    */    throws IOException
/*  67:    */  {
/*  68: 68 */    this(0, 0, null);
/*  69:    */  }
/*  70:    */  
/*  84:    */  public ReliableServerSocket(int paramInt)
/*  85:    */    throws IOException
/*  86:    */  {
/*  87: 87 */    this(paramInt, 0, null);
/*  88:    */  }
/*  89:    */  
/* 101:    */  public ReliableServerSocket(int paramInt1, int paramInt2)
/* 102:    */    throws IOException
/* 103:    */  {
/* 104:104 */    this(paramInt1, paramInt2, null);
/* 105:    */  }
/* 106:    */  
/* 123:    */  public ReliableServerSocket(int paramInt1, int paramInt2, InetAddress paramInetAddress)
/* 124:    */    throws IOException
/* 125:    */  {
/* 126:126 */    this(new DatagramSocket(new InetSocketAddress(paramInetAddress, paramInt1)), paramInt2);
/* 127:    */  }
/* 128:    */  
/* 137:    */  public ReliableServerSocket(DatagramSocket paramDatagramSocket, int paramInt)
/* 138:    */    throws IOException
/* 139:    */  {
/* 140:140 */    if (paramDatagramSocket == null) {
/* 141:141 */      throw new NullPointerException("sock");
/* 142:    */    }
/* 143:    */    
/* 144:144 */    this._serverSock = paramDatagramSocket;
/* 145:145 */    this._backlogSize = (paramInt <= 0 ? 50 : paramInt);
/* 146:146 */    this._backlog = new ArrayList(this._backlogSize);
/* 147:147 */    this._clientSockTable = new HashMap();
/* 148:148 */    this._stateListener = new StateListener(null);
/* 149:149 */    this._timeout = 0;
/* 150:150 */    this._closed = false;
/* 151:    */    
/* 152:152 */    new ReceiverThread().start();
/* 153:    */  }
/* 154:    */  
/* 155:    */  public Socket accept()
/* 156:    */    throws IOException
/* 157:    */  {
/* 158:158 */    if (isClosed()) {
/* 159:159 */      throw new SocketException("Socket is closed");
/* 160:    */    }
/* 161:    */    
/* 162:162 */    synchronized (this._backlog) {
/* 163:163 */      while (this._backlog.isEmpty()) {
/* 164:    */        try {
/* 165:165 */          if (this._timeout == 0) {
/* 166:166 */            this._backlog.wait();
/* 167:    */          }
/* 168:    */          else {
/* 169:169 */            long l = System.currentTimeMillis();
/* 170:170 */            this._backlog.wait(this._timeout);
/* 171:171 */            if (System.currentTimeMillis() - l >= this._timeout) {
/* 172:172 */              throw new SocketTimeoutException();
/* 173:    */            }
/* 174:    */          }
/* 175:    */        }
/* 176:    */        catch (InterruptedException localInterruptedException)
/* 177:    */        {
/* 178:178 */          localInterruptedException.printStackTrace();
/* 179:    */        }
/* 180:    */        
/* 181:181 */        if (isClosed()) {
/* 182:182 */          throw new IOException();
/* 183:    */        }
/* 184:    */      }
/* 185:    */      
/* 186:186 */      return (Socket)this._backlog.remove(0);
/* 187:    */    }
/* 188:    */  }
/* 189:    */  
/* 190:    */  public synchronized void bind(SocketAddress paramSocketAddress)
/* 191:    */    throws IOException
/* 192:    */  {
/* 193:193 */    bind(paramSocketAddress, 0);
/* 194:    */  }
/* 195:    */  
/* 196:    */  public synchronized void bind(SocketAddress paramSocketAddress, int paramInt)
/* 197:    */    throws IOException
/* 198:    */  {
/* 199:199 */    if (isClosed()) {
/* 200:200 */      throw new SocketException("Socket is closed");
/* 201:    */    }
/* 202:    */    
/* 203:203 */    this._serverSock.bind(paramSocketAddress);
/* 204:    */  }
/* 205:    */  
/* 206:    */  public synchronized void close()
/* 207:    */  {
/* 208:208 */    if (isClosed()) {
/* 209:209 */      return;
/* 210:    */    }
/* 211:    */    
/* 212:212 */    this._closed = true;
/* 213:213 */    synchronized (this._backlog) {
/* 214:214 */      this._backlog.clear();
/* 215:215 */      this._backlog.notify();
/* 216:    */    }
/* 217:    */    
/* 218:218 */    if (this._clientSockTable.isEmpty()) {
/* 219:219 */      this._serverSock.close();
/* 220:    */    }
/* 221:    */  }
/* 222:    */  
/* 223:    */  public InetAddress getInetAddress()
/* 224:    */  {
/* 225:225 */    return this._serverSock.getInetAddress();
/* 226:    */  }
/* 227:    */  
/* 228:    */  public int getLocalPort()
/* 229:    */  {
/* 230:230 */    return this._serverSock.getLocalPort();
/* 231:    */  }
/* 232:    */  
/* 233:    */  public SocketAddress getLocalSocketAddress()
/* 234:    */  {
/* 235:235 */    return this._serverSock.getLocalSocketAddress();
/* 236:    */  }
/* 237:    */  
/* 238:    */  public boolean isBound()
/* 239:    */  {
/* 240:240 */    return this._serverSock.isBound();
/* 241:    */  }
/* 242:    */  
/* 243:    */  public boolean isClosed()
/* 244:    */  {
/* 245:245 */    return this._closed;
/* 246:    */  }
/* 247:    */  
/* 248:    */  public void setSoTimeout(int paramInt)
/* 249:    */  {
/* 250:250 */    if (paramInt < 0) {
/* 251:251 */      throw new IllegalArgumentException("timeout < 0");
/* 252:    */    }
/* 253:    */    
/* 254:254 */    this._timeout = paramInt;
/* 255:    */  }
/* 256:    */  
/* 257:    */  public int getSoTimeout()
/* 258:    */  {
/* 259:259 */    return this._timeout;
/* 260:    */  }
/* 261:    */  
/* 268:    */  private ReliableClientSocket addClientSocket(SocketAddress paramSocketAddress)
/* 269:    */  {
/* 270:270 */    synchronized (this._clientSockTable) {
/* 271:271 */      ReliableClientSocket localReliableClientSocket = (ReliableClientSocket)this._clientSockTable.get(paramSocketAddress);
/* 272:    */      
/* 273:273 */      if (localReliableClientSocket == null) {
/* 274:    */        try {
/* 275:275 */          localReliableClientSocket = new ReliableClientSocket(this._serverSock, paramSocketAddress);
/* 276:276 */          localReliableClientSocket.addStateListener(this._stateListener);
/* 277:277 */          this._clientSockTable.put(paramSocketAddress, localReliableClientSocket);
/* 278:    */        }
/* 279:    */        catch (IOException localIOException) {
/* 280:280 */          localIOException.printStackTrace();
/* 281:    */        }
/* 282:    */      }
/* 283:    */      
/* 284:284 */      return localReliableClientSocket;
/* 285:    */    }
/* 286:    */  }
/* 287:    */  
/* 294:    */  private ReliableClientSocket removeClientSocket(SocketAddress paramSocketAddress)
/* 295:    */  {
/* 296:296 */    synchronized (this._clientSockTable) {
/* 297:297 */      ReliableClientSocket localReliableClientSocket = (ReliableClientSocket)this._clientSockTable.remove(paramSocketAddress);
/* 298:    */      
/* 299:299 */      if ((this._clientSockTable.isEmpty()) && 
/* 300:300 */        (isClosed())) {
/* 301:301 */        this._serverSock.close();
/* 302:    */      }
/* 303:    */      
/* 305:305 */      return localReliableClientSocket;
/* 306:    */    }
/* 307:    */  }
/* 308:    */  
/* 327:    */  private class ReceiverThread
/* 328:    */    extends Thread
/* 329:    */  {
/* 330:    */    public ReceiverThread()
/* 331:    */    {
/* 332:332 */      super();
/* 333:333 */      setDaemon(true);
/* 334:    */    }
/* 335:    */    
/* 336:    */    public void run()
/* 337:    */    {
/* 338:338 */      byte[] arrayOfByte = new byte[65535];
/* 339:    */      for (;;)
/* 340:    */      {
/* 341:341 */        DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length);
/* 342:342 */        ReliableServerSocket.ReliableClientSocket localReliableClientSocket = null;
/* 343:    */        try
/* 344:    */        {
/* 345:345 */          ReliableServerSocket.this._serverSock.receive(localDatagramPacket);
/* 346:346 */          SocketAddress localSocketAddress = localDatagramPacket.getSocketAddress();
/* 347:347 */          Segment localSegment = Segment.parse(localDatagramPacket.getData(), 0, localDatagramPacket.getLength());
/* 348:    */          
/* 349:349 */          synchronized (ReliableServerSocket.this._clientSockTable)
/* 350:    */          {
/* 351:351 */            if ((!ReliableServerSocket.this.isClosed()) && 
/* 352:352 */              ((localSegment instanceof SYNSegment)) && 
/* 353:353 */              (!ReliableServerSocket.this._clientSockTable.containsKey(localSocketAddress))) {
/* 354:354 */              localReliableClientSocket = ReliableServerSocket.this.addClientSocket(localSocketAddress);
/* 355:    */            }
/* 356:    */            
/* 359:359 */            localReliableClientSocket = (ReliableServerSocket.ReliableClientSocket)ReliableServerSocket.this._clientSockTable.get(localSocketAddress);
/* 360:    */          }
/* 361:    */          
/* 362:362 */          if (localReliableClientSocket != null) {
/* 363:363 */            localReliableClientSocket.segmentReceived(localSegment);
/* 364:    */          }
/* 365:    */        }
/* 366:    */        catch (IOException localIOException) {
/* 367:367 */          if (!ReliableServerSocket.this.isClosed()) break label161; }
/* 368:368 */        break;
/* 369:    */        label161:
/* 370:370 */        localIOException.printStackTrace();
/* 371:    */      }
/* 372:    */    }
/* 373:    */  }
/* 374:    */  
/* 375:    */  private class ReliableClientSocket
/* 376:    */    extends ReliableSocket
/* 377:    */  {
/* 378:    */    private ArrayList _queue;
/* 379:    */    
/* 380:    */    public ReliableClientSocket(DatagramSocket paramDatagramSocket, SocketAddress paramSocketAddress) throws IOException
/* 381:    */    {
/* 382:382 */      super();
/* 383:383 */      this._endpoint = paramSocketAddress;
/* 384:    */    }
/* 385:    */    
/* 386:    */    protected void init(DatagramSocket paramDatagramSocket, ReliableSocketProfile paramReliableSocketProfile)
/* 387:    */    {
/* 388:388 */      this._queue = new ArrayList();
/* 389:389 */      super.init(paramDatagramSocket, paramReliableSocketProfile);
/* 390:    */    }
/* 391:    */    
/* 392:    */    protected Segment receiveSegmentImpl()
/* 393:    */    {
/* 394:394 */      synchronized (this._queue) {
/* 395:395 */        while (this._queue.isEmpty()) {
/* 396:    */          try {
/* 397:397 */            this._queue.wait();
/* 398:    */          }
/* 399:    */          catch (InterruptedException localInterruptedException) {
/* 400:400 */            localInterruptedException.printStackTrace();
/* 401:    */          }
/* 402:    */        }
/* 403:    */        
/* 404:404 */        return (Segment)this._queue.remove(0);
/* 405:    */      }
/* 406:    */    }
/* 407:    */    
/* 408:    */    protected void segmentReceived(Segment paramSegment)
/* 409:    */    {
/* 410:410 */      synchronized (this._queue) {
/* 411:411 */        this._queue.add(paramSegment);
/* 412:412 */        this._queue.notify();
/* 413:    */      }
/* 414:    */    }
/* 415:    */    
/* 416:    */    protected void closeSocket()
/* 417:    */    {
/* 418:418 */      synchronized (this._queue) {
/* 419:419 */        this._queue.clear();
/* 420:420 */        this._queue.add(null);
/* 421:421 */        this._queue.notify();
/* 422:    */      }
/* 423:    */    }
/* 424:    */    
/* 425:    */    protected void log(String paramString)
/* 426:    */    {
/* 427:427 */      System.out.println(getPort() + ": " + paramString);
/* 428:    */    }
/* 429:    */  }
/* 430:    */  
/* 431:    */  private class StateListener implements ReliableSocketStateListener
/* 432:    */  {
/* 433:    */    private StateListener() {}
/* 434:    */    
/* 435:    */    public void connectionOpened(ReliableSocket paramReliableSocket)
/* 436:    */    {
/* 437:437 */      if ((paramReliableSocket instanceof ReliableServerSocket.ReliableClientSocket)) {
/* 438:438 */        synchronized (ReliableServerSocket.this._backlog) {
/* 439:439 */          while (ReliableServerSocket.this._backlog.size() > 50) {
/* 440:    */            try {
/* 441:441 */              ReliableServerSocket.this._backlog.wait();
/* 442:    */            }
/* 443:    */            catch (InterruptedException localInterruptedException) {
/* 444:444 */              localInterruptedException.printStackTrace();
/* 445:    */            }
/* 446:    */          }
/* 447:    */          
/* 448:448 */          ReliableServerSocket.this._backlog.add(paramReliableSocket);
/* 449:449 */          ReliableServerSocket.this._backlog.notify();
/* 450:    */        }
/* 451:    */      }
/* 452:    */    }
/* 453:    */    
/* 456:    */    public void connectionRefused(ReliableSocket paramReliableSocket) {}
/* 457:    */    
/* 460:    */    public void connectionClosed(ReliableSocket paramReliableSocket)
/* 461:    */    {
/* 462:462 */      if ((paramReliableSocket instanceof ReliableServerSocket.ReliableClientSocket)) {
/* 463:463 */        ReliableServerSocket.this.removeClientSocket(paramReliableSocket.getRemoteSocketAddress());
/* 464:    */      }
/* 465:    */    }
/* 466:    */    
/* 468:    */    public void connectionFailure(ReliableSocket paramReliableSocket)
/* 469:    */    {
/* 470:470 */      if ((paramReliableSocket instanceof ReliableServerSocket.ReliableClientSocket)) {
/* 471:471 */        ReliableServerSocket.this.removeClientSocket(paramReliableSocket.getRemoteSocketAddress());
/* 472:    */      }
/* 473:    */    }
/* 474:    */    
/* 475:    */    public void connectionReset(ReliableSocket paramReliableSocket) {}
/* 476:    */  }
/* 477:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.ReliableServerSocket
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */