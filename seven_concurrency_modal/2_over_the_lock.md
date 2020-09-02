# 내재된 잠금장치를 넘어서  
이전에 봤던 잠금 장치, synchronized 블록은  
한계가 있다.  

- 블로킹 상태에 빠진 스레드를 복귀할 방법 없음
- 잠금장치를 얻을 때 타임아웃 기능 부재
- synchronized 블록만이 방법임.

`ReentrantLock`은 단순히 sync 블록을 넘어  
다양한 잠금장치 기능을 제공한다.

```java
ReentrantLock l1 = new ReentrantLock();

try {
    l1.lockInterruptibly();
} catch (InterruptedException e) {}

```

위 코드는 Thread.interrupt()가 호출되면 종료가 된다.  
데드락 상태에 빠진 스레드를 가로챌 수 있게 한다.

`타임아웃`  
아래와 같은 timeout 기능도 제공한다.  
해당 시간 이내에 lock을 얻지 못하면 exception을 만든다.

```java
ReentrantLock l1 = new ReentrantLock();

try {
    if (l1.tryLock(1000, TimeUnit.MILLISECONDS)) {
        // 동작
        l1.unlock();
    }
} catch (InterruptedException e) {}

```

tryLock을 사용하는데,  
잠금장치를 얻는 데 실패하면, 타임 아웃을 발생시킨다.  
이것이 항상 올바른 해법은 아니지만,  
데드락에서 빠져나올 방법을 제공하므로 나은 방법이다.

조건 변수
```java
ReentrantLock lock = new ReentrantLock();
Condition condition = lock.newCondition();

lock.lock();

try {
    while (bl) {
        condition.await();
    }
} finally { lock.unlock(); }

```

위 코드는 bl이 true면은 condition을 await를 호출하여  
가지고 있던 잠금 장치를 반납하고, singal을 대기하는 상태로 변하게 한다.  
다른 스레드가 signal 또는 signalAll을 호출하면 블로킹을 멈추고 다시 lock을 얻어 bl을 검사한다.

그러므로, 특정 조건을 만족하지 않으면 lock을 하지 않으므로  
식사하는 철학자의 데드락 문제를 해결하는 해법이 된다.


## 원자변수  

변수에 대한 접근을 sync 블록으로 해결하던 솔루션을 봤을 것이다.  
Atomic*** 관련 타입은 더 나은 방법을 제공한다.

이것은 원자적인 처리를 하기에  
컴파일러 최적화에서 발생하는 호출 순서 문제나  
실수로 멀티 쓰레딩을 고려하지 않고 값을 읽거나 수정하는 문제를 방지할 수 있다.  

그리고 이러한 원자 변수는 논블로킹, 락프리 알고리즘의 기초를 제공한다.

## volatile 키워드  
volatile 키워드를 쓴 변수에 대해 읽거나 쓰는 동작에 명령어 순서가 바뀌지 않는다.  
그리고, 항상 RAM에서 읽고, 쓰므로 cache에 있는 상태에서 잘못 읽는 메모리 가시성도 해소할 수 있다.  
다만, 이 키워드를 쓰는 것보다는 원자 변수를 쓰는 것이 더 나을 것이라고 한다.

## 사용시 주의할 점  
wait와 notify는 사용 하기 전  
동기화 블록에 있어야 한다.  
그리고 동기화 블록의 lock 개체와 wait, notify 호출자는 같아야 한다.  
그렇지 않으면, illegalMonitor 예외를 보게 된다.  

마찬가지로 singal, await도 사용하기 전에  
해당 lock 개체를 잠금한 후 사용해야 한다.

wait와 await의 차이는 wait는 항상 동기화 블록이 필요하다는 점?  
인 것 같다.


# 찾아라!

## Fairness을 위한 인수 지원은 무엇을 의미하는가?
- 공정한 잠금장치를 이용해야 하는 이유와 아닌 이유?

## ReentrantReadWriteLock은 무엇인가?

## 불필요한 기상(spurious wakeup)이란 무엇인가?

## AtomicIntegerFieldUpdater는 무엇인가?


# 수행해라!  
- signal과 signalAll의 차이

- ConcurrentSortedList 작성해보기



