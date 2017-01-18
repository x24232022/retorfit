package com.example.administrator.retorfit_20170106.okHttpPost;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public class MultUser {
    //工具：GsonFormat：插件,根据json字符串帮我们生成实体类

    private String name;
    private String username;
    private String nickname;
    private String password;
    private String uuid;

    public MultUser(String name, String username, String nickname, String password, String uuid) {
        this.name = name;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
