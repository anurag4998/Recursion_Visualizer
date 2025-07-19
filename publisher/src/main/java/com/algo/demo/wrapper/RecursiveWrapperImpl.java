package com.algo.demo.wrapper;

import java.util.UUID;
import org.springframework.stereotype.Component;
import com.algo.demo.wrapper.consumers.IMessageGenerator;
import com.algo.demo.wrapper.consumers.Message;
import com.algo.demo.wrapper.consumers.MessageGenerator;
import com.algo.demo.wrapper.consumers.MonoConsumer;
import com.algo.demo.wrapper.funcItfs.RecursiveFour;
import com.algo.demo.wrapper.funcItfs.RecursiveThree;
import com.algo.demo.wrapper.funcItfs.RecursiveTwo;
import com.algo.demo.wrapper.recursiveWrappers.RecursiveFourWrapper;
import com.algo.demo.wrapper.recursiveWrappers.RecursiveThreeWrapper;
import com.algo.demo.wrapper.recursiveWrappers.RecursiveWrapper;

@Component
public class RecursiveWrapperImpl {

    //TODO: Fix this
    private static IMessageGenerator messageGenerator = new MessageGenerator();

    public static <T, U, R> RecursiveWrapper<T, U, R> wrapperTwo(RecursiveTwo<T, U, R> funcToExecute, MonoConsumer monoConsumer, String customMessageToRender ) {
        return new RecursiveWrapper<T, U, R>() {
            int level = 0;                       

            @Override
            public R call(T input1, U input2, UUID parentId, RecursiveWrapper<T, U, R> self) {
                String messageToRender = messageGenerator.generateString(customMessageToRender, input1, input2);
                Message msg = new Message(level++, messageToRender, parentId);
                monoConsumer.consume(msg);
                R result = funcToExecute.execute(
                    input1,
                    input2,
                    (inp1, inp2, ignoredfunc) -> self.call(inp1, inp2, msg.currentId, self)
                );
                level--;
                return result;
            }
        };
    }

    public static <T,U,V,R> RecursiveThreeWrapper<T,U,V,R> wrapperThree(RecursiveThree<T,U,V,R> funcToExecute, MonoConsumer monoConsumer, String customMessageToRender) {
        return new RecursiveThreeWrapper<T,U,V,R>() {
            int level = 0;
            @Override
            public R call (T input1, U input2, V input3, UUID parentId, RecursiveThreeWrapper<T,U,V,R> self) {
                String messageToRender = messageGenerator.generateString(customMessageToRender, input1, input2);
                Message msg = new Message(level++, messageToRender, parentId);
                monoConsumer.consume(msg);
                R result = funcToExecute.execute(
                    input1, 
                    input2, 
                    input3, 
                    (inp1, inp2, inp3, ignoredSelf) -> self.call(inp1, inp2, inp3, msg.currentId, self)
                );
                level--;
                return result;
            }
        };
    }

    public static <T,U,V,W,R> RecursiveFourWrapper<T,U,V,W,R> wrapperFour(RecursiveFour<T,U,V,W,R> funcToExecute, MonoConsumer monoConsumer, String customMessageToRender) {
        return new RecursiveFourWrapper<T,U,V,W,R>() {
            int level = 0;
            
            @Override
            public R call (T input1, U input2, V input3, W input4, UUID parentId, RecursiveFourWrapper<T,U,V,W,R> self) {
                String messageToRender = messageGenerator.generateString(customMessageToRender, input1, input2);
                Message msg = new Message(level++, messageToRender, parentId);
                monoConsumer.consume(msg);
                R result = funcToExecute.execute(
                    input1, 
                    input2, 
                    input3,
                    input4, 
                    (inp1, inp2, inp3, inp4, ignoredSelf) -> self.call(inp1, inp2, inp3, inp4, msg.currentId, self)
                );
                level--;
                return result;
            }
        };
    }
}
