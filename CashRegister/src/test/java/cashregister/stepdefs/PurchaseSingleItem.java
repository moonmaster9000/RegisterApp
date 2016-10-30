package cashregister.stepdefs;

import cashregister.domain.entities.Item;
import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.support.FakeItemRepository;
import cashregister.support.FakeTransactionRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static cashregister.domain.usecases.AddItemToTransaction.addItemToTransaction;
import static cashregister.domain.usecases.CreateItem.createItem;
import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static cashregister.domain.usecases.PresentTransaction.presentTransaction;
import static org.junit.Assert.assertEquals;

public class PurchaseSingleItem {

    @Given("^an admin has added an item to the system$")
    public void anAdminHasAddedAnItemToTheSystem() throws Throwable {
        createItem(item, itemRepo);
    }

    @When("^a cashier scans in the barcode of that item$")
    public void aCashierScansInTheBarcodeOfThatItem() throws Throwable {
        transaction = createTransaction(transactionRepo);

        addItemToTransaction(
                item.getBarcode(),
                transaction.getId(),
                transactionRepo,
                itemRepo
        );
    }

    @Then("^the register should display a total equal to the price of that item$")
    public void theRegisterShouldDisplayATotalEqualToThePriceOfThatItem() throws Throwable {
        Transaction presentedTransaction = presentTransaction(transaction.getId(), transactionRepo);

        assertEquals(
                presentedTransaction.getTotalInCents(),
                item.getPriceInCents()
        );
    }

    private ItemRepository itemRepo = new FakeItemRepository();
    private TransactionRepository transactionRepo = new FakeTransactionRepository();
    private Item item = new Item("item", "barcode", 1);
    private Transaction transaction;
}
