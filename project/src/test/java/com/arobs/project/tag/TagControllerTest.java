//package com.arobs.project.tag;
//
//import com.arobs.project.dtos.TagDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TagControllerTest {
//    @MockBean
//    TagServiceImpl tagService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void whenHandleFindByDescription_givenDescription_returnResponseEntity() throws Exception {
//        TagDTO foundTag = new TagDTO(1, "test");
//        when(tagService.findTagByDescription(any(String.class))).thenReturn(foundTag);
//        MvcResult mvcResult = mockMvc.perform(get("/library-app/tags/description")
//                .param("description", foundTag.getTagDescription()))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("test"));
//    }
//
//    @Test
//    void whenHandleFindById_givenId_returnResponseEntity() throws Exception {
//        TagDTO foundTag = new TagDTO(1, "test");
//        when(tagService.findTagById(any(int.class))).thenReturn(foundTag);
//        MvcResult mvcResult = mockMvc.perform(get("/library-app/tags/{id}", foundTag.getId()))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("test"));
//    }
//
//    @Test
//    void whenHandleInsertTag_givenTagDTO_returnResponseEntity() throws Exception {
//        TagDTO insertedTag = new TagDTO(1, "test insert");
//        when(tagService.insertTag(any(TagDTO.class))).thenReturn(insertedTag);
//        MvcResult mvcResult = mockMvc.perform(post("/library-app/tags")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content("{\n" +
//                        "  \"id\": 1,\n" +
//                        "  \"tagDescription\": \"test insert\"\n" +
//                        "}"))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("1"));
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("test insert"));
//    }
//
//    @Test
//    void whenHandleDeleteTag_givenId_returnResponseEntity() throws Exception {
//        when(tagService.deleteTag(any(int.class))).thenReturn(true);
//        MvcResult mvcResult = mockMvc.perform(delete("/library-app/tags/{id}", 1))
//                .andExpect(status().isOk())
//                .andReturn();
//        mvcResult.getResponse().getContentAsString().equals("Tag successfully deleted!");
//    }
//
//    @Test
//    void whenHandleFindAllTags_given_returnResponseEntity() throws Exception {
//        List<TagDTO> dtoList = new ArrayList<>(5);
//        dtoList.add(new TagDTO(1, "first"));
//        dtoList.add(new TagDTO(2, "second"));
//        when(tagService.findAllTags()).thenReturn(dtoList);
//        MvcResult mvcResult = mockMvc.perform(get("/library-app/tags"))
//                .andExpect(status().isOk())
//                .andReturn();
//        mvcResult.getResponse().getContentAsString().contains("first");
//    }
//}
