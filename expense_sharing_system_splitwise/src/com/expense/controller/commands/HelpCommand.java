package com.expense.controller.commands;

public class HelpCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Commands:");
        System.out.println("  ADD_USER <user_id> <user_name> <email> <mobile_number>");
        System.out.println("  EXPENSE <paid_by_user_id> <amount> <number_of_users> <user_id_1> <user_id_2> ... <SPLIT_TYPE> [split_details]");
        System.out.println("    SPLIT_TYPE: EQUAL | EXACT | PERCENT");
        System.out.println("  SHOW                 -> show all balances");
        System.out.println("  SHOW <user_id>       -> show balance for one user");
        System.out.println("  SHOW_SUMMARY         -> show consolidated balances");
        System.out.println("  HELP                 -> print this help");
        System.out.println("  EXIT                 -> exit program");
    }
}
