package ex.tunitin;

import java.util.*;

public class PrintProcesses {

    static class Process {
        int pid;
        int parentPid;
        String name;
    }

    private Process[] processes;

    private void init() {
        processes = new Process[]{
                new Process() {{ pid = 1; parentPid = 0; name = "init"; }},
                new Process() {{ pid = 2; parentPid = 1; name = "bash"; }},
                new Process() {{ pid = 3; parentPid = 1; name = "sshd"; }},
                new Process() {{ pid = 4; parentPid = 2; name = "vim"; }},
                new Process() {{ pid = 5; parentPid = 2; name = "java"; }},
                new Process() {{ pid = 6; parentPid = 3; name = "nginx"; }},
                new Process() {{ pid = 7; parentPid = 10; name = "booboo"; }},
                new Process() {{ pid = 10; parentPid = 12; name = "blabla"; }},
        };
    }

    public static void main(String[] args) {
        PrintProcesses printProcesses = new PrintProcesses();
        printProcesses.init();
        printProcesses.prepareAndPrintTree();
    }

    private void prepareAndPrintTree() {
        HashMap<Integer, List<Process>> processMap = new HashMap<>();
        for (Process process : processes) {
            processMap.computeIfAbsent(process.parentPid, k -> new ArrayList<>()).add(process);
        }
        List<Integer> startPids = processMap.keySet().stream().filter(ppid -> {
            boolean notFound = true;
            for (Process process : processes) {
                if (process.pid == ppid) {
                    notFound = false;
                    break;
                }
            }
            return notFound;
        }).toList();
        for (Integer startPid : startPids) {
            printTree(processMap, startPid, " ");
        }
    }

    private void printTree(HashMap<Integer, List<Process>> processMap, int parentPid, String prefix) {
        for (Process process : processMap.getOrDefault(parentPid, List.of())) {
            System.out.println(prefix + process.name);
            printTree(processMap, process.pid, prefix + "  ");

        }
    }


}
