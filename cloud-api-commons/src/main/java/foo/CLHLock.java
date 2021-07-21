package foo;

import java.util.concurrent.atomic.AtomicReference;

// FIFO 自旋锁、基于链表、无饥饿性、先到先得的锁，使用volatile保证线程可见，
// 饥饿：一个或者多个线程因为种种原因无法获得所需要的资源，导致一直无法执行的状态
// 尾节点很关键，获取锁的时候把当前节点放入尾结点、后面进来的线程就可以通过尾节点找到当前节点，尾节点的操作使用了原子引用类，使用原子引用类的getAndSet方法确保多线程间安全
public class CLHLock {

    // fasle：未获取到锁或锁已释放 true: 获取到锁或等待中
    private static class Node {
        private volatile boolean lock;
    }

    // 尾部节点引用
    private volatile AtomicReference<Node> tailNode;

    // 当前节点引用
    private  ThreadLocal<Node> cur;

    // 上一个节点引用
    private  ThreadLocal<Node> pre;

    // clh 初始化
    public CLHLock() {
        // tailNode初始化lock为false,为第一个到来得线程获取到锁做准备
        tailNode = new AtomicReference<>(new Node());

        cur = ThreadLocal.withInitial(() -> new Node());

        pre = new ThreadLocal<>();

    }

    // 查看上一个节点锁状态，如果为false则当前线程获取到锁
    public void lock() {
        Node curNode = this.cur.get();
        // 将当前线程的锁状态设置为true表名获取到货等待中
        curNode.lock = true;
        Node preNode = tailNode.getAndSet(curNode);
        pre.set(preNode); // pre节点似乎无用
        while (preNode.lock) { // 查看上一个线程的状态，如果为false，则获取到锁，否则自旋
            System.out.println(Thread.currentThread().getName() + "未获取到锁。。等待中");
        }

    }

    public void unlock() {
        cur.get().lock = false; //volatle其他线程可见，已设置为false则锁立马释放，后一个节点可获取锁
        cur.set(new Node()); // 避免同一个线程中获取到锁然后释放，然后又获取锁进入死循环（不过不加此行则tail节点为当前节点，而当前节点的lock又为true,则当前节点一直自旋）
        System.out.println(Thread.currentThread().getName() + "释放锁");
    }

}

class LockTest {
    public static void main(String[] args) {
        CLHLock lock = new CLHLock();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                lock.unlock();
            }, "Thread-" + i).start();
        }

        /*new Thread(() ->{
            lock.lock();
            lock.unlock();
            lock.lock();
            lock.unlock();
        }).start();*/

    }
}
