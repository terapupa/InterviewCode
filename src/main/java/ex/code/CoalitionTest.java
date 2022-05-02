package ex.code;

import java.util.HashMap;
import java.util.Map;

public class CoalitionTest {


/*
  Implement the functionality in the `rateLimit` function that acts as a pass/fail on whether an
  incoming identifier is allowed to execute or not.  Method should return true or false
 */


    private int interval;
    private int calls;

    Map<String, Integer> intervalMap = new HashMap<>();


    public CoalitionTest(int interval, int calls) {
      this.interval = interval;
      this.calls = calls;
    }

    public boolean rateLimit(String identifier) {
      System.currentTimeMillis();
      intervalMap.merge(identifier, 1, Integer::sum);
      if (intervalMap.get(identifier) > this.calls) {
        intervalMap.put(identifier, 0);
        return false;
      }
      return true;
    }
  }


  class Solution {


    public static final int INTERVAL_SECONDS = 1;
    public static final int MAXIMUM_REQUESTS = 2;

    public static void main(String[] args) throws InterruptedException {
      CoalitionTest rateLimiter = new CoalitionTest(INTERVAL_SECONDS, MAXIMUM_REQUESTS);
      assert rateLimiter.rateLimit("something") == true;
      assert rateLimiter.rateLimit("something") == true;
      assert rateLimiter.rateLimit("something") == false;

      Thread.sleep(INTERVAL_SECONDS * 3);

      assert rateLimiter.rateLimit("something") == true;
      assert rateLimiter.rateLimit("something") == true;
      assert rateLimiter.rateLimit("something") == false;

      assert rateLimiter.rateLimit("some-random-uuid") == true;
    }


}
