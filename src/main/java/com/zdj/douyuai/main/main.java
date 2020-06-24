package com.zdj.douyuai.main;

import com.p6e.broadcast.channel.P6eChannelCallback;
import com.p6e.broadcast.channel.blibli.P6eBliBliChannel;
import com.p6e.broadcast.channel.blibli.P6eBliBliChannelMessage;
import com.p6e.broadcast.channel.douyu.P6eDouYuChannel;
import com.p6e.broadcast.channel.douyu.P6eDouYuChannelMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static Map map = new HashMap();
    public static void main(String[] args) throws InterruptedException {
        new main().douyuShow();

    }
    public void douyuShow() throws InterruptedException {
        P6eDouYuChannel.create("5384600", new P6eChannelCallback.DouYu() {
            @Override
            public void execute(List<P6eDouYuChannelMessage> message) {
                message.stream().forEach(p6eDouYuChannelMessage -> System.out.println(
                        "name: " + p6eDouYuChannelMessage.data().get("nn") + " level: " + p6eDouYuChannelMessage.data().get("level")+ ": "+
                                p6eDouYuChannelMessage.data().get("txt")));
            }
        });
        Thread.sleep(Integer.MAX_VALUE);

    }

    public void bilbilShow() throws InterruptedException {

        P6eBliBliChannel.create("21493050", new P6eChannelCallback.BliBli() {
            @Override
            public void execute(List<P6eBliBliChannelMessage> messages) {
                //messages.stream().forEach(p6eBliBliChannelMessage -> map.put(UUID.randomUUID(), p6eBliBliChannelMessage.data()));
                messages.stream().forEach(p6eBliBliChannelMessage -> {
                    List<Object> tmp= castList(p6eBliBliChannelMessage.data().get("info"),Object.class);
                    if(tmp!= null){
                        String txt = (String) tmp.get(1);
                        List<Object> name = castList(tmp.get(2),Object.class);
                        System.out.println("usname : '" + name.get(1) + "'      say: "+txt);

                        }
                    }
                );
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

}
