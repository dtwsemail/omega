package omega.lang.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang3.StringUtils;

import omega.lang.redis.constants.EnumRedisOperator;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface RedisCache {

	Class metadataType()   ;
	
	EnumRedisOperator operator() ;
	
	int expire() default -1;
	
	String redisKeyWord()  ;
	
	
}
