package cashregister.stepdefs;

import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.CreateItem;
import cashregister.domain.usecases.CreateItemObserver;
import cashregister.support.FakeItemRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseSingleItem {
    private CreateItemObserver observer;
    private ItemRepository itemRepo = new FakeItemRepository();

    @Given("^an admin has added \"([^\"]*)\" brand milk to the cash register system, including display name, barcode, and price in cents$")
    public void anAdminHasAddedBrandMilkToTheCashRegisterSystemIncludingDisplayNameBarcodeAndPriceInCents(String displayName) throws Throwable {
        CreateItem.createItem(displayName, "barcode", 1, observer, itemRepo);
    }

    @When("^a cashier scans in the barcode of \"([^\"]*)\"$")
    public void aCashierScansInTheBarcodeOf(String displayName) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the register should display a total equal to the price of the \"([^\"]*)\"$")
    public void theRegisterShouldDisplayATotalEqualToThePriceOfThe(String displayName) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
