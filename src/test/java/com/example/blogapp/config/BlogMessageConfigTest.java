package com.example.blogapp.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {BlogMessageConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogMessageConfigTest {
    @Autowired
    private BlogMessageConfig blogMessageConfig;

    /**
     * Method under test: {@link BlogMessageConfig#canEqual(Object)}
     */
    @Test
    public void testCanEqual() {
        assertFalse((new BlogMessageConfig()).canEqual("Other"));
    }

    /**
     * Method under test: {@link BlogMessageConfig#canEqual(Object)}
     */
    @Test
    public void testCanEqual2() {
        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();

        BlogMessageConfig blogMessageConfig2 = new BlogMessageConfig();
        blogMessageConfig2.setCreationIssue("Creation Issue");
        blogMessageConfig2.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig2.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig2.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig2.setSuccessMessage("Success Message");
        assertTrue(blogMessageConfig1.canEqual(blogMessageConfig2));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link BlogMessageConfig}
     *   <li>{@link BlogMessageConfig#setCreationIssue(String)}
     *   <li>{@link BlogMessageConfig#setDeletionSuccessMessage(String)}
     *   <li>{@link BlogMessageConfig#setIncorrectObjectTypeMessage(String)}
     *   <li>{@link BlogMessageConfig#setInvalidBlogIdMessage(String)}
     *   <li>{@link BlogMessageConfig#setSuccessMessage(String)}
     *   <li>{@link BlogMessageConfig#toString()}
     *   <li>{@link BlogMessageConfig#getCreationIssue()}
     *   <li>{@link BlogMessageConfig#getDeletionSuccessMessage()}
     *   <li>{@link BlogMessageConfig#getIncorrectObjectTypeMessage()}
     *   <li>{@link BlogMessageConfig#getInvalidBlogIdMessage()}
     *   <li>{@link BlogMessageConfig#getSuccessMessage()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        BlogMessageConfig actualBlogMessageConfig = new BlogMessageConfig();
        actualBlogMessageConfig.setCreationIssue("Creation Issue");
        actualBlogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        actualBlogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        actualBlogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        actualBlogMessageConfig.setSuccessMessage("Success Message");
        String actualToStringResult = actualBlogMessageConfig.toString();
        assertEquals("Creation Issue", actualBlogMessageConfig.getCreationIssue());
        assertEquals("Deletion Success Message", actualBlogMessageConfig.getDeletionSuccessMessage());
        assertEquals("Incorrect Object Type Message", actualBlogMessageConfig.getIncorrectObjectTypeMessage());
        assertEquals("Invalid Blog Id Message", actualBlogMessageConfig.getInvalidBlogIdMessage());
        assertEquals("Success Message", actualBlogMessageConfig.getSuccessMessage());
        assertEquals(
                "BlogMessageConfig(creationIssue=Creation Issue, successMessage=Success Message, invalidBlogIdMessage=Invalid"
                        + " Blog Id Message, deletionSuccessMessage=Deletion Success Message, incorrectObjectTypeMessage=Incorrect"
                        + " Object Type Message)",
                actualToStringResult);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, null);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals2() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, "Different type to BlogMessageConfig");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogMessageConfig#equals(Object)}
     *   <li>{@link BlogMessageConfig#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals3() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");
        assertEquals(blogMessageConfig, blogMessageConfig);
        int expectedHashCodeResult = blogMessageConfig.hashCode();
        assertEquals(expectedHashCodeResult, blogMessageConfig.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogMessageConfig#equals(Object)}
     *   <li>{@link BlogMessageConfig#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals4() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertEquals(blogMessageConfig, blogMessageConfig1);
        int expectedHashCodeResult = blogMessageConfig.hashCode();
        assertEquals(expectedHashCodeResult, blogMessageConfig1.hashCode());
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals5() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Success Message");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals6() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue(null);
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals7() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Creation Issue");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals8() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage(null);
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals9() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Creation Issue");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals10() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage(null);
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals11() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Creation Issue");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals12() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage(null);
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals13() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Creation Issue");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Method under test: {@link BlogMessageConfig#equals(Object)}
     */
    @Test
    public void testEquals14() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage(null);

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertNotEquals(blogMessageConfig, blogMessageConfig1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogMessageConfig#equals(Object)}
     *   <li>{@link BlogMessageConfig#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals15() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue(null);
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue(null);
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertEquals(blogMessageConfig, blogMessageConfig1);
        int expectedHashCodeResult = blogMessageConfig.hashCode();
        assertEquals(expectedHashCodeResult, blogMessageConfig1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogMessageConfig#equals(Object)}
     *   <li>{@link BlogMessageConfig#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals16() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage(null);
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage(null);
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertEquals(blogMessageConfig, blogMessageConfig1);
        int expectedHashCodeResult = blogMessageConfig.hashCode();
        assertEquals(expectedHashCodeResult, blogMessageConfig1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogMessageConfig#equals(Object)}
     *   <li>{@link BlogMessageConfig#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals17() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage(null);
        blogMessageConfig.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage(null);
        blogMessageConfig1.setInvalidBlogIdMessage("Invalid Blog Id Message");
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertEquals(blogMessageConfig, blogMessageConfig1);
        int expectedHashCodeResult = blogMessageConfig.hashCode();
        assertEquals(expectedHashCodeResult, blogMessageConfig1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogMessageConfig#equals(Object)}
     *   <li>{@link BlogMessageConfig#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals18() {
        BlogMessageConfig blogMessageConfig = new BlogMessageConfig();
        blogMessageConfig.setCreationIssue("Creation Issue");
        blogMessageConfig.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig.setInvalidBlogIdMessage(null);
        blogMessageConfig.setSuccessMessage("Success Message");

        BlogMessageConfig blogMessageConfig1 = new BlogMessageConfig();
        blogMessageConfig1.setCreationIssue("Creation Issue");
        blogMessageConfig1.setDeletionSuccessMessage("Deletion Success Message");
        blogMessageConfig1.setIncorrectObjectTypeMessage("Incorrect Object Type Message");
        blogMessageConfig1.setInvalidBlogIdMessage(null);
        blogMessageConfig1.setSuccessMessage("Success Message");
        assertEquals(blogMessageConfig, blogMessageConfig1);
        int expectedHashCodeResult = blogMessageConfig.hashCode();
        assertEquals(expectedHashCodeResult, blogMessageConfig1.hashCode());
    }
}

