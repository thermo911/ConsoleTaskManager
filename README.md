#Console Task Manager
This project is a test task from ITMO uni. Java club.

###Mechanism of work
`main()` methon creates an object of `CommandHandler` with `new Manager()`
as a parameter.

`Manager` class handles all functional of the program.

`CommandHandler` checks out whether user enters correct command by `parseCommand()` method.
If command is correct `CommandHandler` calls corresponding method of object of class `Manager`.

###Console commands review
1. **`add <task text>`**: appending new task int `tasksToDo` list consists in `Manager` object. 
Each new task has a unique id.
2. **`delete <task id>`**: deletion the task with matching id from `tasksToDo` or `tasksDone` lists. 
3. `complete <task id>`: moving the task with matching id from `tasksToDo` to `tasksDone` list 
and set this task done.
4. **`save <filename.txt>`**: creating a new file called _filename.txt_ (if needs) and write an info
about current tasks in this file using special format (_id status text_).
5. **`load <filename.txt>`**: loading tasks from _filename.txt_.
6. **`all`** : printing all current tasks (not done and done).
7. **`completed`**: printing only completed tasks if it exists.
8. **`exit`**: stopping of program executing.

Note: while calling **`save`** or **`load`** command user should enter only name
of file in _*.txt_ format. All files consist in _files/_ directory. So there is some trouble in code
because this directory must be in _src_ directory...