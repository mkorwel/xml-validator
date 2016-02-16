# xml-validator
Simple xml validation project. Project using annotation processing and generate a `AttributeValidatorFactory` to validate xml attribute and `ElementValidatorFactory` to validate xml elements.

## How run project

1. Download all repository: `git clone https://github.com/mkorwel/xml-validator.git`
2. Go to the `core` dictionary: `cd xml-validator/core/`
3. Compile and install this artefact in your local repository: `gradle install`
4. Go to the `xml-validator`: `cd ../xml-validator/`
5. Compile and run: `gradle run`

## How to add new validator
Application find all classes annotated by `@ElementValidator` or `@AttributeValidator` and run all validation rules during parsing XML.
Sample classes of validation are in `pl.mkorwel.xml.validator.application.sample.constrain` package.

## Xml file
Sample xml file is in `xml-validator/src/main/resources/sample.xml`.
