package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
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

public class Long2ReferenceRBTreeMap<V>
  extends AbstractLong2ReferenceSortedMap<V>
  implements Serializable, Cloneable
{
  protected transient Entry<V> tree;
  protected int count;
  protected transient Entry<V> firstEntry;
  protected transient Entry<V> lastEntry;
  protected volatile transient ObjectSortedSet<Long2ReferenceMap.Entry<V>> entries;
  protected volatile transient LongSortedSet keys;
  protected volatile transient ReferenceCollection<V> values;
  protected transient boolean modified;
  protected Comparator<? super Long> storedComparator;
  protected transient LongComparator actualComparator;
  public static final long serialVersionUID = -7046029254386353129L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  private transient Entry<V>[] nodePath;
  
  public Long2ReferenceRBTreeMap()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    if ((this.storedComparator == null) || ((this.storedComparator instanceof LongComparator))) {
      this.actualComparator = ((LongComparator)this.storedComparator);
    } else {
      this.actualComparator = new LongComparator()
      {
        public int compare(long local_k1, long local_k2)
        {
          return Long2ReferenceRBTreeMap.this.storedComparator.compare(Long.valueOf(local_k1), Long.valueOf(local_k2));
        }
        
        public int compare(Long ok1, Long ok2)
        {
          return Long2ReferenceRBTreeMap.this.storedComparator.compare(ok1, ok2);
        }
      };
    }
  }
  
  public Long2ReferenceRBTreeMap(Comparator<? super Long> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public Long2ReferenceRBTreeMap(Map<? extends Long, ? extends V> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Long2ReferenceRBTreeMap(SortedMap<Long, V> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Long2ReferenceRBTreeMap(Long2ReferenceMap<? extends V> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Long2ReferenceRBTreeMap(Long2ReferenceSortedMap<V> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Long2ReferenceRBTreeMap(long[] local_k, V[] local_v, Comparator<? super Long> local_c)
  {
    this(local_c);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Long2ReferenceRBTreeMap(long[] local_k, V[] local_v)
  {
    this(local_k, local_v, null);
  }
  
  final int compare(long local_k1, long local_k2)
  {
    return this.actualComparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.actualComparator.compare(local_k1, local_k2);
  }
  
  final Entry<V> findKey(long local_k)
  {
    int cmp;
    for (Entry<V> local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry<V> locateKey(long local_k)
  {
    Entry<V> local_e = this.tree;
    Entry<V> last = this.tree;
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
    this.dirPath = new boolean[64];
    this.nodePath = new Entry[64];
  }
  
  public V put(long local_k, V local_v)
  {
    this.modified = false;
    int maxDepth = 0;
    if (this.tree == null)
    {
      this.count += 1;
      this.tree = (this.lastEntry = this.firstEntry = new Entry(local_k, local_v));
    }
    else
    {
      Entry<V> local_p = this.tree;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          V oldValue = local_p.value;
          local_p.value = local_v;
          while (local_i-- != 0) {
            this.nodePath[local_i] = null;
          }
          return oldValue;
        }
        this.nodePath[local_i] = local_p;
        if ((this.dirPath[(local_i++)] = cmp > 0 ? 1 : 0) != 0)
        {
          if (local_p.succ())
          {
            this.count += 1;
            Entry<V> local_e = new Entry(local_k, local_v);
            if (local_p.right == null) {
              this.lastEntry = local_e;
            }
            local_e.left = local_p;
            local_e.right = local_p.right;
            local_p.right(local_e);
            break;
          }
          local_p = local_p.right;
        }
        else
        {
          if (local_p.pred())
          {
            this.count += 1;
            Entry<V> local_e = new Entry(local_k, local_v);
            if (local_p.left == null) {
              this.firstEntry = local_e;
            }
            local_e.right = local_p;
            local_e.left = local_p.left;
            local_p.left(local_e);
            break;
          }
          local_p = local_p.left;
        }
      }
      Entry<V> local_e;
      this.modified = true;
      maxDepth = local_i--;
      while ((local_i > 0) && (!this.nodePath[local_i].black())) {
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<V> oldValue = this.nodePath[(local_i - 1)].right;
          if ((!this.nodePath[(local_i - 1)].succ()) && (!oldValue.black()))
          {
            this.nodePath[local_i].black(true);
            oldValue.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] == 0)
            {
              oldValue = this.nodePath[local_i];
            }
            else
            {
              Entry<V> local_x = this.nodePath[local_i];
              oldValue = local_x.right;
              local_x.right = oldValue.left;
              oldValue.left = local_x;
              this.nodePath[(local_i - 1)].left = oldValue;
              if (oldValue.pred())
              {
                oldValue.pred(false);
                local_x.succ(oldValue);
              }
            }
            Entry<V> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            oldValue.black(true);
            local_x.left = oldValue.right;
            oldValue.right = local_x;
            if (local_i < 2) {
              this.tree = oldValue;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = oldValue;
            } else {
              this.nodePath[(local_i - 2)].left = oldValue;
            }
            if (!oldValue.succ()) {
              break;
            }
            oldValue.succ(false);
            local_x.pred(oldValue);
            break;
          }
        }
        else
        {
          Entry<V> oldValue = this.nodePath[(local_i - 1)].left;
          if ((!this.nodePath[(local_i - 1)].pred()) && (!oldValue.black()))
          {
            this.nodePath[local_i].black(true);
            oldValue.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] != 0)
            {
              oldValue = this.nodePath[local_i];
            }
            else
            {
              Entry<V> local_x = this.nodePath[local_i];
              oldValue = local_x.left;
              local_x.left = oldValue.right;
              oldValue.right = local_x;
              this.nodePath[(local_i - 1)].right = oldValue;
              if (oldValue.succ())
              {
                oldValue.succ(false);
                local_x.pred(oldValue);
              }
            }
            Entry<V> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            oldValue.black(true);
            local_x.right = oldValue.left;
            oldValue.left = local_x;
            if (local_i < 2) {
              this.tree = oldValue;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = oldValue;
            } else {
              this.nodePath[(local_i - 2)].left = oldValue;
            }
            if (!oldValue.pred()) {
              break;
            }
            oldValue.pred(false);
            local_x.succ(oldValue);
            break;
          }
        }
      }
    }
    this.tree.black(true);
    while (maxDepth-- != 0) {
      this.nodePath[maxDepth] = null;
    }
    return this.defRetValue;
  }
  
  public V remove(long local_k)
  {
    this.modified = false;
    if (this.tree == null) {
      return this.defRetValue;
    }
    Entry<V> local_p = this.tree;
    int local_i = 0;
    long local_kk = local_k;
    int cmp;
    while ((cmp = compare(local_kk, local_p.key)) != 0)
    {
      this.dirPath[local_i] = (cmp > 0 ? 1 : false);
      this.nodePath[local_i] = local_p;
      if (this.dirPath[(local_i++)] != 0)
      {
        if ((local_p = local_p.right()) == null)
        {
          while (local_i-- != 0) {
            this.nodePath[local_i] = null;
          }
          return this.defRetValue;
        }
      }
      else if ((local_p = local_p.left()) == null)
      {
        while (local_i-- != 0) {
          this.nodePath[local_i] = null;
        }
        return this.defRetValue;
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
        if (local_i == 0) {
          this.tree = local_p.left;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].succ(local_p.right);
        } else {
          this.nodePath[(local_i - 1)].pred(local_p.left);
        }
      }
      else
      {
        local_p.prev().right = local_p.right;
        if (local_i == 0) {
          this.tree = local_p.left;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].right = local_p.left;
        } else {
          this.nodePath[(local_i - 1)].left = local_p.left;
        }
      }
    }
    else
    {
      Entry<V> local_r = local_p.right;
      if (local_r.pred())
      {
        local_r.left = local_p.left;
        local_r.pred(local_p.pred());
        if (!local_r.pred()) {
          local_r.prev().right = local_r;
        }
        if (local_i == 0) {
          this.tree = local_r;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].right = local_r;
        } else {
          this.nodePath[(local_i - 1)].left = local_r;
        }
        boolean color = local_r.black();
        local_r.black(local_p.black());
        local_p.black(color);
        this.dirPath[local_i] = true;
        this.nodePath[(local_i++)] = local_r;
      }
      else
      {
        int local_j = local_i++;
        Entry<V> local_s;
        for (;;)
        {
          this.dirPath[local_i] = false;
          this.nodePath[(local_i++)] = local_r;
          local_s = local_r.left;
          if (local_s.pred()) {
            break;
          }
          local_r = local_s;
        }
        this.dirPath[local_j] = true;
        this.nodePath[local_j] = local_s;
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
        local_s.right(local_p.right);
        boolean color = local_s.black();
        local_s.black(local_p.black());
        local_p.black(color);
        if (local_j == 0) {
          this.tree = local_s;
        } else if (this.dirPath[(local_j - 1)] != 0) {
          this.nodePath[(local_j - 1)].right = local_s;
        } else {
          this.nodePath[(local_j - 1)].left = local_s;
        }
      }
    }
    int color = local_i;
    if (local_p.black())
    {
      while (local_i > 0)
      {
        if (((this.dirPath[(local_i - 1)] != 0) && (!this.nodePath[(local_i - 1)].succ())) || ((this.dirPath[(local_i - 1)] == 0) && (!this.nodePath[(local_i - 1)].pred())))
        {
          Entry<V> local_r = this.dirPath[(local_i - 1)] != 0 ? this.nodePath[(local_i - 1)].right : this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            break;
          }
        }
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<V> local_r = this.nodePath[(local_i - 1)].right;
          if (!local_r.black())
          {
            local_r.black(true);
            this.nodePath[(local_i - 1)].black(false);
            this.nodePath[(local_i - 1)].right = local_r.left;
            local_r.left = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            this.nodePath[local_i] = this.nodePath[(local_i - 1)];
            this.dirPath[local_i] = false;
            this.nodePath[(local_i - 1)] = local_r;
            if (color == local_i++) {
              color++;
            }
            local_r = this.nodePath[(local_i - 1)].right;
          }
          if (((local_r.pred()) || (local_r.left.black())) && ((local_r.succ()) || (local_r.right.black())))
          {
            local_r.black(false);
          }
          else
          {
            if ((local_r.succ()) || (local_r.right.black()))
            {
              Entry<V> local_s = local_r.left;
              local_s.black(true);
              local_r.black(false);
              local_r.left = local_s.right;
              local_s.right = local_r;
              local_r = this.nodePath[(local_i - 1)].right = local_s;
              if (local_r.succ())
              {
                local_r.succ(false);
                local_r.right.pred(local_r);
              }
            }
            local_r.black(this.nodePath[(local_i - 1)].black());
            this.nodePath[(local_i - 1)].black(true);
            local_r.right.black(true);
            this.nodePath[(local_i - 1)].right = local_r.left;
            local_r.left = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            if (!local_r.pred()) {
              break;
            }
            local_r.pred(false);
            this.nodePath[(local_i - 1)].succ(local_r);
            break;
          }
        }
        else
        {
          Entry<V> local_r = this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            this.nodePath[(local_i - 1)].black(false);
            this.nodePath[(local_i - 1)].left = local_r.right;
            local_r.right = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            this.nodePath[local_i] = this.nodePath[(local_i - 1)];
            this.dirPath[local_i] = true;
            this.nodePath[(local_i - 1)] = local_r;
            if (color == local_i++) {
              color++;
            }
            local_r = this.nodePath[(local_i - 1)].left;
          }
          if (((local_r.pred()) || (local_r.left.black())) && ((local_r.succ()) || (local_r.right.black())))
          {
            local_r.black(false);
          }
          else
          {
            if ((local_r.pred()) || (local_r.left.black()))
            {
              Entry<V> local_s = local_r.right;
              local_s.black(true);
              local_r.black(false);
              local_r.right = local_s.left;
              local_s.left = local_r;
              local_r = this.nodePath[(local_i - 1)].left = local_s;
              if (local_r.pred())
              {
                local_r.pred(false);
                local_r.left.succ(local_r);
              }
            }
            local_r.black(this.nodePath[(local_i - 1)].black());
            this.nodePath[(local_i - 1)].black(true);
            local_r.left.black(true);
            this.nodePath[(local_i - 1)].left = local_r.right;
            local_r.right = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            if (!local_r.succ()) {
              break;
            }
            local_r.succ(false);
            this.nodePath[(local_i - 1)].pred(local_r);
            break;
          }
        }
        local_i--;
      }
      if (this.tree != null) {
        this.tree.black(true);
      }
    }
    this.modified = true;
    this.count -= 1;
    while (color-- != 0) {
      this.nodePath[color] = null;
    }
    return local_p.value;
  }
  
  public V put(Long local_ok, V local_ov)
  {
    V oldValue = put(local_ok.longValue(), local_ov);
    return this.modified ? this.defRetValue : oldValue;
  }
  
  public V remove(Object local_ok)
  {
    V oldValue = remove(((Long)local_ok).longValue());
    return this.modified ? oldValue : this.defRetValue;
  }
  
  public boolean containsValue(Object local_v)
  {
    Long2ReferenceRBTreeMap<V>.ValueIterator local_i = new ValueIterator(null);
    int local_j = this.count;
    while (local_j-- != 0)
    {
      Object local_ev = local_i.next();
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
  
  public boolean containsKey(long local_k)
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
  
  public V get(long local_k)
  {
    Entry<V> local_e = findKey(local_k);
    return local_e == null ? this.defRetValue : local_e.value;
  }
  
  public long firstLongKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public long lastLongKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet()
  {
    if (this.entries == null) {
      this.entries = new AbstractObjectSortedSet()
      {
        final Comparator<? super Long2ReferenceMap.Entry<V>> comparator = new Comparator()
        {
          public int compare(Long2ReferenceMap.Entry<V> local_x, Long2ReferenceMap.Entry<V> local_y)
          {
            return Long2ReferenceRBTreeMap.this.storedComparator.compare(local_x.getKey(), local_y.getKey());
          }
        };
        
        public Comparator<? super Long2ReferenceMap.Entry<V>> comparator()
        {
          return this.comparator;
        }
        
        public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator()
        {
          return new Long2ReferenceRBTreeMap.EntryIterator(Long2ReferenceRBTreeMap.this);
        }
        
        public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator(Long2ReferenceMap.Entry<V> from)
        {
          return new Long2ReferenceRBTreeMap.EntryIterator(Long2ReferenceRBTreeMap.this, ((Long)from.getKey()).longValue());
        }
        
        public boolean contains(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Long, V> local_e = (Map.Entry)local_o;
          Long2ReferenceRBTreeMap.Entry<V> local_f = Long2ReferenceRBTreeMap.this.findKey(((Long)local_e.getKey()).longValue());
          return local_e.equals(local_f);
        }
        
        public boolean remove(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Long, V> local_e = (Map.Entry)local_o;
          Long2ReferenceRBTreeMap.Entry<V> local_f = Long2ReferenceRBTreeMap.this.findKey(((Long)local_e.getKey()).longValue());
          if (local_f != null) {
            Long2ReferenceRBTreeMap.this.remove(local_f.key);
          }
          return local_f != null;
        }
        
        public int size()
        {
          return Long2ReferenceRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Long2ReferenceRBTreeMap.this.clear();
        }
        
        public Long2ReferenceMap.Entry<V> first()
        {
          return Long2ReferenceRBTreeMap.this.firstEntry;
        }
        
        public Long2ReferenceMap.Entry<V> last()
        {
          return Long2ReferenceRBTreeMap.this.lastEntry;
        }
        
        public ObjectSortedSet<Long2ReferenceMap.Entry<V>> subSet(Long2ReferenceMap.Entry<V> from, Long2ReferenceMap.Entry<V> local_to)
        {
          return Long2ReferenceRBTreeMap.this.subMap((Long)from.getKey(), (Long)local_to.getKey()).long2ReferenceEntrySet();
        }
        
        public ObjectSortedSet<Long2ReferenceMap.Entry<V>> headSet(Long2ReferenceMap.Entry<V> local_to)
        {
          return Long2ReferenceRBTreeMap.this.headMap((Long)local_to.getKey()).long2ReferenceEntrySet();
        }
        
        public ObjectSortedSet<Long2ReferenceMap.Entry<V>> tailSet(Long2ReferenceMap.Entry<V> from)
        {
          return Long2ReferenceRBTreeMap.this.tailMap((Long)from.getKey()).long2ReferenceEntrySet();
        }
      };
    }
    return this.entries;
  }
  
  public LongSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ReferenceCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractReferenceCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Long2ReferenceRBTreeMap.ValueIterator(Long2ReferenceRBTreeMap.this, null);
        }
        
        public boolean contains(Object local_k)
        {
          return Long2ReferenceRBTreeMap.this.containsValue(local_k);
        }
        
        public int size()
        {
          return Long2ReferenceRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Long2ReferenceRBTreeMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  public LongComparator comparator()
  {
    return this.actualComparator;
  }
  
  public Long2ReferenceSortedMap<V> headMap(long local_to)
  {
    return new Submap(0L, true, local_to, false);
  }
  
  public Long2ReferenceSortedMap<V> tailMap(long from)
  {
    return new Submap(from, false, 0L, true);
  }
  
  public Long2ReferenceSortedMap<V> subMap(long from, long local_to)
  {
    return new Submap(from, false, local_to, false);
  }
  
  public Long2ReferenceRBTreeMap<V> clone()
  {
    Long2ReferenceRBTreeMap<V> local_c;
    try
    {
      local_c = (Long2ReferenceRBTreeMap)super.clone();
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
      Entry<V> local_rp = new Entry();
      Entry<V> local_rq = new Entry();
      Entry<V> local_p = local_rp;
      local_rp.left(this.tree);
      Entry<V> local_q = local_rq;
      local_rq.pred(null);
      for (;;)
      {
        if (!local_p.pred())
        {
          Entry<V> cantHappen = local_p.left.clone();
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
          Entry<V> cantHappen = local_p.right.clone();
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
    Long2ReferenceRBTreeMap<V>.EntryIterator local_i = new EntryIterator();
    local_s.defaultWriteObject();
    while (local_n-- != 0)
    {
      Entry<V> local_e = local_i.nextEntry();
      local_s.writeLong(local_e.key);
      local_s.writeObject(local_e.value);
    }
  }
  
  private Entry<V> readTree(ObjectInputStream local_s, int local_n, Entry<V> pred, Entry<V> succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry<V> top = new Entry(local_s.readLong(), local_s.readObject());
      top.pred(pred);
      top.succ(succ);
      top.black(true);
      return top;
    }
    if (local_n == 2)
    {
      Entry<V> top = new Entry(local_s.readLong(), local_s.readObject());
      top.black(true);
      top.right(new Entry(local_s.readLong(), local_s.readObject()));
      top.right.pred(top);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry<V> top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readLong();
    top.value = local_s.readObject();
    top.black(true);
    top.right(readTree(local_s, top, top, succ));
    if (local_n + 2 == (local_n + 2 & -(local_n + 2))) {
      top.right.black(false);
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
      for (Entry<V> local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private void checkNodePath() {}
  
  private int checkTree(Entry<V> local_e, int local_d, int local_D)
  {
    return 0;
  }
  
  private final class Submap
    extends AbstractLong2ReferenceSortedMap<V>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    long from;
    long field_47;
    boolean bottom;
    boolean top;
    protected volatile transient ObjectSortedSet<Long2ReferenceMap.Entry<V>> entries;
    protected volatile transient LongSortedSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    public Submap(long from, boolean bottom, long local_to, boolean top)
    {
      if ((!bottom) && (!top) && (Long2ReferenceRBTreeMap.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start key (" + from + ") is larger than end key (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_47 = local_to;
      this.top = top;
      this.defRetValue = Long2ReferenceRBTreeMap.this.defRetValue;
    }
    
    public void clear()
    {
      Long2ReferenceRBTreeMap<V>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        local_i.nextEntry();
        local_i.remove();
      }
    }
    
    final boolean in1(long local_k)
    {
      return ((this.bottom) || (Long2ReferenceRBTreeMap.this.compare(local_k, this.from) >= 0)) && ((this.top) || (Long2ReferenceRBTreeMap.this.compare(local_k, this.field_47) < 0));
    }
    
    public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = new AbstractObjectSortedSet()
        {
          public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator()
          {
            return new Long2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Long2ReferenceRBTreeMap.Submap.this);
          }
          
          public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator(Long2ReferenceMap.Entry<V> from)
          {
            return new Long2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Long2ReferenceRBTreeMap.Submap.this, ((Long)from.getKey()).longValue());
          }
          
          public Comparator<? super Long2ReferenceMap.Entry<V>> comparator()
          {
            return Long2ReferenceRBTreeMap.this.long2ReferenceEntrySet().comparator();
          }
          
          public boolean contains(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Long, V> local_e = (Map.Entry)local_o;
            Long2ReferenceRBTreeMap.Entry<V> local_f = Long2ReferenceRBTreeMap.this.findKey(((Long)local_e.getKey()).longValue());
            return (local_f != null) && (Long2ReferenceRBTreeMap.Submap.this.in1(local_f.key)) && (local_e.equals(local_f));
          }
          
          public boolean remove(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Long, V> local_e = (Map.Entry)local_o;
            Long2ReferenceRBTreeMap.Entry<V> local_f = Long2ReferenceRBTreeMap.this.findKey(((Long)local_e.getKey()).longValue());
            if ((local_f != null) && (Long2ReferenceRBTreeMap.Submap.this.in1(local_f.key))) {
              Long2ReferenceRBTreeMap.Submap.this.remove(local_f.key);
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
            return !new Long2ReferenceRBTreeMap.Submap.SubmapIterator(Long2ReferenceRBTreeMap.Submap.this).hasNext();
          }
          
          public void clear()
          {
            Long2ReferenceRBTreeMap.Submap.this.clear();
          }
          
          public Long2ReferenceMap.Entry<V> first()
          {
            return Long2ReferenceRBTreeMap.Submap.this.firstEntry();
          }
          
          public Long2ReferenceMap.Entry<V> last()
          {
            return Long2ReferenceRBTreeMap.Submap.this.lastEntry();
          }
          
          public ObjectSortedSet<Long2ReferenceMap.Entry<V>> subSet(Long2ReferenceMap.Entry<V> from, Long2ReferenceMap.Entry<V> local_to)
          {
            return Long2ReferenceRBTreeMap.Submap.this.subMap((Long)from.getKey(), (Long)local_to.getKey()).long2ReferenceEntrySet();
          }
          
          public ObjectSortedSet<Long2ReferenceMap.Entry<V>> headSet(Long2ReferenceMap.Entry<V> local_to)
          {
            return Long2ReferenceRBTreeMap.Submap.this.headMap((Long)local_to.getKey()).long2ReferenceEntrySet();
          }
          
          public ObjectSortedSet<Long2ReferenceMap.Entry<V>> tailSet(Long2ReferenceMap.Entry<V> from)
          {
            return Long2ReferenceRBTreeMap.Submap.this.tailMap((Long)from.getKey()).long2ReferenceEntrySet();
          }
        };
      }
      return this.entries;
    }
    
    public LongSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = new KeySet(null);
      }
      return this.keys;
    }
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        this.values = new AbstractReferenceCollection()
        {
          public ObjectIterator<V> iterator()
          {
            return new Long2ReferenceRBTreeMap.Submap.SubmapValueIterator(Long2ReferenceRBTreeMap.Submap.this, null);
          }
          
          public boolean contains(Object local_k)
          {
            return Long2ReferenceRBTreeMap.Submap.this.containsValue(local_k);
          }
          
          public int size()
          {
            return Long2ReferenceRBTreeMap.Submap.this.size();
          }
          
          public void clear()
          {
            Long2ReferenceRBTreeMap.Submap.this.clear();
          }
        };
      }
      return this.values;
    }
    
    public boolean containsKey(long local_k)
    {
      return (in1(local_k)) && (Long2ReferenceRBTreeMap.this.containsKey(local_k));
    }
    
    public boolean containsValue(Object local_v)
    {
      Long2ReferenceRBTreeMap<V>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        Object local_ev = local_i.nextEntry().value;
        if (local_ev == local_v) {
          return true;
        }
      }
      return false;
    }
    
    public V get(long local_k)
    {
      long local_kk = local_k;
      Long2ReferenceRBTreeMap.Entry<V> local_e;
      return (in1(local_kk)) && ((local_e = Long2ReferenceRBTreeMap.this.findKey(local_kk)) != null) ? local_e.value : this.defRetValue;
    }
    
    public V put(long local_k, V local_v)
    {
      Long2ReferenceRBTreeMap.this.modified = false;
      if (!in1(local_k)) {
        throw new IllegalArgumentException("Key (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_47)) + ")");
      }
      V oldValue = Long2ReferenceRBTreeMap.this.put(local_k, local_v);
      return Long2ReferenceRBTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public V put(Long local_ok, V local_ov)
    {
      V oldValue = put(local_ok.longValue(), local_ov);
      return Long2ReferenceRBTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public V remove(long local_k)
    {
      Long2ReferenceRBTreeMap.this.modified = false;
      if (!in1(local_k)) {
        return this.defRetValue;
      }
      V oldValue = Long2ReferenceRBTreeMap.this.remove(local_k);
      return Long2ReferenceRBTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public V remove(Object local_ok)
    {
      V oldValue = remove(((Long)local_ok).longValue());
      return Long2ReferenceRBTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public int size()
    {
      Long2ReferenceRBTreeMap<V>.Submap.SubmapIterator local_i = new SubmapIterator();
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
    
    public LongComparator comparator()
    {
      return Long2ReferenceRBTreeMap.this.actualComparator;
    }
    
    public Long2ReferenceSortedMap<V> headMap(long local_to)
    {
      if (this.top) {
        return new Submap(Long2ReferenceRBTreeMap.this, this.from, this.bottom, local_to, false);
      }
      return Long2ReferenceRBTreeMap.this.compare(local_to, this.field_47) < 0 ? new Submap(Long2ReferenceRBTreeMap.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public Long2ReferenceSortedMap<V> tailMap(long from)
    {
      if (this.bottom) {
        return new Submap(Long2ReferenceRBTreeMap.this, from, false, this.field_47, this.top);
      }
      return Long2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Long2ReferenceRBTreeMap.this, from, false, this.field_47, this.top) : this;
    }
    
    public Long2ReferenceSortedMap<V> subMap(long from, long local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Submap(Long2ReferenceRBTreeMap.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = Long2ReferenceRBTreeMap.this.compare(local_to, this.field_47) < 0 ? local_to : this.field_47;
      }
      if (!this.bottom) {
        from = Long2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_47)) {
        return this;
      }
      return new Submap(Long2ReferenceRBTreeMap.this, from, false, local_to, false);
    }
    
    public Long2ReferenceRBTreeMap.Entry<V> firstEntry()
    {
      if (Long2ReferenceRBTreeMap.this.tree == null) {
        return null;
      }
      Long2ReferenceRBTreeMap.Entry<V> local_e;
      Long2ReferenceRBTreeMap.Entry<V> local_e;
      if (this.bottom)
      {
        local_e = Long2ReferenceRBTreeMap.this.firstEntry;
      }
      else
      {
        local_e = Long2ReferenceRBTreeMap.this.locateKey(this.from);
        if (Long2ReferenceRBTreeMap.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (Long2ReferenceRBTreeMap.this.compare(local_e.key, this.field_47) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public Long2ReferenceRBTreeMap.Entry<V> lastEntry()
    {
      if (Long2ReferenceRBTreeMap.this.tree == null) {
        return null;
      }
      Long2ReferenceRBTreeMap.Entry<V> local_e;
      Long2ReferenceRBTreeMap.Entry<V> local_e;
      if (this.top)
      {
        local_e = Long2ReferenceRBTreeMap.this.lastEntry;
      }
      else
      {
        local_e = Long2ReferenceRBTreeMap.this.locateKey(this.field_47);
        if (Long2ReferenceRBTreeMap.this.compare(local_e.key, this.field_47) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (Long2ReferenceRBTreeMap.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public long firstLongKey()
    {
      Long2ReferenceRBTreeMap.Entry<V> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public long lastLongKey()
    {
      Long2ReferenceRBTreeMap.Entry<V> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public Long firstKey()
    {
      Long2ReferenceRBTreeMap.Entry<V> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    public Long lastKey()
    {
      Long2ReferenceRBTreeMap.Entry<V> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    private final class SubmapValueIterator
      extends Long2ReferenceRBTreeMap<V>.Submap.SubmapIterator
      implements ObjectListIterator<V>
    {
      private SubmapValueIterator()
      {
        super();
      }
      
      public V next()
      {
        return nextEntry().value;
      }
      
      public V previous()
      {
        return previousEntry().value;
      }
      
      public void set(V local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(V local_v)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private final class SubmapKeyIterator
      extends Long2ReferenceRBTreeMap.Submap.SubmapIterator
      implements LongListIterator
    {
      public SubmapKeyIterator()
      {
        super();
      }
      
      public SubmapKeyIterator(long from)
      {
        super(from);
      }
      
      public long nextLong()
      {
        return nextEntry().key;
      }
      
      public long previousLong()
      {
        return previousEntry().key;
      }
      
      public void set(long local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(long local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public Long next()
      {
        return Long.valueOf(nextEntry().key);
      }
      
      public Long previous()
      {
        return Long.valueOf(previousEntry().key);
      }
      
      public void set(Long local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Long local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapEntryIterator
      extends Long2ReferenceRBTreeMap<V>.Submap.SubmapIterator
      implements ObjectListIterator<Long2ReferenceMap.Entry<V>>
    {
      SubmapEntryIterator()
      {
        super();
      }
      
      SubmapEntryIterator(long local_k)
      {
        super(local_k);
      }
      
      public Long2ReferenceMap.Entry<V> next()
      {
        return nextEntry();
      }
      
      public Long2ReferenceMap.Entry<V> previous()
      {
        return previousEntry();
      }
      
      public void set(Long2ReferenceMap.Entry<V> local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Long2ReferenceMap.Entry<V> local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapIterator
      extends Long2ReferenceRBTreeMap.TreeIterator
    {
      SubmapIterator()
      {
        super();
        this.next = Long2ReferenceRBTreeMap.Submap.this.firstEntry();
      }
      
      SubmapIterator(long local_k)
      {
        this();
        if (this.next != null) {
          if ((!Long2ReferenceRBTreeMap.Submap.this.bottom) && (Long2ReferenceRBTreeMap.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!Long2ReferenceRBTreeMap.Submap.this.top) && (Long2ReferenceRBTreeMap.this.compare(local_k, (this.prev = Long2ReferenceRBTreeMap.Submap.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = Long2ReferenceRBTreeMap.this.locateKey(local_k);
            if (Long2ReferenceRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
        if ((!Long2ReferenceRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Long2ReferenceRBTreeMap.this.compare(this.prev.key, Long2ReferenceRBTreeMap.Submap.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!Long2ReferenceRBTreeMap.Submap.this.top) && (this.next != null) && (Long2ReferenceRBTreeMap.this.compare(this.next.key, Long2ReferenceRBTreeMap.Submap.this.field_47) >= 0)) {
          this.next = null;
        }
      }
    }
    
    private class KeySet
      extends AbstractLong2ReferenceSortedMap.KeySet
    {
      private KeySet()
      {
        super();
      }
      
      public LongBidirectionalIterator iterator()
      {
        return new Long2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Long2ReferenceRBTreeMap.Submap.this);
      }
      
      public LongBidirectionalIterator iterator(long from)
      {
        return new Long2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Long2ReferenceRBTreeMap.Submap.this, from);
      }
    }
  }
  
  private final class ValueIterator
    extends Long2ReferenceRBTreeMap<V>.TreeIterator
    implements ObjectListIterator<V>
  {
    private ValueIterator()
    {
      super();
    }
    
    public V next()
    {
      return nextEntry().value;
    }
    
    public V previous()
    {
      return previousEntry().value;
    }
    
    public void set(V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(V local_v)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class KeySet
    extends AbstractLong2ReferenceSortedMap.KeySet
  {
    private KeySet()
    {
      super();
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new Long2ReferenceRBTreeMap.KeyIterator(Long2ReferenceRBTreeMap.this);
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new Long2ReferenceRBTreeMap.KeyIterator(Long2ReferenceRBTreeMap.this, from);
    }
  }
  
  private final class KeyIterator
    extends Long2ReferenceRBTreeMap.TreeIterator
    implements LongListIterator
  {
    public KeyIterator()
    {
      super();
    }
    
    public KeyIterator(long local_k)
    {
      super(local_k);
    }
    
    public long nextLong()
    {
      return nextEntry().key;
    }
    
    public long previousLong()
    {
      return previousEntry().key;
    }
    
    public void set(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long next()
    {
      return Long.valueOf(nextEntry().key);
    }
    
    public Long previous()
    {
      return Long.valueOf(previousEntry().key);
    }
    
    public void set(Long local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Long local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Long2ReferenceRBTreeMap<V>.TreeIterator
    implements ObjectListIterator<Long2ReferenceMap.Entry<V>>
  {
    EntryIterator()
    {
      super();
    }
    
    EntryIterator(long local_k)
    {
      super(local_k);
    }
    
    public Long2ReferenceMap.Entry<V> next()
    {
      return nextEntry();
    }
    
    public Long2ReferenceMap.Entry<V> previous()
    {
      return previousEntry();
    }
    
    public void set(Long2ReferenceMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Long2ReferenceMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class TreeIterator
  {
    Long2ReferenceRBTreeMap.Entry<V> prev;
    Long2ReferenceRBTreeMap.Entry<V> next;
    Long2ReferenceRBTreeMap.Entry<V> curr;
    int index = 0;
    
    TreeIterator()
    {
      this.next = Long2ReferenceRBTreeMap.this.firstEntry;
    }
    
    TreeIterator(long local_k)
    {
      if ((this.next = Long2ReferenceRBTreeMap.this.locateKey(local_k)) != null) {
        if (Long2ReferenceRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
    
    Long2ReferenceRBTreeMap.Entry<V> nextEntry()
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
    
    Long2ReferenceRBTreeMap.Entry<V> previousEntry()
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
      Long2ReferenceRBTreeMap.this.remove(this.curr.key);
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
  
  private static final class Entry<V>
    implements Cloneable, Long2ReferenceMap.Entry<V>
  {
    private static final int BLACK_MASK = 1;
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    long key;
    V value;
    Entry<V> left;
    Entry<V> right;
    int info;
    
    Entry() {}
    
    Entry(long local_k, V local_v)
    {
      this.key = local_k;
      this.value = local_v;
      this.info = -1073741824;
    }
    
    Entry<V> left()
    {
      return (this.info & 0x40000000) != 0 ? null : this.left;
    }
    
    Entry<V> right()
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
    
    void pred(Entry<V> pred)
    {
      this.info |= 1073741824;
      this.left = pred;
    }
    
    void succ(Entry<V> succ)
    {
      this.info |= -2147483648;
      this.right = succ;
    }
    
    void left(Entry<V> left)
    {
      this.info &= -1073741825;
      this.left = left;
    }
    
    void right(Entry<V> right)
    {
      this.info &= 2147483647;
      this.right = right;
    }
    
    boolean black()
    {
      return (this.info & 0x1) != 0;
    }
    
    void black(boolean black)
    {
      if (black) {
        this.info |= 1;
      } else {
        this.info &= -2;
      }
    }
    
    Entry<V> next()
    {
      Entry<V> next = this.right;
      if ((this.info & 0x80000000) == 0) {
        while ((next.info & 0x40000000) == 0) {
          next = next.left;
        }
      }
      return next;
    }
    
    Entry<V> prev()
    {
      Entry<V> prev = this.left;
      if ((this.info & 0x40000000) == 0) {
        while ((prev.info & 0x80000000) == 0) {
          prev = prev.right;
        }
      }
      return prev;
    }
    
    public Long getKey()
    {
      return Long.valueOf(this.key);
    }
    
    public long getLongKey()
    {
      return this.key;
    }
    
    public V getValue()
    {
      return this.value;
    }
    
    public V setValue(V value)
    {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public Entry<V> clone()
    {
      Entry<V> local_c;
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
      Map.Entry<Long, V> local_e = (Map.Entry)local_o;
      return (this.key == ((Long)local_e.getKey()).longValue()) && (this.value == local_e.getValue());
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(this.key) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
    }
    
    public String toString()
    {
      return this.key + "=>" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ReferenceRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */