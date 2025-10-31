package org.yiqixue.bootshop.controller;

import org.yiqixue.bootshop.entity.MybatisUser;
import org.yiqixue.bootshop.service.MybatisUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyBatis用户控制器
 * 处理用户相关的HTTP请求
 */
@Controller
@RequestMapping("/mybatis-users")
public class MybatisUserController {

    @Autowired
    private MybatisUserService userService;

    /**
     * 显示用户编辑表单
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        MybatisUser user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        model.addAttribute("user", user);
        model.addAttribute("isEdit", true);
        addFormAttributes(model);
        return "mybatis-user/register";
    }

    /**
     * 处理用户编辑表单提交
     */
    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable Long id,
                            @Valid @ModelAttribute("user") MybatisUser user,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        
        user.setId(id); // 确保ID正确
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            addFormAttributes(model);
            return "mybatis-user/register";
        }

        try {
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("successMessage", 
                "用户信息更新成功！");
            return "redirect:/mybatis-users/" + id;
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("isEdit", true);
            addFormAttributes(model);
            return "mybatis-user/register";
        }
    }

    /**
     * 显示注册表单
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new MybatisUser());
        model.addAttribute("isEdit", false);
        model.addAttribute("countries", getCountries());
        model.addAttribute("cities", getCities());
        model.addAttribute("genderOptions", getGenderOptions());
        model.addAttribute("hobbyOptions", getHobbyOptions());
        return "mybatis-user/register";
    }

    /**
     * 处理注册表单提交
     */
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") MybatisUser user,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            addFormAttributes(model);
            return "mybatis-user/register";
        }

        if (user.getTermsAccepted() == null || !user.getTermsAccepted()) {
            bindingResult.rejectValue("termsAccepted", "error.user", "必须同意服务条款才能注册");
            model.addAttribute("isEdit", false);
            addFormAttributes(model);
            return "mybatis-user/register";
        }

        try {
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("successMessage", 
                "注册成功！欢迎您，" + user.getUsername());
            return "redirect:/mybatis-users/register/success";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("isEdit", false);
            addFormAttributes(model);
            return "mybatis-user/register";
        }
    }

    /**
     * 注册成功页面
     */
    @GetMapping("/register/success")
    public String registrationSuccess() {
        return "mybatis-user/register-success";
    }

    /**
     * 显示所有用户列表
     */
    @GetMapping("/list")
    public String listUsers(Model model) {
        List<MybatisUser> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "mybatis-user/list";
    }

    /**
     * 显示用户详情
     */
    @GetMapping("/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        MybatisUser user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        model.addAttribute("user", user);
        return "mybatis-user/detail";
    }

    /**
     * AJAX: 检查用户名是否可用
     */
    @GetMapping("/check-username")
    @ResponseBody
    public Map<String, Boolean> checkUsername(@RequestParam String username) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("available", userService.isUsernameAvailable(username));
        return result;
    }

    /**
     * AJAX: 检查邮箱是否可用
     */
    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("available", userService.isEmailAvailable(email));
        return result;
    }

    /**
     * AJAX: 检查手机号码是否可用
     */
    @GetMapping("/check-phone")
    @ResponseBody
    public Map<String, Boolean> checkPhone(@RequestParam String phone) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("available", userService.isPhoneAvailable(phone));
        return result;
    }

    // 辅助方法
    private void addFormAttributes(Model model) {
        model.addAttribute("countries", getCountries());
        model.addAttribute("cities", getCities());
        model.addAttribute("genderOptions", getGenderOptions());
        model.addAttribute("hobbyOptions", getHobbyOptions());
    }

    private List<String> getCountries() {
        return Arrays.asList("中国", "美国", "日本", "韩国", "英国", "法国", "德国", "加拿大", "澳大利亚");
    }

    private Map<String, String> getCities() {
        Map<String, String> cities = new HashMap<>();
        cities.put("beijing", "北京");
        cities.put("shanghai", "上海");
        cities.put("guangzhou", "广州");
        cities.put("shenzhen", "深圳");
        return cities;
    }

    private Map<String, String> getGenderOptions() {
        Map<String, String> genders = new HashMap<>();
        genders.put("male", "男");
        genders.put("female", "女");
        genders.put("other", "其他");
        return genders;
    }

    private List<String> getHobbyOptions() {
        return Arrays.asList("阅读", "运动", "音乐", "电影", "旅行", "摄影", "编程", "游戏");
    }
}