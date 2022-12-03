package com.example.blogapp.domain.blog;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BlogDaoTest {
    /**
     * Method under test: {@link BlogDao#canEqual(Object)}
     */
    @Test
    public void testCanEqual() {
        assertFalse((new BlogDao()).canEqual("Other"));
    }

    /**
     * Method under test: {@link BlogDao#canEqual(Object)}
     */
    @Test
    public void testCanEqual2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertFalse(blogDao.canEqual("42"));
    }

    /**
     * Method under test: {@link BlogDao#canEqual(Object)}
     */
    @Test
    public void testCanEqual3() {
        BlogDao blogDao = new BlogDao();

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertTrue(blogDao.canEqual(blogDao1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link BlogDao}
     *   <li>{@link BlogDao#setContent(String)}
     *   <li>{@link BlogDao#setId(int)}
     *   <li>{@link BlogDao#setPublishedDate(Timestamp)}
     *   <li>{@link BlogDao#setStatus(String)}
     *   <li>{@link BlogDao#setTitle(String)}
     *   <li>{@link BlogDao#toString()}
     *   <li>{@link BlogDao#getContent()}
     *   <li>{@link BlogDao#getId()}
     *   <li>{@link BlogDao#getStatus()}
     *   <li>{@link BlogDao#getTitle()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        BlogDao actualBlogDao = new BlogDao();
        actualBlogDao.setContent("Not all who wander are lost");
        actualBlogDao.setId(1);
        actualBlogDao.setPublishedDate(mock(Timestamp.class));
        actualBlogDao.setStatus("Status");
        actualBlogDao.setTitle("Dr");
        actualBlogDao.toString();
        assertEquals("Not all who wander are lost", actualBlogDao.getContent());
        assertEquals(1, actualBlogDao.getId());
        assertEquals("Status", actualBlogDao.getStatus());
        assertEquals("Dr", actualBlogDao.getTitle());
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertNotEquals(blogDao, null);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertNotEquals(blogDao, "Different type to BlogDao");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogDao#equals(Object)}
     *   <li>{@link BlogDao#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals3() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertEquals(blogDao, blogDao);
        int expectedHashCodeResult = blogDao.hashCode();
        assertEquals(expectedHashCodeResult, blogDao.hashCode());
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals4() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals5() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Dr");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals6() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent(null);
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals7() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(123);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals8() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(null);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals9() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Not all who wander are lost");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals10() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle(null);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals11() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent(null);
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent(null);
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogDao#equals(Object)}
     *   <li>{@link BlogDao#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals12() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(null);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(null);
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertEquals(blogDao, blogDao1);
        int expectedHashCodeResult = blogDao.hashCode();
        assertEquals(expectedHashCodeResult, blogDao1.hashCode());
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals13() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle(null);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle(null);
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals14() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(null);
        blogDao.setStatus("Dr");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(null);
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals15() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(null);
        blogDao.setStatus(null);
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(null);
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }
}

