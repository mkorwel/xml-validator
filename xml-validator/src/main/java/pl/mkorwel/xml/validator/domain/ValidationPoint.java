package pl.mkorwel.xml.validator.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by mateusz on 08/02/16.
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public abstract class ValidationPoint {
	private final String value;

	public abstract String pointName();
}
