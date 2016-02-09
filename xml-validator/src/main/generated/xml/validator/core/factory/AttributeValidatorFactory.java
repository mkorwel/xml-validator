package xml.validator.core.factory;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import pl.mkorwel.xml.validator.application.sample.constrain.MaxNumberAttributeValidator;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.AlwaysSuccessValidator;

public class AttributeValidatorFactory {
  public List<ValidationConstrain> create(String elementName, String name) {
    if (elementName == null) {
      throw new IllegalArgumentException("elementName is null!");
    }
    if (name == null) {
      throw new IllegalArgumentException("name is null!");
    }
    if ("person".equals(elementName) && "number".equals(name)) {
      List<ValidationConstrain> result = new ArrayList<>();
      result.add(new MaxNumberAttributeValidator());
      return result;
    }
    List<ValidationConstrain> result = new ArrayList<>();
    result.add(new AlwaysSuccessValidator());
    return result;
  }
}
