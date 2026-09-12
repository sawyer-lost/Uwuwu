# Week 3 – STC89C52 Microcontroller Simulator

Week 3 adds **Memory Read/Write, Stack and FIFO Queue** to the Week 2 simulator. The old instructions are still supported.

## New Instructions

### PUSH
Adds a value to the top of the stack and updates the Stack Pointer (SP).

```text
MOV A,#10
PUSH A
END
```

When the program runs, `10` is stored in the stack and SP changes to the new top position.

### POP
Removes the top value from the stack. This follows **LIFO (Last In, First Out)**.

```text
MOV A,#10
PUSH A
MOV A,#20
PUSH A
POP R0
POP R1
END
```

Result: `R0 = 20`, `R1 = 10`.

### ENQUEUE
Adds a value to the rear of the FIFO queue.

```text
ENQUEUE #10
ENQUEUE #20
ENQUEUE #30
END
```

Queue becomes: `10 → 20 → 30`.

### DEQUEUE
Removes a value from the front of the FIFO queue. This follows **FIFO (First In, First Out)**.

```text
ENQUEUE #10
ENQUEUE #20
ENQUEUE #30
DEQUEUE R0
DEQUEUE R1
DEQUEUE A
END
```

Result: `R0 = 10`, `R1 = 20`, `A = 30`.

## How to Run in VS Code

Open the project folder in VS Code and open the terminal.

### Run the simulator

```bash
javac src/*.java
java -cp src Main
```

### Run the Week 3 tests

```bash
javac src/*.java tests/Week3MemoryStackQueueTest.java
java -cp src:tests Week3MemoryStackQueueTest
```

On Windows, use `;` instead of `:` in the classpath:

```bat
java -cp src;tests Week3MemoryStackQueueTest
```

### Using the simulator

1. Enter the Assembly program in the top-left editor.
2. Click **LOAD**.
3. Use **STEP** to execute one instruction at a time, or **RUN** to execute the program.
4. Check the CPU/Stack/FIFO Queue state and execution trace.
5. Use **RESET** before running another test.

## Queue Flowchart

The handwritten Queue flowchart used for Week 3:

![Handwritten FIFO Queue Flowchart](images/week3-flowchart/queue-flowchart-handwritten.png)

It covers Enqueue, Dequeue, Queue Full, Queue Empty, status/update and the FIFO order.

## Queue Assembly Validation

Validation program:

`programs/week3_queue_validation.txt`

It performs multiple Enqueue and Dequeue operations and checks that values leave the queue in the same order they entered.

## Week 3 Documentation

- `Docs/README.md` – what the main project files are used for
- `Docs/Week-3/` – Week 3 flowchart/status notes
- `Docs/Meeting/` – meeting records
- `Docs/weekly-status/` – weekly progress
- `Docs/decisions/` – project decisions
