package invidi.code;

import java.util.Properties;
import java.util.function.Function;

public class Generic {

  interface PingService {
    String ping(Long ping);
  }

  interface PongService {
    Integer pong(String pong);
  }

  public PingService pingService = ping -> "" + ping;
  public PongService pongService = String::length;

  static <T, R> Function<T, R> retryFunction(Properties properties, T input, Function<T, R> function) {
    int retries = Integer.parseInt((String) properties.get("retries"));
    int iteration = 0;
    do {
      try {
        return (T t) -> function.apply(input);
      } catch (RuntimeException e) {
        if (iteration == retries) {
          throw e;
        } else {
          iteration++;
        }
      }
    } while (true);
  }

  public String retryPing(Long ping) {
    Properties properties = new Properties();
    properties.setProperty("retries", "3");
    Function<Long, String> pingRetryFn = retryFunction(properties, ping, input -> pingService.ping(input));
    return pingRetryFn.apply(ping);
  }

  public Integer retryPong(String pong) {
    Properties properties = new Properties();
    properties.setProperty("retries", "5");
    Function<String, Integer> pongRetryFn = retryFunction(properties, pong, input -> pongService.pong(input));
    return pongRetryFn.apply(pong);
  }

  public static <I, R> R retryGeneric(int retry, I param, Function<I, R> function) {
    Properties properties = new Properties();
    properties.setProperty("retries", String.valueOf(retry));
    Function<I, R> pingFn = retryFunction(properties, param, function);
    return pingFn.apply(param);
  }

  public static void main(String[] arg) {
    Generic generic = new Generic();
    String pingResult = generic.retryPing(134232323234L);
    System.out.println("ping is a string = " + pingResult);
    Integer pongResult = generic.retryPong("345");
    System.out.println("pong is an integer = " + pongResult);

    String genericPingResult = retryGeneric(3, 134232323234L, input -> generic.pingService.ping(input));
    System.out.println("decorateGeneric ping is a string = " + genericPingResult);
    Integer genericPongResult = retryGeneric(5, "1", input -> generic.pongService.pong(input));
    System.out.println("decorateGeneric pong is an integer = " + genericPongResult);

  }
}


//  public static <T, R> R decorateGeneric(T param, Function<T, R> function) {
//    Function<T, R> pingFn = decorateFunction(param, function);
//    return pingFn.apply(param);
//  }

//  public static void main(String[] arg)
//  {
//    Generic generic = new Generic();
//    String pingResult = generic.decoratePing(1);
//    Integer pongResult = generic.decoratePong("1");
//
//
//    String genericPingResult = decorateGeneric(1, input -> generic.pingService.ping(input));
//    Integer genericPongResult = decorateGeneric("1", input -> generic.pongService.pong(input));
//  }


//}
