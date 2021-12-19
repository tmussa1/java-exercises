package fall2021.project;

import java.util.Comparator;

/**
 * Comparator class
 */
public class ProcessComparator implements Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
        return o1.getPriority() - o2.getPriority();
    }
}
