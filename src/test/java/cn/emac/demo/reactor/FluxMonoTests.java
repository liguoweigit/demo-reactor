package cn.emac.demo.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * @author liguowei
 * @time 2018/1/24 15:29
 * @package cn.emac.demo.reactor
 */
public class FluxMonoTests {
    @Test
    public void createFlux(){
        Flux.just("Hello","World").subscribe(System.out::println);
        Flux.fromArray(new String[]{"Hello","World"}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::print);
        Flux.range(1,10).subscribe(System.out::println);
        Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);

        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);


        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);

    }

    @Test
    void createMono(){
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }

}
