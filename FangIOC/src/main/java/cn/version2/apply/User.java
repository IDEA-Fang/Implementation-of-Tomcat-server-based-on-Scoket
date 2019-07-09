package cn.version2.apply;

/**
 * 用户Bean
 * 
 * @author jijs
 * @date 2017-12-3
 */
public class User {
    private String userName;
    private Integer age;
    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [userName=" + userName + ", age=" + age + "]";
    }

}