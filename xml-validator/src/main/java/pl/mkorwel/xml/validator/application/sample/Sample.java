package pl.mkorwel.xml.validator.application.sample;

import pl.mkorwel.xml.validator.parser.XmlValidator;

/**
 * Created by mateusz on 07/02/16.
 */
public class Sample {
	public static void main(String args[]) {
		System.out.println("Program started !!!\n");

		XmlValidator parser = new XmlValidator();
		parser.validate("src/main/resources/sample.xml");
	}
}