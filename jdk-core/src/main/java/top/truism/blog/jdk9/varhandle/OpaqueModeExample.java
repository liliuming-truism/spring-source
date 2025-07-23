package top.truism.blog.jdk9.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class OpaqueModeExample {
    private long counter = 0;
    private static final VarHandle COUNTER_HANDLE;

    static {
        try {
            COUNTER_HANDLE = MethodHandles.lookup()
                .findVarHandle(OpaqueModeExample.class, "counter", long.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception{
        OpaqueModeExample opaqueModeExample = new OpaqueModeExample();
        opaqueModeExample.demonstrateOpaqueMode();

        opaqueModeExample.concurrentCounter();

    }

    public void demonstrateOpaqueMode() {
        // Opaque模式保证原子性
        COUNTER_HANDLE.setOpaque(this, 100L);
        long value = (long) COUNTER_HANDLE.getOpaque(this);

        System.out.println("Opaque mode value: " + value);

        COUNTER_HANDLE.setOpaque(this, 0);
    }

    // 多线程计数器示例
    public void concurrentCounter() throws InterruptedException {
        int numThreads = 4;
        int incrementsPerThread = 1000;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    // 使用Opaque模式进行原子读-修改-写
                    long current, updated;
                    do {
                        current = (long) COUNTER_HANDLE.getOpaque(this);
                        updated = current + 1;
                    }
                    while (!COUNTER_HANDLE.compareAndSet(this, current, updated));
                }
            });
        }

        // 启动所有线程
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }

        long finalValue = (long) COUNTER_HANDLE.getOpaque(this);
        System.out.println("Expected: " + (numThreads * incrementsPerThread));
        System.out.println("Actual: " + finalValue);
    }
}
