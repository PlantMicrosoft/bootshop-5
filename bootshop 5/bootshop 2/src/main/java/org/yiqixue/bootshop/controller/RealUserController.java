package org.yiqixue.bootshop.controller;

import org.yiqixue.bootshop.entity.RealUser;
import org.yiqixue.bootshop.service.RealUserService;
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
 * 用户控制器
 * 处理用户相关的HTTP请求
 */
@Controller
@RequestMapping("/realusers")
public class RealUserController {

    @Autowired
    private RealUserService userService;

    /**
     * 显示注册表单
     * GET /user/register
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // 创建空的User对象用于表单绑定
        model.addAttribute("user", new RealUser());

        // 添加下拉列表的选项数据
        model.addAttribute("countries", getCountries());
        model.addAttribute("cities", getCities());
        model.addAttribute("genderOptions", getGenderOptions());
        model.addAttribute("hobbyOptions", getHobbyOptions());

        return "user/register"; // 返回 templates/user/register.html
    }

    /**
     * 处理注册表单提交
     * POST /user/register
     */
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RealUser user,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        // 如果验证失败，返回表单页面
        if (bindingResult.hasErrors()) {
            // 重新添加下拉列表数据
            model.addAttribute("countries", getCountries());
            model.addAttribute("cities", getCities());
            model.addAttribute("genderOptions", getGenderOptions());
            model.addAttribute("hobbyOptions", getHobbyOptions());
            return "user/register";
        }

        // 验证条款是否同意
        if (user.getTermsAccepted() == null || !user.getTermsAccepted()) {
            bindingResult.rejectValue("termsAccepted", "error.user",
                    "必须同意服务条款才能注册");
            model.addAttribute("countries", getCountries());
            model.addAttribute("cities", getCities());
            model.addAttribute("genderOptions", getGenderOptions());
            model.addAttribute("hobbyOptions", getHobbyOptions());
            return "user/register";
        }

        try {
            // 注册用户
            userService.registerUser(user);

            // 添加成功消息
            redirectAttributes.addFlashAttribute("successMessage",
                    "注册成功！欢迎您，" + user.getUsername());

            // 重定向到成功页面
            return "redirect:/realusers/register/success";

        } catch (RuntimeException e) {
            // 处理异常（如用户名已存在）
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("countries", getCountries());
            model.addAttribute("cities", getCities());
            model.addAttribute("genderOptions", getGenderOptions());
            model.addAttribute("hobbyOptions", getHobbyOptions());
            return "user/register";
        }
    }

    /**
     * 注册成功页面
     * GET /user/register/success
     */
    @GetMapping("/register/success")
    public String registrationSuccess() {
        return "user/register-success";
    }

    /**
     * 显示所有用户列表
     * GET /user/list
     */
    @GetMapping("/list")
    public String listUsers(Model model) {
        List<RealUser> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    /**
     * 显示用户详情
     * GET /user/{id}
     */
    @GetMapping("/{id}")
    public String viewUser(@PathVariable Integer id, Model model) {
        RealUser user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        model.addAttribute("user", user);
        return "user/detail";
    }

    /**
     * AJAX: 检查用户名是否可用
     * GET /user/check-username?username=xxx
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
     * GET /user/check-email?email=xxx
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
     * GET /user/check-phone?phone=xxx
     */
    @GetMapping("/check-phone")
    @ResponseBody
    public Map<String, Boolean> checkPhone(@RequestParam String phone) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("available", userService.isPhoneAvailable(phone));
        return result;
    }

    // ========== 辅助方法：提供下拉列表数据 ==========

    private List<String> getCountries() {
        return Arrays.asList(
                "中国", "美国", "日本", "韩国", "英国",
                "法国", "德国", "加拿大", "澳大利亚"
        );
    }

    private Map<String, String> getCities() {
        Map<String, String> cities = new HashMap<>();
        cities.put("beijing", "北京");
        cities.put("shanghai", "上海");
        cities.put("guangzhou", "广州");
        cities.put("shenzhen", "深圳");
        cities.put("hangzhou", "杭州");
        cities.put("chengdu", "成都");
        return cities;
    }

    private Map<String, String> getGenderOptions() {
        Map<String, String> genders = new HashMap<>();
        genders.put("male", "男");
        genders.put("female", "女");
        genders.put("other", "其他");
        return genders;
    }

    private Map<String, String> getHobbyOptions() {
        Map<String, String> hobbies = new HashMap<>();
        hobbies.put("reading", "阅读");
        hobbies.put("sports", "运动");
        hobbies.put("music", "音乐");
        hobbies.put("travel", "旅行");
        hobbies.put("gaming", "游戏");
        hobbies.put("cooking", "烹饪");
        return hobbies;
    }
}