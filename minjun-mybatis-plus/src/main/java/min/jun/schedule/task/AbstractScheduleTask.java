package min.jun.schedule.task;

/**
 * @author Administrator
 * @date 2020-07-24
 */
public abstract class AbstractScheduleTask implements CronTaskInterface {

    private volatile boolean isRun = false;
    private volatile boolean retry = false;

    /**
     *
     * @return
     */
    public abstract String taskID();

    /**
     *
     * @return
     */
    public abstract boolean openFlag();

    @Override
    public String cronExpression() {
        return "";
    }

    @Override
    public void run() {
        if (isRun) {
            return;
        }
        try {
            isRun = true;
            execTask();
            //            TryFactory.execAndRetry(this::run, 2, 100L);
        } finally {
            isRun = false;
        }
    }
}
