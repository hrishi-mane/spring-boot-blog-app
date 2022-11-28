package com.example.blogapp.model.blog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BlogTest {
    /**
     * Method under test: {@link Blog#canEqual(Object)}
     */
    @Test
    public void testCanEqual() {
        assertFalse((new Blog()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Blog#canEqual(Object)}
     */
    @Test
    public void testCanEqual2() {
        Blog blog = new Blog();

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertTrue(blog.canEqual(blog1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Blog}
     *   <li>{@link Blog#setContent(String)}
     *   <li>{@link Blog#setId(String)}
     *   <li>{@link Blog#setPublishedDate(String)}
     *   <li>{@link Blog#setStatus(String)}
     *   <li>{@link Blog#setTitle(String)}
     *   <li>{@link Blog#toString()}
     *   <li>{@link Blog#getContent()}
     *   <li>{@link Blog#getId()}
     *   <li>{@link Blog#getPublishedDate()}
     *   <li>{@link Blog#getStatus()}
     *   <li>{@link Blog#getTitle()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        Blog actualBlog = new Blog();
        actualBlog.setContent("Not all who wander are lost");
        actualBlog.setId("42");
        actualBlog.setPublishedDate("2020-03-01");
        actualBlog.setStatus("Status");
        actualBlog.setTitle("Dr");
        String actualToStringResult = actualBlog.toString();
        assertEquals("Not all who wander are lost", actualBlog.getContent());
        assertEquals("42", actualBlog.getId());
        assertEquals("2020-03-01", actualBlog.getPublishedDate());
        assertEquals("Status", actualBlog.getStatus());
        assertEquals("Dr", actualBlog.getTitle());
        assertEquals("Blog(id=42, title=Dr, content=Not all who wander are lost, status=Status, publishedDate=2020-03-01)",
                actualToStringResult);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        assertNotEquals(blog, null);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals2() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        assertNotEquals(blog, "Different type to Blog");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Blog#equals(Object)}
     *   <li>{@link Blog#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals3() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        assertEquals(blog, blog);
        int expectedHashCodeResult = blog.hashCode();
        assertEquals(expectedHashCodeResult, blog.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Blog#equals(Object)}
     *   <li>{@link Blog#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals4() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertEquals(blog, blog1);
        int expectedHashCodeResult = blog.hashCode();
        assertEquals(expectedHashCodeResult, blog1.hashCode());
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals5() {
        Blog blog = new Blog();
        blog.setContent("42");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals6() {
        Blog blog = new Blog();
        blog.setContent(null);
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals7() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("Dr");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals8() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId(null);
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals9() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020/03/01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals10() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate(null);
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals11() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("42");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals12() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus(null);
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals13() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("42");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Method under test: {@link Blog#equals(Object)}
     */
    @Test
    public void testEquals14() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle(null);

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertNotEquals(blog, blog1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Blog#equals(Object)}
     *   <li>{@link Blog#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals15() {
        Blog blog = new Blog();
        blog.setContent(null);
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent(null);
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertEquals(blog, blog1);
        int expectedHashCodeResult = blog.hashCode();
        assertEquals(expectedHashCodeResult, blog1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Blog#equals(Object)}
     *   <li>{@link Blog#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals16() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId(null);
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId(null);
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertEquals(blog, blog1);
        int expectedHashCodeResult = blog.hashCode();
        assertEquals(expectedHashCodeResult, blog1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Blog#equals(Object)}
     *   <li>{@link Blog#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals17() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate(null);
        blog.setStatus("Status");
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate(null);
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        assertEquals(blog, blog1);
        int expectedHashCodeResult = blog.hashCode();
        assertEquals(expectedHashCodeResult, blog1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Blog#equals(Object)}
     *   <li>{@link Blog#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals18() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus(null);
        blog.setTitle("Dr");

        Blog blog1 = new Blog();
        blog1.setContent("Not all who wander are lost");
        blog1.setId("42");
        blog1.setPublishedDate("2020-03-01");
        blog1.setStatus(null);
        blog1.setTitle("Dr");
        assertEquals(blog, blog1);
        int expectedHashCodeResult = blog.hashCode();
        assertEquals(expectedHashCodeResult, blog1.hashCode());
    }
}

