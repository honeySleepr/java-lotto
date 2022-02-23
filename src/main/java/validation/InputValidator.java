package validation;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Lotto;

public class InputValidator {
    // TODO: 리팩
    public int validateInteger(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            if (input == null || !input.matches("\\d+")) {
                throw new IllegalArgumentException("숫자를 입력해주세요.");
            }
            throw new IllegalArgumentException("int 범위 내로 입력해주세요.");
        }
    }

    public List<Integer> validateWinningNumber(String input) {
        String[] split = input.replace(" ", "").split(",");

        Set<Integer> numberSet = Arrays.stream(split)
            .map(this::validateInteger)
            .map(this::validateLottoNumber)
            .collect(Collectors.toSet());
        if (numberSet.size() != Lotto.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(
                "중복 없는 " + Lotto.LOTTO_NUMBER_COUNT + "개의 번호를 입력해주세요.");
        }
        return List.copyOf(numberSet);
    }

    public int validateLottoNumber(int input) {
        if (input < Lotto.MIN_LOTTO_NUMBER || input > Lotto.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(Lotto.MIN_LOTTO_NUMBER
                + "~"
                + Lotto.MAX_LOTTO_NUMBER
                + " 내의 숫자를 입력해주세요.");
        }
        return input;
    }

    public int validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        boolean isDuplicate = winningNumbers.stream()
            .anyMatch(n -> n == bonusNumber);
        if (isDuplicate) {
            throw new IllegalArgumentException("중복되지 않는 값을 입력해주세요.");
        }
        return bonusNumber;
    }
}