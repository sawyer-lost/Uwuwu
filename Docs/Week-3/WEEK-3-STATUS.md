# Week 3 – Implementation Status

## New Functionality

- Memory Read/Write
- Stack Pointer (SP)
- PUSH and POP
- FIFO Queue
- ENQUEUE and DEQUEUE
- Queue status and boundary handling
- UI integration for the new modules
- Queue Assembly validation
- Week 3 test documentation

## New Instruction Flow

### PUSH
Assembly/program input → CPU instruction execution → Stack receives value → SP is updated → UI/trace shows the new stack state.

### POP
Assembly/program input → CPU executes POP → top stack value is removed/retrieved → SP is updated → UI/trace shows the updated state.

### ENQUEUE
Assembly/program input → CPU/simulator executes ENQUEUE → value is added at the Queue rear → Queue status is updated → UI/trace shows the queue.

### DEQUEUE
Assembly/program input → CPU/simulator executes DEQUEUE → value is removed from the Queue front → Queue status is updated → UI/trace shows the result.

## Validation

The Queue validation program demonstrates multiple ENQUEUE and DEQUEUE operations. The expected output is compared with the actual simulator execution to verify FIFO behaviour.


## Submission Evidence Update

- The UI now displays the complete 256-byte data-memory map with address/value pairs.
- CPU STATE displays the Stack Pointer, stack contents/status, and FIFO queue contents/status.
- Week 3 tests cover Memory read/write/reset, Stack LIFO, Stack underflow/overflow, Queue FIFO, and Queue empty/full boundary conditions.
- Queue Assembly validation records both expected and actual register results: R0=0A, R1=14, R2=1E.
- Assembly execution result is recorded as PASS for execution through the simulator.
