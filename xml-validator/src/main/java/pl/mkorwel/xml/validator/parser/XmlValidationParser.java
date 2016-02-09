package pl.mkorwel.xml.validator.parser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by mateusz on 08/02/16.
 */
class XmlValidationParser {
	void parse(String xmlPath) {
		tryValidateFile(xmlPath);
	}

	private void tryValidateFile(String xmlPath) {
		try {
			validateFile(xmlPath);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private void validateFile(String xmlPath) throws SAXException, ParserConfigurationException, IOException {
		XMLReader reader = createReader();
		reader.parse(xmlPath);
	}

	private XMLReader createReader() throws SAXException, ParserConfigurationException {
		SAXParserFactory parserFactory = createParserFactory();

		XMLReader reader = parserFactory.newSAXParser().getXMLReader();
		reader.setContentHandler(new ValidationHandler());

		return reader;
	}

	private SAXParserFactory createParserFactory() {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		parserFactory.setNamespaceAware(true);

		return parserFactory;
	}
}
