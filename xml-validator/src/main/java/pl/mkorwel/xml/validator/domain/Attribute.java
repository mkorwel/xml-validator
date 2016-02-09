package pl.mkorwel.xml.validator.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by mateusz on 07/02/16.
 */
@Getter
@Accessors(fluent = true)
public class Attribute extends ValidationPoint {
	private final String elementName;
	private final String name;

	@Builder
	public Attribute(String value, String elementName, String name) {
		super(value);
		this.elementName = elementName;
		this.name = name;
	}

	@Override
	public String pointName() {
		return elementName + "[" + name + "]";
	}
}
