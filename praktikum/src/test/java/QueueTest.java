import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

	@Test
	public void testqueue() {
		try{
			Queue.Queue(0);
			fail("exeption nicht geworfen in queue");
		}catch (ArithmeticException e){}
	}

	@Test
	public void testenqueue(){
		Queue.Queue(3);
		Queue.enqueue(1);
		Queue.enqueue(2);
		Queue.enqueue(3);
		Queue.enqueue(4);
		Assert.assertEquals(4, Queue.queue[3]);
	}

	@Test
	public void testdequeue(){
		Queue.Queue(3);
		Queue.enqueue(1);
		Queue.enqueue(2);
		Queue.enqueue(3);
		Assert.assertEquals(1, Queue.dequeue);
	}

	@Test
	public void testdeque_empty(){
		Queue.Queue(1);
		Queue.enqueue(1);
		Queue.dequeue;
		try{
		Queue.deque;
		fail("exeption nicht geworfen in dequeue bei leerer queue");
		}catch (ArithmeticException e)
	}

}
