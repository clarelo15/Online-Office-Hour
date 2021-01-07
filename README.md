# Online-Office-Hour
Synchronize the student threads, teacher and clock threads.


Students come to school and wait (use busy waiting) in front of the computer-lab until it is time for the lab to open. Once the lab is open, students enter the lab up to the capacity of the lab. If the capacity is reached no additional students can enter the lab. Students over the lab capacity will terminate their execution.

There are two types of questions that the student might have. The type and number of questions that each student will be allowed to ask is determined randomly.

Type A: the student can email the teacher up to #questions_A. These questions do not need an immediate answer from the teacher.

Type B: the student will have a chat session with the teacher. During a chat session, the student is allowed to ask at most #questions_B.

Once the student has entered the lab, he will take some time to think about his questionA. (use yield( ) and later on sleep(random time)). Each question should contain a timestamp (use age()) and the name of the student (use getName()). Create a real question message with the student thread name and timestamp.

Type A questions: As soon as the student figures his questionA, he rushes to send it to the teacher. (implement this by having the student increase his priority (use getPriority(), and setPriority()). Simulate sending the question by sleep(random time) and immediately after that reset the thread’s priority to the default value). All of the questions sent by students will be answered by the teacher in the order in which they were sent. 

After the student is done sending his typeA questions, if he wants to have a chat with the teacher, he needs to wait until the chat session starts and it is his turn to chat. Once it is his turn to chat, he will send a question to the teacher and wait for an answer (any wait should be simulated by busy waiting). If he has fewer than #questions_B he needs to let the teacher know, when it is his very last question (when his chat session ends).

After his chat session, the student will browse the Internet waiting for the online office hour to end (use sleep(time)), where time is long enough such that when the online office hours end, the student will be woke up through an interrupt(). Use isInterrupted() method To check that the interrupt works properly have a message in the body of the catch.

Next the students will leave the lab in the descending order of their name. (Implement this using isAlive() and join()). For example, if there are 4 student threads named from T0 to T3. T0 will join T1, T1 will join T2, T2 will join T3 and T3 after some sleep of random time will terminate. Keep in mind that some student-threads have already terminated. In that case a specific student thread will wait for the next student in sequence.

The teacher arrives at his office before the online office hour begins. He spends some time answering typeA questions (simulate by sleep of random time), but might not be able to answer all of them. Once the time for the online chat session is on, he will let know the first student waiting to chat that he is online and can take questions. He chats with students in the order in which they queued for the online chat session. When done chatting, if there is any remaining time until the office hour ends, the teacher checks if there are any new or unanswered typeA questions and attempts to answers them.

Once the office hour ends, the teacher will terminate. The timer will keep track of the times and, if necessary, release the correct threads. Its code should be a sequence of sleeps of fixed times corresponding to the interval of time between different events.

time intervals: 

     Teacher’s arrival time
     Start of online office hour
     start online chat session
     end online chat session
     end online office hour
     
