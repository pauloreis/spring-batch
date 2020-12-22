package br.com.springbatch.motor.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.BatchStatus.FAILED;

@Component
public class JobListener {

    private static String START_MESSAGE = "%s iniciou a execução";
    private static String END_MESSAGE = "%s terminou a execução com status %s";

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(String.format(START_MESSAGE,
                jobExecution.getJobInstance().getJobName()));

        if (jobExecution.getStatus() == FAILED) {
            throw new RuntimeException("Erro ao iniciar o JOB!");
        }
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        System.out.println(
                String.format(END_MESSAGE,
                        jobExecution.getJobInstance().getJobName(),
                        jobExecution.getStatus()));
    }
}
