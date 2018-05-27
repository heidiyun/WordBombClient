
// Thread를 만드는 방법 1
//class FooThread extends Thread {
//
//    // 새로운 스레드의 시작 함수
//    @Override
//    public void run() {
//        for (int i = 0; i < 1000; ++i) {
//            System.out.println("Foo: " + i);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}


public class ThreadSample {
    public static void main(String[] args) {
        Thread fooThread = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                System.out.println("Foo: " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 새로운 스레드를 생성해서 정의된 run 함수를 수행한다.
        fooThread.start();

        for (int i = 0; i < 1000; ++i) {
            System.out.println("Main: " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


/*
public class ThreadSample {
    public static void main(String[] args) {
        FooThread fooThread = new FooThread();
        // fooThread.run();

        // 새로운 스레드를 생성해서 정의된 run 함수를 수행한다.
        fooThread.start();

        for (int i = 0; i < 1000; ++i) {
            System.out.println("Main: " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
*/












