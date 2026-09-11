# Week 3 Queue Flowchart

The Queue flowchart should show this logic when transferred to the final handwritten flowchart:

```text
START
  |
  v
Choose Queue Operation
  |
  +----------------------+
  |                      |
Enqueue                Dequeue
  |                      |
Queue Full?            Queue Empty?
  |                      |
 Yes -> Full Error     Yes -> Empty Error
  |
 No                     No
  |                      |
Add at Rear           Remove from Front
  |                      |
Update Rear/Size      Update Front/Size
  |                      |
  +----------+-----------+
             |
             v
       Update Queue Status
             |
             v
            END
```

For the submission, this can be redrawn neatly by hand and added as an image to the `images/` folder.
