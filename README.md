# Sale Taxes documentation

To resolve the application I choose to use only java core without any framework like Spring. In the first part of the documentation 
will be described the approach that was taken and the last part would be a short description if I would use Spring framework.  

#### Current approach  

In order to run the application is required to provide a config file and 3 files under resources' folder with the input for testing.  

In order to have a configurable application I created a config.json file that holds the configuration for the application. 
Having a configuration file the application could be easily extended. For example: base_tax_exceptions and import_tax_exceptions can be extended with new categories. 
Besides, in categories_mappings could be added new keywords for a category or could be added a new category.  

For converting the input of the file into java objects. I created a Parser interface that has an implementation TxtFileParser. Each line of the file should correspond 
to a regex pattern if it doesn't then an exception will be thrown, if in the name of a product couldn't be identified the category then an exception will be thrown.  
Once having the ShopCartItems are created the invoice should be created for them. Therefore, an InvoiceService interface that has an implementation InvoiceServiceImpl which
is charge to prepare the invoice and calculate prices and taxes for all ShopCartItems.  
For calculation taxes it was used the Strategy Pattern as follows: a AllTaxesCalculator class is responsible for having all TaxCalculator and filtering 
them based on their eligibility. For all eligible TaxCalculators a context is created and called the Strategy.  
Once taxes and prices were computed the ShopCartItems are sent to be rendered the PrintLineRenderer is responsible for writing in System.out.  

The majority of classes were created in a such way that, if the Spring framework would be integrated the DI would work. 
I created unit tests for all public methods.

#### Spring approach  

In order to run the application is required to provide a config file and the file would be converted in  
a Config bean and afterwards it would be injected wherever is needed. This should be written in a @Configuration class.
For each interface I would create a bean.
In order to calculate all Taxes there are multiple options one of them is:  
Injecting all implementation of TaxCalculator in AllTaxesCalculatorImpl:
``` 
@Service
public class AllTaxesCalculatorImpl implements AllTaxesCalculator {

    private final List<TaxCalculator> taxCalculators;

    public AllTaxesCalculator(List<TaxCalculator> taxCalculators) {
        this.taxCalculators = taxCalculators;
    }
    public BigDecimal calculateAllTaxes(final Item item, final Config config) {
        return taxCalculators.stream()
                .filter(taxCalculator -> taxCalculator.isEligible(item, config))
                .map(taxCalculator -> new ContextTaxCalculator(taxCalculator).calculateTax(item, config))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

```
