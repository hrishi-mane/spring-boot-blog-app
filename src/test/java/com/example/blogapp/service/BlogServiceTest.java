package com.example.blogapp.service;

import com.example.blogapp.domain.User;
import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
import com.example.blogapp.exception.ResourceNotFound;
import com.example.blogapp.model.blog.blogcreate.BlogCreate;
import com.example.blogapp.model.blog.blogdetails.BlogDetailsResponse;
import com.example.blogapp.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogService.class})
@ExtendWith(SpringExtension.class)
class BlogServiceTest {
    @MockBean
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link BlogService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlog() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        when(blogRepository.save(any())).thenReturn(blog);
        when(modelMapper.map(any(), any()))
                .thenThrow(new ResourceNotFound("Resource Name", "Field Name", 42));
        assertThrows(ResourceNotFound.class, () -> blogServiceImpl.createBlog(new BlogCreate()));
        verify(modelMapper).map(any(), (Class<BlogCreateDomain>) any());
    }

    /**
     * Method under test: {@link BlogService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlog2() {
        User user = mock(User.class);
        doNothing().when(user).setBlogs(any());
        doNothing().when(user).setId(any());
        doNothing().when(user).setName(any());
        doNothing().when(user).setPassword(any());
        doNothing().when(user).setSecurityQuestionAnswer(any());
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog = mock(BlogCreateDomain.class);
        doNothing().when(blog).setContent(any());
        doNothing().when(blog).setId(anyInt());
        doNothing().when(blog).setOpinions(any());
        doNothing().when(blog).setPublishedDate(any());
        doNothing().when(blog).setStatus(any());
        doNothing().when(blog).setTitle(any());
        doNothing().when(blog).setUser(any());
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        when(blogRepository.save(any())).thenReturn(blog);
        when(modelMapper.map(any(), any())).thenReturn(null);
        assertNull(blogServiceImpl.createBlog(new BlogCreate()));
        verify(blogRepository).save(any());
        verify(blog).setContent(any());
        verify(blog).setId(anyInt());
        verify(blog).setOpinions(any());
        verify(blog).setPublishedDate(any());
        verify(blog).setStatus(any());
        verify(blog).setTitle(any());
        verify(blog).setUser(any());
        verify(user).setBlogs(any());
        verify(user).setId(any());
        verify(user).setName(any());
        verify(user).setPassword(any());
        verify(user).setSecurityQuestionAnswer(any());
        verify(modelMapper, atLeast(1)).map(any(), (Class<BlogCreateDomain>) any());
    }

    /**
     * Method under test: {@link BlogService#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogs() {
        when(blogRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        BlogDetailsResponse actualBlogs = blogServiceImpl.getBlogs(1, 3, "Sort By", "Sort Dir");
        assertTrue(actualBlogs.getBlogs().isEmpty());
        assertTrue(actualBlogs.isLast());
        assertEquals(1, actualBlogs.getTotalPages());
        assertEquals(0, actualBlogs.getTotalElements());
        assertEquals(0, actualBlogs.getPageNo());
        verify(blogRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BlogService#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogs2() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("asc");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("asc");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("asc");
        blog.setTitle("Dr");
        blog.setUser(user);

        ArrayList<BlogCreateDomain> blogList = new ArrayList<>();
        blogList.add(blog);
        PageImpl<BlogCreateDomain> pageImpl = new PageImpl<>(blogList);
        when(blogRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(modelMapper.map(any(), (Class<BlogCreate>) any())).thenReturn(new BlogCreate());
        BlogDetailsResponse actualBlogs = blogServiceImpl.getBlogs(1, 3, "Sort By", "Sort Dir");
        assertEquals(1, actualBlogs.getBlogs().size());
        assertTrue(actualBlogs.isLast());
        assertEquals(1, actualBlogs.getTotalPages());
        assertEquals(1, actualBlogs.getTotalElements());
        assertEquals(0, actualBlogs.getPageNo());
        verify(blogRepository).findAll((Pageable) any());
        verify(modelMapper).map(any(), (Class<BlogCreate>) any());
    }

    /**
     * Method under test: {@link BlogService#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogs3() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("asc");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("asc");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("asc");
        blog.setTitle("Dr");
        blog.setUser(user);

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("asc");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("asc");

        BlogCreateDomain blog1 = new BlogCreateDomain();
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("asc");
        blog1.setTitle("Dr");
        blog1.setUser(user1);

        ArrayList<BlogCreateDomain> blogList = new ArrayList<>();
        blogList.add(blog1);
        blogList.add(blog);
        PageImpl<BlogCreateDomain> pageImpl = new PageImpl<>(blogList);
        when(blogRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(modelMapper.map(any(), (Class<BlogCreate>) any())).thenReturn(new BlogCreate());
        BlogDetailsResponse actualBlogs = blogServiceImpl.getBlogs(1, 3, "Sort By", "Sort Dir");
        assertEquals(2, actualBlogs.getBlogs().size());
        assertTrue(actualBlogs.isLast());
        assertEquals(1, actualBlogs.getTotalPages());
        assertEquals(2, actualBlogs.getTotalElements());
        assertEquals(0, actualBlogs.getPageNo());
        verify(blogRepository).findAll((Pageable) any());
        verify(modelMapper, atLeast(1)).map(any(), (Class<BlogCreate>) any());
    }


    /**
     * Method under test: {@link BlogService#getBlogById(int)}
     */
    @Test
    void testGetBlogById() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog);
        when(blogRepository.findById(any())).thenReturn(ofResult);
        BlogCreate blogCreateDto = new BlogCreate();
        when(modelMapper.map(any(), (Class<BlogCreate>) any())).thenReturn(blogCreateDto);
        assertSame(blogCreateDto, blogServiceImpl.getBlogById(1));
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<BlogCreate>) any());
    }

    /**
     * Method under test: {@link BlogService#getBlogById(int)}
     */
    @Test
    void testGetBlogById2() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog);
        when(blogRepository.findById(any())).thenReturn(ofResult);
        when(modelMapper.map(any(), (Class<BlogCreate>) any()))
                .thenThrow(new ResourceNotFound("Resource Name", "Field Name", 42));
        assertThrows(ResourceNotFound.class, () -> blogServiceImpl.getBlogById(1));
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<BlogCreate>) any());
    }

    /**
     * Method under test: {@link BlogService#getBlogById(int)}
     */
    @Test
    void testGetBlogById3() {
        when(blogRepository.findById(any())).thenReturn(Optional.empty());
        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any())).thenReturn(new BlogCreate());
        assertThrows(ResourceNotFound.class, () -> blogServiceImpl.getBlogById(1));
    }

    /**
     * Method under test: {@link BlogService#updateBlog(int, BlogCreate)}
     */
    @Test
    void testUpdateBlog() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog);

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog1 = new BlogCreateDomain();
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        when(blogRepository.save(any())).thenReturn(blog1);
        when(blogRepository.findById(any())).thenReturn(ofResult);
        BlogCreate blogCreateDto = new BlogCreate();
        when(modelMapper.map(any(), (Class<BlogCreate>) any())).thenReturn(blogCreateDto);
        assertSame(blogCreateDto, blogServiceImpl.updateBlog(1, new BlogCreate()));
        verify(blogRepository).save(any());
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<BlogCreate>) any());
    }

    /**
     * Method under test: {@link BlogService#updateBlog(int, BlogCreate)}
     */
    @Test
    void testUpdateBlog2() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog);

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog1 = new BlogCreateDomain();
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        when(blogRepository.save(any())).thenReturn(blog1);
        when(blogRepository.findById(any())).thenReturn(ofResult);
        when(modelMapper.map(any(), (Class<BlogCreate>) any()))
                .thenThrow(new ResourceNotFound("Resource Name", "Field Name", 42));
        assertThrows(ResourceNotFound.class, () -> blogServiceImpl.updateBlog(1, new BlogCreate()));
        verify(blogRepository).save(any());
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<BlogCreate>) any());
    }

    /**
     * Method under test: {@link BlogService#updateBlog(int, BlogCreate)}
     */
    @Test
    void testUpdateBlog3() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        when(blogRepository.save(any())).thenReturn(blog);
        when(blogRepository.findById(any())).thenReturn(Optional.empty());
        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any())).thenReturn(new BlogCreate());
        assertThrows(ResourceNotFound.class, () -> blogServiceImpl.updateBlog(1, new BlogCreate()));
    }

    /**
     * Method under test: {@link BlogService#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog);
        doNothing().when(blogRepository).delete(any());
        when(blogRepository.findById(any())).thenReturn(ofResult);
        blogServiceImpl.deleteBlog(1);
        verify(blogRepository).findById(any());
        verify(blogRepository).delete(any());
    }

    /**
     * Method under test: {@link BlogService#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog2() {
        User user = new User();
        user.setBlogs(new HashSet<>());
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog = new BlogCreateDomain();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setOpinions(new HashSet<>());
        blog.setPublishedDate(mock(Timestamp.class));
        blog.setStatus("Status");
        blog.setTitle("Dr");
        blog.setUser(user);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog);
        doThrow(new ResourceNotFound("Resource Name", "Field Name", 42)).when(blogRepository).delete(any());
        when(blogRepository.findById(any())).thenReturn(ofResult);
        assertThrows(ResourceNotFound.class, () -> blogServiceImpl.deleteBlog(1));
        verify(blogRepository).findById(any());
        verify(blogRepository).delete(any());
    }

    /**
     * Method under test: {@link BlogService#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog3() {
        doNothing().when(blogRepository).delete(any());
        when(blogRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> blogServiceImpl.deleteBlog(1));
        verify(blogRepository).findById(any());
    }
}

