package org.iris.monitorsystem.annotation;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.iris.monitorsystem.repository.dao.MonitorSystem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@PerfmonReport
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class PerfmonReportInterceptor {

    @Inject
    MonitorSystem monitorSystem;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        String className = context.getMethod().getDeclaringClass().getSimpleName();
        String methodName = context.getMethod().getName();

        String timestamp = LocalDateTime.now().format(formatter);
        String reportName = className + "_" + methodName + "_" + timestamp + ".txt";

        monitorSystem.startPerfmon();

        try {
            return context.proceed();
        } finally {
            monitorSystem.generateReportPerfmon(reportName);
            monitorSystem.stopPerfmon();
        }
    }
}
