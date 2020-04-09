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
        if (reverse) {
            if ((getName().equals("runner1"))) {
                System.out.println(getName() + " взял палку и забег окончен");
                return;
            }
            System.out.println(getName() + " взял палку и бежит к " + runnerS.getName());
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!reverse) {
            if (getName().equals("runner5")) {
                System.out.println(getName() + " взял палку и бежит к финишу");
                try {
                    sleep(5000);
                } catch (InterruptedException ignored) {
                }

                reverse = true;
            } else {
                System.out.println(getName() + " взял палку и бежит к " + runnerF.getName());
                try {
                    sleep(5000);
                } catch (InterruptedException ignored) {
                }
                runnerF.start();
                try {
                    sleep(15000);
                } catch (InterruptedException ignored) {
                }
            }
        }
        if (getName().equals("runner5")) {
            System.out.println(getName() + " бежит к " + runnerS.getName());
            try {
                sleep(5000);
            } catch (InterruptedException ignored) {
            }
        }


        if (i > 0 && this.reverse) {
            this.runnerS.setReverse(true);
            this.runnerS.rice();
            i--;
        }
    }

}