package xml.validator.core.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by mateusz on 07/02/16.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationResult {
	private final Optional<String> message;

	public static ValidationResult successful() {
		return new ValidationResult(Optional.empty());
	}

	public static ValidationResult faild(String message) {
		return new ValidationResult(Optional.of(Objects.requireNonNull(message)));
	}

	public boolean isValid() {
		return !message.isPresent();
	}

	public boolean isNotValid() {
		return !isValid();
	}

	public String getMessage() {
		return message.get();
	}
}
