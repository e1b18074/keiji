package oit.is.group7.keiji.security;

import oit.is.group7.keiji.model.UserInfo;
import oit.is.group7.keiji.model.UserInfoMapper;
import oit.is.group7.keiji.security.SecurityUserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder ;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

public class AuthUtil {

    public static UserInfo getUserInfo() {

        if (SecurityContextHolder.getContext() == null) {
            throw new AuthenticatedUserNotFoundException();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new AuthenticatedUserNotFoundException();
        }
        // 未認証状態の場合、getPrincipal()は「anonymousUser」文字列（String型）が返る
        // そのため、認証済であるか判定するには、これがユーザ情報クラスのインスタンスかどうかを調べれば良い
        if (!(auth.getPrincipal() instanceof SecurityUserDetails)) {
            throw new AuthenticatedUserNotFoundException();
        }
        return ((SecurityUserDetails) auth.getPrincipal()).getUserInfo();
    }

    private static class AuthenticatedUserNotFoundException extends RuntimeException {
    }
}
