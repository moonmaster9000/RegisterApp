package cashregister.stepdefs;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.observers.AddItemToTransactionObserver;
import cashregister.support.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static cashregister.domain.usecases.AddItemToTransaction.*;
import static cashregister.domain.usecases.CreateItem.createItem;
import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static cashregister.domain.usecases.PresentTransaction.presentTransaction;
import static org.junit.Assert.assertEquals;

public class PurchaseSingleItem {
    private PresentTransactionObserverSpy presentTransactionObserver = new PresentTransactionObserverSpy();

    @Given("^an admin has added an item to the system$")
    public void anAdminHasAddedAnItemToTheSystem() throws Throwable {
        createItem(item, createItemObserver, itemRepo);
    }

    @When("^a cashier scans in the barcode of that item$")
    public void aCashierScansInTheBarcodeOfThatItem() throws Throwable {
        createTransaction(createTransactionObserver, transactionRepo);

        addItemToTransaction(
                item.getBarcode(),
                createTransactionObserver.spyCreatedTransaction().getId(),
                transactionRepo,
                itemRepo,
                addItemToTransactionObserver
        );
    }

    @Then("^the register should display a total equal to the price of that item$")
    public void theRegisterShouldDisplayATotalEqualToThePriceOfThatItem() throws Throwable {
        presentTransaction(createTransactionObserver.spyCreatedTransaction().getId(), presentTransactionObserver, transactionRepo);

        assertEquals(
                presentTransactionObserver.spyPresentedTransaction().getTotalInCents(),
                item.getPriceInCents()
        );
    }

    private CreateItemObserverSpy createItemObserver = new CreateItemObserverSpy();
    private ItemRepository itemRepo = new FakeItemRepository();
    private TransactionRepository transactionRepo = new FakeTransactionRepository();
    private CreateTransactionObserverSpy createTransactionObserver = new CreateTransactionObserverSpy();
    private AddItemToTransactionObserver addItemToTransactionObserver = new AddItemToTransactionObserverSpy();
    private Item item = new Item("item", "barcode", 1);
}
