# Digital-Banking-Application

## Modes
- Root [Individual bank]
- User

## Functions for each mode
### Root
- **Add customer** (Requires to specify which particular branch)
- **Show customers** (Lists customers assocaited with the bank, with a list of transcations for each customer)
- **Add branch** (Requires name/ location for new branch)
- **List branches**

### User
- **Interact Etransfer** feature (Must specify the name of person to etransfer)
- **Withdraw** (Default service fee will be charged if the bank associated with the current atm is not associated with the user of the atm)
- **Deposit** (Same restrictions as withdrawing)
- **Find associated branch** (The bank associated with the atm must be associated with the customer for this option)
- **Current Balance** (Returns the current balance of the user if the user is associated with the bank of the atm)

## To-do(s)
1. Add security token for root accounts as well as for each user
2. Remove STD I/O and create a interactive display for the application (Will vary depending on modes)
3. Add a Bank of Canada class to manage the schedule 1 banks