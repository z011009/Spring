package cn.wugou.demo02;

public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void select() {
        log("select");
        userService.select();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void insert() {
        log("insert");
        userService.insert();
    }

    //日志方法
    public void log(String msg) {
        System.out.println("使用了" + msg + "方法");
    }
}
