package hello.advanced.v4;


import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logTrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;


    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                //저장 로직
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외발생");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");

    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
