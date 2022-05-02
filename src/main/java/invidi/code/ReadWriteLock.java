package invidi.code;


public class ReadWriteLock {
  private int readers       = 0;
  private int writers       = 0;
  private int writeRequests = 0;

  public synchronized void lockRead() throws InterruptedException{
    while(writers > 0 || writeRequests > 0){
      wait();
    }
    readers++;
  }

  public synchronized void unlockRead(){
    readers--;
    notifyAll();
  }

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    while(readers > 0 || writers > 0){
      wait();
    }
    writeRequests--;
    writers++;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writers--;
    notifyAll();
  }
}


//public class A {
//
//  private int var1 = 0;
//  private int var2 = 0;
//  private int var3 = 0;
//
//  public synchronized void method1() throws InterruptedException {
//    while (var2 > 0 || var3 > 0) {
//      wait();
//    }
//    var1++;
//  }
//
//  public synchronized void method2() {
//    var1--;
//    notifyAll();
//  }
//
//  public synchronized void method3() throws InterruptedException {
//    var3++;
//    while (var1 > 0 || var2 > 0) {
//      wait();
//    }
//    var3--;
//    var2++;
//  }
//
//  public synchronized void method4() throws InterruptedException {
//    var2--;
//    notifyAll();
//  }

//}

