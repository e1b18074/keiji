package oit.is.group7.keiji.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class seclogin extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {



    http.authorizeRequests() // Spring Securityのフォームを利用してログインを行う
        .antMatchers("/", "/login").permitAll() // 認証なしでアクセス可能なパス
        .anyRequest().authenticated(); // それ以外は認証が必要
    http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/keiji") // ログイン成功時に遷移するURL
                .usernameParameter("username") // ログインフォームのユーザー欄のname属性を設定
                .passwordParameter("password") // ログインフォームのパスワード欄のname属性を設定
                .failureUrl("/login?error") // ログイン失敗時に遷移するURL
                .permitAll();
    http.logout().logoutSuccessUrl("/");

    http.csrf().disable();
    http.headers().frameOptions().disable();

  }

}
