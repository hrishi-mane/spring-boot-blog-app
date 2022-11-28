package com.example.blogapp.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.Test;

public class BlogConfigTest {
    /**
     * Method under test: {@link BlogConfig#objectMapper()}
     */
    @Test
    public void testObjectMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ObjectMapper actualObjectMapperResult = (new BlogConfig()).objectMapper();
        assertTrue(actualObjectMapperResult.getPolymorphicTypeValidator() instanceof LaissezFaireSubTypeValidator);
        VisibilityChecker<?> visibilityChecker = actualObjectMapperResult.getVisibilityChecker();
        assertTrue(visibilityChecker instanceof VisibilityChecker.Std);
        assertNull(actualObjectMapperResult.getPropertyNamingStrategy());
        assertTrue(actualObjectMapperResult.getDeserializationContext() instanceof DefaultDeserializationContext.Impl);
        assertSame(actualObjectMapperResult.getFactory(), actualObjectMapperResult.getFactory());
        assertTrue(actualObjectMapperResult.getSerializerFactory() instanceof BeanSerializerFactory);
        assertTrue(actualObjectMapperResult.getSerializerProvider() instanceof DefaultSerializerProvider.Impl);
        assertTrue(actualObjectMapperResult.getSerializerProviderInstance() instanceof DefaultSerializerProvider.Impl);
        assertTrue(actualObjectMapperResult.getSubtypeResolver() instanceof StdSubtypeResolver);
        DeserializationConfig deserializationConfig = actualObjectMapperResult.getDeserializationConfig();
        assertNull(deserializationConfig.getActiveView());
        assertTrue(deserializationConfig.getAccessorNaming() instanceof DefaultAccessorNamingStrategy.Provider);
        assertEquals(237020304, deserializationConfig.getDeserializationFeatures());
        assertTrue(deserializationConfig.getClassIntrospector() instanceof BasicClassIntrospector);
        assertSame(actualObjectMapperResult.getDateFormat(), deserializationConfig.getDateFormat());
        assertNull(deserializationConfig.getDefaultMergeable());
        assertSame(visibilityChecker, deserializationConfig.getDefaultVisibilityChecker());
        assertNull(deserializationConfig.getFullRootName());
        assertNull(deserializationConfig.getHandlerInstantiator());
        assertTrue(deserializationConfig.getAttributes() instanceof ContextAttributes.Impl);
        assertTrue(deserializationConfig.getAnnotationIntrospector() instanceof JacksonAnnotationIntrospector);
    }
}

