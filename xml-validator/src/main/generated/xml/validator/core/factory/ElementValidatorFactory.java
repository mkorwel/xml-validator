package xml.validator.core.factory;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import pl.mkorwel.xml.validator.application.sample.constrain.InvalidNameElementValidator;
import pl.mkorwel.xml.validator.application.sample.constrain.NameLengthElementValidator;
import pl.mkorwel.xml.validator.application.sample.constrain.SurnameLengthElementValidator;
import xml.validator.core.constrain.ValidationConstrain;
import xml.validator.core.domain.AlwaysSuccessValidator;

public class ElementValidatorFactory {
  public List<ValidationConstrain> create(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name is null!");
    }
    if ("name".equals(name)) {
      List<ValidationConstrain> result = new ArrayList<>();
      result.add(new NameLengthElementValidator());
      result.add(new InvalidNameElementValidator());
      return result;
    }
    if ("surname".equals(name)) {
      List<ValidationConstrain> result = new ArrayList<>();
      result.add(new SurnameLengthElementValidator());
      return result;
    }
    List<ValidationConstrain> result = new ArrayList<>();
    result.add(new AlwaysSuccessValidator());
    return result;
  }
}
