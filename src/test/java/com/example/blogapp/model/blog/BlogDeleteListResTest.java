package com.example.blogapp.model.blog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlogDeleteListResTest {
    /**
     * Method under test: {@link BlogDeleteListRes#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new BlogDeleteListRes()).canEqual("Other"));
    }

    /**
     * Method under test: {@link BlogDeleteListRes#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes1 = new BlogDeleteListRes();
        blogDeleteListRes1.setResultStatus(resultStatus);
        assertTrue(blogDeleteListRes.canEqual(blogDeleteListRes1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link BlogDeleteListRes}
     *   <li>{@link BlogDeleteListRes#setResultStatus(ResultStatus)}
     *   <li>{@link BlogDeleteListRes#toString()}
     *   <li>{@link BlogDeleteListRes#getResultStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        BlogDeleteListRes actualBlogDeleteListRes = new BlogDeleteListRes();
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");
        actualBlogDeleteListRes.setResultStatus(resultStatus);
        String actualToStringResult = actualBlogDeleteListRes.toString();
        assertSame(resultStatus, actualBlogDeleteListRes.getResultStatus());
        assertEquals(
                "BlogDeleteListRes(resultStatus=ResultStatus(statusCode=Status Code, status=Status, message=Not all who"
                        + " wander are lost))",
                actualToStringResult);
    }

    /**
     * Method under test: {@link BlogDeleteListRes#equals(Object)}
     */
    @Test
    void testEquals() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();
        blogDeleteListRes.setResultStatus(resultStatus);
        assertNotEquals(blogDeleteListRes, null);
    }

    /**
     * Method under test: {@link BlogDeleteListRes#equals(Object)}
     */
    @Test
    void testEquals2() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();
        blogDeleteListRes.setResultStatus(resultStatus);
        assertNotEquals(blogDeleteListRes, "Different type to BlogDeleteListRes");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogDeleteListRes#equals(Object)}
     *   <li>{@link BlogDeleteListRes#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();
        blogDeleteListRes.setResultStatus(resultStatus);
        assertEquals(blogDeleteListRes, blogDeleteListRes);
        int expectedHashCodeResult = blogDeleteListRes.hashCode();
        assertEquals(expectedHashCodeResult, blogDeleteListRes.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogDeleteListRes#equals(Object)}
     *   <li>{@link BlogDeleteListRes#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();
        blogDeleteListRes.setResultStatus(resultStatus);

        ResultStatus resultStatus1 = new ResultStatus();
        resultStatus1.setMessage("Not all who wander are lost");
        resultStatus1.setStatus("Status");
        resultStatus1.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes1 = new BlogDeleteListRes();
        blogDeleteListRes1.setResultStatus(resultStatus1);
        assertEquals(blogDeleteListRes, blogDeleteListRes1);
        int expectedHashCodeResult = blogDeleteListRes.hashCode();
        assertEquals(expectedHashCodeResult, blogDeleteListRes1.hashCode());
    }

    /**
     * Method under test: {@link BlogDeleteListRes#equals(Object)}
     */
    @Test
    void testEquals5() {
        ResultStatus resultStatus = mock(ResultStatus.class);
        doNothing().when(resultStatus).setMessage(any());
        doNothing().when(resultStatus).setStatus(any());
        doNothing().when(resultStatus).setStatusCode(any());
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();
        blogDeleteListRes.setResultStatus(resultStatus);

        ResultStatus resultStatus1 = new ResultStatus();
        resultStatus1.setMessage("Not all who wander are lost");
        resultStatus1.setStatus("Status");
        resultStatus1.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes1 = new BlogDeleteListRes();
        blogDeleteListRes1.setResultStatus(resultStatus1);
        assertNotEquals(blogDeleteListRes, blogDeleteListRes1);
    }
}

