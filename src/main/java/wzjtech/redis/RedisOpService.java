package wzjtech.redis;

import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisOpService {

  @Autowired
  private RedissonClient client;

  public void lock(){
    var lock = client.getLock("name");
    if(lock.isLocked()){

    }else {
      lock.lock();
    }
    var topic = client.getTopic("topic");
    topic.publish("published now");

    var transaction = client.createTransaction(TransactionOptions.defaults());
    transaction.commit();

  }

}
