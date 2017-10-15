package treeserver.utils;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by cao on 7/5/16.
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
    }

    public static <T> List<T> decodeListJson(String json) {
        List<T> list = Lists.newArrayList();
        try {
            if (StringUtils.isNotEmpty(json)) {
                list = JsonUtils.objectMapper.readValue(json, new TypeReference<List<T>>() {
                });
            }
        } catch (IOException e) {
            logger.error("解析json错误");
            return Collections.emptyList();
        }
        return list;
    }
}
