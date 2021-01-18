package oit.is.group7.keiji.security;

import java.util.ArrayList;
import java.util.Collection;

import oit.is.group7.keiji.model.UserInfo;
import oit.is.group7.keiji.model.UserInfoMapper;
import oit.is.group7.keiji.security.SecurityUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository ;
import org.springframework.util.StringUtils;

@Service
class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 入力値が空の場合もあるため、予めチェックしておく。（DBFluteの場合、事前にチェック必須。）
        if (username == null || username.isEmpty()) {
           throw new UsernameNotFoundException("username is empty");
        }

        try {
            UserInfo user = userInfoMapper.selectByUser(username);
            Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());

            if (user != null) {
              UserInfo userInfo = new UserInfo(user.getName(), user.getPassword(), authorities);
              // さらに、Spring Security用の認証情報も生成
              return new SecurityUserDetails(userInfo);
            } else {
              // ユーザが見つからなかった場合、規定の例外を投げる。
              // なお、UsernameNotFoundExceptionにメッセージを渡しても画面に反映されない（上記と同じ）
              throw new UsernameNotFoundException("");
            }
        } catch (Exception e) {
            // DBアクセス等でエラー（接続エラー等）が起きた場合に備えて、
            // 例外を捕まえ、適正なメッセージを持った別の例外に変えて投げ直す。
            // こうしておかないと、発生した元のExceptionの持つエラーメッセージがログイン画面に表示されてしまう
            throw new DbAccessException(
                    messageSource.getMessage("demo.unexpectedError", null, LocaleContextHolder.getLocale()), e);
        }
    }

    private static class DbAccessException extends RuntimeException {

        DbAccessException(String msg, Exception cause) {
            super(msg, cause);
        }
    }

}
