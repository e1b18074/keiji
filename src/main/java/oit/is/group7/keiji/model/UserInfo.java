package oit.is.group7.keiji.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Collection;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {

    private int number;
    private String id;
    private String name;
    private String password;
    private String role;
    private boolean enabled;
    private boolean AccountNonExpired;
    private Collection<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public UserInfo() {

    }

    public UserInfo(String name, String password, Collection<GrantedAuthority> authorities) {
      this.name = name;
      this.password = password;
      this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public int getUserNumber() {
        return this.number;
    }

    public void setUserNumber(int number){
        this.number = number;
    }

    public String getUserId() {
        return this.id;
    }

    public void setUserId(String id){
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserPassword(String password){
        this.password = password;
    }

    public String getUserName() {
        return this.name;
    }

    public void setUserName(String name){
        this.name = name;
    }

    public String getUserRole() {
        return this.role;
    }

    public void setUserRole(String role){
        this.role = role;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

}
