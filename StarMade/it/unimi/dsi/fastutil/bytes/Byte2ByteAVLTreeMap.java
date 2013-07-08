package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.SortedMap;

public class Byte2ByteAVLTreeMap
  extends AbstractByte2ByteSortedMap
  implements Serializable, Cloneable
{
  protected transient Entry tree;
  protected int count;
  protected transient Entry firstEntry;
  protected transient Entry lastEntry;
  protected volatile transient ObjectSortedSet<Byte2ByteMap.Entry> entries;
  protected volatile transient ByteSortedSet keys;
  protected volatile transient ByteCollection values;
  protected transient boolean modified;
  protected Comparator<? super Byte> storedComparator;
  protected transient ByteComparator actualComparator;
  public static final long serialVersionUID = -7046029254386353129L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  
  public Byte2ByteAVLTreeMap()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    if ((this.storedComparator == null) || ((this.storedComparator instanceof ByteComparator))) {
      this.actualComparator = ((ByteComparator)this.storedComparator);
    } else {
      this.actualComparator = new ByteComparator()
      {
        public int compare(byte local_k1, byte local_k2)
        {
          return Byte2ByteAVLTreeMap.this.storedComparator.compare(Byte.valueOf(local_k1), Byte.valueOf(local_k2));
        }
        
        public int compare(Byte ok1, Byte ok2)
        {
          return Byte2ByteAVLTreeMap.this.storedComparator.compare(ok1, ok2);
        }
      };
    }
  }
  
  public Byte2ByteAVLTreeMap(Comparator<? super Byte> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public Byte2ByteAVLTreeMap(Map<? extends Byte, ? extends Byte> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Byte2ByteAVLTreeMap(SortedMap<Byte, Byte> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Byte2ByteAVLTreeMap(Byte2ByteMap local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Byte2ByteAVLTreeMap(Byte2ByteSortedMap local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Byte2ByteAVLTreeMap(byte[] local_k, byte[] local_v, Comparator<? super Byte> local_c)
  {
    this(local_c);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Byte2ByteAVLTreeMap(byte[] local_k, byte[] local_v)
  {
    this(local_k, local_v, null);
  }
  
  final int compare(byte local_k1, byte local_k2)
  {
    return this.actualComparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.actualComparator.compare(local_k1, local_k2);
  }
  
  final Entry findKey(byte local_k)
  {
    int cmp;
    for (Entry local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry locateKey(byte local_k)
  {
    Entry local_e = this.tree;
    Entry last = this.tree;
    int cmp = 0;
    while ((local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0))
    {
      last = local_e;
      local_e = cmp < 0 ? local_e.left() : local_e.right();
    }
    return cmp == 0 ? local_e : last;
  }
  
  private void allocatePaths()
  {
    this.dirPath = new boolean[48];
  }
  
  public byte put(byte local_k, byte local_v)
  {
    this.modified = false;
    if (this.tree == null)
    {
      this.count += 1;
      this.tree = (this.lastEntry = this.firstEntry = new Entry(local_k, local_v));
      this.modified = true;
    }
    else
    {
      Entry local_p = this.tree;
      Entry local_q = null;
      Entry local_y = this.tree;
      Entry local_z = null;
      Entry local_e = null;
      Entry local_w = null;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          byte oldValue = local_p.value;
          local_p.value = local_v;
          return oldValue;
        }
        if (local_p.balance() != 0)
        {
          local_i = 0;
          local_z = local_q;
          local_y = local_p;
        }
        if ((this.dirPath[(local_i++)] = cmp > 0 ? 1 : 0) != 0)
        {
          if (local_p.succ())
          {
            this.count += 1;
            local_e = new Entry(local_k, local_v);
            this.modified = true;
            if (local_p.right == null) {
              this.lastEntry = local_e;
            }
            local_e.left = local_p;
            local_e.right = local_p.right;
            local_p.right(local_e);
            break;
          }
          local_q = local_p;
          local_p = local_p.right;
        }
        else
        {
          if (local_p.pred())
          {
            this.count += 1;
            local_e = new Entry(local_k, local_v);
            this.modified = true;
            if (local_p.left == null) {
              this.firstEntry = local_e;
            }
            local_e.right = local_p;
            local_e.left = local_p.left;
            local_p.left(local_e);
            break;
          }
          local_q = local_p;
          local_p = local_p.left;
        }
      }
      local_p = local_y;
      local_i = 0;
      while (local_p != local_e)
      {
        if (this.dirPath[local_i] != 0) {
          local_p.incBalance();
        } else {
          local_p.decBalance();
        }
        local_p = this.dirPath[(local_i++)] != 0 ? local_p.right : local_p.left;
      }
      if (local_y.balance() == -2)
      {
        Entry oldValue = local_y.left;
        if (oldValue.balance() == -1)
        {
          local_w = oldValue;
          if (oldValue.succ())
          {
            oldValue.succ(false);
            local_y.pred(oldValue);
          }
          else
          {
            local_y.left = oldValue.right;
          }
          oldValue.right = local_y;
          oldValue.balance(0);
          local_y.balance(0);
        }
        else
        {
          local_w = oldValue.right;
          oldValue.right = local_w.left;
          local_w.left = oldValue;
          local_y.left = local_w.right;
          local_w.right = local_y;
          if (local_w.balance() == -1)
          {
            oldValue.balance(0);
            local_y.balance(1);
          }
          else if (local_w.balance() == 0)
          {
            oldValue.balance(0);
            local_y.balance(0);
          }
          else
          {
            oldValue.balance(-1);
            local_y.balance(0);
          }
          local_w.balance(0);
          if (local_w.pred())
          {
            oldValue.succ(local_w);
            local_w.pred(false);
          }
          if (local_w.succ())
          {
            local_y.pred(local_w);
            local_w.succ(false);
          }
        }
      }
      else if (local_y.balance() == 2)
      {
        Entry oldValue = local_y.right;
        if (oldValue.balance() == 1)
        {
          local_w = oldValue;
          if (oldValue.pred())
          {
            oldValue.pred(false);
            local_y.succ(oldValue);
          }
          else
          {
            local_y.right = oldValue.left;
          }
          oldValue.left = local_y;
          oldValue.balance(0);
          local_y.balance(0);
        }
        else
        {
          local_w = oldValue.left;
          oldValue.left = local_w.right;
          local_w.right = oldValue;
          local_y.right = local_w.left;
          local_w.left = local_y;
          if (local_w.balance() == 1)
          {
            oldValue.balance(0);
            local_y.balance(-1);
          }
          else if (local_w.balance() == 0)
          {
            oldValue.balance(0);
            local_y.balance(0);
          }
          else
          {
            oldValue.balance(1);
            local_y.balance(0);
          }
          local_w.balance(0);
          if (local_w.pred())
          {
            local_y.succ(local_w);
            local_w.pred(false);
          }
          if (local_w.succ())
          {
            oldValue.pred(local_w);
            local_w.succ(false);
          }
        }
      }
      else
      {
        return this.defRetValue;
      }
      if (local_z == null) {
        this.tree = local_w;
      } else if (local_z.left == local_y) {
        local_z.left = local_w;
      } else {
        local_z.right = local_w;
      }
    }
    return this.defRetValue;
  }
  
  private Entry parent(Entry local_e)
  {
    if (local_e == this.tree) {
      return null;
    }
    Entry local_y;
    Entry local_x = local_y = local_e;
    for (;;)
    {
      if (local_y.succ())
      {
        Entry local_p = local_y.right;
        if ((local_p == null) || (local_p.left != local_e))
        {
          while (!local_x.pred()) {
            local_x = local_x.left;
          }
          local_p = local_x.left;
        }
        return local_p;
      }
      if (local_x.pred())
      {
        Entry local_p = local_x.left;
        if ((local_p == null) || (local_p.right != local_e))
        {
          while (!local_y.succ()) {
            local_y = local_y.right;
          }
          local_p = local_y.right;
        }
        return local_p;
      }
      local_x = local_x.left;
      local_y = local_y.right;
    }
  }
  
  public byte remove(byte local_k)
  {
    this.modified = false;
    if (this.tree == null) {
      return this.defRetValue;
    }
    Entry local_p = this.tree;
    Entry local_q = null;
    boolean dir = false;
    byte local_kk = local_k;
    int cmp;
    while ((cmp = compare(local_kk, local_p.key)) != 0) {
      if ((dir = cmp > 0 ? 1 : 0) != 0)
      {
        local_q = local_p;
        if ((local_p = local_p.right()) == null) {
          return this.defRetValue;
        }
      }
      else
      {
        local_q = local_p;
        if ((local_p = local_p.left()) == null) {
          return this.defRetValue;
        }
      }
    }
    if (local_p.left == null) {
      this.firstEntry = local_p.next();
    }
    if (local_p.right == null) {
      this.lastEntry = local_p.prev();
    }
    if (local_p.succ())
    {
      if (local_p.pred())
      {
        if (local_q != null)
        {
          if (dir) {
            local_q.succ(local_p.right);
          } else {
            local_q.pred(local_p.left);
          }
        }
        else {
          this.tree = (dir ? local_p.right : local_p.left);
        }
      }
      else
      {
        local_p.prev().right = local_p.right;
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_p.left;
          } else {
            local_q.left = local_p.left;
          }
        }
        else {
          this.tree = local_p.left;
        }
      }
    }
    else
    {
      Entry local_r = local_p.right;
      if (local_r.pred())
      {
        local_r.left = local_p.left;
        local_r.pred(local_p.pred());
        if (!local_r.pred()) {
          local_r.prev().right = local_r;
        }
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_r;
          } else {
            local_q.left = local_r;
          }
        }
        else {
          this.tree = local_r;
        }
        local_r.balance(local_p.balance());
        local_q = local_r;
        dir = true;
      }
      else
      {
        Entry local_s;
        for (;;)
        {
          local_s = local_r.left;
          if (local_s.pred()) {
            break;
          }
          local_r = local_s;
        }
        if (local_s.succ()) {
          local_r.pred(local_s);
        } else {
          local_r.left = local_s.right;
        }
        local_s.left = local_p.left;
        if (!local_p.pred())
        {
          local_p.prev().right = local_s;
          local_s.pred(false);
        }
        local_s.right = local_p.right;
        local_s.succ(false);
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_s;
          } else {
            local_q.left = local_s;
          }
        }
        else {
          this.tree = local_s;
        }
        local_s.balance(local_p.balance());
        local_q = local_r;
        dir = false;
      }
    }
    while (local_q != null)
    {
      Entry local_r = local_q;
      local_q = parent(local_r);
      if (!dir)
      {
        dir = (local_q != null) && (local_q.left != local_r);
        local_r.incBalance();
        if (local_r.balance() == 1) {
          break;
        }
        if (local_r.balance() == 2)
        {
          Entry local_s = local_r.right;
          if (local_s.balance() == -1)
          {
            Entry local_w = local_s.left;
            local_s.left = local_w.right;
            local_w.right = local_s;
            local_r.right = local_w.left;
            local_w.left = local_r;
            if (local_w.balance() == 1)
            {
              local_s.balance(0);
              local_r.balance(-1);
            }
            else if (local_w.balance() == 0)
            {
              local_s.balance(0);
              local_r.balance(0);
            }
            else
            {
              local_s.balance(1);
              local_r.balance(0);
            }
            local_w.balance(0);
            if (local_w.pred())
            {
              local_r.succ(local_w);
              local_w.pred(false);
            }
            if (local_w.succ())
            {
              local_s.pred(local_w);
              local_w.succ(false);
            }
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_w;
              } else {
                local_q.left = local_w;
              }
            }
            else {
              this.tree = local_w;
            }
          }
          else
          {
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_s;
              } else {
                local_q.left = local_s;
              }
            }
            else {
              this.tree = local_s;
            }
            if (local_s.balance() == 0)
            {
              local_r.right = local_s.left;
              local_s.left = local_r;
              local_s.balance(-1);
              local_r.balance(1);
              break;
            }
            if (local_s.pred())
            {
              local_r.succ(true);
              local_s.pred(false);
            }
            else
            {
              local_r.right = local_s.left;
            }
            local_s.left = local_r;
            local_r.balance(0);
            local_s.balance(0);
          }
        }
      }
      else
      {
        dir = (local_q != null) && (local_q.left != local_r);
        local_r.decBalance();
        if (local_r.balance() == -1) {
          break;
        }
        if (local_r.balance() == -2)
        {
          Entry local_s = local_r.left;
          if (local_s.balance() == 1)
          {
            Entry local_w = local_s.right;
            local_s.right = local_w.left;
            local_w.left = local_s;
            local_r.left = local_w.right;
            local_w.right = local_r;
            if (local_w.balance() == -1)
            {
              local_s.balance(0);
              local_r.balance(1);
            }
            else if (local_w.balance() == 0)
            {
              local_s.balance(0);
              local_r.balance(0);
            }
            else
            {
              local_s.balance(-1);
              local_r.balance(0);
            }
            local_w.balance(0);
            if (local_w.pred())
            {
              local_s.succ(local_w);
              local_w.pred(false);
            }
            if (local_w.succ())
            {
              local_r.pred(local_w);
              local_w.succ(false);
            }
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_w;
              } else {
                local_q.left = local_w;
              }
            }
            else {
              this.tree = local_w;
            }
          }
          else
          {
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_s;
              } else {
                local_q.left = local_s;
              }
            }
            else {
              this.tree = local_s;
            }
            if (local_s.balance() == 0)
            {
              local_r.left = local_s.right;
              local_s.right = local_r;
              local_s.balance(1);
              local_r.balance(-1);
              break;
            }
            if (local_s.succ())
            {
              local_r.pred(true);
              local_s.succ(false);
            }
            else
            {
              local_r.left = local_s.right;
            }
            local_s.right = local_r;
            local_r.balance(0);
            local_s.balance(0);
          }
        }
      }
    }
    this.modified = true;
    this.count -= 1;
    return local_p.value;
  }
  
  public Byte put(Byte local_ok, Byte local_ov)
  {
    byte oldValue = put(local_ok.byteValue(), local_ov.byteValue());
    return this.modified ? null : Byte.valueOf(oldValue);
  }
  
  public Byte remove(Object local_ok)
  {
    byte oldValue = remove(((Byte)local_ok).byteValue());
    return this.modified ? Byte.valueOf(oldValue) : null;
  }
  
  public boolean containsValue(byte local_v)
  {
    ValueIterator local_i = new ValueIterator(null);
    int local_j = this.count;
    while (local_j-- != 0)
    {
      byte local_ev = local_i.nextByte();
      if (local_ev == local_v) {
        return true;
      }
    }
    return false;
  }
  
  public void clear()
  {
    this.count = 0;
    this.tree = null;
    this.entries = null;
    this.values = null;
    this.keys = null;
    this.firstEntry = (this.lastEntry = null);
  }
  
  public boolean containsKey(byte local_k)
  {
    return findKey(local_k) != null;
  }
  
  public int size()
  {
    return this.count;
  }
  
  public boolean isEmpty()
  {
    return this.count == 0;
  }
  
  public byte get(byte local_k)
  {
    Entry local_e = findKey(local_k);
    return local_e == null ? this.defRetValue : local_e.value;
  }
  
  public byte firstByteKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public byte lastByteKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectSortedSet<Byte2ByteMap.Entry> byte2ByteEntrySet()
  {
    if (this.entries == null) {
      this.entries = new AbstractObjectSortedSet()
      {
        final Comparator<? super Byte2ByteMap.Entry> comparator = new Comparator()
        {
          public int compare(Byte2ByteMap.Entry local_x, Byte2ByteMap.Entry local_y)
          {
            return Byte2ByteAVLTreeMap.this.storedComparator.compare(local_x.getKey(), local_y.getKey());
          }
        };
        
        public Comparator<? super Byte2ByteMap.Entry> comparator()
        {
          return this.comparator;
        }
        
        public ObjectBidirectionalIterator<Byte2ByteMap.Entry> iterator()
        {
          return new Byte2ByteAVLTreeMap.EntryIterator(Byte2ByteAVLTreeMap.this);
        }
        
        public ObjectBidirectionalIterator<Byte2ByteMap.Entry> iterator(Byte2ByteMap.Entry from)
        {
          return new Byte2ByteAVLTreeMap.EntryIterator(Byte2ByteAVLTreeMap.this, ((Byte)from.getKey()).byteValue());
        }
        
        public boolean contains(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Byte, Byte> local_e = (Map.Entry)local_o;
          Byte2ByteAVLTreeMap.Entry local_f = Byte2ByteAVLTreeMap.this.findKey(((Byte)local_e.getKey()).byteValue());
          return local_e.equals(local_f);
        }
        
        public boolean remove(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Byte, Byte> local_e = (Map.Entry)local_o;
          Byte2ByteAVLTreeMap.Entry local_f = Byte2ByteAVLTreeMap.this.findKey(((Byte)local_e.getKey()).byteValue());
          if (local_f != null) {
            Byte2ByteAVLTreeMap.this.remove(local_f.key);
          }
          return local_f != null;
        }
        
        public int size()
        {
          return Byte2ByteAVLTreeMap.this.count;
        }
        
        public void clear()
        {
          Byte2ByteAVLTreeMap.this.clear();
        }
        
        public Byte2ByteMap.Entry first()
        {
          return Byte2ByteAVLTreeMap.this.firstEntry;
        }
        
        public Byte2ByteMap.Entry last()
        {
          return Byte2ByteAVLTreeMap.this.lastEntry;
        }
        
        public ObjectSortedSet<Byte2ByteMap.Entry> subSet(Byte2ByteMap.Entry from, Byte2ByteMap.Entry local_to)
        {
          return Byte2ByteAVLTreeMap.this.subMap((Byte)from.getKey(), (Byte)local_to.getKey()).byte2ByteEntrySet();
        }
        
        public ObjectSortedSet<Byte2ByteMap.Entry> headSet(Byte2ByteMap.Entry local_to)
        {
          return Byte2ByteAVLTreeMap.this.headMap((Byte)local_to.getKey()).byte2ByteEntrySet();
        }
        
        public ObjectSortedSet<Byte2ByteMap.Entry> tailSet(Byte2ByteMap.Entry from)
        {
          return Byte2ByteAVLTreeMap.this.tailMap((Byte)from.getKey()).byte2ByteEntrySet();
        }
      };
    }
    return this.entries;
  }
  
  public ByteSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ByteCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractByteCollection()
      {
        public ByteIterator iterator()
        {
          return new Byte2ByteAVLTreeMap.ValueIterator(Byte2ByteAVLTreeMap.this, null);
        }
        
        public boolean contains(byte local_k)
        {
          return Byte2ByteAVLTreeMap.this.containsValue(local_k);
        }
        
        public int size()
        {
          return Byte2ByteAVLTreeMap.this.count;
        }
        
        public void clear()
        {
          Byte2ByteAVLTreeMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  public ByteComparator comparator()
  {
    return this.actualComparator;
  }
  
  public Byte2ByteSortedMap headMap(byte local_to)
  {
    return new Submap((byte)0, true, local_to, false);
  }
  
  public Byte2ByteSortedMap tailMap(byte from)
  {
    return new Submap(from, false, (byte)0, true);
  }
  
  public Byte2ByteSortedMap subMap(byte from, byte local_to)
  {
    return new Submap(from, false, local_to, false);
  }
  
  public Byte2ByteAVLTreeMap clone()
  {
    Byte2ByteAVLTreeMap local_c;
    try
    {
      local_c = (Byte2ByteAVLTreeMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.allocatePaths();
    if (this.count != 0)
    {
      Entry local_rp = new Entry();
      Entry local_rq = new Entry();
      Entry local_p = local_rp;
      local_rp.left(this.tree);
      Entry local_q = local_rq;
      local_rq.pred(null);
      for (;;)
      {
        if (!local_p.pred())
        {
          Entry cantHappen = local_p.left.clone();
          cantHappen.pred(local_q.left);
          cantHappen.succ(local_q);
          local_q.left(cantHappen);
          local_p = local_p.left;
          local_q = local_q.left;
        }
        else
        {
          while (local_p.succ())
          {
            local_p = local_p.right;
            if (local_p == null)
            {
              local_q.right = null;
              local_c.tree = local_rq.left;
              for (local_c.firstEntry = local_c.tree; local_c.firstEntry.left != null; local_c.firstEntry = local_c.firstEntry.left) {}
              for (local_c.lastEntry = local_c.tree; local_c.lastEntry.right != null; local_c.lastEntry = local_c.lastEntry.right) {}
              return local_c;
            }
            local_q = local_q.right;
          }
          local_p = local_p.right;
          local_q = local_q.right;
        }
        if (!local_p.succ())
        {
          Entry cantHappen = local_p.right.clone();
          cantHappen.succ(local_q.right);
          cantHappen.pred(local_q);
          local_q.right(cantHappen);
        }
      }
    }
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    int local_n = this.count;
    EntryIterator local_i = new EntryIterator();
    local_s.defaultWriteObject();
    while (local_n-- != 0)
    {
      Entry local_e = local_i.nextEntry();
      local_s.writeByte(local_e.key);
      local_s.writeByte(local_e.value);
    }
  }
  
  private Entry readTree(ObjectInputStream local_s, int local_n, Entry pred, Entry succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry top = new Entry(local_s.readByte(), local_s.readByte());
      top.pred(pred);
      top.succ(succ);
      return top;
    }
    if (local_n == 2)
    {
      Entry top = new Entry(local_s.readByte(), local_s.readByte());
      top.right(new Entry(local_s.readByte(), local_s.readByte()));
      top.right.pred(top);
      top.balance(1);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readByte();
    top.value = local_s.readByte();
    top.right(readTree(local_s, top, top, succ));
    if (local_n == (local_n & -local_n)) {
      top.balance(1);
    }
    return top;
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    setActualComparator();
    allocatePaths();
    if (this.count != 0)
    {
      this.tree = readTree(local_s, this.count, null, null);
      for (Entry local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private static int checkTree(Entry local_e)
  {
    return 0;
  }
  
  private final class Submap
    extends AbstractByte2ByteSortedMap
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    byte from;
    byte field_47;
    boolean bottom;
    boolean top;
    protected volatile transient ObjectSortedSet<Byte2ByteMap.Entry> entries;
    protected volatile transient ByteSortedSet keys;
    protected volatile transient ByteCollection values;
    
    public Submap(byte from, boolean bottom, byte local_to, boolean top)
    {
      if ((!bottom) && (!top) && (Byte2ByteAVLTreeMap.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start key (" + from + ") is larger than end key (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_47 = local_to;
      this.top = top;
      this.defRetValue = Byte2ByteAVLTreeMap.this.defRetValue;
    }
    
    public void clear()
    {
      SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        local_i.nextEntry();
        local_i.remove();
      }
    }
    
    final boolean in3(byte local_k)
    {
      return ((this.bottom) || (Byte2ByteAVLTreeMap.this.compare(local_k, this.from) >= 0)) && ((this.top) || (Byte2ByteAVLTreeMap.this.compare(local_k, this.field_47) < 0));
    }
    
    public ObjectSortedSet<Byte2ByteMap.Entry> byte2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = new AbstractObjectSortedSet()
        {
          public ObjectBidirectionalIterator<Byte2ByteMap.Entry> iterator()
          {
            return new Byte2ByteAVLTreeMap.Submap.SubmapEntryIterator(Byte2ByteAVLTreeMap.Submap.this);
          }
          
          public ObjectBidirectionalIterator<Byte2ByteMap.Entry> iterator(Byte2ByteMap.Entry from)
          {
            return new Byte2ByteAVLTreeMap.Submap.SubmapEntryIterator(Byte2ByteAVLTreeMap.Submap.this, ((Byte)from.getKey()).byteValue());
          }
          
          public Comparator<? super Byte2ByteMap.Entry> comparator()
          {
            return Byte2ByteAVLTreeMap.this.entrySet().comparator();
          }
          
          public boolean contains(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Byte, Byte> local_e = (Map.Entry)local_o;
            Byte2ByteAVLTreeMap.Entry local_f = Byte2ByteAVLTreeMap.this.findKey(((Byte)local_e.getKey()).byteValue());
            return (local_f != null) && (Byte2ByteAVLTreeMap.Submap.this.in3(local_f.key)) && (local_e.equals(local_f));
          }
          
          public boolean remove(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Byte, Byte> local_e = (Map.Entry)local_o;
            Byte2ByteAVLTreeMap.Entry local_f = Byte2ByteAVLTreeMap.this.findKey(((Byte)local_e.getKey()).byteValue());
            if ((local_f != null) && (Byte2ByteAVLTreeMap.Submap.this.in3(local_f.key))) {
              Byte2ByteAVLTreeMap.Submap.this.remove(local_f.key);
            }
            return local_f != null;
          }
          
          public int size()
          {
            int local_c = 0;
            Iterator<?> local_i = iterator();
            while (local_i.hasNext())
            {
              local_c++;
              local_i.next();
            }
            return local_c;
          }
          
          public boolean isEmpty()
          {
            return !new Byte2ByteAVLTreeMap.Submap.SubmapIterator(Byte2ByteAVLTreeMap.Submap.this).hasNext();
          }
          
          public void clear()
          {
            Byte2ByteAVLTreeMap.Submap.this.clear();
          }
          
          public Byte2ByteMap.Entry first()
          {
            return Byte2ByteAVLTreeMap.Submap.this.firstEntry();
          }
          
          public Byte2ByteMap.Entry last()
          {
            return Byte2ByteAVLTreeMap.Submap.this.lastEntry();
          }
          
          public ObjectSortedSet<Byte2ByteMap.Entry> subSet(Byte2ByteMap.Entry from, Byte2ByteMap.Entry local_to)
          {
            return Byte2ByteAVLTreeMap.Submap.this.subMap((Byte)from.getKey(), (Byte)local_to.getKey()).byte2ByteEntrySet();
          }
          
          public ObjectSortedSet<Byte2ByteMap.Entry> headSet(Byte2ByteMap.Entry local_to)
          {
            return Byte2ByteAVLTreeMap.Submap.this.headMap((Byte)local_to.getKey()).byte2ByteEntrySet();
          }
          
          public ObjectSortedSet<Byte2ByteMap.Entry> tailSet(Byte2ByteMap.Entry from)
          {
            return Byte2ByteAVLTreeMap.Submap.this.tailMap((Byte)from.getKey()).byte2ByteEntrySet();
          }
        };
      }
      return this.entries;
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = new KeySet(null);
      }
      return this.keys;
    }
    
    public ByteCollection values()
    {
      if (this.values == null) {
        this.values = new AbstractByteCollection()
        {
          public ByteIterator iterator()
          {
            return new Byte2ByteAVLTreeMap.Submap.SubmapValueIterator(Byte2ByteAVLTreeMap.Submap.this, null);
          }
          
          public boolean contains(byte local_k)
          {
            return Byte2ByteAVLTreeMap.Submap.this.containsValue(local_k);
          }
          
          public int size()
          {
            return Byte2ByteAVLTreeMap.Submap.this.size();
          }
          
          public void clear()
          {
            Byte2ByteAVLTreeMap.Submap.this.clear();
          }
        };
      }
      return this.values;
    }
    
    public boolean containsKey(byte local_k)
    {
      return (in3(local_k)) && (Byte2ByteAVLTreeMap.this.containsKey(local_k));
    }
    
    public boolean containsValue(byte local_v)
    {
      SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        byte local_ev = local_i.nextEntry().value;
        if (local_ev == local_v) {
          return true;
        }
      }
      return false;
    }
    
    public byte get(byte local_k)
    {
      byte local_kk = local_k;
      Byte2ByteAVLTreeMap.Entry local_e;
      return (in3(local_kk)) && ((local_e = Byte2ByteAVLTreeMap.this.findKey(local_kk)) != null) ? local_e.value : this.defRetValue;
    }
    
    public byte put(byte local_k, byte local_v)
    {
      Byte2ByteAVLTreeMap.this.modified = false;
      if (!in3(local_k)) {
        throw new IllegalArgumentException("Key (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_47)) + ")");
      }
      byte oldValue = Byte2ByteAVLTreeMap.this.put(local_k, local_v);
      return Byte2ByteAVLTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public Byte put(Byte local_ok, Byte local_ov)
    {
      byte oldValue = put(local_ok.byteValue(), local_ov.byteValue());
      return Byte2ByteAVLTreeMap.this.modified ? null : Byte.valueOf(oldValue);
    }
    
    public byte remove(byte local_k)
    {
      Byte2ByteAVLTreeMap.this.modified = false;
      if (!in3(local_k)) {
        return this.defRetValue;
      }
      byte oldValue = Byte2ByteAVLTreeMap.this.remove(local_k);
      return Byte2ByteAVLTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public Byte remove(Object local_ok)
    {
      byte oldValue = remove(((Byte)local_ok).byteValue());
      return Byte2ByteAVLTreeMap.this.modified ? Byte.valueOf(oldValue) : null;
    }
    
    public int size()
    {
      SubmapIterator local_i = new SubmapIterator();
      int local_n = 0;
      while (local_i.hasNext())
      {
        local_n++;
        local_i.nextEntry();
      }
      return local_n;
    }
    
    public boolean isEmpty()
    {
      return !new SubmapIterator().hasNext();
    }
    
    public ByteComparator comparator()
    {
      return Byte2ByteAVLTreeMap.this.actualComparator;
    }
    
    public Byte2ByteSortedMap headMap(byte local_to)
    {
      if (this.top) {
        return new Submap(Byte2ByteAVLTreeMap.this, this.from, this.bottom, local_to, false);
      }
      return Byte2ByteAVLTreeMap.this.compare(local_to, this.field_47) < 0 ? new Submap(Byte2ByteAVLTreeMap.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public Byte2ByteSortedMap tailMap(byte from)
    {
      if (this.bottom) {
        return new Submap(Byte2ByteAVLTreeMap.this, from, false, this.field_47, this.top);
      }
      return Byte2ByteAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Byte2ByteAVLTreeMap.this, from, false, this.field_47, this.top) : this;
    }
    
    public Byte2ByteSortedMap subMap(byte from, byte local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Submap(Byte2ByteAVLTreeMap.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = Byte2ByteAVLTreeMap.this.compare(local_to, this.field_47) < 0 ? local_to : this.field_47;
      }
      if (!this.bottom) {
        from = Byte2ByteAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_47)) {
        return this;
      }
      return new Submap(Byte2ByteAVLTreeMap.this, from, false, local_to, false);
    }
    
    public Byte2ByteAVLTreeMap.Entry firstEntry()
    {
      if (Byte2ByteAVLTreeMap.this.tree == null) {
        return null;
      }
      Byte2ByteAVLTreeMap.Entry local_e;
      Byte2ByteAVLTreeMap.Entry local_e;
      if (this.bottom)
      {
        local_e = Byte2ByteAVLTreeMap.this.firstEntry;
      }
      else
      {
        local_e = Byte2ByteAVLTreeMap.this.locateKey(this.from);
        if (Byte2ByteAVLTreeMap.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (Byte2ByteAVLTreeMap.this.compare(local_e.key, this.field_47) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public Byte2ByteAVLTreeMap.Entry lastEntry()
    {
      if (Byte2ByteAVLTreeMap.this.tree == null) {
        return null;
      }
      Byte2ByteAVLTreeMap.Entry local_e;
      Byte2ByteAVLTreeMap.Entry local_e;
      if (this.top)
      {
        local_e = Byte2ByteAVLTreeMap.this.lastEntry;
      }
      else
      {
        local_e = Byte2ByteAVLTreeMap.this.locateKey(this.field_47);
        if (Byte2ByteAVLTreeMap.this.compare(local_e.key, this.field_47) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (Byte2ByteAVLTreeMap.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public byte firstByteKey()
    {
      Byte2ByteAVLTreeMap.Entry local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public byte lastByteKey()
    {
      Byte2ByteAVLTreeMap.Entry local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public Byte firstKey()
    {
      Byte2ByteAVLTreeMap.Entry local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    public Byte lastKey()
    {
      Byte2ByteAVLTreeMap.Entry local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    private final class SubmapValueIterator
      extends Byte2ByteAVLTreeMap.Submap.SubmapIterator
      implements ByteListIterator
    {
      private SubmapValueIterator()
      {
        super();
      }
      
      public byte nextByte()
      {
        return nextEntry().value;
      }
      
      public byte previousByte()
      {
        return previousEntry().value;
      }
      
      public void set(byte local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(byte local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public Byte next()
      {
        return Byte.valueOf(nextEntry().value);
      }
      
      public Byte previous()
      {
        return Byte.valueOf(previousEntry().value);
      }
      
      public void set(Byte local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Byte local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private final class SubmapKeyIterator
      extends Byte2ByteAVLTreeMap.Submap.SubmapIterator
      implements ByteListIterator
    {
      public SubmapKeyIterator()
      {
        super();
      }
      
      public SubmapKeyIterator(byte from)
      {
        super(from);
      }
      
      public byte nextByte()
      {
        return nextEntry().key;
      }
      
      public byte previousByte()
      {
        return previousEntry().key;
      }
      
      public void set(byte local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(byte local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public Byte next()
      {
        return Byte.valueOf(nextEntry().key);
      }
      
      public Byte previous()
      {
        return Byte.valueOf(previousEntry().key);
      }
      
      public void set(Byte local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Byte local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapEntryIterator
      extends Byte2ByteAVLTreeMap.Submap.SubmapIterator
      implements ObjectListIterator<Byte2ByteMap.Entry>
    {
      SubmapEntryIterator()
      {
        super();
      }
      
      SubmapEntryIterator(byte local_k)
      {
        super(local_k);
      }
      
      public Byte2ByteMap.Entry next()
      {
        return nextEntry();
      }
      
      public Byte2ByteMap.Entry previous()
      {
        return previousEntry();
      }
      
      public void set(Byte2ByteMap.Entry local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Byte2ByteMap.Entry local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapIterator
      extends Byte2ByteAVLTreeMap.TreeIterator
    {
      SubmapIterator()
      {
        super();
        this.next = Byte2ByteAVLTreeMap.Submap.this.firstEntry();
      }
      
      SubmapIterator(byte local_k)
      {
        this();
        if (this.next != null) {
          if ((!Byte2ByteAVLTreeMap.Submap.this.bottom) && (Byte2ByteAVLTreeMap.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!Byte2ByteAVLTreeMap.Submap.this.top) && (Byte2ByteAVLTreeMap.this.compare(local_k, (this.prev = Byte2ByteAVLTreeMap.Submap.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = Byte2ByteAVLTreeMap.this.locateKey(local_k);
            if (Byte2ByteAVLTreeMap.this.compare(this.next.key, local_k) <= 0)
            {
              this.prev = this.next;
              this.next = this.next.next();
            }
            else
            {
              this.prev = this.next.prev();
            }
          }
        }
      }
      
      void updatePrevious()
      {
        this.prev = this.prev.prev();
        if ((!Byte2ByteAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Byte2ByteAVLTreeMap.this.compare(this.prev.key, Byte2ByteAVLTreeMap.Submap.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!Byte2ByteAVLTreeMap.Submap.this.top) && (this.next != null) && (Byte2ByteAVLTreeMap.this.compare(this.next.key, Byte2ByteAVLTreeMap.Submap.this.field_47) >= 0)) {
          this.next = null;
        }
      }
    }
    
    private class KeySet
      extends AbstractByte2ByteSortedMap.KeySet
    {
      private KeySet()
      {
        super();
      }
      
      public ByteBidirectionalIterator iterator()
      {
        return new Byte2ByteAVLTreeMap.Submap.SubmapKeyIterator(Byte2ByteAVLTreeMap.Submap.this);
      }
      
      public ByteBidirectionalIterator iterator(byte from)
      {
        return new Byte2ByteAVLTreeMap.Submap.SubmapKeyIterator(Byte2ByteAVLTreeMap.Submap.this, from);
      }
    }
  }
  
  private final class ValueIterator
    extends Byte2ByteAVLTreeMap.TreeIterator
    implements ByteListIterator
  {
    private ValueIterator()
    {
      super();
    }
    
    public byte nextByte()
    {
      return nextEntry().value;
    }
    
    public byte previousByte()
    {
      return previousEntry().value;
    }
    
    public void set(byte local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(byte local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte next()
    {
      return Byte.valueOf(nextEntry().value);
    }
    
    public Byte previous()
    {
      return Byte.valueOf(previousEntry().value);
    }
    
    public void set(Byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class KeySet
    extends AbstractByte2ByteSortedMap.KeySet
  {
    private KeySet()
    {
      super();
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new Byte2ByteAVLTreeMap.KeyIterator(Byte2ByteAVLTreeMap.this);
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new Byte2ByteAVLTreeMap.KeyIterator(Byte2ByteAVLTreeMap.this, from);
    }
  }
  
  private final class KeyIterator
    extends Byte2ByteAVLTreeMap.TreeIterator
    implements ByteListIterator
  {
    public KeyIterator()
    {
      super();
    }
    
    public KeyIterator(byte local_k)
    {
      super(local_k);
    }
    
    public byte nextByte()
    {
      return nextEntry().key;
    }
    
    public byte previousByte()
    {
      return previousEntry().key;
    }
    
    public void set(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte next()
    {
      return Byte.valueOf(nextEntry().key);
    }
    
    public Byte previous()
    {
      return Byte.valueOf(previousEntry().key);
    }
    
    public void set(Byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Byte2ByteAVLTreeMap.TreeIterator
    implements ObjectListIterator<Byte2ByteMap.Entry>
  {
    EntryIterator()
    {
      super();
    }
    
    EntryIterator(byte local_k)
    {
      super(local_k);
    }
    
    public Byte2ByteMap.Entry next()
    {
      return nextEntry();
    }
    
    public Byte2ByteMap.Entry previous()
    {
      return previousEntry();
    }
    
    public void set(Byte2ByteMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Byte2ByteMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class TreeIterator
  {
    Byte2ByteAVLTreeMap.Entry prev;
    Byte2ByteAVLTreeMap.Entry next;
    Byte2ByteAVLTreeMap.Entry curr;
    int index = 0;
    
    TreeIterator()
    {
      this.next = Byte2ByteAVLTreeMap.this.firstEntry;
    }
    
    TreeIterator(byte local_k)
    {
      if ((this.next = Byte2ByteAVLTreeMap.this.locateKey(local_k)) != null) {
        if (Byte2ByteAVLTreeMap.this.compare(this.next.key, local_k) <= 0)
        {
          this.prev = this.next;
          this.next = this.next.next();
        }
        else
        {
          this.prev = this.next.prev();
        }
      }
    }
    
    public boolean hasNext()
    {
      return this.next != null;
    }
    
    public boolean hasPrevious()
    {
      return this.prev != null;
    }
    
    void updateNext()
    {
      this.next = this.next.next();
    }
    
    Byte2ByteAVLTreeMap.Entry nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = (this.prev = this.next);
      this.index += 1;
      updateNext();
      return this.curr;
    }
    
    void updatePrevious()
    {
      this.prev = this.prev.prev();
    }
    
    Byte2ByteAVLTreeMap.Entry previousEntry()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = (this.next = this.prev);
      this.index -= 1;
      updatePrevious();
      return this.curr;
    }
    
    public int nextIndex()
    {
      return this.index;
    }
    
    public int previousIndex()
    {
      return this.index - 1;
    }
    
    public void remove()
    {
      if (this.curr == null) {
        throw new IllegalStateException();
      }
      if (this.curr == this.prev) {
        this.index -= 1;
      }
      this.next = (this.prev = this.curr);
      updatePrevious();
      updateNext();
      Byte2ByteAVLTreeMap.this.remove(this.curr.key);
      this.curr = null;
    }
    
    public int skip(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasNext())) {
        nextEntry();
      }
      return local_n - local_i - 1;
    }
    
    public int back(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasPrevious())) {
        previousEntry();
      }
      return local_n - local_i - 1;
    }
  }
  
  private static final class Entry
    implements Cloneable, Byte2ByteMap.Entry
  {
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    private static final int BALANCE_MASK = 255;
    byte key;
    byte value;
    Entry left;
    Entry right;
    int info;
    
    Entry() {}
    
    Entry(byte local_k, byte local_v)
    {
      this.key = local_k;
      this.value = local_v;
      this.info = -1073741824;
    }
    
    Entry left()
    {
      return (this.info & 0x40000000) != 0 ? null : this.left;
    }
    
    Entry right()
    {
      return (this.info & 0x80000000) != 0 ? null : this.right;
    }
    
    boolean pred()
    {
      return (this.info & 0x40000000) != 0;
    }
    
    boolean succ()
    {
      return (this.info & 0x80000000) != 0;
    }
    
    void pred(boolean pred)
    {
      if (pred) {
        this.info |= 1073741824;
      } else {
        this.info &= -1073741825;
      }
    }
    
    void succ(boolean succ)
    {
      if (succ) {
        this.info |= -2147483648;
      } else {
        this.info &= 2147483647;
      }
    }
    
    void pred(Entry pred)
    {
      this.info |= 1073741824;
      this.left = pred;
    }
    
    void succ(Entry succ)
    {
      this.info |= -2147483648;
      this.right = succ;
    }
    
    void left(Entry left)
    {
      this.info &= -1073741825;
      this.left = left;
    }
    
    void right(Entry right)
    {
      this.info &= 2147483647;
      this.right = right;
    }
    
    int balance()
    {
      return (byte)this.info;
    }
    
    void balance(int level)
    {
      this.info &= -256;
      this.info |= level & 0xFF;
    }
    
    void incBalance()
    {
      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
    }
    
    protected void decBalance()
    {
      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
    }
    
    Entry next()
    {
      Entry next = this.right;
      if ((this.info & 0x80000000) == 0) {
        while ((next.info & 0x40000000) == 0) {
          next = next.left;
        }
      }
      return next;
    }
    
    Entry prev()
    {
      Entry prev = this.left;
      if ((this.info & 0x40000000) == 0) {
        while ((prev.info & 0x80000000) == 0) {
          prev = prev.right;
        }
      }
      return prev;
    }
    
    public Byte getKey()
    {
      return Byte.valueOf(this.key);
    }
    
    public byte getByteKey()
    {
      return this.key;
    }
    
    public Byte getValue()
    {
      return Byte.valueOf(this.value);
    }
    
    public byte getByteValue()
    {
      return this.value;
    }
    
    public byte setValue(byte value)
    {
      byte oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public Byte setValue(Byte value)
    {
      return Byte.valueOf(setValue(value.byteValue()));
    }
    
    public Entry clone()
    {
      Entry local_c;
      try
      {
        local_c = (Entry)super.clone();
      }
      catch (CloneNotSupportedException cantHappen)
      {
        throw new InternalError();
      }
      local_c.key = this.key;
      local_c.value = this.value;
      local_c.info = this.info;
      return local_c;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, Byte> local_e = (Map.Entry)local_o;
      return (this.key == ((Byte)local_e.getKey()).byteValue()) && (this.value == ((Byte)local_e.getValue()).byteValue());
    }
    
    public int hashCode()
    {
      return this.key ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "=>" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ByteAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */