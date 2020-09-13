public class CommandHandler {
    private Manager manager;

    public CommandHandler(Manager manager) {
        this.manager = manager;
    }

    public void parseCommand(String str) {
        String[] tokens = str.split("\\s", 2);

        if(tokens.length == 1) {
            if(tokens[0].equalsIgnoreCase("all")) {
                manager.printTasks();
            } else if(tokens[0].equalsIgnoreCase("completed")) {
                manager.printCompleted();
            } else {
                System.out.println("Command not found");
            }
        } else {
            if(tokens[0].equalsIgnoreCase("add")) {
                manager.addTask(tokens[1]);
            } else if(tokens[0].equalsIgnoreCase("delete")) {
                int id = -1;
                try {
                    id = Integer.parseInt(tokens[1]);
                } catch (NumberFormatException e) {
                    System.out.println("incorrect id format (use decimal integer)");
                }
                manager.deleteTask(id);
            } else if(tokens[0].equalsIgnoreCase("load")) {
                manager.loadFromFile(tokens[1]);
            } else if(tokens[0].equalsIgnoreCase("save")) {
                manager.saveTasks(tokens[1]);
            } else if(tokens[0].equalsIgnoreCase("complete")) {
                int id = -1;
                try {
                    id = Integer.parseInt(tokens[1]);
                } catch (NumberFormatException e) {
                    System.out.println("incorrect id format (use decimal integer)");
                }
                manager.completeTask(id);
            }
        }
    }
}
