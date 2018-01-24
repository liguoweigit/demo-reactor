package cn.emac.demo.reactor;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import cn.emac.demo.reactor.repositories.ImperativeRestaurantRepository;

/**
 * @author Emac
 * @since 2017-06-04
 */
public class BaseTests {
    public static final int CONCURRENT_SIZE = 100;
    public static final int PACK_SIZE       = 10_000;
    private LocalDateTime   start;

    @AfterEach
    public void afterEach() {
        System.out.println(Duration.between(start, LocalDateTime.now()).toMillis());
    }

    @BeforeEach
    public void beforeEach() {

        // start from scratch
        ImperativeRestaurantRepository.INSTANCE.deleteAll();
        start = LocalDateTime.now();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
