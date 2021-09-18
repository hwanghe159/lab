package com.example.batch.reader;

import com.example.batch.domain.Transaction;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.transform.FieldSet;

public class TransactionReader implements ItemStreamReader<Transaction> {

  private ItemStreamReader<FieldSet> fieldSetReader;
  private int recordCount = 0;
  private int expectedRecordCount = 0;

  public TransactionReader(ItemStreamReader<FieldSet> fieldSetReader) {
    this.fieldSetReader = fieldSetReader;
  }

  @Override
  public Transaction read() throws Exception {
    return process(fieldSetReader.read());
  }

  private Transaction process(FieldSet fieldSet) {
    Transaction result = null;

    if (fieldSet != null) {
      if (fieldSet.getFieldCount() > 1) {
        result = new Transaction();

      }
    }
  }

  @AfterStep
  public ExitStatus afterStep(StepExecution execution) {
    if (recordCount == expectedRecordCount) {
      return execution.getExitStatus();
    } else {
      return ExitStatus.STOPPED;
    }
  }

  @Override
  public void open(ExecutionContext executionContext) throws ItemStreamException {

  }

  @Override
  public void update(ExecutionContext executionContext) throws ItemStreamException {

  }

  @Override
  public void close() throws ItemStreamException {

  }
}
