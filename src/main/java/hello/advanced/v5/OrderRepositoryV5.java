package hello.advanced.v5;


import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logTrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.template = new TraceTemplate(logTrace);
    }

    public void save(String itemId) {
        template.execute("OrderRepository.save()",()->{
            //저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외발생");
            }
            sleep(1000);
            return null;
        });

    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
