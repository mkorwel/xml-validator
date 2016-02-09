# xml-validator
Simple xml validation project. Project using annotation processing and generate a `AttributeValidatorFactory` to validate xml attribute and `ElementValidatorFactory` to validate xml elements.

## How run project

1. Download all repository
2. Go to the `code` dictionary and run: `gradle install`
3. Go to the `xml-validator` and run: `gradle run`

## How to add new validator
Application find all classes annotated by `@ElementValidator` or `@AttributeValidator` and run all validation rules during parsing XML.
Sample classes of validation are in `pl.mkorwel.xml.validator.application.sample.constrain` package.

## Xml file
Sample xml file is in `xml-validator/src/main/resources/sample.xml`.
