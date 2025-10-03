# Nomnom User Guide

Nomnom is a simple chatbot that helps you manage your tasks.  
You can add todos, deadlines, and events, mark them as done, delete them, search for them, and more.

---

## Quick Start

1. Ensure you have **Java 17** installed.
2. Download the latest `nomnom.jar` release.
3. Open a terminal in the folder containing `nomnom.jar`.
4. Run the program with:
   ```
   java -jar nomnom.jar
   ```
5. You will see Nomnom’s welcome message. Start typing commands!

---

## Features

### Adding a Todo
Adds a task without date/time.
```
todo <description>
```
Example:
```
todo read book
```

---

### Adding a Deadline
Adds a task with a due date.
```
deadline <description> /by yyyy-MM-dd
```
Example:
```
deadline return book /by 2025-10-05
```

---

### Adding an Event
Adds a task with a start and end time.
```
event <description> /from <start> /to <end>
```
Example:
```
event project meeting /from 2pm /to 4pm
```

---

### Listing Tasks
Shows all tasks in the list.
```
list
```

---

### Marking/Unmarking Tasks
Marks a task as done or undo it.
```
mark <task number>
unmark <task number>
```

---

### Deleting a Task
Deletes a task by its number.
```
delete <task number>
```

---

### Clearing All Tasks
Deletes every task in the list.
```
clear
```

---

### Finding Tasks
Finds tasks that contain a given keyword.
```
find <keyword>
```
Example:
```
find book
```

---

### Help
Displays all available commands and formats.
```
help
```

---

### Exiting Nomnom
Exits the program.
```
bye
```

---

## Saving
- Your tasks are saved automatically in `data/nomnom.txt`.
- When you restart Nomnom, your tasks will be loaded from this file.

---

## Example

```
__________________________________________________
hello! i'm nomnom 
what can I do for you?
(enter "help" to see commands and their formats)
__________________________________________________

> todo buy groceries
okay, added this task:
[T][ ] buy groceries
__________________________________________________

> list
here are the tasks in your list:
1.[T][ ] buy groceries
__________________________________________________
```

---

