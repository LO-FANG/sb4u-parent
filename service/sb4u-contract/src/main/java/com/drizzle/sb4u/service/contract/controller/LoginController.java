package com.drizzle.sb4u.service.contract.controller;

import com.drizzle.sb4u.common.base.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/15/19:26
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {

    @PostMapping("/login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R info() {
        return R.ok().data("name", "admin")
                     .data("roles", "[admin]")
                     .data("avatar","http://gips2.baidu.com/it/u=3667383313,1853904760&fm=3042&app=3042&f=JPEG&wm=1,huayi,0,0,13,9&wmo=0,0&w=960&h=1280");
    }

    @PostMapping("/logout")
    public R logout() {
        return R.ok();
    }
}
