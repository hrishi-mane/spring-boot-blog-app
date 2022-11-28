package com.example.blogapp.domain.blog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
     *   <li>{@link BlogDao#setPublishedDate(String)}
     *   <li>{@link BlogDao#setStatus(String)}
     *   <li>{@link BlogDao#setTitle(String)}
     *   <li>{@link BlogDao#toString()}
     *   <li>{@link BlogDao#getContent()}
     *   <li>{@link BlogDao#getId()}
     *   <li>{@link BlogDao#getPublishedDate()}
     *   <li>{@link BlogDao#getStatus()}
     *   <li>{@link BlogDao#getTitle()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        BlogDao actualBlogDao = new BlogDao();
        actualBlogDao.setContent("Not all who wander are lost");
        actualBlogDao.setId(1);
        actualBlogDao.setPublishedDate("2020-03-01");
        actualBlogDao.setStatus("Status");
        actualBlogDao.setTitle("Dr");
        String actualToStringResult = actualBlogDao.toString();
        assertEquals("Not all who wander are lost", actualBlogDao.getContent());
        assertEquals(1, actualBlogDao.getId());
        assertEquals("2020-03-01", actualBlogDao.getPublishedDate());
        assertEquals("Status", actualBlogDao.getStatus());
        assertEquals("Dr", actualBlogDao.getTitle());
        assertEquals(
                "BlogDao(id=1, title=Dr, content=Not all who wander are lost, publishedDate=2020-03-01," + " status=Status)",
                actualToStringResult);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
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
        blogDao.setPublishedDate("2020-03-01");
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
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertEquals(blogDao, blogDao);
        int expectedHashCodeResult = blogDao.hashCode();
        assertEquals(expectedHashCodeResult, blogDao.hashCode());
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
    public void testEquals4() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
    public void testEquals5() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Dr");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
        blogDao.setPublishedDate("2020/03/01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
        blogDao.setPublishedDate(null);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Dr");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus(null);
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals12() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Not all who wander are lost");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertNotEquals(blogDao, blogDao1);
    }

    /**
     * Method under test: {@link BlogDao#equals(Object)}
     */
    @Test
    public void testEquals13() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle(null);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
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
    public void testEquals14() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent(null);
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent(null);
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        assertEquals(blogDao, blogDao1);
        int expectedHashCodeResult = blogDao.hashCode();
        assertEquals(expectedHashCodeResult, blogDao1.hashCode());
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
    public void testEquals15() {
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
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogDao#equals(Object)}
     *   <li>{@link BlogDao#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals16() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus(null);
        blogDao.setTitle("Dr");

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus(null);
        blogDao1.setTitle("Dr");
        assertEquals(blogDao, blogDao1);
        int expectedHashCodeResult = blogDao.hashCode();
        assertEquals(expectedHashCodeResult, blogDao1.hashCode());
    }
}

