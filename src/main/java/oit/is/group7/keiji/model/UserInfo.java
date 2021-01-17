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
    private String name;
    private String password;
    private String date;
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
        return this.authorities;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
    return date;
  }

    public  void setDate(String date){
      this.date = date;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role){
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
