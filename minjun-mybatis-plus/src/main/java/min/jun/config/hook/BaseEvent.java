package min.jun.config.hook;

import lombok.Getter;

@Getter
public abstract class BaseEvent<T> {
    private final T data;
    private final String msgType;
    private final String uniqueKey;

    public BaseEvent(T data, String msgType, String uniqueKey) {
        this.data = data;
        this.msgType = msgType;
        this.uniqueKey = uniqueKey;
    }

}