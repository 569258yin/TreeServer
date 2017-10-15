package treeserver.utils;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import treeserver.bean.Bean;
import treeserver.bean.HttpResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPOutputStream;

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

    public static String generateJson(Object o) {
        StringWriter sw = new StringWriter();
        JsonFactory jsonFactory = objectMapper.getJsonFactory();
        JsonGenerator jg = null;
        try {
            jg = jsonFactory.createJsonGenerator(sw);
            jg.writeObject(o);
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            Closer.close(jg);
        }
        return sw.toString();
    }

    public static String generateBeanJson(Bean bean){
        StringWriter sw = new StringWriter();
        JsonFactory jsonFactory = objectMapper.getJsonFactory();
        JsonGenerator jg = null;
        try {
            jg = jsonFactory.createJsonGenerator(sw);
            jg.writeStartObject();
            jg.writeBooleanField("ret",true);
            jg.writeStringField("message", "success");
            jg.writeNumberField("code", 200);
            jg.writeFieldName("data");
            jg.writeStartObject();
            jg.writeStringField("id",bean.getId());
            jg.writeObjectField("tree",bean.getJson());
            jg.writeEndObject();
            jg.writeEndObject();
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            Closer.close(jg);
        }
        return sw.toString();
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

    public static String compress(String str){
        if (str == null || str.length() == 0) {
            return str;
        }
        str = StringUtils.replace(str,"\r","");
        str = StringUtils.replace(str,"\n","");
        str = StringUtils.replace(str," ","");
        return str;
    }

    public static String dealChars(String str){
        return str.replaceAll("\\\\","");
    }
}
