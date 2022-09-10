package com.example.blogapp.service;

import com.example.blogapp.domain.User;
import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
import com.example.blogapp.domain.opinion.OpinionDomain;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.exception.ResourceNotFound;
import com.example.blogapp.model.opinion.Opinion;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.OpinionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OpinionService.class})
@ExtendWith(SpringExtension.class)
class OpinionServiceTest {
    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OpinionRepository opinionRepository;

    @Autowired
    private OpinionService opinionServiceImpl;

    /**
     * Method under test: {@link OpinionService#createOpinion(int, Opinion)}
     */
    @Test
    void testCreateOpinion() {
        when(opinionRepository.save(any()))
                .thenThrow(new BlogApiException(HttpStatus.CONTINUE, "An error occurred"));

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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog1);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        when(modelMapper.map(any(), (Class<OpinionDomain>) any())).thenReturn(opinion);
        assertThrows(BlogApiException.class, () -> opinionServiceImpl.createOpinion(1,
                new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost")));
        verify(opinionRepository).save(any());
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<OpinionDomain>) any());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionsByBlogId(int)}
     */
    @Test
    void testGetOpinionsByBlogId() {
        when(opinionRepository.findByBlogId(anyInt())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(opinionServiceImpl.getOpinionsByBlogId(1).isEmpty());
        verify(opinionRepository).findByBlogId(anyInt());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionsByBlogId(int)}
     */
    @Test
    void testGetOpinionsByBlogId2() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");

        ArrayList<OpinionDomain> opinionList = new ArrayList<>();
        opinionList.add(opinion);
        Optional<List<OpinionDomain>> ofResult = Optional.of(opinionList);
        when(opinionRepository.findByBlogId(anyInt())).thenReturn(ofResult);
        when(modelMapper.map(any(), (Class<Opinion>) any()))
                .thenReturn(new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost"));
        assertEquals(1, opinionServiceImpl.getOpinionsByBlogId(1).size());
        verify(opinionRepository).findByBlogId(anyInt());
        verify(modelMapper).map(any(), (Class<Opinion>) any());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionsByBlogId(int)}
     */
    @Test
    void testGetOpinionsByBlogId3() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");

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

        OpinionDomain opinion1 = new OpinionDomain();
        opinion1.setBlog(blog1);
        opinion1.setEmail("jane.doe@example.org");
        opinion1.setId(1);
        opinion1.setMessage("Not all who wander are lost");
        opinion1.setName("Name");

        ArrayList<OpinionDomain> opinionList = new ArrayList<>();
        opinionList.add(opinion1);
        opinionList.add(opinion);
        Optional<List<OpinionDomain>> ofResult = Optional.of(opinionList);
        when(opinionRepository.findByBlogId(anyInt())).thenReturn(ofResult);
        when(modelMapper.map(any(), (Class<Opinion>) any()))
                .thenReturn(new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost"));
        assertEquals(2, opinionServiceImpl.getOpinionsByBlogId(1).size());
        verify(opinionRepository).findByBlogId(anyInt());
        verify(modelMapper, atLeast(1)).map(any(), (Class<Opinion>) any());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionsByBlogId(int)}
     */
    @Test
    void testGetOpinionsByBlogId4() {
        when(opinionRepository.findByBlogId(anyInt())).thenReturn(Optional.empty());
        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any()))
                .thenReturn(new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost"));
        assertThrows(ResourceNotFound.class, () -> opinionServiceImpl.getOpinionsByBlogId(1));
        verify(opinionRepository).findByBlogId(anyInt());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionById(int, int)}
     */
    @Test
    void testGetOpinionById() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        when(opinionRepository.findById(any())).thenReturn(ofResult);

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
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog1);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        Opinion opinionDto = new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost");

        when(modelMapper.map(any(), (Class<Opinion>) any())).thenReturn(opinionDto);
        assertSame(opinionDto, opinionServiceImpl.getOpinionById(1, 1));
        verify(opinionRepository).findById(any());
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<Opinion>) any());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionById(int, int)}
     */
    @Test
    void testGetOpinionById2() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        when(opinionRepository.findById(any())).thenReturn(ofResult);

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
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog1);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        when(modelMapper.map(any(), (Class<Opinion>) any()))
                .thenThrow(new BlogApiException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(BlogApiException.class, () -> opinionServiceImpl.getOpinionById(1, 1));
        verify(opinionRepository).findById(any());
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<Opinion>) any());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionById(int, int)}
     */
    @Test
    void testGetOpinionById3() {
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

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog1 = mock(BlogCreateDomain.class);
        when(blog1.getId()).thenReturn(-1);
        doNothing().when(blog1).setContent(any());
        doNothing().when(blog1).setId(anyInt());
        doNothing().when(blog1).setOpinions(any());
        doNothing().when(blog1).setPublishedDate(any());
        doNothing().when(blog1).setStatus(any());
        doNothing().when(blog1).setTitle(any());
        doNothing().when(blog1).setUser(any());
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        OpinionDomain opinion = mock(OpinionDomain.class);
        when(opinion.getBlog()).thenReturn(blog1);
        doNothing().when(opinion).setBlog(any());
        doNothing().when(opinion).setEmail(any());
        doNothing().when(opinion).setId(anyInt());
        doNothing().when(opinion).setMessage(any());
        doNothing().when(opinion).setName(any());
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        when(opinionRepository.findById(any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setBlogs(new HashSet<>());
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog2 = new BlogCreateDomain();
        blog2.setContent("Not all who wander are lost");
        blog2.setId(1);
        blog2.setOpinions(new HashSet<>());
        blog2.setPublishedDate(mock(Timestamp.class));
        blog2.setStatus("Status");
        blog2.setTitle("Dr");
        blog2.setUser(user2);
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog2);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any()))
                .thenReturn(new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost"));
        assertThrows(BlogApiException.class, () -> opinionServiceImpl.getOpinionById(1, 1));

    }

    /**
     * Method under test: {@link OpinionService#getOpinionById(int, int)}
     */
    @Test
    void testGetOpinionById4() {
        when(opinionRepository.findById(any())).thenReturn(Optional.empty());

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

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog1 = mock(BlogCreateDomain.class);
        when(blog1.getId()).thenReturn(-1);
        doNothing().when(blog1).setContent(any());
        doNothing().when(blog1).setId(anyInt());
        doNothing().when(blog1).setOpinions(any());
        doNothing().when(blog1).setPublishedDate(any());
        doNothing().when(blog1).setStatus(any());
        doNothing().when(blog1).setTitle(any());
        doNothing().when(blog1).setUser(any());
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        OpinionDomain opinion = mock(OpinionDomain.class);
        when(opinion.getBlog()).thenReturn(blog1);
        doNothing().when(opinion).setBlog(any());
        doNothing().when(opinion).setEmail(any());
        doNothing().when(opinion).setId(anyInt());
        doNothing().when(opinion).setMessage(any());
        doNothing().when(opinion).setName(any());
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");

        User user2 = new User();
        user2.setBlogs(new HashSet<>());
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog2 = new BlogCreateDomain();
        blog2.setContent("Not all who wander are lost");
        blog2.setId(1);
        blog2.setOpinions(new HashSet<>());
        blog2.setPublishedDate(mock(Timestamp.class));
        blog2.setStatus("Status");
        blog2.setTitle("Dr");
        blog2.setUser(user2);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog2);
        when(blogRepository.findById(any())).thenReturn(ofResult);
        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any()))
                .thenReturn(new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost"));
        assertThrows(ResourceNotFound.class, () -> opinionServiceImpl.getOpinionById(1, 1));
        verify(opinionRepository).findById(any());
    }

    /**
     * Method under test: {@link OpinionService#getOpinionById(int, int)}
     */
    @Test
    void testGetOpinionById5() {
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

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog1 = mock(BlogCreateDomain.class);
        when(blog1.getId()).thenReturn(-1);
        doNothing().when(blog1).setContent(any());
        doNothing().when(blog1).setId(anyInt());
        doNothing().when(blog1).setOpinions(any());
        doNothing().when(blog1).setPublishedDate(any());
        doNothing().when(blog1).setStatus(any());
        doNothing().when(blog1).setTitle(any());
        doNothing().when(blog1).setUser(any());
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        OpinionDomain opinion = mock(OpinionDomain.class);
        when(opinion.getBlog()).thenReturn(blog1);
        doNothing().when(opinion).setBlog(any());
        doNothing().when(opinion).setEmail(any());
        doNothing().when(opinion).setId(anyInt());
        doNothing().when(opinion).setMessage(any());
        doNothing().when(opinion).setName(any());
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        when(opinionRepository.findById(any())).thenReturn(ofResult);
        when(blogRepository.findById(any())).thenReturn(Optional.empty());
        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any()))
                .thenReturn(new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost"));
        assertThrows(ResourceNotFound.class, () -> opinionServiceImpl.getOpinionById(1, 1));
    }

    /**
     * Method under test: {@link OpinionService#updateOpinionById(int, int, Opinion)}
     */
    @Test
    void testUpdateOpinionById() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);

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

        OpinionDomain opinion1 = new OpinionDomain();
        opinion1.setBlog(blog1);
        opinion1.setEmail("jane.doe@example.org");
        opinion1.setId(1);
        opinion1.setMessage("Not all who wander are lost");
        opinion1.setName("Name");
        when(opinionRepository.save(any())).thenReturn(opinion1);
        when(opinionRepository.findById(any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setBlogs(new HashSet<>());
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog2 = new BlogCreateDomain();
        blog2.setContent("Not all who wander are lost");
        blog2.setId(1);
        blog2.setOpinions(new HashSet<>());
        blog2.setPublishedDate(mock(Timestamp.class));
        blog2.setStatus("Status");
        blog2.setTitle("Dr");
        blog2.setUser(user2);
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog2);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        Opinion opinionDto = new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost");

        when(modelMapper.map(any(), (Class<Opinion>) any())).thenReturn(opinionDto);
        assertSame(opinionDto, opinionServiceImpl.updateOpinionById(1, 1,
                new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost")));
        verify(opinionRepository).save(any());
        verify(opinionRepository).findById(any());
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<Opinion>) any());
    }

    /**
     * Method under test: {@link OpinionService#updateOpinionById(int, int, Opinion)}
     */
    @Test
    void testUpdateOpinionById2() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);

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

        OpinionDomain opinion1 = new OpinionDomain();
        opinion1.setBlog(blog1);
        opinion1.setEmail("jane.doe@example.org");
        opinion1.setId(1);
        opinion1.setMessage("Not all who wander are lost");
        opinion1.setName("Name");
        when(opinionRepository.save(any())).thenReturn(opinion1);
        when(opinionRepository.findById(any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setBlogs(new HashSet<>());
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog2 = new BlogCreateDomain();
        blog2.setContent("Not all who wander are lost");
        blog2.setId(1);
        blog2.setOpinions(new HashSet<>());
        blog2.setPublishedDate(mock(Timestamp.class));
        blog2.setStatus("Status");
        blog2.setTitle("Dr");
        blog2.setUser(user2);
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog2);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        when(modelMapper.map(any(), (Class<Opinion>) any()))
                .thenThrow(new BlogApiException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(BlogApiException.class, () -> opinionServiceImpl.updateOpinionById(1, 1,
                new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost")));
        verify(opinionRepository).save(any());
        verify(opinionRepository).findById(any());
        verify(blogRepository).findById(any());
        verify(modelMapper).map(any(), (Class<Opinion>) any());
    }

    /**
     * Method under test: {@link OpinionService#updateOpinionById(int, int, Opinion)}
     */
    @Test
    void testUpdateOpinionById3() {
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

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog1 = mock(BlogCreateDomain.class);
        when(blog1.getId()).thenReturn(-1);
        doNothing().when(blog1).setContent(any());
        doNothing().when(blog1).setId(anyInt());
        doNothing().when(blog1).setOpinions(any());
        doNothing().when(blog1).setPublishedDate(any());
        doNothing().when(blog1).setStatus(any());
        doNothing().when(blog1).setTitle(any());
        doNothing().when(blog1).setUser(any());
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        OpinionDomain opinion = mock(OpinionDomain.class);
        when(opinion.getBlog()).thenReturn(blog1);
        doNothing().when(opinion).setBlog(any());
        doNothing().when(opinion).setEmail(any());
        doNothing().when(opinion).setId(anyInt());
        doNothing().when(opinion).setMessage(any());
        doNothing().when(opinion).setName(any());
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);

        User user2 = new User();
        user2.setBlogs(new HashSet<>());
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog2 = new BlogCreateDomain();
        blog2.setContent("Not all who wander are lost");
        blog2.setId(1);
        blog2.setOpinions(new HashSet<>());
        blog2.setPublishedDate(mock(Timestamp.class));
        blog2.setStatus("Status");
        blog2.setTitle("Dr");
        blog2.setUser(user2);

        OpinionDomain opinion1 = new OpinionDomain();
        opinion1.setBlog(blog2);
        opinion1.setEmail("jane.doe@example.org");
        opinion1.setId(1);
        opinion1.setMessage("Not all who wander are lost");
        opinion1.setName("Name");
        when(opinionRepository.save(any())).thenReturn(opinion1);
        when(opinionRepository.findById(any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setBlogs(new HashSet<>());
        user3.setId(1);
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog3 = new BlogCreateDomain();
        blog3.setContent("Not all who wander are lost");
        blog3.setId(1);
        blog3.setOpinions(new HashSet<>());
        blog3.setPublishedDate(mock(Timestamp.class));
        blog3.setStatus("Status");
        blog3.setTitle("Dr");
        blog3.setUser(user3);
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog3);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any()))
                .thenReturn(new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost"));
        assertThrows(BlogApiException.class, () -> opinionServiceImpl.updateOpinionById(1, 1,
                new Opinion(1, "Name", "jane.doe@example.org", "Not all who wander are lost")));
        verify(opinionRepository).findById(any());
        verify(opinion).getBlog();
        verify(opinion).setBlog(any());
        verify(opinion).setEmail(any());
        verify(opinion).setId(anyInt());
        verify(opinion).setMessage(any());
        verify(opinion).setName(any());
        verify(blog1).getId();
        verify(blog1).setContent(any());
        verify(blog1).setId(anyInt());
        verify(blog1).setOpinions(any());
        verify(blog1).setPublishedDate(any());
        verify(blog1).setStatus(any());
        verify(blog1).setTitle(any());
        verify(blog1).setUser(any());
        verify(blogRepository).findById(any());
    }

    /**
     * Method under test: {@link OpinionService#deleteOpinionById(int, int)}
     */
    @Test
    void testDeleteOpinionById() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        doNothing().when(opinionRepository).delete(any());
        when(opinionRepository.findById(any())).thenReturn(ofResult);

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
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog1);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        opinionServiceImpl.deleteOpinionById(1, 1);
        verify(opinionRepository).findById(any());
        verify(opinionRepository).delete(any());
        verify(blogRepository).findById(any());
    }

    /**
     * Method under test: {@link OpinionService#deleteOpinionById(int, int)}
     */
    @Test
    void testDeleteOpinionById2() {
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

        OpinionDomain opinion = new OpinionDomain();
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        doThrow(new BlogApiException(HttpStatus.CONTINUE, "An error occurred")).when(opinionRepository)
                .delete(any());
        when(opinionRepository.findById(any())).thenReturn(ofResult);

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
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog1);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        assertThrows(BlogApiException.class, () -> opinionServiceImpl.deleteOpinionById(1, 1));
        verify(opinionRepository).findById(any());
        verify(opinionRepository).delete(any());
        verify(blogRepository).findById(any());
    }

    /**
     * Method under test: {@link OpinionService#deleteOpinionById(int, int)}
     */
    @Test
    void testDeleteOpinionById3() {
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

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog1 = mock(BlogCreateDomain.class);
        when(blog1.getId()).thenReturn(-1);
        doNothing().when(blog1).setContent(any());
        doNothing().when(blog1).setId(anyInt());
        doNothing().when(blog1).setOpinions(any());
        doNothing().when(blog1).setPublishedDate(any());
        doNothing().when(blog1).setStatus(any());
        doNothing().when(blog1).setTitle(any());
        doNothing().when(blog1).setUser(any());
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        OpinionDomain opinion = mock(OpinionDomain.class);
        when(opinion.getBlog()).thenReturn(blog1);
        doNothing().when(opinion).setBlog(any());
        doNothing().when(opinion).setEmail(any());
        doNothing().when(opinion).setId(anyInt());
        doNothing().when(opinion).setMessage(any());
        doNothing().when(opinion).setName(any());
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        doNothing().when(opinionRepository).delete(any());
        when(opinionRepository.findById(any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setBlogs(new HashSet<>());
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog2 = new BlogCreateDomain();
        blog2.setContent("Not all who wander are lost");
        blog2.setId(1);
        blog2.setOpinions(new HashSet<>());
        blog2.setPublishedDate(mock(Timestamp.class));
        blog2.setStatus("Status");
        blog2.setTitle("Dr");
        blog2.setUser(user2);
        Optional<BlogCreateDomain> ofResult1 = Optional.of(blog2);
        when(blogRepository.findById(any())).thenReturn(ofResult1);
        assertThrows(BlogApiException.class, () -> opinionServiceImpl.deleteOpinionById(1, 1));
        verify(opinionRepository).findById(any());
        verify(opinion).getBlog();
        verify(opinion).setBlog(any());
        verify(opinion).setEmail(any());
        verify(opinion).setId(anyInt());
        verify(opinion).setMessage(any());
        verify(opinion).setName(any());
        verify(blog1).getId();
        verify(blog1).setContent(any());
        verify(blog1).setId(anyInt());
        verify(blog1).setOpinions(any());
        verify(blog1).setPublishedDate(any());
        verify(blog1).setStatus(any());
        verify(blog1).setTitle(any());
        verify(blog1).setUser(any());
        verify(blogRepository).findById(any());
    }

    /**
     * Method under test: {@link OpinionService#deleteOpinionById(int, int)}
     */
    @Test
    void testDeleteOpinionById4() {
        doNothing().when(opinionRepository).delete(any());
        when(opinionRepository.findById(any())).thenReturn(Optional.empty());

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

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog1 = mock(BlogCreateDomain.class);
        when(blog1.getId()).thenReturn(1);
        doNothing().when(blog1).setContent(any());
        doNothing().when(blog1).setId(anyInt());
        doNothing().when(blog1).setOpinions(any());
        doNothing().when(blog1).setPublishedDate(any());
        doNothing().when(blog1).setStatus(any());
        doNothing().when(blog1).setTitle(any());
        doNothing().when(blog1).setUser(any());
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        OpinionDomain opinion = mock(OpinionDomain.class);
        when(opinion.getBlog()).thenReturn(blog1);
        doNothing().when(opinion).setBlog(any());
        doNothing().when(opinion).setEmail(any());
        doNothing().when(opinion).setId(anyInt());
        doNothing().when(opinion).setMessage(any());
        doNothing().when(opinion).setName(any());
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");

        User user2 = new User();
        user2.setBlogs(new HashSet<>());
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setSecurityQuestionAnswer("Security Question Answer");

        BlogCreateDomain blog2 = new BlogCreateDomain();
        blog2.setContent("Not all who wander are lost");
        blog2.setId(1);
        blog2.setOpinions(new HashSet<>());
        blog2.setPublishedDate(mock(Timestamp.class));
        blog2.setStatus("Status");
        blog2.setTitle("Dr");
        blog2.setUser(user2);
        Optional<BlogCreateDomain> ofResult = Optional.of(blog2);
        when(blogRepository.findById(any())).thenReturn(ofResult);
        assertThrows(ResourceNotFound.class, () -> opinionServiceImpl.deleteOpinionById(1, 1));
        verify(opinionRepository).findById(any());
        verify(opinion).setBlog(any());
        verify(opinion).setEmail(any());
        verify(opinion).setId(anyInt());
        verify(opinion).setMessage(any());
        verify(opinion).setName(any());
        verify(blog1).setContent(any());
        verify(blog1).setId(anyInt());
        verify(blog1).setOpinions(any());
        verify(blog1).setPublishedDate(any());
        verify(blog1).setStatus(any());
        verify(blog1).setTitle(any());
        verify(blog1).setUser(any());
        verify(blogRepository).findById(any());
    }

    /**
     * Method under test: {@link OpinionService#deleteOpinionById(int, int)}
     */
    @Test
    void testDeleteOpinionById5() {
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

        User user1 = new User();
        user1.setBlogs(new HashSet<>());
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setSecurityQuestionAnswer("Security Question Answer");
        BlogCreateDomain blog1 = mock(BlogCreateDomain.class);
        when(blog1.getId()).thenReturn(1);
        doNothing().when(blog1).setContent(any());
        doNothing().when(blog1).setId(anyInt());
        doNothing().when(blog1).setOpinions(any());
        doNothing().when(blog1).setPublishedDate(any());
        doNothing().when(blog1).setStatus(any());
        doNothing().when(blog1).setTitle(any());
        doNothing().when(blog1).setUser(any());
        blog1.setContent("Not all who wander are lost");
        blog1.setId(1);
        blog1.setOpinions(new HashSet<>());
        blog1.setPublishedDate(mock(Timestamp.class));
        blog1.setStatus("Status");
        blog1.setTitle("Dr");
        blog1.setUser(user1);
        OpinionDomain opinion = mock(OpinionDomain.class);
        when(opinion.getBlog()).thenReturn(blog1);
        doNothing().when(opinion).setBlog(any());
        doNothing().when(opinion).setEmail(any());
        doNothing().when(opinion).setId(anyInt());
        doNothing().when(opinion).setMessage(any());
        doNothing().when(opinion).setName(any());
        opinion.setBlog(blog);
        opinion.setEmail("jane.doe@example.org");
        opinion.setId(1);
        opinion.setMessage("Not all who wander are lost");
        opinion.setName("Name");
        Optional<OpinionDomain> ofResult = Optional.of(opinion);
        doNothing().when(opinionRepository).delete(any());
        when(opinionRepository.findById(any())).thenReturn(ofResult);
        when(blogRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> opinionServiceImpl.deleteOpinionById(1, 1));
        verify(opinion).setBlog(any());
        verify(opinion).setEmail(any());
        verify(opinion).setId(anyInt());
        verify(opinion).setMessage(any());
        verify(opinion).setName(any());
        verify(blog1).setContent(any());
        verify(blog1).setId(anyInt());
        verify(blog1).setOpinions(any());
        verify(blog1).setPublishedDate(any());
        verify(blog1).setStatus(any());
        verify(blog1).setTitle(any());
        verify(blog1).setUser(any());
        verify(blogRepository).findById(any());
    }
}

