package com.company;

public class Runner extends Thread {
    private volatile Runner runnerF;
    private volatile Runner runnerS;
    private boolean reverse = false;
    private volatile int i = 4;

    public Runner(String name) {
        super(name);
    }

    public synchronized void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public void setRunnerFS(Runner runnerF, Runner runnerS) {
        this.runnerF = runnerF;
        this.runnerS = runnerS;
    }

    @Override
    public synchronized void run() {
        rice();
    }

    public synchronized void rice() {
        System.out.println(getName() + " взял палку");
        try { sleep(500); } catch (InterruptedException ignored) { }

        if (!reverse) {
            if (getName().equals("runner5")) {
                reverse = true;
                System.out.println("бежит к финишу");
                try { sleep(5000); } catch (InterruptedException ignored) { }

                System.out.println("бежит к " + runnerS.getName()); // поменял букву R на S

                try { sleep(5000); } catch (InterruptedException ignored) { }

            } else {
                System.out.println(getName() + " побежал к " + runnerF.getName());
                try { sleep(5000); } catch (InterruptedException ignored) { }
                runnerF.start();
                try { runnerF.join(); } catch (InterruptedException ignored) { }

            }
        }

        if (getName().equals("runner5"))
            return;

        System.out.println(getName() + " взял палку");
        try { sleep(500); } catch (InterruptedException ignored) { }
        if (!(getName().equals("runner1")))
            System.out.println(getName() + " побежал передавать палку " + runnerS.getName());
        else
            System.out.println("забег окончен");
        try { sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

    }
}