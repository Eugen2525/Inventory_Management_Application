package ru.javawebinar.topjava.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.TestUtil.authorize;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

/**
 * https://github.com/JavaOPs/topjava
 */
public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUserList() throws Exception {
        authorize(ADMIN);
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"));
    }

    @Test
    public void testUserListUnAuth() throws Exception {
        authorize(USER);
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("exception/exception"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/exception/exception.jsp"));
    }

    @Test
    public void testMealList() throws Exception {
        authorize(USER);
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"));
    }
}