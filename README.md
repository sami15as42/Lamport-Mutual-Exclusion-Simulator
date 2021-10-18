# Lamport-Mutual-Exclusion-Simulator
This java application simulates the Lamport mutual exclusion algorithm. The application is made up of ten processes that share a critical resource. The main body of a process is an endless loop that waits for a random delay between 1s and 5s, before entering a critical section using Lamport's algorithm. The body of a critical section is also waiting for a random delay between 1 and 2s. The processes communicate between them by sockets in peer-to-peer.
