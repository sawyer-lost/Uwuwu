# STC89C52 MicroOS Simulator - Week 3

Week 3 extends the Week 2 simulator with Memory, Stack, and FIFO Queue functionality while keeping the existing CPU/instruction work.

## Added in Week 3
- Memory Read/Write and reset support.
- 8051-style Stack Pointer handling.
- PUSH / POP stack instructions.
- Fixed-size circular FIFO Queue.
- ENQUEUE / DEQUEUE instructions.
- Empty/full conditions and status reporting.
- UI display for CPU, SP, stack, queue, and memory samples.
- Queue flowchart and validation Assembly program.
- Week 3 automated tests.

## Run
```bash
cd src
javac *.java
java Main
```

## Week 3 validation test
```bash
cd src
javac *.java
cd ../tests
javac -cp ../src Week3MemoryStackQueueTest.java
java -cp ../src:. Week3MemoryStackQueueTest
```

Expected output: `All Week 3 tests passed.`
