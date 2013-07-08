package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongListIterator;
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

public class Char2LongRBTreeMap
  extends AbstractChar2LongSortedMap
  implements Serializable, Cloneable
{
  protected transient Entry tree;
  protected int count;
  protected transient Entry firstEntry;
  protected transient Entry lastEntry;
  protected volatile transient ObjectSortedSet<Char2LongMap.Entry> entries;
  protected volatile transient CharSortedSet keys;
  protected volatile transient LongCollection values;
  protected transient boolean modified;
  protected Comparator<? super Character> storedComparator;
  protected transient CharComparator actualComparator;
  public static final long serialVersionUID = -7046029254386353129L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  private transient Entry[] nodePath;
  
  public Char2LongRBTreeMap()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    if ((this.storedComparator == null) || ((this.storedComparator instanceof CharComparator))) {
      this.actualComparator = ((CharComparator)this.storedComparator);
    } else {
      this.actualComparator = new CharComparator()
      {
        public int compare(char local_k1, char local_k2)
        {
          return Char2LongRBTreeMap.this.storedComparator.compare(Character.valueOf(local_k1), Character.valueOf(local_k2));
        }
        
        public int compare(Character ok1, Character ok2)
        {
          return Char2LongRBTreeMap.this.storedComparator.compare(ok1, ok2);
        }
      };
    }
  }
  
  public Char2LongRBTreeMap(Comparator<? super Character> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public Char2LongRBTreeMap(Map<? extends Character, ? extends Long> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Char2LongRBTreeMap(SortedMap<Character, Long> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Char2LongRBTreeMap(Char2LongMap local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Char2LongRBTreeMap(Char2LongSortedMap local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Char2LongRBTreeMap(char[] local_k, long[] local_v, Comparator<? super Character> local_c)
  {
    this(local_c);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Char2LongRBTreeMap(char[] local_k, long[] local_v)
  {
    this(local_k, local_v, null);
  }
  
  final int compare(char local_k1, char local_k2)
  {
    return this.actualComparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.actualComparator.compare(local_k1, local_k2);
  }
  
  final Entry findKey(char local_k)
  {
    int cmp;
    for (Entry local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry locateKey(char local_k)
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
    this.dirPath = new boolean[64];
    this.nodePath = new Entry[64];
  }
  
  public long put(char local_k, long local_v)
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
      Entry local_p = this.tree;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          long oldValue = local_p.value;
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
            Entry local_e = new Entry(local_k, local_v);
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
            Entry local_e = new Entry(local_k, local_v);
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
      Entry local_e;
      this.modified = true;
      maxDepth = local_i--;
      while ((local_i > 0) && (!this.nodePath[local_i].black())) {
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry oldValue = this.nodePath[(local_i - 1)].right;
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
              Entry local_x = this.nodePath[local_i];
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
            Entry local_x = this.nodePath[(local_i - 1)];
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
          Entry oldValue = this.nodePath[(local_i - 1)].left;
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
              Entry local_x = this.nodePath[local_i];
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
            Entry local_x = this.nodePath[(local_i - 1)];
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
  
  public long remove(char local_k)
  {
    this.modified = false;
    if (this.tree == null) {
      return this.defRetValue;
    }
    Entry local_p = this.tree;
    int local_i = 0;
    char local_kk = local_k;
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
      Entry local_r = local_p.right;
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
        Entry local_s;
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
          Entry local_r = this.dirPath[(local_i - 1)] != 0 ? this.nodePath[(local_i - 1)].right : this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            break;
          }
        }
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry local_r = this.nodePath[(local_i - 1)].right;
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
              Entry local_s = local_r.left;
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
          Entry local_r = this.nodePath[(local_i - 1)].left;
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
              Entry local_s = local_r.right;
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
  
  public Long put(Character local_ok, Long local_ov)
  {
    long oldValue = put(local_ok.charValue(), local_ov.longValue());
    return this.modified ? null : Long.valueOf(oldValue);
  }
  
  public Long remove(Object local_ok)
  {
    long oldValue = remove(((Character)local_ok).charValue());
    return this.modified ? Long.valueOf(oldValue) : null;
  }
  
  public boolean containsValue(long local_v)
  {
    ValueIterator local_i = new ValueIterator(null);
    int local_j = this.count;
    while (local_j-- != 0)
    {
      long local_ev = local_i.nextLong();
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
  
  public boolean containsKey(char local_k)
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
  
  public long get(char local_k)
  {
    Entry local_e = findKey(local_k);
    return local_e == null ? this.defRetValue : local_e.value;
  }
  
  public char firstCharKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public char lastCharKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectSortedSet<Char2LongMap.Entry> char2LongEntrySet()
  {
    if (this.entries == null) {
      this.entries = new AbstractObjectSortedSet()
      {
        final Comparator<? super Char2LongMap.Entry> comparator = new Comparator()
        {
          public int compare(Char2LongMap.Entry local_x, Char2LongMap.Entry local_y)
          {
            return Char2LongRBTreeMap.this.storedComparator.compare(local_x.getKey(), local_y.getKey());
          }
        };
        
        public Comparator<? super Char2LongMap.Entry> comparator()
        {
          return this.comparator;
        }
        
        public ObjectBidirectionalIterator<Char2LongMap.Entry> iterator()
        {
          return new Char2LongRBTreeMap.EntryIterator(Char2LongRBTreeMap.this);
        }
        
        public ObjectBidirectionalIterator<Char2LongMap.Entry> iterator(Char2LongMap.Entry from)
        {
          return new Char2LongRBTreeMap.EntryIterator(Char2LongRBTreeMap.this, ((Character)from.getKey()).charValue());
        }
        
        public boolean contains(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
          Char2LongRBTreeMap.Entry local_f = Char2LongRBTreeMap.this.findKey(((Character)local_e.getKey()).charValue());
          return local_e.equals(local_f);
        }
        
        public boolean remove(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
          Char2LongRBTreeMap.Entry local_f = Char2LongRBTreeMap.this.findKey(((Character)local_e.getKey()).charValue());
          if (local_f != null) {
            Char2LongRBTreeMap.this.remove(local_f.key);
          }
          return local_f != null;
        }
        
        public int size()
        {
          return Char2LongRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Char2LongRBTreeMap.this.clear();
        }
        
        public Char2LongMap.Entry first()
        {
          return Char2LongRBTreeMap.this.firstEntry;
        }
        
        public Char2LongMap.Entry last()
        {
          return Char2LongRBTreeMap.this.lastEntry;
        }
        
        public ObjectSortedSet<Char2LongMap.Entry> subSet(Char2LongMap.Entry from, Char2LongMap.Entry local_to)
        {
          return Char2LongRBTreeMap.this.subMap((Character)from.getKey(), (Character)local_to.getKey()).char2LongEntrySet();
        }
        
        public ObjectSortedSet<Char2LongMap.Entry> headSet(Char2LongMap.Entry local_to)
        {
          return Char2LongRBTreeMap.this.headMap((Character)local_to.getKey()).char2LongEntrySet();
        }
        
        public ObjectSortedSet<Char2LongMap.Entry> tailSet(Char2LongMap.Entry from)
        {
          return Char2LongRBTreeMap.this.tailMap((Character)from.getKey()).char2LongEntrySet();
        }
      };
    }
    return this.entries;
  }
  
  public CharSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public LongCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractLongCollection()
      {
        public LongIterator iterator()
        {
          return new Char2LongRBTreeMap.ValueIterator(Char2LongRBTreeMap.this, null);
        }
        
        public boolean contains(long local_k)
        {
          return Char2LongRBTreeMap.this.containsValue(local_k);
        }
        
        public int size()
        {
          return Char2LongRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Char2LongRBTreeMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  public CharComparator comparator()
  {
    return this.actualComparator;
  }
  
  public Char2LongSortedMap headMap(char local_to)
  {
    return new Submap('\000', true, local_to, false);
  }
  
  public Char2LongSortedMap tailMap(char from)
  {
    return new Submap(from, false, '\000', true);
  }
  
  public Char2LongSortedMap subMap(char from, char local_to)
  {
    return new Submap(from, false, local_to, false);
  }
  
  public Char2LongRBTreeMap clone()
  {
    Char2LongRBTreeMap local_c;
    try
    {
      local_c = (Char2LongRBTreeMap)super.clone();
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
      local_s.writeChar(local_e.key);
      local_s.writeLong(local_e.value);
    }
  }
  
  private Entry readTree(ObjectInputStream local_s, int local_n, Entry pred, Entry succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry top = new Entry(local_s.readChar(), local_s.readLong());
      top.pred(pred);
      top.succ(succ);
      top.black(true);
      return top;
    }
    if (local_n == 2)
    {
      Entry top = new Entry(local_s.readChar(), local_s.readLong());
      top.black(true);
      top.right(new Entry(local_s.readChar(), local_s.readLong()));
      top.right.pred(top);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readChar();
    top.value = local_s.readLong();
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
      for (Entry local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private void checkNodePath() {}
  
  private int checkTree(Entry local_e, int local_d, int local_D)
  {
    return 0;
  }
  
  private final class Submap
    extends AbstractChar2LongSortedMap
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    char from;
    char field_47;
    boolean bottom;
    boolean top;
    protected volatile transient ObjectSortedSet<Char2LongMap.Entry> entries;
    protected volatile transient CharSortedSet keys;
    protected volatile transient LongCollection values;
    
    public Submap(char from, boolean bottom, char local_to, boolean top)
    {
      if ((!bottom) && (!top) && (Char2LongRBTreeMap.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start key (" + from + ") is larger than end key (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_47 = local_to;
      this.top = top;
      this.defRetValue = Char2LongRBTreeMap.this.defRetValue;
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
    
    final boolean in2(char local_k)
    {
      return ((this.bottom) || (Char2LongRBTreeMap.this.compare(local_k, this.from) >= 0)) && ((this.top) || (Char2LongRBTreeMap.this.compare(local_k, this.field_47) < 0));
    }
    
    public ObjectSortedSet<Char2LongMap.Entry> char2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = new AbstractObjectSortedSet()
        {
          public ObjectBidirectionalIterator<Char2LongMap.Entry> iterator()
          {
            return new Char2LongRBTreeMap.Submap.SubmapEntryIterator(Char2LongRBTreeMap.Submap.this);
          }
          
          public ObjectBidirectionalIterator<Char2LongMap.Entry> iterator(Char2LongMap.Entry from)
          {
            return new Char2LongRBTreeMap.Submap.SubmapEntryIterator(Char2LongRBTreeMap.Submap.this, ((Character)from.getKey()).charValue());
          }
          
          public Comparator<? super Char2LongMap.Entry> comparator()
          {
            return Char2LongRBTreeMap.this.char2LongEntrySet().comparator();
          }
          
          public boolean contains(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
            Char2LongRBTreeMap.Entry local_f = Char2LongRBTreeMap.this.findKey(((Character)local_e.getKey()).charValue());
            return (local_f != null) && (Char2LongRBTreeMap.Submap.this.in2(local_f.key)) && (local_e.equals(local_f));
          }
          
          public boolean remove(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
            Char2LongRBTreeMap.Entry local_f = Char2LongRBTreeMap.this.findKey(((Character)local_e.getKey()).charValue());
            if ((local_f != null) && (Char2LongRBTreeMap.Submap.this.in2(local_f.key))) {
              Char2LongRBTreeMap.Submap.this.remove(local_f.key);
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
            return !new Char2LongRBTreeMap.Submap.SubmapIterator(Char2LongRBTreeMap.Submap.this).hasNext();
          }
          
          public void clear()
          {
            Char2LongRBTreeMap.Submap.this.clear();
          }
          
          public Char2LongMap.Entry first()
          {
            return Char2LongRBTreeMap.Submap.this.firstEntry();
          }
          
          public Char2LongMap.Entry last()
          {
            return Char2LongRBTreeMap.Submap.this.lastEntry();
          }
          
          public ObjectSortedSet<Char2LongMap.Entry> subSet(Char2LongMap.Entry from, Char2LongMap.Entry local_to)
          {
            return Char2LongRBTreeMap.Submap.this.subMap((Character)from.getKey(), (Character)local_to.getKey()).char2LongEntrySet();
          }
          
          public ObjectSortedSet<Char2LongMap.Entry> headSet(Char2LongMap.Entry local_to)
          {
            return Char2LongRBTreeMap.Submap.this.headMap((Character)local_to.getKey()).char2LongEntrySet();
          }
          
          public ObjectSortedSet<Char2LongMap.Entry> tailSet(Char2LongMap.Entry from)
          {
            return Char2LongRBTreeMap.Submap.this.tailMap((Character)from.getKey()).char2LongEntrySet();
          }
        };
      }
      return this.entries;
    }
    
    public CharSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = new KeySet(null);
      }
      return this.keys;
    }
    
    public LongCollection values()
    {
      if (this.values == null) {
        this.values = new AbstractLongCollection()
        {
          public LongIterator iterator()
          {
            return new Char2LongRBTreeMap.Submap.SubmapValueIterator(Char2LongRBTreeMap.Submap.this, null);
          }
          
          public boolean contains(long local_k)
          {
            return Char2LongRBTreeMap.Submap.this.containsValue(local_k);
          }
          
          public int size()
          {
            return Char2LongRBTreeMap.Submap.this.size();
          }
          
          public void clear()
          {
            Char2LongRBTreeMap.Submap.this.clear();
          }
        };
      }
      return this.values;
    }
    
    public boolean containsKey(char local_k)
    {
      return (in2(local_k)) && (Char2LongRBTreeMap.this.containsKey(local_k));
    }
    
    public boolean containsValue(long local_v)
    {
      SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        long local_ev = local_i.nextEntry().value;
        if (local_ev == local_v) {
          return true;
        }
      }
      return false;
    }
    
    public long get(char local_k)
    {
      char local_kk = local_k;
      Char2LongRBTreeMap.Entry local_e;
      return (in2(local_kk)) && ((local_e = Char2LongRBTreeMap.this.findKey(local_kk)) != null) ? local_e.value : this.defRetValue;
    }
    
    public long put(char local_k, long local_v)
    {
      Char2LongRBTreeMap.this.modified = false;
      if (!in2(local_k)) {
        throw new IllegalArgumentException("Key (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_47)) + ")");
      }
      long oldValue = Char2LongRBTreeMap.this.put(local_k, local_v);
      return Char2LongRBTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public Long put(Character local_ok, Long local_ov)
    {
      long oldValue = put(local_ok.charValue(), local_ov.longValue());
      return Char2LongRBTreeMap.this.modified ? null : Long.valueOf(oldValue);
    }
    
    public long remove(char local_k)
    {
      Char2LongRBTreeMap.this.modified = false;
      if (!in2(local_k)) {
        return this.defRetValue;
      }
      long oldValue = Char2LongRBTreeMap.this.remove(local_k);
      return Char2LongRBTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public Long remove(Object local_ok)
    {
      long oldValue = remove(((Character)local_ok).charValue());
      return Char2LongRBTreeMap.this.modified ? Long.valueOf(oldValue) : null;
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
    
    public CharComparator comparator()
    {
      return Char2LongRBTreeMap.this.actualComparator;
    }
    
    public Char2LongSortedMap headMap(char local_to)
    {
      if (this.top) {
        return new Submap(Char2LongRBTreeMap.this, this.from, this.bottom, local_to, false);
      }
      return Char2LongRBTreeMap.this.compare(local_to, this.field_47) < 0 ? new Submap(Char2LongRBTreeMap.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public Char2LongSortedMap tailMap(char from)
    {
      if (this.bottom) {
        return new Submap(Char2LongRBTreeMap.this, from, false, this.field_47, this.top);
      }
      return Char2LongRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Char2LongRBTreeMap.this, from, false, this.field_47, this.top) : this;
    }
    
    public Char2LongSortedMap subMap(char from, char local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Submap(Char2LongRBTreeMap.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = Char2LongRBTreeMap.this.compare(local_to, this.field_47) < 0 ? local_to : this.field_47;
      }
      if (!this.bottom) {
        from = Char2LongRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_47)) {
        return this;
      }
      return new Submap(Char2LongRBTreeMap.this, from, false, local_to, false);
    }
    
    public Char2LongRBTreeMap.Entry firstEntry()
    {
      if (Char2LongRBTreeMap.this.tree == null) {
        return null;
      }
      Char2LongRBTreeMap.Entry local_e;
      Char2LongRBTreeMap.Entry local_e;
      if (this.bottom)
      {
        local_e = Char2LongRBTreeMap.this.firstEntry;
      }
      else
      {
        local_e = Char2LongRBTreeMap.this.locateKey(this.from);
        if (Char2LongRBTreeMap.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (Char2LongRBTreeMap.this.compare(local_e.key, this.field_47) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public Char2LongRBTreeMap.Entry lastEntry()
    {
      if (Char2LongRBTreeMap.this.tree == null) {
        return null;
      }
      Char2LongRBTreeMap.Entry local_e;
      Char2LongRBTreeMap.Entry local_e;
      if (this.top)
      {
        local_e = Char2LongRBTreeMap.this.lastEntry;
      }
      else
      {
        local_e = Char2LongRBTreeMap.this.locateKey(this.field_47);
        if (Char2LongRBTreeMap.this.compare(local_e.key, this.field_47) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (Char2LongRBTreeMap.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public char firstCharKey()
    {
      Char2LongRBTreeMap.Entry local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public char lastCharKey()
    {
      Char2LongRBTreeMap.Entry local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public Character firstKey()
    {
      Char2LongRBTreeMap.Entry local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    public Character lastKey()
    {
      Char2LongRBTreeMap.Entry local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    private final class SubmapValueIterator
      extends Char2LongRBTreeMap.Submap.SubmapIterator
      implements LongListIterator
    {
      private SubmapValueIterator()
      {
        super();
      }
      
      public long nextLong()
      {
        return nextEntry().value;
      }
      
      public long previousLong()
      {
        return previousEntry().value;
      }
      
      public void set(long local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(long local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public Long next()
      {
        return Long.valueOf(nextEntry().value);
      }
      
      public Long previous()
      {
        return Long.valueOf(previousEntry().value);
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
    
    private final class SubmapKeyIterator
      extends Char2LongRBTreeMap.Submap.SubmapIterator
      implements CharListIterator
    {
      public SubmapKeyIterator()
      {
        super();
      }
      
      public SubmapKeyIterator(char from)
      {
        super(from);
      }
      
      public char nextChar()
      {
        return nextEntry().key;
      }
      
      public char previousChar()
      {
        return previousEntry().key;
      }
      
      public void set(char local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(char local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public Character next()
      {
        return Character.valueOf(nextEntry().key);
      }
      
      public Character previous()
      {
        return Character.valueOf(previousEntry().key);
      }
      
      public void set(Character local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Character local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapEntryIterator
      extends Char2LongRBTreeMap.Submap.SubmapIterator
      implements ObjectListIterator<Char2LongMap.Entry>
    {
      SubmapEntryIterator()
      {
        super();
      }
      
      SubmapEntryIterator(char local_k)
      {
        super(local_k);
      }
      
      public Char2LongMap.Entry next()
      {
        return nextEntry();
      }
      
      public Char2LongMap.Entry previous()
      {
        return previousEntry();
      }
      
      public void set(Char2LongMap.Entry local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Char2LongMap.Entry local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapIterator
      extends Char2LongRBTreeMap.TreeIterator
    {
      SubmapIterator()
      {
        super();
        this.next = Char2LongRBTreeMap.Submap.this.firstEntry();
      }
      
      SubmapIterator(char local_k)
      {
        this();
        if (this.next != null) {
          if ((!Char2LongRBTreeMap.Submap.this.bottom) && (Char2LongRBTreeMap.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!Char2LongRBTreeMap.Submap.this.top) && (Char2LongRBTreeMap.this.compare(local_k, (this.prev = Char2LongRBTreeMap.Submap.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = Char2LongRBTreeMap.this.locateKey(local_k);
            if (Char2LongRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
        if ((!Char2LongRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Char2LongRBTreeMap.this.compare(this.prev.key, Char2LongRBTreeMap.Submap.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!Char2LongRBTreeMap.Submap.this.top) && (this.next != null) && (Char2LongRBTreeMap.this.compare(this.next.key, Char2LongRBTreeMap.Submap.this.field_47) >= 0)) {
          this.next = null;
        }
      }
    }
    
    private class KeySet
      extends AbstractChar2LongSortedMap.KeySet
    {
      private KeySet()
      {
        super();
      }
      
      public CharBidirectionalIterator iterator()
      {
        return new Char2LongRBTreeMap.Submap.SubmapKeyIterator(Char2LongRBTreeMap.Submap.this);
      }
      
      public CharBidirectionalIterator iterator(char from)
      {
        return new Char2LongRBTreeMap.Submap.SubmapKeyIterator(Char2LongRBTreeMap.Submap.this, from);
      }
    }
  }
  
  private final class ValueIterator
    extends Char2LongRBTreeMap.TreeIterator
    implements LongListIterator
  {
    private ValueIterator()
    {
      super();
    }
    
    public long nextLong()
    {
      return nextEntry().value;
    }
    
    public long previousLong()
    {
      return previousEntry().value;
    }
    
    public void set(long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long next()
    {
      return Long.valueOf(nextEntry().value);
    }
    
    public Long previous()
    {
      return Long.valueOf(previousEntry().value);
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
  
  private class KeySet
    extends AbstractChar2LongSortedMap.KeySet
  {
    private KeySet()
    {
      super();
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new Char2LongRBTreeMap.KeyIterator(Char2LongRBTreeMap.this);
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new Char2LongRBTreeMap.KeyIterator(Char2LongRBTreeMap.this, from);
    }
  }
  
  private final class KeyIterator
    extends Char2LongRBTreeMap.TreeIterator
    implements CharListIterator
  {
    public KeyIterator()
    {
      super();
    }
    
    public KeyIterator(char local_k)
    {
      super(local_k);
    }
    
    public char nextChar()
    {
      return nextEntry().key;
    }
    
    public char previousChar()
    {
      return previousEntry().key;
    }
    
    public void set(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character next()
    {
      return Character.valueOf(nextEntry().key);
    }
    
    public Character previous()
    {
      return Character.valueOf(previousEntry().key);
    }
    
    public void set(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Char2LongRBTreeMap.TreeIterator
    implements ObjectListIterator<Char2LongMap.Entry>
  {
    EntryIterator()
    {
      super();
    }
    
    EntryIterator(char local_k)
    {
      super(local_k);
    }
    
    public Char2LongMap.Entry next()
    {
      return nextEntry();
    }
    
    public Char2LongMap.Entry previous()
    {
      return previousEntry();
    }
    
    public void set(Char2LongMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Char2LongMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class TreeIterator
  {
    Char2LongRBTreeMap.Entry prev;
    Char2LongRBTreeMap.Entry next;
    Char2LongRBTreeMap.Entry curr;
    int index = 0;
    
    TreeIterator()
    {
      this.next = Char2LongRBTreeMap.this.firstEntry;
    }
    
    TreeIterator(char local_k)
    {
      if ((this.next = Char2LongRBTreeMap.this.locateKey(local_k)) != null) {
        if (Char2LongRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
    
    Char2LongRBTreeMap.Entry nextEntry()
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
    
    Char2LongRBTreeMap.Entry previousEntry()
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
      Char2LongRBTreeMap.this.remove(this.curr.key);
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
    implements Cloneable, Char2LongMap.Entry
  {
    private static final int BLACK_MASK = 1;
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    char key;
    long value;
    Entry left;
    Entry right;
    int info;
    
    Entry() {}
    
    Entry(char local_k, long local_v)
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
    
    public Character getKey()
    {
      return Character.valueOf(this.key);
    }
    
    public char getCharKey()
    {
      return this.key;
    }
    
    public Long getValue()
    {
      return Long.valueOf(this.value);
    }
    
    public long getLongValue()
    {
      return this.value;
    }
    
    public long setValue(long value)
    {
      long oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public Long setValue(Long value)
    {
      return Long.valueOf(setValue(value.longValue()));
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
      Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
      return (this.key == ((Character)local_e.getKey()).charValue()) && (this.value == ((Long)local_e.getValue()).longValue());
    }
    
    public int hashCode()
    {
      return this.key ^ HashCommon.long2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "=>" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2LongRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */