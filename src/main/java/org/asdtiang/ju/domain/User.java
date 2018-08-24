package org.asdtiang.ju.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author asdtiang asdtiangxia@163.com
 *
 * 2018年6月12日
 */
@Entity
@Table(name = "ju_user")
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8407376072364433531L;
    @Id
    @Column
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username; 
    @JsonIgnore
    @Column
    private String password; //密码
    @Column
    private String nickname;
    @Column
    private String salt; 
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdated;
    @Column
    private Boolean enabled = Boolean.TRUE;
    @Column
    private UsernameType usernameType;
    //微信ID
    private String weChatUid;

    //微博ID
    private String weiBoUid;

    //qqID
    private String qqUid;

    private Integer loginCount = 0;

    public static enum UsernameType {
        TEL,
        EMAIL,
        Other
    }

    public User() {
        super();
    }

    public User(Long id, String username, String password, String nickname,
                String salt, Calendar dateCreated, Calendar lastUpdated,
                Boolean enabled) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.salt = salt;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }




    public UsernameType getUsernameType() {
        return usernameType;
    }

    public void setUsernameType(UsernameType usernameType) {
        this.usernameType = usernameType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Calendar lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getWeChatUid() {
        return weChatUid;
    }

    public void setWeChatUid(String weChatUid) {
        this.weChatUid = weChatUid;
    }

    public String getWeiBoUid() {
        return weiBoUid;
    }

    public void setWeiBoUid(String weiBoUid) {
        this.weiBoUid = weiBoUid;
    }

    public String getQqUid() {
        return qqUid;
    }

    public void setQqUid(String qqUid) {
        this.qqUid = qqUid;
    }


    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", nickname=" + nickname + ", salt=" + salt
                + ", dateCreated=" + dateCreated + ", lastUpdated="
                + lastUpdated + ", enabled=" + enabled +  "]";
    }

}
