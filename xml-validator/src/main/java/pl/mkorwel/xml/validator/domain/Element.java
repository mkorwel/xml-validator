package pl.mkorwel.xml.validator.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by mateusz on 07/02/16.
 */
@Getter
@Accessors(fluent = true)
public class Element extends ValidationPoint {
	private final String name;

	@Builder
	public Element(String value, String name) {
		super(value);
		this.name = name;
	}

	@Override
	public String pointName() {
		return name;
	}
}
