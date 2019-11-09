package com.example.study.controller;

import com.example.study.dto.AccessTokenDTO;
import com.example.study.dto.GithubUser;
import com.example.study.mapper.UserMapper;
import com.example.study.model.User;
import com.example.study.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private UserMapper userMapper;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String url;



    @RequestMapping("/callback")
    public   String callback(@RequestParam(name="code") String code,
                             @RequestParam(name="state") String state,
                             HttpServletRequest request,
                             HttpServletResponse response)
    {
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(url);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);

        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if(githubUser!=null){
            User byAccountId = userMapper.findByAccountId(githubUser.getId());
            User user = new User();

            user.setToken(accessToken);
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId());
            user.setGmtCreate(System.currentTimeMillis());
            user.setAvatarUrl(githubUser.getAvatar_url());
            if(byAccountId==null) {
                userMapper.insert(user);
            }else{
                user.setId(byAccountId.getId());
                User updateUser = userMapper.updateToken(byAccountId);
            }

            request.getSession().setAttribute("user",githubUser);
            response.addCookie(new Cookie("token",githubUser.getId().toString()));
            System.out.println(githubUser.getAvatar_url());
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
