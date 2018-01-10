package net.easysmarthouse.service;

import java.io.File;
import net.easysmarthouse.service.context.ProxiedResolverGenericXmlApplicationContext;
import net.easysmarthouse.service.props.AppPropertySource;
import net.easysmarthouse.sheduler.SimpleSchedulerImpl;
import net.easysmarthouse.sheduler.task.RefreshDevicesTask;
import net.easysmarthouse.sheduler.task.TaskProperties;
import net.easysmarthouse.sheduler.thread.CycleTaskThread;
import net.easysmarthouse.sheduler.thread.QueueTaskThread;
import org.springframework.core.env.MutablePropertySources;

public class ServiceLauncher {

    private static final String SHEDULER_BEAN_NAME = "scheduler";
    private static final long REFRESH_TASK_DELAY = 200l;
    private static final String[] CONTEXTS = new String[]{
        "file:config" + File.separator + "app-context.xml"};

    private ServiceLauncher() {
    }

    public static void main(String[] args) throws InterruptedException {
        ProxiedResolverGenericXmlApplicationContext context = new ProxiedResolverGenericXmlApplicationContext();
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
