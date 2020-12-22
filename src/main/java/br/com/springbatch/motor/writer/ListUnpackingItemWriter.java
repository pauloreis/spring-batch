package br.com.springbatch.motor.writer;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUnpackingItemWriter<UserOdds> implements ItemWriter<List<UserOdds>>, ItemStream, InitializingBean {
    @Autowired
    private ItemWriter<UserOdds> delegate;

    @Override
    public void write(List<? extends List<UserOdds>> lists) throws Exception {
        final List<UserOdds> consolidatedList = new ArrayList<>();
        for (List<UserOdds> list : lists) {
            consolidatedList.addAll(list);
        }
        delegate.write(consolidatedList);
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        if (delegate instanceof ItemStream) {
            ((ItemStream) delegate).open(executionContext);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        if (delegate instanceof ItemStream) {
            ((ItemStream) delegate).update(executionContext);
        }
    }

    @Override
    public void close() throws ItemStreamException {
        if (delegate instanceof ItemStream) {
            ((ItemStream) delegate).close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(delegate, "You must set a delegate!");
    }

    public void setDelegate(ItemWriter<UserOdds> delegate) {
        this.delegate = delegate;
    }
}
