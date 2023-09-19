package dawson;

import java.util.Scanner;

import dawson.command.Command;
import dawson.command.Echo;
import dawson.command.Exit;

public class Dawson {
    /**
     * Print given input text together with a line as separator
     * 
     * @param text
     */
    public static void printText(String text) {
        String line = "\t_______________________________________________________" + System.lineSeparator();

        System.out.println(line);
        System.out.println("\t " + text);
        System.out.println(line);
    }

    public static void printText(String[] texts) {
        String line = "\t_______________________________________________________" + System.lineSeparator();

        System.out.println(line);
        for (String text : texts) {
            System.out.println("\t " + text);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {

        String introText = "Hello! My name is: ";
        String welcomeText = "What can I do for you?";
        String[] dawsonText = {
                introText,
                "██████╗░░█████╗░░██╗░░░░░░░██╗░██████╗░█████╗░███╗░░██╗",
                "██╔══██╗██╔══██╗░██║░░██╗░░██║██╔════╝██╔══██╗████╗░██║",
                "██║░░██║███████║░╚██╗████╗██╔╝╚█████╗░██║░░██║██╔██╗██║",
                "██║░░██║██╔══██║░░████╔═████║░░╚═══██╗██║░░██║██║╚████║",
                "██████╔╝██║░░██║░░╚██╔╝░╚██╔╝░██████╔╝╚█████╔╝██║░╚███║",
                "╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═════╝░░╚════╝░╚═╝░░╚══╝",
                "",
                welcomeText
        };
        printText(dawsonText);

        Storage storage = new Storage("data/test.txt");
        
        TaskList taskList;
        try {
            taskList = new TaskList(storage.load());
        } catch (DawsonException e) {
            printText(e.getMessage());
            taskList = new TaskList();
        }

        Command newCommand = new Echo("");
        Scanner scanner = new Scanner(System.in);

        while (!(newCommand instanceof Exit)) {
            String nextLineString = scanner.nextLine().trim();
            if (nextLineString.equals("")) {
                continue; // Ignore empty string
            }

            newCommand = Command.getCommand(nextLineString, taskList);
            try {
                newCommand.execute();
                storage.save(taskList);
            } catch (DawsonException e) {
                printText(e.getMessage());
            }
        }

        scanner.close();
    }
}
