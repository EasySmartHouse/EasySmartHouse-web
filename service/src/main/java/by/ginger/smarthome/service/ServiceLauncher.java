package by.ginger.smarthome.service;

import by.ginger.smarthome.service.props.AppPropertySource;
import by.ginger.smarthome.sheduler.SimpleSchedulerImpl;
import by.ginger.smarthome.sheduler.task.RefreshDevicesTask;
import by.ginger.smarthome.sheduler.task.TaskProperties;
import by.ginger.smarthome.sheduler.thread.CycleTaskThread;
import by.ginger.smarthome.sheduler.thread.QueueTaskThread;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MutablePropertySources;

public class ServiceLauncher {

    private static final String SHEDULER_BEAN_NAME = "scheduler";
    private static final long REFRESH_TASK_DELAY = 200l;
    private static final String[] CONTEXTS = new String[]{
        "classpath:app-context.xml",};
    
    private ServiceLauncher() {
    }
    
    public static void main(String[] args) throws InterruptedException {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.registerShutdownHook();
        
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        propertySources.addFirst(new AppPropertySource());
            
        context.load(CONTEXTS);
        context.refresh();

        SimpleSchedulerImpl scheduler = (SimpleSchedulerImpl) context.getBean(SHEDULER_BEAN_NAME);
        scheduler.addTask(new RefreshDevicesTask(), new TaskProperties(true, REFRESH_TASK_DELAY));

        CycleTaskThread cycleThread = new CycleTaskThread(true, scheduler);
        QueueTaskThread queueThread = new QueueTaskThread(true, scheduler);

        new Thread(cycleThread).start();

        final Thread execute = new Thread(queueThread);
        execute.start();
        execute.join();
    }
}
