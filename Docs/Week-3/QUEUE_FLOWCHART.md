# Week 3 - FIFO Queue Flowchart

```text
          START
            |
            v
       Queue operation?
        /           \
   ENQUEUE          DEQUEUE
      |                |
      v                v
   Is Full?         Is Empty?
    /   \             /   \
  YES    NO          YES    NO
   |      |           |      |
  Error   v          Error   v
        Insert              Remove
        at Rear             at Front
           \                /
            v              v
              Update size
                   |
                   v
              Queue status
                   |
                   v
                  END
```

The queue uses circular indexing, so the rear wraps to index 0 when it reaches the capacity limit.
