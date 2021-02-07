package com.techelevator.tenmo;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.util.Scanner;

import com.techelevator.tenmo.models.Accounts;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AccountsServices;
import com.techelevator.tenmo.services.ApiServiceBase;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.tenmo.services.TransferServices;
import com.techelevator.view.ConsoleService;
import org.springframework.web.client.RestTemplate;


public class App {

private static final String API_BASE_URL = "http://localhost:8080/";
    
    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private Scanner scanner = new Scanner(System.in);
	private AuthenticatedUser currentUser;
	private ConsoleService console;
	private AuthenticationService authenticationService;
	private RestTemplate apiCall = new RestTemplate();

//    public AccountsServices accountsServices = new AccountsServices(API_BASE_URL);
//    public TransferServices transferServices = new TransferServices(API_BASE_URL);
    
    public static void main(String[] args) {
    	App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
    	app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;

	}

	public void run() {
//		System.out.println("*********************");
//		System.out.println("* Welcome to TEnmo! *");
//		System.out.println("*********************");
		System.out.println
				("████████╗███████╗███╗░░██╗███╗░░░███╗░█████╗░\n" +
				 "╚══██╔══╝██╔════╝████╗░██║████╗░████║██╔══██╗\n" +
				 "░░░██║░░░█████╗░░██╔██╗██║██╔████╔██║██║░░██║\n" +
				 "░░░██║░░░██╔══╝░░██║╚████║██║╚██╔╝██║██║░░██║\n" +
				 "░░░██║░░░███████╗██║░╚███║██║░╚═╝░██║╚█████╔╝\n" +
				 "░░░╚═╝░░░╚══════╝╚═╝░░╚══╝╚═╝░░░░░╚═╝░╚════╝░");

		System.out.println
				 ("Ⓐ ⓉⒺⒶⓂ ⓂⒶⒿⓇ ⒶⓅⓅⓁⒾⒸⒶⓉⒾⓄⓃ");

		
		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
		AccountsServices accountsServices = new AccountsServices(API_BASE_URL, currentUser);
		BigDecimal balance = accountsServices.getBalance();
		System.out.println(balance);
		
	}

	private void viewTransferHistory() {
		System.out.println("________________________________________________________________");
		System.out.println();
    	System.out.println("Enter (1) view transaction history.");
		System.out.println("Enter (2) view transaction details.");
		System.out.println("________________________________________________________________");

		String oneOrTwo = scanner.nextLine();
		int usersChoice = 0;
		usersChoice = Integer.parseInt(oneOrTwo);

		if (usersChoice == 1) {
			try {
				ResponseEntity<transfers[]> transferListFrom = apiCall
						.getForEntity(API_BASE_URL + "transfers/" + currentUser.getUser().getId(), transfers[].class);
				List<transfers> listAllTransfersFrom = Arrays.asList(transferListFrom.getBody());

				ResponseEntity<transfers[]> transferListTo = apiCall.getForEntity(
						API_BASE_URL + "transfers/user_to/" + currentUser.getUser().getId(), transfers[].class);
				List<transfers> listAllTransfersTo = Arrays.asList(transferListTo.getBody());
				System.out.println("Listing all transfers: ------------------");

				User user = new User();
				accounts account = new accounts();
				// list all transfers from current user to another user
				if (listAllTransfersTo.size() > 0) {
					for (transfers to : listAllTransfersTo) {
						account = getAcctById(to.getAccount_from());
						user = getUserById(account.getUser_id());
						System.out.println("From " + user.getUsername() + "       Amount: " + to.getAmount());
					}
				} // list all transactions from another user to current user
				if (listAllTransfersFrom.size() > 0) {
					for (transfers t : listAllTransfersFrom) {
						account = getAcctById(t.getAccount_to());
						user = getUserById(account.getUser_id());
						System.out.println("To " + user.getUsername() + "         Amount: " + t.getAmount());
					}
				}
			} catch (NullPointerException ex) {
				System.out.println("Null Pointer Error.");
			}
		}

		if (usersChoice == 2) {
			System.out.println("Enter the ID of the transfer you want to see details for: ");
			String userIdChoice = scanner.nextLine();
			int transfer_id = Integer.parseInt(userIdChoice);

			try {
				transfers detailsTransfer = apiCall.getForObject(API_BASE_URL + "/transfers/details/" + transfer_id,
						transfers.class);

				User userNameTo = apiCall
						.getForObject(API_BASE_URL + "users/account/" + detailsTransfer.getAccount_to(), User.class);
				User userNameFrom = apiCall
						.getForObject(API_BASE_URL + "users/account/" + detailsTransfer.getAccount_from(), User.class);

				System.out.println("Details for transfer " + transfer_id);
				System.out.println("       ***        ");
				System.out.println("Transfer ID: " + detailsTransfer.getTransfer_id());
				System.out.println("Account to: " + userNameTo.getUsername());
				System.out.println("Account from: " + userNameFrom.getUsername());
				if (detailsTransfer.getTransfer_type_id() == 1) {
					System.out.println("Transfer type ID: " + "Received");
				} else if (detailsTransfer.getTransfer_type_id() == 2) {
					System.out.println("Transfer type ID: " + "Sent");
				}
				System.out.println("Transfer status ID:  Success");
				System.out.println("Amount: " + detailsTransfer.getAmount());
			} catch (NullPointerException ex) {
				System.out.println("Null Pointer Exception - transfer ID does not exist. Try again.");
				return;
			}
		} else if (usersChoice != 1 || usersChoice != 2) {
			return;
		}
	}
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		//Once user list is corrected in transferservices need to call on that function here too
		TransferServices transferServices = new TransferServices(API_BASE_URL, currentUser);
		List<User> users = transferServices.listUser();
		for(User user:users)
		{
			System.out.println(user.getId() + " " + user.getUsername());
			
		}
		
		console.getUserId();
		console.getUserInputInteger(MAIN_MENU_OPTION_SEND_BUCKS);
		
		
		
		//Transfers transfers = transferServices.sendBucks();
		
//		int receiversacctId = transfers.getAccountTo();
//		BigDecimal amtTransfrd = transfers.getTransferAmount();
//		Accounts account = accountsServices.getUpdatedBalanceFromSender(int userId, amtTransfrd);
//		Accounts account1 = accountsServices.getReceiversNewBalance(receiversacctId, amtTransfrd);
		// TODO Auto-generated method stub
		
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}
	
	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
            	authenticationService.register(credentials);
            	isRegistered = true;
            	System.out.println("Registration successful. You can now login.");
            } catch(AuthenticationServiceException e) {
            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
            }
        }
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);
				ApiServiceBase.setUser(currentUser);
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}
	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
}
