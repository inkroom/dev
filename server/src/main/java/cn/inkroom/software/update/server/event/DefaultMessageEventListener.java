package cn.inkroom.software.update.server.event;

/**
 * 默认处理，没多大用
 */
public class DefaultMessageEventListener implements MessageEventListener {
    @Override
    public void hand(MessageEventKey key) {
        switch (key.getMessageReader().getType()) {
            case OK:


                break;
            case FAIL:
                break;
            case UPDATE:
                break;
        }
    }
}
