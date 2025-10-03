package nomnom.parser;

import nomnom.commands.*;

public class Parser {
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


