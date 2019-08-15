package com.cm.v16serach.exchange;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cm.v16.api.ISerachApi;
import com.cm.v16.common.pojo.ResultBean;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author CaoMeng
 * @Date 2019-08-12
 */
@Component
@RabbitListener(queues = "queue-1")
public class TopicExchangeSerachConsumer{

    @Reference
    private ISerachApi serachApi;


    @RabbitHandler
    public void addProductRecordToSerachSystem(Long newId){
        ResultBean resultBean=serachApi.addOrUpdateByIdData(newId);
        System.out.println(resultBean.getData());
    }

    @RabbitHandler
    public void other(String msg){
        System.out.println(msg);
    }
}
