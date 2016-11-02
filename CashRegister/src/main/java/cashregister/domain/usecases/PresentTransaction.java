package cashregister.domain.usecases;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.exceptions.InvalidRequest;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

import static cashregister.domain.Constraint.EXISTS;

public class PresentTransaction {
    private final String transactionId;
    private final TransactionRepository transactionRepo;

    public PresentTransaction(String transactionId, TransactionRepository transactionRepo) {
        this.transactionId = transactionId;
        this.transactionRepo = transactionRepo;
    }

    public Transaction execute() {
        return new Execute().invoke();
    }

    public static Transaction presentTransaction(String transactionId, TransactionRepository transactionRepo) {
        return new PresentTransaction(transactionId, transactionRepo).execute();
    }

    private class Execute {
        private final List<ValidationError> errors;
        private final Transaction transaction;

        Execute() {
            errors = new ArrayList<>();
            transaction = transactionRepo.findById(transactionId);
        }

        Transaction invoke() {
            if (transactionNotFound()){
                throw validationErrors();
            } else {
                return transaction;
            }
        }

        private InvalidRequest validationErrors() {
            errors.add(new ValidationError("transactionId", EXISTS));
            throw new InvalidRequest(errors);
        }

        private boolean transactionNotFound() {
            return transaction == null;
        }
    }
}
