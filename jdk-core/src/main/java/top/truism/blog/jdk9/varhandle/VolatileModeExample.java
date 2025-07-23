package top.truism.blog.jdk9.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VolatileModeExample {
    private int volatileField = 0;
    private int normalField = 0;

    private static final VarHandle VOLATILE_HANDLE;
    private static final VarHandle NORMAL_HANDLE;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            VOLATILE_HANDLE = lookup.findVarHandle(
                VolatileModeExample.class, "volatileField", int.class);
            NORMAL_HANDLE = lookup.findVarHandle(
                VolatileModeExample.class, "normalField", int.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception{
        VolatileModeExample volatileModeExample = new VolatileModeExample();
        volatileModeExample.demonstrateVolatileMode();

        volatileModeExample.memoryBarrierExample();
    }

    public void demonstrateVolatileMode() {
        // Volatile模式的读写
        VOLATILE_HANDLE.setVolatile(this, 100);
        int value = (int) VOLATILE_HANDLE.getVolatile(this);

        System.out.println("Volatile mode value: " + value);
    }

    // 内存屏障效果示例
    public void memoryBarrierExample() throws InterruptedException {
        Thread writer = new Thread(() -> {
            // 1. 先写普通字段
            NORMAL_HANDLE.set(this, 42);

            // 2. 再写volatile字段
            // 这会创建一个内存屏障，确保普通字段的写入对其他线程可见
            VOLATILE_HANDLE.setVolatile(this, 1);

            System.out.println("Writer: 完成写入");
        });

        Thread reader = new Thread(() -> {
            // 等待volatile字段变化
            while ((int) VOLATILE_HANDLE.getVolatile(this) == 0) {
                Thread.yield();
            }

            // 由于volatile的内存屏障效果，现在可以看到普通字段的更新
            int normalValue = (int) NORMAL_HANDLE.get(this);
            int volatileValue = (int) VOLATILE_HANDLE.getVolatile(this);

            System.out.println("Reader: normalField = " + normalValue +
                ", volatileField = " + volatileValue);
        });

        reader.start();
        Thread.sleep(100);
        writer.start();

        writer.join();
        reader.join();
    }

    // 双重检查锁定模式示例
    public static class Singleton {
        private static Singleton instance;
        private static final VarHandle INSTANCE_HANDLE;

        static {
            try {
                INSTANCE_HANDLE = MethodHandles.lookup()
                    .findStaticVarHandle(Singleton.class, "instance", Singleton.class);
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }

        public static Singleton getInstance() {
            Singleton result = (Singleton) INSTANCE_HANDLE.getAcquire(Singleton.class);

            if (result == null) {
                synchronized (Singleton.class) {
                    result = (Singleton) INSTANCE_HANDLE.getVolatile(Singleton.class);
                    if (result == null) {
                        result = new Singleton();
                        INSTANCE_HANDLE.setRelease(Singleton.class, result);
                    }
                }
            }

            return result;
        }

        private Singleton() {
            // 私有构造函数
        }
    }
}
