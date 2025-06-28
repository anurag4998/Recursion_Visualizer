package com.algo.demo.wrapper;

import java.util.UUID;

import com.algo.demo.wrapper.consumers.Message;
import com.algo.demo.wrapper.consumers.MonoConsumer;


public class RecursiveWrapperImpl {

   public static <T, U, R> RecursiveWrapper<T, U, R> wrapperTwo(RecursiveTwo<T, U, R> funcToExecute, MonoConsumer monoConsumer, String customMessageToRender ) {
        return new RecursiveWrapper<T, U, R>() {
            int level = 0;                       

            @Override
            public R call(T input1, U input2, UUID parentId, RecursiveWrapper<T, U, R> self) {
                Message msg = new Message(level++, customMessageToRender + input1, parentId);
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
}
