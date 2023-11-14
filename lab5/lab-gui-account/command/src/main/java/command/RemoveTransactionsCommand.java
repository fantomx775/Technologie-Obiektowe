package command;

import model.Account;
import model.Transaction;

public class RemoveTransactionsCommand implements Command {

    private Transaction transactionToRemove;
    private Account account;

    public RemoveTransactionsCommand(Transaction transactionToRemove, Account account) {
        this.transactionToRemove = transactionToRemove;
        this.account = account;
    }

    @Override
    public void execute() {
        account.removeTransaction(transactionToRemove);
    }

    @Override
    public String getName() {
        return "Remove transaction: " + transactionToRemove.toString();
    }

    @Override
    public void undo() {
        account.addTransaction(transactionToRemove);
    }

    @Override
    public void redo() {
        execute();
    }
}
