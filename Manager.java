import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private List<Task> tasksToDo;
    private List<Task> tasksDone;

    private FileReader fileReader;
    private FileWriter fileWriter;

    public Manager() {
        tasksToDo = new LinkedList<>();
        tasksDone = new LinkedList<>();
    }

    void addTask(String text) {
        for(int i = 1; ; i++) {
            boolean correctId = true;
            for(Task task: tasksToDo) {
                if(task.getId()==i) {
                    correctId = false;
                    break;
                }
            }
            for(Task task: tasksDone) {
                if(task.getId()==i) {
                    correctId = false;
                    break;
                }
            }
            if(correctId) {
                tasksToDo.add(new Task(i, text));
                return;
            }
        }
    }

    void loadFromFile(String fileName) {
        File file = new File("src/files/" + fileName);

        if(file.exists()) {
            tasksToDo.clear();
            tasksDone.clear();

            try (Scanner sc = new Scanner(file)) {
                List<String> taskLines = new LinkedList<>();

                while(sc.hasNextLine()) {
                    taskLines.add(sc.nextLine());
                }

                for(String taskLine: taskLines) {
                    String[] tokens = taskLine.split("\\s", 3);
                    int id = -1;

                    try {
                        id = Integer.parseInt(tokens[0]);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                    Task task = new Task(id, tokens[2]);

                    if(tokens[1].equals("<done>")) {
                        task.setDone(true);
                        tasksDone.add(task);
                    } else {
                        tasksToDo.add(task);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("File not found.");
        }
    }

    void printTasks() {
        for(Task t: tasksToDo) {
            System.out.printf("%5d | %s\n", t.getId(), t.getText());
        }
        for(Task t: tasksDone) {
            System.out.printf("%5d | <done> | %s\n", t.getId(), t.getText());
        }
    }

    void deleteTask(int id) {
        boolean success = false;

        for(Task task: tasksToDo) {
            if(task.getId() == id) {
                tasksToDo.remove(task);
                success = true;
                break;
            }
        }

        for(Task task: tasksDone) {
            if(task.getId() == id) {
                tasksDone.remove(task);
                success = true;
                break;
            }
        }

        if(!success) {
            System.out.printf("task with id %d not found\n", id);
        }
    }

    public void printCompleted() {
        for(Task t: tasksDone) {
            System.out.printf("%5d | <done> | %s\n", t.getId(), t.getText());
        }
    }

    public void saveTasks(String filename) {
        File file = new File("src/files/" + filename);

        if(!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("create file " + filename);
            } catch (IOException e) {
                System.out.println("incorrect filename");
                return;
            }
        }

        try {
            fileWriter = new FileWriter(file);
            for(Task task: tasksToDo) {
                String str = "";
                str += task.getId() + " ";
                str += "<!done>" + " ";
                str += task.getText();
                fileWriter.write(str + "\n");
                fileWriter.flush();
            }

            for(Task task: tasksDone) {
                String str = "";
                str += task.getId() + " ";
                str +=  "<done>" + " ";
                str += task.getText();
                fileWriter.write(str + "\n");
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void completeTask(int id) {
        boolean success = false;

        for(Task task: tasksToDo) {
            if(task.getId() == id) {
                task.setDone(true);
                tasksDone.add(task);
                tasksToDo.remove(task);
                success = true;
                break;
            }
        }

        if(!success) {
            System.out.printf("task with id %d not found\n", id);
        }
    }
}
