package fall2021.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;

/**
 * A process scheduler class mimicking an operating system
 */
public class ProcessScheduling {

    public static final Integer MAX_WAIT_TIME = 30;

    /**
     * Reads from a file and populates processes
     * @return
     */
    private static List<Process> populateProcesses(){
        List<Process> processes = new ArrayList<>();
        try {
            // Read file
            URL url = ProcessScheduling.class.getResource("process_scheduling_input.txt");
            File file = new File(url.getPath());

            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                String line = scanner.nextLine();
                // Split by space
                String lineArray [] = line.split(" ");

                // Insert data into list after parsing
                Process process = new Process();
                processes.add(process);
                process.setProcessId(Integer.parseInt(lineArray[0]));
                process.setPriority(Integer.parseInt(lineArray[1]));
                process.setDuration(Integer.parseInt(lineArray[2]));
                process.setArrivalTime(Integer.parseInt(lineArray[3]));
            }
            // Handle exception
        } catch (FileNotFoundException
                | ArrayIndexOutOfBoundsException
                | NumberFormatException e) {
            System.out.println("Error reading file " + e.getCause());;
        }

        return processes;
    }

    /**
     * The main function that processes items
     * @param processes
     */
    private static void performProcesses(List<Process> processes)
    {
        int currentTime = 0; // the ticks
        boolean running = false; // checks if the processor is busy
        int totalWaitTime = 0; // wait time for all processes
        boolean isEmpty = false; // flag to check if list is empty
        int length = processes.size(); // initial length of the list

        // Populate queue
        PriorityQueue<Process> queue = new PriorityQueue<>(new ProcessComparator());
        Process runningProcess = null; // One process is run at a time

        // Max wait time
        System.out.println("Maximum wait time = " + MAX_WAIT_TIME);

        while(processes.size() >= 0 || !queue.isEmpty()){

            if(runningProcess != null) {

                // Update completion time of the running process
                runningProcess.setCompletion(runningProcess.getCompletion() + 1);

                // Check if the completion time has reached duration
                if(runningProcess.getCompletion().equals(runningProcess.getDuration())){
                    System.out.println("Process " + runningProcess.getProcessId() +
                            " finished at " + currentTime);
                    if(processes.isEmpty() && queue.isEmpty()) break;
                    running = false; // Stop running the process because it is already processed
                }

                // Update the priority of long waiting processes
                if (!running && Math.addExact(Math.addExact(runningProcess.getWaitTime(), runningProcess.getArrivalTime()),
                        runningProcess.getDuration()) <= currentTime) {
                    Iterator<Process> iterator = queue.iterator();
                    while (iterator.hasNext()) {
                        Process aProcess = iterator.next(); // Calculate wait time
                        aProcess.setWaitTime(currentTime - aProcess.getArrivalTime());

                        // Decrement wait time by 1 if max wait time is reached
                        if(aProcess.getWaitTime() > MAX_WAIT_TIME) {
                            System.out.println("Update priority:");
                            System.out.println("PID = " + aProcess.getProcessId() + ", wait time = "
                                    + aProcess.getWaitTime() + ", current priority = "
                                    + aProcess.getPriority());
                            aProcess.setPriority(aProcess.getPriority() - 1);
                            System.out.println("PID = " + aProcess.getProcessId() + ", new priority = " +
                                    aProcess.getPriority());
                        }
                    }
                }
            }

            if(processes.size() > 0) {
                // Remove processes from list and add them to the queue
                // when their arrival time is reached
                Process process = processes.get(0);
                if (process.getArrivalTime() <= currentTime) {
                    processes.remove(0);
                    queue.add(process);
                }
            } else {
                // Output message the first time list is empty
                if(!isEmpty) {
                    System.out.println("D becomes empty at time " + currentTime);
                    isEmpty = true;
                }
            }

            // Process new operations from the queue if the processor
            // is not busy
            if(!queue.isEmpty() && !running){
                runningProcess = queue.remove();
                // Update wait time
                runningProcess.setWaitTime(Math.max(currentTime - runningProcess.getArrivalTime(), 0));
                runningProcess.setCompletion(0);
                running = true;
                totalWaitTime += runningProcess.getWaitTime(); // Add to the total wait time of
                // the current item's wait time
                System.out.println("Process removed from queue is: id = " + runningProcess.getProcessId()
                      + ", at time " + currentTime + ", wait time = " +
                        runningProcess.getWaitTime() + " Total wait time = " + totalWaitTime);
                System.out.println("Process id = " + runningProcess.getProcessId() + " Priority = "
                                + runningProcess.getPriority() +
                " Arrival = " + runningProcess.getArrivalTime() + " Duration = " + runningProcess.getDuration());
            }

            currentTime++;
        }

        // Print the total and average wait times at the end
        System.out.println("Total wait time = " + totalWaitTime);
        System.out.println("Average wait time = " + (double) totalWaitTime / length * 1.0);
    }

    public static void main(String [] args){

        // Sets output to file
        try { // Note to TA - Please make sure you set your sources root in the IDE while running
            System.setOut(new PrintStream(new File("src/main/java/fall2021/project/process_scheduling_output.txt")));
        } catch (Exception e) {
            System.out.println("Error redirecting file " + e.getCause());
        }

        // Populate processes pulled from a file
        List<Process> processes = populateProcesses();

        for(Process process: processes){
            System.out.println("Id = " + process.getProcessId() + ", priority = "
                    + process.getPriority() + ", duration = " + process.getDuration()
                    + ", arrival time = " + process.getArrivalTime());
        }

        // Processes items in the queue
        performProcesses(processes);
    }
}