package com.example.batch.config;

import java.util.List;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;

public class ExploringTasklet implements Tasklet {

  private JobExplorer explorer;

  public ExploringTasklet(JobExplorer explorer) {
    this.explorer = explorer;

  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    String jobName = chunkContext.getStepContext().getJobName();
    List<JobInstance> instances = explorer.getJobInstances(jobName, 0, Integer.MAX_VALUE);
    System.out.printf("%s 잡에는 %d개의 잡 인스턴스가 존재합니다.", jobName, instances.size());

    System.out.println("********************* 결과 *********************");
    for (JobInstance instance : instances) {
      List<JobExecution> jobExecutions = explorer.getJobExecutions(instance);
      System.out.printf("%d 인스턴스에는 %d개의 execution이 존재합니다.",
          instance.getInstanceId(), jobExecutions.size());
      for (JobExecution jobExecution : jobExecutions) {
        System.out.printf("%d execution의 ExitStatus 결과는 %s입니다.",
            jobExecution.getId(), jobExecution.getExitStatus());
      }
    }
    return RepeatStatus.FINISHED;
  }
}
