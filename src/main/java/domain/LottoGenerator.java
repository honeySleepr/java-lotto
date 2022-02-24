package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final List<Integer> allNumbers;

    static {
        allNumbers = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
    }

    private LottoGenerator() {
    }

    public static Lotto generate() {
        Collections.shuffle(allNumbers);
        List<Integer> randomNumbers = List.copyOf(allNumbers.subList(0, 6));
        return new Lotto(randomNumbers);
    }
}