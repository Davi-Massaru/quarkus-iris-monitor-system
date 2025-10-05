# Quarkus IRIS Monitor System

> **Performance monitoring meets innovation.**
>
> The **Quarkus IRIS Monitor** System delivers a powerful, seamless solution to **analyze Java runtime performance within InterSystems IRIS environments**. By integrating the Quarkus framework with **^PERFMON capabilities through the IRIS native SDK**, it transforms standard method executions into actionable, real-time performance insights, empowering developers and administrators to monitor, optimize, and fully understand their system.

---

## 🌟 What is Quarkus IRIS Monitor System?

This project harnesses the full power of InterSystems IRIS in combination with Quarkus, delivering a cutting-edge development and monitoring toolkit tailored for modern Java applications.

At its core, it introduces the @PerfmonReport annotation, which automatically triggers IRIS performance analysis routines (^PERFMON) every time a business method is executed — turning ordinary method calls into actionable performance insights with zero manual intervention.

The Quarkus IRIS Monitor System effectively bridges the gap between application-level observability and database-level intelligence, empowering developers and system administrators with real-time, actionable metrics.

This solution showcases:
- Deep integration with InterSystems IRIS native APIs
- Modern Jakarta EE and Quarkus architecture
- A simple, elegant developer experience powered by annotations

In other words — you code as usual, and the system records **real IRIS-level performance data** behind the scenes.

---

## The `@PerfmonReport` Annotation

```java
package org.iris.patient.service;

import org.iris.monitorsystem.annotation.PerfmonReport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PatientService {
    
    @PerfmonReport
    public Object patientGetInfo(String patientKey) {
            // Your business logic
    }

}

```

When you annotate a method with @PerfmonReport, the following happens automatically:

Starts IRIS performance monitoring (^PERFMON).

Executes your business logic.

Stops the monitor and generates a report.

Saves the report under /usr/irissys/mgr/Temp/ with a timestamped filename.

Example output: ```PatientService_patientGetInfo_20251005_161906.txt```

Inside the file, you get detailed IRIS runtime metrics, including:

- Routine names
- Execution time
- Routine loads and fetches
- Line load ratios
- Process resource usage

This transforms your Quarkus service methods into automatic performance probes, with zero manual configuration.

## Why it Matters

Developers often struggle to correlate application performance with database engine activity.
With @PerfmonReport, you get real-time insight directly from IRIS about what’s happening at the system level, while keeping your Quarkus application structure clean and idiomatic.

- ✅ Seamless integration with Quarkus CDI and interceptors
- ✅ Native use of InterSystems IRIS APIs
- ✅ No manual monitoring setup — it's all automatic
- ✅ Developer-friendly

It should not be used in PRODUCTION as it is a local development and analysis tool.

It can help with performance testing, understanding slowdowns, and other issues.

If your production is slow and for some reason you suspect the problem is on the database side, this feature can be used, but with caution.

We recommend creating a local quarkus, pointing to the database if necessary, but do not run it directly in production.

## Example in Action

When invoking PatientService.patientGetInfo("Patient/4"), the system:

Starts IRIS performance monitor

Executes repository calls

Generates a ```PatientService_patientGetInfo_20251005_161906.txt``` report with detailed IRIS metrics

Output example: ```/usr/irissys/mgr/Temp/PatientService_patientGetInfo_20251005_161906.txt```
```
                         Routine Activity by Routine

Started: 10/05/2025 07:21:36PM                    Collected: 10/05/2025 07:21:37PM

Routine Name                     RtnLines  % Lines   RtnLoads  RtnFetch  Line/Load Directory
-------------------------------- --------- --------- --------- --------- --------- ---------
Other                                  0.0       0.0       0.0       0.0         0
PERFMON                               44.0       2.4       0.0       0.0         0 /usr/irissys/mgr/
iris.src.dc.AdapterPerfmonProc.1       6.0       0.3       2.0       0.0       3.0 /usr/irissys/mgr/FHIRSERVER/
%SYS.DBSRV.1                         403.0      21.7       8.0       0.0      50.4 /usr/irissys/mgr/
%SYS.WorkQueueMgr                     16.0       0.9       0.0       0.0         0 /usr/irissys/mgr/
%qaqpreparser                        848.0      45.8       0.0       0.0         0 /usr/irissys/mgr/irislib/
%SYS.SQLSRV                          240.0      13.0       7.0       0.0      34.3 /usr/irissys/mgr/
%sqlcq.FHIRSERVER.cls24.1             58.0       3.1       2.0       0.0      29.0 /usr/irissys/mgr/irislocaldata/
HSFHIR.X0001.S.Patient.1               2.0       0.1       2.0       0.0       1.0 /usr/irissys/mgr/FHIRSERVER/
%Collection.AbstractList.1            26.0       1.4       0.0       0.0         0 /usr/irissys/mgr/irislib/
%sqlcq.FHIRSERVER.cls25.1             77.0       4.2       0.0       0.0         0 /usr/irissys/mgr/irislocaldata/
%sqlcq.FHIRSERVER.cls26.1             77.0       4.2       0.0       0.0         0 /usr/irissys/mgr/irislocaldata/
%sqlcq.FHIRSERVER.cls27.1             55.0       3.0       0.0       0.0         0 /usr/irissys/mgr/irislocaldata/
%SYS.System.1                          1.0       0.1       0.0       0.0         0 /usr/irissys/mgr/
```

## ⚙️ Installation Guide

Clone the repository
```bash
git clone https://github.com/Davi-Massaru/quarkus-iris-monitor-system.git
cd quarkus-iris-monitor-system
```

Start the IRIS container
```bash
docker compose up -d --build
```

Install the InterSystems JDBC driver locally
```
cd quarkus-iris-monitor-system

./mvnw install:install-file \
  -Dfile=src/main/resources/lib/intersystems-jdbc-3.7.1.jar \
  -DgroupId=com.intersystems \
  -DartifactId=intersystems-jdbc \
  -Dversion=3.7.1 \
  -Dpackaging=jar \
  -DgeneratePom=true
```

Run the application

```
./mvnw clean quarkus:dev
```

[Davi Muta](https://www.linkedin.com/in/davi-massaru-teixeira-muta-003284191/)

[Davi Muta - Intersystem Community link](https://community.intersystems.com/user/davi-massaru-teixeira-muta)

