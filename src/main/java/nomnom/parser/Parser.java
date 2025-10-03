package nomnom.parser;

import nomnom.commands.AddDeadlineCommand;
import nomnom.commands.AddEventCommand;
import nomnom.commands.AddToDoCommand;
import nomnom.commands.ClearAllCommand;
import nomnom.commands.Command;
import nomnom.commands.DeleteTaskCommand;
import nomnom.commands.DisplayHelpMenuCommand;
import nomnom.commands.ExitCommand;
import nomnom.commands.FindCommand;
import nomnom.commands.InvalidCommand;
import nomnom.commands.ListCommand;
import nomnom.commands.MarkCommand;
import nomnom.commands.UnmarkCommand;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {
    /**
     * Splits the user input into a command word and arguments,
     * then returns the appropriate Command object.
     * If the command is not recognised, returns an InvalidCommand.
     *
     * @param input the full user input
     * @return the Command representing the user’s instruction
     */
    public static Command parse(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        String arguments = (words.length > 1) ? words[1].trim() : "";

        switch (commandWord) {
        case "todo": return new AddToDoCommand(arguments);
        case "deadline": return new AddDeadlineCommand(arguments);
        case "event": return new AddEventCommand(arguments);
        case "list": return new ListCommand();
        case "mark": return new MarkCommand(arguments);
        case "unmark": return new UnmarkCommand(arguments);
        case "delete": return new DeleteTaskCommand(arguments);
        case "clear": return new ClearAllCommand();
        case "find" : return new FindCommand(arguments);
        case "bye": return new ExitCommand();
        case "help": return new DisplayHelpMenuCommand();
        default: return new InvalidCommand("\nnomnom doesn't recognise this command: " + commandWord + "\nenter \"help\" to see valid commands");
        }
    }
}


