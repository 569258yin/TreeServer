package treeserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭 closeAble 类型
 * Created by cao on 14/11/19.
 */
public class Closer {
    private static final Logger logger = LoggerFactory.getLogger(Closer.class);

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("closer error", e);
            }
        }
    }
}
