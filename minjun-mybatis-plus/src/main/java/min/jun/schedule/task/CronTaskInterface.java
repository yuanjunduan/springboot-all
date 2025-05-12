package min.jun.schedule.task;

/**
 * @author Administrator
 */
public interface CronTaskInterface extends Runnable {

    public void execTask();

    public String cronExpression();
}
