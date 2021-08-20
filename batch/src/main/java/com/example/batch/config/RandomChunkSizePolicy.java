package com.example.batch.config;

import java.util.Random;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;

public class RandomChunkSizePolicy implements CompletionPolicy {

  private int chunkSize;
  private int totalProcessed;
  private Random random = new Random();

  @Override
  public boolean isComplete(RepeatContext context, RepeatStatus result) {
    if (RepeatStatus.FINISHED == result) {
      return true;
    } else {
      return isComplete(context);
    }
  }

  @Override
  public boolean isComplete(RepeatContext context) {
    return this.totalProcessed >= chunkSize;
  }

  @Override
  public RepeatContext start(RepeatContext parent) {
    this.chunkSize = random.nextInt(20);
    this.totalProcessed = 0;
    System.out.printf("청크 사이즈가 %d로 설정됨.", this.chunkSize);
    return parent;
  }

  @Override
  public void update(RepeatContext context) {
    this.chunkSize++;
  }
}
