package cn.version1.javaBean;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2019/5/26
 * 17:42
 * #
 */
public class Student {

    private String mingzi;


    private int age;

    private boolean sex;

    public String getMingzi() {
        return mingzi;
    }

    public void setMingzi(String mingzi) {
        this.mingzi = mingzi;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mingzi='" + mingzi + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
