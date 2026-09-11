# Project File Guide

This document briefly explains the purpose of each important project file.

## Source Files (`src/`)

| File | Purpose |
|---|---|
| `Main.java` | Main UI and user-interface integration for the simulator. |
| `Simulator.java` | Connects the UI, CPU, Memory, Stack, Queue and program execution flow. |
| `CPU.java` | CPU state and instruction execution logic, including registers, PC and SP-related execution. |
| `Instruction.java` | Represents a parsed assembly instruction and its operands. |
| `InstructionSet.java` | Defines the supported simulator instructions and their execution behavior. |
| `Memory.java` | Implements memory storage with Read/Write operations and reset/boundary handling. |
| `Stack.java` | Implements the stack data structure used by PUSH/POP and tracks stack state. |
| `Queue.java` | Implements the fixed-size FIFO queue used by ENQUEUE/DEQUEUE. |
| `ExecutionTrace.java` | Records and formats instruction execution information shown in the UI. |

## Programs (`programs/`)

| File | Purpose |
|---|---|
| `demo.txt` | Demo assembly program used for simulator execution/examples. |
| `week3_queue_validation.txt` | Week-3 Queue validation program demonstrating multiple ENQUEUE/DEQUEUE operations and FIFO order. |

## Tests (`tests/`)

| File | Purpose |
|---|---|
| `InstructionTest.java` | Tests the existing instruction behavior. |
| `DemoProgramTest.java` | Tests execution of the demo program. |
| `Week3InstructionExamples.java` | Contains Week-3 instruction examples used for validation. |
| `Week3MemoryStackQueueTest.java` | Tests the new Week-3 Memory, Stack and FIFO Queue functionality. |

## Documentation (`Docs/Week-3/`)

| File | Purpose |
|---|---|
| `QUEUE_FLOWCHART.md` | Queue flowchart covering Enqueue, Dequeue, Empty, Full and status/update conditions. |

## Images (`images/`)

The `images/week3-instructions/` folder contains the output screenshots for the instruction examples shown in the Week-3 README.

## Important Note

`.class` files are compiled Java output files. The main editable/source files are the `.java` files listed above.
