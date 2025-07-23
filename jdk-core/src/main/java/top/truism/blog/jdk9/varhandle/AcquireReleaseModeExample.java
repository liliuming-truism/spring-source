package top.truism.blog.jdk9.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AcquireReleaseModeExample {
    private int data = 0;
    private boolean ready = false;

    private static final VarHandle DATA_HANDLE;
    private static final VarHandle READY_HANDLE;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            DATA_HANDLE = lookup.findVarHandle(
                AcquireReleaseModeExample.class, "data", int.class);
            READY_HANDLE = lookup.findVarHandle(
                AcquireReleaseModeExample.class, "ready", boolean.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception{
        AcquireReleaseModeExample acquireReleaseModeExample = new AcquireReleaseModeExample();
        acquireReleaseModeExample.producerConsumerExample();

        acquireReleaseModeExample.messagePassingExample();
    }

    // 生产者-消费者模式示例
    public void producerConsumerExample() throws InterruptedException {
        Thread producer = new Thread(() -> {
            System.out.println("Producer: 准备数据...");

            // 1. 先设置数据（可以使用任何模式）
            DATA_HANDLE.set(this, 42);

            // 2. 使用Release模式设置ready标志
            // 这确保了数据的写入在ready标志设置之前完成
            READY_HANDLE.setRelease(this, true);

            System.out.println("Producer: 数据已准备好");
        });

        Thread consumer = new Thread(() -> {
            System.out.println("Consumer: 等待数据...");

            // 1. 使用Acquire模式检查ready标志
            while (!(boolean) READY_HANDLE.getAcquire(this)) {
                Thread.yield(); // 等待数据准备好
            }

            // 2. 此时可以安全地读取数据
            // Acquire-Release语义保证了数据的可见性
            int value = (int) DATA_HANDLE.get(this);

            System.out.println("Consumer: 读取到数据 = " + value);
        });

        consumer.start();
        Thread.sleep(100); // 确保consumer先启动
        producer.start();

        producer.join();
        consumer.join();
    }

    // 更复杂的示例：消息传递
    public void messagePassingExample() throws InterruptedException {
        final int[] sharedData = new int[10];
        VarHandle arrayHandle = MethodHandles.arrayElementVarHandle(int[].class);

        Thread sender = new Thread(() -> {
            // 填充数据
            for (int i = 0; i < sharedData.length; i++) {
                arrayHandle.set(sharedData, i, i * i);
            }

            // 使用Release语义通知数据准备完成
            READY_HANDLE.setRelease(this, true);
            System.out.println("Sender: 所有数据已发送");
        });

        Thread receiver = new Thread(() -> {
            // 使用Acquire语义等待数据
            while (!(boolean) READY_HANDLE.getAcquire(this)) {
                Thread.yield();
            }

            // 现在可以安全地读取所有数据
            System.out.print("Receiver: 接收到数据 [");
            for (int i = 0; i < sharedData.length; i++) {
                int value = (int) arrayHandle.get(sharedData, i);
                System.out.print(value);
                if (i < sharedData.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        });

        receiver.start();
        Thread.sleep(50);
        sender.start();

        sender.join();
        receiver.join();
    }
}
