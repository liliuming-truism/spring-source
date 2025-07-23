package top.truism.blog.jdk9.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class PlainModeExample {
    private int value = 0;
    private static final VarHandle VALUE_HANDLE;

    static {
        try {
            VALUE_HANDLE = MethodHandles.lookup()
                .findVarHandle(PlainModeExample.class, "value", int.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception{
        PlainModeExample plainModeExample = new PlainModeExample();
        plainModeExample.demonstratePlainMode();

        plainModeExample.multiThreadExample();
    }

    public void demonstratePlainMode() {
        // Plain模式的读写操作
        VALUE_HANDLE.set(this, 42);        // 普通写
        int result = (int) VALUE_HANDLE.get(this);  // 普通读

        System.out.println("Plain mode value: " + result);

        // 注意：Plain模式不保证内存可见性
        // 在多线程环境下，其他线程可能看不到这个修改
    }

    // 多线程示例 - 展示Plain模式的问题
    public void multiThreadExample() throws InterruptedException {
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                VALUE_HANDLE.set(this, i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        Thread reader = new Thread(() -> {
            int lastSeen = -1;
            for (int i = 0; i < 1000; i++) {
                int current = (int) VALUE_HANDLE.get(this);
                if (current != lastSeen) {
                    System.out.println("Reader saw: " + current);
                    lastSeen = current;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        writer.start();
        reader.start();
        writer.join();
        reader.join();
    }
}
