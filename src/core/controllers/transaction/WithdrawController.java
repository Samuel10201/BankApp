package core.controllers.transaction;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.TransactionType;
import core.models.storage.Storage;

/**
 *
 * @author familiavasquezbarragan
 */
public class WithdrawController {
    public static Response withdraw(TransactionType type, String sourceAccountid, String destinationAccountid, String amount){
        try{
  
            Storage storage = Storage.getInstance();
            Account sourceAccount = storage.getAccount(sourceAccountid);
            if(sourceAccountid.equals("")){
                    return new Response("Source account most be not empty", Status.BAD_REQUEST);
            }
            try{
                if (sourceAccountid.length() != 13) {
                    return new Response("Source account most be in format XXX-XXXXXX-XX", Status.BAD_REQUEST);
                }
                if(sourceAccount==null){
                    return new Response("Source account does not exists",Status.BAD_REQUEST);
                }
  
            }catch(Exception ex){
                return new Response("",Status.INTERNAL_SERVER_ERROR);
            }
            
            
            if(!destinationAccountid.equals("")){
                return new Response("To make a withdraw, destination account must be empty",Status.BAD_REQUEST);
            }
            
            
            double amountDouble;
            
            if (amount.equals("")) {
                    return new Response("Amount must be not empty", Status.BAD_REQUEST);
            }
            try{
                amountDouble=Double.parseDouble(amount);
                if(amountDouble<0){
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
                if (amountDouble == 0) {
                    return new Response("Amount must be bigger than 0", Status.BAD_REQUEST);
                }
                
            }catch(NumberFormatException ex){
                return new Response("Amount must be numeric", Status .BAD_REQUEST);
            }
            
  
            if(sourceAccount.getBalance()-amountDouble<0){
                return new Response("You can not withdraw that amount from the source account",Status.BAD_REQUEST);
            }
            
            
            sourceAccount.setBalance(sourceAccount.getBalance()-amountDouble);
            storage.Deposit(new Transaction(type,sourceAccount,null,amountDouble));
            return new Response ("Successfull withdraw",Status.OK);
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
}
