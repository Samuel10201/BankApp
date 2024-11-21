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
public class DepositController {
    public static Response deposit(TransactionType type, String sourceAccountid, String destinationAccountid, String amount){
        try{
            
            if(!sourceAccountid.equals("")){
                return new Response("To make a deposit, source account must be empty",Status.BAD_REQUEST);
            }
            
            
            Storage storage = Storage.getInstance();
            Account destinationAccount = storage.getAccount(destinationAccountid);
            
            if(destinationAccountid.equals("")){
                    return new Response("Destination account most be not empty", Status.BAD_REQUEST);
            }
            try{
                if (destinationAccountid.length() != 13) {
                    return new Response("Destination account most be in format XXX-XXXXXX-XX", Status.BAD_REQUEST);
                }
                if(destinationAccount==null){
                    return new Response("Destination account does not exists",Status.BAD_REQUEST);
                }
  
            }catch(NumberFormatException ex){
                return new Response("Destination account must be numeric", Status .BAD_REQUEST);
            }
            
            double amountDouble;
            
            if (amount.equals("")) {
                    return new Response("Amount must be not empty", Status.BAD_REQUEST);
            }
            try{
                amountDouble=Double.parseDouble(amount);
                if(amountDouble < 0){
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
                if (amountDouble == 0) {
                    return new Response("Amount must be bigger than 0", Status.BAD_REQUEST);
                }
                
            }catch(NumberFormatException ex){
                return new Response("Amount must be numeric", Status .BAD_REQUEST);
            }
            
            
            destinationAccount.setBalance(destinationAccount.getBalance()+amountDouble);
            storage.Deposit(new Transaction(type,null,destinationAccount,amountDouble));
            return new Response ("Successfull deposit",Status.OK);
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
