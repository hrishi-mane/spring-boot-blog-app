package com.example.blogapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogListRes;
import com.example.blogapp.repository.BlogRepository;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {BlogListService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogListServiceTest {
    @Autowired
    private BlogListService blogListService;

    @MockBean
    private BlogObjectMapper blogObjectMapper;

    @MockBean
    private BlogRepository blogRepository;

    /**
     * Method under test: {@link BlogListService#getBlogs(int, int, String, String)}
     */
    @Test
    public void testGetBlogs() {
        when(blogRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        BlogListRes actualBlogs = blogListService.getBlogs(1, 3, "Sort By", "Sort Dir");
        assertTrue(actualBlogs.getBlogList().isEmpty());
        assertTrue(actualBlogs.isLast());
        assertEquals(1, actualBlogs.getTotalPages());
        assertEquals(0, actualBlogs.getTotalElements());
        assertEquals(0, actualBlogs.getPageNo());
        assertEquals("Success", actualBlogs.getResultStatus().getStatus());
        verify(blogRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BlogListService#getBlogs(int, int, String, String)}
     */
    @Test
    public void testGetBlogs2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("asc");
        blogDao.setTitle("Dr");

        ArrayList<BlogDao> blogDaoList = new ArrayList<>();
        blogDaoList.add(blogDao);
        PageImpl<BlogDao> pageImpl = new PageImpl<>(blogDaoList);
        when(blogRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(blogObjectMapper.convertsBlogPageToBlog((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));
        assertThrows(BlogApiException.class, () -> blogListService.getBlogs(1, 3, "Sort By", "Sort Dir"));
        verify(blogRepository).findAll((Pageable) any());
        verify(blogObjectMapper).convertsBlogPageToBlog((BlogDao) any());
    }

    /**
     * Method under test: {@link BlogListService#getBlogs(int, int, String, String)}
     */
    @Test
    public void testGetBlogs3() {
        when(blogRepository.findAll((Pageable) any())).thenReturn(null);
        when(blogObjectMapper.convertsBlogPageToBlog((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));
        assertThrows(BlogApiException.class, () -> blogListService.getBlogs(1, 3, "Sort By", "Sort Dir"));
        verify(blogRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BlogListService#getBlogs(int, int, String, String)}
     */
    @Test
    public void testGetBlogs4() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("asc");
        blogDao.setTitle("Dr");

        ArrayList<BlogDao> blogDaoList = new ArrayList<>();
        blogDaoList.add(blogDao);
        PageImpl<BlogDao> pageImpl = new PageImpl<>(blogDaoList);
        when(blogRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(blogObjectMapper.convertsBlogPageToBlog((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));
        assertThrows(BlogApiException.class, () -> blogListService.getBlogs(-1, 3, "Sort By", "Sort Dir"));
    }

    /**
     * Method under test: {@link BlogListService#getBlogs(int, int, String, String)}
     */
    @Test
    public void testGetBlogs5() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("asc");
        blogDao.setTitle("Dr");

        ArrayList<BlogDao> blogDaoList = new ArrayList<>();
        blogDaoList.add(blogDao);
        PageImpl<BlogDao> pageImpl = new PageImpl<>(blogDaoList);
        when(blogRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(blogObjectMapper.convertsBlogPageToBlog((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));
        assertThrows(BlogApiException.class, () -> blogListService.getBlogs(1, 3, " ", "Sort Dir"));
    }

    /**
     * Method under test: {@link BlogListService#getBlogs(int, int, String, String)}
     */
    @Test
    public void testGetBlogs6() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("asc");
        blogDao.setTitle("Dr");

        ArrayList<BlogDao> blogDaoList = new ArrayList<>();
        blogDaoList.add(blogDao);
        PageImpl<BlogDao> pageImpl = new PageImpl<>(blogDaoList);
        when(blogRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(blogObjectMapper.convertsBlogPageToBlog((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));
        assertThrows(BlogApiException.class, () -> blogListService.getBlogs(1, 3, "Sort By", "asc"));
        verify(blogRepository).findAll((Pageable) any());
        verify(blogObjectMapper).convertsBlogPageToBlog((BlogDao) any());
    }
}

