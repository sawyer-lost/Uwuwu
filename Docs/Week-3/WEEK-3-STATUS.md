# Week 3 Status

## Memory - Punarvi
- Basic data-memory Read/Write is implemented in `src/Memory.java`.
- Memory reset and address validation are included.
- Simulator/UI can access memory values.

## Stack - Keora
- `src/Stack.java` implements an 8051-style upward-growing stack.
- Stack Pointer is held by `CPU.java` and starts at `07H`.
- `PUSH` and `POP` are supported by the simulator.
- Empty/full conditions are handled.

## FIFO Queue - Hisham
- `src/Queue.java` implements a fixed-size circular FIFO queue.
- `ENQUEUE` and `DEQUEUE` are supported by the simulator.
- Empty/full conditions and queue status are available.
- `programs/week3_queue_validation.txt` validates FIFO order.

## Validation & Documentation - Izhan
- Queue flowchart is in `Docs/Week-3/QUEUE_FLOWCHART.md`.
- Week 3 tests are in `tests/Week3MemoryStackQueueTest.java`.
- Expected FIFO result for 10, 20, 30 is 10, 20, 30.
