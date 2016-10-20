package cashregister.domain.usecases;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.observers.PresentTransactionObserver;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

import static cashregister.domain.Constraint.EXISTS;

public class PresentTransaction {
    private final String transactionId;
    private PresentTransactionObserver observer;
    private final TransactionRepository transactionRepo;

    public PresentTransaction(String transactionId, PresentTransactionObserver presentTransactionObserver, TransactionRepository transactionRepo) {
        this.transactionId = transactionId;
        this.observer = presentTransactionObserver;
        this.transactionRepo = transactionRepo;
    }

    public void execute() {
        new Execute().invoke();
    }

    public static void presentTransaction(String transactionId, PresentTransactionObserver presentTransactionObserver, TransactionRepository transactionRepo) {
        new PresentTransaction(transactionId, presentTransactionObserver, transactionRepo).execute();
    }

    private class Execute {

        private final List<ValidationError> errors;
        private final Transaction transaction;

        Execute() {
            errors = new ArrayList<>();
            transaction = transactionRepo.findById(transactionId);
        }

        void invoke() {
            if (transactionNotFound()){
                sendValidationErrorsToObserver();
            } else {
                sendTransactionToObserver();
            }
        }

        private void sendTransactionToObserver() {
            observer.transactionPresented(transaction);
        }

        private void sendValidationErrorsToObserver() {
            errors.add(new ValidationError("transactionId", EXISTS));
            observer.validationFailed(errors);
        }

        private boolean transactionNotFound() {
            return transaction == null;
        }
    }
}
