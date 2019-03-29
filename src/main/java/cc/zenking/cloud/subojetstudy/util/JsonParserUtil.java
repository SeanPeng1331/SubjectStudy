package cc.zenking.cloud.subojetstudy.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang.time.DateUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;

/**
 * JSON与Java对象相互转换的工具类
 *
 * @author cjc
 */
public class JsonParserUtil {
    /**
     * 内置的ObjectMapper实例
     */
    private ObjectMapper objectMapper;

    public static final JsonParserUtil DEFAULT = new JsonParserUtil();

    /**
     * 构造函数,使用默认的ObjectMapper实例, 拥有以下特性:<br>
     * 1) 允许字段名不使用引号<br>
     * 2) 允许字段名和字符串使用单引号<br>
     * 3) 允许数字含有前导符0<br>
     * 4) 允许有不存在的属性<br>
     * 5) 支持以下日期格式:<br>
     * "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd",
     * "MM-dd","HH:mm:ss", "HH:mm"<br/>
     */
    public JsonParserUtil() {
        this.objectMapper = new ObjectMapper()
                // JSON序列化忽略空值
                .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
                .setSerializationInclusion(Include.NON_NULL)
                // 允许字段名不用引号
                .configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                // 允许使用单引号
                .configure(Feature.ALLOW_SINGLE_QUOTES, true)
                // 允许数字含有前导0
                .configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true)
                //.configure(Feature.STRICT_DUPLICATE_DETECTION, true)
                // 允许未知的属性
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        //this.objectMapper.setSerializationInclusion(Include.NON_NULL);
        this.setDatePatterns(new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "MM-dd HH:mm"});
        // null替换为""
//        this.objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
//                arg1.writeString("");
//            }
//        });
    }

    /**
     * 构造函数, 指定ObjectMapper对象
     *
     * @param objectMapper
     */
    public JsonParserUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 获取当前ObjectMapper对象
     *
     * @return
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 设置识别的日期格式集合
     *
     * @param datePatterns
     * @return
     */
    public JsonParserUtil setDatePatterns(final String[] datePatterns) {
        this.objectMapper.setDateFormat(new SimpleDateFormat(datePatterns[0]) {
            @Override
            public Date parse(String source) {
                try {
                    return DateUtils.parseDate(source, datePatterns);
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "date [" + source + "] should comply with one the formats:" + Arrays.toString(datePatterns),
                            e);
                }
            }
        });
        return this;
    }

    /**
     * json转Object对象, 根据json字符串的结构自动调整为对应的数据类型, 具体对应关系如下：<br>
     * 1)字符串->String类型<br>
     * 2)整数->int类型<br>
     * 3)长整数->long类型<br>
     * 4)实数->double类型 <br>
     * 5)键值对->(LinkedHash)Map类型<br>
     * 6)数组->(Array)List类型<br>
     *
     * @param json
     * @return
     */
    public Object toObject(String json) {
        return toBean(json, Object.class);
    }

    /**
     * json转T对象
     *
     * @param <T>
     * @param json
     * @param beanType
     * @return
     */
    public <T> T toBean(String json, Class<T> beanType) {
        try {
            return objectMapper.readValue(json, beanType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T toBean(String json, TypeReference reference) {
        try {
            return objectMapper.readValue(json, reference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json转T对象数组
     *
     * @param json
     * @param elementType
     * @return
     */
    public <T> T[] toArray(String json, Class<T> elementType) {
        try {
            JavaType eType = toJavaType(elementType);
            return objectMapper.readValue(json, ArrayType.construct(eType, null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * json转List&lt;T>对象
     *
     * @param <T>
     * @param json
     * @param elementType
     * @return
     */
    public <T> List<T> toList(String json, Class<T> elementType) {
        try {
            JavaType eType = toJavaType(elementType);
            return objectMapper.readValue(json, CollectionType.construct(List.class, eType));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json转Map&lt;K,V>对象
     *
     * @param json
     * @param keyType
     * @param valueType
     * @return
     */
    public <K, V> Map<K, V> toMap(String json, Class<K> keyType, Class<V> valueType) {
        try {
            JavaType kType = toJavaType(keyType);
            JavaType vType = toJavaType(valueType);
            return objectMapper.readValue(json, MapType.construct(Map.class, kType, vType));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象类型转JSON字符串
     *
     * @param obj
     * @return
     */
    public String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取对应的JavaType
     *
     * @param clazz
     * @return May be: {@link SimpleType} or {@link ArrayType} or
     * {@link CollectionType} or {@link MapType}
     */
    private JavaType toJavaType(Class<?> clazz) {
        if (clazz.isArray()) {
            return ArrayType.construct(SimpleType.construct(clazz.getComponentType()), null);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return CollectionType.construct(clazz, SimpleType.construct(Object.class));
        } else if (Map.class.isAssignableFrom(clazz)) {
            return MapType.construct(clazz, SimpleType.construct(Object.class), SimpleType.construct(Object.class));
        } else {
            return SimpleType.construct(clazz);
        }
    }

}
