package com.example.blogapp.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResourceNotFoundTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ResourceNotFound#ResourceNotFound(String, String, int)}
     *   <li>{@link ResourceNotFound#getFieldName()}
     *   <li>{@link ResourceNotFound#getFieldValue()}
     *   <li>{@link ResourceNotFound#getResourceName()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        ResourceNotFound actualResourceNotFound = new ResourceNotFound("Resource Name", "Field Name", 42);

        assertEquals("Field Name", actualResourceNotFound.getFieldName());
        assertEquals(42, actualResourceNotFound.getFieldValue());
        assertEquals("Resource Name", actualResourceNotFound.getResourceName());
    }

    /**
     * Method under test: {@link ResourceNotFound#ResourceNotFound(String, String, int)}
     */
    @Test
    public void testConstructor2() {
        ResourceNotFound actualResourceNotFound = new ResourceNotFound("Resource Name", "Field Name", 42);

        assertEquals("Resource Name", actualResourceNotFound.getResourceName());
        assertEquals(42, actualResourceNotFound.getFieldValue());
        assertEquals("Field Name", actualResourceNotFound.getFieldName());
    }
}

