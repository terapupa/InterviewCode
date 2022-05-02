package invidi.code;

public class ReadWriteLockNoRequest {
  private int readers       = 0;
  private int writers       = 0;

  public synchronized void lockRead() throws InterruptedException{
    while(writers > 0){
      wait();
    }
    readers++;
  }

  public synchronized void unlockRead(){
    readers--;
    notifyAll();
  }

  public synchronized void lockWrite() throws InterruptedException{
    while(readers > 0 || writers > 0){
      wait();
    }
    writers++;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writers--;
    notifyAll();
  }
}

//public class A {
//  private int readers = 0;
//  private int writers = 0;
//
//  public synchronized void method1() throws InterruptedException{
//    while(writers > 0){
//      wait();
//    }
//    readers++;
//  }
//
//  public synchronized void method2(){
//    readers--;
//    notifyAll();
//  }
//
//  public synchronized void method3() throws InterruptedException{
//    while(readers > 0 || writers > 0){
//      wait();
//    }
//    writers++;
//  }
//
//  public synchronized void method4() throws InterruptedException{
//    writers--;
//    notifyAll();
//  }
//}

